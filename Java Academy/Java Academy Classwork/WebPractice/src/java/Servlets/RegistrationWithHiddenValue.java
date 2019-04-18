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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeffrey McMullen II
 */
@WebServlet(name = "RegistrationWithHiddenValue", urlPatterns = {"/RegistrationWithHiddenValue"})
public class RegistrationWithHiddenValue extends HttpServlet {

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
        } else {
            // Ask for confirmation
            out.println("You entered the following data");
            out.println("<p>Last name: " + lastName);
            out.println("<br>First name: " + firstName);
            out.println("<br>MI: " + mi);
            out.println("<br>Telephone: " + telephone);
            out.println("<br>Email: " + email);
            out.println("<br>Address: " + street);
            out.println("<br>City: " + city);
            out.println("<br>State: " + state);
            out.println("<br>Zip: " + zip);

            // Set the action for processing the answers
            out.println("<p><form method=\"post\" action="
                    + "RegistrationWithHiddenValue>");
            // Set hidden values
            out.println("<p><input type=\"hidden\" "
                    + "value=" + lastName + " name=\"lastName\">");
            out.println("<p><input type=\"hidden\" "
                    + "value=" + firstName + " name=\"firstName\">");
            out.println("<p><input type=\"hidden\" "
                    + "value=" + mi + " name=\"mi\">");
            out.println("<p><input type=\"hidden\" "
                    + "value=" + telephone + " name=\"telephone\">");
            out.println("<p><input type=\"hidden\" "
                    + "value=" + email + " name=\"email\">");
            out.println("<p><input type=\"hidden\" "
                    + "value=" + street + " name=\"street\">");
            out.println("<p><input type=\"hidden\" "
                    + "value=" + city + " name=\"city\">");
            out.println("<p><input type=\"hidden\" "
                    + "value=" + state + " name=\"state\">");
            out.println("<p><input type=\"hidden\" "
                    + "value=" + zip + " name=\"zip\">");
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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String mi = request.getParameter("mi");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");

            storeStudent(lastName, firstName, mi, telephone, email,
                    street, city, state, zip);

            out.println(firstName + " " + lastName
                    + " is now registered in the database");
        } 
        catch (Exception ex) 
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