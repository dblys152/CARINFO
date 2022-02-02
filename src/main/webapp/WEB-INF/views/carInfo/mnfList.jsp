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
			<a href="carMdlList" class="btn btn-outline-secondary">자동차 모델</a>
			<a href="mnfList" type="button" class="btn btn-secondary">제조사</a>
			<div class="float-end">
				<a href="mnfWrite" class="btn btn-info">제조사 등록</a>
				<a href="mnfExcelWrite" class="btn btn-success">제조사 일괄등록</a>
			</div>
		</div>
		<form:form modelAttribute="searchVo" method="get">
		<div class="col-md-6">
			<form:hidden path="pageNo"/>
			<div class="input-group mb-2">
				<form:input path="schText" class="form-control" placeholder="제조사명을 입력하세요." aria-label="Search"/>
			  	<button type="button" id="schBtn" class="btn btn-secondary me-1"><i class="bi bi-search"></i></button>
			  	<a href="mnfList" class="btn btn-outline-secondary">초기화</a>
			</div>
		</div>
		<div class="d-flex bd-highlight mb-3">
			<div class="me-auto bd-highlight">
				<button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#mnfModal">
					정렬순서 변경
				</button>
			</div>
			<div class="p-2 bd-highlight">
				<div class="form-check">
					<form:checkbox path="ordDesc" cssClass="form-check-input"/>
					<label class="form-check-label">정렬순서 역순</label>
				</div>
			</div>
			<div class="bd-highlight">
				<button type="button" id="excelDown" class="btn btn-success">Excel 다운로드</button>
			</div>
		</div>
		</form:form>
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

<!-- Modal -->
<div class="modal fade" id="mnfModal" tabindex="-1" aria-hidden="true">
 	<div class="modal-dialog modal-lg modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">제조사 정렬순서 변경</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>드래그 앤 드롭으로 정렬순서를 변경 가능합니다.</p>
				<div class="row mb-5 row-cols-1 row-cols-md-5 g-4 overflow-auto" id="mnfBox"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="saveMnfSrtOrd">저장</button>
				<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<link href="/resources/dragula/dragula.min.css" rel="stylesheet" />
<script src="/resources/dragula/dragula.min.js"></script>
<script>
let drake = window.dragula();	// dragula 변수 (드래그앤드롭)
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

	/* 엑셀 다운로드 */
	document.getElementById('excelDown').addEventListener('click', fn_mnfExcelDown);

	/* 정렬순서 변경 팝업 */
	document.querySelector('button[data-bs-target="#mnfModal"]').addEventListener('click', fn_popMnfListCore);

	/* 정렬순서 저장 */
	document.getElementById('saveMnfSrtOrd').addEventListener('click', () => {
		let mnfList = document.querySelectorAll('#mnfBox input[name="mnfNo"]'),
			mnfListLen = mnfList.length;
		if(mnfListLen == 0) {
			alert('저장할 데이터가 없습니다.');
		} else if(confirm('정렬순서를 저장하시겠습니까?')) {
			let mnfNoList = []
			for(let i = 0; i < mnfListLen; i++) {
				mnfNoList.push(mnfList[i].value);
			}
			fn_saveMnfSrtOrd(mnfNoList);
		}
	});
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
	  	url: 'mnfListCore',
	  	params: {
			"pageNo": form[fname="pageNo"].value
		  , "schText": form[fname="schText"].value
		  , "ordDesc": form[fname="ordDesc"].checked
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

function fn_popMnfListCore() {
	axios({
		method: 'get',
	  	url: 'mnfListPopCore',
	  	params: {}
	}).then((res) => {
		document.querySelector('#mnfBox').innerHTML = res.data;
		let scripts = document.querySelector('#mnfBox').getElementsByTagName("script");
		for (let i = 0; i < scripts.length; i++) {
		    new Function(scripts[i].innerText)();	//스크립트 주입
		    scripts[i].remove();					//스크립트 태그 제거
		} 
		fn_setupDragula();
	}).catch((err) => {
    	console.log(err);
	});
}

function fn_setupDragula() {
    drake.destroy();
    drake = dragula([document.querySelector('#mnfBox')]);
}

function fn_saveMnfSrtOrd(mnfNoList) {
	axios({
		method: 'post',
	  	url: 'mnfSrtOrd',
	 	data: JSON.stringify(mnfNoList),
	 	headers: {'Content-Type': 'application/json'}
	}).then((res) => {
		location.href="mnfList";
	}).catch((err) => {
		alert('저장 실패하였습니다.');
    	console.log(err);
	});
}

function fn_mnfExcelDown() {
	axios({
		method: 'get',
	  	url: 'mnfExcelDown',
	  	responseType: 'blob',
	  	responseEncoding: 'utf8'
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
