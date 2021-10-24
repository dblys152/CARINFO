var gnbActive;

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