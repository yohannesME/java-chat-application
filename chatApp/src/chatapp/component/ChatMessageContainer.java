/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.component;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Yohannes
 */
public class ChatMessageContainer extends VBox{
    protected final ScrollPane sp_main;

    public ScrollPane getScrollPane() {
        return sp_main;
    }
 
    
    
    public ChatMessageContainer(){
        sp_main = new ScrollPane();
 
                
                
        sp_main.setId("chatScrollPane");

        setAlignment(javafx.geometry.Pos.CENTER);
        setId("messageContainer");
        setPrefHeight(549.0);
        setPrefWidth(594.0);
        sp_main.setContent(this);
        
        
        heightProperty().addListener((observable, oldValue, newValue)->{
            sp_main.setVvalue((Double)newValue);
        });
        
        
    }
}
