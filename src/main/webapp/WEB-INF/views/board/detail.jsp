<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.users.id==principal.user.id }">		
		<button id="btn-delete" class="btn btn-danger">삭제</button>
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
	</c:if>
	<div>
		<br/><br/>
		<div>
			글 번호 : <span id="id"><i>${board.id}</i> </span>
			작성자 : <span><i>${board.users.username }</i></span>
		</div>
			<h3>${board.title}</h3>
	</div>
	<hr/>
	<div>
		<div>${board.content}</div>
	</div>
	<hr/>
</div>
<br/>
<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>

	