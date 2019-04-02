package com.JayGames.Network_Application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * This class is used by the MessengerServer's performHeartbeat method to ensure
 * that stale ClientConnections are accounted for. Clients that poll the 
 * MessengerServer trigger the server to use the corresponding ClientConnection's
 * setLastClientDate method with the current date. When the server performs a
 * heartbeat, it will use the timeoutCheck method to ensure that the client
 * has polled within the allotted time. Otherwise, the client will be removed
 * from the ClientConnections hashmap. A new addition has been made to track
 * the clientConnections player number and a new method has been created to
 * have this number decremented when players leave the lobby.
 */
public class ClientConnection implements Serializable
{
    private final DateFormat dateFormat;
    
    private Date lastClientDate;
    
    private int playerNumber;
    
    /**
     * Constructs the class by setting up a dateFormat for comparing timestamps with
     * and setting lastClientDate to the current date.
     */
    public ClientConnection()
    {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        lastClientDate = new Date();
    }
    
    /**
     * Determines if the client is still connected to the server within provided
     * leeway time in seconds.
     * 
     * @param timeoutThreshold An integer representing the amount of allowed
     * seconds for there to be since the client's last interaction with the server.
     * @param currentDate The current date to be subtracted from with lastClientDate
     * to determine the difference.
     * @return A boolean of true for still connected or false for timeout.
     */
    public boolean timeoutCheck(int timeoutThreshold, Date currentDate)
    {
        long diff = DateExpressions.getDateDiff(lastClientDate, currentDate, TimeUnit.SECONDS);
        return diff < timeoutThreshold;
    }
    
    /**
     * Lowers this ClientConnection playerNumber value by 1 if it is greater
     * than the low limit passed. This method will be called when a player
     * disconnects.
     * 
     * @param disconnectingPlayerNumber An integer containing the playerNumber 
     * of the connection that has been removed from the server.
     */
    public void decrementPlayerNumber(int disconnectingPlayerNumber)
    {
        if (playerNumber > disconnectingPlayerNumber)
        {
            playerNumber--;
        }
    }
    
    /**
     * Gets the last time the client interacted with the server.
     *
     * @return A Date object containing the last interaction date the server had
     * from this ClientConnection.
     */
    public Date getLastClientDate()
    {
        return lastClientDate;
    }

    /**
     * Sets the lastClientDate field to the value of the Date object provided.
     *
     * @param lastClientDate A Date object containing date information.
     */
    public void setLastClientDate(Date lastClientDate)
    {
        this.lastClientDate = lastClientDate;
    }

    /**
     * Gets the current number assigned to this clientConnection.
     * 
     * @return An integer containing the clientConnection's player number.
     */
    public int getPlayerNumber()
    {
        return playerNumber;
    }

    /**
     * Sets the playerNumber to the value specified.
     * 
     * @param playerNumber An integer containing the number to be assigned
     * to this clientConnection's playerNumber.
     */
    public void setPlayerNumber(int playerNumber)
    {
        this.playerNumber = playerNumber;
    }
}
