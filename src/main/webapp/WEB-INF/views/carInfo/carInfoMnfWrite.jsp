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
            <p class="lead mb-0">제조사 ${ mnfVo.mnfNo == null ? '등록' : '수정 ' }</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<form:form modelAttribute="mnfVo" enctype="multipart/form-data">
		<form:hidden path="mnfNo"/>
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조사명<span class="text-danger">*</span></label>
			<div class="col-sm-10">
			  <form:input class="form-control" path="mnfNm"/>
			</div>
		</div>
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조국<span class="text-danger">*</span></label>
			<div class="col-sm-10">
				<form:select class="form-select" aria-label="Default select example" path="ntnCd">
					<c:forEach items="${ ntnCdList }" var="i">
					<form:option value="${ i.ntnCd }"><c:out value="${ i.ntnCdKrNm } (${ i.ntnCdEnNm })"/></form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조사 로고<span class="text-danger">*</span></label>
		 	<div class="col-sm-10" id="file_box">
				<c:choose>
				<c:when test="${ mnfVo.mnfNo != null }">
					<c:out value="${ mnfVo.fileNm }"/><button type="button" class="btn-close" id="log_del" aria-label="Close"></button><br/>
					<input type="hidden" name="fileNo" value="<c:out value="${ mnfVo.fileNo }"/>"/>
					<img src="/file/images/<c:out value="${ mnfVo.fileNo }"/>" class="logo" style="width:50px;height:50px">
				</c:when>
				<c:otherwise>
					<label for="formFile" class="form-label">로고 크기 50x50</label>
					<input class="form-control upload_img" type="file" name="file">
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		</form:form>
		<div class="mb-3 row">
			<div class="d-flex justify-content-between bd-highlight mb-3">
				<div></div>
				<div>
					<button type="button" class="btn btn-info" id="mnfSave">저장</button>
					<button type="button" class="btn btn-outline-secondary" onclick="history.back();">취소</button>
				</div>
				<div></div>
			</div>
		</div>
	</div>
</div>

<script>
window.addEventListener('DOMContentLoaded', () => {

	if(${ mnfVo.mnfNo != null }) {
		document.getElementById('log_del').addEventListener('click', () => {
			if(confirm('로고를 삭제하시겠습니까?')) {
				let html = '';
				html += '<label for="formFile" class="form-label">로고 크기 50x50</label>';
				html += '<input class="form-control upload_img" type="file" name="file">';
				document.getElementById('file_box').innerHTML = html;
			}
		});
	}

	document.getElementById('mnfSave').addEventListener('click', () => {
		let mnfNmInp = document.querySelector('input[name="mnfNm"]');
		let fileInp = document.querySelector('input[name="file"]');
		let fileNoInp = document.querySelector('input[name="fileNo"]');

		if(mnfNmInp.value == '') {
			alert('제조사명을 입력해주세요.');
			mnfNmInp.focus();
		} else if((fileInp == null || fileInp.value == '') && (fileNoInp == null || fileNoInp.value == '')) {
			 alert('로고를 등록해주세요.');
			 fileInp.focus();
		} else if(confirm('저장 하시겠습니까?')) {
			let formData = new FormData();
			formData.append("mnfNo", document.querySelector('input[name="mnfNo"]').value);
			formData.append("mnfNm", mnfNmInp.value);
			formData.append("ntnCd", document.querySelector('select[name="ntnCd"]').value);
			if(fileInp != null) formData.append("file", fileInp.files[0]);
			if(fileNoInp != null) formData.append("fileNo", fileNoInp.value);

			fn_saveMnf(formData);
		}
	});

});

function fn_saveMnf(formData) {
	axios({
		method: 'post'
	  , url: 'mnfWrite'
	  , data: formData
	  , headers: {'Content-Type': 'multipart/form-data'}
	}).then((res) => {
		location.href="mnfView?mnfNo=" + res.data.mnfNo;
	}).catch((err) => {
		alert('저장 실패하였습니다.');
    	console.log(err);
	});
}
</script>
