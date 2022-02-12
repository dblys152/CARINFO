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
            <p class="lead mb-0 fw-bold">자동차 모델 ${ carMdlVo.carMdlNo == null ? '등록' : '수정 ' }</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<form:form modelAttribute="carMdlVo" enctype="multipart/form-data">
		<form:hidden path="carMdlNo"/>
		<input type="text" style="display:none;"/>

		<div class="row mb-5 row-cols-1 row-cols-md-6 g-4 overflow-auto" id="mnfBox" style="height: 310px" >
			<label class="col-sm-2 col-form-label">제조사 선택<span class="text-danger">*</span></label>
			<c:forEach items="${ mnfList }" var="i">
			<div class="col">
				<div class="card ${ carMdlVo.carMdlNo == null ? '' : carMdlVo.mnfNo == i.mnfNo ? 'mnf_choice' : '' }" style="align-items: center;cursor: pointer;">
					<input type="hidden" name="mnfNo" value="<c:out value="${ i.mnfNo }"/>">
					<img src="/file/images/<c:out value="${ i.fileNo }"/>" class="card-img-top" style="width:50px;height:50px">
					<span class="card-text"><c:out value="${ i.mnfNm }"/></span>
				</div>
			</div>
			</c:forEach>
		</div>

		<div class="row mb-3">
		 	<label class="col-sm-2 col-form-label">모델명<span class="text-danger">*</span></label>
			<div class="col-sm-10">
				<form:input path="carMdlNm" class="form-control" maxlength="50"/>
			</div>
		</div>
		<div class="row mb-3">
		 	<label class="col-sm-2 col-form-label">외형<span class="text-danger">*</span></label>
			<div class="col-sm-10">
				<form:select path="carAprnCd" class="form-select">
					<form:option value="">선택하세요.</form:option>
					<c:forEach items="${ carAprnCdList }" var="i">
					<form:option value="${ i.cmnCd }"><c:out value="${ i.cmnCdNm }"/></form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="row mb-3">
		 	<label class="col-sm-2 col-form-label">종류<span class="text-danger">*</span></label>
			<div class="col-sm-10">
				<form:select path="carKnCd" class="form-select">
					<form:option value="">선택하세요.</form:option>
					<c:forEach items="${ carKnCdList }" var="i">
					<form:option value="${ i.cmnCd }"><c:out value="${ i.cmnCdNm }"/></form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		</form:form>
		<div class="mb-3 row">
			<div class="d-flex justify-content-between bd-highlight mb-3">
				<div></div>
				<div>
					<button type="button" class="btn btn-info" id=saveCarMdl>저장</button>
					<button type="button" class="btn btn-outline-secondary" onclick="history.back();">취소</button>
				</div>
				<div></div>
			</div>
		</div>
	</div>
</div>

<script>
window.addEventListener('DOMContentLoaded', () => {

	/* 제조사 선택 이벤트 */
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

	/* 자동자모델 저장 버튼 클릭 */
	document.getElementById('saveCarMdl').addEventListener('click', () => {
		let mnfCard = document.querySelector('#mnfBox .mnf_choice');
		let carMdlNmInp = document.querySelector('input[name="carMdlNm"]');
		let carAprnCdSel = document.querySelector('select[name="carAprnCd"]');
		let carKnCdSel = document.querySelector('select[name="carKnCd"]');

		if(mnfCard == null) {
			alert('제조사를 선택해주세요.');
		} else if(carMdlNmInp.value.trim() == '') {
			alert('모델명을 입력해주세요.');
			carMdlNmInp.value = carMdlNmInp.value.trim();
			carMdlNmInp.focus();
		} else if(carAprnCdSel.value == '') {
			alert('외형을 선택해주세요.');
			carAprnCdSel.focus();
		} else if(carKnCdSel.value == '') {
			alert('종류을 선택해주세요.');
			carKnCdSel.focus();
		} else if(confirm('저장 하시겠습니까?')) {
			let dataForm = {
				"carMdlNo": document.querySelector('input[name="carMdlNo"]').value,
				"carMdlNm": carMdlNmInp.value.trim(),
				"mnfNo": mnfCard.querySelector('input[name="mnfNo"]').value,
				"carAprnCd": carAprnCdSel.value,
				"carKnCd": carKnCdSel.value
			};

			fn_saveCarMdl(dataForm);
		}
	});

});

function fn_saveCarMdl(dataForm) {
	axios({
		method: 'post',
	  	url: 'car-mdl',
	 	data: JSON.stringify(dataForm),
	 	headers: {'Content-Type': 'application/json'}
	}).then((res) => {
		location.href="carMdlView?carMdlNo=" + res.data.carMdlNo;
	}).catch((err) => {
		alert('저장 실패하였습니다.');
    	console.log(err);
	});
}
</script>
