package chatapp.main;

import chatapp.event.EventAlert;
import chatapp.event.PublicEvent;
import chatapp.model.Model_Alert;
import chatapp.model.Model_User_Account;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RegisterGUI extends VBox {
    private static RegisterGUI instance;
    protected final VBox vBox0;
    protected final TextField name;
    protected final TextField userName;
    protected final Pane pane;
    protected final TextField email;
    protected final PasswordField password;
    protected final PasswordField comfirmPassword;
    protected final Pane pane0;
    protected final Button registerBtn;
    protected final HBox hb_container;
    protected final Hyperlink switchToLogin;
    protected final Label lbError;

    public static RegisterGUI getInstance(){
        if(instance==null)
            return new RegisterGUI();
        return instance;
    }

    public RegisterGUI() {
        instance = this;
        vBox0 = new VBox();
        name = new TextField();
        userName = new TextField();
        pane = new Pane();
        email = new TextField();
        password = new PasswordField();
        comfirmPassword = new PasswordField();
        pane0 = new Pane();
        registerBtn = new Button();
        hb_container = new HBox();
        switchToLogin = new Hyperlink();
        lbError = new Label();
        

        
        //THE REGISTRATION PART
        
        setAlignment(javafx.geometry.Pos.CENTER);
        setId("registerFormContainer");
        setPrefHeight(474.0);
        setPrefWidth(606.0);

        name.setId("Name");
        name.setMaxHeight(USE_PREF_SIZE);
        name.setMaxWidth(USE_PREF_SIZE);
        name.setPrefHeight(35.0);
        name.setPrefWidth(300.0);
        name.setPromptText("Name");
        name.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        VBox.setMargin(name, new Insets(0.0, 0.0, 15.0, 0.0));
        name.setFont(new Font("Ebrima", 16.0));

        userName.setId("Username");
        userName.setMaxHeight(USE_PREF_SIZE);
        userName.setMaxWidth(USE_PREF_SIZE);
        userName.setPrefHeight(35.0);
        userName.setPrefWidth(300.0);
        userName.setPromptText("Username");
        userName.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        userName.setFont(new Font("Ebrima", 16.0));
        VBox.setMargin(userName, new Insets(0.0, 0.0, 15.0, 0.0));

        pane.setMaxHeight(USE_PREF_SIZE);
        pane.setMaxWidth(USE_PREF_SIZE);
        pane.setPrefHeight(2.0);
        pane.setPrefWidth(300.0);
        pane.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        pane.setOpaqueInsets(new Insets(0.0));

        email.setId("email");
        email.setMaxHeight(USE_PREF_SIZE);
        email.setMaxWidth(USE_PREF_SIZE);
        email.setPrefHeight(35.0);
        email.setPrefWidth(300.0);
        email.setPromptText("Email");
        email.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        email.setFont(new Font("Ebrima", 16.0));
        VBox.setMargin(email, new Insets(0.0, 0.0, 15.0, 0.0));

        password.setId("password");
        password.setMaxHeight(USE_PREF_SIZE);
        password.setMaxWidth(USE_PREF_SIZE);
        password.setPrefHeight(35.0);
        password.setPrefWidth(300.0);
        password.setPromptText("Password");
        password.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        password.setFont(new Font("Ebrima", 16.0));
        VBox.setMargin(password, new Insets(0.0, 0.0, 15.0, 0.0));

        comfirmPassword.setId("comfirm-password");
        comfirmPassword.setMaxHeight(USE_PREF_SIZE);
        comfirmPassword.setMaxWidth(USE_PREF_SIZE);
        comfirmPassword.setPrefHeight(35.0);
        comfirmPassword.setPrefWidth(300.0);
        
        comfirmPassword.setPromptText("Confirm Password");
        comfirmPassword.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        comfirmPassword.setFont(new Font("Ebrima", 16.0));
        VBox.setMargin(comfirmPassword, new Insets(0.0, 0.0, 15.0, 0.0));

        pane0.setMaxHeight(USE_PREF_SIZE);
        pane0.setMaxWidth(USE_PREF_SIZE);
        pane0.setPrefHeight(2.0);
        pane0.setPrefWidth(310.0);
        pane0.setStyle("-fx-background-color: #363A44; -fx-text-inner-color: #fafafa;");
        pane0.setOpaqueInsets(new Insets(0.0));

        registerBtn.setMaxHeight(USE_PREF_SIZE);
        registerBtn.setMaxWidth(USE_PREF_SIZE);
        registerBtn.setMnemonicParsing(false);
        registerBtn.setPrefHeight(40.0);
        registerBtn.setPrefWidth(100.0);
        registerBtn.setStyle("-fx-background-color: #58B8D3;");
        registerBtn.setText("Sign Up");
        registerBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        registerBtn.setFont(new Font("Ebrima Bold", 16.0));
        VBox.setMargin(registerBtn, new Insets(15.0, 0.0, 0.0, 0.0));

        hb_container.setAlignment(javafx.geometry.Pos.CENTER);
        hb_container.setPrefHeight(60.0);
        hb_container.setPrefWidth(606.0);

        switchToLogin.setId("switchToLogin");
        switchToLogin.setText("Have an account? Login");
        switchToLogin.setTextFill(javafx.scene.paint.Color.valueOf("#9da7a7"));
        
        lbError.setTextFill(Color.RED);

        //END OF REGISTRATION PART OF THE GUI
       
        getChildren().add(name);
        getChildren().add(userName);
        getChildren().add(pane);
        getChildren().add(email);
        getChildren().add(password);
        getChildren().add(comfirmPassword);
        getChildren().add(pane0);
        getChildren().add(registerBtn);
        hb_container.getChildren().add(switchToLogin);
        getChildren().add(hb_container);
        getChildren().add(lbError);
        
        
        switchToLogin.setOnMouseClicked((e)->{
            PublicEvent.getInstance().getEventAuth().setLoginScene(Login.getInstance());
        });


        registerBtn.setOnAction((event)->{
        //GATHER INFORMATION FROM TEXT FIELD AND SEND TO SERVER
        String username = userName.getText().trim();
        String Name = name.getText();
        String Email = email.getText();
        String pass = password.getText();
        String confirmpassword = comfirmPassword.getText();
        
        if (username.equals("")) {
            userName.requestFocus();
            lbError.setText("Please Insert Your UserName");
        } 
        if (Name.equals("")) {
            name.requestFocus();
            lbError.setText("Please Insert Your Name");
        } 
        else {
            Pattern namePattern = Pattern.compile("[a-zA-Z]*[ ][a-zA-Z]*");
            Matcher nameMatch = namePattern.matcher(name.getText());
            if (!nameMatch.matches()){
                name.requestFocus();
                lbError.setText("There Must be a space Between FirstName and LastName");
            }
        }
        
        if (Email.equals("")) {
            email.requestFocus();
            lbError.setText("Email Is Required");
        }
        else {
            Pattern emailPattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-z]+)+");
            Matcher emailMatch = emailPattern.matcher(email.getText());
            if(!(emailMatch.find() && emailMatch.group().equals(email.getText()))){
            email.requestFocus();
            lbError.setText("Invalid Email Format");
            }
        }
        
        if (pass.equals("")) {
            password.requestFocus();
            lbError.setText("Password Is Required");            
        } else if (!pass.equals(confirmpassword)) {
                comfirmPassword.requestFocus();
                lbError.setText("Password Does Not Match");
        } else {

            if (password.getText().length() < 8){
                lbError.setText("Password Must have a Length Of 8 And a Combination Of Digits, LowerCase, UpperCase and Special Characters");
            }
            Model_User_Account data = new Model_User_Account(username, pass, Name, Email);
            PublicEvent.getInstance().getRegisterEvent().register(data, new EventAlert() {
                @Override
                public void callAlert(Model_Alert alert) {
                    if (!alert.isAction()) {
                        lbError.setText(alert.getMessage());
                    } else {
                        PublicEvent.getInstance().getEventAuth().setLoginScene(Login.getInstance());
                    }
                }
            });
        }            
            });
    
    }
}
