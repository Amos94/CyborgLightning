<?php
$con=mysqli_connect("localhost:3306","intendev_prun","Project.run2016","intendev_projectrun");
$sql="INSERT INTO intendev_projectrun.users (email,name,password,dob,gender,activated) VALUES ('amos.neculau@gmail.com','Amos Neculau','AmosPassword123','1994-30-12',0,1)";

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