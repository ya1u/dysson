let index={
	init: function(){
		$("#btn-save").on("click",()=>{
			//화살표 함수사용 이유: this를 바인딩하기 위해 사용
			if(document.querySelector("#username").value == "" || document.querySelector("#password").value == "" || document.querySelector("#name").value == "" || document.querySelector("#phone").value == "" || document.querySelector("#email").value == "") {
				alert("필수 입력란을 모두 채워주세요.");
				return false;
			}
			if(document.querySelector("#password").value != document.querySelector("#pwcheck").value) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			this.save();
		});
		
	},
	
	save: function(){
		let data={
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			phone: $("#phone").val(),
			address: $("#address").val(),
		};
		
		$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"			
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			
		}).fail(function(error){
			alert(JSON.stringify(error));
			//응답이 비정상
		});
	
	}
}
index.init();