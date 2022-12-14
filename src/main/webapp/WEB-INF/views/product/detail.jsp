<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link href="/css/detail.css" rel="stylesheet">
<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.users.id==principal.user.id }">
		<button id="btn-delete" class="btn btn-danger">삭제</button>
		<a href="/product/${product.id}/updateForm" class="btn btn-warning">수정</a>
	</c:if>
	<div>
		<br/><br/>

		<div class="proForm">
			<span class="pro">
				<img src="/img/${product.imgName}" class="img">
			</span>
			<span class="pro pro2">
				<span id="name"><h3>${product.name}</h3></span><hr>
				<table>
<%-- 					<tr>
						<td>제품번호 : </td>
						<td><span id="id"><i>${product.id}</i></span></td>
					</tr> --%>
					<tr>
						<td>카테고리 : </td>
						<td><span id="category"><i>${product.category}</i></span></td>
					</tr>
					<tr>
						<td>가 격 : </td>
						<td><span id="price"><i><fmt:formatNumber value="${product.price}" pattern="#,###"/>원</i></span></td>
					</tr>
					<tr>
						<td>제조사 : </td>
						<td><span id="made"><i>${product.made}</i></span></td>
					</tr>
				</table>
			</span>
		</div>
	</div>
	<hr/>
	<div>
		<h3>제품 상세</h3>
		<div>${product.content}</div>
	</div>
	<hr/>
</div>
<br/>
<script type="text/javascript" src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp"%>