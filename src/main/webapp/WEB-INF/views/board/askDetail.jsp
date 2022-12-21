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
				글 번호 : <span id="id"><i>${board.id}</i> </span>
				&nbsp;작성자 : <span><i>${board.users.username }</i></span>
				&nbsp; <span style="color:grey;"><i><fmt:formatDate value="${board.createDate}" pattern="YYYY-MM-dd hh:mm"/></i></span>
			</div>
			<div>
				
				<button class="btn btn-secondary" onclick="history.back()">목록</button>
				<!-- 본인과 관리자만 삭제 및 수정 -->
				<c:if test="${board.users.id==principal.user.id || principal.user.roles eq 'admin'}">		
					<button id="btn-delete" class="btn btn-danger">삭제</button>
					<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
				</c:if>
			</div>
		</div>
		<c:if test="${principal.user.id != null}">
			<div class="card d-flex">
			<div class="card-header">댓글 등록</div>
				<form class="d-flex">
					<input type="hidden" id="usersId" value="${principal.user.id }"/>
					<input type="hidden" id="boardsId" value="${board.id }"/>
					<div class="card-body"><textarea class="form-control" rows="1" id="reply-content"></textarea></div>
					<div class="card-footer"><button class="btn btn-primary" id="btn-replySave" style="width:100%; height:100%;">등록</button></div>
				</form>
			</div>
		</c:if>
		<br>
		<br>
		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply-box" class="list-group">
				<c:forEach var="reply" items="${board.replys}">
					<li id="reply-${reply.id}" class="list-gruop-item d-flex justify-content-between">
						<div>&nbsp;${reply.content}</div>
						<div class="d-flex">
							<div><span style="color:grey;"><fmt:formatDate value="${reply.createDate}" pattern="YY.MM.dd hh:mm"/></span>&nbsp;&nbsp;&nbsp;</div>
							<div>&nbsp;${reply.users.username }&nbsp;</div>
							<c:if test="${principal.user.id==reply.users.id}">	
							<button onClick="index.replyDelete(${board.id},${reply.id})" class="btn btn-danger btn-sm">삭제</button>
							</c:if>
						</div>				
					</li>
					<hr>			
				</c:forEach>			
			</ul>		
		</div>
	</div>
	<br>
	<br>
	<br>

<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>

	