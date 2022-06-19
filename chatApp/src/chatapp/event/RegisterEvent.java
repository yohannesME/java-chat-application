/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.event;

import chatapp.model.Model_Login;
import chatapp.model.Model_User_Account;

/**
 *
 * @author Yohannes
 */
public interface RegisterEvent {
    
    public void register(Model_User_Account data, EventAlert event);
    
    public void login(Model_Login data);
    
}
