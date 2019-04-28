<?php
	//Servername
	$serverName = "localhost";
	
	//Username from Unity
	$user = $_POST['user'];
	
	//Password from Unity
	$password = $_POST['password'];

	//Database name
	$dbName = "schoolt5_Owls";
	
	//Variable Sql Statement
	$sql = $_POST['SQLSTATEMENT'];
	
	//Make Connection
	$connection = mysqli_connect($serverName, $user, $password, $dbName);
	
	//Check Connection
	//If connection failure, print connection error
	if (!$connection){
		die ("Connection Failed.".mysqli_connect_error());	
	}
			
	//Select all columns from teamscores database
	//$sql = "SELECT * FROM teamscores";
	
	//Perform query
	$result = mysqli_query ($connection, $sql);
			
	//Show data for each row
	if(mysqli_num_rows($result) >0){
		
		while ($row = mysqli_fetch_array($result)){
			
			//For each row
			//Echo the row with ; as spacers
			foreach ($row as $key =>$value)
			{				
				if (!is_int($key))
				{
					echo $value.";";											
				}	
			}			
		}		
	}		
?>