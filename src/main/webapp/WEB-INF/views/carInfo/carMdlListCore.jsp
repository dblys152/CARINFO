<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
<c:when test="${ fn:length(carMdlList) > 0 }">
	<c:forEach items="${ carMdlList }" var="i">
	<tr style="cursor:pointer;">
		<th scope="row">
			<c:out value="${ i.seq }"/>
			<input type="hidden" name="carMdlNo" value="<c:out value="${ i.carMdlNo }"/>">
		</th>
		<td><img src="/file/images/<c:out value="${ i.mnfFileNo }"/>" class="mnf_logo"></td>
		<td><c:out value="${ i.carMdlNm }"/></td>
		<td><c:out value="${ i.carAprnNm }"/></td>
		<td><c:out value="${ i.carKnNm }"/></td>
		<td><c:out value="${ i.rlsYear }"/></td>
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
let listTr = document.querySelectorAll('#carMdlTbl > tbody tr:not(.nodata)');
for(let i = 0; i < listTr.length; i++) {
	listTr[i].addEventListener('click', (e) => {
		location.href="carMdlView?carMdlNo="
			+ e.currentTarget.querySelector('input[name="carMdlNo"]').value
			+ "&pageNo=" + document.querySelector('#pageNo').value;
	});
}
</script>
