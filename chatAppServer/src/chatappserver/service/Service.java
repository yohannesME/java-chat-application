package chatappserver.service;


import chatappserver.model.Model_Alert;
import chatappserver.model.Model_Client;
import chatappserver.model.Model_Login;
import chatappserver.model.Model_Receive_Message;
import chatappserver.model.Model_User_Account;
import chatappserver.model.Model_Send_Message;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;

public class Service {

    private static Service instance;
    private SocketIOServer server;
    private ServiceUser serviceUser;
    private List<Model_Client> listClient;
    private TextArea textArea;
    private final int PORT_NUMBER = 9999;

    public static Service getInstance(TextArea textArea) {
        if (instance == null) {
            instance = new Service(textArea);
        }
        return instance;
    }

    private Service(TextArea textArea) {
        this.textArea = textArea;
        serviceUser = new ServiceUser();
        listClient = new ArrayList<>();
    }

    public void startServer() {
        Configuration config = new Configuration();
        config.setPort(PORT_NUMBER);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient sioc) {
                textArea.setText(textArea.getText() + "\nOne client connected\n");
            }
        });
        server.addEventListener("register", Model_User_Account.class, new DataListener<Model_User_Account>() {
            @Override
            public void onData(SocketIOClient sioc, Model_User_Account t, AckRequest ar) throws Exception {
                Model_Alert message = serviceUser.register(t);
                ar.sendAckData(message.isAction(), message.getMessage(), message.getData());
                System.out.println(message.isAction());
                if (message.isAction()) {
                    textArea.setText("User has Register :" + t.getUserName() + " Pass :" + t.getPassword() + "\n");
                    addClient(sioc, (Model_User_Account) message.getData());
                    server.getBroadcastOperations().sendEvent("list_user", (Model_User_Account) message.getData());
                }
            }
        });
        server.addEventListener("login", Model_Login.class, new DataListener<Model_Login>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Login t, AckRequest ar) throws Exception {
                Model_User_Account login = serviceUser.login(t);
                System.out.println(login==null);
                if (login != null) {
                    ar.sendAckData(true, login);
                    addClient(sioc, login);
//                    userConnect(login.getUserID());
                } else {
                    ar.sendAckData(false);
                }
            }
        });
        
        server.addEventListener("list_user", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer userID, AckRequest ar) throws Exception {
                try {
                    List<Model_User_Account> list = serviceUser.getUser(userID);
                    sioc.sendEvent("list_user", list.toArray());
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });
        
        server.addEventListener("send_to_user", Model_Send_Message.class, new DataListener<Model_Send_Message>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Send_Message t, AckRequest ar) throws Exception {
                sendToClient(t);
            }
        });
        
//        server.addDisconnectListener(new DisconnectListener() {
//            @Override
//            public void onDisconnect(SocketIOClient sioc) {
//                int userID = removeClient(sioc);
//                if (userID != 0) {
//                    //  removed
//                    userDisconnect(userID);
//                }
//            }
//        });

        server.start();
        textArea.setText(textArea.getText() + "Server has Start on port : " + PORT_NUMBER + "\n");
    }

//    private void userConnect(int userID) {
//        server.getBroadcastOperations().sendEvent("user_status", userID, true);
//    }
//
//    private void userDisconnect(int userID) {
//        server.getBroadcastOperations().sendEvent("user_status", userID, false);
//    }

    private void addClient(SocketIOClient client, Model_User_Account data) {
        listClient.add(new Model_Client(client, data ));
        System.out.println("added the client"); 
    }

    private void sendToClient(Model_Send_Message data){

        for (Model_Client c : listClient) {
            if (c.getUser().getUserID() == data.getToUserID()) {
                serviceUser.message(data);
                c.getClient().sendEvent("receive_ms", new Model_Receive_Message(data.getFromUserID(), data.getText()));
                break;
            }
        }      
    }
    

    

    public int removeClient(SocketIOClient client) {
        for (Model_Client d : listClient) {
            if (d.getClient() == client) {
                listClient.remove(d);
                return d.getUser().getUserID();
            }
        }
        return 0;
    }

    public List<Model_Client> getListClient() {
        return listClient;
    }
}
