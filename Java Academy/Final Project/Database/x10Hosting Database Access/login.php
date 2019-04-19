<?php
	//Include the database_credentials.php script variables to access the database with.
	include "/home/jaygame2/includes/admin_database_credentials.php";	
	
	//Should be querying the database for User table column names.
	$uName = $_POST['uName'];
	$pWord = $_POST['pWord'];
	
	if(empty($uName) || empty($pWord))
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
	
	//SELECT `User_id` FROM `users` WHERE `Username` = "Jeffro Bodeen" AND `Password` = "jeffufah";		
	$login_query = "SELECT `User_id` FROM `users` WHERE `Username` = \"".$uName."\" AND `Password` = \"".$pWord."\";";		
				
	
	$loginSQLResult = mysqli_query ($connection, $login_query);
			
	//Show data for each column name in User table.
	if (mysqli_num_rows($loginSQLResult) > 0)
	{
		while ($row = mysqli_fetch_array($loginSQLResult))
		{		
			//For each row
			//Echo the row with ~`! as spacers
			foreach ($row as $key =>$value)
			{				
				if (!is_int($key))
				{
					//Delimeter
					echo "userID".$value;											
				}	
			}			
		}
	}	
	else
	{
		//Return a notification of an empty result.
		echo "Username and Password not found.";
	}		
		
	mysqli_close($connection);
?>