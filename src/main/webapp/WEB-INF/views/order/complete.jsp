<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link href="/css/complete.css" rel="stylesheet">

	<div class="container">
		<hr>
		<img src="/image/check.png" class="img">
		<h3>주문이 정삭적으로 완료되었습니다.</h3>
		<h5>저희 다이쓴을 이용해주셔서 감사합니다.</h5>
		<button class="btn_order btn" onclick="location.href='/mypage/${principal.user.id}'">주문상세보기</button>
		<button class="btn_home btn" onclick="location.href='/'">메인으로</button>
		<hr>
	</div>

<%@ include file="../layout/footer.jsp"%>