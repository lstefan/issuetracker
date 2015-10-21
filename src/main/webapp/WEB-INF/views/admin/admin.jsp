<!DOCTYPE html> 
<%@page info="Projects List" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
 	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			<fmt:message key="welcome"/> : ${pageContext.request.userPrincipal.name}
		</h2>
	</c:if>
    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
