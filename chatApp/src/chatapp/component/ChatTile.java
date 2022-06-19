/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.component;

import chatapp.event.PublicEvent;
import chatapp.model.Model_User_Account;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 *
 * @author Yohannes
 */
public class ChatTile  extends HBox {
    //CHATTER NAME
    protected final Label chatName;
    protected final Circle statusIndicator;
    protected final Label status;
    //-----------------------------
    
    private Model_User_Account user;

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        PublicEvent.getInstance().getEventInitChat().initChat();
        this.user = user;
        chatName.setText(user.getUserName());
    }
    
    public ChatTile(){
        
        chatName = new Label();
        statusIndicator = new Circle();
        status = new Label();
        
        


       setAlignment(javafx.geometry.Pos.CENTER_LEFT);
       setId("chatterNameContainer");
       setPrefHeight(82.0);
       setPrefWidth(200.0);

        chatName.setPrefHeight(31.0);
        chatName.setPrefWidth(90.0);
        chatName.setTextFill(javafx.scene.paint.Color.valueOf("#9da7a7"));
        chatName.setFont(new Font("Ebrima Bold", 24.0));
        HBox.setMargin(chatName, new Insets(0.0, 0.0, 0.0, 20.0));

//        statusIndicator.setFill(javafx.scene.paint.Color.valueOf("#50c984"));
//        statusIndicator.setRadius(4.0);
//        statusIndicator.setStroke(javafx.scene.paint.Color.BLACK);
//        statusIndicator.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
//        statusIndicator.setStrokeWidth(0.0);

//        status.setText(" online");
        status.setTextFill(javafx.scene.paint.Color.valueOf("#9da7a7"));
        status.setFont(new Font("Ebrima Bold", 16.0));
        HBox.setMargin(status, new Insets(0.0, 10.0, 0.0, 10.0));

        
       getChildren().add(chatName);
//       getChildren().add(statusIndicator);
       getChildren().add(status);
    }
    
}
