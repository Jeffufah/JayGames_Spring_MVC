package rmi_messenger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 * 
 * This class is used to get the time difference between two dates.
 */
public class DateExpressions
{
    /**
     * Gets the difference of the two dates provided.
     *
     * @param oldDate A Date object that is to be an older date than the new date
     * provided.
     * @param newDate A Date object that is to be a newer date than the old date
     * provided.
     * @param timeUnit A TimeUnit object to convert the time difference into
     * milliseconds.
     * @return The difference value in milliseconds.
     */
    public static long getDateDiff(Date oldDate, Date newDate, TimeUnit timeUnit)
    {
        long dateDifference = newDate.getTime() - oldDate.getTime();
        return timeUnit.convert(dateDifference, TimeUnit.MILLISECONDS);
    }
}
