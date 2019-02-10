<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admission Success</title>
    </head>
    <body>
        <h1>${headerMessage}</h1>
        <h3>Congratulations! DTCC has processed your application form successfully.</h3>
        <h2>Your info::</h2>
        <table>
            <tr>
                <td>Student Name :</td>
                <td>${newStudent.studentName}</td>
            </tr>
            <tr>
                <td>Student Hobby :</td>
                <td>${newStudent.studentHobby}</td>
            </tr>
            <tr>
                <td>Student Mobile :</td>
                <td>${newStudent.studentMobile}</td>
            </tr>
            <tr>
                <td>Student DOB :</td>
                <td>${newStudent.studentDOB}</td>
            </tr>
            <tr>
                <td>Student Skill Set :</td>
                <td>${newStudent.studentSkillSet}</td>
            </tr>
        </table>
        <table>
            <tr>
                <td><h3>Student Address :</h3></td>
            </tr>
            <tr>
                <td>Country: ${newStudent.studentAddress.country}</td>
            </tr>
            <tr>
                <td>City: ${newStudent.studentAddress.city}</td>
            </tr>
            <tr>
                <td>Street: ${newStudent.studentAddress.street}</td>
            </tr>
            <tr>
                <td>Pincode: ${newStudent.studentAddress.pincode}</td>
            </tr>
        </table>
    </body>
</html>
