package nz.theappstore.com.shoppingcartmodule.persistence;

import java.security.Principal;

import javax.security.auth.Subject;

/**
 * Created by vyomkeshjha on 9/12/17.
 */

public class UserEntity implements Principal {
    private int userId;

    private String userName;

    private String passcode;

    private int accessLevel;

    public UserEntity(int userId, String userName, String passcode, int accessLevel) {
        this.userId = userId;
        this.userName = userName;
        this.passcode = passcode;
        this.accessLevel = accessLevel;
    }

    public UserEntity(String userName, String passcode, int accessLevel) {
        this.userName = userName;
        this.passcode = passcode;
        this.accessLevel = accessLevel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return userName;
    }

    public boolean implies(Subject subject) {
        return (subject.getPrincipals().contains(this));
    }
}
