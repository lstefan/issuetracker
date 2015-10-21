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
						<h2>Add and View Projects</h2>
					</div>
				</div>
				<hr />

				<div class="row">
					<div class="col-md-12">
					<button type="submit" class="btn btn-primary"
						onclick="window.location.href='${pageContext.request.contextPath}/user/createProject'"
						id="btnNewProject" style="height:50px; width:100px">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						New 
					</button>	
					</div>
				</div>
				<hr />
				
	    <spring:url value="/projects/viewBookmarked" var="bookmarkedUrl"/>
    	<spring:url value="/user/projects" var="allUrl"/>
		<spring:url value="/projects/viewMyProjects" var="mineUrl"/>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
							Projects
                        </div>
                        <div class="panel-body">
							<ul class="nav nav-tabs">
								<li class=""><a href="${fn:escapeXml(allUrl)}">All projects</a></li>
								<li class=""><a href="${fn:escapeXml(bookmarkedUrl)}">Bookmarked projects</a></li>
								<li class=""><a href="${fn:escapeXml(mineUrl)}">My projects</a></li>
							</ul>
							<div class="tab-content">
	                            <div class="table-responsive">
	                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
	                                    <thead>
	                                        
												<tr>
													<th>Id</th>
													<th>Name</th>
													<th>Location</th>
													<th>Description</th>
													<th>Release Notes</th>
													<th>Owner</th>
													<th>Versions</th>
													<th>Actions</th>
												</tr>
	                                       
	                                    </thead>
	                                    <tbody>
												<c:forEach var="project" items="${projectsList}"
													varStatus="lineInfo">
													<tr>
														<td>${project.id}</td>
														<td>
														    <spring:url value="/projects/{projectId}" var="projectUrl">
	                											<spring:param name="projectId" value="${project.id}"/>
	            											</spring:url>
	            											<a href="${fn:escapeXml(projectUrl)}"><c:out value="${project.projectName}"/></a>
															
														</td>
														<td>${project.location}</td>
														<td>${project.description}</td>
														<td>${project.releaseNotes}</td>
														<td>${project.owner.getFirstname()}</td>
														<td>${project.getLastVersion().name}</td>
														<td>  
	          												<spring:url value="/projects/{projectId}/addIssue" var="issueUrl">
	          													<spring:param name="projectId" value="${project.id}"/>
	          												</spring:url>
	           												<a href="${fn:escapeXml(issueUrl)}"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>	
	           											<c:if test="${pageContext.request.userPrincipal.name eq project.owner.getEmail()}">
							          
															<spring:url value="/projects/{projectId}/editProject" var="projectUrl">
	                											<spring:param name="projectId" value="${project.id}"/>
	           												</spring:url>
	           												<a href="${fn:escapeXml(projectUrl)}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
	           												
															<spring:url value="/projects/{projectId2}/deleteProject" var="projectDeleteUrl">
	                											<spring:param name="projectId2" value="${project.id}"/>
	           												</spring:url>	
	           										     	
	           												<a href="${fn:escapeXml(projectDeleteUrl)}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
	           				<%-- 								<spring:url value="/projects/{projectId}/addToFavorites" var="favUrl">
	          													<spring:param name="projectId" value="${project.id}"/>
	          												</spring:url>
	           												<a href="${fn:escapeXml(favUrl)}"><span class="glyphicon glyphicon-star" aria-hidden="true"></span></a> --%>
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