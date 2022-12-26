<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/checkout/">


  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

  <!-- Favicons -->
  <link rel="apple-touch-icon" href="/docs/5.0/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
  <link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
  <link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
  <link rel="manifest" href="/docs/5.0/assets/img/favicons/manifest.json">
  <link rel="mask-icon" href="/docs/5.0/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
  <link rel="icon" href="/docs/5.0/assets/img/favicons/favicon.ico">
  <meta name="theme-color" content="#7952b3">

  <style>
  	*:not(header){
  		text-align:left;
  	}
    .mb-4, p {
      text-align: center;
    }


  </style>

  <link href="https://getbootstrap.com/docs/5.0/examples/checkout/form-validation.css" rel="stylesheet">
</head>

<body class="bg-light">

  <div class="container">
    <main>
      <!-- 카트목록 -->
      <div class="row g-3">
        <div class="col-md-5 col-lg-4 order-md-last">
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-muted">Your cart</span>
            <span class="badge rounded-pill" style="background: dodgerblue">${user.cart.count}</span>
          </h4>
          
          
<!-- 장바구니 불러오기 코드 미구현-->


           <ul class="list-group mb-3">
            <li class="list-group-item d-flex justify-content-between lh-sm">
              <div>
                <h6 class="my-0">전기난로</h6>
                <small class="text-muted">상세정보</small>
              </div>
              <span class="text-muted">\90,000</span>
            </li>
            
            
            <li class="list-group-item d-flex justify-content-between">
              <span>합계 </span>
              <strong><fmt:formatNumber value="${totalPrice}" pattern="#,###"/>원</strong>
            </li>
          </ul>
          <!-- 카트목록끝 -->

        </div>
        <div class="col-md-7 col-lg-8">
          <h4 class="mb-3">상품구매</h4>
          <form class="needs-validation" novalidate>
            <div class="row g-3">
              <div class="col-sm-6">
                <label for="name" class="form-label">이름</label>
                <input type="text" class="form-control" id="name" placeholder="Name" value="${principal.user.name}" required>
                <!-- <div class="invalid-feedback">
                  Valid first name is required.
                </div> -->
              </div>


              <div class="col-6">
                <label for="username" class="form-label">아이디</label>
                <div class="input-group">
                
                  <input type="text" class="form-control" id="username" placeholder="Username" value="${principal.user.username}" required>

                </div>
              </div>

              <div class="col-12">
                <label for="username" class="form-label">이메일</label>
                <div class="input-group">
                  <span class="input-group-text">@</span>
                  <input type="text" class="form-control" id="email" placeholder="Email" value="${principal.user.email}" required>

                </div>
              </div>


              <div class="col-12">
                <label for="address" class="form-label">주소</label>
                <input type="text" class="form-control" id="address" placeholder="주소" value="${principal.user.address}" required>
                <div class="invalid-feedback">
                  Please enter your shipping address.
                </div>
              </div>

              <div class="col-12">
                <label for="addressDetail" class="form-label">상세주소(선택)</label>
                <input type="text" class="form-control" id="addressDetail" placeholder="상세주소" name="address-detail">
              </div>

            </div>

            <hr class="my-4">

            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="same-address">
              <label class="form-check-label" for="same-address">배송주소 불러오기</label>
            </div>

<!--             <div class="form-check">
              <input type="checkbox" class="form-check-input" id="save-info">
              <label class="form-check-label" for="save-info">배송정보 저장</label>
            </div> -->

            <hr class="my-4">

            <h4 class="mb-3">결제방법</h4>

            <div class="my-3">
              <div class="form-check">
                <input id="kakaoPay" name="paymentMethod" type="radio" class="form-check-input" required checked>
                <label class="form-check-label" for="kakaoPay"><img src="/image/kakaoPay2.png"></label>
              </div>
            </div>

            <hr class="my-4">

            <button class="w-100 btn btn-secondary btn-lg" type="button" id="btn-pay">구매하기</button>
          </form>
        </div>
        
      </div>
    </main>

  </div>
	<br>
	
  <!-- iamport.payment -->
  <script>
  
  
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

  <script src="https://getbootstrap.com/docs/5.0/examples/checkout/form-validation.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="/js/kakaoAddress.js"></script>
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
  <script src="/js/payment.js"></script>
<%@ include file="../layout/footer.jsp"%>