/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JayGames.Network_Application;

/**
 * Course ID: EYF-649 
 * Date: 2019/04/02
 * @author Jeffrey McMullen II
 * 
 * This class stores a particular lobby hostname, ipaddress, and port.
 */
public class LobbyInfo
{
    private final String hostname;
    private final String ipAddress;
    private final String port;
    
    /**
     * Constructs this class by requiring the lobby owner's host name, ip address
     * and port number.
     * @param hostname A String containing the username of the lobby host.
     * @param ipAddress A String containing the ip address of the lobby host.
     * @param port  A String containing the port number of the lobby host.
     */
    public LobbyInfo(String hostname, String ipAddress, String port)
    {
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Gets the lobby owner's host name.
     * 
     * @return A String containing the host name of the lobby owner.
     */
    public String getHostname()
    {
        return hostname;
    }

    /**
     * Gets the lobby owner's ip address.
     * 
     * @return A String containing the ip address of the lobby owner. 
     */
    public String getIpAddress()
    {
        return ipAddress;
    }

    /**
     * Gets the lobby owner's port number.
     * 
     * @return A String containing the port number of the lobby owner. 
     */
    public String getPort()
    {
        return port;
    }
    
    /**
     * Concatenates the class fields together and returns them as a String.
     * 
     * @return A String containing the field values with delimiter padding
     * in the following combination: ~`!
     */
    @Override
    public String toString()
    {
        return hostname + "~`!" + ipAddress + "~`!" + port;
    }
}
