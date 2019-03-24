<?php
	session_start();
	$message = htmlentities($_POST['message']);
	$mysqli = new \mysqli("localhost", "smd", "smd123", "mdb", 3306);
	$stmt = $mysqli->prepare("INSERT INTO messages (message) VALUES (?)");
	$stmt->bind_param('s', $message);
	$stmt->execute();
	include "index.php";
