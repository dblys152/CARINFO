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
			<a href="mdlList" class="btn btn-secondary">자동차 모델</a>
			<a href="mnfList" class="btn btn-outline-secondary">제조사</a>
			<div class="float-end">
				<a href="mnfWrite" class="btn btn-info">자동차 모델 등록</a>
			</div>
		</div>
		<div class="col-md-4">
			<div class="input-group mb-3">
				<input class="form-control" type="text" placeholder="Search" aria-label="Search">
			  	<button type="button" id="schBtn" class="btn btn-secondary me-1"><i class="bi bi-search"></i></button>
			  	<a href="mnfList" class="btn btn-outline-secondary">초기화</a>
			</div>
		</div>
		<table class="table table-hover align-middle">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">제조사명</th>
				<th scope="col">제조국</th>
				<th scope="col">등록일</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th scope="row">1</th>
				<td>BMW</td>
				<td>독일</td>
				<td>2021-10-11</td>
			</tr>
			<tr>
				<th scope="row">2</th>
				<td>포르쉐</td>
				<td>독일</td>
				<td>2021-10-11</td>
			</tr>
			<tr>
				<th scope="row">3</th>
				<td>폭스바겐</td>
				<td>독일</td>
				<td>2021-10-11</td>
			</tr>
		</tbody>
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
