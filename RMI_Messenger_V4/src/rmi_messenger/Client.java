package rmi_messenger;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Jeffrey McMullen II
 */
public class Client implements Serializable
{
    DateFormat dateFormat;
    
    private Date lastClientDate;
    
    public Client()
    {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        lastClientDate = new Date();
    }

    public Date getLastClientDate()
    {
        return lastClientDate;
    }

    public void setLastClientDate(Date lastClientDate)
    {
        this.lastClientDate = lastClientDate;
    }
    
    /**
     * Determines if the client is still connected to the server within provided
     * leeway time in seconds.
     * @param timeoutThreshold An integer representing the amount of allowed
     * seconds for there to be since the client's last interaction with the server.
     * @return A boolean of true for still connected or false for timeout.
     */
    public boolean timeoutCheck(int timeoutThreshold, Date currentDate)
    {
        long diff = getDateDiff(lastClientDate, currentDate, TimeUnit.SECONDS);
        
        return diff < timeoutThreshold;
    }
    
    /**
     * Get a diff between two dates
     *
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit)
    {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
