package chatapp.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class Model_Send_Message {

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_Send_Message(int fromUserID, int toUserID, String text) {
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.text = text;
    }

    public Model_Send_Message() {
    }
    public Model_Send_Message(Object o) {
        try {
            JSONObject obj = (JSONObject) o;
            fromUserID = obj.getInt("fromUserID");
            toUserID = obj.getInt("toUserID");
            text = obj.getString("text");
        } catch (JSONException ex) {
            Logger.getLogger(Model_Send_Message.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int fromUserID;
    private int toUserID;
    private String text;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fromUserID", fromUserID);
            json.put("toUserID", toUserID);
            json.put("text", text);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
