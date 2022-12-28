<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
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
	.search {
	  width: 20%;
	  right:100px;
	 
	  display:flex;
	 
	  
	
	  
	}
	
	.searchTerm {
	  width: 100%;
	  border: 3px solid #333;
	  border-right: none;
	  padding: 5px;
	  
	  border-radius: 5px 0 0 5px;
	  outline: none;
	  color: #9DBFAF;
	  
	  
	  
	}
	
	.searchTerm:focus{
	  color: #333;
	}
	
	.searchButton {
	  width: 40px;
	  
	  border: 1px solid #333;
	  background: #333;
	  text-align: center;
	  color: #fff;
	  border-radius: 0 5px 5px 0;
	  cursor: pointer;
	  font-size: 20px;
	  margin-left:auto;
	}
	        
	
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
	    <b style="font-size: 24px;" class="item_notice"><a href="/auth/support"  class="hvr-shadow">공지사항</a></b>
	    <b style="font-size: 24px;" class="item_notice"><a href="/auth/support/askBoard"  class="hvr-shadow">1:1 문의</a></b>
	    <!-- <b style="font-size: 24px;" class="item_notice"><a href="#">상품후기</a></b> -->
    </div>
    <br>
    <br>
    <div class="buttons">
      <a href="#"><button class="btn btn-success">메인으로 돌아가기</button></a>
      <c:if test="${principal.user.roles eq 'admin'}">
      <a href="/board/saveForm"><button class="btn btn-secondary float-right">글 작성</button></a>
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
		<c:forEach var="board" items="${boards.content}">
			<c:if test="${board.category eq 'notice'}">
	      		<tr>
	      			<td>${board.id}</td>
	      			<td><a href="/board/${board.id}">${board.title}</a>
	      			<!-- 댓글0일때 표시 안되게함 -->
							<c:if test="${board.replyCnt > 0 }">
								<c:out value="[${board.replyCnt}]"/>
							</c:if>
	      			</td>
	   				<td>${board.users.username}</td>
	      			<td><fmt:formatDate value="${board.createDate}" pattern="YYYY-MM-dd"/></td> 
	      		</tr>
	      	</c:if>	
      	</c:forEach> 
      	
      </tbody>
    </table>
      
    <form action="/auth/support" method="GET">
	    <div class="search">
	       <input type="text" class="searchTerm" placeholder="Search" name="searchKeyword">
	       <button type="submit" class="searchButton">
	         <i class="fa fa-search"></i>
	      </button>
	    </div>
		<br>
	</form>
      <div>

    <!-- 페이징 -->
    <c:set var="startPage" value="${boards.number - boards.number % 5}" />
    <ul class="pagination justify-content-center">
        <li class="page-item <c:if test='${boards.number < 5}'>disabled</c:if>">
            <a class="page-link" href="/admin?category=${param.category}&page=${startPage - 5}&searchKeyword=${param.searchKeyword}"><</a>
        </li>
        <c:forEach var="page" begin="1" end="5">
            <c:if test="${(startPage + page) <= boards.totalPages}">
                <li class="page-item <c:if test='${boards.number eq startPage + page - 1}'>active</c:if>">
                    <a class="page-link" href="/admin?category=${param.category}&page=${startPage + page - 1}&searchKeyword=${param.searchKeyword}">${startPage + page}</a>
                </li>
            </c:if>
        </c:forEach>
        <li class="page-item <c:if test='${startPage + 5 > boards.totalPages}'>disabled</c:if>">
            <a class="page-link" href="/admin?category=${param.category}&page=${startPage + 5}&searchKeyword=${param.searchKeyword}">></a>
        </li>
    </ul>
    <!-- 페이징 끝 -->


      </div>
  </div>
 <%@ include file="../layout/footer.jsp"%>