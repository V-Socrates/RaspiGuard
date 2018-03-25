<?php
   include('session.php');
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>RaspiGuard | Unit Status</title>
  <link rel="icon" href="icons/raspiguard.png">
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
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
          <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Unit Status</li>
      </ol>
      <!-- Example DataTables Card-->
	  	<?php
		include_once 'config.php';
		
		


		// Create connection
		$conn = mysqli_connect($servername, $username, $password, $database);

		$sql="SELECT * FROM units WHERE username='$login_session'; "; 

		$result = mysqli_query($conn, $sql);
		
		if (mysqli_num_rows($result) > 0) {
			
			echo "<div class='card mb-3'>" ;
			echo "<div class='card-header'>" ;
			echo "<i class='fa fa-table'></i> Unit Status</div>" ; 
			echo "<div class='card-body'>" ; 
			echo "<div class='table-responsive'>" ; 
			echo "<table class='table table-bordered' id='dataTable' width='100%' cellspacing='0'>" ; 
			echo "<thead>" ; 
			echo "<tr>" ; 
			echo "<th>Unit Name</th>" ; 
			echo "<th>Location</th>" ; 
			echo "<th>Door Status</th>" ; 
			echo "<th>Door Alarm</th>" ;
			echo "<th>Moisture Level</th>" ; 
			echo "<th>Light Level</th>" ; 
			echo "</tr>" ; 
			echo "</thead>" ; 
			echo "<tbody>" ;	
			
			$alarmOff = "Turn alarm off";
			$alarmOn = "Turn alarm on";
			
			
			
			//table content LOOP BEGINS here
			while($row = mysqli_fetch_assoc($result))
			{
				
				if ($row["dooralarmstate"] <=> "on") { 
				
					$alarmMsg = $alarmOn;
					$alarmUpdate = "off";
				} else { $alarmMsg = $alarmOff;$alarmUpdate = "on";}
				
				
			   $record=sprintf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s<a class='nav-link' style='display:inline-flex;' href='toggleAlarm.php'><i class='fa fa-fw fa-wrench'></i><span class='nav-link-text'> $alarmMsg</span></a></td><td>%s</td><td>%s</td></tr>\n", 
					   $row["unitname"], $row["location"], $row["doorstatus"] , $row["dooralarmstate"], $row["moisturelevel"], $row["lightlevel"]); 
			   echo $record;
			}
			
			
			//table content LOOP ENDS here
			echo "</tbody>" ;
			
			
			
			
			
			echo "</table>" ; 
			echo "</div>" ; 
			echo "</div>" ;
			echo "</div>" ;
			
		
		} else {
			
			echo "Did not find any data for username <b><u>" . $login_session . "</b></u>";
		}
		
		
	  
	  mysqli_close($conn);
	  
		?>
      </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
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
    <!-- Page level plugin JavaScript-->
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
  </div>
</body>

</html>
