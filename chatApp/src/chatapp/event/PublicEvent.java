package chatapp.event;




public class PublicEvent {
    
    private static PublicEvent instance;
    private EventChat eventChat;
    private EventAuthentication eventAuth;
    private RegisterEvent registerEvent;
    private EventAlert eventAlert;
    private EventMain eventMain;
    private LoginAlert loginAlert;
    private EventMenuLeft eventMenuLeft;
    private EventInitChat eventInitChat;

    public EventInitChat getEventInitChat() {
        return eventInitChat;
    }

    public void addEventInitChat(EventInitChat eventInitChat) {
        this.eventInitChat = eventInitChat;
    }

    public EventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }

    public void addEventMenuLeft(EventMenuLeft eventMenuLeft) {
        this.eventMenuLeft = eventMenuLeft;
    }

    public LoginAlert getLoginAlert() {
        return loginAlert;
    }

    public void addLoginAlert(LoginAlert loginAlert) {
        this.loginAlert = loginAlert;
    }



    public EventMain getEventMain() {
        return eventMain;
    }

    public void addEventMain(EventMain eventMain) {
        this.eventMain = eventMain;
    }

    public EventAlert getEventAlert() {
        return eventAlert;
    }

    public void addEventAlert(EventAlert eventAlert) {
        this.eventAlert = eventAlert;
    }

    public RegisterEvent getRegisterEvent() {
        return registerEvent;
    }

    public void addRegisterEvent(RegisterEvent registerEvent) {
        this.registerEvent = registerEvent;
    }

    public EventAuthentication getEventAuth() {
        return eventAuth;
    }


    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    public EventChat getEventChat() {
        return eventChat;
    }

    private PublicEvent() {
//        instance=this;
    }

    public void addEventChat(EventChat event) {
        this.eventChat = event;
    }
    
    public void addEventAuth(EventAuthentication eventAuth) {
        this.eventAuth = eventAuth;
    }
}
