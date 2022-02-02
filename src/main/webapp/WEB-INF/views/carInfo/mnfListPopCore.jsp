<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
<c:when test="${ fn:length(mnfList) > 0 }">
	<c:forEach items="${ mnfList }" var="i">
	<%-- <tr style="cursor:pointer;">
		<td><img src="/file/images/<c:out value="${ i.fileNo }"/>" class="mnf_logo"></td>
		<td><c:out value="${ i.mnfNm }"/></td>
		<td><c:out value="${ i.ntnCdKrNm } (${ i.ntnCdEnNm })"/></td>
	</tr> --%>
	<div class="col">
		<div class="card" style="align-items: center;cursor: pointer;">
			<input type="hidden" name="mnfNo" value="<c:out value="${ i.mnfNo }"/>">
			<img src="/file/images/<c:out value="${ i.fileNo }"/>" class="card-img-top" style="width:50px;height:50px">
			<span class="card-text"><c:out value="${ i.mnfNm }"/></span>
		</div>
	</div> 
	</c:forEach>
</c:when>
<c:otherwise>
	<!-- <tr class="nodata">
		<td class="text-center" colspan="3">등록된 데이터가 없습니다.</td>
	</tr> -->
	<div class="col">
		등록된 데이터가 없습니다.
	</div>
</c:otherwise>
</c:choose>

<script>
let mnfCards = document.querySelectorAll('#mnfBox .col .card');
for(let i = 0; i < mnfCards.length; i++) {
	mnfCards[i].addEventListener('mousedown', (e) => {
		for(let i = 0; i < mnfCards.length; i++) {
			mnfCards[i].classList.remove('mnf_choice');
		}

		let thisMnf = e.currentTarget;
		if(!thisMnf.classList.contains('mnf_choice')) {
			thisMnf.classList.add('mnf_choice');
		}
	});
}
</script>