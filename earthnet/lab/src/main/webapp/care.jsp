<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>PLab Customer Care</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	
<!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="bower_components/bootstrap/dist/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet"
          type="text/css">
    <link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" type="text/css">
    <link rel="stylesheet" href="slimmenu/dist/css/slimmenu.min.css" type="text/css">
    <%--<link href="dist/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="dist/css/qunit-1.12.0.css" rel="stylesheet" type="text/css">
    <!--     <link href="dist/css/timeline.css" rel="stylesheet" type="text/css"> -->
<!--     <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"> -->
    <link href="css/plab.css" rel="stylesheet" type="text/css">

    <!-- Additional -->
    <!--     <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
        <link href="bower_components/morrisjs/morris.css" rel="stylesheet">
     -->
    <!-- JQuery Latest -->
    <script src="js/jquery-2.1.4.js"></script>
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="DataTables-1.10.12/media/js/jquery.dataTables.min.js"></script>
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <!--    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
     -->    <script src="slimmenu/dist/js/jquery.slimmenu.min.js"></script>
    <!--     <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script> -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<!--     <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> -->
    <script src="js/plab_new.js"></script>
    
    <script type="text/javascript" language="javascript">
    
    google.charts.load('current', {packages: ['corechart']});// change bar to type of chart used
    
    	var offerInstanceDataTable = null;
    	var serviceInstanceDataTable = null;
    	var usageRecordDataTable = null;
    
    	function getOfferInstanceData(){
    		if(offerInstanceDataTable !== null){
  				offerInstanceDataTable.destroy();
  			}
    		$('#offerInstanceDetailsDiv').toggle(true);
    		$('#serviceInstanceDetailsDiv').toggle(false);
    		$('#usageRecordDetailsDiv').toggle(false);
    		var mdn = document.getElementById('submitMDN').value;
    		// Log CustCare Rep || Time -- getOfferInstances for MDN
  			$.get(hostURl + "/care/offerinstance/" + mdn, function(data){
      			var res = JSON.parse(data);
      			offerInstanceDataTable = $('#offerInstanceDetailsDiv table').DataTable({
      				data: res,
      				columns: [
      					{
      						data: 'offer',
      						render: function(data,type,full,meta){
      							return data['name'];
      						}
      					},
      					{data: 'dateCreated'},
      					{
      						data: 'endTmstamp',
      						render: function(data,type,full,meta){
                        		if(full.expired === true) {  
                        			return data;
                        		}else {
                        			return 'Active';
                        		}
                        	}
      					},
      					{
      						data: 'offerInstanceId',
      						render: function(data,type,full,meta){
      							return '<a onclick="viewDetails('+ data +');">View</a>';
      						}
      					}
      				],
      				"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
                    	if(aData.expired === true) {                  
                    		$('td', nRow).css('background-color', 'LightGrey');
                    	} else {
                    		$('td', nRow).css('background-color', 'LightGreen');
                    	}
                    },
                    "order": [[2,"asc"]]
      			});
      		});
    	};
    	function viewDetails(offerInstanceId){
    		// Log CustCare Rep || Time -- viewDetials for MDN & OfferInstanceId
    		if(serviceInstanceDataTable !== null){
  				serviceInstanceDataTable.destroy();
  			}
  			if(usageRecordDataTable !== null){
  				usageRecordDataTable.destroy();
  			}
  			$('#serviceInstanceDetailsDiv').toggle(true);
  			$('#usageRecordDetailsDiv').toggle(true);
    		$.get(hostURl + "/admin/serviceinstances/" + offerInstanceId,function(data){
    			var res = JSON.parse(data);
    			serviceInstanceDataTable = $('#serviceInstanceDetailsDiv table').DataTable({
      				data: res,
      				columns: [
      					{data: 'name'},
      					{data: 'spoID'},
      					{data: 'vispServiceInstanceId'},
      					{
      						data: 'counter',
      						render: function(data,type,full,meta){
      							return data/1000000000;
      						}
      					}
      				]
      			});
    		});
    		$.get(hostURl + "/offerinstance/" + offerInstanceId + "/usage", function(data){
    			var usageRes = JSON.parse(data);
    			if(usageRes[0] !== null){
	    			usageRecordDataTable = $('#usageRecordDetailsDiv table').DataTable({
	      				data: usageRes,
	      				columns: [
	      					{data: 'firsttimeofUsage'},
	      					{data: 'lasttimeofUsage'},
	      					{
	      						data: 'serviceInstance',
	      						render: function(data,type,full,meta){
	      							return data['spoID'];
	      						}
	      					},
	      					{
	      						data: 'deltaBytes',
	      						render: function(data,type,full,meta){
	      							return data/1000000000;
	      						}
	      					}
	      				]
	      			});
	    		 	bodyWidth = document.getElementById("pageContentDiv").offsetWidth;
	    			var data = new google.visualization.DataTable();
	           	 	data.addColumn('timeofday','Time of Day');
	           	 	data.addColumn('number','Usage Recieved (MB)');
	           	 	for(var i = 0; i < usageRes.length; i++){
	           	 		var avgUsage = (usageRes[i]['deltaBytes']/1000000) /(usageRes[i]['lasttimeofusage'] - usageRes[i]['firsttimeofusage']);
	           	 		data.addRows([
	           	 			[usageRes[i]['firsttimeofusage'],avgUsage],
	           	 			[usageRes[i]['lasttimeofusage'],avgUsage]
	           	 		]);
	           	 	}
	           	 	var options = {
	 					title: 'Usage Recieved per hour',
	 					height: 400,
	 					width: bodyWidth * .875,
	 					hAxis: {
	 						title:'Time of Day',
	 						format: 'MM-DD-YYYY h:mm a',
	 					},
	 					vAxis: {
	 						title: 'Amount of Usage (MB)'
	 					}
	 				};
	           	 	var chart = google.visualization.LineChart(document.getElementById('usageCanvasDiv'));
	           	 	chart.draw(data,options);
    			}
    		});
    	};
    </script>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0" data-role="header">
	        <div class="navbar-header">
	            <a class="navbar-brand" href="care.jsp">PLab Customer Care</a>
	        </div>
	        <!-- /.navbar-top-links -->
	    </nav>
	    <div id="main" class="ui-content">
	    	<div class="panel-body">
				<div id="mdnSearchBarDiv" style="align:center; text-align:center;">
					<h3>Enter MDN:</h3>
					<input type="text" id="submitMDN">
					<div id="submitButtonDiv" style="margin-top: 15px;">
						<button id="submitMDNButton" onclick="getOfferInstanceData()">Submit</button>
					</div>
				</div>
				<div id="pageContentDiv">
					<div id="offerInstanceDetailsDiv" style="margin-top: 20px; display: none">
						<table id="example1" class="display" cellspacing="0" width="100%" style="align:center; text-align:center">
							<thead>
								<tr>
									<th>Offer Name</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Details</th>
								</tr>
							</thead>
						</table>
					</div>
					<div id="serviceInstanceDetailsDiv" style="margin-top: 20px; display: none">
						<table id="example2" class="display" cellspacing="0" width="100%" style="align: center;text-align:center">
							<thead>
								<tr>
									<th>Service Name</th>
									<th>SPO ID</th>
									<th>VispServiceInstanceID</th>
									<th>Usage (GB)</th>
								</tr>
							</thead>
						</table>
					</div>
					<div id="usageRecordDetailsDiv" style="margin-top: 20px; display: none">
						<table id="example3" class="display" cellspacing="0" width="100%" style="align: center; text-align:center">
							<thead>
								<tr>
									<th>Start Time</th>
									<th>End Time</th>
									<th>SPO ID</th>
									<th>GB Used</th>
								</tr>
							</thead>
						</table>
						<div id="usageCanvasDiv"><!-- For Google Chart --></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>