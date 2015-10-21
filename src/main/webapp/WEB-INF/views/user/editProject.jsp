<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form modelAttribute="editedProjectVersion" class="form-horizontal">
	<div id="messages">
		<c:if test="${not empty statusMessageKey}">
			<p>
				<fmt:message key="${statusMessageKey}" />
			</p>
		</c:if>

		<spring:hasBindErrors name="editedProjectVersion">
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
								<c:if test="${project.id eq null}">
									<fmt:message key="project.form.titleEdit" />
								</c:if>
								<c:if test="${project.id ne null}">
									<fmt:message key="project.form.titleEdit" />
								</c:if>
							</h2>
						</div>
					</div>
					<hr />

					<div class="row">
						<div class="col-md-6">

							<div class="form-group" style="display: none">
								<label for="name" class="col-sm-2 control-label"><fmt:message
										key="project.id" /></label>
								<div class="col-sm-10">
									<form:input type="hidden" path="project.id"
										class="form-control" />
								</div>
								<div class="col-sm-10">
									<form:input type="hidden" path="version.id"
										class="form-control" />
								</div>
								<%-- 								<div class="col-sm-10">
									<form:input type="hidden" path="project.owner.id" class="form-control"/>
								</div>	 --%>

							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label"><fmt:message
										key="project.name" /></label>
								<div class="col-sm-10">
									<form:input path="project.projectName" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-sm-2 control-label"><fmt:message
										key="project.description" /></label>
								<div class="col-sm-10">
									<form:textarea path="project.description" class="form-control"
										rows="3" />
								</div>
							</div>



							<div class="form-group">
								<label for="versions" class="col-sm-2 control-label"><fmt:message
										key="project.versions" /></label>
								<div class="col-sm-10">

									<form:select path="version.id">
										<%-- <c:if test="${(issue.id eq null) && (issue.project eq null)}  "> 
							          	  <form:option value="" label="--Please Select"/> 
							          	 </c:if> --%>
										<form:options items="${versionsList}" itemValue="id"
											itemLabel="name" />
									</form:select>
								</div>

							</div>



							<div class="form-group">
								<label for="version" class="col-sm-2 control-label"><fmt:message
										key="project.newversion" /></label>
								<div class="col-sm-10">
									<!-- <input name="versionName" type="text" class="form-control"/> -->
									<form:input path="version.name" type="text"
										class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="notes" class="col-sm-2 control-label"><fmt:message
										key="project.notes" /></label>
								<div class="col-sm-10">
									<form:textarea path="project.releaseNotes" class="form-control"
										rows="5" />
								</div>
							</div>
							<div class="form-group">
								<label for="location" class="col-sm-2 control-label"><fmt:message
										key="project.location" /></label>
								<div class="col-sm-10">
									<form:input path="project.location" class="form-control" />
								</div>
							</div>
							<%-- 							<div class="form-group">
								<label for="location" class="col-sm-2 control-label"><fmt:message key="project.creator"/></label>
								<div class="col-sm-10">
									<form:input path="owner.username" readonly="readonly" class="form-control"/>
								</div>
							</div>	 --%>
							<div class="form-group">
								<div class="col-sm-10" style="float: right;">
									<button class="btn btn-default" type="submit" id="save"
										name="save" value="<fmt:message key="button.save"/>">Save</button>
									<button class="btn btn-default" type="submit" id="cancel"
										name="cancel" value="<fmt:message key="button.cancel"/>">Cancel</button>
								</div>
							</div>



						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</form:form>
