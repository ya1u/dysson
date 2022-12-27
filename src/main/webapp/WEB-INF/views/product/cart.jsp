<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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
    .cnt {
      width: 120px;
      display: flex;
    }
    .me-3 {
      margin-left: 16px;
    }
</style>
<main id="cart" style="max-width:650px">
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
              <div class="col-2 cnt">
<%--                <p class="cartItemQuantity p-1 text-center">${cartItem.count}</p>--%>
                <button class="ambtn" onclick="location.href='/api/cartItem/minus/${cartItem.id}/${user.id}'">&nbsp;-&nbsp;</button>
                <input class="form-control text-center me-3" id="amount" name="amount" type="text" value="${cartItem.count}" style="max-width: 3rem"></input>
                <button class="ambtn" onclick="location.href='/api/cartItem/plus/${cartItem.id}/${user.id}'">&nbsp;+&nbsp;</button>
              </div>
              <div class="col-2">
                <p id="cartItem1Price"><fmt:formatNumber value="${(cartItem.count)*(cartItem.product.price)}" pattern="#,###"/>원</p>
              </div>
              <div class="col-2">
                <a href="/user/cart/${principal.user.id}/${cartItem.id}/delete" class="btn btn-warning" style="background-color: #DC3545; border: #DC3545;">삭제</a>
              </div>
            </div>
            <hr>
          </c:forEach>

          <div class="row mx-0 mb-2">
            <div class="col-sm-8 p-0 d-inline">
              <h3>Total.</h3>
            </div>
            <div class="col-sm-4 p-0">
                <h5 id="total" style="font-size: 20px; padding-top: 5px"><fmt:formatNumber value="${totalPrice}" pattern="#,###"/>원</h5>
            </div>
            </div>
          </div>
          <c:choose>
            <c:when test="${totalPrice > 0}">
              <a href="/order/cart/${principal.user.id}"><button id="btn-checkout" class="shopnow"><span>구매하기</span></button></a>
            </c:when>
            <c:otherwise>
              <a href="#"><button id="btn-checkout" class="shopnow" onclick="alert('장바구니가 비었습니다.')"><span>구매하기</span></button></a>
            </c:otherwise>
          </c:choose>
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