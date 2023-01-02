<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
	a {
		text-decoration: none;
	}
	.btn-hover {
		width: 100%;
	}


</style>
 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
<div style="background-color:white;border-radius:4px;padding:20px;min-height:500px; width:960px; margin: auto;">
	<br>
	<h3><span style="font-size:20px">${principal.user.username } 님의</span> My Page</h3>
	<div style="width:100%;margin-top:20px;margin-bottom:20px;">
		<table style="width:100%;height:50px;text-align:center;font-weight:bold;border:1px solid #e0e0e0;">
			<tbody>
			<tr>
				<td id="mypage_menu_1" style="width: 33.33%; border-right: 1px solid rgb(224, 224, 224); ">
					<a href="/mypage/${principal.user.id}">
						<span style="color:black;">주문내역/배송조회</span>
					</a>
				</td>
				<td id="mypage_menu_2" style="width: 33.33%; border-right: 1px solid rgb(224, 224, 224); background-color: #e0e0e0;">
					<a href="/mypage/userInfo">
						<span style="color:black;">회원정보수정</span>
					</a>
				</td>
				<td id="mypage_menu_3" style="width:33.33%;border-right:1px solid #e0e0e0;">
					<a href="/mypage/userWithdrawal">
						<span style="color:black;">회원탈퇴</span>
					</a>

				</td>


			</tr>
			<tr>
				<td colspan="3" style="height:2px;background-color:#707070;"></td>
			</tr>
		</tbody>
		</table>	
	</div>
	
	<div class="container" style="border:1px solid #eee; ">
	
		<input type="hidden" id="id" value="${principal.user.id}"/>
	
		<div class="form-group">
			<label for="email">Username</label> <input type="email" value="${principal.user.username}"
				class="form-control" placeholder="Enter Username" id="username" readonly>
		</div>
		<div class="form-group">
			<label for="email">Email</label> <input type="email"  value="${principal.user.email}"
				class="form-control" placeholder="Enter email" id="email">
		</div>
		<div class="form-group">
			<label for="address">주소</label> <input type="text" value="${principal.user.address}"
				class="form-control" placeholder="주소변경" id="address" readonly>
		</div>
		<div class="form-group">
			<label for="addressDetail">상세주소</label> <input type="text" 
				class="form-control" placeholder="상세주소입력"id="addressDetail" name="address_detail">
		</div>
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" placeholder="Enter password" id="password" value="${principal.user.password}">
				<p id="valid_password"></p>
			</div>
		</c:if>

		<hr>
		<button id="btn-update"class="btn-hover color-1" style="background-image: linear-gradient(to right, #29323c, #485563, #2b5876, #4e4376);
  box-shadow: 0 4px 15px 0 rgba(45, 54, 65, 0.75);">회원수정완료</button>
	
	
	</div>
	
</div>

<script src="/js/user.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/js/kakaoAddress.js"></script>

    

<%@ include file="../layout/footer.jsp"%>

