<%-- 
    Document   : TestBeanScopeWithDatabase
    Created on : Jan 21, 2019, 2:31:10 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import = "JavaClasses.JDBCount" %>
<jsp:useBean id = "JDBCount" scope = "application"
             class = "JavaClasses.JDBCount">
</jsp:useBean>
<html>
    <head>
        <title>TestBeanScopeWithDatabase</title>
    </head>
    <body>
        <h3>Testing Bean Scope with Database in JSP (Application) </h3>
        <% 
            JDBCount.setSessionID(session.getId());
            JDBCount.increaseCount();
        %>
        You are visitor number <%= JDBCount.getCount()%> <br />
        From host: <%= request.getRemoteHost()%>
        and session: <%= session.getId()%>
    </body>
</html>
