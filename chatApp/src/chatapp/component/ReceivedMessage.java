/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Yohannes
 */
public class ReceivedMessage extends HBox{

    public ReceivedMessage(String msg) {
       setAlignment(Pos.CENTER_LEFT);
       setPadding(new Insets(5,5,5,10));

        Text text = new Text(msg);
        TextFlow textFlow = new TextFlow(text);


        textFlow.setStyle("-fx-color: rgb(233,233, 235 );" + 
                "-fx-background-radius: 20px;");

        textFlow.setPadding(new Insets(5,5,5,10));

       getChildren().add(textFlow);
    }
    
    
}
