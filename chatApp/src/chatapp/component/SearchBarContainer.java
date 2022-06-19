/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.component;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Yohannes
 */
public class SearchBarContainer extends HBox {
    protected final ImageView searchIconImage;
    protected final TextField searchBar;
    protected final Pane pane1;

    public SearchBarContainer() {
        searchIconImage = new ImageView();
        searchBar = new TextField();
        pane1 = new Pane();
        

        setAlignment(javafx.geometry.Pos.CENTER);
        setId("searchBar");
        setPrefHeight(42.0);
        setPrefWidth(200.0);
        getStylesheets().add("/chatapp/values/style.css");

        searchIconImage.setFitHeight(25.0);
        searchIconImage.setFitWidth(30.0);
        searchIconImage.setPickOnBounds(true);
        searchIconImage.setPreserveRatio(true);
        searchIconImage.setImage(new Image(getClass().getResource("/images/icons8-search-24.png").toExternalForm()));
        HBox.setMargin(searchIconImage, new Insets(0.0, 10.0, 0.0, 0.0));

        searchBar.setPrefHeight(25.0);
        searchBar.setPrefWidth(216.0);
        searchBar.setPromptText("Search");
        searchBar.getStyleClass().add("transparent-background");
        searchBar.getStylesheets().add("/chatapp/values/style.css");
        searchBar.setFont(new Font("Ebrima", 15.0));
        VBox.setMargin(this, new Insets(20.0, 30.0, 20.0, 30.0));

        pane1.setPrefHeight(2.0);
        pane1.setPrefWidth(330.0);
        pane1.getStyleClass().add("dark-gray-background");
        pane1.getStylesheets().add("/chatapp/values/style.css");
         
        getChildren().add(searchIconImage);
        getChildren().add(searchBar);

    }
    
    
    
}
