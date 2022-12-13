let index={
	init: function() {
		$("#btn-add").on("click",()=>{
			this.add();
		});
		$("#btn-delete").on("click",()=>{
			this.deleteById();
		});
		$("btn-update").on("click",()=>{
			this.update();
		});
	},
	add: function() {
		let data = {
			name: $("#name").val(),
			content: $("#content").val(),
			made: $("#made").val(),
			price: $("#price").val(),
			category: $("#category").val(),
			imgName: $("#imgName").val()
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
	deleteById: function() {
		let id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/product/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert("제품이 삭제되었습니다.");
			location.href = "/";
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
}
index.init();