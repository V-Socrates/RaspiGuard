<?php
   include("config.php");
   
   $error = "";
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form 
      
      $myusername = mysqli_real_escape_string($db,$_POST['username']);
      $mypassword = mysqli_real_escape_string($db,$_POST['password']); 
	  $mypassword = md5($mypassword);
	  $myemail = mysqli_real_escape_string($db,$_POST['email']);
	  $mypin = mysqli_real_escape_string($db,$_POST['pin']);
	  $date = date('Y-m-d H:i:s');
      
      $sql = "SELECT id FROM users WHERE username = '$myusername' ";
      $result = mysqli_query($db,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);

      
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
		  
		$error = "Error, an account with this username already exists!";
		  

      }else {
		  
         $sql = "INSERT INTO users (username,password,pin,email,created_at,updated_at) VALUES  ('$myusername','$mypassword','$mypin','$myemail','$date','$date') ";
		 $result = mysqli_query($db,$sql);
		 
		 session_start();
		 $_SESSION['login_user'] = $myusername;
         
         header("location: index.php");
		 
		 
		 
		 
      }
   }
?>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>RaspiGuard | Register</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">Register an Account</div>
      <div class="card-body">
        <form action = "" method = "post">
		<div class="form-group">
            <label for="exampleInputEmail1">Email</label>
            <input class="form-control" id="exampleInputEmail1" name = "email" type="email" aria-describedby="emailHelp" placeholder="Enter email">
          </div>
          <div class="form-group">
            <label for="exampleInputEmail1">Username</label>
            <input class="form-control" id="exampleInputEmail1" name = "username" type="text" aria-describedby="emailHelp" placeholder="Enter username">
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputPassword1">Password</label>
                <input class="form-control" id="exampleInputPassword1" name = "password" type="password" placeholder="Password">
              </div>
              <div class="col-md-6">
                <label for="exampleConfirmPassword">Confirm password</label>
                <input class="form-control" id="exampleConfirmPassword" type="password" placeholder="Confirm password">
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputPin1">Pin</label>
                <input class="form-control" id="exampleInputPin1" name = "pin" type="password" placeholder="Pin">
              </div>
              <div class="col-md-6">
                <label for="exampleConfirmPassword">Confirm Pin</label>
                <input class="form-control" id="exampleConfirmPin" type="password" placeholder="Confirm Pin">
              </div>
            </div>
          </div>
		  <input class="btn btn-primary btn-block" type = "submit" value = "Submit"/>
        </form>
		<div style = "font-size:11px; color:#cc0000; margin-top:10px"><?php echo $error; ?></div>
        <div class="text-center">
          <a class="d-block small mt-3" href="login.php">Login Page</a>
          <a class="d-block small" href="forgot-password.php">Forgot Password?</a>
        </div>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>
