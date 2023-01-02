<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link href="/css/detail.css" rel="stylesheet">
<style>
#myform fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
}
#myform fieldset legend{
    text-align: right;
}
#myform input[type=radio]{
    display: none;
}
#myform label{
    font-size: 3em;
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
}
#myform label:hover{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform label:hover ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#reviewContents {
    width: 100%;
    height: 150px;
    padding: 10px;
    box-sizing: border-box;
    border: solid 1.5px #D3D3D3;
    border-radius: 5px;
    font-size: 16px;
    resize: none;
}
.rate {
	color:#FFB900;
	
	
}
.rate-image{
	background-image:url(/image/rate.PNG);
	background-repeat:no-repeat;
	background-position: 4px 9px;
	
}

.rate1 {
	

	color:#FFB900;
}
.rate-image1{
	display:flex;

/* 	background-image:url(/image/rate.PNG); */
	background-repeat:no-repeat;
	background-position: 10px 1px;
	padding-left:10px;
}
.nbsp {

	padding-left: 10px;
}

.rate-image2 {
	background-image:url(/image/rate.PNG);
	background-repeat:no-repeat;
	width: 95.594px;
	height: 30px;
	padding-top: 10px;
	background-position: 10.1px 10px;
	display: flex;
}
  	

  		
  	

</style>

<div class="container">
<%--	<div class="buttons">--%>

<%--	</div>--%>
	<c:if test="${principal.user.roles eq 'admin'}">
		<button id="btn-delete" class="btn btn-danger" onClick="index.deleteById(${product.id})">삭제</button>
		<a href="/product/updateForm/${product.id}" class="btn btn-warning">수정</a>
	</c:if>
	<hr>
	<div>
		<br/><br/>
		<h2 class="proName">${product.name}</h2>
		<div class="proForm">
			<span class="pro">
				
				<img src="/img/${product.imgName}" class="img">
			</span>
			<span class="pro pro2" style="text-align:left;">

				<table>
					<tr>
						<td class="rate-image2">
							<c:forEach var="product" items="${ratingAvg}" varStatus="status" begin="1" end="${product.ratingAvg}">
								<span  class="rate1">★</span>
							</c:forEach>
							&nbsp;
						</td>
						<td style="padding-left: 0; padding-top: 10.5px">(<fmt:formatNumber value="${product.ratingCount}" pattern="0"/>)</td>
					</tr>
				</table>
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
					<form class="d-flex" action="/users/cart/${principal.user.id}/${product.id}" method="post">
					<button type="button" class="btn btn_buy" onclick="inputValueChange()">바로구매</button>
                    <div class="d-flex">
                    <input class="form-control text-center me-3" id="amount" name="amount" type="num" value="1" style="max-width: 3rem"/>
						<button type="submit" style="border-radius: 0.375rem;" onclick="alert('장바구니에 등록되었습니다.')"><svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-cart-plus" viewBox="0 0 16 16" style="padding: 5px;">
							  <path d="M9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9V5.5z"></path>
							  <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"></path></svg>
						</button>
                    </div>
                </form>
				</section>
			</span>
		</div>
	</div>
	<hr/>
	<div>
		<h3>제품 상세</h3>
		<div>${product.content}</div>
	</div>
	<hr/>
	
	
	<!-- 리뷰 -->
	<h3> 제품 리뷰 </h3>
		<input type="hidden" id="usersId" value="${principal.user.id }"/>
		<input type="hidden" id="productId" value="${product.id }"/>
		<c:if test="${principal.user.id != null}">
	 	<form class="mb-3" name="myform" id="myform">
	<fieldset>
		<span class="text-bold">별점을 선택해주세요</span>
		<input type="radio" name="reviewStar" value="5" id="rate1" ><label
			for="rate1">★</label>
		<input type="radio" name="reviewStar" value="4" id="rate2" ><label
			for="rate2">★</label>
		<input type="radio" name="reviewStar" value="3" id="rate3" ><label
			for="rate3">★</label>
		<input type="radio" name="reviewStar" value="2" id="rate4" ><label
			for="rate4">★</label>
		<input type="radio" name="reviewStar" value="1" id="rate5" ><label
			for="rate5">★</label>
	</fieldset>

		
	<div>

		
		<textarea class="col-auto form-control" id="content" rows="1"
				  placeholder="좋은 상품평을 남겨주시면 Dysson에 큰 힘이 됩니다! 포인트 5000p도 지급!!"></textarea>
		
	</div>
	<div class="d-flex flex-row-reverse">
		<button type="button" class="btn btn_review" id="btn-reviewSave">리뷰등록</button>
		
	</div>
		
		
		

	</form>	
	</c:if>
<table class="table table-stripped" id="reviews">
    <thead>
        <tr>
            <th style="width:10%">Rating</th> <!-- 평점 -->
            <th style="width:10%">User</th>
            <th style="width:60%">content</th>
            <th style="width:20%">Date</th>
        </tr>
    </thead>
    <tbody>
        
            <!-- 평점 기준 별표시 출력 -->
            
            <c:forEach var="review" items="${product.review}">
            	<tr>
            		<td class="rate-image"><c:forEach var="rate" items="${rate}" varStatus="status" begin="1" end="${ review.rate }"><span class="rate">★</span></c:forEach></td>
            		<td>${review.users.username}</td>
            		<td>${review.content}</td>
            		<td><fmt:formatDate value="${review.createDate}" pattern="YYYY-MM-dd"/></td>
            	
            	
            
            	</tr>
            </c:forEach>
            
<!--             <tr>
                <td class="rate">★★★★★</td>
                <td>익명</td>
                <td>내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용</td>
                <td>2022-12-23</td>
            </tr> -->
    
        
    </tbody>
</table>


	
	

</div>
<br/>
<script type="text/javascript">
	function inputValueChange(){
		let inputValue = document.getElementById('amount').value;
		let url = '/order/product/${principal.user.id}/${product.id}/' + inputValue;
		console.log(url);
		window.location.href = url;
	}
</script>

<script type="text/javascript" src="/js/product.js"></script>
<!-- <script type="text/javascript" src="/js/review.js"></script> -->

<%@ include file="../layout/footer.jsp"%>