let index = {
	init: function() {
		$("#btn-pay").on("click",()=>{
			this.pay();

		});
	},

	pay: function() {
		var IMP = window.IMP;
		IMP.init('imp17768865');
		var msg;

		IMP.request_pay({
			pg: 'kakaopay',
			pay_method : 'card',
			merchant_uid : 'dysson001' + new Date().getTime(),
			name : "다이쓴 제품",
			amount : 100,
			buyer_email : "koy@koy.com",
			buyer_name : "김오영",
			buyer_tel : "010-1234-1234",
			buyer_addr : "수원시",
			buyer_postcode : "01010"
		}, function(resp) {

			if(resp.success) {
				//서버단에서 결제정보 조회를 위해 ajax로 imp_uid전달

				$.ajax({
					url: "/payment/complete", //cross-domain error가 발생하지 않도록 주의
					type: 'POST',
					dataType : 'json',
					data: {
						imp_uid : resp.imp_uid
					}



			}).done(function(data){
				//서버에서 REST API로 결제 정보확인 및 서비스루틴이 정상인경우
				if(all_fine) {
					msg = '결제가 완료되었습니다';
					msg += '\n고유ID : ' + resp.imp_uid;
					msg += '\n상점 거래ID : ' + resp.merchant_uid;
					msg += '\n결제 금액 : ' + resp.paid_amount;
					msg += '\n카트 승인번호 : ' + resp.apply_num;

					alert(msg);

				} else {
					alert("제대로 결제안됨");
				}
			});
			//성공시 이동 페이지

			location.href="/order/order";
		}else {
			msg = '결제에 실패하였습니다'
			msg += '에러내용 : ' + resp.error_msg;

			//실패시 이동페이지
			location.href="/";
			alert(msg);
		}

		});
	}			
}

index.init();




/*$(function(){
		var IMP = window.IMP;
		IMP.init('imp17768865');
		var msg;
		
		IMP.request_pay({
			pg: 'kakaopay',
			pay_method : 'card',
			merchant_uid : 'dysson001' + new Date().getTime(),
			name : "다이쓴 제품",
			amount : 100,
			buyer_email : "koy@koy.com",
			buyer_name : "김오영",
			buyer_tel : "010-1234-1234",
			buyer_addr : "수원시",
			buyer_postcode : "01010"
		}, function(resp) {
			
			if(resp.success) {
				//서버단에서 결제정보 조회를 위해 ajax로 imp_uid전달	
			
				$.ajax({
					url: "/payment/complete", //cross-domain error가 발생하지 않도록 주의
					type: 'POST',
					dataType : 'json',
					data: {
						imp_uid : resp.imp_uid
					}
				
				
				
			}).done(function(data){
				//서버에서 REST API로 결제 정보확인 및 서비스루틴이 정상인경우
				if(all_fine) {
					msg = '결제가 완료되었습니다';
					msg += '\n고유ID : ' + resp.imp_uid;
					msg += '\n상점 거래ID : ' + resp.merchant_uid;
					msg += '\결제 금액 : ' + resp.paid_amount;
					msg += '\카트 승인번호 : ' + resp.apply_num;
					
					alert(msg);
					
				} else {
					alert("제대로 결제안됨");
				}
			});
			//성공시 이동 페이지
			
			location.href="/";
		}else {
			msg = '결제에 실패하였습니다'
			msg += '에러내용 : ' + resp.error_msg;
			
			//실패시 이동페이지
			location.href="/";
			alert(msg);
		}
			
		});
	
	})*/