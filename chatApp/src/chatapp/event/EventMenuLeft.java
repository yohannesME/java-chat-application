package chatapp.event;


import chatapp.model.Model_User_Account;
import java.util.List;

public interface EventMenuLeft {

    public void newUser(List<Model_User_Account> users);

}
