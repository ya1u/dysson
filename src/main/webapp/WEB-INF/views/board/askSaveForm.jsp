<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
	.buttons {
		display:flex;
		justify-content:space-between;
	}


</style>
<div class="container" style="max-width:900px;">
<h4>1:1 문의글 작성</h4>
<hr>
	<form>
		<input type="hidden" id="category" value="ask">
		<input type="hidden" id="secret" value="true">
		<div class="form-group">
			<label for="title">Title</label>
			<input type="text" class="form-control" placeholder="Enter title" id="title" style="text-align:left;">
		</div>
		
		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	
	<div class="buttons">
		<button id="" class="btn btn-danger">뒤로가기</button>
		<button id="btn-save" class="btn btn-primary">저장</button>
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

