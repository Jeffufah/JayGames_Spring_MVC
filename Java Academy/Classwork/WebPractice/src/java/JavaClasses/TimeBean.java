/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClasses;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Jeffrey McMullen II
 */
public class TimeBean 
{

    private Locale[] allLocale = Locale.getAvailableLocales();
    private String[] allTimeZone = TimeZone.getAvailableIDs();
    private int localeIndex;
    private int timeZoneIndex;

    /**
     *
     */
    public TimeBean() 
    {
        Arrays.sort(allTimeZone);
    }

    /**
     *
     * @return
     */
    public Locale[] getAllLocale() 
    {
        return allLocale;
    }

    /**
     *
     * @return
     */
    public String[] getAllTimeZone() 
    {
        return allTimeZone;
    }

    /**
     *
     * @return
     */
    public int getLocaleIndex() 
    {
        return localeIndex;
    }

    /**
     *
     * @return
     */
    public int getTimeZoneIndex() 
    {
        return timeZoneIndex;
    }

    /**
     *
     * @param index
     */
    public void setLocaleIndex(int index) 
    {
        localeIndex = index;
    }

    /**
     *
     * @param index
     */
    public void setTimeZoneIndex(int index) 
    {
        timeZoneIndex = index;
    }

    /**
     * Return a string for the current time with the specified locale and time
     * zone
     * @param localeIndex
     * @param timeZoneIndex
     * @return 
     */
    public String currentTimeString(
            int localeIndex, int timeZoneIndex) 
    {
        Calendar calendar
                = new GregorianCalendar(allLocale[localeIndex]);
        TimeZone timeZone
                = TimeZone.getTimeZone(allTimeZone[timeZoneIndex]);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(
                DateFormat.FULL, DateFormat.FULL, allLocale[localeIndex]);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(calendar.getTime());
    }
}
