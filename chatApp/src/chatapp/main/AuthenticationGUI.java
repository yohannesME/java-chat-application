/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.main;

import chatapp.event.EventAlert;
import chatapp.event.EventAuthentication;
import chatapp.event.PublicEvent;
import chatapp.model.Model_Alert;
import chatapp.model.Model_User_Account;
import chatapp.service.Service;
import io.socket.client.Ack;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.VBox;
import chatapp.model.Model_Login;
import chatapp.event.RegisterEvent;
import chatapp.main.RegisterGUI;

/**
 *
 * @author Yohannes
 */
public class AuthenticationGUI extends BorderPane{
    private static AuthenticationGUI instance;
    protected final VBox vBox;
    protected final HBox hBox;
    protected final ImageView logo;
    protected final VBox vBox1;
    protected final VBox vBox2;
    protected final HBox hBox1;
    protected final HBox hBox2;
    private final Login loginScene;

    
    
    public static AuthenticationGUI getInstance(){
        if(instance == null)
            return new AuthenticationGUI();
        return instance;
    }
    
    public AuthenticationGUI(){
        vBox = new VBox();
        hBox = new HBox();
        logo = new ImageView();
        vBox1 = new VBox();
        vBox2 = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        loginScene = new Login();

        
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(702.0);
        setPrefWidth(1006.0);

        BorderPane.setAlignment(vBox, javafx.geometry.Pos.CENTER);
        vBox.setPrefHeight(200.0);
        vBox.setPrefWidth(100.0);
        vBox.setStyle("-fx-background-color: #262930;");

        hBox.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        hBox.setPrefHeight(197.0);
        hBox.setPrefWidth(606.0);

        logo.setFitHeight(72.0);
        logo.setFitWidth(215.0);
        logo.setPickOnBounds(true);
        logo.setPreserveRatio(true);
        logo.setImage(new Image(getClass().getResource("/images/yefetene.png").toExternalForm()));

        BorderPane.setAlignment(vBox1, javafx.geometry.Pos.CENTER);
        vBox1.setPrefHeight(502.0);
        vBox1.setPrefWidth(200.0);
        vBox1.setStyle("-fx-background-color: #262930;");
        setLeft(vBox1);

        BorderPane.setAlignment(vBox2, javafx.geometry.Pos.CENTER);
        vBox2.setPrefHeight(200.0);
        vBox2.setPrefWidth(200.0);
        vBox2.setStyle("-fx-background-color: #262930;");
        setRight(vBox2);

        BorderPane.setAlignment(hBox1, javafx.geometry.Pos.CENTER);
        hBox1.setPrefHeight(50.0);
        hBox1.setPrefWidth(200.0);
        hBox1.setStyle("-fx-background-color: #262930;");
        setTop(hBox1);

        BorderPane.setAlignment(hBox2, javafx.geometry.Pos.CENTER);
        hBox2.setPrefHeight(50.0);
        hBox2.setPrefWidth(200.0);
        hBox2.setStyle("-fx-background-color: #262930;");

        setBottom(hBox2);
        
        setCenter(vBox);

        hBox.getChildren().add(logo);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(loginScene);
        

        
        PublicEvent.getInstance().addEventAuth(new EventAuthentication(){
            @Override
            public void setRegisterScene(RegisterGUI r) {
                vBox.getChildren().remove(Login.getInstance());
                vBox.getChildren().add(RegisterGUI.getInstance());                
            }

            @Override
            public void setLoginScene(Login l) {
                vBox.getChildren().add(Login.getInstance());
                vBox.getChildren().remove(RegisterGUI.getInstance());
            }
        });
        
        PublicEvent.getInstance().addRegisterEvent(new RegisterEvent(){
            @Override
            public void register(Model_User_Account data, EventAlert event) {

                Service.getInstance().getClient().emit("register", data.toJsonObject(), new Ack() {
                    @Override
                    public void call(Object... os) {
                        
                        if (os.length > 0) {
                            Model_Alert ms = new Model_Alert((boolean) os[0], os[1].toString());
                            if (ms.isAction()) {

                                Model_User_Account user = new Model_User_Account(os[2]);
                                Service.getInstance().setUser(user);
                            }

                            Platform.runLater(new Runnable(){
                                @Override
                                public void run() {
                                    event.callAlert(ms);
                                }
                            });
                        }
                                   
            }
                });                
            }

            @Override
            public void login(Model_Login data) {
                 new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("login", data.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if (os.length > 0) {
                                    boolean action = (Boolean) os[0];
                                    if (action) {
                                        Service.getInstance().setUser(new Model_User_Account(os[1]));
                                        PublicEvent.getInstance().getEventMain().initChat();
                                    } else {
                                        //  password wrong
                                        Platform.runLater(new Runnable(){
                                            @Override
                                            public void run() {
                                                PublicEvent.getInstance().getLoginAlert().callAlert("Incorrect Username or Password.");
                                            }
                                        });
                                    }
                                }
                            }
                        });

                    }
                }).start();
            }
        });
      

    }


    
}
