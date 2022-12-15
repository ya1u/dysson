let index = {
	init: function() {
		$("#btn-save").on("click",()=>{
			this.save();
			
		}),
		$("#btn-replySave").on("click",()=>{
			this.replySave();
			
		})
		
	},
	save: function() {
		let data = {
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
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	replySave:function(){
		let data={
			usersId: $("#usersId").val(),
			boardsId: $("#boardsId").val(),
			content: $("#reply-content").val()
		};
		
		
		$.ajax({ 
			type:"POST",
			url:`/api/board/${data.boardsId}/reply`,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			/*alert("댓글 작성이 완료되었습니다.");*/
			location.href=`/board/${data.boardsId}`;

		}).fail(function(error){
			alert(JSON.stringify(error));

		});
	},
	
}
index.init();