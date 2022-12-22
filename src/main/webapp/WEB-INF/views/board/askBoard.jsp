<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
	a {
	text-decoration: none;
	color:black;
	}
	.buttons {
		display:flex;
		justify-content: space-between;
	}
	.container {
		max-width:960px;
		
	}
	.noticeList {
		display:block;
		
	}
	.item_notice {
		border-right:1px solid #ccc;
		padding-right:50px;
		padding-left:50px;	
	}
	.support_banner {
		object-fit:cover;
		width:60%;
				
	}
/* 	.support {
		position:relative;
		
	}
	.dysson-support {
		position:absolute;
		top:50px;
		left:700px
	} */
@media (max-width: 960px) {
		.support_banner {
	   	 width:100%;
	}
}

	
</style>

<div class="support">
	<img class="support_banner" src="/image/support-banner2.png">

</div>
<div class="container">
	<br>
	<br>
	<div class="noticeList"> 
	    <b style="font-size: 24px;" class="item_notice"><a href="/auth/support">공지사항</a></b>
	    <b style="font-size: 24px;" class="item_notice"><a href="/auth/support/askBoard">1:1 문의</a></b>
	    <b style="font-size: 24px;" class="item_notice"><a href="#">상품후기</a></b>
    </div>
    <br>
    <br>
    <div class="buttons">
      <a href="#"><button class="btn btn-success">메인으로 돌아가기</button></a>
      <c:if test="${principal.user.id != null}">
      <a href="/board/askSaveForm"><button class="btn btn-secondary float-right">글 작성</button></a>
      </c:if>
    </div>
    <hr>
    <table class="table  table-sm table-hover">
      <thead class="thead-dark">
        <tr class="text-center">
          <th style="width: 10%;">번호</th>
          <th style="width: 60%;">제목</th>
          <th style="width: 15%;">작성자</th>
          <th style="width: 15%;">작성일</th>
          <!-- <th style="width: 15%;">조회수</th> -->
        </tr>
      </thead>
      <tbody class="text-center">
<%-- 		<c:forEach var="board" items="${boards.content}">
			<c:if test="${board.category eq 'ask'}">
	      		<tr>
	      			<td>${board.id}</td>
	      			<td><a href="/askBoard/${board.id}">${board.title}</a></td>
	   				<td>${board.users.username}</td>
	      			<td><fmt:formatDate value="${board.createDate}" pattern="YYYY-MM-dd"/></td> 
	      		</tr>
      		</c:if>
      	</c:forEach>  --%>
      	
      	<c:forEach var="board" items="${boards.content}">
      		<c:if test="${board.category eq 'ask'}">
      		
      			<tr>
      				<td>${board.id}</td>
      				<c:choose>
      					
      					<c:when test="${principal.user.id == board.users.id || principal.user.roles eq 'admin'}">
      						<td><a href="/askBoard/${board.id}">${board.title}</a>
      							<img src="/image/lock2.png" style="width:15px">	
      						<!-- 댓글0일때 표시 안되게함 -->
      							<c:if test="${board.replyCnt > 0 }">
      								<c:out value="[${board.replyCnt}]"/>
      							</c:if>      						
      						</td> 
    						<td>${board.users.username}</td>
	      					<td><fmt:formatDate value="${board.createDate}" pattern="YYYY-MM-dd"/></td> 
      					</c:when>
      					<c:otherwise>
      						<td>1:1 문의글 입니다...
      							<img src="/image/lock2.png" style="width:15px">	
      							<!-- 댓글0일때 표시 안되게함 -->
      							<c:if test="${board.replyCnt > 0 }">
      								<c:out value="[${board.replyCnt}]"/>
      							</c:if>
      						</td>
    						<td>${board.users.username}</td>
	      					<td><fmt:formatDate value="${board.createDate}" pattern="YYYY-MM-dd"/></td> 
      					</c:otherwise>
      				</c:choose>
      			</tr>
      		</c:if>
      	</c:forEach>
      	
      	
      </tbody>
   	 </table>
      <br><br>
      <div>
      
<!--       <form action="/board/search" method="GET">
      	<div class="search">
      		<input name="keyword" type="text" placeholder="검색어를 입력해주세요">
      	</div>
      	<button>검색하기</button>
      
      </form> -->
      
      
      
      
        <ul class="pagination justify-content-center">
        	<c:choose>
        		<c:when test="${boards.first}">
        			<li class="page-item disabled"><a class="page-link"
        				href="?page=${boards.number-1}">ᐸ</a></li>
        		</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="?page=${boards.number-1}">ᐸ</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${boards.last}">
					<li class="page-item disabled"><a class="page-link"
						href="?page=${boards.number+1}">ᐳ</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="?page=${boards.number+1}">ᐳ</a></li>
				</c:otherwise>
			</c:choose>		
        			
        	
        
        
        
        
        
        
<!--           <li><a href="#" style="margin-right: 5px;" class="text-secondary">ᐸ</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">1</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">2</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">3</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">4</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">5</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">ᐳ</a></li> -->
        </ul>

      </div>
  </div>
 <%@ include file="../layout/footer.jsp"%>