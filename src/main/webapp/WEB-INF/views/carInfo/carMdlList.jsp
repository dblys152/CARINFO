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
            <p class="lead mb-0 fw-bold">자동차정보관리</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<div class="mb-3">
			<a href="carMdlList" class="btn btn-secondary">자동차 모델</a>
			<a href="mnfList" class="btn btn-outline-secondary">제조사</a>
			<div class="float-end">
				<a href="carMdlWrite" class="btn btn-info">자동차 모델 등록</a>
			</div>
		</div>
		<form:form modelAttribute="searchVo" method="get">
		<div class="col-md-6">
			<form:hidden path="pageNo"/>
			<div class="input-group mb-2">
				<form:input path="schText" class="form-control" placeholder="모델명을 입력하세요." aria-label="Search"/>
			  	<button type="button" id="schBtn" class="btn btn-secondary me-1"><i class="bi bi-search"></i></button>
			  	<a href="carMdlList" class="btn btn-outline-secondary">초기화</a>
			</div>
		</div>
		<div class="d-flex bd-highlight mb-3">
			<div class="me-auto bd-highlight">

			</div>
			<div class="p-2 bd-highlight">
				<div class="form-check">
					<form:checkbox path="ordDesc" cssClass="form-check-input"/>
					<label class="form-check-label">정렬순서 역순</label>
				</div>
			</div>
		</div>
		</form:form>
		<table class="table table-hover align-middle" id="carMdlTbl">
		<colgroup>
			<col width="50px"><col width="80px"><col width="150px">
			<col width="80px"><col width="80px"><col width="80px">
			<col width="150px">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">제조사</th>
				<th scope="col">모델명</th>
				<th scope="col">외형</th>
				<th scope="col">종류</th>
				<th scope="col">최근연식</th>
				<th scope="col">등록일</th>
			</tr>
		</thead>
		<tbody></tbody>
		</table>
		<!-- Pagination-->
		<nav aria-label="Pagination">
			<hr class="my-0" />
			<ul class="pagination justify-content-center my-4" id="paging"></ul>
		</nav>
	</div>
</div>

<script>
window.addEventListener('DOMContentLoaded', () => {
	fn_listCore();	//데이터 목록 조회

	/* 검색 버튼 클릭 */
	document.getElementById('schBtn').addEventListener('click', () => {
		fn_list(1);
	});

	/* 검색어 엔터 클릭 검색 */
	document.getElementById('schText').addEventListener('keydown', (e) => {
		if(e.keyCode == 13)
			fn_list(1);
	});

	/* 정렬순서 역순 체크 시 조회 */
	document.querySelector('input[name="ordDesc"]').addEventListener('click', fn_listCore);

});

function fn_list(n) {
	if(n == null) n = 1;
	let form = document.forms["searchVo"];
	form[fname="pageNo"].value = n;
	form.submit();
}

function fn_listCore() {
	let form = document.forms["searchVo"];
	axios({
		method: 'get',
	  	url: 'carMdlListCore',
	  	params: {
			"pageNo": form[fname="pageNo"].value
		  , "schText": form[fname="schText"].value
		  , "ordDesc": form[fname="ordDesc"].checked
		}
	}).then((res) => {
		document.querySelector('#carMdlTbl > tbody').innerHTML = res.data;

		let scripts = document.querySelector('#carMdlTbl > tbody').getElementsByTagName("script");
		for (let i = 0; i < scripts.length; i++) {
		    new Function(scripts[i].innerText)();	//스크립트 주입
		    scripts[i].remove();					//스크립트 태그 제거
		}
	}).catch((err) => {
    	console.log(err);
	});
}

</script>
