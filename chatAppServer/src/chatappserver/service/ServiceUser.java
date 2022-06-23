package chatappserver.service;


import chatappserver.connection.DatabaseConnection;
import chatappserver.model.Model_Alert;
import chatappserver.model.Model_Client;
import chatappserver.model.Model_Login;
import chatappserver.model.Model_Saved_Message;
import chatappserver.model.Model_Send_Message;
import chatappserver.model.Model_User_Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser {

    public ServiceUser() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }

    public Model_Alert register(Model_User_Account data) {
        //  Check user exit

        Model_Alert message = new Model_Alert();
        try {
            System.out.println(con==null);
            
            PreparedStatement p = con.prepareStatement(CHECK_USER,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                message.setAction(false);
                message.setMessage("User Already Exit");
                System.out.println("here user already exits");
            } else {
                message.setAction(true);
            }

            r.close();
            p.close();

            if (message.isAction()) {
                //  Insert User Register
                p = con.prepareStatement(INSERT_USER);
                p.setString(1, data.getUserName());
                p.setString(2, data.getPassword());
                p.setString(3, data.getName());
                p.setString(4, data.getEmail());
                
                p.execute();
                p.close();
                message.setAction(true);
                message.setMessage("Ok");
                message.setData(data);
            }
        } catch (SQLException e) {
            message.setAction(false);
            message.setMessage("Server Error");
            System.out.println(e.toString());
        }
        return message;
    }

    public Model_User_Account login(Model_Login login) throws SQLException {
        Model_User_Account data = null;
        PreparedStatement p = con.prepareStatement(LOGIN,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        p.setString(1, login.getUserName());
        p.setString(2, login.getPassword());

        ResultSet r = p.executeQuery();

        if (r.first()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String email = r.getString(3);
            String name = r.getString(4);
            data = new Model_User_Account(userName, login.getPassword(), name, email);
            data.setUserID(userID);
        }
        r.close();
        p.close();

        return data;
    }

    public List<Model_User_Account> getUser(int exitUser) throws SQLException {
        List<Model_User_Account> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, exitUser);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String email = r.getString(3);
            String name = r.getString(4);
            Model_User_Account u = new Model_User_Account(userName, "", name, email);
            u.setUserID(userID);
            list.add(u);
        }
        r.close();
        p.close();
        return list;
    }

    private boolean checkUserStatus(int userID) {
        List<Model_Client> clients = Service.getInstance(null).getListClient();
        for (Model_Client c : clients) {
            if (c.getUser().getUserID() == userID) {
                return true;
            }
        }
        return false;
    }
    
    public void message(Model_Send_Message data){
        try{
        ArrayList<Model_Saved_Message> messageFromList = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(GET_MESSAGE);
        p.setInt(1, data.getFromUserID());
        ResultSet r = p.executeQuery();
        while(r.next()){
            if(r.getObject(1) != null)
                messageFromList = (ArrayList<Model_Saved_Message>) r.getObject(1);
            else
                messageFromList = new ArrayList<Model_Saved_Message>();
        }   
        r.close();
        
        ArrayList<Model_Saved_Message> messageToList = new ArrayList<>();
        
        p.setInt(1, data.getFromUserID());
        r = p.executeQuery();
        while(r.next()){
            if(r.getObject(1) != null)
                messageToList = (ArrayList<Model_Saved_Message>) r.getObject(1);
            else
                messageToList = new ArrayList<Model_Saved_Message>();
        }   
        r.close();
        p.close();
        
        
        messageFromList.add(new Model_Saved_Message(data.getText(), true));
        messageToList.add(new Model_Saved_Message(data.getText(), false));
        
        p = con.prepareStatement(SET_MESSAGE);
        p.setInt(1, Model_Saved_Message.toJsonObject(messageFromList));
        r = p.executeQuery();
        while(r.next()){
            if(r.getObject(1) != null)
                messageFromList = (ArrayList<Model_Saved_Message>) r.getObject(1);
            else
                messageFromList = new ArrayList<Model_Saved_Message>();
        }   
        r.close();
        
        
        
        
        
            
        }catch(SQLException e){
            
        }
    }
    

    //  SQL
    private final String GET_MESSAGE = "SELECT Message FROM user WHERE UserID=?";
    private final String SET_MESSAGE = "UPDATE user SET Message=?";
    private final String LOGIN = "select UserID, UserName, Email, Name from `user` where `user`.UserName=BINARY(?) and `user`.`Password`=BINARY(?)";
    private final String SELECT_USER_ACCOUNT = "select UserID, UserName, Email, Name from user where UserID<>?";
    private final String INSERT_USER = "insert into user (UserName, `Password`, `Name`, `Email`) values (?,?,?,?)";
    private final String INSERT_USER_ACCOUNT = "insert into user_account (UserID, UserName) values (?,?)";
    private final String CHECK_USER = "select UserName from user where UserName =? limit 1";
    //  Instance
    private final Connection con;
}


