<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
<c:when test="${ fn:length(mnfList) > 0 }">
	<c:forEach items="${ mnfList }" var="i">
	<tr style="cursor:pointer;">
		<th scope="row">
			<c:out value="${ i.rn }"/>
			<input type="hidden" name="mnfNo" value="<c:out value="${ i.mnfNo }"/>">
		</th>
		<td><img src="/file/images/<c:out value="${ i.fileNo }"/>" class="logo" style="width:50px;height:50px"></td>
		<td><c:out value="${ i.mnfNm }"/></td>
		<td><c:out value="${ i.ntnCdKrNm } (${ i.ntnCdEnNm })"/></td>
		<td><c:out value="${ i.regDt }"/></td>
	</tr>
	</c:forEach>
</c:when>
<c:otherwise>
	<tr class="nodata">
		<td class="text-center" colspan="5">등록된 데이터가 없습니다.</td>
	</tr>
</c:otherwise>
</c:choose>

<script>
$(document).ready(() => {
	$('#mnfTbl > tbody tr:not(.nodata)').on('click', (e) => {
		location.href="mnfView?mnfNo=" + $(e.currentTarget).find('input[name="mnfNo"]').val();
	});

	paging(10, '${ totCnt }', '${ listCnt }', '${ pageNo }');
});
</script>
