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
            <p class="lead mb-0 fw-bold">제조사 ${ mnfVo.mnfNo == null ? '등록' : '수정 ' }</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<form:form modelAttribute="mnfVo" enctype="multipart/form-data">
		<form:hidden path="mnfNo"/>
		<input type="text" style="display:none;"/>
		<div class="row mb-3">
		 	<label class="col-sm-2 col-form-label fw-bold">제조사명<span class="text-danger">*</span></label>
			<div class="col-sm-10">
			  <form:input path="mnfNm" class="form-control" maxlength="25"/>
			</div>
		</div>
		<div class="row mb-3">
		 	<label class="col-sm-2 col-form-label fw-bold">제조국<span class="text-danger">*</span></label>
			<div class="col-sm-10">
				<form:select path="ntnCd" class="form-select">
					<form:option value="">선택하세요.</form:option>
					<c:forEach items="${ ntnCdList }" var="i">
					<form:option value="${ i.ntnCd }"><c:out value="${ i.ntnCdKrNm } (${ i.ntnCdEnNm })"/></form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<%-- <div class="row mb-3">
		 	<label class="col-sm-2 col-form-label">제조사 로고<span class="text-danger">*</span></label>
		 	<div class="col-sm-10" id="file_box">
				<c:choose>
				<c:when test="${ mnfVo.mnfNo != null }">
					<c:out value="${ mnfVo.orgFileNm }"/><button type="button" class="btn-close" id="log_del" aria-label="Close"></button><br/>
					<input type="hidden" name="fileNo" value="<c:out value="${ mnfVo.fileNo }"/>"/>
					<img src="/file/images/<c:out value="${ mnfVo.fileNo }"/>" class="mnf_logo">
				</c:when>
				<c:otherwise>
					<label class="form-label">정방형 크기 1MB이하</label>
					<input class="form-control upload_img" type="file" name="file">
				</c:otherwise>
				</c:choose>
			</div>
		</div> --%>
		<div class="row mb-3">
		 	<label class="col-sm-2 col-form-label fw-bold">제조사 로고<span class="text-danger">*</span></label>
		 	<div class="col-sm-10" id="file_box">
		 		<label class="form-label">정방형 크기 1MB이하</label>
				<div id="input-image"></div>
			</div>
		</div>
		</form:form>
		<div class="row mb-3">
			<div class="d-flex justify-content-between bd-highlight mb-3">
				<div></div>
				<div>
					<button type="button" class="btn btn-info" id="saveMnf">저장</button>
					<button type="button" class="btn btn-outline-secondary" onclick="history.back();">취소</button>
				</div>
				<div></div>
			</div>
		</div>
	</div>
</div>

<link href="/resources/image-uploader/dist/image-uploader.min.css" rel="stylesheet" />
<script src="/resources/image-uploader/dist/image-uploader.min.js"></script>
<script>
window.addEventListener('DOMContentLoaded', () => {

	/* 이미지 업로더 라이브러리 */
	let imageData = [];
	if(${mnfVo.fileNo != null}) {
		imageData.push({id: '${mnfVo.fileNo}', src: '/file/images/${mnfVo.fileNo}'});
	}
	$('#input-image').imageUploader({
		preloaded: imageData,
		label:'사진을 올려놓거나 여기를 클릭하세요.',
		extensions: ['.jpg','.jpeg','.png','.gif','.svg'],
		mimes: ['image/jpeg','image/png','image/gif','image/svg+xml'],
		maxSize: undefined,
		maxFiles: 1
	});
	document.querySelector('#input-image input[type="file"]').multiple = false;
	
	/* 로고 삭제 버튼 클릭 */
	/* if(${ mnfVo.mnfNo != null }) {
		document.getElementById('log_del').addEventListener('click', () => {
			if(confirm('로고를 삭제하시겠습니까?')) {
				let html = '';
				html += '<label class="form-label">정방형 크기 1MB이하</label>';
				html += '<input class="form-control upload_img" type="file" name="file">';
				document.getElementById('file_box').innerHTML = html;
			}
		});
	} */

	/* 제조사 저장 버튼 클릭 */
	document.getElementById('saveMnf').addEventListener('click', () => {
		let mnfNmInp = document.querySelector('input[name="mnfNm"]');
		let ntnCdSel = document.querySelector('select[name="ntnCd"]');
		let fileInp = document.querySelector('input[name="images[]"]');
		let fileNoInp = document.querySelector('input[name="preloaded[]"]');

		if(mnfNmInp.value.trim() == '') {
			alert('제조사명을 입력해주세요.');
			mnfNmInp.value = mnfNmInp.value.trim();
			mnfNmInp.focus();
		} else if(ntnCdSel.value == '') {
			alert('제조국을 선택해주세요.');
			ntnCdSel.focus();
		} else if(fileInp.files.length == 0 && (fileNoInp == null || fileNoInp.value == '')) {
			 alert('로고를 등록해주세요.');
			 fileInp.focus();
		} else if(confirm('저장 하시겠습니까?')) {
			let formData = new FormData();
			formData.append("mnfNo", document.querySelector('input[name="mnfNo"]').value);
			formData.append("mnfNm", mnfNmInp.value.trim());
			formData.append("ntnCd", ntnCdSel.value);
			if(fileInp.files.length > 0) 
				formData.append("file", fileInp.files[0]);
			if(fileNoInp != null) 
				formData.append("fileNo", fileNoInp.value);

			fn_saveMnf(formData);
		}
	});

});

function fn_saveMnf(formData) {
	axios({
		method: 'post',
	  	url: 'mnf',
	  	data: formData,
	  	headers: {'Content-Type': 'multipart/form-data'}
	}).then((res) => {
		location.href="mnfView?mnfNo=" + res.data.mnfNo;
	}).catch((err) => {
		if(err.response) {
			if(err.response.data.errors.length > 0) {
				alert('데이터를 확인해주세요.\n저장 실패하였습니다.');
				console.log(err);
			} else {
				alert(err.response.data.message + '\n저장 실패하였습니다.');
				console.log(err);
			}
		} else if(err.request) {
			alert('저장 실패하였습니다.');
			console.log(err.request);
		} else {
			alert('저장 실패하였습니다.');
			console.log(err);
		}
	});
}
</script>
