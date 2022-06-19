package chatappserver.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_User_Account {


    private String userName;
    private String password;
    private String name;
    private String email;
    private int userID;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("userName", userName);
            json.put("password", password);
            json.put("name", name);
            json.put("email", email);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
        
    public Model_User_Account(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            userID = obj.getInt("userID");
            userName = obj.getString("userName");
            email = obj.getString("email");
            name = obj.getString("name");
            password = obj.getString("password");
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
        

    public Model_User_Account(String userName, String password, String name, String email) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
    }
    


    public Model_User_Account() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
