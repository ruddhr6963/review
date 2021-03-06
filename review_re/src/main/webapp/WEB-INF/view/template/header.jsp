<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	<title>리뷰 사이트</title>
<!-- 	<meta charset="utf-8"> -->
<!-- 	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->

   <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
   <script type="text/javascript" src="/review/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
   <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
	<script>
	$(document).ready(function(){
		$(".sub-wrap").on("click", function(){
			console.log($(this).childrens().text());
		});
	});
	</script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/layout.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/design.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap/bootstrap.css" />">
<style>
@font-face{
	font-family: bmdohyeon;
	src: url('font/BMDOHYEON_ttf.ttf')
}
@font-face{
	font-family: binggrae;
	src: url('font/Binggrae.ttf')
}
@font-face{
	font-family: bmjua;
	src: url('font/BMJUA_ttf.ttf')
}
@font-face{
	font-family: hoonwhitecatr;
	src: url('HoonWhitecatR.ttf')
}
.menu-wrap>ul>li>a {
	text-decoration: none;
	font-size: 18px;
	font-family: binggrae;
	font-weight: bold;
	color : cornflowerblue;
}
/* 메인 메뉴 스타일 */
.menu-wrap {
/*  	background-color: gray; */
	width: 900px;
	margin: 0 auto;
}

.menu-wrap>ul {
	list-style: none;
	display: block;
	margin-top: 10px; 
}

.menu-wrap>ul>li {
	display: inline-block;
}

.menu-wrap> ul > li > ul > li > a {
	font-family: binggrae;
	display: inline-block;
	font-weight: bold;
	text-decoration: none;
	
}

/* 서브메뉴 스타일 */
.menu-wrap li ul {
	position: absolute;
	display: none;
	width: 150px;
 	background: dodgerblue;
 	opacity: 0.825;
	list-style: none;
	margin-top: 10px;
	margin-left: -10px;
	padding: 0px;
	font-size: 15px;
	border-radius: 5px;
}

.menu-wrap li {
	padding: 10px;
}

.menu-wrap li:hover {
	background-color: white;
	border-radius: 5px;
}

.menu-wrap li:hover>ul {
	display: block;
}

.right {
	margin-left: auto;
}



#main {   
	margin: 0 auto;
	padding: 0;
	display: flex;
	flex:auto; 
	width: 1000px;
}
 
#main > article {
	min-height: 800px;
	margin: 4px;
	padding: 5px;
/* 	border: 1px solid black; */
/* 	border-radius: 7pt; */
	width: 800px;
	order: 2;
}

#main > nav {
	min-height: 800px;
	margin: 4px;
	padding: 5px;
/* 	border: 1px solid black; */
/* 	border-radius: 7pt; */
	width: 200px;
	order: 1;
}
  
#main > aside {
	min-height: 800px;
	margin: 4px;
	padding: 5px;
/* 	border: 1px solid black; */
/* 	border-radius: 7pt; */
	width: 200px;
	order: 3;
}

.imgHeader{
	background-image: url('img/pencil.jpg');
	color: white;
	align-content: center;
	margin: 0 auto;
	width: 1000px;
	height: 300px;
	border: 0;
	padding: 180 0 0 80;
}

/* login-form */
.loginform-wrap{
	width: 200px;
	height: 10px;
}
.login_tab{
	border: 1;
	border-collapse: collapse;
}
.login_sub > input{
	margin: 0 auto;
	width: 55px;
	height: 50px;
	background-color: dodgerblue;
	color: white;
}
.input-group, form-control{
	height: 40px !important;
	margin: 0 auto !important;
}

.search-bar{
	margin-bottom: -20px;
}
.search-bar > button{
	width: 40px;
	height: 40px;
	background-color: white;
}
.searchicon{
	background-image: url('img/searchicon40.png');
}
</style>
</head>
<body>
	<%-- header (상단) --%>
	<header class="imgHeader">
		<div class="menu-wrap">
			<ul>
				<li><a href="#">Home</a></li>
				<li><a href="#">국내영화</a>
					<ul>
						<li><a href="#">SF/판타지</a></li>
						<li><a href="#">드라마</a></li>
						<li><a href="#">전쟁/모험</a></li>
						<li><a href="#">미스터리/스릴러</a></li>
						<li><a href="#">애니메이션</a></li>
						<li><a href="#">코미디</a></li>
						<li><a href="#">액션/느와르</a></li>
						<li><a href="#">기타</a></li>
					</ul></li>
				<li><a href="#">해외영화</a>
					<ul>
						<li><a href="#">SF/판타지</a></li>
						<li><a href="#">드라마</a></li>
						<li><a href="#">전쟁/모험</a></li>
						<li><a href="#">미스터리/스릴러</a></li>
						<li><a href="#">애니메이션</a></li>
						<li><a href="#">코미디</a></li>
						<li><a href="#">액션/느와르</a></li>
						<li><a href="#">기타</a></li>
					</ul></li>
				<li><a href="${pageContext.request.contextPath}/list?item=1">국내도서</a>
					<ul>
						<li><a href="#">SF/판타지/무협</a></li>
						<li><a href="#">추리</a></li>
						<li><a href="#">로맨스</a></li>
						<li><a href="#">공포/스릴러</a></li>
						<li><a href="#">역사</a></li>
						<li><a href="#">시/에세이</a></li>
						<li><a href="#">철학/종교</a></li>
						<li><a href="#">과학</a></li>
						<li><a href="#">기타</a></li>
					</ul></li>
				<li><a href="${pageContext.request.contextPath}/list?item=2">해외도서</a>
					<ul>
						<li><a href="#">SF/판타지/무협</a></li>
						<li><a href="#">추리</a></li>
						<li><a href="#">로맨스</a></li>
						<li><a href="#">공포/스릴러</a></li>
						<li><a href="#">역사</a></li>
						<li><a href="#">시/에세이</a></li>
						<li><a href="#">철학/종교</a></li>
						<li><a href="#">과학</a></li>
						<li><a href="#">기타</a></li>
					</ul></li>
				<li><a href="#">기타</a></li>
				<li><a href="#">자유게시판</a></li>
				<li><a href="#">공지</a></li>
				<li><a href="attend">출석체크</a></li>
				<li>
				</li>
			</ul>
		</div>
		<div style="margin-left: 600px;">
			<form action="#">
				<div class="col-lg-6">
					<div class="input-group">
						<input type="search" style="width: 200px;" placeholder="검색어" class="form-control">
						<span class="input-group-btn search-bar">
							<button class="btn btn-default searchicon" type="button"></button>
						</span>
					</div>
				</div>
			</form>
		</div>
	</header>
	<%-- body (몸) --%>
	<div id='main'>
