<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h2>아이디중복확인</h2>
    <label for="username"> 아이디를 입력해주세요</label>
    <input type="text" id="username" value="${username}">
    <input type="submit" value="중복체크"><br>
    <c:if test="${result ==1 }">
        <script>
            opener.document.frm.username.value="";
        </script>
        ${username}은 이미 사용중인 아이디입니다.
    </c:if>
    <c:if test="${result ==0 }">

        ${username}은 이미 사용중인 아이디입니다.
        <input type="button" value="사용" onclick="idok(${username})">
    </c:if>
</div>
<script src="/js/user.js"></script>