<!DOCTYPE html>
<%@page info="Home Page" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp" />

<body>
	<div id="wrapper">
		<jsp:include page="../fragments/bodyHeader.jsp" />
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>Hello!</h2>
						<h5>Welcome ${pageContext.request.userPrincipal.name} , Love to see you back.</h5>
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-md-12">
						<h2>What's new</h2>
						<br />
					</div>


 					<div class="col-md-5">
						<!-- Recent Issues -->
						<div class="panel panel-default">
							<div class="panel-heading"><b>Top 5 Projects</b></div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th style="display:none;">Id</th>
												<th>Name</th>
												<th>Location</th>
												<th>Description</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach var="topProject" items="${topProjectsList}"
												varStatus="lineInfo">
												<tr>
													<td style="display:none;">${topProject.id}</td>
													<td>
													    <spring:url value="/projects/{projectId}" var="projectUrl">
                											<spring:param name="projectId" value="${topProject.id}"/>
            											</spring:url>
            											<a href="${fn:escapeXml(projectUrl)}"><c:out value="${topProject.projectName}"/></a>
														
													</td>
													<td>${topProject.location}</td>
													<td style='width: 50px; word-wrap:break-word'>${topProject.description}</td>
												</tr>
											</c:forEach>
										</tbody>

									</table>
								</div>
							</div>
						</div>
						<!-- End  Basic Table  -->
						<!-- End Recent Issues -->
					</div> 
					
 					<div class="col-md-5">
						<!-- Recent Issues -->
						<div class="panel panel-default">
							<div class="panel-heading"><b>Top 5 Users</b></div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th style="display:none;">Id</th>
												<th>First name</th>
												<th>Last name</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach var="topUser" items="${topUsersList}"
												varStatus="lineInfo">
												<tr>
													<td style="display:none;">${topUser.id}</td>
													<td>${topUser.firstname}</td>
													<td>${topUser.lastname}</td>
													
												</tr>
											</c:forEach>
										</tbody>

									</table>
								</div>
							</div>
						</div>
						<!-- End  Basic Table  -->
						<!-- End Recent Issues -->
					</div> 		
					
					<div class="col-md-10">
						<!--   Recent Projects -->
						<div class="panel panel-default">
							<div class="panel-heading"><b>Recent Projects</b></div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th style="display:none;">Id</th>
												<th>Name</th>
												<th>Location</th>
												<th>Description</th>
												<th>Creation date</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach var="project" items="${projectsList}"
												varStatus="lineInfo">
												<tr>
													<td style="display:none;">${project.id}</td>
													<td>
													    <spring:url value="/projects/{projectId}" var="projectUrl">
                											<spring:param name="projectId" value="${project.id}"/>
            											</spring:url>
            											<a href="${fn:escapeXml(projectUrl)}"><c:out value="${project.projectName}"/></a>
														
													</td>
													<td>${project.location}</td>
													<td>${project.description}</td>
													<td>${project.created}</td>
												</tr>
											</c:forEach>
										</tbody>

									</table>
								</div>
							</div>
						</div>
						<!-- Recent projects End -->
					</div>
				</div>

			</div>

		</div>
	</div>
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