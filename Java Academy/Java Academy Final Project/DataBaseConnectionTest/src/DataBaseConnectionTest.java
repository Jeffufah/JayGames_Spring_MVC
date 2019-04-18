import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

class DataBaseConnectionTest
{
    public static void main(String args[]) throws MalformedURLException, ProtocolException, IOException 
    {
        //URL myUrl = new URL("http://jaygames.x10host.com/home/jaygame2/public_html/scripts/download.php");
        //URL myUrl = new URL("http://23.254.203.100:80/");
        //connectToLocalDB();
        //connectToCloudDB();
        //openWebpage(myUrl);
    }
    
    public static void connectToLocalDB()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/jaygame2_dtccjaygames", "root", "");
            //here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) 
            {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " 
                        + rs.getString(3) + "  " + rs.getString(4) + "  " 
                        + rs.getString(5));
            }
            con.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    
    public static void connectToCloudDB()
    {
        double version = 0.1;
        //String url = "http://jaygames.x10host.com/home/jaygame2/public_html/scripts/get_users.php";
        String url = "http://jaygames.x10host.com/home/jaygame2/public_html/scripts/admin_sql_command.php";
        //String url = "http://jaygames.x10host.com/home/jaygame2/public_html/scripts/download.php";
        //String url = "http://jaygames.x10host.com/home/jaygame2/public_html/scripts/login.php";
        
        String sql1 = "SELECT Column_Name "
                + "FROM INFORMATION_SCHEMA.COLUMNS "
                + "WHERE TABLE_SCHEMA = 'jaygame2_dtccjaygames' "
                + "AND TABLE_NAME = 'users'";
        
        Scanner input = new Scanner(System.in);
        
        do
        {
            System.out.println("Enter query (0 to quit): ");
            String sql2 = input.nextLine();
                    
            if (sql2.equals("0"))
            {
                break;
            }
        
            String[] fields = 
            {
              "adminSQL" + ":" + sql2 + ";",
            };

            ConnectionFactory connection = new ConnectionFactory(fields, url, version);

            String result = connection.buildConnection();        

            
            if (result.contains("No results from adminSQL query."))
            {
                System.out.println(result);
            }
            else
            {
                String[] resultArray = result.split("~`!");

                ArrayList<String> rows = new ArrayList<String>();

                StringBuilder rowContents = new StringBuilder();

                int columnCounter = 0;
                
                for(int i = 1; i < resultArray.length; i++)
                {
                    rowContents.append(resultArray[i]);
                    rowContents.append(" ");
                    columnCounter++;
                    if (columnCounter == Integer.parseInt(resultArray[0]))
                    {
                        rows.add(rowContents.toString());
                        rowContents = new StringBuilder();
                        columnCounter = 0;
                    }
                }

                for (int i = 0; i < rows.size(); i++)
                {
                    System.out.println((i + 1) + ": " + rows.get(i));
                }
            }
        }
        while(true);
    }
    
    public static boolean openWebpage(URI uri) 
    {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) 
        {
            try 
            {
                desktop.browse(uri);
                return true;
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) 
    {
        try 
        {
            return openWebpage(url.toURI());
        } 
        catch (URISyntaxException e) 
        {
            e.printStackTrace();
        }
        return false;
    }
}