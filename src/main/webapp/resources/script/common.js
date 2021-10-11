var gnbActive;

$(function() {

	//gnb, lnb active
    $('.navbar-nav .nav-link').removeClass('active');
    var gnbTest = gnbActive === null && gnbActive === undefined;
    $('.navbar-nav .'+gnbActive).addClass('active');



});