<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


	<div class="container" style="max-width:720px">
	  <form> 
	    <div class="form-group">
	      <label for="userEmail">Email</label>
	      <input type="email" class="form-control" id="userEmail" aria-describedby="emailHelp" placeholder="가입시 입력한 이메일을 입력해주세요.">
	      <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
	    </div>
	    <div class="form-group">
	      <label for="userName">username</label>
	      <input type="text" class="form-control" id="userName" placeholder="회원 아이디를 입력해주세요">
	    </div>
	    <button type="submit" class="btn btn-primary btn-block" id="checkEmail">Submit</button>
	  </form>
	</div>
	<hr>
	<div class="text-center small mt-2" id="checkMsg" style="color: red"></div>
<%@ include file="../layout/footer.jsp"%>