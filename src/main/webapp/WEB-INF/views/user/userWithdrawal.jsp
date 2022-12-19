<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
	a {
		text-decoration: none;
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
					<a href="/auth/mypage">
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
<!-- 		<div class="form-group">
			<label for="pwd">Password</label> <input type="password" "
				class="form-control" placeholder="Enter password" id="password">
		</div> -->

</div>
<!-- <script src="/js/mypage.js"></script> -->
<%@ include file="../layout/footer.jsp"%>

