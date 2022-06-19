/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.event;

import chatapp.main.Login;
import chatapp.main.RegisterGUI;

/**
 *
 * @author Yohannes
 */
public interface EventAuthentication {
    public void setRegisterScene(RegisterGUI r);
    public void setLoginScene(Login l); 
}
