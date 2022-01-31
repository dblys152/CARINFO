<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
<c:if test="${!empty refreshParentAndClose}">
	alert('<c:out value="${refreshParentAndClose}" />');
	window.opener.location.reload(true);
	self.close();
</c:if>
<c:if test="${!empty close}">
	alert('<c:out value="${close}" />');
	self.close();
</c:if>
<c:if test="${!empty messages}">
	alert('<c:out value="${messages}" />');
</c:if>

<c:if test="${reload eq 'true'}">
	opener.location.reload();
</c:if>

<c:choose>
	<c:when test="${!empty history}">
		window.history.go("${history}");
	</c:when>
	<c:when test="${empty redirect}">
		history.back();
	</c:when>
	<c:otherwise>
		location.href="${redirect}";
	</c:otherwise>
</c:choose>
</script>
</head>
<body></body>
</html>