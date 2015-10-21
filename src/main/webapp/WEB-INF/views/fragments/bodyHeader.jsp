<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<title>Issue Tracker</title>

<!-- BOOTSTRAP STYLES-->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.css"
	rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.css"
	rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/custom.css"
	rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>


<nav class="navbar navbar-default navbar-cls-top " role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".sidebar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.html">Issue Tracker </a>
	</div>
</nav>
<!-- /. NAV TOP  -->
<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">
			<li class="text-center"><img
				src="${pageContext.request.contextPath}/resources/assets/img/find_user.png"
				class="user-image img-responsive" /></li>


			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-dashboard fa-3x"></i>
					Home </a></li>
<%-- 			<li><a href="${pageContext.request.contextPath}/user/dashboard"><i
					class="fa fa-bar-chart-o fa-3x"></i> My Dashboard </a></li> --%>
					
            <li class="">
                <a href="#"><i class="fa fa-bar-chart-o fa-3x"></i>My dashboard<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse" aria-expanded="false" style="height: 0px;">
     				<spring:url value="/dashboard/project/all/{type}" var="myProjectsUrl">
   						<spring:param name="type" value="mine"/>
					</spring:url>
     				<spring:url value="/dashboard/project/all/{type}" var="followedProjectsUrl">
   						<spring:param name="type" value="followed"/>
					</spring:url>
                    <li>
                        <a href="${myProjectsUrl}">Owned projects</a>
                    </li>
                    <li>
                        <a href="${followedProjectsUrl}">Followed projects</a>
                    </li>
                </ul>
            </li>  				
			<li><a href="${pageContext.request.contextPath}/user/projects"><i class="fa fa-table fa-3x"></i>
					Projects </a></li>
			<li><a href="${pageContext.request.contextPath}/user/issues"><i class="fa fa-edit fa-3x"></i>
					Issues </a></li>
			<li><a href="<spring:url value="/logout" htmlEscape="true" />"><i
					class="fa fa-desktop fa-3x"></i> Logout</a></li>
		</ul>

	</div>
</nav>


