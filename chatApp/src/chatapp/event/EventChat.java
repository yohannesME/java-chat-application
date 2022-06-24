/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.event;

import chatapp.model.Model_Receive_Message;
import chatapp.model.Model_Send_Message;
import chatapp.model.Model_User_Account;
import java.util.List;

/**
 *
 * @author Yohannes
 */
public interface EventChat {

    public void sendMessage(String message);

    public void receiveMessage(Model_Receive_Message message);
    
    public void selectChat(Model_User_Account user);
    
    public void loadMessage(List<Model_Send_Message> user);
}
