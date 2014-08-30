package org.dispatcher.beans;

import org.dispatcher.constants.Status;
import org.dispatcher.constants.UserType;

/**
 * Created by KrishnaTeja on 18/08/14.
 */
public class LoginResponseBean {

    Status status;
    UserType userType;

    public LoginResponseBean(Status status){
        this.status = status;
    }

    public LoginResponseBean(Status status, UserType userType){
        this.status = status;
        this.userType = userType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
