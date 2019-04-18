/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jeffrey McMullen II
 */
public class JDBCount 
{
    private int count = 0;
    private Statement statement = null;
    private String sessionID = null;
    
    public JDBCount() throws SQLException, ClassNotFoundException 
    {
        initializeJdbc();
    }

    /**
     * Return count property
     * @return 
     * @throws java.sql.SQLException 
     */
    public int getCount() throws SQLException 
    {
        
        ResultSet rset = statement.executeQuery("SELECT `Visit_count` FROM `Count` "
                + "WHERE `Session_ID` = " + "\"" + sessionID + "\"");
        rset.next();
        count = rset.getInt(1);
        
        return count;
    }

    /**
     * Increase count
     * @throws java.sql.SQLException
     */
    public void increaseCount() throws SQLException 
    {
        count++;

        statement.executeUpdate("UPDATE Count SET Visit_count = " + "\"" + count + "\""
            + "WHERE Session_ID = " + "\"" + sessionID + "\"");
    }
    
    /**
     * Stores the sessionID
     * @param sessionID 
     * @throws java.sql.SQLException 
     */
    public void setSessionID(String sessionID) throws SQLException
    {
        this.sessionID = sessionID;
        
        ResultSet rset = statement.executeQuery("SELECT `Visit_count` FROM `Count` "
                + "WHERE `Session_ID` = " + "\"" + sessionID + "\"");
        
        if (!rset.isBeforeFirst() ) 
        {                     
            statement.executeUpdate("INSERT INTO `Count`(`Session_ID`, `Visit_count`) "
                + "VALUES (" + "\"" +sessionID + "\", " + "\"" + count + "\")");           
        } 
        else
        {
            rset.next();
            setCount(rset.getInt(1));
        }
    }
    
    private void setCount(int count)
    {
        this.count = count;
    }

    /**
     * Initialize database connection
     */
    public void initializeJdbc() throws SQLException, ClassNotFoundException 
    {
        Class.forName("com.mysql.jdbc.Driver");

        // Connect to the sample database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/WebPractice", "root", "");

        statement = connection.createStatement();
        System.out.println(statement);
    }
}
