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
      <input type="submit" value="로그인" id="btn-login">
      <a href="#"><img src="/image/loginkakao.png" style="width: 100%;"> </a>
      <div class="textalign">
        <p>회원이 아니신가요?</p>
        <p><a href="/auth/joinForm">회원가입</a></p>

      </div>
    </div>    
  </form>
<%@ include file="../layout/footer.jsp"%>