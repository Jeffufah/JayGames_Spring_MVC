<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Jay Games</title>
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
        <link rel="icon" href="images/favicon.ico" type="image/x-icon" />
    </head>
    <body>
        <ul>
            <li><a class="active" href="Home">Home</a></li>
            <li><a href="Games">Games</a></li>
        </ul>

        <div style="padding:0px;margin-top:0px;background-color:#1abc9c;height:1000px;">
            <h2>Happy Gaming</h2> 
            <div id="Login" style="clear:both;">
                <div style="float:right;">
                    <div style="text-align:left;">
                        <form action="Home" method="post">
                            Username: <input type="text" name="userName"/>
                            <br/>
                            Password: <input type="text" name="userPassword"/>
                            <br/>
                            <input type="submit" value="Login"/>
                            ${user.loginStatus}
                            <br />
                        </form>
                    </div>
                </div>
            </div>    
        </div>
    </body>
</html>