/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatappserver.model;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yohannes
 */
public class Model_Saved_Message {
    String text;
    boolean isSend;

    public Model_Saved_Message() {
    }

    public Model_Saved_Message(String text, boolean isSend) {
        this.text = text;
        this.isSend = isSend;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIsSend() {
        return isSend;
    }

    public void setIsSend(boolean isSend) {
        this.isSend = isSend;
    }
    
    
    
    public static JSONObject toJsonObject(ArrayList<Model_Saved_Message> msg) {
        try {
            JSONObject json = new JSONObject();
            json.put("message", msg);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
    public Model_Saved_Message(Object msg) {
        try {
            JSONObject json = (JSONObject) msg;
            ArrayList<Model_Saved_Message> message = (ArrayList) json.get("message");

        } catch (JSONException e) {
        }
    }
    
    
    
    
    
}
