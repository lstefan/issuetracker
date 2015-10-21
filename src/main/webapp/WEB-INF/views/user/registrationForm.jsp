<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>	
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
</head>
<body>
<%--     <div class="page-header">
        <h1><spring:message code="label.user.registration.page.title"/></h1>
    </div> --%>
    <!--
        If the user is anonymous (not logged in), show the registration form.
    -->
    
	
    <div class="container">
	    <div class="row text-center ">
            <div class="col-md-12">
                <br><br>
                <h2> Issuetracker </h2>
               
                <h5> Login yourself to get access to a new testing experience </h5>
                 <br>
            </div>
        </div>
         <div class="row">
               
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                        <strong>  New User ? Register Yourself </strong>  
                            </div>
                            <div class="panel-body">
                                <form:form action="${pageContext.request.contextPath}/user/register" method="POST" enctype="utf8" id="signupform" role="form" commandName="user">
									<c:if test="${user.signInProvider != null}">
										<form:hidden path="signInProvider"/>
									</c:if>  								
								<br>
                                     <div class="form-group input-group">
                                            <span class="input-group-addon control-label">@</span>
											<form:input id="user-email" path="email" type="text" class="form-control" placeholder="Email Address"/>
											<form:errors id="error-email" path="email" cssClass="help-block"/>
                                        </div>								
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-tag"></i></span>
                                            <form:input id="user-firstName" type="text" path="firstName" class="form-control"  placeholder="First Name"/>
											<form:errors id="error-firstName" path="firstName" cssClass="help-block"/>
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-tag"></i></span>
											<form:input id="user-lastName" path="lastName" type="text" class="form-control" placeholder="Last Name"/>
											<form:errors id="error-lastName" path="lastName" cssClass="help-block"/>
                                        </div>										

									<c:if test="${user.signInProvider == null}">
                                      <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
											<form:password id="user-password" path="password" class="form-control" placeholder="Password"/>
											<form:errors id="error-password" path="password" cssClass="help-block"/>
                                        </div>
										<div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                            <form:password id="user-passwordVerification" path="passwordVerification" class="form-control" placeholder="Retype Password"/>
											<form:errors id="error-passwordVerification" path="passwordVerification" cssClass="help-block"/>
                                        </div>
                                     </c:if>
									 <button id="btn-signup" type="submit" class="btn btn-success"><i class="icon-hand-right"></i>Register Me</button>

                                    <hr>
                                    Already Registered ?  <a href="${pageContext.request.contextPath}/user/login">Login here</a>
                                    </form:form>
                            </div>
                           
                        </div>
                    </div>
                
                
        </div>
    </div>
	
	
    
    <!--
        If the user is authenticated, show a help message instead
        of registration form.
    -->
    <sec:authorize access="isAuthenticated()">
        <p><spring:message code="text.registration.page.authenticated.user.help"/></p>
    </sec:authorize>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/user.form.js"></script> 
</body>
</html>	
	