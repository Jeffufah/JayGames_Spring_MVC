/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaygames_jsf.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.sql.*;
import java.io.Serializable;
import javax.annotation.PreDestroy;

/**
 *
 * @author Jeffrey McMullen II
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable
{
    private int userID;
    private String username;
    private String password;
    private String sessionUser;
    private String loginStatus;
    private String redirectPage;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean()
    {
        userID = 0;
        loginStatus = "You are not logged on.";
        sessionUser = "";
    }



    /**
     *
     * @return @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public String queryLoginDB() throws SQLException, ClassNotFoundException 
    {

        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connect to the database
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/jaygame2_dtccjaygames", "root", "");
        
        PreparedStatement loginStatement = connection.prepareStatement(
                "SELECT User_id FROM users");

        ResultSet rset = loginStatement.executeQuery("SELECT `User_id` FROM `users` "
                + "WHERE `Username` = " + "\"" + username + "\" AND `Password` = " + "\"" + password + "\"");

        if (!rset.isBeforeFirst()) 
        {
            loginStatus = "Login failed.";
            redirectPage = "Login";
            connection.close();
        } 
        else 
        {
            rset.next();
            userID = rset.getInt(1);
            sessionUser = username;
            loginStatus = "Logged in as: " + sessionUser;
            redirectPage = "index";
            connection.close();
            queryLogEventDB(userID, 1);
            
        }
        
        return "LoginStatus";
    }

    private void queryLogEventDB(int userId, int loggingIn) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connect to the database
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/jaygame2_dtccjaygames", "root", "");
        
        PreparedStatement loginStatement = connection.prepareStatement(
                    "insert into log_events (LE_user_id,"
                    + " LE_device_id, Log_type) values (?, ?, ?)");
        
        /*INSERT INTO `log_events`(`LE_user_id`, `LE_device_id`, `Log_type`) VALUES (1,1,1)*/
        loginStatement.executeUpdate("INSERT INTO `log_events`(`LE_user_id`, `LE_device_id`, `Log_type`) VALUES "
                + "(\"" + userId + "\", " + "\"" + "1" + "\", " + "\"" + loggingIn + "\")");
    }
    
    @PreDestroy
    public void sessionDestroyed() throws ClassNotFoundException, SQLException 
    {
        if (userID != 0)
        {
            queryLogEventDB(userID, 0);
        }
    }

    /**
     * 
     * @return 
     */
    public int getUserID() 
    {
        return userID;
    }

    /**
     * 
     * @param userID 
     */
    public void setUserID(int userID) 
    {
        this.userID = userID;
    }
  
    /**
     *
     * @return
     */
    public String getUsername() 
    {
        return this.username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) 
    {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() 
    {
        return this.password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }

    /**
     *
     * @param loginStatus
     */
    public void setLoginStatus(String loginStatus) 
    {
        this.loginStatus = loginStatus;
    }

    /**
     *
     * @return
     */
    public String getLoginStatus() 
    {
        return loginStatus;
    }

    /**
     *
     * @return
     */
    public String getSessionUser() 
    {
        return this.sessionUser;
    }

    /**
     *
     * @param sessionUser
     */
    public void setSessionUser(String sessionUser) 
    {
        this.sessionUser = sessionUser;
    }
    
    /**
     * 
     * @return 
     */
    public String getRedirectPage() 
    {
        return redirectPage;
    }

    /**
     * 
     * @param redirectPage 
     */
    public void setRedirectPage(String redirectPage) 
    {
        this.redirectPage = redirectPage;
    }
}
