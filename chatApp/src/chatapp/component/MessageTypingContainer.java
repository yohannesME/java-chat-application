/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.component;

import chatapp.event.PublicEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Yohannes
 */
public class MessageTypingContainer extends HBox{
    protected final TextField message_tf;
    protected final ImageView sendBtnImage;

    public MessageTypingContainer() {

        message_tf = new TextField();
        sendBtnImage = new ImageView(); 
        

        setAlignment(javafx.geometry.Pos.CENTER);
        setPrefHeight(60.0);
        setPrefWidth(605.0);

        message_tf.setAlignment(javafx.geometry.Pos.CENTER);
        message_tf.setPrefHeight(65.0);
        message_tf.setPrefWidth(540.0);
        message_tf.setPromptText("Type something to send....");
        message_tf.getStyleClass().add("transparent-background");
        message_tf.getStylesheets().add("/chatapp/values/style.css");

        sendBtnImage.setFitHeight(30.0);
        sendBtnImage.setFitWidth(32.0);
        sendBtnImage.setPickOnBounds(true);
        sendBtnImage.setImage(new Image(getClass().getResource("/images/send-message.png").toExternalForm()));
        
        getChildren().add(message_tf);
        getChildren().add(sendBtnImage);
        
        sendBtnImage.setOnMouseClicked((event)->{
            PublicEvent.getInstance().getEventChat().sendMessage(message_tf.getText());
            message_tf.setText("");
        });
        message_tf.setOnAction((event)->{
            PublicEvent.getInstance().getEventChat().sendMessage(message_tf.getText());
            message_tf.setText("");
;
        });
    }
    

}
