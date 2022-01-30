<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
<c:when test="${ fn:length(ntnCdList) > 0 }">
	<c:forEach items="${ ntnCdList }" var="i">
	<tr>
		<td><c:out value="${ i.ntnCdKrNm }"/></td>
		<td><c:out value="${ i.ntnCdEnNm }"/></td>
		<td><c:out value="${ i.ntnCd }"/></td>
	</tr>
	</c:forEach>
</c:when>
<c:otherwise>
	<tr class="nodata">
		<td class="text-center" colspan="3">등록된 데이터가 없습니다.</td>
	</tr>
</c:otherwise>
</c:choose>

