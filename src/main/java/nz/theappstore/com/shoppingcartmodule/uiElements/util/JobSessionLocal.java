package nz.theappstore.com.shoppingcartmodule.uiElements.util;

public class JobSessionLocal {

    private int sessionId;

    public JobSessionLocal(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}
