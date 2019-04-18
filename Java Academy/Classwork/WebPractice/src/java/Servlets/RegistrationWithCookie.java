/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeffrey McMullen II
 */
@WebServlet(name = "RegistrationWithCookie", urlPatterns = {"/RegistrationWithCookie"})
public class RegistrationWithCookie extends HttpServlet {
    
    private static final String CONTENT_TYPE = "text/html";
    
    private PreparedStatement pstmt;
    
    /** Initialize variables */
    public void init() throws ServletException {
    initializeJdbc();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registration at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtain data from the form
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String mi = request.getParameter("mi");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");

        if (lastName.length() == 0 || firstName.length() == 0) {
            out.println("Last Name and First Name are required");
        } 
        else 
        {
            // Create cookies and send cookies to browsers
            Cookie cookieLastName = new Cookie("lastName", lastName);
            // cookieLastName.setMaxAge(1000);
            response.addCookie(cookieLastName);
            Cookie cookieFirstName = new Cookie("firstName", firstName);
            response.addCookie(cookieFirstName);
            // cookieFirstName.setMaxAge(0);
            Cookie cookieMi = new Cookie("mi", mi);
            response.addCookie(cookieMi);
            Cookie cookieTelephone = new Cookie("telephone", telephone);
            response.addCookie(cookieTelephone);
            Cookie cookieEmail = new Cookie("email", email);
            response.addCookie(cookieEmail);
            Cookie cookieStreet = new Cookie("street", street);
            response.addCookie(cookieStreet);
            Cookie cookieCity = new Cookie("city", city);
            response.addCookie(cookieCity);
            Cookie cookieState = new Cookie("state", state);
            response.addCookie(cookieState);
            Cookie cookieZip = new Cookie("zip", zip);
            response.addCookie(cookieZip);

            // Ask for confirmation
            out.println("You entered the following data");
            out.println("<p>Last name: " + lastName);
            out.println("<br>First name: " + firstName);
            out.println("<br>MI: " + mi);
            out.println("<br>Telephone: " + telephone);

            out.println("<br>Email: " + email);
            out.println("<br>Street: " + street);
            out.println("<br>City: " + city);
            out.println("<br>State: " + state);
            out.println("<br>Zip: " + zip);

            // Set the action for processing the answers
            out.println("<p><form method=\"post\" action="
                    + "RegistrationWithCookie>");
            out.println("<p><input type=\"submit\" value=\"Confirm\" >");
            out.println("</form>");
        }

        out.close(); // Close stream
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        String lastName = "";
        String firstName = "";
        String mi = "";
        String telephone = "";
        String email = "";
        String street = "";
        String city = "";
        String state = "";
        String zip = "";

        // Read the cookies
        Cookie[] cookies = request.getCookies();

        // Get cookie values
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("lastName")) {
                lastName = cookies[i].getValue();
            } else if (cookies[i].getName().equals("firstName")) {
                firstName = cookies[i].getValue();
            } else if (cookies[i].getName().equals("mi")) {
                mi = cookies[i].getValue();
            } else if (cookies[i].getName().equals("telephone")) {
                telephone = cookies[i].getValue();
            } else if (cookies[i].getName().equals("email")) {
                email = cookies[i].getValue();
            } else if (cookies[i].getName().equals("street")) {
                street = cookies[i].getValue();
            } else if (cookies[i].getName().equals("city")) {
                city = cookies[i].getValue();
            } else if (cookies[i].getName().equals("state")) {
                state = cookies[i].getValue();
            } else if (cookies[i].getName().equals("zip")) {
                zip = cookies[i].getValue();
            }
        }

        try 
        {
            storeStudent(lastName, firstName, mi, telephone, email, street,
                    city, state, zip);

            Cookie cookieLastName = new Cookie("lastName", lastName);

            out.println(firstName + " " + lastName
                    + " is now registered in the database");

            out.close(); // Close stream
        }
        catch(Exception ex) 
        {
            out.println("Error: " + ex.getMessage());
        }
    }
        
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Initialize database connection
     */
    private void initializeJdbc() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/WebPractice", "root", "");
            System.out.println("Database connected");

            // Create a Statement
            pstmt = conn.prepareStatement("insert into Address "
                    + "(lastName, firstName, mi, telephone, email, street, city, "
                    + "state, zip) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Store a student record to the database
     */
    private void storeStudent(String lastName, String firstName,
            String mi, String phone, String email, String address,
            String city, String state, String zip) throws SQLException {
        pstmt.setString(1, lastName);
        pstmt.setString(2, firstName);
        pstmt.setString(3, mi);
        pstmt.setString(4, phone);
        pstmt.setString(5, email);
        pstmt.setString(6, address);
        pstmt.setString(7, city);
        pstmt.setString(8, state);
        pstmt.setString(9, zip);
        pstmt.executeUpdate();
    }
}