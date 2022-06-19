/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Yohannes
 */
public class SentMessage extends HBox {
    

    public SentMessage(String msg) {
        

        setAlignment(Pos.CENTER_RIGHT);
        setPadding(new Insets(5,5,5,10));

        Text text = new Text(msg);
        TextFlow textFlow = new TextFlow(text);


        textFlow.setStyle("-fx-color: rgb(239,242, 255 );" + 
                "-fx-background-color: rgb(15, 125, 242);" + 
                "-fx-background-radius: 20px;");

        textFlow.setPadding(new Insets(5,5,5,10));
        text.setFill(Color.color(0.934, 0.945, 0.996));

        getChildren().add(textFlow);
    }

    
    
}
