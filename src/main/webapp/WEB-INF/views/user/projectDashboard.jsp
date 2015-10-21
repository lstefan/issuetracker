<!DOCTYPE html>
<%@page info="Home Page" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>

	<!-- BOOTSTRAP STYLES-->
	<link
		href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.css"
		rel="stylesheet" />
	<!-- FONTAWESOME STYLES-->
	<link
		href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.css"
		rel="stylesheet" />
	<!-- MORRIS CHART STYLES-->
	
	<!-- CUSTOM STYLES-->
	<link
		href="${pageContext.request.contextPath}/resources/assets/css/custom.css"
		rel="stylesheet" />
	<!-- GOOGLE FONTS-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans'
		rel='stylesheet' type='text/css' />
	<!-- TABLE STYLES-->
	<link
		href="${pageContext.request.contextPath}/resources/assets/js/dataTables/dataTables.bootstrap.css"
		rel="stylesheet" />
		
 <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
 <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
 <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>		

</head>

<body>
	<div id="wrapper">
		<jsp:include page="../fragments/bodyHeader.jsp" />
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>My projects</h2>
					</div>
				</div>
				
				<hr />
				
            <div class="row">
				<div class="col-md-12">
				<div class="panel-body">
				<!-- Split button -->
					<div class="btn-group">
						  <button type="button" class="btn btn-primary btn-lg">Project</button>
						  <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							<span class="caret"></span>
							<span class="sr-only">Toggle Dropdown</span>
						  </button>
						  <ul class="dropdown-menu" role="menu">

						  	<c:forEach var="project" items="${projectsList}">
						  	 	<spring:url value="/dashboard/project/{projectId}/{type}" var="projectUrl">
              						<spring:param name="projectId" value="${project.id}"/>
              						<spring:param name="type" value="${type}"/>
          						</spring:url>
          						<spring:url value="/dashboard/project/all/{type}" var="projectUrlAll">
          							<spring:param name="type" value="${type}"/>
          						</spring:url>
						  		<li><a href="${fn:escapeXml(projectUrl)}"> ${project.projectName}</a></li>
						  	</c:forEach>
							<li class="divider"></li>
							<li><a href="${fn:escapeXml(projectUrlAll)}">All projects</a></li>
						  </ul>

					</div>
					<hr> 
					<br>
					<div id="project_dashboard">
					<c:if test="${not empty project}">
						<h3> Statistics for project ${project.projectName}</h3>
					</c:if>
					<c:if test="${empty project}">
						<h3> Statistics for all my projects</h3>
					</c:if>
					<br>
<!-- 						<ul class="nav nav-tabs">
						  <li role="presentation" class="dropdown">
							    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
							      <b>Select project</b> <span class="caret"></span>
							    </a>
							    <ul class="dropdown-menu" role="menu">
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Project1</a></li>
									    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Project2</a></li>
									    <li role="presentation" class="divider"></li>		
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">All projects</a></li>											
							    </ul>
						  </li>
						  
						  <li role="presentation" class="dropdown">
							    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
							      <b>Sort issues by</b> <span class="caret"></span>
							    </a>
							    <ul class="dropdown-menu" role="menu">
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">State</a></li>
									    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Priority</a></li>		
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Severity</a></li>											
							    </ul>
						  </li>						  
						</ul> -->	

						<div class="tab-content">
							<div class="row">
								<div class="col-md-4 col-sm-6 col-xs-6">           
									<div class="panel panel-back noti-box">
										<span class="icon-box bg-color-yellow">
											<i class="fa fa-thumbs-o-down"></i>
										</span>
										<div class="text-box">
											<p class="main-text">${open} Open Issues</p>
											<p class="text-muted">Pending</p>
										</div>
									 </div>
								</div>	
								
								<div class="col-md-4 col-sm-6 col-xs-6">           
									<div class="panel panel-back noti-box">
										<span class="icon-box bg-color-red">
											<i class="fa fa-warning"></i>
										</span>
										<div class="text-box">
											<p class="main-text">${critical} Critical Issues</p>
											<p class="text-muted">To fix</p>
										</div>
									 </div>
								</div>	
								
								<div class="col-md-4 col-sm-6 col-xs-6">           
									<div class="panel panel-back noti-box">
										<span class="icon-box bg-color-green">
											<i class="fa fa-thumbs-o-up"></i>
										</span>
										<div class="text-box">
											<p class="main-text">${closed} Closed Issues</p>
											<p class="text-muted"> Fixed </p>
										</div>
									 </div>
								</div>									
								
							</div>		
							<hr>   
							<div class= "row">
								<div class="col-md-4 col-sm-6 col-xs-6">
									<div class="panel panel-default">
										<div class="panel-heading">
											<b>Issues by status</b>
										</div>
										<div class="panel-body">	
											<div id="status-chart" style="height: 250px; align:center"></div>
										</div>
									</div>	
								</div>
								
								<div class="col-md-4 col-sm-6 col-xs-6">
									<div class="panel panel-default">
										<div class="panel-heading">
											<b>Issues by severity</b>
										</div>
										<div class="panel-body">	
											<div id="severity-chart" style="height: 250px;  align:center"></div>
										</div>
									</div>	
								</div>	
								
								<div class="col-md-4 col-sm-6 col-xs-6">
									<div class="panel panel-default">
										<div class="panel-heading">
											<b>Issues by priority</b>
										</div>
										<div class="panel-body">	
											<div id="priority-chart" style="height: 250px;  align:center"></div>
										</div>
									</div>	
								</div>			
							
														
							</div>
							
							<hr>
							<h4>Open versus closed issues</h4>
							<br>
							<div class="row">
								<div class="col-md-9 col-sm-12 col-xs-12">                     
									<div class="panel panel-default">
										<div class="panel-heading">
											<ul class="nav nav-pills">
											    <spring:url value="/dashboard/{projectId}/{year}/{type}" var="currentYear">
											    	<spring:param name="projectId" value="${project.id}"/>
											    	<spring:param name="year" value="current"/>
											    	<spring:param name="type" value="${type}"/>
											    </spring:url>
										    	<spring:url value="/dashboard/{projectId}/{year}/{type}" var="lastYear">
										    		<spring:param name="projectId" value="${project.id}"/>
										    		<spring:param name="year" value="last"/>
										    		<spring:param name="type" value="${type}"/>
										    	</spring:url>
											    <spring:url value="/dashboard/project/all/{year}/{type}" var="currentYearAll">
											    	<spring:param name="year" value="current"/>
											    	<spring:param name="type" value="${type}"/>
											    </spring:url>
										    	<spring:url value="/dashboard/project/all/{year}/{type}" var="lastYearAll">
										    		<spring:param name="year" value="last"/>
										    		<spring:param name="type" value="${type}"/>
										    	</spring:url>										    	
										    	<c:if test="${not empty project}">
														<li class=""><a href="${fn:escapeXml(currentYear)}">Current year</a></li>
														<li class=""><a href="${fn:escapeXml(lastYear)}">Last year</a></li>
												</c:if>
												<c:if test="${empty project}">
														<li class=""><a href="${fn:escapeXml(currentYearAll)}">Current year</a></li>
														<li class=""><a href="${fn:escapeXml(lastYearAll)}">Last year</a></li>
												</c:if>										

											</ul>
										</div>
										<div class="panel-body">
											<div id="bar-chart" style="position: relative;"> </div>
										</div>
									</div>            
								</div>								
							</div>
						</div>
					</div>
				</div>
				</div>
            </div>			

			</div>
		</div>
	</div>
	
         <!-- /. PAGE WRAPPER  -->
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<script>
	new Morris.Donut({
	 element: 'status-chart',
	 data: [
    {label: "OPEN", value: "${open}"},
    {label: "CLOSED", value: "${closed}"},
    {label: "IN PROGRESS", value: "${inProgress}"},
    {label: "RESOLVED", value: "${resolved}"}
  ],
   colors: [
    '#0BA462',
    '#39B580',
    '#67C69D',
    '#95D7BB'
  ]
	});
	
	new Morris.Donut({
	 element: 'priority-chart',
	 data: [
    {label: "HIGH", value: "${high}"},
    {label: "MEDIUM", value: "${medium}"},
    {label: "LOW", value: "${low}"}
  ]
	});	
	
	new Morris.Donut({
	 element: 'severity-chart',
	 data: [
    {label: "CRITICAL", value: "${critical}"},
    {label: "MAJOR", value: "${major}"},
    {label: "MODERATE", value: "${moderate}"},
	{label: "MINOR", value: "${minor}"},
	{label: "COSMETIC", value: "${cosmetic}"}],
	colors: [
	'#B22400',
    '#E62E00',
    '#FF4719',
    '#FF704D',
    '#FF9980'
  ]
  
	});	

	var month_data = [
	                  {"period": "Jan", "open": "${x[0]}", "closed": "${y[0]}"},
	                  {"period": "Feb", "open": "${x[1]}", "closed": "${y[1]}"},
	                  {"period": "Mar", "open": "${x[2]}", "closed": "${y[2]}"},
	                  {"period": "Apr", "open": "${x[3]}", "closed": "${y[3]}"},
					  {"period": "May", "open": "${x[4]}", "closed": "${y[4]}"},
	                  {"period": "Jun", "open": "${x[5]}", "closed": "${y[5]}"},
	                  {"period": "Jul", "open": "${x[6]}", "closed": "${y[6]}"},
	                  {"period": "Aug", "open": "${x[7]}", "closed": "${y[7]}"},
	                  {"period": "Sept","open": "${x[8]}", "closed": "${y[8]}"},
	                  {"period": "Oct", "open": "${x[9]}", "closed": "${y[9]}"},
	                  {"period": "Nov", "open": "${x[10]}", "closed": "${y[10]}"},
	                  {"period": "Dec", "open": "${x[11]}", "closed": "${y[11]}"},
	                ];
    
	Morris.Bar({
	  element: 'bar-chart',
	  data: month_data,
	  xkey: 'period',
	  ykeys: ['open', 'closed'],
	  labels: ['OPEN', 'CLOSED']
	});
	</script>
	
	
    <!-- JQUERY SCRIPTS -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery-1.10.2.js"></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.metisMenu.js"></script>
     <!-- DATA TABLE SCRIPTS -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
    </script>
         <!-- CUSTOM SCRIPTS -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/custom.js"></script>	
</body>

</html>