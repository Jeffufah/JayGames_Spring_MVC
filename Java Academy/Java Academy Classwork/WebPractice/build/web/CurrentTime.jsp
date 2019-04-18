<%-- 
    Document   : CurrentTime
    Created on : Jan 20, 2019, 4:31:58 PM
    Author     : Jeffrey McMullen II
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Current Time JSP</title>
    </head>
    <body>
        <h1>
            Current time is: <%= new java.util.Date() %> 
        </h1>
    </body>
</html>
