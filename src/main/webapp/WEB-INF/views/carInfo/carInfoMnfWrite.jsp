<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>>

<script>
//menu 활성화
gnbActive = 'setting';
</script>

<!-- Page content-->
<div class="row">
	<!-- Page header-->
	<div class="py-3 bg-light border-bottom mb-4">
        <div class="my-1">
            <p class="lead mb-0">제조사 등록</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<input type="hidden" name="mnfNo" value="<c:out value="${ vo.mnfNo }"/>">
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조사명<span class="text-danger">*</span></label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" name="mnfNm" value="<c:out value="${ vo.mnfNm }"/>">
			</div>
		</div>
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조국<span class="text-danger">*</span></label>
			<div class="col-sm-10">
				<select class="form-select" aria-label="Default select example" name="ntnCd">
					<c:forEach items="${ ntnCdList }" var="i">
					<option value="<c:out value="${ i.ntnCd }"/>"><c:out value="${ i.ntnCdKrNm } (${ i.ntnCdEnNm })"/></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조사 로고<span class="text-danger">*</span></label>
		 	<div class="col-sm-10">
				<label for="formFile" class="form-label">로고 크기 50x50</label>
				<input class="form-control" type="file" id="formFile">
			</div>
		</div>
		<div class="mb-3 row">
			<div class="d-flex justify-content-between bd-highlight mb-3">
				<div></div>
				<div>
					<button type="button" class="btn btn-info" id="mnfSave">등록</button>
					<a href="mnfList" class="btn btn-outline-secondary">취소</a>
				</div>
				<div></div>
			</div>
		</div>
	</div>
</div>

<script>
window.addEventListener('DOMContentLoaded', () => {

	document.getElementById('mnfSave').addEventListener('click', () => {
		let mnfNmInp = document.querySelector('input[name="mnfNm"]');
		if(mnfNmInp.value == '') {
			alert("제조사명을 입력해주세요.");
			mnfNmInp.focus();
		} else {
			let dataForm = {
				"mnfNo": document.querySelector('input[name="mnfNo"]').value
			  , "mnfNm": mnfNmInp.value
			  , "ntnCd": document.querySelector('select[name="ntnCd"]').value
			};
			fn_saveMnf(dataForm);
		}
	});

});

function fn_saveMnf(dataForm) {
	axios.post('mnfWrite', dataForm)
	.then((res) => {
		location.href="mnfView?mnfNo=" + res.data.mnfNo;
	}).catch((err) => {
    	console.log(err);
	});
}
</script>
