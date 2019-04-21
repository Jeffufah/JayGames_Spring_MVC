<?php
	//Include the database_credentials.php script variables to access the database with.
	include "/home/jaygame2/includes/admin_database_credentials.php";	
	
	$uName = $_POST['uName'];
	$pWord = $_POST['pWord'];
	
	if(empty($uName) || empty($pWord))
	{
		die ("Empty login credentials are not allowed.");
	}
	
	$connection = mysqli_connect($admin_serverName, $admin_user, $admin_password, $admin_dbName);
	
	if (!$connection)
	{
		die ("Error".mysqli_connect_error());	
	}
	
	$login_query = "SELECT `User_id` FROM `users` WHERE `Username` = \"".$uName."\" AND `Password` = \"".$pWord."\";";		
				
	
	$loginSQLResult = mysqli_query ($connection, $login_query);
			
	if (mysqli_num_rows($loginSQLResult) > 0)
	{
		while ($row = mysqli_fetch_array($loginSQLResult))
		{		
			foreach ($row as $key =>$value)
			{				
				if (!is_int($key))
				{
					echo "userID".$value;											
				}	
			}			
		}
	}	
	else
	{
		echo "Username and Password not found.";
	}		
		
	mysqli_close($connection);
?>