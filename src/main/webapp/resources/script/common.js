var gnbActive;	//메뉴 gnb

$(function() {

	//gnb, lnb active
    $('.navbar-nav .nav-link').removeClass('active');
    var gnbTest = gnbActive === null && gnbActive === undefined;
    $('.navbar-nav .'+gnbActive).addClass('active');


    $('.upload_img').change(function() {
		var fileVal = $(this).val().split('\\');
		var fileName = fileVal[fileVal.length-1]; // 파일명
	    if (fileVal && fileName != '') {
	        //확장자 확인
	    	var ext = fileName.split('.').pop().toLowerCase();
			if(ext != 'png' && ext != 'jpg' && ext != 'jpeg') {
				alert('이미지 파일만 업로드가 가능합니다.');
				$(this).val('');
			}
	    }
	});

});

function paging(pageSize, totCnt, listCnt, pageNo) {
	//화면에 표시할 페이지 번호 개수, 총 데이터 수, 페이지당 표시할 데이터 수, 현재 페이지 번호

	let pageCnt = Math.ceil(totCnt / listCnt);	//총 페이지 번호 개수
	let start = Math.floor((pageNo - 1) / pageSize) * pageSize + 1;	//시작 페이지 번호
	let end = (start + pageSize < pageCnt ? start + pageSize : pageCnt + 1); //끝 페이지 번호

	let html = '';
	if(start > 1) {		//시작점 페이지가 아닌 경우 왼쪽 이동 버튼 활성화
		html += '<li class="page-item"><button type="button" onclick="list(1)" class="page-link"><<</button></li>';
		html += '<li class="page-item"><button type="button" onclick="list('+ (start - pageSize) +')" class="page-link"><</button></li>';
	} else {	//disabled 처리
		html += '<li class="page-item disabled"><button type="button" class="page-link" tabindex="-1" aria-disabled="true"><</button></li>';
	}

	for(let i = start; i < end; i++) {
		html += '<li class="page-item ' + (i == pageNo ? 'active' : '') + '" aria-current="page"><button type="button" onclick="list('+ i +')" class="page-link">'+ i +'</button></li>';
	}

	if(end < pageCnt + 1) {	//마지막점 페이지가 아닌 경우 오른쪽 이동 버튼 활성화
		html += '<li class="page-item"><button type="button" onclick="list('+ (start + pageSize) +')" class="page-link">></button></li>';
		html += '<li class="page-item"><button type="button" onclick="list('+ pageCnt +')" class="page-link">>></button></li>';
	} else {	//disabled 처리
		html += '<li class="page-item disabled"><button type="button" class="page-link" tabindex="-1" aria-disabled="true">></button></li>';
	}

	$('#paging').html(html);
}
