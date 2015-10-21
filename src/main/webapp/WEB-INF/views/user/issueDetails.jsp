<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript"
	src="<c:url value="http://localhost:8080/issuetracker/resources/assets/js/jquery-1.10.2.js" />"></script>
	
<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="issue">
        <h2>Errors</h2>
        <div class="formerror">
            <ul>
            <c:forEach var="error" items="${errors.allErrors}">
                <li>${error.defaultMessage}</li>
            </c:forEach>
            </ul>
        </div>
    </spring:hasBindErrors>
</div>
<body>
	<div id="wrapper">
		<jsp:include page="../fragments/bodyHeader.jsp" />
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>

							Issue ${issue.title}

						</h2>
					</div>
				</div>			
				<hr/>
				
				<div class="row">
					<div class="col-md-6">
                      <!--    Striped Rows Table  -->
                    <div class="panel">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <tbody>
                                        <tr>
                                            <td><b>Title</b></td>
                                            <td>${issue.title}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Category</b></td>
                                            <td>${issue.category}</td>
                                        </tr>
                                        <tr>
                                            <td><b>State</b></td>
                                            <td>${issue.state}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Priority</b></td>
                                            <td>${issue.priority}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Severity</b></td>
                                            <td>${issue.severity}</td>
                                        </tr>                                                                                
                                        <tr>
                                            <td><b>Project</b></td>
                                            <td>${issue.project.projectName}</td>
                                        </tr>
                                         <tr>
                                            <td><b>Affected version</b></td>
                                            <td>${issue.openOnVersion.name}</td>
                                        </tr> 
                                        <c:if test="${not empty issue.fixedOnVersion.name}">
										    <tr>
                                            	<td><b>Fixed version</b></td>
                                            	<td>${issue.fixedOnVersion.name}</td>
                                        	</tr> 
										</c:if>     
                                        <c:if test="${not empty issue.resolution}">
										    <tr>
                                            	<td><b>Resolution</b></td>
                                            	<td>${issue.resolution}</td>
                                        	</tr> 
										</c:if> 										                                
                                         <tr>
                                            <td><b>Created By</b></td>
                                            <td>${issue.createdBy.firstname}</td>
                                        </tr>      
                                         <tr>
                                            <td><b>Created On</b></td>
                                            <td>${issue.created}</td>
                                        </tr>           
                                        <tr>
                                            <td><b>Assigned To</b></td>
                                            <td>${issue.assigned.firstname}</td>
                                        </tr> 
                                       <tr>
                                            <td><b>Modified By</b></td>
                                            <td>${issue.modifiedBy.firstname}</td>
                                        </tr>                                                                                   
                                       <tr>
                                            <td><b>Modified On</b></td>
                                            <td>${issue.modified}</td>
                                        </tr>  
                                       <tr>
                                            <td><b>Description</b></td>
                                            <td>${issue.description}</td>
                                       </tr>                                                                                  
                                
                                        
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
					onclick="window.location.href='${pageContext.request.contextPath}/user/issues'">
					BACK
				</button>	
				</div>
			<!-- </div> -->	
			
            </div>
		</div>
	</div>
	
	
</body>
