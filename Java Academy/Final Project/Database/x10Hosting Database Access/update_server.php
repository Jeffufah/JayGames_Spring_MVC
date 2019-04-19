<?php
	//Include the database_credentials.php script variables to access the database with.
	include "/home/jaygame2/includes/admin_database_credentials.php";	
	
	//Should be querying the database for User table column names.
	$userID = $_POST['userID'];
	
	//Make Connection
	$connection = mysqli_connect($admin_serverName, $admin_user, $admin_password, $admin_dbName);
	
	//Check Connection
	//If connection failure, print connection error
	if (!$connection)
	{
		die ("Error".mysqli_connect_error());
	}
		
	//Validate the id, username, and password
	$updateServerQuery = "UPDATE `game_servers` SET `heartbeat_timestamp`= NOW() WHERE `GS_user_id` = \"".$userID."\";";		
		
	mysqli_query ($connection, $updateServerQuery);	
		
	mysqli_close($connection);
?>