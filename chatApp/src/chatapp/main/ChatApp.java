package chatapp.main;

import chatapp.event.EventMain;
import chatapp.event.PublicEvent;
import chatapp.service.Service;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class ChatApp extends Application {
    

    AuthenticationGUI root;
    
    @Override
    public void start(Stage stage) throws Exception {
        root = new AuthenticationGUI();

        Service.getInstance().startServer();
        
        Scene scene = new Scene(root);
        
        PublicEvent.getInstance().addEventMain(new EventMain(){
            @Override
            public void initChat() {
                scene.setRoot(MainChatGUI.getInstance());
                Service.getInstance().getClient().emit("list_user", Service.getInstance().getUser().getUserID());
            }
        });
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        
    }

    public static void main(String[] args) {
        launch(args);
   
    }
    
}
