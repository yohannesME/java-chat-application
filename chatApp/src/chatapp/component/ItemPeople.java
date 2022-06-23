/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.component;

import chatapp.event.PublicEvent;
import chatapp.model.Model_User_Account;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 *
 * @author Yohannes
 */
public class ItemPeople extends HBox{
    
    //CHAT CONTAINER 
//    protected static ItemPeople instance;
    protected final ImageView profilePictureIcon;
    protected final VBox textContainer;
    protected final Label chatterName;
    protected final Label chatLastMessage;
    protected final Pane pane2;
    protected final Circle circle;
    protected final Label unreadMessageNumber;
    //------------------------------
    
//    public static ItemPeople getInstance(){
//        if(instance==null)
//            return new ItemPeople();
//        return instance;
//    }
    
    Model_User_Account user;

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }
    
    
    ItemPeople(Model_User_Account data){
//        instance = this;
        user = data;
        
        profilePictureIcon = new ImageView();
        textContainer = new VBox();
        chatterName = new Label();
        chatLastMessage = new Label();
        pane2 = new Pane();
        circle = new Circle();
        unreadMessageNumber = new Label();
    
        //DYNAMICALLY GENERATED CHAT PERSON PREVIEW

        setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        setId("currentChat");
        setPrefHeight(100.0);
        setPrefWidth(200.0);
        setPadding(new Insets(3, 5, 3, 5));

        profilePictureIcon.setFitHeight(60.0);
        profilePictureIcon.setFitWidth(69.0);
        profilePictureIcon.setPickOnBounds(true);
        profilePictureIcon.setPreserveRatio(true);
        profilePictureIcon.setImage(new Image(getClass().getResource("/images/profile-picture-.png").toExternalForm()));
        HBox.setMargin(profilePictureIcon, new Insets(0.0, 0.0, 0.0, 10.0));

        textContainer.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        textContainer.setPrefHeight(100.0);
        textContainer.setPrefWidth(210.0);

        chatterName.setPrefHeight(26.0);
        chatterName.setPrefWidth(268.0);
        chatterName.setText(user.getUserName());
        chatterName.setPadding(new Insets(USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, 20));
        chatterName.setTextFill(javafx.scene.paint.Color.WHITE);
        chatterName.setFont(new Font("Ebrima Bold", 20.0));

        chatLastMessage.setPrefHeight(26.0);
        chatLastMessage.setPrefWidth(281.0);
        chatLastMessage.setText("you: ok!");
        chatLastMessage.setPadding(new Insets(0,0,0,10));
        chatLastMessage.setTextFill(javafx.scene.paint.Color.valueOf("#9da7a7"));
        chatLastMessage.setFont(new Font("Ebrima", 15.0));
        textContainer.setOpaqueInsets(new Insets(5,2,2,5));
        HBox.setMargin(textContainer, new Insets(0.0));

        pane2.setPrefHeight(70.0);
        pane2.setPrefWidth(137.0);

        circle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        circle.setLayoutX(39.0);
        circle.setLayoutY(36.0);
        circle.setRadius(12.0);
        circle.setStroke(javafx.scene.paint.Color.BLACK);
        circle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        circle.setStrokeWidth(0.0);

        unreadMessageNumber.setLayoutX(32.0);
        unreadMessageNumber.setLayoutY(28.0);
        unreadMessageNumber.setText("");
        
        
        getChildren().add(profilePictureIcon);
        textContainer.getChildren().add(chatterName);
        textContainer.getChildren().add(chatLastMessage);
        getChildren().add(textContainer);
        pane2.getChildren().add(circle);
        pane2.getChildren().add(unreadMessageNumber);
        getChildren().add(pane2);
        
        
//        //THIS IS A PROFILE OF ONE CHAT THAT IS SHOWN ON THE LEFT SIDE
        boolean mouse = false;
        setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.out.println(user.getUserName());
                PublicEvent.getInstance().getEventChat().selectChat(user);
            }

        });
        setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
//                mouse = false;
                setStyle("-fx-color: rgb(5, 19, 97);");        
            }

        });
        setOnMouseEntered((evt)->{
//            mouse = true;
            setStyle("-fx-color: rgb(50, 19, 97);");        
        });

    }
    
}
