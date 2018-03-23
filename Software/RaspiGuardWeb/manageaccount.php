<?php
   include('session.php');
   
   
   
   $passstatus = "";
   $pinstatus = "";
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
	   
	   
	   
	if (isset($_POST['passSubmit'])) {
      $myusername = $login_session;
      $mypassword = mysqli_real_escape_string($db,$_POST['password']); 
	  $mypassword = md5($mypassword);
	  $newpassword = mysqli_real_escape_string($db,$_POST['newpassword']);
	  $newpassword = md5($newpassword);
      
      $sql = "SELECT id FROM users WHERE username = '$myusername' and password = '$mypassword'";
      $result = mysqli_query($db,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      $active = $row['active'];
      
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
         
         
		 $sql = "UPDATE users SET password = '$newpassword' WHERE username = '$myusername' ";
		 $result = mysqli_query($db,$sql);
		 $passstatus = "Password changed!";
     
      }else {
         $passstatus = "Current password is invalid, try again";
      }
	}
	
	
	if (isset($_POST['pinSubmit'])) {
      $myusername = $login_session;
      $mypin = mysqli_real_escape_string($db,$_POST['pin']); 

	  $newpin = mysqli_real_escape_string($db,$_POST['newpin']);

      
      $sql = "SELECT id FROM users WHERE username = '$myusername' and pin = '$mypin'";
      $result = mysqli_query($db,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      $active = $row['active'];
      
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
         
         
		 $sql = "UPDATE users SET pin = '$newpin' WHERE username = '$myusername' ";
		 $result = mysqli_query($db,$sql);
		 $pinstatus = "PIN changed!";
     
      }else {
         $pinstatus = "Current PIN is invalid, try again";
      }
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
  <title>RaspiGuard | Manage Account</title>
  <link rel="icon" href="icons/raspiguard.png">
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
 <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.php">RaspiGuard</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="index.php">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Dashboard</span>
          </a>
        </li>
		<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link" href="unitstatus.php">
            <i class="fa fa-fw fa-desktop"></i>
            <span class="nav-link-text">Unit Status</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="statistics.php">
            <i class="fa fa-fw fa-area-chart"></i>
            <span class="nav-link-text">Statistics</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="activitylog.php">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">Activity Log</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link" href="manageaccount.php">
            <i class="fa fa-fw fa-wrench"></i>
            <span class="nav-link-text">Manage Account</span>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout (<?php echo $login_session; ?>)</a>
        </li>
      </ul>
    </div>
  </nav>
  
  
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Manage Account</li>
      </ol>
	  
	  
	  
		
        <div class="col-sm-4">
          <h2>Change Password</h2>
		  
		<form action = "" method = "post">
		  <div class="form-group">
			<label for="exampleInputPassword1">Current Password</label>
			<input type="password" class="form-control input-sm" id="exampleInputPassword1" name = "password" placeholder="Current Password">
		  </div>
		  <div class="form-group">
			<label for="exampleInputPassword2">New Password</label>
			<input type="password" class="form-control" id="exampleInputPassword2" name = "newpassword" placeholder="New Password">
		  </div>
		  <div class="form-group">
			<label for="exampleInputPassword3">Confirm Password</label>
			<input type="password" class="form-control" id="exampleInputPassword3" placeholder="Confirm Password">
		  </div>
		  <input type = "submit" class="btn btn-primary" name="passSubmit" value = " Submit "/>
		</form>
		<div style = "font-size:11px; color:#cc0000; margin-top:10px"><?php echo $passstatus; ?></div>
		   
        </div>
		
		<hr size=1>
		
		  <div class="col-sm-2">
		
          <h2 style="margin-top : 20px;">Change Pin</h2>
		  <form action = "" method = "post">
		  <div class="form-group">
			<label for="exampleInputPin1">Current Pin</label>
			<input type="password" class="form-control" id="exampleInputPin1" name = "pin" placeholder="Current Pin">
		  </div>
		  <div class="form-group">
			<label for="exampleInputPin2">New Pin</label>
			<input type="password" class="form-control" id="exampleInputPin2" name = "newpin" placeholder="New Pin">
		  </div>
		  <div class="form-group">
			<label for="exampleInputPin3">Confirm Pin</label>
			<input type="password" class="form-control" id="exampleInputPin3" placeholder="Confirm Pin">
		  </div>
		  <input type = "submit" class="btn btn-primary" name="pinSubmit" value = " Submit "/>
		</form>
		<div style = "font-size:11px; color:#cc0000; margin-top:10px"><?php echo $pinstatus; ?></div>
		  
          

		  
		  
		 
		 
		 
		 
        </div>
		
		
      </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
	
	
	
	
	
	
	
	
	
	
    <footer class="sticky-footer" >
      <div class="container" >
        <div class="text-center" >
          <small>RaspiGuard 2018</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="logout.php">Logout</a>
          </div>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
  </div>
</body>

</html>
