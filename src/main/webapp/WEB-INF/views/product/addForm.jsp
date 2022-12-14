<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<link href="/css/addForm.css" rel="stylesheet">
<div class="container">
<hr>
	<form action="/product/saveProduct" method="post" enctype="multipart/form-data">
		<div class="form-group">
		<table>
		  <tr>
			<th><label for="category">카테고리</label></th>
			<td><select name="category" id="category">
				<option value="">--Category select--</option>
				<option value="KICHEN">KICHEN</option>
				<option value="AIR">AIR</option>
				<option value="CLEANER">CLEANER</option>				
			</select>
			</td>
		  </tr>
		  <tr>
			<th><label for="name">제품 이름</label></th>
			<td><input type="text" class="form-control" placeholder="Product Name" id="name" name="name"></td>
		  </tr>
		  <tr>
			<th><label for="price">가격</label></th>
			<td><input type="number" class="form-control" placeholder="Product Price" id="price" name="price"></td>
		  </tr>
		  <tr>
			<th><label for="made">제조사</label></th>
			<td><input type="text" class="form-control" placeholder="Made In" id="made" name="made"></td>
		  </tr>
		  <tr>
			<th><label for="content">제품 소개</label></th>
			<td><input type="text" class="form-control" placeholder="Product content" id="content" name="content"></td>
		  </tr>
		  <tr>
			<th><label for="imgProduct">제품 이미지</label></th>
			<td><input type="file" class="form-control" id="imgProduct" name="imgProduct" name="imgProduct"></td>
		  </tr>
		</table>
		</div>
			<div class="select_imgProduct"><img src=""/></div>
		<button type="submit" id="btn-add" class="btn btn-primary">등록</button>
	</form>
			<script>
			  $("#imgProduct").change(function(){
			   if(this.files && this.files[0]) {
			    var reader = new FileReader;
			    reader.onload = function(data) {
			     $(".select_imgProduct img").attr("src", data.target.result).width(500);        
			    }
			    reader.readAsDataURL(this.files[0]);
			   }
			  });
			 </script>
			 
			 <%=request.getRealPath("/") %><br><br>
	<hr>
</div>
<!-- <script type="text/javascript" src="/js/product.js"></script> -->
<%@ include file="../layout/footer.jsp" %>