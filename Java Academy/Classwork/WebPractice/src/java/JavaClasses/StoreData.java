/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClasses;

import Servlets.AddressRegistration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jeffrey McMullen II
 */
public class StoreData 
{
    // Use a prepared statement to store a student into the database

    private PreparedStatement pstmt;

    public StoreData() 
    {
        initializeJdbc();
    }

    /**
     * Initialize database connection
     */
    private void initializeJdbc() 
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the sample database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/WebPractice", "root", "");

            // Create a Statement
            pstmt = connection.prepareStatement("insert into Address "
                    + "(lastName, firstName, mi, telephone, email, street, city, "
                    + "state, zip) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }

    /**
     * Store a student record to the database
     * @param address
     * @throws java.sql.SQLException
     */
    public void storeStudent(AddressRegistration address) throws SQLException 
    {
        pstmt.setString(1, address.getLastName());
        pstmt.setString(2, address.getFirstName());
        pstmt.setString(3, address.getMi());
        pstmt.setString(4, address.getTelephone());
        pstmt.setString(5, address.getEmail());
        pstmt.setString(6, address.getStreet());
        pstmt.setString(7, address.getCity());
        pstmt.setString(8, address.getState());
        pstmt.setString(9, address.getZip());
        pstmt.executeUpdate();
    }
}