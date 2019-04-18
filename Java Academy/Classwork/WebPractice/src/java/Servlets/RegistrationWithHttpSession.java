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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeffrey McMullen II
 */
@WebServlet(name = "RegistrationWithHttpSession", urlPatterns = {"/RegistrationWithHttpSession"})
public class RegistrationWithHttpSession extends HttpServlet {
        
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
            // Create an Address object
            AddressRegistration address = new AddressRegistration();
            address.setLastName(lastName);
            address.setFirstName(firstName);
            address.setMi(mi);
            address.setTelephone(telephone);
            address.setEmail(email);
            address.setStreet(street);
            address.setCity(city);
            address.setState(state);
            address.setZip(zip);

            // Get an HttpSession or create one if it does not exist
            HttpSession httpSession = request.getSession();

            //httpSession.setMaxInactiveInterval(-1);
            
            // Store student object to the session
            httpSession.setAttribute("address", address);

            // Ask for confirmation
            out.println("You entered the following data");
            out.println("<p>Last name: " + lastName);
            out.println("<p>First name: " + firstName);
            out.println("<p>MI: " + mi);
            out.println("<p>Telephone: " + telephone);
            out.println("<p>Email: " + email);
            out.println("<p>Address: " + street);
            out.println("<p>City: " + city);
            out.println("<p>State: " + state);
            out.println("<p>Zip: " + zip);

            // Set the action for processing the answers
            out.println("<p><form method=\"post\" action="
                    + "RegistrationWithHttpSession>");
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
        // Set response type and output stream to the browser
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtain the HttpSession
        HttpSession httpSession = request.getSession();

        // Get the Address object in the HttpSession
        AddressRegistration address = (AddressRegistration) (httpSession.getAttribute("address"));

        try 
        {
            storeStudent(address);

            out.println(address.getFirstName() + " " + address.getLastName()
                    + " is now registered in the database");
            out.close(); // Close stream
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
     * Store an address to the database 
     */
    private void storeStudent(AddressRegistration address) throws SQLException 
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