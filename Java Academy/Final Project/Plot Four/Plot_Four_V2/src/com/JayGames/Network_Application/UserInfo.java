/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JayGames.Network_Application;

/**
 * Course ID: EYF-649 
 * Date: 2019/04/01
 * @author Jeffrey McMullen II
 * 
 * Create a class to store the userID, username, and password of the end user.
 */
public class UserInfo
{
    private final int userID;
    private final String username;
    private final String password;
    
    /**
     * Constructs this class by requiring the end user's id, username, and password.
     * 
     * @param userID An integer containing the id of the user.
     * @param username A String containing the username of the user.
     * @param password A String containing the password of the user.
     */
    public UserInfo(int userID, String username, String password)
    {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the user's id.
     * 
     * @return An integer containing the user's id. 
     */
    public int getUserID()
    {
        return userID;
    }

    /**
     * Gets the user's username.
     * 
     * @return A String containing the user's username. 
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Gets the user's password.
     * 
     * @return A String containing the user's password. 
     */
    public String getPassword()
    {
        return password;
    }
}
