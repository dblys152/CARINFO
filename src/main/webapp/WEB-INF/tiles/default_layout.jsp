<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<meta content="text/html; charset=utf-8" http-equiv="Content-type"/>
	<title>
		<tiles:getAsString name="title"/>
	</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/resources/bootstrap5/assets/favicon.ico" />
    <link href="/resources/bootstrap5/css/styles.css" rel="stylesheet" />
    <link href="/resources/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet" />
    <script src="/resources/bootstrap5/js/scripts.js"></script>
    <script src="/resources/script/jquery_v3.5.1.js"></script>
    <script src="/resources/script/common.js"></script>

    <script src="/resources/script/axios.min.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header"/>

	<div class="container">
		<!-- Content -->
		<div class="contents">
	  		<tiles:insertAttribute name="body"/>
	  	</div>
	</div>

	<tiles:insertAttribute name="footer"/>
</body>
</html>