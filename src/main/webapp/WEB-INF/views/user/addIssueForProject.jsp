<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript"
	src="<c:url value="http://localhost:8080/issuetracker/resources/assets/js/jquery-1.10.2.js" />"></script>
	
<form:form modelAttribute="issue" class="form-horizontal">
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
							<c:if test="${issue.id eq null}"> 
							<fmt:message key="issue.form.titleNew"/>
							</c:if>
							<c:if test="${issue.id ne null}"> 
							<fmt:message key="issue.form.titleEdit"/>
							</c:if>
						</h2>
					</div>
				</div>			
				<hr/>
				
				<div class="row">
					<div class="col-md-6">
									
						
							<div class="form-group" style="display:none">
								<label for="name" class="col-sm-2 control-label"><fmt:message key="issue.id"/></label>
								<div class="col-sm-10">
									<form:input path="id" class="form-control"/>
								</div>
							</div>						
							<div class="form-group">
								<label for="projects" class="col-sm-2 control-label"><fmt:message key="issue.selectedProject"/></label>
								<div class="col-sm-10">
									
<%-- 							          <form:select path="project.id" id="dropdown1">
							          	  <form:options items="${projectsList}" itemValue="id" itemLabel="projectName"/>
							          </form:select>	 --%>
							          
							          <%-- <form:input path="project.id" class="form-control" itemValue="${selectedProject}" itemLabel="projectName" disabled="true"/> --%>
							          <input class="form-control" value="${selectedProjectName}" disabled="disabled"/>
							          
							          <form:input path="project.id" type="hidden" itemValue="${selectedProject}" />				
									<%-- <b>${selectedProject}</b>  --%>
								</div>
								
							</div>	
							<div class="form-group">
								<label for="versions" class="col-sm-2 control-label"><fmt:message key="issue.versions"/></label>
								<div class="col-sm-10">
							          
						          <form:select id="dropdown2" path="openOnVersion.id">
						          		<form:options items="${versionList}" itemValue="id" itemLabel="name"/>
						          </form:select>	
						          								
								</div>
								
							</div>														
						
							<div class="form-group">
								<label for="title" class="col-sm-2 control-label"><fmt:message key="issue.title"/></label>
								<div class="col-sm-10">
									<form:input path="title" class="form-control"/>
								</div>
							</div>
							
							<div class="form-group">
								<label for="category" class="col-sm-2 control-label"><fmt:message key="issue.category"/></label>
								<div class="col-sm-10">
									
							          <form:select path="category">
							          	  <form:option value="issueSelection" label="--Please Select"/>
							          	  <form:options items="${categoryList}" />
							          </form:select>									
								</div>
							</div> 	
							
							<div class="form-group">
								<label for="category" class="col-sm-2 control-label"><fmt:message key="issue.severity"/></label>
								<div class="col-sm-10">
									
							          <form:select path="severity">
							          	  <form:option value="issueSelection" label="--Please Select"/>
							          	  <form:options items="${severityList}" />
							          </form:select>									
								</div>
							</div> 	
							
							<div class="form-group">
								<label for="category" class="col-sm-2 control-label"><fmt:message key="issue.priority"/></label>
								<div class="col-sm-10">
									
							          <form:select path="priority">
							          	  <form:option value="issueSelection" label="--Please Select"/>
							          	  <form:options items="${priorityList}" />
							          </form:select>									
								</div>
							</div> 								
														
							<div class="form-group">
								<label for="description" class="col-sm-2 control-label"><fmt:message key="issue.description"/></label>
								<div class="col-sm-10">
									<form:textarea path="description" class="form-control" rows="3"/>
								</div>
							</div>	
							
							<div class="form-group">
								<div class="col-sm-10" style="float:right;">
									<button class="btn btn-default"  type="submit" id="save" name="save" value="<fmt:message key="button.save"/>">Save</button>
									<button class="btn btn-default"  type="submit" id="cancel"  name="cancel" value="<fmt:message key="button.cancel"/>">Cancel</button>
								</div>
							</div>
						
						
					</div>
				</div>
            </div>
		</div>
	</div>
	
	

</body>
</form:form>