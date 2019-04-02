package com.JayGames.Webserver_Access;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Make this more easy to read.
 * @author Owner
 */
public class ConnectionFactory 
{
    private double API_VERSION;
    private String API = "";

    private String METHOD = "POST";
    private String TYPE = "application/x-www-form-urlencoded";
    private String USER_AGENT = "Mozilla/5.0";
    private String data = "";
    private URL connection;
    private HttpURLConnection urlConnection;
    
    
    private final HashMap<String, String> fields = new HashMap();
    
    public ConnectionFactory(String[] fieldStrings, String url, double version)
    {
        this.API_VERSION = version;
        this.API = url;
        fields.put("version", String.valueOf(version));
        for (int i = 0; i < fieldStrings.length; i++)
        {
            String[] fieldPoints = fieldStrings[i].split(";");
            
            for(int f = 0; f < fieldPoints.length; f++)
            {
                fields.put(fieldPoints[f].split(":")[0], fieldPoints[f].split(":")[1]);
            }
        }
    }
    
    public String buildConnection()
    {
        StringBuilder content = new StringBuilder();
        if(!this.getEndpoints().equalsIgnoreCase("") && !this.getEndpoints().isEmpty())
        {         
            String vars = "";
            String vals = "";
            
            try
            {
                for(Map.Entry<String, String> entry : fields.entrySet())
                {
                    vars = entry.getKey();
                    vals = entry.getValue();
                    data += ("&" + vars + "=" + vals);
                }
                
                if(data.startsWith("&"))
                {
                    data = data.replaceFirst("&", "");
                }
                
                connection = new URL(API);
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(readWithAccess(connection, data)));
                String line;
                while((line = reader.readLine()) != null)
                {
                    content.append(line + "\n");
                }
                reader.close();
                
                return content.toString();
            }
            catch(Exception e)
            {
                System.out.println("Connection builder failed.");
                System.err.println(e.fillInStackTrace());
            }
        }
        else
        {
            return null;
        }
        return null;
    }
    
    private InputStream readWithAccess(URL url, String data)
    {
        try
        {
            byte[] out = data.toString().getBytes();
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setReadTimeout(1000); //Look at this later.
            urlConnection.setRequestMethod(METHOD);
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty("User-Agent", USER_AGENT);
            urlConnection.addRequestProperty("Content-Type", TYPE);
            urlConnection.connect();
            
            try
            {
             OutputStream os = urlConnection.getOutputStream(); 
             os.write(out);
            }
            catch(Exception e)
            {
                System.out.println("Inner try failed.");
                System.err.println(e.getMessage());
            }
            
            return urlConnection.getInputStream();
        }
        catch(Exception e)
        {
            System.out.println("Outer try failed.");
            System.err.println(e.fillInStackTrace());
            return null;
        }
    }
    
    public String getApiVersion()
    {
        return String.valueOf(API_VERSION);
    }
    
    public String getEndpoints()
    {
        return fields.toString();
    }
    
    public String getEndpointValue(String key)
    {
        return fields.get(key);
    }
    
    public void setUserAgent(String userAgent)
    {
        this.USER_AGENT = userAgent;
    }
    
    public void setMethod(String method)
    {
        this.METHOD = method;
    }
    
    public void setSubmissionType(String type)
    {
        this.TYPE = type;
    }
}