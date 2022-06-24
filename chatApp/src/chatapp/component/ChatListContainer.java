
package chatapp.component;

import chatapp.event.EventMenuLeft;
import chatapp.event.PublicEvent;
import chatapp.model.Model_User_Account;
import chatapp.service.Service;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class ChatListContainer extends  VBox{

    ScrollPane spChats;

    public ChatListContainer() {
        
        spChats = new ScrollPane();
        
        setId("searchAndChatList");
        setPrefHeight(702.0);
        setPrefWidth(326.0);

        
        heightProperty().addListener((observable, oldValue, newValue)->{
            spChats.setVvalue((Double)newValue);
        });
        
        spChats.setContent(this);
        
//        spChats.setStyle("");
        getStyleClass().add("gray-background");

        
        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft(){
            @Override
            public void newUser(List<Model_User_Account> users) {
                ItemPeople people;

                for (Model_User_Account user : users) {
                    if(!(user.getUserID() == Service.getInstance().getUser().getUserID())){
                        people = new ItemPeople(user);
                        getChildren().add(people);
                        
                    }
                }
            }

        });
    }

    public ScrollPane getScrollPane() {
        return spChats;
    }
    
    
    
    
}
