<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
	.buttons {
		display:flex;
		justify-content:space-between;
	}


</style>
<div class="container" style="max-width:900px;">
	<form>
		<input type="hidden" id="id" value="${board.id}"/>
		<div class="form-group">
			<input value="${board.title}" type="text" class="form-control" style="text-align:left;"
			 placeholder="Enter title" id="title">
<!-- 			<label for="title">Title</label>
			<input type="text" class="form-control" placeholder="Enter title" id="title" style="text-align:left;"> -->
		</div>
		
		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
	</form>
	
	<div class="buttons">
		<button class="btn btn-danger"><a href="javascript:history.back();">뒤로가기</a></button>
		<button id="btn-update" class="btn btn-primary">수정</button>
	</div>
</div>
<br/>
<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 400
      });
</script>
<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

