package nl.whitehorses.tornado.beans;

public class TornadoApplicationBean {
    
    private String authenticationId;
    
    public TornadoApplicationBean() {
        super();
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }
}
