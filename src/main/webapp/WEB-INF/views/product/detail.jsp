<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link href="/css/detail.css" rel="stylesheet">
<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.users.id==principal.user.id }">
		<button id="btn-delete" class="btn btn-danger">삭제</button>
		<a href="/product/${product.id}/updateForm" class="btn btn-warning">수정</a>
	</c:if>
	<hr>
	<div>
		<br/><br/>
		<h2 class="proName">${product.name}</h2>
		<div class="proForm">
			<span class="pro">
				
				<img src="/img/${product.imgName}" class="img">
			</span>
			<span class="pro pro2">
				<table>
					<tr><th>한정 판매</th></tr>
					<tr><td>오직 dysson에서만 구매 가능한 상품입니다.</td></tr>
				</table>
				<hr>
				<section class="proInfo">
				<h5 class="title-box font-mss">Product Info <span class="korSub"> 제품정보</span></h5>
				<table>
					<tr>
						<td>브랜드 / 품번&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><span id="id"><i>${product.made} / ${product.id}</i></span></td>
					</tr>
					<tr>
						<td>카테고리</td>
						<td><span id="category"><a href="#" class="hash">#${product.category}</a></span></td>
					</tr>

					<tr>
						<td>가 격</td>
						<th><span id="price"><i><fmt:formatNumber value="${product.price}" pattern="#,###"/>원</i></span></th>
					</tr>
				</table>
				</section><hr>
				<section class="delInfo">
				<h5 class="title-box font-mss">Delivery  Info <span class="korSub"> 배송정보</span></h5>
				<table>
					<tr>
						<td>출고 정보&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><i>결제 3일 이내 출고</i></td>
					</tr>
					<tr>
						<td>배송 방법</td>
						<td><i>국내 배송 / 입점사 배송</i></td>
					</tr>
				</table>
				</section><hr>
				<section class="buy">
					<button class="btn btn_buy">바로구매</button>
					<button><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-cart-plus" viewBox="0 0 16 16">
							  <path d="M9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9V5.5z"/>
							  <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/></svg>
					</button>
				</section>
			</span>
		</div>
	</div>
	<hr/>
	<div>
		<h3>제품 상세</h3>
		--------------------------------------------
		<div>${product.content}</div>
	</div>
	<hr/>
</div>
<br/>
<script type="text/javascript" src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp"%>