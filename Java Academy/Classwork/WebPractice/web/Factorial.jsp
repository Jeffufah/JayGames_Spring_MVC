<%-- 
    Document   : Factorial
    Created on : Jan 20, 2019, 4:41:27 PM
    Author     : Jeffrey McMullen II
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Factorial
        </title>
    </head>
    <body>

        <% 
            for (int i = 0; i <= 10; i++) 
            { 
        %>
                Factorial of <%= i %> is <%= computeFactorial(i) %> <br /> 
        <% 
            } 
        %>

        <%! 
            private long computeFactorial(int n) 
            {
                if (n == 0) 
                {
                    return 1;
                }
                else 
                {
                    return n * computeFactorial(n - 1);
                }
            } 
        %>
        
        <%-- Practice code snippet --%>
        <% 
            int k = 9;
            for (int j = 1; j <= k; j++) 
            {
        %>
                <%= j %> <br />
        <%
            } 
        %>
       
    </body>
</html>
