<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
	.btnFlex {
		display:flex;
		
		justify-content:space-between;
	}
	
	.btn {
		margin-right:5px;
		margin-bottom:15px;
	}

</style>
	<div class="container detail-form" style="width:900px;">
		<div>
			<br/><br/>
<%-- 			<div class="writeForm">
				글 번호 : <span id="id"><i>${board.id}</i> </span>
				작성자 : <span><i>${board.users.username }</i></span>
				<hr>
			</div> --%>
			<hr>
				<h3>${board.title}</h3>
		</div>
		<hr/>
		<div>
			<div>${board.content}</div>
		</div>
		<hr/>
	
	
		<div class="btnFlex">
			<div>
				글 번호 : <span id="boardsId"><i>${board.id}</i> </span>
				작성자 : <span><i>${board.users.username }</i></span>
			</div>
			<div>
				<button class="btn btn-secondary" onclick="history.back()">목록</button>
				<c:if test="${board.users.id==principal.user.id }">		
					<button id="btn-delete" class="btn btn-danger">삭제</button>
					<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
			</c:if>
			</div>
		</div>
		<div class="card">
			<div class="card-body"><textarea class="form-control" rows="2" id="reply-content"></textarea></div>
			<div class="card-footer"><button class="btn btn-primary" style="justify-content:row-reverse" id="btn-replySave">등록</button></div>
		</div>
		<br>
		<br>
		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply-box" class="list-group">
				<c:forEach var="reply" items="${board.replys}">
					<li id="reply-1" class="list-gruop-item d-flex justify-content-between">
						<div>${reply.content}</div>
						<div class="d-flex">
							<div id="usersId">작성자: ${reply.user.username }&nbsp;</div>
							<button class="badge">삭제</button>
						</div>				
					</li>			
				</c:forEach>			
			</ul>		
		</div>
	</div>
	<br>
	<br>
	<br>

<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>

	