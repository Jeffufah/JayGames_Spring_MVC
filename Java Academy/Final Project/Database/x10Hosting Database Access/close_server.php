<?php
	//Include the database_credentials.php script variables to access the database with..
	include "/home/jaygame2/includes/admin_database_credentials.php";	
	
	$userID = $_POST['userID'];
	$uName = $_POST['uName'];
	$pWord = $_POST['pWord'];
	
	if(empty($userID) || empty($uName) || empty($pWord))
	{
		die ("Empty login credentials are not allowed.");
	}
	
	//Make Connection
	$connection = mysqli_connect($admin_serverName, $admin_user, $admin_password, $admin_dbName);
	
	//Check Connection
	//If connection failure, print connection error
	if (!$connection)
	{
		die ("Error".mysqli_connect_error());	
	}
		
	//Validate the id, username, and password
	$validationQuery = "SELECT `User_id` FROM `users` WHERE `User_id` = \"".$userID."\" AND `Username` = \"".$uName."\" AND `Password` = \"".$pWord."\";";		
		
	$validationSQLResult = mysqli_query ($connection, $validationQuery);
			
	//Show data for each column name in User table.
	if (mysqli_num_rows($validationSQLResult) > 0)
	{		
		$deleteServersQuery = "DELETE FROM `game_servers` WHERE GS_user_id = \"".$userID."\";";
		mysqli_query($connection, $deleteServersQuery);
	}	
	else
	{
		//Return a notification of an empty result.
		echo "Credentials do not match.";
	}		
		
	mysqli_close($connection);
?>