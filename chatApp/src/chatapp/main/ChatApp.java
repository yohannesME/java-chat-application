/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.main;

import chatapp.event.EventChat;
import chatapp.event.EventMain;
import chatapp.event.PublicEvent;
import chatapp.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yohannes
 */
public class ChatApp extends Application {
    

    AuthenticationGUI root;
    
    @Override
    public void start(Stage stage) throws Exception {
        root = new AuthenticationGUI();

        Service.getInstance().startServer();
        
        Scene scene = new Scene(root);
        
        PublicEvent.getInstance().addEventMain(new EventMain(){
            @Override
            public void initChat() {
                scene.setRoot(MainChatGUI.getInstance());
                Service.getInstance().getClient().emit("list_user", Service.getInstance().getUser().getUserID());
            }
        });
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
   
    }
    
}
