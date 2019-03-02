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
public class ClientMessage implements Serializable
{
    private String name;
    private String message;
    
    public ClientMessage(){}
    
    public ClientMessage(String name, String message)
    {
        this.name = name;
        this.message = message;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
