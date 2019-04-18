<%-- 
    Document   : ComputeLoan1
    Created on : Jan 21, 2019, 1:45:35 PM
    Author     : Jeffrey McMullen II
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ComputeLoan Using the Loan Class</title>
    </head>
    <body>
        <%@ 
            page import = "JavaClasses.Loan" 
        %>
        <% 
            double loanAmount = Double.parseDouble(
                    request.getParameter("loanAmount"));
            double annualInterestRate = Double.parseDouble(
                    request.getParameter("annualInterestRate"));
            int numberOfYears = Integer.parseInt(
                    request.getParameter("numberOfYears"));
            Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);
        %>
        Loan Amount: <%= loanAmount%> <br />
        Annual Interest Rate: <%= annualInterestRate%> <br />
        Number of Years: <%= numberOfYears%> <br />
        <b>
            Monthly Payment: <%= loan.getMonthlyPayment()%> <br />
            Total Payment: <%= loan.getTotalPayment()%> <br /> 
        </b>
    </body>
</html>
