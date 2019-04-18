<%-- 
    Document   : TestBeanScope
    Created on : Jan 21, 2019, 2:16:52 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import = "JavaClasses.Count" %>
<jsp:useBean id = "count" scope = "application"
             class = "JavaClasses.Count">
</jsp:useBean>
<html>
    <head>
        <title>TestBeanScope</title>
    </head>
    <body>
        <h3>Testing Bean Scope in JSP (Application) </h3>
        <% count.increaseCount();%>
        You are visitor number <%= count.getCount()%> <br />
        From host: <%= request.getRemoteHost()%>
        and session: <%= session.getId()%>
    </body>
</html>
