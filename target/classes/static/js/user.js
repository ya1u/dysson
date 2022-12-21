let index={
	init: function(){
		$("#btn-save").on("click",()=>{
<<<<<<< HEAD
			//화살표 함수사용 이유: this를 바인딩하기 위해 사용
			if(document.querySelector("#username").value == "" || document.querySelector("#password").value == "" || document.querySelector("#name").value == "" || document.querySelector("#phone").value == "" || document.querySelector("#email").value == "") {
				alert("필수 입력란을 모두 채워주세요.");
				return false;
			}
=======
			

>>>>>>> parent of 7ac73a6 (test)
			if(document.querySelector("#password").value != document.querySelector("#pwcheck").value) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			this.save();
		});
		
<<<<<<< HEAD
		$("#forgot-pwd").on("click",()=>{
			this.findPwd();
=======
		$("#btn-find").on("click",()=>{
			this.find();
>>>>>>> parent of 7ac73a6 (test)
			
		});
		$("#btn-update").on("click",()=>{
			this.update();
		});
		
<<<<<<< HEAD
	},
	
	save: function(){
=======
		
	},
	
/*	save: function(){
>>>>>>> parent of 7ac73a6 (test)
		let data={
			username: $("#username").val(),
			name: $("#name").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			phone: $("#phone").val(),
			address: $("#address").val().concat(" "+$("#addressDetail").val())
				
		};

		console.log(data.address)
		$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"			
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			location.href="/auth/loginForm";
		}).fail(function(error){
			alert(JSON.stringify(error));
			//응답이 비정상
		});
	
<<<<<<< HEAD
=======
	},*/
	
	save: function(){
		let data={
			username: $("#username").val(),
			name: $("#name").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			phone: $("#phone").val(),
			address: $("#address").val().concat(" "+$("#addressDetail").val())
				
		};

		console.log(data.address)
		$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"			
		}).done(function(resp){
			if(resp.status == 400) {
				alert("회원가입 입력 정보를 다시 확인해주세요")
				
				if(resp.data.hasOwnProperty('valid_username')){
					$('#valid_username').text(resp.data.vaild.username);
					$('#valid_username').css('color','red');
				}
				else $('#vaild_username').text('');
				
				if(resp.data.hasOwnProperty('valid_password')){
					$('#valid_password').text(resp.data.valid_password);
					$('#valid_password').css('color', 'red');
				}
				else $('#valid_password').text('');
				
				
				if(resp.data.hasOwnProperty('valid_email')){
					$('#valid_email').text(resp.data.valid_email);
					$('#valid_email').css('color', 'red');
				}
				else $('#valid_email').text('');
			}
			else {
				alert("회원가입이 완료되었습니다.");
				location.href = "/auth/loginForm";	
			}
						
		}).fail(function(error){
			alert(JSON.stringify(error));
			
		});
	
>>>>>>> parent of 7ac73a6 (test)
	},
	update: function(){
		let data={
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			address: $("#address").val().concat(" "+$("#addressDetail").val())
		};

		console.log(data.password)
		console.log(data.email)
		console.log(data.address)
		$.ajax({ 
			type:"PUT",
			url:"/user",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			location.href="/auth/mypage";
			//응답이 정상
		}).fail(function(error){
			alert(JSON.stringify(error));
			//응답이 비정상
		});
	},
	
	
<<<<<<< HEAD
/*	findPwd: function() {
		LoadingWithMask();
		
		let data = {
			username: $("#username").val(),
			email: $("#email").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/auth/find",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"
			
			
		}).done(function(resp) {
        if (resp.status == 400) {
            if (resp.data.hasOwnProperty('valid_email')) {
                $('#valid_email').text(resp.data.valid_email);
                $('#email').focus();
            } else {
                $('#valid_email').text('');
            }

            if (resp.data.hasOwnProperty('valid_username')) {
                $('#valid_username').text(resp.data.valid_username);
                $('#username').focus();
            } else {
                $('#valid_username').text('');
            }

            closeLoadingWithMask();
=======
	find: function() {
	    
		
	    let data = {
	        username: $("#username").val(),
	        email: $("#email").val()	
	    };
	
	    $.ajax({
	        type: "POST",
	        url: "/auth/find",
	        data: JSON.stringify(data),
	        contentType: "application/json; charset=utf-8"
	    }).done(function(resp) {
	        if (resp.status == 400) {
	            if (resp.data.hasOwnProperty('valid_email')) { //'hasOwnProperty'객체가 특정 프로퍼티를 가지고 있는지를 나타내는 boolean 값을 반환
	                $('#valid_email').text(resp.data.valid_email);
	                $('#email').focus();
	            } else {
	                $('#valid_email').text('');
	            }
	
	            if (resp.data.hasOwnProperty('valid_username')) {
	                $('#valid_username').text(resp.data.valid_username);
	                $('#username').focus();
	            } else {
	                $('#valid_username').text('');
	            }
	
	          
>>>>>>> parent of 7ac73a6 (test)
	        } else {				
	            alert("임시 비밀번호가 발송되었습니다.");
	            location.href = "/auth/loginForm";
	        }
<<<<<<< HEAD
			    }).fail(function(error) {
			        console.log(error);
			    });
			}
		
		
	}*/
	
	
=======
	    }).fail(function(error) {
	        alert(JSON.stringify(error));
	    });
}
		
>>>>>>> parent of 7ac73a6 (test)
	
}
index.init();