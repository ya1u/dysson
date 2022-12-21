<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link href="/css/cart.css" rel="stylesheet">
<style>
    .col-2 {
        margin: auto;
        padding: 0;
    }
    .col-5 {
        margin-top: 45px;
        width: 150px;
    }
    h3 {
      float: right;
    }
</style>
<main id="cart" style="max-width:600px">
    <div class="back"><a href="/product/store">&#11178; store</a></div>
    <h1>Your Cart</h1>
    <hr>
    <div class="container-fluid">
      <div class="row align-items-start">
        <div class="">
          <!--product list-->
          <c:forEach var="cartItem" items="${cartItems}">
            <div class="cartItem row align-items-start">
              <div class="col-3 mb-2">
                <img class="w-100" src="/img/${cartItem.product.imgName}" alt="art image">
              </div>
              <div class="col-5 mb-2">
                <h6 class="" style="font-size: 20px">${cartItem.product.name}</h6>
<%--                <p class="pl-1 mb-0">20 x 24</p>--%>
<%--                <p class="pl-1 mb-0">Matte Print</p>--%>
              </div>
              <div class="col-2">
<%--                <p class="cartItemQuantity p-1 text-center">${cartItem.count}</p>--%>
                <input class="form-control text-center me-3" id="amount" name="amount" type="number" value="${cartItem.count}" style="max-width: 5rem"></input>
              </div>
              <div class="col-2">
                <p id="cartItem1Price"><fmt:formatNumber value="${(cartItem.count)*(cartItem.product.price)}" pattern="#,###"/>원</p>
              </div>
              <div class="col-2">
                <a href="/user/cart/${user.id}/${cartItem.id}/delete" class="btn btn-warning" style="background-color: #DC3545; border: #DC3545;">삭제</a>
              </div>
            </div>
            <hr>
          </c:forEach>

          <div class="row mx-0 mb-2">
            <div class="col-sm-8 p-0 d-inline">
              <h3>Total.</h3>
            </div>
            <div class="col-sm-4 p-0">
              <p id="total" style="font-size: 20px; padding-top: 5px"><fmt:formatNumber value="${totalPrice}" pattern="#,###"/>원</h5>
            </div></p>
            </div>
          </div>
          <a href="#"><button id="btn-checkout" class="shopnow"><span>구매하기</span></button></a>
        </div>
      </div>
    </div>
    </div>
  </main>
  <br>
  <br>
  <br>
  <br>



<%@ include file="../layout/footer.jsp"%>