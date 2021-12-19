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
            <p class="lead mb-0">제조사 일괄등록</p>
        </div>
	</div>
	<!-- content -->
	<div class="col-lg-12">
		<div class="mb-3 row">
		 	<div class="col-sm-8" id="file_box">
				<label for="formFile" class="form-label">엑셀 파일 등록 (.xls, .xlsx)</label>
				<input class="form-control upload_excel" type="file" name="file">
			</div>
		</div>
		<div class="mb-3 row">
			<div class="d-flex justify-content-between bd-highlight mb-3">
				<div></div>
				<div>
					<button type="button" class="btn btn-success" id="excelConvert">데이터 확인</button>
				</div>
				<div></div>
			</div>
		</div>
		<div id="example" class="hot "></div>
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

	const data = [
	  ['', 'Tesla', 'Volvo', 'Toyota', 'Ford'],
	  ['2019', 10, 11, 12, 13],
	  ['2020', 20, 11, 14, 13],
	  ['2021', 30, 15, 12, 13]
	];

	const container = document.getElementById('example');
	const hot = new Handsontable(container, {
	  data: data,
	  rowHeaders: true,
	  colHeaders: true,
	  height: 'auto',
	  licenseKey: 'non-commercial-and-evaluation' // for non-commercial use only
	});

	/* 데이터 확인 버튼 클릭 */
	document.getElementById('excelConvert').addEventListener('click', () => {
		let fileInp = document.querySelector('input[name="file"]');
		if(fileInp == null || fileInp.value == '') {
			 alert('엑셀 파일을 등록해주세요.');
			 fileInp.focus();
		} else {
			let formData = new FormData();
			formData.append("file", fileInp.files[0]);

			fn_convertExcel(formData);
		}
	});

	/* 제조사 저장 버튼 클릭 */
	document.getElementById('mnfSave').addEventListener('click', () => {
		let mnfNmInp = document.querySelector('input[name="mnfNm"]');
		let fileInp = document.querySelector('input[name="file"]');
		let fileNoInp = document.querySelector('input[name="fileNo"]');

		if(mnfNmInp.value == '') {
			alert('제조사명을 입력해주세요.');
			mnfNmInp.focus();
		} else if((fileInp == null || fileInp.value == '') && (fileNoInp == null || fileNoInp.value == '')) {
			 alert('로고를 등록해주세요.');
			 fileInp.focus();
		} else if(confirm('저장 하시겠습니까?')) {
			let formData = new FormData();
			formData.append("mnfNo", document.querySelector('input[name="mnfNo"]').value);
			formData.append("mnfNm", mnfNmInp.value);
			formData.append("ntnCd", document.querySelector('select[name="ntnCd"]').value);
			if(fileInp != null) formData.append("file", fileInp.files[0]);
			if(fileNoInp != null) formData.append("fileNo", fileNoInp.value);

			fn_saveMnf(formData);
		}
	});

});

function fn_convertExcel(formData) {
	axios({
		method: 'post'
	  , url: 'mnfExcelConvert'
	  , data: formData
	  , headers: {'Content-Type': 'multipart/form-data'}
	}).then((res) => {

	}).catch((err) => {
		alert('변환 실패하였습니다.');
    	console.log(err);
	});
}

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
