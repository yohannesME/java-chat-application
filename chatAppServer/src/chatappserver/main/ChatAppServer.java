/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatappserver.main;

import chatappserver.connection.DatabaseConnection;
import chatappserver.service.Service;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Yohannes
 */
public class ChatAppServer extends Application {
    AnchorPane root;
    ScrollPane sp_main;
    TextArea info;
    
    
    @Override
    public void start(Stage primaryStage) {
        root = new AnchorPane();
        
        

        
        
        sp_main = new ScrollPane();
        sp_main.setPrefSize(418, 256);
        sp_main.setLayoutY(70);
        sp_main.setLayoutX(30);
        sp_main.setFitToWidth(true);
        
        info = new TextArea();
        info.setPrefSize(412, 248);
        
        info.setEditable(false);
        
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        
        sp_main.setContent(info);
        root.getChildren().addAll(sp_main);
        Scene scene = new Scene(root, 500, 500);
        
        primaryStage.setOnShown((event) -> {
            formWindowOpened(event);
        });
        
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });        
        
        
    }
    
    
    private void formWindowOpened(WindowEvent event){
        try {
            DatabaseConnection.getInstance().connectToDatabase();
            Service.getInstance(info).startServer();

        } catch (SQLException ex) {
            Logger.getLogger(ChatAppServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
