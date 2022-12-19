<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


	<div class="container" style="max-width:720px">
	  <form> 
	    <div class="form-group">
	      <label for="username">Username</label>
	      <input type="text" class="form-control" id="username"  placeholder="회원 아이디를 입력해주세요">
	      <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
	    </div>
	    <p class="valid-text" id="valid_username"></p>
	    <div class="form-group">
	      <label for="email">Email</label>
	      <input type="email" class="form-control" id="email" placeholder="가입시 입력한 이메일을 입력해주세요.">
	    </div>
	    <p class="valid-text" id="valid_email" align="left"></p>
	  </form>
	  <button class="btn btn-primary btn-block" id="btn-find">임시 패스워드 전송</button>
	</div>
	<hr>
	<!-- <div class="text-center small mt-2" id="checkMsg" style="color: red"></div> -->
	<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>