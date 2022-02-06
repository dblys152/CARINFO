<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
//menu 활성화
gnbActive = 'setting';
</script>

<!-- Page content-->
<div class="row">
	<!-- Page header-->
	<div class="py-3 bg-light border-bottom mb-4">
        <div class="my-1">
            <p class="lead mb-0">자동차 모델 상세정보</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<input type="hidden" name="mnfNo" value="<c:out value="${ carMdlVo.carMdlNo }"/>">
		<div class="row mb-5">
			<div class="col-sm-6">
				<table>
				<colgroup>
					<col width="50px"><col width="100px"><col width="100px"><col width="80px"><col width="80px">
				</colgroup>
				<tbody>
					<tr>
						<td><img src="/file/images/<c:out value="${ carMdlVo.mnfFileNo }"/>" class="mnf_logo"></td>
						<td><c:out value="${ carMdlVo.mnfNm }"/></td>
						<td><c:out value="${ carMdlVo.carMdlNm }"/></td>
						<td><c:out value="${ carMdlVo.carKnNm }"/></td>
						<td><c:out value="${ carMdlVo.carAprnNm }"/></td>
					</tr>
				</tbody>
				</table>
			</div>
		</div>
		<div class="row mb-1">
			<div class="d-flex flex-wrap">
				<div class="me-2">
					<h5>연식</h5>
				</div>
				<div class="me-2">
					<select name="rlsYear" class="form-select">
						<c:choose>
						<c:when test="${ fn:length(carMdlYearList) > 0 }">
							<c:forEach items="${ carMdlYearList }" var="y">
							<option value="${ y }"><c:out value="${ y }"/>년</option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<option value="">등록된 연식이 없습니다.</option>
						</c:otherwise>
						</c:choose>
					</select>
				</div>
				<div class="me-2">
					<button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#carMdlYearModal">연식 등록</button>
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<div class="d-flex justify-content-between bd-highlight mb-3">
				<div></div>
				<div>
					<a href="carMdlWrite?carMdlNo=${ carMdlVo.carMdlNo }" class="btn btn-info">수정</a>
					<a href="carMdlList?pageNo=${ param.pageNo }" class="btn btn-outline-secondary">목록</a>
				</div>
				<div>
					<button type="button" class="btn btn-danger" id="delCarMdl">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="carMdlYearModal" tabindex="-1" aria-hidden="true">
 	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">연식 등록</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<select id="popRlsYear" class="form-select">
				<c:forEach var="i" begin="1" end="${ thisYear - 2009 }">
					<option value="${ thisYear - i + 1 }"><c:out value="${ thisYear - i + 1 }"/></option>
				</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="saveMnfSrtOrd">등록</button>
				<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<script>
window.addEventListener('DOMContentLoaded', () => {

	/* 제조사 삭제 버튼 클릭 */
	document.getElementById('delCarMdl').addEventListener('click', () => {
		if(confirm('모델을 삭제하시겠습니까?')) {
			fn_delCarMdl({"carMdlNo": "${ carMdlVo.carMdlNo }"});
		}
	});

});

function fn_delCarMdl(dataForm) {

	return;

	axios({
		method: 'delete',
	  	url: 'car-mdl',
	  	data: JSON.stringify(dataForm),
	  	headers: {'Content-Type': 'application/json'}
	}).then((res) => {
		location.href="mnfList";
	}).catch((err) => {
		alert('삭제 실패하였습니다.');
    	console.log(err);
	});
}
</script>
