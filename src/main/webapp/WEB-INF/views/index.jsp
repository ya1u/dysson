<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
  
  <div class="container">
    <!-- Carousel -->
    <div id="demo" class="carousel slide" data-bs-ride="carousel">

      <!-- Indicators/dots -->
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
        <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
        <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
      </div>
      
      <!-- The slideshow/carousel -->
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="image/main2.jpg" alt="Los Angeles" class="d-block" style="width:100%">
        </div>
        <div class="carousel-item">
          <img src="image/main3.jpg" alt="Chicago" class="d-block" style="width:100%">
        </div>
        <div class="carousel-item">
          <img src="image/main1.jpg" alt="New York" class="d-block" style="width:100%">
        </div>
      </div>
      
      <!-- Left and right controls/icons -->
      <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
      </button>
    </div>
    <div class="container-fluid mt-3">
      <h3>dysson experience.</h3>
      <p>dysson 제품 및 서비스로 더욱더 많은 걸 누리다.</p>
    </div>
  </div>
<%@ include file="layout/footer.jsp"%>
