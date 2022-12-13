function checkId() {
	var id = $('#username').val();
	
	$.ajax({
		url:'./idCheck',
		type:'post',
		data:{username:id},
		success:function(cnt){//컨트롤러에서 넘어온 cnt 값 받음
			if(cnt == 0) {
				$('.id_ok').css("display","inline-block");
				$('.id_already').css("display","inline-block");
			}
			
			
		}
	})
}