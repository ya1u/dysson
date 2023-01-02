let index2={
	init: function() {
		$("#btn-save").on("click", () => {
			if (document.querySelector("#password").value != document.querySelector("#pwcheck").value) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			if(document.frm.rename.value.length==0){
				alert("아이디 중복 체크를 해주세요.");
				return false;
			}
			this.save();
		});

		$("#btn-find").on("click", () => {
			this.find();

		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#idCheck").on("click", () => {
			this.idCheck();
		});

	},

	save: function(){
		let data={
			username: $("#username").val(),
			name: $("#name").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			phone: $("#phone").val(),
			address: $("#address").val().concat(" "+$("#addressDetail").val())

		};


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

				if(resp.data.hasOwnProperty('valid_name')){
					$('#valid_name').text(resp.data.valid_name);
					$('#valid_name').css('color', 'red');
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
			location.href="/mypage/" + data.id;
			//응답이 정상
		}).fail(function(error){
			alert(JSON.stringify(error));
			//응답이 비정상
		});
	},


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


	        } else {
	            alert("임시 비밀번호가 발송되었습니다.");
	            location.href = "/auth/loginForm";
	        }
	    }).fail(function(error) {
	        alert(JSON.stringify(error));
	    });
	},

	deleteById: function(id) {
		console.log(id);

		$.ajax({
			type: "DELETE",
			url: `/user/${id}`,
			dataType: "json"
		}).done(function(resp) {
			alert("계정이 탈퇴되었습니다.");
			location.href = "/admin";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	deleteById2: function(id) {


		$.ajax({
			type: "POST",
			url: `/userdel/${id}`,
			data: {
				inputPwd: $("#inputPwd").val()
			}
		}).done(function(resp) {
			// alert("계정이 탈퇴되었습니다.");

		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	idCheck: function () {
		if (document.frm.username.value.length==0) {
			alert("아이디를 입력해주세요");
			frm.username.focus();
			return;
		}
		let url = "/auth/idCheck/username/" + document.frm.username.value;
		window.open(url,"_blank_1", "toolbar=no , menubar=no, scrollbars=yes,resizeable=no,width=450px,height=200px");

	},
	idok: function(username) {

		opener.frm.username.value=document.frm.username.value;
		opener.frm.rename.value=document.frm.username.value;
		self.close();

	}

	// deleteById2: function(id) {
	// 	console.log(id);
	// 	let data = {
	// 		inputPwd : $("#inputPwd").val()
	// 	}
	//
	// 	$.ajax({
	// 		type: "GET",
	// 		url: '/mypage/userWithdrawal/' +data.inputPwd,
	// 		dataType: "json"
	// 	}).done(function(resp) {
	// 		alert("계정이 탈퇴되었습니다.");
	// 	}).fail(function(error) {
	// 		alert(JSON.stringify(error));
	// 	});


}


index2.init();