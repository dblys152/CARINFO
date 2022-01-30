<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
//menu 활성화
gnbActive = 'setting';
</script>

<style>
.choice {
	background: #dcdcdc
}
</style>

<!-- Page content-->
<div class="row">
	<!-- Page header-->
	<div class="py-3 bg-light border-bottom mb-4">
        <div class="my-1">
            <p class="lead mb-0">자동차 모델 ${ carMdlVo.carMdlNo == null ? '등록' : '수정 ' }</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<form:form modelAttribute="carMdlVo" enctype="multipart/form-data">
		<form:hidden path="carMdlNo"/>

		<div class="row mb-5 row-cols-1 row-cols-md-6 g-4">
			<label class="col-sm-2 col-form-label">제조사 선택<span class="text-danger">*</span></label>
			<c:forEach items="${ mnfList }" var="i">
			<div class="col" id="mnfBox">
				<div class="card" style="align-items: center;cursor: pointer;">
					<img src="/file/images/<c:out value="${ i.fileNo }"/>" class="card-img-top" style="width:50px;height:50px">
					<span class="card-text"><c:out value="${ i.mnfNm }"/></span>
				</div>
			</div>
			</c:forEach>
		</div>

		<div class="row mb-3">
		 	<label class="col-sm-2 col-form-label">모델명<span class="text-danger">*</span></label>
			<div class="col-sm-10">
			  <form:input class="form-control" path="carMdlNm"/>
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

	let mnfCards = document.querySelectorAll('#mnfBox .card');
	for(let i = 0; i < mnfCards.length; i++) {
		mnfCards[i].addEventListener('click', (e) => {
			for(let i = 0; i < mnfCards.length; i++) {
				mnfCards[i].classList.remove('choice');
			}

			let thisMnf = e.currentTarget;
			if(!thisMnf.classList.contains('choice')) {
				thisMnf.classList.add('choice');
			}
		});
	}

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
