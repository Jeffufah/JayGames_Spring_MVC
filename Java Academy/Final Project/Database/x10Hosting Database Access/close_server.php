<?php
	//Include the database_credentials.php script variables to access the database with.
	include "/home/jaygame2/includes/admin_database_credentials.php";	
	
	$userID = $_POST['userID'];
	$uName = $_POST['uName'];
	$pWord = $_POST['pWord'];
	
	if(empty($userID) || empty($uName) || empty($pWord))
	{
		die ("Empty login credentials are not allowed.");
	}
	
	$connection = mysqli_connect($admin_serverName, $admin_user, $admin_password, $admin_dbName);
	
	if (!$connection)
	{
		die ("Error".mysqli_connect_error());	
	}
		
	$validationQuery = "SELECT `User_id` FROM `users` WHERE `User_id` = \"".$userID."\" AND `Username` = \"".$uName."\" AND `Password` = \"".$pWord."\";";		
		
	$validationSQLResult = mysqli_query ($connection, $validationQuery);
			
	if (mysqli_num_rows($validationSQLResult) > 0)
	{		
		$deleteServersQuery = "DELETE FROM `game_servers` WHERE GS_user_id = \"".$userID."\";";
		mysqli_query($connection, $deleteServersQuery);
	}	
	else
	{
		echo "Credentials do not match.";
	}		
		
	mysqli_close($connection);
?>