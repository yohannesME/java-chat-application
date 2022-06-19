
package chatapp.component;

import chatapp.event.EventChat;
import chatapp.event.PublicEvent;
import chatapp.model.Model_Receive_Message;
import chatapp.model.Model_Send_Message;
import chatapp.model.Model_User_Account;
import chatapp.service.Service;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Chat extends VBox {
//    //CHATTER NAME
    ChatTile chatTile;
//    //-----------------------------
    //MESSAGES
    ChatMessageContainer chatMessageContainer;
    
    Model_User_Account userTosend;

    //---------SMALL BECAUSE DYNAMICALLY GENERATED
    //MESSAGE TYPING
    MessageTypingContainer messageTypingContainer;
    //------------------
    
    public Chat(){
        chatTile = new ChatTile();
        
        chatMessageContainer = new ChatMessageContainer();

        messageTypingContainer = new MessageTypingContainer();

        BorderPane.setAlignment(this, javafx.geometry.Pos.CENTER);
        setPrefHeight(200.0);
        setPrefWidth(100.0);

        getChildren().add(chatTile);
        getChildren().add(chatMessageContainer.getScrollPane());

        getChildren().add(messageTypingContainer);
        
        widthProperty().addListener((observable, oldValue, newValue) -> {
            chatMessageContainer.setPrefWidth((double)newValue-410);
    });   
        
        
        PublicEvent.getInstance().addEventChat(new EventChat(){
        @Override
        public void sendMessage(String message) {
            sendMsg(message);
        }

        @Override
        public void receiveMessage(Model_Receive_Message message) {
            if(message.getFromUserID() == chatTile.getUser().getUserID())
                addReceivedMessageLabel(message.getText(), chatMessageContainer);
        }

        @Override
        public void selectChat(Model_User_Account user) {
            chatTile.setUser(user);
            chatMessageContainer.getChildren().removeAll(chatMessageContainer.getChildren());
            userTosend = user;
        }
        
        
    });    
    
        
                
    }
    void sendMsg(String messageToSend){
            if(!messageToSend.isEmpty()){
                send(new Model_Send_Message( Service.getInstance().getUser().getUserID(),userTosend.getUserID(), messageToSend));
                addSentMessageLabel(messageToSend);
            }
    }
    
    
    public void addSentMessageLabel(String msg){
        SentMessage msgContainer = new SentMessage(msg);
        chatMessageContainer.getChildren().add(msgContainer);   
    }

    
    

    public static void addReceivedMessageLabel(String messageFromClient, VBox vbox){
        ReceivedMessage msgContainer = new ReceivedMessage(messageFromClient);

        Platform.runLater(()->{
            vbox.getChildren().add(msgContainer);
        });
    }    
    
    private void send(Model_Send_Message data) {
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }
} 
