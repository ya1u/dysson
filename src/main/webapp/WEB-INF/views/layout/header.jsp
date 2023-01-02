<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Dysson</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"> -->
  <link rel="stylesheet" href="/css/index.css">
  <link rel="stylesheet" href="/css/hover.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  <script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<%--  <link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>--%>
  <script src="https://kit.fontawesome.com/ad85960d2b.js" crossorigin="anonymous"></script>
  
  

</head>
<body>
  <header id="header">
    <a href="/" class="logo"><img src="/image/dysson_logo.png"></a>
    <!-- <nav class="nav">
      <ul class="gnb">
        <li><a href="#">Home</a><span class="sub_menu_toggle_btn"></span></li>
        <li><a href="#">About</a><span class="sub_menu_toggle_btn"></span></li>
        <li><a href="#">Support</a><span class="sub_menu_toggle_btn"></span></li>
        <li><a href="#">Store</a><span class="sub_menu_toggle_btn"></span></li>
      </ul>
    </nav> -->
    </header>
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-center">

    <!-- Links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/auth/about">About</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/auth/support">Support</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/product/store">Store</a>
      </li>
      
      <!-- Dropdown -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
          Account
        </a>
        <c:choose>
        	<c:when test="${empty principal}">
       	        <div class="dropdown-menu">
		          <a class="dropdown-item" href="/auth/loginForm">Login</a>		          
        		</div>
        	</c:when>
        	<c:otherwise>
       	        <div class="dropdown-menu">
                    <a class="dropdown-item" href="/logout">Logout</a>

		            <a class="dropdown-item" href="/mypage/${principal.user.id}">My Page</a>

                    <c:if test="${principal.user.roles eq 'admin'}">
                      <a class="dropdown-item" href="/admin">Admin Page</a>
                    </c:if>
                    <c:choose>
                      <c:when test="${empty user.cart.count}">
                          <a class="dropdown-item d-flex" href="/cart/${principal.user.id}">Cart&nbsp;&nbsp;&nbsp;<div class="blue"><span>0</span></div></a>
                      </c:when>
                      <c:otherwise>
                          <a class="dropdown-item d-flex" href="/cart/${principal.user.id}">Cart&nbsp;&nbsp;&nbsp;<div class="blue"><span>${user.cart.count}</span></div></a>
                      </c:otherwise>
                    </c:choose>
		        </div>
        	</c:otherwise>
        </c:choose>
      </li>
    </ul>
  </nav>
  <br>