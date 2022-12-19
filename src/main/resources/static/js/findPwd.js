/*    $("#checkEmail").click(function () {
        let email = $("#userEmail").val();
        let username = $("#userName").val();

        $.ajax({
            type: "GET",
            url: "/check/findPw",
            data: {
                "email": email,
                "username": username
            },
            success: function (res) {
                if (res['check']) {
                    swal("발송 완료!", "입력하신 이메일로 임시비밀번호가 발송되었습니다.", "success").then((OK) => {
                        if(OK) {
                            $.ajax({
                                type: "POST",
                                url: "/check/findPw/sendEmail",
                                data: {
                                    "email": email,
                                    "username": username
                                }
                            })
                            window.location = "/login";
                        }


                    }
                )
                    $('#checkMsg').html('<p style="color:darkblue"></p>');
                } else {
                    $('#checkMsg').html('<p style="color:red">일치하는 정보가 없습니다.</p>');
                }
            }
        })
    })*/