<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.users.id==principal.user.id }">
		<button id="btn-delete" class="btn btn-danger">삭제</button>
		<a href="/product/${product.id}/updateForm" class="btn btn-warning">수정</a>
	</c:if>
	<div>
		<br/><br/>
		<div>
			제품번호 : <span id="id"><i>${product.id} </i></span><br>
			제품 명 : <span id="name"><i>${product.name} </i></span><br> 
			제품소개 : <span id="content"><i>${product.content} </i></span><br>
			카테고리 : <span id="category"><i>${product.category} </i></span><br>
			가 격 : <span id="price"><i>${product.price} </i></span><br>
			제조사 : <span id="made"><i>${product.made} </i></span><br>
		</div>
		<br/>
		<h3>${product.name}</h3>
	</div>
	<hr/>
	<div>
		<div>${product.content}</div>
	</div>
	<hr/>
</div>
<br/>
<script type="text/javascript" src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp"%>