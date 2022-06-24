package chatapp.component;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

public class SideBar extends VBox {
    protected final Pane pane;
    protected final ImageView chatListBtn;

    protected final ImageView logoutBtn;
    protected final Pane pane0;    

    public SideBar() {       
        pane = new Pane();
        chatListBtn = new ImageView();
        pane0 = new Pane();
        logoutBtn = new ImageView();

        setAlignment(javafx.geometry.Pos.CENTER);
        setId("sideBar");
        setPrefHeight(702.0);
        setPrefWidth(68.0);
        getStyleClass().add("dark-gray-background");
        getStylesheets().add("/chatapp/values/style.css");

        pane.setPrefHeight(278.0);
        pane.setPrefWidth(92.0);
        pane.getStylesheets().add("/chatapp/values/style.css");

        chatListBtn.setFitHeight(48.0);
        chatListBtn.setFitWidth(38.0);
        chatListBtn.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
        chatListBtn.setPickOnBounds(true);
        chatListBtn.setPreserveRatio(true);
        chatListBtn.setImage(new Image(getClass().getResource("/images/icons8-chatting-91.png").toExternalForm()));
        VBox.setMargin(chatListBtn, new Insets(20.0, 0.0, 0.0, 0.0));

        VBox.setVgrow(pane0, javafx.scene.layout.Priority.ALWAYS);
        pane0.setPrefHeight(200.0);
        pane0.setPrefWidth(200.0);
        VBox.setMargin(pane0, new Insets(30.0, 0.0, 0.0, 0.0));

        logoutBtn.setFitHeight(48.0);
        logoutBtn.setFitWidth(38.0);
        logoutBtn.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
        logoutBtn.setPickOnBounds(true);
        logoutBtn.setPreserveRatio(true);
        logoutBtn.setImage(new Image(getClass().getResource("/images/icons8-logout-24.png").toExternalForm()));
        VBox.setMargin(logoutBtn, new Insets(0.0, 0.0, 30.0, 0.0));

        getChildren().add(pane);
        getChildren().add(chatListBtn);
        getChildren().add(pane0);
        getChildren().add(logoutBtn);  
        
           menuBarOption(chatListBtn);
        
    chatListBtn.setOnMouseClicked(event->{

        menuBarOption(chatListBtn);
    });
    
    logoutBtn.setOnMouseClicked(event->{
        logoutBtn.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    });

    }

    public void menuBarOption(ImageView btn){
        if(chatListBtn == btn){
            chatListBtn.setOpacity(1);
        }else{
            chatListBtn.setOpacity(0.2);
        }
    }
    
}


