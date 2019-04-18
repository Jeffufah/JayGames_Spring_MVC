/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 * 
 * This is a helper class with two static methods for acquiring either an
 * external or local ip address for the MessengerServer.
 */
public class IPAddressFetcher
{
    /**
     * Utilizes http://bot.whatismyipaddress.com to acquire the external ip
     * address by which this server is to be reached.
     *
     * @return A String containing the external ip address to reach this server
     * with.
     * @throws java.net.UnknownHostException
     */
    public static String acquireExternalIPAddress() throws java.net.UnknownHostException
    {
        // Find public IP address 
        String externalIPAddress = "";

        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads external IPAddress 
            externalIPAddress = sc.readLine().trim();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        return externalIPAddress;
    }

    /**
     * Utilizes InetAddress class to acquire the IPV4 address of the machine
     * this server is running on.
     *
     * @return A String containing the IPV4 address to reach this server with
     * over a local area network.
     * @throws java.net.UnknownHostException
     */
    public static String acquireLocalIPAddress() throws java.net.UnknownHostException
    {
        return InetAddress.getLocalHost().getHostAddress().trim();
    }
}
