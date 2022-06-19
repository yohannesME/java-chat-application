package chatapp.main;



import chatapp.component.*;
import chatapp.event.*;
import chatapp.model.Model_Send_Message;
import chatapp.service.Service;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;


public  class MainChatGUI extends BorderPane {
    
    protected static MainChatGUI instance;

    protected final HBox hBox;

    //MENU OPTIONS
    SideBar sideBar;
    //-------------------
    //SECOND PANEL
    
    ChatListContainer chatlistContainer;
    
    //SEARCH BAR
//    SearchBarContainer searchBarContainer;
    //---------------

    protected final Chat chat;

    public static MainChatGUI getInstance(){
        if(instance == null)
            return new MainChatGUI();
        return instance;
    }
    
 
    public MainChatGUI() {

        instance = this;
        
        hBox = new HBox();

        sideBar = new SideBar();
        
        chatlistContainer = new ChatListContainer();
        
        
        chat = new Chat();

        setPrefHeight(702.0);
        setPrefWidth(1006.0);

        BorderPane.setAlignment(hBox, javafx.geometry.Pos.CENTER);
        hBox.setId("container");
        hBox.setPrefHeight(702.0);
        hBox.setPrefWidth(401.0);
        hBox.getStyleClass().add("gray-background");
        hBox.getStylesheets().add("/chatapp/values/style.css");

        setLeft(hBox);


//       setCenter(chat);

        
//        chatlistContainer.getChildren().add(pane1);

        //the added chat
//        getChildren().add(currentChat);        

        hBox.getChildren().add(sideBar);

        //------------
        hBox.getChildren().add(chatlistContainer.getScrollPane());
        
        PublicEvent.getInstance().addEventInitChat(new EventInitChat(){
            @Override
            public void initChat() {
                setCenter(chat);
            }
            
        });

    }
    
   
        
    
}
