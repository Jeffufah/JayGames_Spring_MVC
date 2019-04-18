<%-- 
    Document   : DBLoginInitialization
    Created on : Jan 21, 2019, 8:16:07 PM
    Author     : Jeffrey McMullen II
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import = "JavaClasses.DBBean" %>
<jsp:useBean 
    id = "dBBeanId" scope = "session"
    class = "JavaClasses.DBBean">
</jsp:useBean>
<jsp:setProperty name = "dBBeanId" property = "*" />
<html>
    <head>
        <title>DBLoginInitialization</title>
    </head>
    <body>
        <%-- Connect to the database --%>
        <% dBBeanId.initializeJdbc(); %>

        <% if (dBBeanId.getConnection() == null) { %>
        Error: Login failed. Try again.
        <% } else {%>
        <jsp:forward page = "Table.jsp" />
        <% }%>
    </body>
</html>