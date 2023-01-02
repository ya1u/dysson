<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link href="/css/store.css" rel="stylesheet">
<style>
	.search {
	  width: 200px;
	  display:flex; 
	  margin:auto;
	}
	
	.searchTerm {
	  width: 100%;
	  border: 3px solid #333;
	  border-right: none;
	  padding: 5px;	  
	  border-radius: 5px 0 0 5px;
	  outline: none;
	  color: #9DBFAF;
	  	  
	}
	
	.searchTerm:focus{
	  color: #333;
	}
	
	.searchButton {
	  width: 40px;  
	  border: 1px solid #333;
	  background: #333;
	  text-align: center;
	  color: #fff;
	  border-radius: 0 5px 5px 0;
	  cursor: pointer;
	  font-size: 20px;
	  margin-left:auto;
	}
    @media screen and (max-width: 720px ) {
        .search{
            width: 50%;
        }
    }
</style>

<%-- <div class="container">
	<c:forEach var="product" items="${product.content}">
		<div class="card m-2">
			<div class="card-body">
				<img src="${product.img}" width="300" class="" onerror="this.src='/shop/data/skin/apple_tree/img/common/noimg_300.gif'">
				<h4 class="card-title">${product.name}</h4>
				<a href="/product/${product.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<ul class="pagination">
		<c:choose>
			<c:when test="${product.first}">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${product.number-1}">◀</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?page=${product.number-1}">◀</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${product.last}">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${product.number+1}">▶</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?page=${product.number+1}">▶</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div> --%>

<!-- 상품검색 -->
  
  <form action="/product/store" method="GET" style="text-align: center;">
    <div class="search">
       <input type="text" class="searchTerm" placeholder="Search" name="searchKeyword">
       <button type="submit" class="searchButton">
         <i class="fa fa-search"></i>
      </button>
    </div>
  </form>
<h1 class="text-center"><a href="/product/store">Category</a></h1>
<ul>
  <li><a href="/product/store/kitchen"> kitchen</a></li>
  <li><a href="/product/store/Air">Air</a></li>
  <li><a href="/product/store/Cleaner">Cleaner</a></li>
</ul>

<div class="container">
  <div class="row" id="slider-text">
    <div class="col-md-6" >

     
      <h2>NEW Collection</h2>

    </div>
  </div>
</div>

<!-- Item slider-->
<div class="container-fluid">

  <div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12">
      <div class="carousel carousel-showmanymoveone slide" id="itemslider" data-ride="carousel">
        <div class="carousel-inner">
        
        

        <c:forEach var="productKitchen" items="${productKitchen}">


		    <div class="item">
			  <div class="col-xs-12 col-sm-6 col-md-2">
				<a href="/product/${productKitchen.id}"><img src="/img/${productKitchen.imgName}" class="img-responsive center-block"></a>
				<h4 class="text-center">${productKitchen.name}</h4>
				<h5 class="text-center"><fmt:formatNumber value="${productKitchen.price}" pattern="#,###"/>원</h5>
			  </div>
			</div>
		  </c:forEach>
		  
		  <c:forEach var="product" items="${product.content}">
		    <div class="item">
			  <div class="col-xs-12 col-sm-6 col-md-2">
				<a href="/product/${product.id}"><img src="/img/${product.imgName}" class="img-responsive center-block"></a>
				<h4 class="text-center">${product.name}</h4>
				<h5 class="text-center"><fmt:formatNumber value="${product.price}" pattern="#,###"/>원</h5>
			  </div>
			</div>
		  </c:forEach>
		  
           <div class="item active">
            <div class="col-xs-12 col-sm-6 col-md-2">
              <a href="#"><img src="/image/soldout.jpg" class="img-responsive center-block"></a>
              <h4 class="text-center red">DYSSON The GreenFan C2</h4>
              <h5 class="text-center red">SOLD OUT</h5>
            </div>
          </div>
</div>          

          <!-- <div class="item">
            <div class="col-xs-12 col-sm-6 col-md-2">
              <a href="#"><img src="https://images.unsplash.com/photo-1524010349062-860def6649c3?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=e725946a3f177dce83a705d4b12019c2&auto=format&fit=crop&w=500&q=60" class="img-responsive center-block"></a>
              <h4 class="text-center">MAYORAL KOŠULJA</h4>
              <h5 class="text-center">800 TK</h5>
            </div>
          </div>

          <div class="item">
            <div class="col-xs-12 col-sm-6 col-md-2">
              <a href="#"><img src="https://images.unsplash.com/photo-1511556820780-d912e42b4980?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=04aebe9a22884efa1a5f61e434215597&auto=format&fit=crop&w=500&q=60" class="img-responsive center-block"></a>
              <span class="badge">10%</span>
              <h4 class="text-center">PANTALONE TERI 2</h4>
              <h5 class="text-center">4000,00 TK</h5>
            </div>
          </div>

          <div class="item">
            <div class="col-xs-12 col-sm-6 col-md-2">
              <a href="#"><img src="https://images.unsplash.com/photo-1531925470851-1b5896b67dcd?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=91fe0ca1b5d72338a8aac04935b52148&auto=format&fit=crop&w=500&q=60" class="img-responsive center-block"></a>
              <h4 class="text-center">CVETNA HALJINA</h4>
              <h5 class="text-center">4000,00 RSD</h5>
            </div>
          </div>

          <div class="item">
            <div class="col-xs-12 col-sm-6 col-md-2">
              <a href="#"><img src="https://images.unsplash.com/photo-1516961642265-531546e84af2?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=74065eec3c2f6a8284bbe30402432f1d&auto=format&fit=crop&w=500&q=60" class="img-responsive center-block"></a>
              <h4 class="text-center">MAJICA FOTO</h4>
              <h5 class="text-center">40,00 TK</h5>
            </div>
          </div>

          <div class="item">
            <div class="col-xs-12 col-sm-6 col-md-2">
              <a href="#"><img src="https://images.unsplash.com/photo-1532086853747-99450c17fa2e?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=61a42a11f627b0d21d0df757d9b8fe23&auto=format&fit=crop&w=500&q=60" class="img-responsive center-block"></a>
              <h4 class="text-center">MAJICA MAYORAL</h4>
              <h5 class="text-center">100,00 TK</h5>
            </div>
          </div>

        </div> -->

        <div id="slider-control">
        <a class="left carousel-control" href="#itemslider" data-slide="prev"><img src="https://pixsector.com/cache/852dce6a/avb91899cb3246210ca63.png" alt="Left" class="img-responsive"></a>
        <a class="right carousel-control" href="#itemslider" data-slide="next"><img src="https://pixsector.com/cache/ef1ee4a1/av85f1b171d762037fe92.png" alt="Right" class="img-responsive"></a>
      </div>
      </div>
    </div>
  </div>
</div>
<!-- Item slider end-->
<br/><br/>


 <script>

$(document).ready(function(){

$('#itemslider').carousel({ interval: 3000 });

$('.carousel-showmanymoveone .item').each(function(){
var itemToClone = $(this);

for (var i=1;i<6;i++) {
itemToClone = itemToClone.next();

if (!itemToClone.length) {
itemToClone = $(this).siblings(':first');
}

itemToClone.children(':first-child').clone()
.addClass("cloneditem-"+(i))
.appendTo($(this));
}
});
});

 </script>

<%@ include file="../layout/footer.jsp"%>