package chatapp.main;


import chatapp.event.LoginAlert;
import chatapp.event.PublicEvent;
import chatapp.model.Model_Login;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Login extends VBox {

    private static Login instance;
    protected final TextField username;
    protected final PasswordField password;
    protected final Pane pane;
    protected final HBox btnContainer;
    protected final Hyperlink switchToRegister;
    protected final Button loginBtn;
    protected final Label lbError;

    public static Login getInstance(){
        if(instance==null)
            return new Login();
        return instance;
    }
    
    
    public Login() {
        instance = this;
        username = new TextField();
        password = new PasswordField();
        pane = new Pane();
        btnContainer = new HBox();
        switchToRegister = new Hyperlink();
        loginBtn = new Button();
        lbError = new Label();


        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(702.0);
        setPrefWidth(1006.0);

       //THE LOGIN PART
        setAlignment(javafx.geometry.Pos.CENTER);
        setPrefHeight(245.0);
        setPrefWidth(606.0);

        username.setId("username");
        username.setMaxHeight(USE_PREF_SIZE);
        username.setMaxWidth(USE_PREF_SIZE);
        username.setPrefHeight(35.0);
        username.setPrefWidth(300.0);
        username.setPromptText("@ Username");
        username.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        VBox.setMargin(username, new Insets(0.0, 0.0, 15.0, 0.0));
        username.setFont(new Font("Ebrima", 16.0));

        password.setId("password");
        password.setMaxHeight(USE_PREF_SIZE);
        password.setMaxWidth(USE_PREF_SIZE);
        password.setPrefHeight(35.0);
        password.setPrefWidth(300.0);
        password.setPromptText("** Password");
        password.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        password.setFont(new Font("Ebrima", 16.0));

        pane.setMaxHeight(USE_PREF_SIZE);
        pane.setMaxWidth(USE_PREF_SIZE);
        pane.setPrefHeight(2.0);
        pane.setPrefWidth(300.0);
        pane.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        pane.setOpaqueInsets(new Insets(0.0));

        btnContainer.setAlignment(javafx.geometry.Pos.CENTER);
        btnContainer.setPrefHeight(60.0);
        btnContainer.setPrefWidth(606.0);

        switchToRegister.setId("switchToSignup");
        switchToRegister.setText("Sign Up");
        switchToRegister.setTextFill(javafx.scene.paint.Color.valueOf("#9da7a7"));
        HBox.setMargin(switchToRegister, new Insets(0.0, 20.0, 0.0, 20.0));

        loginBtn.setId("login");
        loginBtn.setMaxHeight(USE_PREF_SIZE);
        loginBtn.setMaxWidth(USE_PREF_SIZE);
        loginBtn.setMnemonicParsing(false);
        loginBtn.setPrefHeight(40.0);
        loginBtn.setPrefWidth(100.0);
        loginBtn.setStyle("-fx-background-color: #58B8D3;");
        loginBtn.setText("Login");
        loginBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        HBox.setMargin(loginBtn, new Insets(0.0));
        loginBtn.setFont(new Font("Ebrima Bold", 16.0));
        
        lbError.setTextFill(Color.RED);

        //END OF THE LOGIN
        
        getChildren().add(username);
        getChildren().add(password);
        getChildren().add(pane);
        btnContainer.getChildren().add(switchToRegister);
        btnContainer.getChildren().add(loginBtn);
        getChildren().add(btnContainer);
        getChildren().add(lbError);
        
        
        
    
        switchToRegister.setOnMouseClicked((e)->{
            PublicEvent.getInstance().getEventAuth().setRegisterScene(RegisterGUI.getInstance());
        });
        
        loginBtn.setOnAction((event)->{
            String user = username.getText();
            String pass = password.getText();
            if(user.equals("")){
                username.requestFocus();
            }else if(pass.equals("")){
                password.requestFocus();
            }else{
                PublicEvent.getInstance().getRegisterEvent().login(new Model_Login(user, pass));
            }
            
        });
        password.setOnAction((event)->{
            String user = username.getText();
            String pass = password.getText();
            if(user.equals("")){
                username.requestFocus();
            }else if(pass.equals("")){
                password.requestFocus();
            }else{
                PublicEvent.getInstance().getRegisterEvent().login(new Model_Login(user, pass));
            }
            
        });
        
        
        PublicEvent.getInstance().addLoginAlert(new LoginAlert(){
            @Override
            public void callAlert(String alert) {
                lbError.setText(alert);
            }
        });
    
    }
}
