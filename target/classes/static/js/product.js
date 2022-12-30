let index={
	init: function() {
		$("#btn-add").on("click",()=>{
			this.add();
		});
		$("btn-update").on("click",()=>{
			this.update();
		});
		$("#btn-reviewSave").on("click",()=>{
			this.reviewSave();
		})
	},
	add: function() {
		let data = {
			name: $("#name").val(),
			content: $("#content").val(),
			made: $("#made").val(),
			price: $("#price").val(),
			category: $("#category").val(),
			img: $("#img").val()
		};
		$.ajax({
			type: "POST",
			url: "/api/product",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			alert("제품 등록이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error))
		});
	},
	deleteById: function(id) {
		console.log(id);
		$.ajax({
			type: "DELETE",
			url: `/api/product/${id}`,
			dataType: "json"
		}).done(function(resp) {
			alert("제품이 삭제되었습니다.");
			location.href = "/product/store";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	deleteById2: function(id) {
		console.log(id);
		$.ajax({
			type: "DELETE",
			url: `/api/product/${id}`,
			dataType: "json"
		}).done(function(resp) {
			alert("제품이 삭제되었습니다.");
			location.href = "/admin";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	update: function() {
		let id = $("#id").val();
		
		let data = {
			name: $("#name").val(),
			content: $("#content").val(),
			made: $("#made").val(),
			price: $("#price").val(),
			category: $("#category").val()
		};
		$.ajax({
			type: "PUT",
			url: "/api/product/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	reviewSave: function() {
		let data = {
			usersId: $("#usersId").val(),
			productId: $("#productId").val(),
			rate: $('[name=reviewStar]:checked').val(),
			content: $("#content").val()
			
			
		}
		if(data.rate == null) {
			data.rate = 0;

		}
		if(data.content == "" || data.content == null) {
			alert("내용을 입력해주세요")
			return false;
		}
		$.ajax({ 
			type:"POST",
			url:`/api/product/${data.productId}/review`,
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			alert("리뷰 등록 완료되었습니다.");
			location.href=`/product/reviewAvg/${data.productId}/${data.rate}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});					
	
	},
	
}
index.init();