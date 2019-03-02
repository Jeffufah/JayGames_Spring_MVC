package com.jaygames_spring_mvc.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 * @author Owner
 */
//@Component
//@Scope("session")
public class User
{
    private String userName;
    private String userPassword;
    
    private String loginStatus;

    public User()
    {
        loginStatus = "You are not logged in.";
    }
    
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getLoginStatus()
    {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus)
    {
        this.loginStatus = loginStatus;
    }
}
