<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/joinform.css">

	<form>
	    <div class="member">
	      <!--<a href="#"><img class="logo" src="/image/logo.png"></a>  -->
	
	      <div class="field">
	          <b>아이디</b>
	          <div class="field-id">
	            
	            <input type="text" placeholder="UserID" class="item" id="username" name="username" oninput="checkId()" style="text-align: left;">
	            <!-- id ajax 중복체크 -->
				<span class="id_ok">사용 가능한 아이디입니다.</span>
				<span class="id_already">누군가 이 아이디를 사용하고 있어요.</span>
	            <button class="item" id>중복확인</button>
	          </div>
	      </div>
	      <div class="field">
	          <b>비밀번호</b>
	          <input class="userpw" type="password" id="password" placeholder="Password" style="text-align: left;">
	      </div>
	      <div class="field">
	          <b>비밀번호 재확인</b> 
	          <input class="userpw-confirm" type="password" placeholder="Password_check" id="pwcheck" style="text-align: left;"> 
	      </div>
	      <div class="field">
	          <b>이름</b>
	          <input type="text" placeholder="Name" id="name" style="text-align: left;">
	      </div>
	
	
	      <div class="field">
	          <b>Email</b>
	          <input type="email" placeholder="Email" id="email" style="text-align: left;">
	      </div>
	      <div class="field">
	        <b>전화번호</b>
	        <input type="text" placeholder="Telephone" id="phone" style="text-align: left;">
	      </div>
	      <div class="field">
	        <b>주소</b>
	    
	     
	          <input type="text" id="address" placeholder="주소찾기" class="item" readonly style="text-align: left;">        
	  
	          <input type="text" placeholder="상세주소" name="address_detail" id="addressDetail" style="text-align: left;">
	        
	      </div>
	    <input type="button" value="가입하기" id="btn-save" style="text-align: left;">
	</div>
  </form>
  <%@ include file="../layout/footer.jsp"%>

    <script src="/js/user.js"></script>
    <script src="/js/joinCheck.js"></script>
    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
    window.onload = function(){
        document.getElementById("address").addEventListener("click", function(){ //주소입력칸을 클릭하면
            //카카오 지도 발생
            new daum.Postcode({
                oncomplete: function(data) { //선택시 입력값 세팅
                    document.getElementById("address").value = data.address; // 주소 넣기
                    document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
                }
            }).open();
        });
    }
    </script>
</body>
</html>