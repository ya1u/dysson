<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
	a {
		text-decoration: none;
	}
	.box-pwd-chk{
		border: 1px solid #d3d3d3;
		height: 420px;

	}
	.header-title {
		padding: 20px 0 18px;
		border-bottom: 1px solid #e4e4e4;
	}
	.box-pwd-input {
		padding: 50px 64px 40px;
	}
	.pwdbtn {
		position: relative;
		display: block;
		width: 100%;
		height: 65px;
		color: #fff;
		text-align: center;
		line-height: 65px;
		background-color: #333;
		font-size: 22px;
		margin-top: 16px;
		border: none;
	}
	.input-pwd {
		position: relative;
		width: 100%;
		height: 67px;
		padding: 23px;
		background: #fff;
		border: 1px solid #d9d9d9;
		color: #222;
		font-size: 21px;
		font-weight: 400;
		outline: 0;
		box-sizing: border-box;
	}


</style>
<div style="background-color:white;border-radius:4px;padding:20px;min-height:500px; width:960px; margin: auto;">
	<br>
	<h3><span style="font-size:20px">${principal.user.username } 님의</span> My Page</h3>
	<div style="width:100%;margin-top:20px;margin-bottom:20px;">
		<table style="width:100%;height:50px;text-align:center;font-weight:bold;border:1px solid #e0e0e0;">
			<tbody>
			<tr>
				<td id="mypage_menu_1" style="width: 33.33%; border-right: 1px solid rgb(224, 224, 224);">
					<a href="/mypage/${principal.user.id}">
						<span style="color:black;">주문내역/배송조회</span>
					</a>
				</td>
				<td id="mypage_menu_2" style="width: 33.33%; border-right: 1px solid rgb(224, 224, 224);">
					<a href="/mypage/userInfo">
						<span style="color:black;">회원정보수정</span>
					</a>
				</td>
				<td id="mypage_menu_3" style="width:33.33%;border-right:1px solid #e0e0e0; background-color: #e0e0e0;">
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

		<div class="box-pwd-chk">
			<h2 class="header-title">비밀번호 확인</h2>
			<div class="box-pwd-input">
				<p>계정탈퇴를 하기 위해 비밀번호를 다시 한 번 입력해 주세요.</p>
				<div style="margin-bottom: 16px">
					<span>아이디</span>
					<strong>${principal.user.username }</strong>
				</div>
				<%--<label for="input_password" >비밀번호</label>--%>
				<input type="hidden" id="userId" value="${principal.user.id }"/>
				<input type="hidden" id="userPwd" value="${principal.user.password }"/>
				<input type="password" name="pwd" placeholder="Password" class="input-pwd" id="inputPwd">
				<button class="pwdbtn" type="button"  style="display: block;" onClick="index2.deleteById2(${principal.user.id})">확인</button>
<%--				<button class="pwdbtn" type="button"  style="display: block;" onclick="location.href='/mypage/userWithdrawal/'"> 확인</button>--%>
			</div>

	</div>


</div>
<!-- <script src="/js/mypage.js"></script> -->
<%@ include file="../layout/footer.jsp"%>
<%--<script type="text/javascript" src="/js/user.js"></script>--%>

