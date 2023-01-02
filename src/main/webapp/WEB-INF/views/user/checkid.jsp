<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<div>
    <h2>아이디중복확인</h2>
    <form action="/auth/checkId.do" method="GET" name="frm">
        <label for="username"> 아이디를 입력해주세요</label>
        <input type="text" id="username" value="${username}" name="username">
        <input type="submit" value="중복체크"><br>
        <c:if test="${result == 1 }">
            <script>
                opener.document.frm.username.value="";
            </script>
            ${username}은 이미 사용중인 아이디입니다.
        </c:if>
        <c:if test="${result == -1 }">

            ${username}은 사용 가능한 아이디입니다.
            <input type="button" value="사용" onclick="index2.idok('${username}')">
        </c:if>
    </form>
</div>
<script src="/js/user.js"></script>