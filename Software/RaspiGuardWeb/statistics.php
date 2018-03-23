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
  <title>RaspiGuard | Statistics</title>
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
          <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Statistics</li>
      </ol>
      <!-- Area Chart Example-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-area-chart"></i> Average Daily Moisture Levels</div>
        <div class="card-body">
          <canvas id="myAreaChart" width="100%" height="30"></canvas>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-8">
          <!-- Example Bar Chart Card-->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-bar-chart"></i> Activities per Month</div>
            <div class="card-body">
              <canvas id="myBarChart" width="100" height="50"></canvas>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <!-- Example Pie Chart Card-->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-pie-chart"></i> Average Light Level of All Time</div>
            <div class="card-body">
              <canvas id="myPieChart" width="100%" height="100"></canvas>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Your Website 2018</small>
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
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
	
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-charts.js"></script>
	<?php
			include_once 'config.php';			
		

			// Create connection
			$conn = mysqli_connect($servername, $username, $password, $database);

			
			$sql="SELECT SUM(DATE(datetime) BETWEEN '2017-11-01' AND '2018-11-30') AS Nov,
	   SUM(DATE(datetime) BETWEEN '2017-12-01' AND '2018-12-31') AS Dets,
	   SUM(DATE(datetime) BETWEEN '2018-01-01' AND '2018-01-31') AS Jan, 
       SUM(DATE(datetime) BETWEEN '2018-02-01' AND '2018-02-28') AS Feb, 
       SUM(DATE(datetime) BETWEEN '2018-03-01' AND '2018-03-31') AS Mar,
       SUM(DATE(datetime) BETWEEN '2018-04-01' AND '2018-04-30') AS Apr
		FROM `activitylog` WHERE username='$login_session'"; 

			$result = mysqli_query($conn, $sql);

			if (mysqli_num_rows($result) > 0) {
				
				echo "<script>" ; 
				echo "var ctx = document.getElementById('myBarChart');"  ; 
				 
				$row = mysqli_fetch_row($result);
				 				
				$barChartData=sprintf("var myLineChart = new Chart(ctx, {type: 'bar',data: {labels: ['November', 'December', 'January', 'February', 'March', 'April'],datasets: [{label: 'Activities',backgroundColor: 'rgba(2,117,216,1)',borderColor: 'rgba(2,117,216,1)',data: [%s, %s, %s, %s, %s, %s],}],},options: {scales: {xAxes: [{time: {unit: 'month'},gridLines: {display: false},ticks: {maxTicksLimit: 6}}],yAxes: [{ticks: {min: 0,max: 1000,maxTicksLimit: 5},gridLines: {display: true}}],},legend: {display: false}}});", 
						   $row[0], $row[1], $row[2], $row[3], $row[4], $row[5]);
				   echo $barChartData;

				echo "</script>" ; 
								
			}
					
			
	?>
	
	<?php
	
			include_once 'config.php';			
		

			// Create connection
			$conn = mysqli_connect($servername, $username, $password, $database);

			
			$sql="SELECT (
	SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-12' AND '2018-03-13' AND activity='moisture measurement' AND username='$login_session') AS d1,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-13' AND '2018-03-14' AND activity='moisture measurement' AND username='$login_session' ) AS d2,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-14' AND '2018-03-15' AND activity='moisture measurement' AND username='$login_session' ) AS d3,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-15' AND '2018-03-16' AND activity='moisture measurement' AND username='$login_session' ) AS d4,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-16' AND '2018-03-17' AND activity='moisture measurement' AND username='$login_session' ) AS d5,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-17' AND '2018-03-18' AND activity='moisture measurement' AND username='$login_session' ) AS d6,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-18' AND '2018-03-19' AND activity='moisture measurement' AND username='$login_session' ) AS d7,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-19' AND '2018-03-20' AND activity='moisture measurement' AND username='$login_session' ) AS d8,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-20' AND '2018-03-21' AND activity='moisture measurement' AND username='$login_session' ) AS d9,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-21' AND '2018-03-22' AND activity='moisture measurement' AND username='$login_session' ) AS d10,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-22' AND '2018-03-23' AND activity='moisture measurement' AND username='$login_session' ) AS d11,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-23' AND '2018-03-24' AND activity='moisture measurement' AND username='$login_session' ) AS d12,
	(SELECT AVG(moisturelevel) FROM `activitylog` WHERE datetime BETWEEN '2018-03-24' AND '2018-03-25' AND activity='moisture measurement' AND username='$login_session' ) AS d13;"; 

			$result = mysqli_query($conn, $sql);
			
			
			
			
			
			if (mysqli_num_rows($result) > 0) {
				
				echo "<script>" ; 
				echo "var ctx = document.getElementById('myAreaChart');"  ; 
				 
				$row = mysqli_fetch_row($result);
				 				
				$areaChartData=sprintf("var myLineChart = new Chart(ctx, {type: 'line',data: {labels: ['Mar 12', 'Mar 13', 'Mar 14', 'Mar 15', 'Mar 16', 'Mar 17', 'Mar 18', 'Mar 19', 'Mar 20', 'Mar 21', 'Mar 22', 'Mar 23', 'Mar 24'],datasets: [{label: 'Level',lineTension: 0.3,backgroundColor: 'rgba(2,117,216,0.2)',borderColor: 'rgba(2,117,216,1)',pointRadius: 5,pointBackgroundColor: 'rgba(2,117,216,1)',pointBorderColor: 'rgba(255,255,255,0.8)',pointHoverRadius: 5,pointHoverBackgroundColor: 'rgba(2,117,216,1)',pointHitRadius: 20,pointBorderWidth: 2,data: [%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s],}],},options: {scales: {xAxes: [{time: {unit: 'date'},gridLines: {display: false},ticks: {maxTicksLimit: 7}}],yAxes: [{ticks: {min: 0,max: 100,maxTicksLimit: 5},gridLines: {color: 'rgba(0, 0, 0, .125)',}}],},legend: {display: false}}});", 
						   $row[0], $row[1], $row[2], $row[3], $row[4], $row[5], $row[6], $row[7], $row[8], $row[9], $row[10], $row[1], $row[12]);
				   echo $areaChartData;

				echo "</script>" ; 
				
				
				
				

				
			}
	
	
	?>
	
	
	<?php
			include_once 'config.php';			
	

			// Create connection
			$conn = mysqli_connect($servername, $username, $password, $database);
			

			
			$sql="SELECT AVG(lightlevel) FROM `activitylog` WHERE activity='light measurement' AND username='$login_session'"; 

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
	
	
	
	
  </div>
</body>

</html>


