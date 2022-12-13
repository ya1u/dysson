<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<link href="/css/addForm.css" rel="stylesheet">
<div class="container">
<hr>
	<form>
		<table>
		<div class="form-group">
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
			<td><input type="text" class="form-control" placeholder="Product Name" id="name"></td>
		  </tr>
		  <tr>
			<th><label for="price">가격</label></th>
			<td><input type="number" class="form-control" placeholder="Product Price" id="price"></td>
		  </tr>
		  <tr>
			<th><label for="made">제조사</label></th>
			<td><input type="text" class="form-control" placeholder="Made In" id="made"></td>
		  </tr>
		  <tr>
			<th><label for="content">제품 소개</label></th>
			<td><input type="text" class="form-control" placeholder="Product content" id="content"></td>
		  </tr>
		  <tr>
			<th><label for="img">제품 이미지</label></th>
			<td><input type="file" class="form-control" id="img"></td>
		  </tr>
		</div>
		</table>
			<div class="select_img"><img src=""/></div>
	</form>
			<script>
			  $("#img").change(function(){
			   if(this.files && this.files[0]) {
			    var reader = new FileReader;
			    reader.onload = function(data) {
			     $(".select_img img").attr("src", data.target.result).width(500);        
			    }
			    reader.readAsDataURL(this.files[0]);
			   }
			  });
			 </script>
			 
			 <%=request.getRealPath("/") %><br><br>
	<button id="btn-add" class="btn btn-primary">등록</button>
	<hr>
</div>
<script type="text/javascript" src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp" %>