<%-- 
    Document   : ComputeLoan2
    Created on : Jan 21, 2019, 5:14:04 PM
    Author     : Jeffrey McMullen II
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ComputeLoan Using the Loan Class</title>
    </head>
    <body>
        <%@ page import = "JavaClasses.Loan" %>
        <jsp:useBean id = "loan" class = "JavaClasses.Loan"
                     scope = "page" ></jsp:useBean>
        <jsp:setProperty name = "loan" property = "*" />
        Loan Amount: <%= loan.getLoanAmount()%><br />
        Annual Interest Rate: <%= loan.getAnnualInterestRate()%><br />
        Number of Years: <%= loan.getNumberOfYears()%><br />
        <b>
            Monthly Payment: <%= loan.getMonthlyPayment()%><br />
            Total Payment: <%= loan.getTotalPayment()%><br />
        </b>
    </body>
</html>