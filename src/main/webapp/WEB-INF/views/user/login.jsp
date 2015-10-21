<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
<%--     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/social-buttons-3.css"/> --%>

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

<!-- 
    If the user is anonymous (not logged in), show the login form
    and social sign in buttons.
-->
 <sec:authorize access="isAnonymous()"> 
    <!-- Login form -->
    <div class="container">  	
	    <div class="row text-center ">
            <div class="col-md-12">
                <br><br>
                <h2> Issuetracker </h2>
               
                <h5> Login yourself to get access to a new testing experience </h5>
                 <br>
            </div>
        </div>
		
		 <div class="row ">
                  <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                        <strong>   Enter Details To Login </strong>  
                            </div>
                            <div class="panel-body">
                                <form role="form" action="${pageContext.request.contextPath}/login/authenticate" method="POST">
                                       <br>
                                     <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-tag"></i></span>
                                            <input type="text" class="form-control" name="username" value="" placeholder="Your email ">
                                     </div>
                                            <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                            <input type="password" class="form-control" name="password" placeholder="Your Password">
                                        </div>
                                    <div class="form-group">
                                            <label class="checkbox-inline">
                                                <input type="checkbox"> Remember me
                                            </label>
                                            <span class="pull-right">
                                                   
                                            </span>
                                    </div>
                                     
                                    
                                      <button type="submit" class="btn btn-info"> Login </button>
                                      <!-- <a id="btn-login" href="#" class="btn btn-success">Login  </a> -->
                                      <input type="hidden" name="scope" value="email" />
                                      <a id="btn-fblogin" href="${pageContext.request.contextPath}/auth/facebook?scope=email" class="btn btn-primary">Login with Facebook</a>

                                    
                                
                                    <hr>
                                            Don't have an account?
											<a href="${pageContext.request.contextPath}/user/register"> Register here! </a>
                                                                        									
                                </form>
                            </div>
                           
                        </div>
                    </div>		 
		 </div>
		
		
		
		
		

	</div>
 </sec:authorize> 
<!-- 
    If the user is already authenticated, show a help message instead
    of the login form and social sign in buttons.
-->
<sec:authorize access="isAuthenticated()">
    <p><spring:message code="text.login.page.authenticated.user.help"/></p>
</sec:authorize>	
</body>
</html>
