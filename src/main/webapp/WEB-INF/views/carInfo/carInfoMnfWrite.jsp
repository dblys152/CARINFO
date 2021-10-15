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
            <p class="lead mb-0">제조사 등록</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조사명<span class="text-danger">*</span></label>
			<div class="col-sm-10">
			  <input type="text" class="form-control">
			</div>
		</div>
		<div class="mb-3 row">
		 	<label for="inputPassword" class="col-sm-2 col-form-label">제조국<span class="text-danger">*</span></label>
			<div class="col-sm-10">
				<select class="form-select" aria-label="Default select example">
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
			<div class="d-flex justify-content-center">
				<button type="button" class="btn btn-info">등록</button>
				<a href="mnfList" class="btn btn-outline-secondary">취소</a>
			</div>
		</div>
	</div>
</div>
