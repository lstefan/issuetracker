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

</head>
<body>
	<div id="wrapper">
		<jsp:include page="../fragments/bodyHeader.jsp" />
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>Project ${project.projectName}</h2>
					</div>
				</div>
				<hr />
				
				<div class="row">
					<div class="col-md-12">
						<spring:url value="/projects/{projectId}/bookmark" var="favUrl">
								<spring:param name="projectId" value="${project.id}"/>
						</spring:url>
						<spring:url value="/projects/{projectId}/removeFromBookmark" var="unfavUrl">
								<spring:param name="projectId" value="${project.id}"/>
						</spring:url>
						
						<c:choose>
						      <c:when test="${bookmarked==true}">
								 <c:set var="link" value="${unfavUrl}"/>
								 <c:set var="buttonText" value="UnFollow"/>
						      </c:when>
						
						      <c:otherwise>
						      	<c:set var="link" value="${favUrl}"/>
						      	<c:set var="buttonText" value="Follow"/>
						      </c:otherwise>
						</c:choose>
						
						
					<button type="submit" class="btn btn-primary"
						onclick="window.location.href='${fn:escapeXml(link)}'"
						id="btnNewProject" style="height:50px; width:100px">
						${buttonText}
					</button>	
					</div>
				</div>
				<hr />
				
            <div class="row">
                <div class="col-md-6">
                      <!--    Striped Rows Table  -->
                    <div class="panel">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <tbody>
                                        <tr>
                                            <td><b>Location</b></td>
                                            <td>${project.location}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Description</b></td>
                                            <td>${project.description}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Release Notes</b></td>
                                            <td>${project.releaseNotes}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Date created</b></td>
                                            <td>${project.created}</td>
                                        </tr>
                                         <tr>
                                            <td><b>Date modified</b></td>
                                            <td>${project.modified}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Versions</b></td>
                                            <td>
												<select>
												  <c:forEach items="${versionsList}" var="versionVal">
												    <option value="${versionVal}">
												        ${versionVal.name}
												    </option>
												  </c:forEach>
												</select>							          			
											</td>
                                        </tr>
                                       <tr>
                                            <td><b>Created by</b></td>
                                            <td>${project.owner.getFirstname()}</td>
                                        </tr>
                                       <tr>                                    
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!--  End  Striped Rows Table  -->
                </div>
                
            </div>	
            
           	<!-- <div class="row"> -->
				<div class="col-md-6">
				<button class="btn btn-default"
					onclick="window.location.href='${pageContext.request.contextPath}/user/projects'">
					BACK 
				</button>	
				</div>
			<!-- </div> -->		

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