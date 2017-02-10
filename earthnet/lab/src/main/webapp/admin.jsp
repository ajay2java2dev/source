<%--
  Created by IntelliJ IDEA.
  User: menonka
  Date: 6/15/2016
  Time: 1:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>PLab-Offers</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="bower_components/bootstrap/dist/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css">
<link href="bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css">
<link
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"
	type="text/css">
<link rel="stylesheet" href="slimmenu/dist/css/slimmenu.min.css"
	type="text/css">
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
     -->
<script src="slimmenu/dist/js/jquery.slimmenu.min.js"></script>
<!--     <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script> -->
<script src="dist/js/sb-admin-2.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<!--     <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> -->
<script src="js/plab_new.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script type="text/javascript" language="javascript">
	google.charts.load('current', {
		packages : [ 'corechart', 'line' ]
	});

	function activaTab(tab) {
		$('.nav-tabs a[href="#' + tab + '"]').tab('show');
	};

	function updateByTime(instanceId) {
		var timeEditted = prompt("Enter time : ");
		if (timeEditted === "" || timeEditted === null) {
			alert("Please enter reporting info in given format (see example on prompt)");
		} else {
			$.post(hostURl + "/admin/service/classification/bytime/"
					+ instanceId + "/" + timeEditted, "", function(data) {
				alert('Time updated to :' + data);
			});
		}
	};

	function updateBySpeed(instanceId) {
		var speedEditted = prompt("Enter speed :");
		if (speedEditted === "" || speedEditted === null) {
			alert("Please enter reporting info in given format (see example on prompt)");
		} else {
			$.post(hostURl + "/admin/service/qos/" + instanceId + "/"
					+ speedEditted, "", function(data) {
				alert('Speed updated to :' + data);
			});
		}
	};

	function updateByReporting(instanceId) {
		var volumeEditted = prompt("Enter volume : ");
		if (volumeEditted === "" || volumeEditted === null) {
			alert("Please enter reporting info in given format (see example on prompt)");
		} else {
			$.post(hostURl + "/admin/service/reporting/" + instanceId + "/"
					+ volumeEditted, "", function(data) {
				alert('Volume updated to :' + data);
			});
		}
	};

	function getOffer() {
		$("body").toggleClass("wait");
		var offerId = $("#offerId").val();

		if (offerId === "") {
			$("body").toggleClass("wait");
			alert("Invalid offer id !")
		} else {
			$("body").toggleClass("wait");
			$.get(hostURl + "/offer?id=" + offerId, function(data) {
				//clear contents
				$("#offerTableContentDivNewNew table").remove();
				//make json response
				var parsedJson = JSON.parse(data);
				createJsonToComplexTable(parsedJson);
			});
		}
	};

	var offersDataTable = null;
	var offerInstancesDataTable = null;
	var serviceInstancesDataTable = null;
	var offerInstanceIdSelected = null;
	var custFeedBackDataTable = null;
	var custHistoryDataTable = null;
	var custJobsDataTable = null;
	var invitedCustDataTable = null;
	var usageDataTable = null;

	var offerIdSelected = null;
	var offerInstanceIdSelected = 0;
	var mdnSelected = null;
	var custIdSelected = '';
	var acctNumSelected = '';

	function getOffers() {
		$("#offerInstancesTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		$("#usageCanvasDiv").hide();
		offerIdSelected = null;
		offerInstanceIdSelected = 0;
		mdnSelected = null;
		custIdSelected = '';
		acctNumSelected = '';
		document.getElementById("mdn").value = '';
		//$("body").toggleClass("wait");
		$
				.get(
						hostURl + "/admin/offers",
						function(offerData) {
							//$("body").toggleClass("wait");
							$("#offerTableContentDivNew").show();
							if (offersDataTable !== null) {
								offersDataTable.destroy();
							}

							//var xdata = JSON.parse('[{"offerId" : 197,"name" : "NEW OFFER1","charge" : 0,"startDate" : "2016-06-23","endDate" : "2016-06-23","state" : "DRAFT"}]');
							var xdata = JSON.parse(offerData);
							var count = 1;
							offersDataTable = $(
									'#offerTableContentDivNew table')
									.DataTable(
											{
												data : xdata,
												columns : [
														{
															data : 'offerId'
														},
														{
															data : 'name',
															render : function(
																	data, type,
																	full, meta) {
																return '<a onclick="getOfferInstances('
																		+ full.offerId
																		+ ');">'
																		+ data
																		+ '</a>';
															}
														},
														{
															data : 'totalActiveCust'
														},
														{
															data : 'totalInvitedCust'
														},
														{
															data : 'startDate'
														},
														{
															data : 'endDate'
														},
														{
															data : 'state'
														},
														{
															data : 'offerId',
															render : function(
																	data, type,
																	full, meta) {
																return '<a onclick="javascript:getFeedback('
																		+ data
																		+ ',\'offer\',\''
																		+ full.name
																		+ '\');">View</a>';
															}
														} ],
												"deferRender" : true
											});
							//                createJsonToComplexTable(parsedJson);
						});
	};

	//[{"dateCreated":"2016-06-27","usage":"0","mdn":"7818795596","offerName":"0"

	function getOfferInstances(offerId) {
		$("#offerTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		activaTab("offer-instance")
		//$("body").toggleClass("wait");var offerIdSelected = null;
		offerInstanceIdSelected = 0;
		mdnSelected = null;
		custIdSelected = '';
		acctNumSelected = '';
		var mdn = document.getElementById("mdn").value;
		if (offerId !== null && offerId !== '') {
			reqURL = "/admin/offerinstances?offerId=" + offerId;
			if (mdn !== null && mdn !== '') {
				reqURL = reqURL + "&mdn=" + mdn;
			}
		} else {
			reqURL = "/admin/offerinstances"
			if (mdn !== null && mdn !== '') {
				reqURL = reqURL + "?mdn=" + mdn;
			}
		}

		if (offerInstancesDataTable !== null) {
			offerInstancesDataTable.destroy();
		}

		$("#offerInstancesTableContentDivNew").show();
		offerInstancesDataTable = $('#offerInstancesTableContentDivNew table')
				.DataTable(
						{
							'ajax' : hostURl + reqURL,
							"processing" : true,
							'serverSide' : true,
							'ordering' : false,
							'searching' : false,
							columns : [
									{
										"data" : 'offerInstanceId'
									},
									{
										"data" : 'offerName',
										render : function(data, type, full,
												meta) {
											return '<a onclick="javascript:getServiceInstances('
													+ full.offerInstanceId
													+ ');">' + data + '</a>';
										}
									},
									{
										"data" : 'mdn'
									},
									{
										"data" : 'dateCreated'
									},
									{
										"data" : 'endTmstamp',
										render : function(data, type, full,
												meta) {
											if (full.expired === true) {
												return data;
											} else {
												return 'Active';
											}
										}
									},
									{
										"data" : 'offerId',
										render : function(data, type, full,
												meta) {
											return '<a onclick="javascript:displayUsage('
													+ full.offerInstanceId
													+ ',\'\',\'\');">'
													+ 'View'
													+ '</a>';
										}
									},
									{
										"data" : 'offerInstanceId',
										render : function(data, type, full,
												meta) {
											return '<a onclick="javascript:getCustomerHistory('
													+ data + ');">View</a>';
										}
									},
									{
										"data" : 'mdn',
										render : function(data, type, full,
												meta) {
											return '<a onclick="javascript:getFeedback('
													+ data
													+ ',\'mdn\',\''
													+ full.offerName
													+ '\');">View</a>';
										}
									},
									{
										"data" : '',
										render : function(data, type, full,
												meta) {
											return '<a onclick="javascript:getCustomerJobs('
													+ full.custIdNo
													+ ','
													+ full.acctNo
													+ ');">View</a>';
										}
									} ],
							"fnRowCallback" : function(nRow, aData,
									iDisplayIndex, iDisplayIndexFull) {
								if (aData.expired === true) {
									$('td', nRow).css('background-color',
											'LightGrey');
								}
							},
							"order" : [ [ 4, "desc" ], [ 2, "desc" ] ],
							"deferRender" : true
						});
		$('#example2 tbody').on('click', 'tr', function() {
			var rowData = offerInstancesDataTable.row(this).data();
			offerInstanceIdSelected = rowData.offerInstanceId;
			mdnSelected = rowData.mdn;
			custIdSelected = rowData.custIdNo;
			acctNumSelected = rowData.acctNo;
		});
	};

	function hideAll() {
		$("#offerTableContentDivNew").hide();
		$("#offerInstancesTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		$("#usageCanvasDiv").hide();
	};

	function getServiceInstances(offerInstanceId) {
		//$("body").toggleClass("wait");
		offerInstanceIdSelected = offerInstanceId;
		$("#offerTableContentDivNew").hide();
		$("#offerInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		$("#usageCanvasDiv").hide();
		if (offerInstanceId === "" || offerInstanceId === null) {
			//$("body").toggleClass("wait");
			alert("No offer Instance available");
			getOfferInstances('');
			activaTab("offer-instance");
		} else {
			$
					.get(
							hostURl + "/admin/serviceinstances/"
									+ offerInstanceId,
							function(offerData) {
								//$("body").toggleClass("wait");
								activaTab("service-instance");
								$("#serviceInstancesTableContentDivNew").show();
								if (serviceInstancesDataTable !== null)
									serviceInstancesDataTable.destroy();
								//var xdata = JSON.parse('[{"offerId" : 197,"name" : "NEW OFFER1","charge" : 0,"startDate" : "2016-06-23","endDate" : "2016-06-23","state" : "DRAFT"}]');
								var xdata = JSON.parse(offerData);
								serviceInstancesDataTable = $(
										'#serviceInstancesTableContentDivNew table')
										.DataTable(
												{
													data : xdata,
													/*
													 ajax: {
													 url: hostURl + "/admin/offers",
													 dataSrc: ''
													 },*/
													columns : [
															{
																data : 'name'
															},
															{
																data : 'mdn'
															},
															{
																data : 'counter'
															},
															{
																data : 'spoID'
															},
															{
																data : 'vispServiceInstanceId'
															},
															{
																data : 'vispServiceID'
															} ],
													"fnRowCallback" : function(
															nRow, aData,
															iDisplayIndex,
															iDisplayIndexFull) {
														if (aData.endTmstamp !== "9999-12-31 11:59:59") {
															$('td', nRow)
																	.css(
																			'background-color',
																			'LightGrey');
														}
													},
													order : [ [ 1, 'asc' ] ],
													"deferRender" : true
												});
							});
		}
	};

	$.put = function(url, data, callback) {

		if ($.isFunction(data)) {
			callback = data, data = {}
		}

		return $.ajax({
			url : url,
			type : 'POST',
			success : callback,
			data : data,
			contentType : 'application/json'
		});
	};

	var instanceId;

	///offerinstance/terminate/{offerInstanceId}
	function termianteOffer(offerInstanceId) {
		$.put(hostURl + "/offerInstance/terminate/" + offerInstanceId, "",
				function(data) {
					//                       var response = $.parseJSON(data);
					//                       alert(data);
					//                        createJsonToComplexTable(response);
				});
	};

	function acceptOffer(offerId) {
		//var offerId = $("#offerId").val();
		//var mdn = $("#mdn").val();

		var mdn = prompt("Please enter MDN");
		//$("#offerTableContentDivNew table").remove();

		if (offerId === "" || mdn === "") {
			alert('Please enter offer ID & Valid MDN !')
		} else {
			$.put(hostURl + "/offer/" + offerId + "/" + mdn + "/terms/accept",
					"", function(data) {
						instanceId = data;
						$.put(hostURl + "/offer/" + instanceId + "/accept", "",
								function(data) {
									var response = $.parseJSON(data);
									alert(data);
									createJsonToComplexTable(response);
								});
					});
		}
	};

	function getOfferInstanceUsage() {
		var offerInstanceID = $("#offerId").val();
		var mdn = $("#mdn").val();
		if (offerInstanceID === "") {
			alert('Please Enter Offer Instance ID')
		} else {
			$.post(hostURl + "/offer/usage/" + offerInstanceID, "", function(
					data) {
				$("#offerTableContentDivNew table").remove();
				var response = $.parseJSON(data);
				createJsonToComplexTable(response);
			});
		}
	};

	function updateOffer(speed) {
		var offerInstanceID = $("#offerId").val();
		var mdn = $("#mdn").val();
		if (offerInstanceID === "") {
			alert('Please Enter Offer Instance ID')
		} else {
			$.post(hostURl + "/offer/update/" + offerInstanceID + "/" + speed,
					"", function(data) {
						$("#offerTableContentDivNew table").remove();
						var response = $.parseJSON(data);
						createJsonToComplexTable("Visp Service ID : "
								+ response);
					});
		}
	};

	function getOfferInstance() {
		var offerId = $("#offerId").val();
		var mdn = $("#mdn").val();
		$("#offerTableContentDivNew table").remove();
		if (offerId === "" || mdn === "") {
			alert("Invalid offer id or MDN passed !")
		} else {
			$.get(hostURl + "/offer/instance?offerId=" + offerId + "&mdn="
					+ mdn, function(data) {
				$("#offerTableContentDivNew table").remove();
				var response = $.parseJSON(data);
				createJsonToComplexTable(response);
			});
		}
	};

	function getCustomerHistory(oiId) {
		//$("body").toggleClass("wait");
		$("#offerTableContentDivNew").hide();
		$("#offerInstancesTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").show();
		$("#customerFeedBackTableContentDivNew").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#usageCanvasDiv").hide();
		document.getElementById('dispMDN-ch').innerHTML = "MDN:";
		activaTab("customer-history");
		$
				.get(
						hostURl + "/customer/history/" + oiId,
						function(data) {
							//$("body").toggleClass("wait");
							var res = JSON.parse(data);
							document.getElementById('dispMDN-ch').innerHTML = "MDN: "
									+ mdnSelected;
							if (custHistoryDataTable !== null)
								custHistoryDataTable.destroy();

							custHistoryDataTable = $(
									'#customerHistoryTableContentDivNew table')
									.DataTable(
											{
												data : res,
												columns : [
														{
															data : 'endTmstamp',
															render : function(
																	data, type,
																	full, meta) {
																if (!full
																		.hasOwnProperty('choiceInstance')) {
																	return full['choice']['title'];
																} else {
																	return full['choiceInstance']['choice']['title'];
																}
															}
														},
														{
															data : 'selected',
															render : function(
																	data, type,
																	full, meta) {
																if (data === true) {
																	return 'Yes';
																} else {
																	return 'No';
																}
															}
														},
														{
															data : 'dateCreated',
															render : function(
																	data, type,
																	full, meta) {
																if (full
																		.hasOwnProperty('choiceInstance')) {
																	return data;
																} else {
																	if (full.endTmstamp === '9999-12-31 11:59:59 PM') {
																		return 'Active';
																	} else {
																		return full.endTmstamp;
																	}
																}
															}
														} ],
												order : [ [ 2, "desc" ] ],
												"deferRender" : true
											});
						});
	};

	function getCustomerFeedback(mdn) {
		//$("body").toggleClass("wait");
		$("#offerTableContentDivNew").hide();
		$("#offerInstancesTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").show();
		$("#usageCanvasDiv").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		activaTab("customer-feedback");
		$.get(hostURl + "/customer/feedback?mdn=" + mdn, function(data) {
			//$("body").toggleClass("wait");
			$("#customerFeedBackTableContentDivNew").show();
			if (custFeedBackDataTable !== null)
				custFeedBackDataTable.destroy();

			var feedBackData = JSON.parse(data);
			custFeedBackDataTable = $(
					'#customerFeedBackTableContentDivNew table').DataTable({
				data : feedBackData,
				columns : [ {
					data : 'custom_var',
					render : function(data, type, full, meta) {
						return data.loggedInMdn;
					}
				}, {
					data : 'custom_var',
					render : function(data, type, full, meta) {
						return data.osName;
					}
				}, {
					data : 'custom_var',
					render : function(data, type, full, meta) {
						return data.osVersion;
					}
				}, {
					data : 'custom_var',
					render : function(data, type, full, meta) {
						return data.appVersion;
					}
				}, {
					data : 'overall'
				}, {
					data : 'comments'
				}, {
					data : 'offerName'
				}, {
					data : 'dateCreated'
				}, {
					data : 'replied',
					render : function(data, type, full, meta) {
						if (data === true) {
							return 'Yes';
						} else {
							return 'No';
						}
					}
				} ]
			});
		});
	};

	function getFeedback(request, type, givenOfferName) {
		//$("body").toggleClass("wait");
		$("#offerTableContentDivNew").hide();
		$("#offerInstancesTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#usageCanvasDiv").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").show();
		activaTab("customer-feedback");
		var requestURL = '';

		if (type == 'mdn') {
			requestURL = "/customer/feedback?mdn=" + request;
			mdnSelected = request;
		} else if (type == 'offer') {
			requestURL = "/offer/feedback/" + request;
			offerIdSelected = request;
		} else {
			requestURL = "/customer/feedback?mdn=0";
		}

		$
				.get(
						hostURl + requestURL,
						function(data) {
							// $("body").toggleClass("wait");
							if (custFeedBackDataTable !== null)
								custFeedBackDataTable.destroy();
							var res = JSON.parse(data);
							custFeedBackDataTable = $(
									'#customerFeedBackTableContentDivNew table')
									.DataTable(
											{
												data : res,
												columns : [
														{
															data : 'offerName',
															render : function(
																	data, type,
																	full, meta) {

																if (givenOfferName === ''
																		|| givenOfferName === null) {
																	return data;
																}
																return givenOfferName;
															}
														},
														{
															data : 'custom_var',
															render : function(
																	data, type,
																	full, meta) {
																return data.loggedInMdn;
															}
														},
														{
															data : 'custom_var',
															render : function(
																	data, type,
																	full, meta) {
																return data.osName;
															}
														},
														{
															data : 'custom_var',
															render : function(
																	data, type,
																	full, meta) {
																return data.osVersion;
															}
														},
														{
															data : 'custom_var',
															render : function(
																	data, type,
																	full, meta) {
																return data.appVersion;
															}
														},
														{
															data : 'overall'
														},
														{
															data : 'comments',
															render : function(
																	data, type,
																	full, meta) {
																return '<p align="center">'
																		+ data
																		+ '</p>';
															}
														},
														{
															data : 'referer',
															render : function(
																	data, type,
																	full, meta) {
																return 'Portal';
															}
														},
														{
															data : 'dateCreated'
														},
														{
															data : 'comments',
															render : function(
																	data, type,
																	full, meta) {
																return '<a href="#replyPopup" data-rel="popup" data-position-to="window" data-transition="fade" onclick="javascript:replyToComment('
																		+ full.ccid
																		+ ',\''
																		+ data
																		+ '\');">Reply</a>';
																if (data === null
																		|| data === '') {
																	return '<a onclick="javascript:replyToComment('
																			+ full.ccid
																			+ ',\''
																			+ data
																			+ '\');">Reply</a>';
																} else {
																	return '<p align="center">'
																			+ data
																			+ '</p>';
																}
															}
														} ],
												"deferRender" : true
											})
						})
	};

	function replyToComment(ccid, commentString) {
		//Implement This
		document.getElementById('commentArea').InnerHTML = commentString;
		document.getElementById('commentArea').readOnly = true;
		document.getElementById('replyButton').onclick = function() {
			sendReply(ccid);
		};
		document.getElementById('replyPopup').style.display = 'block';
	}

	function sendReply(ccid) {
		replyComment = document.getElementById('replyArea').value;
		alert(replyComment);
		$.post(hostURL + 'feedback/submit/reply/' + ccid + '&reply='
				+ replyComment, function(data) {
			alert(data);
		});
		clearPopup();
	};

	function clearPopup() {
		document.getElementById('commentArea').readOnly = false;
		document.getElementById('commentArea').InnerHTML = '';
		document.getElementById('replyArea').InnerHTML = '';
		document.getElementById('replyButton').onclick = null;
	}

	function getCustomerJobs(custid, acctno) {
		//$("body").toggleClass("wait");
		$("#offerTableContentDivNew").hide();
		$("#offerInstancesTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").hide();
		$("#usageCanvasDiv").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").show();
		activaTab("customer-jobs");
		var url;
		if (custid == '' || acctno == '') {
			url = hostURl + '/admin/jobs';
		} else {
			url = hostURl + '/admin/jobs/' + custid + '/' + acctno;
		}
		$.get(url, function(data) {
			//$("body").toggleClass("wait");
			if (custJobsDataTable !== null)
				custJobsDataTable.destroy();
			var jobData = JSON.parse(data);
			custJobsDataTable = $('#customerJobsTableContentDivNew table')
					.DataTable({
						data : jobData,
						columns : [ {
							data : 'mdn'
						}, {
							data : 'custId'
						}, {
							data : 'custAccNo'
						}, {
							data : 'displayJobType'
						}, {
							data : 'scheduledTime'
						} ]
					});
		});
	};

	// function for fetching and displaying invited customer details
	function getInvitedCustomer(offerId) {
		//$("body").toggleClass("wait");
		$("#offerTableContentDivNew").hide();
		$("#offerInstancesTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").hide();
		$("#usageCanvasDiv").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#invitedCustomerTableContentDivNew").show();
		activaTab("invited-customer");
		var url = hostURl + '/admin/offer?id=' + offerId + '';
		$.get(url,
				function(data) {
					//$("body").toggleClass("wait");
					if (invitedCustDataTable !== null)
						invitedCustDataTable.destroy();
					var jobData = JSON.parse(data);
					invitedCustDataTable = $(
							'#invitedCustomerTableContentDivNew table')
							.DataTable({
								data : jobData,
								columns : [ {
									data : 'mdn'
								}, {
									data : 'plStartDate'
								}, {
									data : 'inviteExpiryDate'
								}, {
									data : 'offerAccepted'
								}, {
									data : 'pilotStartDate'
								}, {
									data : 'pilotEndDate'
								}, {
									data : 'exitedDate'
								}, {
									data : 'featureStatus'
								} ]
							});
				});
	};

	function displayUsage(offerInstanceId, start, end) {
		//$("body").toggleClass("wait");
		offerInstanceIdSelected = offerInstanceId;
		$("#offerTableContentDivNew").hide();
		$("#offerInstancesTableContentDivNew").hide();
		$("#serviceInstancesTableContentDivNew").hide();
		$("#customerHistoryTableContentDivNew").hide();
		$("#customerFeedBackTableContentDivNew").hide();
		$("#customerJobsTableContentDivNew").hide();
		$("#invitedCustomerTableContentDivNew").hide();
		$("#usageTableContentDivNew").show();
		activaTab("usages");
		var reqURL = null;
		if (start === null || start === "" || end === null || end === "") {
			reqURL = "/offerinstance/" + offerInstanceId + "/usage";
		} else {
			reqURL = "/offerinstance/" + offerInstanceId
					+ "/usage?startTimestamp=" + start + "&endTimestamp=" + end;
		}
		bodyWidth = document.getElementById("body").offsetWidth;
		$.get(hostURl + reqURL, function(data) {
			//$("body").toggleClass("wait");
			var res = JSON.parse(data);
			document.getElementById('dispMDN-usage').innerHTML = 'MDN: '
					+ mdnSelected;
			if (usageDataTable !== null) {
				usageDataTable.destroy();
			}

			usageDataTable = $("#usageTableContentDivNew table").DataTable({
				data : res,
				columns : [ {
					data : 'firsttimeofUsage'
				}, {
					data : 'lasttimeofUsage'
				}, {
					data : 'deltaBytes',
					render : function(data, type, full, meta) {
						return "" + data / 1000000000 + " GB";
					}
				}, {
					data : 'ratingGroup',
				}, {
					data : 'dateCreated',
				} ],
				order : [ [ 0, 'asc' ] ],
				"deferRender" : true
			});
			var data = new google.visualization.DataTable();
			data.addColumn('timeofday', 'Time of Day');
			data.addColumn('number', 'Usage (VISP)');
			var data2 = new google.visualization.DataTable();
			data2.addColumn('timeofday', 'Time of Day');
			data2.addColumn('number', 'Usage (DM)');
			for (var i = 0; i < res.length; i++) {
				data.addRows([
						[ new Date(res[i]['fisttimeofUsage']),
								res[i]['totalBytes'] - res[i]['deltaBytes'] ],
						[ new Date(res[i]['lasttimeofUsage']),
								res[i]['totalBytes'] ], ]);
			}
			var options = {
				title : 'Usage Recieved per hour',
				height : 400,
				width : bodyWidth * .875,
				hAxis : {
					title : 'Time of Day',
					format : 'h:mm a',
					viewWindow : {
						min : [ 0, 0, 0 ],
						max : [ 23, 59, 0 ]
					}
				},
				vAxis : {
					title : 'Amount of Usage(units)'
				}
			};
			var chart = new google.visualization.LineChart(document
					.getElementById('usageCanvas'));
			chart.draw(data, options);
		});
	};
</script>
</head>

<body id="body">
	<div id="wrapper" data-role="page">
		<!-- Navigation -->

		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0" data-role="header">
			<div class="navbar-header">
				<!--             <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> -->
				<!--                 <span class="sr-only">Toggle navigation</span> -->
				<!--                 <span class="icon-bar"></span> -->
				<!--                 <span class="icon-bar"></span> -->
				<!--                 <span class="icon-bar"></span> -->
				<!--             </button> -->
				<a class="navbar-brand" href="/admin.jsp">PLab Customer
					Care</a>
			</div>
			<!--
	        <br>
	        <div align="right">
	        	<a href="/logout">SignOut</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        </div>		
	        -->	
			<!-- /.navbar-top-links -->
		</nav>

		<div id="main" data-role="main" class="ui-content">
			<div id="offerTableTemplateDiv">
				<div class="panel-body">
					<div class="col-lg-15">
						<div class="panel panel-primary">
							<div class="panel-heading">Pricing Lab</div>
							<!-- /.panel-heading -->
							<div class="panel-body" style="max-width: 100%;">
								<!-- Nav tabs -->
								<ul class="nav nav-tabs">
									<li class="active"><a href="#offer"
										onclick="javascript:getOffers();" data-toggle="tab">Pilot
											Offers</a></li>
									<li><a href="#offer-instance"
										onclick="javascript:getOfferInstances(offerIdSelected);"
										data-toggle="tab">Enrolled Customers</a></li>
									<!--                                 <li><a href="#service" data-toggle="tab">Service</a> -->
									<!--                                 </li> -->
									<!-- New Tab to dispaly invited customer for specific offer id -->
									<li><a href="#invited-customer"
										onclick="javascript:getInvitedCustomer('1021');"
										data-toggle="tab">Invited Customers</a></li>
									</a>
									</li>
									<li><a href="#service-instance"
										onclick="javascript:getServiceInstances(offerInstanceIdSelected);"
										data-toggle="tab">Services</a></li>
									<li><a href="#usages"
										onclick="displayUsage(offerInstanceIdSelected,'','');"
										data-toggle="tab">Usage</a></li>
									<li><a href="#customer-history"
										onclick="javascript:getCustomerHistory(offerInstanceIdSelected);"
										data-toggle="tab">Choice History</a></li>
									<li><a href="#customer-feedback"
										onclick="javascript:getFeedback('','','');" data-toggle="tab">Feedback</a></li>
									<li><a href="#customer-jobs"
										onclick="javascript:getCustomerJobs(custIdSelected,acctNumSelected);"
										data-toggle="tab">Scheduled Jobs</a></li>
								</ul>
								<hr>
								<!-- Tab panes -->
								<div class="tab-content">
									<div class="tab-pane fade in active" id="offer">
										<div id="offerTableContentDivNew">
											<table id="example" class="display" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th>Offer ID</th>
														<th>Name</th>
														<th>Active Custs</th>
														<th>Invited Custs</th>
														<th>Start Date</th>
														<th>End Date</th>
														<th>Status</th>
														<th>Feedback</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<div class="tab-pane fade" id="offer-instance">
										<p>
											<b>MDN : </b> <input type="text" name="mdn" id="mdn" value=""
												maxlength="10" /> &nbsp; &nbsp; <input type="button"
												value="Search"
												onclick="javascript:getOfferInstances(offerIdSelected);" />
										</p>
										<div id="offerInstancesTableContentDivNew">
											<table id="example2" class="display" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th>Offer Instance ID</th>
														<th>Offer Name</th>
														<th>MDN</th>
														<th>Enrolled Date</th>
														<th>End Date</th>
														<th>Usage</th>
														<th>History</th>
														<th>Feedback</th>
														<th>Scheduled Jobs</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<div class="tab-pane fade" id="service"></div>
									<div class="tab-pane fade" id="service-instance">
										<div id="serviceInstancesTableContentDivNew">
											<table id="example3" class="display" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th>Service Name</th>
														<th>MDN</th>
														<th>Usage</th>
														<th>SPO ID</th>
														<th>VispServiceInstanceID</th>
														<th>VispServiceID</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<div class="tab-pane fade" id="usages">
										<h3 id="dispMDN-usage" style="text-align: right;">MDN:</h3>
										<div id="usageTableContentDivNew">
											<table id="example4" class="display" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th>Start Time</th>
														<th>End Time</th>
														<th>Data Used</th>
														<th>Rating Group</th>
														<th>Created Date</th>
														
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<div class="tab-pane fade" id="customer-history">
										<h3 id="dispMDN-ch" style="text-align: right;">MDN:</h3>
										<div id="customerHistoryTableContentDivNew">
											<table id="example5" class="display" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th>Choice Title</th>
														<th>Selected</th>
														<th>Switch Off Date</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<div class="tab-pane fade" id="customer-feedback">
										<div id="customerFeedBackTableContentDivNew">
											<table id="example6" class="display" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th width="15%">Offer</th>
														<th width="8%">MDN</th>
														<th width="5%">OS Name</th>
														<th width="5%">OS Version</th>
														<th width="5%">App Version</th>
														<th width="5%">Rating</th>
														<th width="30%">Comments</th>
														<th width="5%">Source</th>
														<th width="8%">Date</th>
														<th width="4%">Reply</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>

									<div class="tab-pane fade" id="customer-jobs">
										<div id="customerJobsTableContentDivNew">
											<table id="example7" class="display" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th>MDN</th>
														<th>Cust Id</th>
														<th>Account Number</th>
														<th>Job Type</th>
														<th>Scheduled Time</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>

									<div class="tab-pane fade" id="invited-customer">
										<div id="invitedCustomerTableContentDivNew">
											<table id="example8" class="display" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th>MDN</th>
														<th>Invited Date</th>
														<th>Invited Expiry Date</th>
														<th>Offer Accepted</th>
														<th>Pilot Start Date</th>
														<th>Pilot End Date</th>
														<th>Exited Date</th>
														<th>Feature Status</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>

								</div>
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
				</div>
			</div>
			<!-- <div data-role="popup" id="replyPopup" data-overlay-theme="b" style="height:1000%; width:200%; display:none;">
		      <a onclick="clearPopup()" href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>
	      <div data-role="header">
	        <h1>Reply:</h1>
	      </div>
	
	      <div data-role="main" >
	        <textarea id="commentArea"></textarea>
	        <p>Type your reply here:</p>
			<textarea id="replyArea"></textarea>
			<button id="replyButton" type="button" value="Reply" >Reply</button>
	      </div>
	
	      <div data-role="footer">
	        <h1></h1>
	      </div>
	 	</div> -->
		</div>
	</div>
	<script type="text/javascript">
		javascript: getOffers();
	</script>
</body>
</html>