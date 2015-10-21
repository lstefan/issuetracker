<!DOCTYPE html>
<%@page info="Projects List" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
			</div>
		</div>
	</div>
</body>

</html>
