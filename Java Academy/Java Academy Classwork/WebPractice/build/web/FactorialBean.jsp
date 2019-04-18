<%-- 
    Document   : FactorialBean
    Created on : Jan 21, 2019, 6:05:52 PM
    Author     : Jeffrey McMullen II
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%@ page import = "JavaClasses.FactorialBean" %>
 <jsp:useBean 
    id = "factorialBeanId"
    class = "JavaClasses.FactorialBean" scope = "page" >
 </jsp:useBean>
 <jsp:setProperty name = "factorialBeanId" property = "*" />
 <html>
     <head>
         <title>
             FactorialBean
         </title>
     </head>
     <body>
         <h3>Compute Factorial Using a Bean</h3>
         <form method = "post">
             Enter new value: <input name = "number" /><br /><br />
             <input type = "submit" name = "Submit"
                    value = "Compute Factorial" />
             <input type = "reset" value = "Reset" /><br /><br />
             Factorial of
             <jsp:getProperty name = "factorialBeanId"
                              property = "number" /> is
             <%@ page import = "java.text.*" %>
             <% NumberFormat format = NumberFormat.getNumberInstance();%>
             <%= format.format(factorialBeanId.getFactorial())%>
         </form>
     </body>
 </html>