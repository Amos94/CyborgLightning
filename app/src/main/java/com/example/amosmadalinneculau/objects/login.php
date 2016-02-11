<?php
$con=mysqli_connect("localhost:3306","intendev_prun","Project.run2016","intendev_projectrun");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$email = $_POST['email'];
$password = $_POST['password'];

$result = mysqli_query($con,"SELECT name FROM intendev_projectrun.users where email='$email' and password='$password'");

$row = mysqli_fetch_array($result);
$data = $row[0];

if($data != null){
echo $data;
echo "<br>";
echo "connection successfuly";
}
else{
	echo "Couldn't connect with credentials given";
}
mysqli_close($con);
?>