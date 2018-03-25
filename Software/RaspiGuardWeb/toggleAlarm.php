<?php
   include('session.php');
   include_once 'config.php';

	$sql = "SELECT dooralarmstate FROM units WHERE username = '$login_session'";
      $result = mysqli_query($db,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
		  
		  if ($row["dooralarmstate"] <=> "on") { 
				

					$alarmUpdate = "on";
				} else $alarmUpdate = "off";
         
         
		 $sql = "UPDATE units SET dooralarmstate = '$alarmUpdate' WHERE username = '$login_session' ";
		 $result = mysqli_query($db,$sql);
		 header("location: unitstatus.php");
     
      }
	

?>