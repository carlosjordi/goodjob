<?php

	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'goodjod');
	
	$con = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	if(mysqli_connect_errno())
	{
		die('Unable to connect to database' . mysqli_connect_error());
    }
    
    $id = $_GET['id_producto'];

    $consulta_string = "update producto set estado = 1 where id = $id";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo 'Producto Aceptado';
	}
	
	mysqli_close($con);
?>