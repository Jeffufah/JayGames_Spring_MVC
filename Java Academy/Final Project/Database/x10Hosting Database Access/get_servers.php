<?php
	//Include the database_credentials.php script variables to access the database with.
	include "/home/jaygame2/includes/admin_database_credentials.php";	
	
	$userID = $_POST['userID'];
	$uName = $_POST['uName'];
	$pWord = $_POST['pWord'];
	
	$gameID = $_POST['gameID'];
	$accessLevel = $_POST['accessLevel'];
	
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
		$deleteStaleServersQuery = "DELETE FROM `game_servers` WHERE heartbeat_timestamp < (NOW() - INTERVAL 1 MINUTE);";
		mysqli_query($connection, $deleteStaleServersQuery);
	
	
		$getServersQuery = "SELECT `Username`, `ip_address`, `port` 
					FROM `game_servers` 
					INNER JOIN `users` ON `GS_user_id` = `User_id`
					WHERE `GS_game_id` = \"".$gameID."\"
					AND `access_level` = \"".$accessLevel."\";";
		
		$getServersSQLResult = mysqli_query ($connection, $getServersQuery);
		
		if (mysqli_num_rows($getServersSQLResult) > 0)
		{
			$date = date();
		
			while ($row = mysqli_fetch_array($getServersSQLResult))
			{		
				//For each row
				//Echo the row with ~`! as spacers
				foreach ($row as $key =>$value)
				{				
					if (!is_int($key))
					{
						//Delimeter
						echo $value."~`!";											
					}	
				}			
			}
		}
		else
		{
			echo "There aren't any servers that match these filters.";
		}
	}	
	else
	{
		//Return a notification of an empty result.
		echo "Credentials do not match.";
	}		
		
	mysqli_close($connection);
?>