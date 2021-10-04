<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<meta content="text/html; charset=utf-8" http-equiv="Content-type"/>
	<title>
		<tiles:getAsString name="title"/>
	</title>
</head>
<body>
	<tiles:insertAttribute name="header"/>

	<section class="container">
		<!-- Content -->
		<div class="contents">
	  		<tiles:insertAttribute name="body"/>
	  	</div>
	</section>

	<tiles:insertAttribute name="footer"/>
</body>
</html>