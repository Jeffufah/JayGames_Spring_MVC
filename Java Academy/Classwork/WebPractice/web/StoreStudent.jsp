<%-- 
    Document   : StoreStudent
    Created on : Jan 21, 2019, 6:49:07 PM
    Author     : Jeffrey McMullen II
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import = "Servlets.AddressRegistration" %>
<jsp:useBean id = "addressId" class = "Servlets.AddressRegistration"
             scope = "session"></jsp:useBean>
<jsp:useBean id = "storeDataId" class = "JavaClasses.StoreData"
             scope = "application"></jsp:useBean>

    <html>
        <body>
        <%
            storeDataId.storeStudent(addressId);

            out.println(addressId.getFirstName() + " "
                    + addressId.getLastName()
                    + " is now registered in the database");
            out.close(); // Close stream
        %>
    </body>
</html>