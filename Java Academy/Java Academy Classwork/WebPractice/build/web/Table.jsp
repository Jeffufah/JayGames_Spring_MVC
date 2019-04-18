<%-- 
    Document   : Table
    Created on : Jan 21, 2019, 8:22:00 PM
    Author     : Jeffrey McMullen II
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import = "JavaClasses.DBBean" %>
<jsp:useBean id = "dBBeanId" scope = "session"
             class = "JavaClasses.DBBean">
</jsp:useBean>
<html>
    <head>
        <title>Table</title>
    </head>
    <body>
        <% String[] tables = dBBeanId.getTables();
     if (tables == null) { %>
        No tables
        <% } else { %>
        <form method = "post" action = "BrowseTable.jsp">
            Select a table
            <select name = "tablename" size = "1">
                <% for (int i = 0; i < tables.length; i++) {%>
                <option><%= tables[i]%></option>
                <% }
        }%>
            </select><br /><br /><br />
            <input type = "submit" name = "Submit"
                   value = "Browse Table Content">
            <input type = "reset" value = "Reset">
        </form>
    </body>
</html>
