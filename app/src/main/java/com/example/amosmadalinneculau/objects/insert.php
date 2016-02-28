<?php
$con=mysqli_connect("localhost:3306","intendev_prun","Project.run2016","intendev_projectrun");

$userEmail = $_POST['email'];
$userName = $_POST['name'];
$userPass = $_POST['password'];
$userDOB = $_POST['dob'];
$userGend = $_POST['gender'];
$userActive = $_POST['activated']; 

$sql="INSERT INTO intendev_projectrun.users (email,name,password,dob,gender,activated) VALUES ('$userEmail','$userName','$userPass','$userDOB',$userGend, $userActive)";

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
else{
	echo "Connection was established\n\n\n";
}

if(mysqli_query($con,$sql))
{
	echo "values have been successfully inserted";
}
else{
	echo "values have NOT been successfully inserted";
}
?>