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
		<input type="hidden" name="carMdlNo" value="<c:out value="${ carMdlVo.carMdlNo }"/>">
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
		<div class="row mb-5">
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
		<div class="row mb-5">
			<div class="d-flex flex-wrap">
				<div class="me-2">
					<h5>모델 사진</h5>
				</div>
				<div class="me-2">
					<button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#carMdlFile">사진 등록</button>
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<div class="d-flex justify-content-between bd-highlight mb-3">
				<div></div>
				<div>
					<a href="carMdlWrite?carMdlNo=${ carMdlVo.carMdlNo }" class="btn btn-info">모델수정</a>
					<a href="carMdlList?pageNo=${ param.pageNo }" class="btn btn-outline-secondary">목록</a>
				</div>
				<div>
					<button type="button" class="btn btn-danger" id="delCarMdl">모델삭제</button>
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
				<c:forEach var="i" begin="0" end="${ thisYear - 2010 }">
					<option value="${ thisYear - i }"><c:out value="${ thisYear - i }"/></option>
				</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="saveCarMdlYear">등록</button>
				<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="carMdlFile" tabindex="-1" aria-hidden="true">
 	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">모델 사진 등록</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form id="popFileForm">
				<div class="row mb-3">
				 	<label class="col-sm-2 col-form-label">분류<span class="text-danger">*</span></label>
					<div class="col-sm-10">
						<select name="carMdlFileClCd" class="form-select">
							<option	value="">선택하세요.</option>
							<c:forEach items="${ carMdlFileClCdList }" var="i">
							<option value="${ i.cmnCd }"><c:out value="${ i.cmnCdNm }"/></option>
							</c:forEach>
						</select>
					</div>
				</div>
				</form>
				<div id="input-image"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="saveCarMdlYear">등록</button>
				<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<link href="/resources/image-uploader/dist/image-uploader.min.css" rel="stylesheet" />
<script src="/resources/image-uploader/dist/image-uploader.min.js"></script>
<script>
window.addEventListener('DOMContentLoaded', () => {

	/* 연식 등록 */
	document.getElementById('saveCarMdlYear').addEventListener('click', () => {
		let thisYear = document.getElementById('popRlsYear').value;
		let carMdlYearList = JSON.parse("${ carMdlYearList }").map(String);
		if(carMdlYearList.indexOf(thisYear) > -1) {
			alert('이미 등록된 연식입니다.');
		} else if(confirm('연식을 등록하시겠습니까?')) {
			let dataForm = {
				'carMdlNo': document.querySelector('input[name="carMdlNo"]').value,
				'rlsYear': thisYear
			};
			fn_saveCarMdlYear(dataForm);
		}
	});

	/* 사진 등록 팝업 */
	document.querySelector('button[data-bs-target="#carMdlFile"]').addEventListener('click', () => {
		
		fn_imageUploader();
	});

	/* 포토 등록 팝업 */
	


	/* 모델 삭제 버튼 클릭 */
	document.getElementById('delCarMdl').addEventListener('click', () => {
		if(confirm('모델을 삭제하시겠습니까?')) {
			fn_delCarMdl({"carMdlNo": "${ carMdlVo.carMdlNo }"});
		}
	});

});

function fn_saveCarMdlYear(dataForm) {
	axios({
		method: 'post',
	  	url: 'car-mdl/year',
	  	data: JSON.stringify(dataForm),
	  	headers: {'Content-Type': 'application/json'}
	}).then((res) => {
		location.reload();
	}).catch((err) => {
		alert('등록 실패하였습니다.');
    	console.log(err);
	});
}

function fn_imageUploader() {
	document.getElementById('input-image').innerHTML = '';
	$('#input-image').imageUploader({
		label:'사진을 올려놓거나 여기를 클릭하세요.',
		extensions: ['.jpg','.jpeg','.png','.gif','.svg'],
		mimes: ['image/jpeg','image/png','image/gif','image/svg+xml'],
		maxSize: undefined,
		maxFiles: undefined
	});
}

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
