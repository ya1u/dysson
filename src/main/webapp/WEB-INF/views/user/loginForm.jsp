<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/loginform.css">
  <form action="/auth/loginProc" method="POST">
    <div class="member">
      <!--<a href="#"><img class="logo" src="/image/logo.png"></a>  -->

      <div class="field">                        
        <input type="text" placeholder="UserID" class="item" id="username" name="username" style="text-align: left;">           
      </div>
      <div class="field">         
        <input class="userpw" type="password" placeholder="Password" id="password" name="password" style="text-align: left;">
      </div>
      <div class="remember" style="display:flex; justify-content: space-between;">         
        <div>&nbsp;<input type="checkbox" name="remember">Remember me</div>
        	<div><a href="/auth/findPw"> 비밀번호 찾기</a></div>
      </div>
      <input type="submit" value="로그인" id="btn-login">
      <a href="https://kauth.kakao.com/oauth/authorize?client_id=2fadb3c76663155318a907aa33153a61&redirect_uri=http://localhost:8050/auth/kakao/callback&response_type=code"><img src="/image/loginkakao.png" style="width: 100%;"> </a>
      <div class="textalign">
        <p>회원이 아니신가요?</p>
        <p><a href="/auth/joinForm">회원가입</a></p>

      </div>
    </div>    
  </form>
<%@ include file="../layout/footer.jsp"%>