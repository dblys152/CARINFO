<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
//menu 활성화
gnbActive = 'setting';
</script>

<!-- Page content-->
<div class="row">
	<!-- Page header-->
	<div class="py-3 bg-light border-bottom mb-4">
        <div class="my-1">
            <p class="lead mb-0">제조사 상세정보</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<input type="hidden" name="mnfNo" value="<c:out value="${ vo.mnfNo }"/>">
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조사명</label>
			<div class="col-sm-10">
			  <c:out value="${ vo.MNFNM }"/>
			</div>
		</div>
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조국</label>
			<div class="col-sm-10">
				<c:out value="${ vo.ntnCdKrNm } (${ vo.ntnCdEnNm })"/>
			</div>
		</div>
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조사 로고</label>
		 	<div class="col-sm-10">

			</div>
		</div>
		<div class="mb-3 row">
			<div class="d-flex justify-content-between bd-highlight mb-3">
				<div></div>
				<div>
					<a href="mnfWrite?mnfNo=${ param.mnfNo }" class="btn btn-info">수정</a>
					<a href="mnfList" class="btn btn-outline-secondary">목록</a>
				</div>
				<div>
					<button type="button" class="btn btn-danger" id="mnfDel">삭제</button>
				</div>
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
