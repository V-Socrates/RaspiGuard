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
  <title>RaspiGuard | Home</title>
  <link rel="icon" href="images/raspiguard.png">
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
        <li class="breadcrumb-item active">My Dashboard</li>
      </ol>
	  
	 
	  
      <!-- Icon Cards - THEY ARE CUTE WIDGETS BUT A BIT TOO TIME CONSUMING TO IMPLEMENT AND NO RETURN ON TIME INVESTMENT
	  
      <div class="row">
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-primary o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-bell"></i>
              </div>
              <div class="mr-5">6 New Alerts!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-list"></i>
              </div>
              <div class="mr-5">11 New Activities!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>

        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-warning"></i>
              </div>
              <div class="mr-5">3 New Alarms!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
		
		 <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-question"></i>
              </div>
              <div class="mr-5">123 New Item?</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
		
      </div>
	  
			Icon Cards - THEY ARE CUTE WIDGETS BUT A BIT TOO TIME CONSUMING TO IMPLEMENT AND NO RETURN ON TIME INVESTMENT		-->
	  

	  
	  	  <!-- Example Notifications Card-->

		  
		  
		  
		  
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-bell-o"></i> Recent Door Activities</div>
            <div class="list-group list-group-flush small">
			
			
			<?php

			include_once 'config.php';
			


			// Create connection
			$conn = mysqli_connect($servername, $username, $password, $database);

			
			$sql="SELECT * FROM activitylog WHERE username='$login_session' AND activity LIKE '%door%' OR activity LIKE '%alarm%' ORDER BY id DESC LIMIT 10";  

			$result = mysqli_query($conn, $sql);
			
			
			if (mysqli_num_rows($result) > 0) {
				
				while($row = mysqli_fetch_assoc($result))
				{
					
				echo "<a class='list-group-item list-group-item-action'>" ; 
				echo "<div class='media'>" ; 
				
				
				if (strcmp($row["activity"], "door closed") == 0) {
					
					echo "<img class='d-flex mr-3 rounded-circle' src='images/door_closed.png' alt='images/raspiguard.png'>" ;
					
				} elseif (strcmp($row["activity"], "door opened") == 0) {
					
					echo "<img class='d-flex mr-3 rounded-circle' src='images/door_opened.png' alt='images/raspiguard.png'>" ;
					
				} elseif (strcmp($row["activity"], "alarm triggered") == 0) {
					
					echo "<img class='d-flex mr-3 rounded-circle' src='images/alarm_triggered.png' alt='images/raspiguard.png'>" ;
					
				} elseif (strcmp($row["activity"], "Alarm triggered") == 0) {
					
					echo "<img class='d-flex mr-3 rounded-circle' src='images/alarm_triggered.png' alt='images/raspiguard.png'>" ;
					
				}elseif (strcmp($row["activity"], "alarm on") == 0) {
					
					echo "<img class='d-flex mr-3 rounded-circle' src='images/alarm_on.png' alt='images/raspiguard.png'>" ;
					
				}elseif (strcmp($row["activity"], "alarm off") == 0) {
					
					echo "<img class='d-flex mr-3 rounded-circle' src='images/alarm_off.png' alt='images/raspiguard.png'>" ;
					
				}elseif (strcmp($row["activity"], "moisture measurement") == 0) {
					
					echo "<img class='d-flex mr-3 rounded-circle' src='images/moisture.png' alt='images/raspiguard.png'>" ;
					
				}elseif (strcmp($row["activity"], "light measurement") == 0) {
					
					echo "<img class='d-flex mr-3 rounded-circle' src='images/light.png' alt='images/raspiguard.png'>" ;
					
				}else echo "<img class='d-flex mr-3 rounded-circle' src='images/raspiguard.png' alt=''>" ; 
				
				
				
				echo "<div class='media-body'>" ; 
				
				$record=sprintf("%s <strong>%s</strong>", $row["sensorname"], $row["activity"]); 
				echo $record;
				
				
				$date = date_create($row["datetime"]);
				

				echo "<div class='text-muted smaller'>";
				echo date_format($date, 'l \a\t g:i A, jS F Y');
				echo "</div>" ;
				
				
				
				//echo "<strong>Sensor Name</strong> Activity/Event occured at" ; 
				//echo "<strong> Location</strong>." ; - should we include location?
				
				
				
				//echo "<div class='text-muted smaller'>Today at 5:43 PM - 5m ago</div>" ; 
				echo "</div>" ; 
				echo "</div>" ; 
				echo "</a>" ; 
				}
				
				
				
				
				
			} else {
			
			echo "<div class='media-body'>" ; 
			echo "Did not find any data for username <b><u>" . $login_session . "</b></u>";
			echo "</div>" ;
			}
			
			?>
			
			
			
					   
			  
			  
			  
              <a class="list-group-item list-group-item-action" href="activitylog.php">View all activity...</a>
            </div>
          </div>
	  
     <div class="row">
	<div class="col-lg-4">
          <!-- Example Pie Chart Card-->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-pie-chart"></i> Average Light Level Today</div>
            <div class="card-body">
              <canvas id="myPieChart" width="100%" height="100"></canvas>
            </div>
          </div>
        </div>
	<div class="col-lg-4">
          <!-- Example Pie Chart Card-->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-pie-chart"></i> Average Moisture Level Today</div>
            <div class="card-body">
              <canvas id="myPieChart2" width="100%" height="100"></canvas>
            </div>
          </div>
        </div>
	<div class="col-lg-4">
          <!-- Example Bar Chart Card-->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-bar-chart"></i> Alarms Today</div>
            <div class="card-body">
              <canvas id="myBarChart" width="100" height="100"></canvas>
            </div>
          </div>
        </div>


      </div>


  
	  

	  
	  





	  
      <!-- Example DataTables Card-->
	  	<?php
		include_once 'config.php';
		date_default_timezone_set('America/Toronto');	
		$dateToday = date("Y-m-d");

		// Create connection
		$conn = mysqli_connect($servername, $username, $password, $database);

		$sql="SELECT * FROM activitylog WHERE username='$login_session' AND datetime LIKE '%{$dateToday}%'; ";  

		$result = mysqli_query($conn, $sql);
		
		if (mysqli_num_rows($result) > 0) {
			
			echo "<div class='card mb-3'>" ;
			echo "<div class='card-header'>" ;
			echo "<i class='fa fa-table'></i> Today's Activity Log</div>" ; 
			echo "<div class='card-body'>" ; 
			echo "<div class='table-responsive'>" ; 
			echo "<table class='table table-bordered' id='dataTable' width='100%' cellspacing='0'>" ; 
			echo "<thead>" ; 
			echo "<tr>" ; 
			echo "<th>Date & Time</th>" ; 
			echo "<th>Sensor</th>" ; 
			echo "<th>Event</th>" ; 
			echo "</tr>" ; 
			echo "</thead>" ; 
			echo "<tfoot>" ; 
			echo "<tr>" ; 
			echo "<th>Date & Time</th>" ; 
			echo "<th>Sensor</th>" ; 
			echo "<th>Event</th>" ; 
			echo "</tr>" ; 
			echo "</tfoot>" ;
			echo "<tbody>" ;	
			
			
			//table content LOOP BEGINS here
			while($row = mysqli_fetch_assoc($result))
			{
			   $record=sprintf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>\n", 
					   $row["datetime"], $row["sensorname"], $row["activity"]); 
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
	    
                  
                <!--
             
              
			  
                <tr>
                  <td>2017/11/29 23:39:27</td>
                  <td>Bedroom</td>
                  <td>Door Opened</td>
                </tr>
				<tr>
                  <td>2017/11/29 23:39:37</td>
                  <td>Bedroom</td>
                  <td>Door Closed</td>
                </tr>
				<tr>
                  <td>2017/11/29 23:39:45</td>
                  <td>Bedroom</td>
                  <td>Alarm ON</td>
                </tr>
				<tr>
                  <td>2017/12/29 09:39:45</td>
                  <td>Bedroom</td>
                  <td>Alarm OFF</td>
                </tr>
				<tr>
                  <td>2017/12/29 23:39:45</td>
                  <td>Bedroom</td>
                  <td>Door Opened</td>
                </tr>
				<tr>
                  <td>2017/12/29 23:41:45</td>
                  <td>Bedroom</td>
                  <td>Door Closed</td>
                </tr>
				<tr>
                  <td>2017/12/29 23:45:45</td>
                  <td>Bedroom</td>
                  <td>Alarm ON</td>
                </tr>
                
              
			  
			  -->
			  
			  
            
          
        
        
      
	  
	  
		</div>
    <!-- /.container-fluid-->
	</div>
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
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
	
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-charts.js"></script>
	

	<?php
			include_once 'config.php';
			date_default_timezone_set('America/Toronto');	
			$dateToday = date("Y-m-d");
			
	

			// Create connection
			$conn = mysqli_connect($servername, $username, $password, $database);
			

			
			$sql="SELECT AVG(lightlevel) FROM `activitylog` WHERE activity='Sensor Readings' AND datetime LIKE '%{$dateToday}%' AND username='$login_session'"; 

			$result = mysqli_query($conn, $sql);
			

			if (mysqli_num_rows($result) > 0) {
				
				echo "<script>" ; 
				echo "var ctx = document.getElementById('myPieChart');"  ; 
				 
				$row = mysqli_fetch_row($result);
				 				
				$maxLightLevel = 255;				
				$pieChartData=sprintf("var myPieChart = new Chart(ctx, {type: 'pie',data: {labels: ['Light', 'Dark'],datasets: [{data: [%s, %s],backgroundColor: ['#ffff88', '#2d2d2d'],}],},});", 
						   $row[0], ($maxLightLevel - $row[0]));
				   echo $pieChartData;

				echo "</script>" ; 
								
			}
					
			
	?>

	<?php
			include_once 'config.php';
			date_default_timezone_set('America/Toronto');	
			$dateToday = date("Y-m-d");
			
	

			// Create connection
			$conn = mysqli_connect($servername, $username, $password, $database);
			

			
			$sql="SELECT AVG(moisturelevel) FROM `activitylog` WHERE activity='Sensor Readings' AND datetime LIKE '%{$dateToday}%' AND username='$login_session'"; 

			$result = mysqli_query($conn, $sql);
			

			if (mysqli_num_rows($result) > 0) {
				
				echo "<script>" ; 
				echo "var ctx = document.getElementById('myPieChart2');"  ; 
				 
				$row = mysqli_fetch_row($result);
				 				
				$maxLightLevel = 255;				
				$pieChartData=sprintf("var myPieChart = new Chart(ctx, {type: 'pie',data: {labels: ['Moist', 'Dry'],datasets: [{data: [%s, %s],backgroundColor: ['#007bff', '#2d2d2d'],}],},});", 
						   $row[0], ($maxLightLevel - $row[0]));
				   echo $pieChartData;

				echo "</script>" ; 
								
			}
					
			
	?>

<?php
			include_once 'config.php';
			date_default_timezone_set('America/Toronto');	
			$dateToday = date("Y-m-d");
			
		

			// Create connection
			$conn = mysqli_connect($servername, $username, $password, $database);

			
			$sql="SELECT count(activity) AS alarm_count FROM activitylog WHERE activity='alarm triggered' AND datetime LIKE '%{$dateToday}%' AND username='$login_session'"; 

			$result = mysqli_query($conn, $sql);

			if (mysqli_num_rows($result) > 0) {
				
				echo "<script>" ; 
				echo "var ctx = document.getElementById('myBarChart');"  ; 
				 
				$row = mysqli_fetch_row($result);
				 				
				$barChartData=sprintf("var myLineChart = new Chart(ctx, {type: 'bar',data: {labels: ['Alarms'],datasets: [{label: 'Alarms',backgroundColor: 'rgba(128,0,0,1)',borderColor: 'rgba(2,117,216,1)',data: [%s],}],},options: {scales: {xAxes: [{time: {unit: 'month'},gridLines: {display: false},ticks: {maxTicksLimit: 6}}],yAxes: [{ticks: {min: 0,max: 10,maxTicksLimit: 5},gridLines: {display: true}}],},legend: {display: false}}});", 
						   $row[0]);
				   echo $barChartData;

				echo "</script>" ; 
								
			}
					
			
	?>


		
  </div>
</body>

</html>
