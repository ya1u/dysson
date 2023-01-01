<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/joinform.css">


	    <div class="member">
	      <!--<a href="#"><img class="logo" src="/image/logo.png"></a>  -->
	
	      <div class="field">
	          <label for="username"><b><span style="color:red;">*</span>아이디</b></label>
	          <div class="field-id">
				  	<form name="frm">
					  <input type="text" placeholder="UserID" class="item" id="username" name="username" style="width:250px;text-align: left;">
						<input type="hidden" id="rename">
						<button class="item" id="idCheck" type="button" >중복확인</button>
					</form>


	          </div>
	          <p id="valid_username"></p>
	      </div>
	      <div class="field">
	          <label for="password"><b><span style="color:red;">*</span>비밀번호</b></label>
	          <input class="userpw" type="password" id="password" placeholder="Password" style="text-align: left;">
	      </div>
	      <p id="valid_password"></p>
	      
	      <div class="field">
	          <b><span style="color:red;">*</span>비밀번호 재확인</b> 
	          <input class="userpw-confirm" type="password" placeholder="Password_check" id="pwcheck" style="text-align: left;"> 
	      </div>
	      <div class="field">
	          <b><span style="color:red;">*</span>이름</b>
	          <input type="text" placeholder="Name" id="name" style="text-align: left;">
	      </div>
			<p id="valid_name"></p>
	
	      <div class="field">
	         <label for="email"><b><span style="color:red;">*</span>Email</b></label>
	          <input type="email" placeholder="Email" id="email" style="text-align: left;">
	      </div>
	      <p id="valid_email"></p>
	      <div class="field">
	        <b>전화번호</b>
	        <input type="text" placeholder="Telephone" id="phone" style="text-align: left;">
	      </div>
	      <div class="field">
	        <b>주소</b>
	    
	     
	          <input type="text" id="address" placeholder="주소찾기" class="item" readonly style="text-align: left;">

	          <input type="text" placeholder="상세주소" name="address_detail" id="addressDetail" style="text-align: left;">
	        
	      </div>
	    <input type="button" value="가입하기" id="btn-save" style="text-align: left;">
	</div>

<script src="/js/user.js"></script>
<!-- <script src="/js/joinCheck.js"></script> -->

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/js/kakaoAddress.js"></script>
<%@ include file="../layout/footer.jsp"%>


