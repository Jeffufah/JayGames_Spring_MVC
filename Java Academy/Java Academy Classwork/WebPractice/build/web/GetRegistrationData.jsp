<%-- 
    Document   : GetRegistrationData
    Created on : Jan 21, 2019, 6:45:11 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import = "Servlets.AddressRegistration" %>
<jsp:useBean id = "addressId"
             class = "Servlets.AddressRegistration" scope = "session"></jsp:useBean>
<jsp:setProperty name = "addressId" property = "*" />

<html>
    <body>
        <h1>Registration Using JSP</h1>

        <%
            if (addressId.getLastName() == null
                    || addressId.getFirstName() == null) {
                out.println("Last Name and First Name are required");
                return; // End the method
            }
        %>

        <p>You entered the following data</p>
        <p>Last name: <%= addressId.getLastName()%></p>
        <p>First name: <%= addressId.getFirstName()%></p>
        <p>MI: <%= addressId.getMi()%></p>
        <p>Telephone: <%= addressId.getTelephone()%></p>
        <p>Email: <%= addressId.getEmail()%></p>
        <p>Address: <%= addressId.getStreet()%></p>
        <p>City: <%= addressId.getCity()%></p>
        <p>State: <%= addressId.getState()%></p>
        <p>Zip: <%= addressId.getZip()%></p>

        <!-- Set the action for processing the answers -->
        <form method = "post" action = "StoreStudent.jsp">
            <input type = "submit" value = "Confirm">
        </form>
    </body>
</html>