<!DOCTYPE html>
<%@page info="Home Page" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
<head>

	<!-- BOOTSTRAP STYLES-->
	<link
		href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.css" rel="stylesheet" />
	<!-- FONTAWESOME STYLES-->
	<link
		href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.css" rel="stylesheet" />
	<!-- MORRIS CHART STYLES-->
	
	<!-- CUSTOM STYLES-->
	<link
		href="${pageContext.request.contextPath}/resources/assets/css/custom.css" rel="stylesheet" />
	<!-- GOOGLE FONTS-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
	<!-- TABLE STYLES-->
	<link
		href="${pageContext.request.contextPath}/resources/assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
	
<!-- 	<link rel="stylesheet"
		href="http://code.jquery.com/mobile/1.4.4/jquery.mobile-1.4.4.min.css" />
	<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<script
		src="http://code.jquery.com/mobile/1.4.4/jquery.mobile-1.4.4.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="http://code.ionicframework.com/1.0.0-beta.13/css/ionic.css" />
	<script
		src="http://code.ionicframework.com/1.0.0-beta.13/js/ionic.bundle.js"></script> -->

	
<%-- <jsp:include page="../fragments/staticFiles.jsp" /> --%>


</head>


<body>
	<div id="wrapper">
		<jsp:include page="../fragments/bodyHeader.jsp" />
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>Add and View Issues</h2>
					</div>
				</div>
				<hr />

				<div class="row">
					<div class="col-md-12">
					<button type="submit" class="btn btn-primary"
						onclick="window.location.href='${pageContext.request.contextPath}/user/createIssue'"
						id="btnNewIssue" style="height:50px; width:100px">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						New 
					</button>	
					</div>
				</div>
				<hr />
				
	    <spring:url value="/issues/viewAssigned" var="assignedUrl"/>
    	<spring:url value="/user/issues" var="allUrl"/>
		<spring:url value="/issues/viewReported" var="reportedUrl"/>
						
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             Issues	 
                        </div>
                        <div class="panel-body">
							<ul class="nav nav-tabs">
								<li class=""><a href="${fn:escapeXml(allUrl)}">All issues</a></li>
								<li class=""><a href="${fn:escapeXml(reportedUrl)}">Opened by me</a></li>
								<li class=""><a href="${fn:escapeXml(reportedUrl)}">Assigned to me</a></li>
							</ul>  
							<div class="tab-content">                      
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        
											<tr>
												<th style="display:none;">Id</th>
												<th>Title</th>
												<th>Category</th>
												<th>State</th>
												<th>Priority</th>
												<th>Severity</th>
												<th>Description</th>
												<th>Created By</th>
												<th>Project</th>
												<th>Actions</th>
												
											</tr>
                                       
                                    </thead>
                                    <tbody>
											<c:forEach var="issue" items="${issuesList}"
												varStatus="lineInfo">
												<tr>
													<td style="display:none;">${issue.id}</td>
													<td>
														<spring:url value="/issues/{issueId}" var="issueUrl">
                											<spring:param name="issueId" value="${issue.id}"/>
            											</spring:url>
            											<a href="${fn:escapeXml(issueUrl)}"><c:out value="${issue.title}"/></a>
													</td>
													<td>${issue.category}</td>	
													<td>${issue.state}</td>	
													<td>${issue.priority}</td>
													<td>${issue.severity}</td>	
													<td>${issue.description}</td>
													<td>${issue.createdBy.getFirstname()}</td>
													<td>${issue.project.projectName}</td>
													<td>
													<c:if test="${pageContext.request.userPrincipal.name eq issue.createdBy.getEmail() || pageContext.request.userPrincipal.name eq issue.assigned.getEmail()}">            
														<spring:url value="/issues/{issueId}/editIssue" var="issueUrl">
                											<spring:param name="issueId" value="${issue.id}"/>
           												</spring:url>
           												<a href="${fn:escapeXml(issueUrl)}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
           												
														<spring:url value="/issues/{issueId2}/deleteIssue" var="issueDeleteUrl">
                											<spring:param name="issueId2" value="${issue.id}"/>
           												</spring:url>
													
														<a href="${fn:escapeXml(issueDeleteUrl)}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
<%--  <a href="${fn:escapeXml(issueDeleteUrl)}"><span class="glyphicon glyphicon-trash" aria-hidden="true" data-toggle="modal" data-target="#myModal" ></span> </a>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 class="modal-title" id="myModalLabel">Modal title Here</h4>
		</div>
		<div class="modal-body">
			Are you sure?
		</div>
		<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="button" class="btn btn-primary">OK</button>
		</div>
		</div>
	</div>
</div> 		 --%>									
													</c:if>
													</td>																						
												</tr>
											</c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            </div>
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>			

			</div>
		</div>
	</div>
	
         <!-- /. PAGE WRAPPER  -->
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
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