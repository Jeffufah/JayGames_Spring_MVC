<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> <%-- Allows for use of spring:message to pull label values from /WEB-INF/properties/Student_Form_Messages_en.properties file --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admission Form</title>
    </head>
    <body>
        <%-- 
            When language links are clicked, they pass their language value to 
            the front controller's localeResolver bean.
        --%>
        <a href="?siteLanguage=en">English</a>  |  <a href="?siteLanguage=fr">French</a>
        <%-- <h1>${headerMessage}</h1> --%>
        <h1><spring:message code="label.headerMessage"/></h1>
        
        <h3><spring:message code="label.admissionForm"/></h3>
        <form:errors path = "newStudent.*"/>
        <form action="submitAdmissionForm.html" method="post">
            <table
                <tr>
                    <td><spring:message code="label.studentName"/></td> <td><input type="text" name="studentName"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.studentHobby"/></td> <td><input type="text" name="studentHobby"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.studentMobile"/></td> <td><input type="text" name="studentMobile"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.studentDOB"/></td> <td><input type="text" name="studentDOB"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.studentSkillSet"/></td>
                <td>
                    <select name="studentSkillSet" multiple>
                        <option value="Java Core"> Java Core</option>
                        <option value="Spring Core"> Spring Core</option>
                        <option value="Spring MVC">Spring MVC</option>
                    </select>
                </td>
                </tr>
            </table>

            <table>
                <tr>
                    <td><spring:message code="label.studentAddress"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.country"/></td> <td><input type="text" name="studentAddress.country"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.city"/></td> <td><input type="text" name="studentAddress.city"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.street"/></td> <td><input type="text" name="studentAddress.street"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.pincode"/></td> <td><input type="text" name="studentAddress.pincode"/></td>
                </tr>
            </table>
            <input type="submit" value="<spring:message code="label.submit.admissionForm"/>"/>
        </form>
    </body>
</html>