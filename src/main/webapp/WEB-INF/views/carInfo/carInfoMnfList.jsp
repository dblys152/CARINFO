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
			</div>
		</div>
		<div class="col-md-4">
			<div class="input-group mb-3">
			  <input class="form-control" type="text" placeholder="Search" aria-label="Search">
			  <div class="input-group-append">
			    <button type="button" class="input-group-text lime lighten-2">
			    	<i class="fas fa-search"></i>
			    </button>
			  </div>
			</div>
		</div>
		<table class="table table-hover" id="mnfTbl">
		<colgroup>
			<col width = "50px">
			<col width = "80px">
			<col width = "150px">
			<col width = "150px">
			<col width = "150px">
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
			<ul class="pagination justify-content-center my-4">
				<li class="page-item disabled"><a class="page-link" href="#" tabindex="-1" aria-disabled="true">Newer</a></li>
				<li class="page-item active" aria-current="page"><a class="page-link" href="#!">1</a></li>
				<li class="page-item"><a class="page-link" href="#!">2</a></li>
				<li class="page-item"><a class="page-link" href="#!">3</a></li>
				<li class="page-item disabled"><a class="page-link" href="#!">...</a></li>
				<li class="page-item"><a class="page-link" href="#!">15</a></li>
				<li class="page-item"><a class="page-link" href="#!">Older</a></li>
			</ul>
		</nav>
	</div>
</div>

<script>
window.addEventListener('DOMContentLoaded', () => {
	listCore();
});

function listCore() {
	axios.get('mnfListCore')
	.then((res) => {
		let tbl = document.querySelector('#mnfTbl > tbody');
		tbl.innerHTML = res.data;

		let trs = document.querySelectorAll('#mnfTbl > tbody tr');
	    for(tr of trs) {
			tr.addEventListener('click', () => {
				location.href="mnfView?mnfNo=" + tr.querySelector('input[name="mnfNo"]').value
			});
	    }

	}).catch((err) => {
    	console.log(err);
	});
}

</script>
