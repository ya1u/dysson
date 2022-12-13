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
		min-height:600px;
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
}
	
</style>
<div class="container">
	<br>
	<br>
	<div class="noticeList"> 
	    <b style="font-size: 24px;" class="item_notice"><a href="#">공지사항</a></b>
	    <b style="font-size: 24px;" class="item_notice"><a href="#">1:1 문의</a></b>
	    <b style="font-size: 24px;" class="item_notice"><a href="#">상품후기</a></b>
    </div>
    <br>
    <br>
    <div class="buttons">
      <a href="#"><button class="btn btn-success">메인으로 돌아가기</button></a>
      <a href="/board/saveForm"><button class="btn btn-secondary float-right">글 작성</button></a>
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
        <tr>
          <td>1</td>
          <td><a href=#>test</a></td>
          <td>관리자</td>
          <td>2022-12-09</td>
        </tr>
        <tr>
          <td>2</td>
          <td>testesttesttesttesttesttestt</td>
          <td>관리자</td>
          <td>2022-12-09</td>
        </tr>
        <tr>
          <td>3</td>
          <td>test</td>
          <td>관리자</td>
          <td>2022-12-09</td>
        </tr>
        <tr>
          <td>4</td>
          <td>test</td>
          <td>관리자</td>
          <td>2022-12-09</td>
        </tr>
      </tbody>
    </table>
    

      <!-- <ul class="pagination text-center" style="justify-content: center;">
        <li class="page-item "><a class="page-link" href="#">Previous</a></li>
        <li class="page-item active"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
      </ul> -->
      <br><br>
      <div>
        <ul class="pagination justify-content-center">
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">ᐸ</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">1</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">2</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">3</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">4</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">5</a></li>
          <li><a href="#" style="margin-right: 5px;" class="text-secondary">ᐳ</a></li>
        </ul>

      </div>
  </div>
 <%@ include file="../layout/footer.jsp"%>