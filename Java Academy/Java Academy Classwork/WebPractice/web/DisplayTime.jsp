<%-- 
    Document   : DisplayTime
    Created on : Jan 21, 2019, 6:29:49 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="GB18030"%>
<!DOCTYPE html>
 <%@ page import = "JavaClasses.TimeBean" %>
 <jsp:useBean id = "timeBeanId"
 class = "JavaClasses.TimeBean" scope = "application" >
 </jsp:useBean>
 <jsp:setProperty name = "timeBeanId" property = "*" />

 <html>
     <head>
         <title>
             Display Time
         </title>
     </head>
     <body>
         <h3>Choose locale and time zone</h3>
         Current time is
         <%= timeBeanId.currentTimeString(timeBeanId.getLocaleIndex(),
         timeBeanId.getTimeZoneIndex())%>
     </body>
<html>