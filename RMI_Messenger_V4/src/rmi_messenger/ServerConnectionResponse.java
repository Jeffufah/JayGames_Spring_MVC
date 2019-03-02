/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_messenger;

import java.io.Serializable;

/**
 *
 * @author Jeffrey McMullen II
 */
public class ServerConnectionResponse implements Serializable
{
    private boolean isConnected;
    private String response;
    private int messageCount;
    private String[] clients;
    
    public ServerConnectionResponse(){}
    
    public ServerConnectionResponse(boolean isConnected, String response, int messageCount, String[] clients)
    {
        this.isConnected = isConnected;
        this.response = response;
        this.messageCount = messageCount;
        this.clients = clients;
    }

    public boolean getIsConnected()
    {
        return isConnected;
    }

    public void setIsConnected(boolean isConnected)
    {
        this.isConnected = isConnected;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public int getMessageCount()
    {
        return messageCount;
    }

    public void setMessageCount(int messageCount)
    {
        this.messageCount = messageCount;
    }

    public String[] getClients()
    {
        return clients;
    }

    public void setClients(String[] clients)
    {
        this.clients = clients;
    }
    
    
}
