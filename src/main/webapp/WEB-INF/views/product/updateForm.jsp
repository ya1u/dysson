<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<link href="/css/addForm.css" rel="stylesheet">
<div class="container">
<h4>제품 수정 페이지</h4>
<hr>
	<form action="/updateProduct" method="post" enctype="multipart/form-data">
		<input id="id" name="id" type="hidden" value="${product.id}">
		<input id="imgName" name="imgName" type="hidden" value="${product.imgName}">
		<input id="imgOriName" name="imgOriName" type="hidden" value="${product.imgOriName}">
		<input id="imgUrl" name="imgUrl" type="hidden" value="${product.imgUrl}">
		<div class="form-group">
		<table>
		  <tr>
			<th><label for="category">카테고리</label></th>
			<td><select name="category" id="category">
				<option value="${product.category}">--Category select--</option>
				<option value="KICHEN">KICHEN</option>
				<option value="AIR">AIR</option>
				<option value="CLEANER">CLEANER</option>				
			</select>
			</td>
		  </tr>
		  <tr>
			<th><label for="name">제품 이름</label></th>
			<td><input type="text" class="form-control" placeholder="Product Name" id="name" name="name" value="${product.name}"></td>
		  </tr>
		  <tr>
			<th><label for="price">가격</label></th>
			<td><input type="number" class="form-control" placeholder="Product Price" id="price" name="price" value="${product.price}"></td>
		  </tr>
		  <tr>
			<th><label for="made">제조사</label></th>
			<td><input type="text" class="form-control" placeholder="Made In" id="made" name="made" value="${product.made}"></td>
		  </tr>
		  <tr>
			<th><label for="content">제품 소개</label></th>
			<td><pre><textarea class="form-control summernote" rows="5" id="content" name="content">${product.content}</textarea></pre></td>
		  </tr>
		  <tr>
			<th><label for="imgProduct">제품 이미지</label></th>
			<td><input type="file" class="form-control" id="imgProduct" name="imgProduct" name="imgProduct"></td>
		  </tr>
		</table>
			<h5 style="color: #AE0000;">※사진 파일 미등록 시 기존의 사진을 그대로 사용</h5>
		</div>
			<div class="select_imgProduct"><img src=""/></div>
		<a href="/product/${product.id}" class="btn btn-warning" style="background-color: #6c757d; border-color: #6c757d; color: #FFF;">돌아가기</a>
		<button type="submit" id="btn-add" class="btn btn-primary">등록</button>
	</form>
			<script>
			  $("#imgProduct").change(function(){
			   if(this.files && this.files[0]) {
				   const reader = new FileReader;
				   reader.onload = function(data) {
			     $(".select_imgProduct img").attr("src", data.target.result).width(500);        
			    }
			    reader.readAsDataURL(this.files[0]);
			   }
			  });
			  
		      $('.summernote').summernote({
		          tabsize: 2,
		          height: 400
		        });
			 </script>
			 
<%-- 			 <%=request.getRealPath("/") %><br><br>--%>
	<hr>
</div>
<!-- <script type="text/javascript" src="/js/product.js"></script> -->
<%@ include file="../layout/footer.jsp" %>