package com.jaygames_jsf.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.sql.*;
import java.io.Serializable;

@Named(value = "userRegistrationBean")
@SessionScoped
/**
 *
 */
public class UserRegistrationBean implements Serializable 
{
    private String username;
    private String email;
    private String password;
    private int privacyLevel;
    private String gender;
    private String country;
    private String state;
    private String language;
    
    private String status;
   
    // Use a prepared statement to store a student into the database
    private PreparedStatement pstmt;

    public UserRegistrationBean() 
    {
        privacyLevel = -1;
        initializeJdbc();
    }
    
    private boolean isRquiredFieldsFilled() 
    {
        return !(username == null || email == null
                || password == null || privacyLevel == -1
                || gender == null || country == null
                || state == null || language == null);
    }
    
    /**
     * Initialize database connection
     */
    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Connect to the database
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/jaygame2_dtccjaygames", "root", "");

            // Create a Statement
            pstmt = connection.prepareStatement("insert into users (username,"
                    + " email, password, USR_privacy_id, gender, country, state, "
                    + "language) values (?, ?, ?, ?, ?, ?, ?, ?)");
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    
    /**
     * Store an address to the database
     */
    public String storeUser() 
    {
        try 
        {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setInt(4, privacyLevel);
            pstmt.setString(5, gender);
            pstmt.setString(6, country);
            pstmt.setString(7, state);
            pstmt.setString(8, language);
            pstmt.executeUpdate();
            status = username
                    + " is now registered in the database.";
        } 
        catch (Exception ex) 
        {
            status = ex.getMessage().toString();
        }

        return "RegistrationStatus";
    }
    
    public String processSubmit() {
        if (isRquiredFieldsFilled()) {
            return "ConfirmCredentials";
        } else {
            return "";
        }
    }
    
    public String loginRedirect()
    {
        return "Login";
    }

    public String getInput() {
        return "<p style=\"color:red\">You entered <br />"
                + "Username: " + username + "<br />"
                + "Email: " + email + "<br />"
                + "Password: " + password + "<br />"
                + "Privacy Level: " + privacyLevel + "<br />"
                + "Gender: " + gender + "<br />"
                + "Country: " + country + "<br />"
                + "State: " + state + "<br />"
                + "Language: " + language + "<br />";
    }
    
    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public int getPrivacyLevel() 
    {
        return privacyLevel;
    }

    public void setPrivacyLevel(int privacyLevel) 
    {
        this.privacyLevel = privacyLevel;
    }

    public String getGender() 
    {
        return gender;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getCountry() 
    {
        return country;
    }

    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getState() 
    {
        return state;
    }

    public void setState(String state) 
    {
        this.state = state;
    }

    public String getLanguage() 
    {
        return language;
    }

    public void setLanguage(String language) 
    {
        this.language = language;
    }

    public PreparedStatement getPstmt() 
    {
        return pstmt;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }
    
    public void setPstmt(PreparedStatement pstmt) 
    {
        this.pstmt = pstmt;
    }
}
