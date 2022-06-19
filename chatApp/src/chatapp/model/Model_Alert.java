package chatapp.model;

public class Model_Alert {

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Model_Alert(boolean action, String message) {
        this.action = action;
        this.message = message;
    }

    public Model_Alert() {
    }

    private boolean action;
    private String message;
}
