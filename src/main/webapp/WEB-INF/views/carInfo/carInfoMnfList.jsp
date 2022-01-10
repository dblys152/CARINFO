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
            <p class="lead mb-0">자동차정보관리</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<div class="mb-3">
			<a href="mdlList" class="btn btn-outline-secondary">자동차 모델</a>
			<a href="mnfList" type="button" class="btn btn-secondary">제조사</a>
			<div class="float-end">
				<a href="mnfWrite" class="btn btn-info">제조사 등록</a>
				<a href="mnfExcelWrite" class="btn btn-success">제조사 일괄등록</a>
				<button type="button" id="excelDown" class="btn btn-success">Excel 다운로드</button>
			</div>
		</div>
		<div class="col-md-4">
			<form:form modelAttribute="searchVo" method="get">
			<form:hidden path="pageNo"/>
			<div class="input-group mb-3">
				<form:input path="schText" class="form-control" placeholder="Search" aria-label="Search"/>
			  	<button type="button" id="schBtn" class="btn btn-secondary me-1"><i class="bi bi-search"></i></button>
			  	<a href="mnfList" class="btn btn-outline-secondary">초기화</a>
			</div>
			</form:form>
		</div>
		<table class="table table-hover align-middle" id="mnfTbl">
		<colgroup>
			<col width="50px"><col width="80px"><col width="150px">
			<col width="150px"><col width="150px">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">로고</th>
				<th scope="col">제조사명</th>
				<th scope="col">제조국</th>
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
	listCore();	//데이터 목록 조회

	/* 검색 버튼 클릭 */
	document.getElementById('schBtn').addEventListener('click', () => {
		list(1);
	});

	/* 검색어 엔터 클릭 검색 */
	document.getElementById('schText').addEventListener('keydown', (e) => {
		if(e.keyCode==13) list(1);
	});

	/* 엑셀 다운로드 */
	document.getElementById('excelDown').addEventListener('click', fn_mnfExcelDown)

});

function list(n) {
	if(n == null) n = 1;
	let form = document.forms["searchVo"];
	form[fname="pageNo"].value = n;
	form.submit();
}

function listCore() {
	let form = document.forms["searchVo"];
	axios({
		method: 'get'
	  , url: 'mnfListCore'
	  , params: {
			"pageNo": form[fname="pageNo"].value
		  , "schText": form[fname="schText"].value
		}
	}).then((res) => {
		document.querySelector('#mnfTbl > tbody').innerHTML = res.data;

		let scripts = document.querySelector('#mnfTbl > tbody').getElementsByTagName("script");
		for (let i = 0; i < scripts.length; i++) {
		    new Function(scripts[i].innerText)();	//스크립트 주입
		    scripts[i].remove();					//스크립트 태그 제거
		}
	}).catch((err) => {
    	console.log(err);
	});
}

function fn_mnfExcelDown() {
	axios({
		method: 'get'
	  , url: 'mnfExcelDown'
	  , responseType: 'blob'
	  , responseEncoding: 'utf8'
	}).then((res) => {
		let name = res.headers["content-disposition"]
					.split("filename=")[1]
	    			.replace(/"/g, "");
		name = decodeURIComponent(name);
		let blob = new Blob([res.data], {type: res.data.type});
        let url = window.URL.createObjectURL(blob);
        let link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", name);
        link.style.cssText = "display:none";
        document.body.appendChild(link);
        link.click();
        link.remove();
        window.URL.revokeObjectURL(url);	//메모리 누수 방지
	}).catch((err) => {
		alert('Excel 다운로드 실패하였습니다.');
    	console.log(err);
	});
}

</script>
