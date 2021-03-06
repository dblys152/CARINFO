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
			<c:out value="${ i.seq }"/>
			<input type="hidden" name="mnfNo" value="<c:out value="${ i.mnfNo }"/>">
		</th>
		<td><img src="/file/images/<c:out value="${ i.fileNo }"/>" class="mnf_logo"></td>
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
/* 페이징 */
paging(10, '${ totCnt }', '${ listCnt }', '${ pageNo }');

/* 목록 클릭 시 상세화면 이동 */
let listTr = document.querySelectorAll('#mnfTbl > tbody tr:not(.nodata)');
for(let i = 0; i < listTr.length; i++) {
	listTr[i].addEventListener('click', (e) => {
		location.href="mnfView?mnfNo="
			+ e.currentTarget.querySelector('input[name="mnfNo"]').value
			+ "&pageNo=" + document.querySelector('#pageNo').value;
	});
}
</script>
