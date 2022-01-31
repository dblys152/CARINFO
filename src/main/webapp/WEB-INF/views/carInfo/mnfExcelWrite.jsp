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
					<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#ntnCdModal">
						국가코드표
					</button>
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

<!-- Modal -->
<div class="modal fade" id="ntnCdModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
 	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">국가코드표</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="input-group mb-3">
					<input type="hidden">
					<input id="popSchText" class="form-control" placeholder="Search" aria-label="Search"/>
				  	<button type="button" id="popSchBtn" class="btn btn-secondary me-1"><i class="bi bi-search"></i></button>
				  	<button type="button" id="popSchReset" class="btn btn-outline-secondary">초기화</button>
				</div>
				<table class="table table-hover align-middle" id="popNtnTbl">
				<colgroup>
					<col style="width:150px"><col style="width:150px"><col style="width:100px">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">국가한글명</th>
						<th scope="col">국가영문명</th>
						<th scope="col">국가코드</th>
					</tr>
				</thead>
				<tbody></tbody>
				</table>
			</div>
			<!-- <div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div> -->
		</div>
	</div>
</div>

<link href="/resources/handsontable/handsontable.full.min.css" rel="stylesheet" />
<script src="/resources/handsontable/handsontable.full.min.js"></script>
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

	/* 국가코드표 팝업 */
	let form = document.forms["popSearchVo"];
	document.querySelector('button[data-bs-target="#ntnCdModal"]').addEventListener('click', () => {
		document.getElementById('popSchText').value = '';
		fn_popListCore();
	});
	/* 국가코드표 팝업 검색 버튼 클릭 */
	document.getElementById('popSchBtn').addEventListener('click', fn_popListCore);
	/* 국가코드표 팝업 검색어 엔터 클릭 검색 */
	document.getElementById('popSchText').addEventListener('keydown', (e) => {
		if(e.keyCode == 13)
			fn_popListCore();
	});
	/* 국가코드표 팝업 초기화 버튼 클릭 */
	document.getElementById('popSchReset').addEventListener('click', () => {
		document.getElementById('popSchText').value = '';
		fn_popListCore();
	});

	/* 제조사 저장 버튼 클릭 */
	document.getElementById('mnfSave').addEventListener('click', () => {
		if(document.querySelectorAll('.htCore').length == 0) {
			alert('데이터 확인된 파일이 없습니다.');
		} else {
			if(document.querySelectorAll('#listHandson .htInvalid').length > 0) {
				alert('데이터 형식을 확인해주세요.');
			} else if(confirm('제조사를 일괄 저장하시겠습니까?')) {
				fn_saveMnfList(hot.getSourceData());
			}
		}
	});

});

function fn_excelParser(formData) {
	axios({
		method: 'post',
	  	url: 'mnfExcelParser',
	  	data: formData,
	  	headers: {'Content-Type': 'multipart/form-data'}
	}).then((res) => {
		fn_hansontableLoad(res.data.jsonArr);
	}).catch((err) => {
		if(err.response) {
			if(err.response.data.errors.length > 0) {
				alert('데이터를 확인해주세요.\n변환 실패하였습니다.');
				console.log(err);
			} else {
				alert(err.response.data.message + '\n변환 실패하였습니다.');
				console.log(err);
			}
		} else if(err.request) {
			alert('변환 실패하였습니다.');
			console.log(err.request);
		} else {
			alert('변환 실패하였습니다.');
			console.log(err);
		}
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
		colWidths: [100, 200, 100, 200],
		colHeaders: [
			'제조사 로고<span class="text-danger">*</span>',
			'제조사명<span class="text-danger">*</span>',
			'국가코드<span class="text-danger">*</span>',
			'제조국'
		],
		columns: [
			{data: 'fileNo', type: 'numeric', className:'htCenter htMiddle', validator: fn_customValidator,
				readOnly: true,
				renderer: fn_customImgRenderer
			},
			{data: 'mnfNm', type: 'text', className:'htCenter htMiddle', validator: fn_customValidator},
			{data: 'ntnCd', type: 'text', className:'htCenter htMiddle', validator: fn_customValidator},
			{data: '', type: 'text', className:'htCenter htMiddle', readOnly: true}
		],
		contextMenu: {
			items: {
				"remove_row": {
					name: "행 삭제"
				}
			}
		},
		afterChange: function(changes, source) {
		  	if(changes != null && source != 'not') {
		  		for(let i = 0; i < changes.length; i++) {
					if(changes[i][1] == 'ntnCd') {
						let flag = 0;
						for(let ni = 0; ni < ntnList.length; ni++) {
							if(changes[i][3] == ntnList[ni].id) {
								hot.setDataAtCell(changes[i][0], 3, ntnList[ni].text);
								flag = 1;
								break;
							}
						}
						if(flag == 0)
							hot.setDataAtCell(changes[i][0], 3, null);
					}
		  		}
			}
			this.validateCells();
		},
		afterValidate: function(isValid, value, row, prop, source) {
			value = (value == null ? '' : (typeof value == 'string' ? value.trim() : value));
			if(prop == 'fileNo' && isNaN(parseInt(value))) {
				return false;
			} else if(prop == 'mnfNm' && value == '') {
				return false;
			} else if(prop == 'ntnCd' && value == '') {
				return false;
			} else {
				return true;
			}
		}
	}

	hot = new Handsontable(hotElement, hotSettings);
	hot.validateCells();
	for(let i = 0; i < hot.getSourceData().length; i++) {
		for(let ni = 0; ni < ntnList.length; ni++) {
			if(hot.getDataAtCell(i, 2).trim() == ntnList[ni].id) {
				hot.setDataAtCell(i, 3, ntnList[ni].text);
				break;
			}
		}
	}
}

function fn_customImgRenderer(instance, td, row, col, prop, value, cellProperties) {
	Handsontable.renderers.TextRenderer.apply(this, arguments);
	if(value != null) {
		td.innerHTML = '<img src="/file/images/'+ value +'" style="width:50px;height:50px">';
	}
}

function fn_customValidator(query, callback) {
	callback(true);
}

function fn_popListCore() {
	axios({
		method: 'get',
	  	url: 'ntnCdListPopCore',
	  	params: {
			"schText": document.getElementById('popSchText').value
		}
	}).then((res) => {
		document.querySelector('#popNtnTbl > tbody').innerHTML = res.data;
	}).catch((err) => {
    	console.log(err);
	});
}

function fn_saveMnfList(dataList) {
	axios({
		method: 'post',
	  	url: 'mnfExcelWrite',
	  	data: JSON.stringify(dataList),
	  	headers: {'Content-Type': 'application/json'}
	}).then((res) => {
		location.href="mnfList";
	}).catch((err) => {
		alert('저장 실패하였습니다.');
    	console.log(err);
	});
}

</script>
