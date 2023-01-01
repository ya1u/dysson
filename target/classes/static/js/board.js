let index = {
	init: function() {
		$("#btn-save").on("click",()=>{
			this.save();
			
		}),
		$("#btn-delete").on("click",()=>{
			this.deleteById();
		});
		$("#btn-update").on("click",()=>{
			this.update();
		});
		
		$("#btn-replySave").on("click",()=>{
			if(document.querySelector("#reply-content").value == null) {
				alert("댓글을 입력해주세요")
				return false;
			}
			this.replySave();
			
		})

	},
	save: function() {
		let data = {
			secret: $("#secret").val(),
			category: $("#category").val(),
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({ 
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href="/auth/support";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	update: function(){
		let id=$("#id").val();
		
		let data={
			title: $("#title").val(),
			content: $("#content").val()
		};
		console.log(id);
		console.log(data);
		$.ajax({ 
			type:"PUT",
			url:"/api/board/"+id,
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			alert("수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	
	deleteById: function(){
		let id=$("#id").text();
		
		$.ajax({ 
			type:"DELETE",
			url:"/api/board/"+id,
			dataType:"json" 
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href="/auth/support";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	replyDelete: function(boardId,replyId) {

		$.ajax({
			type:"DELETE",
			url:`/api/board/${boardId}/reply/${replyId}`,
			dataType:"json"
		}).done(function(resp){
			alert("댓글삭제 성공.");
			location.href=`/board/${boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
/*	deleteById: function(){
		let id=$("#id").text();
		
		$.ajax({ 
			type:"DELETE",
			url:"/api/board/"+id,
			dataType:"json" 
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href="/auth/support";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},*/
	replySave:function(){
		let data={
			usersId: $("#usersId").val(),
			boardsId: $("#boardsId").val(),
			content: $("#reply-content").val()
		};
		
		
		$.ajax({ 
			type:"POST",
			url:"/api/board/${data.boardsId}/reply",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			alert("댓글 작성이 완료되었습니다.");
			// location.href="/board/" + data.boardsId;
			location.href="/board/" + data.boardsId;

		}).fail(function(error){
			alert(JSON.stringify(error));

		});
	},
	
}
index.init();