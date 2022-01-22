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
				<div class="mt-3">
					<a href="<c:url value="/resources/sample/제조사등록샘플.xlsx"/>">제조사등록샘플파일.xlsx</a>
				</div>
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
		<div id="listHandson" class="mb-3"></div>
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

<script src="/resources/handsontable/select2-editor.js"></script>
<script>
let hot;	// handsontable 변수
const ntnJsonList = JSON.parse('${ ntnJsonList }');
let ntnList = [];
for(let i = 0; i < ntnJsonList.length; i++) {
	ntnList.push({'id': ntnJsonList[i].ntnCd, 'text': ntnJsonList[i].ntnCdKrNm + ' (' + ntnJsonList[i].ntnCdEnNm + ')'})
}

window.addEventListener('DOMContentLoaded', () => {
	/* 데이터 확인 버튼 클릭 */
	document.getElementById('excelConvert').addEventListener('click', () => {
		let fileInp = document.querySelector('input[name="file"]');
		if(fileInp == null || fileInp.value == '') {
			 alert('엑셀 파일을 등록해주세요.');
			 fileInp.focus();
		} else {
			let parsingFlag = 0;
			if($('.htCore').length == 0) {
				parsingFlag = 1;
			} else if(confirm('작성 중인 엑셀 파일이 있습니다. 변환하시겠습니까?')) {
				hot.destroy();
				parsingFlag = 1;
			}

			if(parsingFlag == 1) {
				let formData = new FormData();
				formData.append("file", fileInp.files[0]);
				fn_excelParser(formData);
			}
		}
	});

	/* 제조사 저장 버튼 클릭 */
	document.getElementById('mnfSave').addEventListener('click', () => {

	});

});

function fn_excelParser(formData) {
	axios({
		method: 'post'
	  , url: 'mnfExcelParser'
	  , data: formData
	  , headers: {'Content-Type': 'multipart/form-data'}
	}).then((res) => {
		fn_hansontableLoad(res.data.jsonArr);
	}).catch((err) => {
		alert('변환 실패하였습니다.');
    	console.log(err);
	});
}

function fn_hansontableLoad(data) {
	const dataObject = JSON.parse(data);
	const hotElement = document.querySelector('#listHandson');
	let hotSettings = {
		data: dataObject,
		licenseKey: 'non-commercial-and-evaluation', // for non-commercial use only
		height: 'auto',
		rowHeaders: true,
		colWidths: [100, 200, 200],
		colHeaders: [
			'제조사 로고<span class="text-danger">*</span>',
			'제조사명<span class="text-danger">*</span>',
			'국가코드<span class="text-danger">*</span>'
		],
		columns: [
			{data: 'fileNo', className:'htCenter htMiddle', readOnly: true, renderer: fn_customImgRenderer},
			{data: 'mnfNm', type: 'text', className:'htCenter htMiddle'},
			{data: 'ntnCd', type: 'key-value-list', filter: false, className:'htCenter htMiddle',
				source: ntnList, keyProperty: 'id', valueProperty: 'text'
			}
			/* {data: 'ntnCd', editor: 'select2', className:'htCenter htMiddle', renderer: fn_customSelectRenderer,
				select2Options: {
					data: ntnList,
                    dropdownAutoWidth: true,
                    allowClear: true,
                    width: 'resolve'
				}
			} */
		]
	}

	hot = new Handsontable(hotElement, hotSettings);
	hot.validateCells();
}

function fn_customImgRenderer(instance, td, row, col, prop, value, cellProperties) {
	Handsontable.renderers.TextRenderer.apply(this, arguments);
	if(value != null) {
		td.innerHTML = '<img src="/file/images/'+ value +'" style="width:50px;height:50px">';
	}
}

function fn_customSelectRenderer(instance, td, row, col, prop, value, cellProperties) {
	if (instance.getCell(row, col)) {
		$(instance.getCell(row,col)).addClass('select2dropdown');
	}
	for(let i = 0; i < ntnList.length; i++) {
		if(value == ntnList[i].id) {
			value = ntnList[i].text;
		}
	}
	Handsontable.renderers.TextRenderer.apply(this, arguments);
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
