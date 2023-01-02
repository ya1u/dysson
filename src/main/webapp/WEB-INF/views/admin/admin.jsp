<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>
<style>
    a {
        text-decoration: none;
        color:black;
    }
    .container {
        max-width:960px;

    }
    .search-tab{
        display: flex;
        height: 35px;

    }

    .search {
        width: 30%;
        right:100px;

        display:flex;

        margin-left:auto;


    }

    .searchTerm {
        width: 100%;
        border: 3px solid #333;
        border-right: none;
        padding: 5px;

        border-radius: 5px 0 0 5px;
        outline: none;
        color: #333;

    }

    .searchTerm:focus{
        color: #333;
    }

    .searchButton {
        width: 80px;

        border: 1px solid #333;
        background: #333;
        text-align: center;
        color: #fff;
        border-radius: 0 5px 5px 0;
        cursor: pointer;
        font-size: 20px;
        margin-left:auto;
    }
    .line {
        line-height: 45px;
    }
    .btn-kick {
        border: 1px solid #0F9E5E;
    }
    .text-width {

        overflow:hidden;
        text-overflow:ellipsis;
        white-space:nowrap;
    }

</style>
<div class="container">
    <div class="board-title" style="text-align: left; font-size: 25px; color:#333">| 매출표</div>
        <div id="chart_div" style="width: 900px; height: 500px;"></div>
    <c:set var = "totalAll" value = "0" />
    <c:set var = "totalKitchen" value = "0" />
    <c:set var = "totalAir" value = "0" />
    <c:set var = "totalCleaner" value = "0" />
    <input type="hidden" value="<c:forEach var="orderItemAll" items="${orderItemAll}" varStatus="status">
				${orderItemAll.itemTotalPrice}
				<c:set var="totalAll" value="${totalAll + orderItemAll.itemTotalPrice}"/>
				<c:out value="${totalAll}"/>
			</c:forEach>">

    <input type="hidden" value="<c:forEach var="orderItemKitchen" items="${orderItemKitchen}" varStatus="status">
				${orderItemKitchen.itemTotalPrice}
				<c:set var="totalKitchen" value="${totalKitchen + orderItemKitchen.itemTotalPrice}"/>
				<c:out value="${totalKitchen}"/>
			</c:forEach>">

    <input type="hidden" value="<c:forEach var="orderItemAir" items="${orderItemAir}" varStatus="status">
				${orderItemAir.itemTotalPrice}
				<c:set var="totalAir" value="${totalAir + orderItemAir.itemTotalPrice}"/>
				<c:out value="${totalAir}"/>
			</c:forEach>">

    <input type="hidden" value="<c:forEach var="orderItemCleaner" items="${orderItemCleaner}" varStatus="status">
				${orderItemCleaner.itemTotalPrice}
				<c:set var="totalCleaner" value="${totalCleaner + orderItemCleaner.itemTotalPrice}"/>
				<c:out value="${totalCleaner}"/>
			</c:forEach>">

    <c:if test="${category eq 'user'}">
        <div class="board-title" style="text-align: left; font-size: 25px; color:#333">| 회원 관리</div>
        <!-- 정렬 및 검색 탭 -->
        <form action="/admin" method="GET" class="form-inline bd-highlight justify-content-between">
            <div class="search">
                <select id="select" class="form-control searchTerm" style="width: 50%;border-right: 3px solid #333" onchange="selectSearchType()">
                    <option value="username">아이디</option>
                    <option value="username">이름</option>
                </select>&nbsp;
                <input type="hidden" name="category" id="category" value="${param.category}">
                <input type="hidden" name="searchType" id="searchType" value="username">

                <input type="text" class="searchTerm" placeholder="Search" name="searchKeyword">
                <button type="submit" class="searchButton">
                    <i class="fa fa-search"></i>
                </button>
            </div>
        </form><br>
    <table class="table table-sm table-hover">
        <thead class="thead-dark">
        <tr class="text-center">
            <th style="width: 10%;">번호</th>
            <th style="width: 15%;">아이디</th>
            <th style="width: 15%;">이름</th>
            <th style="width: 25%;">이메일</th>
            <th style="width: 20%;">가입일</th>
            <th style="width: 15%;">관리</th>
        </tr>
        </thead>
        <tbody class="text-center">
        <c:forEach var="user" items="${users.content}">
                <tr class="line">
                    <th>${user.id}</th>
                    <th>${user.username}</th>
                    <th>${user.name}</th>
                    <th>${user.email}</th>
                    <th><fmt:formatDate value="${user.createDate}" pattern="YYYY-MM-dd"/></th>
                    <th><button class="btn btn-kick" onClick="index2.deleteById(${user.id})" id="btn-del">회원 삭제</button></th>
                </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 회원 관리 탭 끝 -->

    <!-- 페이징 -->
    <c:set var="startPage" value="${users.number - users.number % 5}" />
    <ul class="pagination justify-content-center">
        <li class="page-item <c:if test='${users.number < 5}'>disabled</c:if>">
            <a class="page-link" href="/admin?category=${param.category}&page=${startPage - 5}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"><</a>
        </li>
        <c:forEach var="page" begin="1" end="5">
            <c:if test="${(startPage + page) <= users.totalPages}">
                <li class="page-item <c:if test='${users.number eq startPage + page - 1}'>active</c:if>">
                    <a class="page-link" href="/admin?category=${param.category}&page=${startPage + page - 1}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}">${startPage + page}</a>
                </li>
            </c:if>
        </c:forEach>
        <li class="page-item <c:if test='${startPage + 5 > users.totalPages}'>disabled</c:if>">
            <a class="page-link" href="/admin?category=${param.category}&page=${startPage + 5}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}">></a>
        </li>
    </ul>
    <!-- 페이징 끝 -->


        <br>
        <div class="board-title" style="text-align: left; font-size: 25px; color:#333">| 상품 관리</div>
        <br>
        <div style="text-align: left; "><a href="/product/addForm" style="color:#4ba53a;"> # 상품 등록하기</a></div>
        <div style="text-align: left"># 등록된 상품</div>


        <table class="table table-sm table-hover">
            <thead class="thead-dark">
            <tr class="text-center">
                <th style="width: 5%;">상품번호</th>
                <th style="width: 15%;">카테고리</th>
                <th style="width: 25%;">상품명</th>
                <th style="width: 20%;">가격</th>
                <th style="width: 10%;">평점</th>
                <th style="width: 25%;">관리</th>
            </tr>
            </thead>
            <tbody class="text-center">
            <c:forEach var="product" items="${product.content}">
                <tr class="line">
                    <th>${product.id}</th>
                    <th>${product.category}</th>
                    <th class="text-width"><a href="/product/${product.id}">${product.name}</a></th>

                    <th><fmt:formatNumber value="${product.price}" pattern="#,###"/></th>
                    <th>${product.ratingAvg}</th>
                    <th>
                        <button class="btn btn-kick" onClick="index.deleteById2(${product.id})" id="btn-delete">상품 삭제</button>
                        <button class="btn btn-kick"><a href="/product/updateForm/${product.id}"> 수정</a></button>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- 페이징 -->
        <c:set var="startPage" value="${product.number - product.number % 5}" />
        <ul class="pagination justify-content-center">
            <li class="page-item <c:if test='${product.number < 5}'>disabled</c:if>">
                <a class="page-link" href="/admin?&page=${startPage - 5}"><</a>
            </li>
            <c:forEach var="page" begin="1" end="5">
                <c:if test="${(startPage + page) <= product.totalPages}">
                    <li class="page-item <c:if test='${users.number eq startPage + page - 1}'>active</c:if>">
                        <a class="page-link" href="/admin?&page=${startPage + page - 1}">${startPage + page}</a>
                    </li>
                </c:if>
            </c:forEach>
            <li class="page-item <c:if test='${startPage + 5 > product.totalPages}'>disabled</c:if>">
                <a class="page-link" href="/admin?&page=${startPage + 5}">></a>
            </li>
        </ul>
        <!-- 페이징 끝 -->
    </c:if>

    <br>
    <div class="board-title" style="text-align: left; font-size: 25px; color:#333">| 주문 관리</div>
    <br>
    <table class="table table-sm table-hover">
        <thead class="thead-dark">
        <tr class="text-center">
            <th style="width: 7%;">주문번호</th>
            <th style="width: 13%;">주문일시</th>
            <th style="width: 10%;">주문자(ID)</th>
            <th style="width: 9%;">주문상태</th>
            <th style="width: 30%;">배송지</th>
            <th style="width: 9%;">배송상태</th>
            <th style="width: 40%;">관리</th>
        </tr>
        </thead>
        <tbody class="text-center">
        <c:forEach var="order" items="${order}">
            <tr class="line">
                <th>${order.id}</th>
                <th>${order.createDate}</th>
                <th>${order.user.username}</th>
                <c:choose>
                <c:when test="${order.isCancel == 0}">
                    <th>결제완료</th>
                </c:when>
                <c:otherwise>
                    <th>취소완료</th>
                </c:otherwise>
                </c:choose>
                <th>${order.user.address}</th>
                <th>
                    <c:choose>
                        <c:when test="${order.isCancel == 0}">
                            <c:if test="${order.isDelivery == 0}">배송준비중</c:if>
                            <c:if test="${order.isDelivery == 1}">배송중</c:if>
                            <c:if test="${order.isDelivery == 2}">배송완료</c:if>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </th>
                <th>
                    <c:choose>
                        <c:when test="${order.isCancel == 0}">
                            <button class="btn btn-kick"><a href="/order/cancel/${order.user.id}/${order.id}">주문취소</a></button>
                            <button class="btn btn-kick"><a href="/admin/order/isDelivery/${order.id}">배송상태변경</a></button>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../layout/footer.jsp"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawVisualization);

    function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
            ['Day', '매출액'],
            ['ALL', ${totalAll} ],
            ['KITCHEN', ${totalKitchen} ],
            ['AIR',  ${totalAir}],
            ['CLEANER',  ${totalCleaner}],



        ]);

        var options = {
            title : '카테고리별 매출액',
            vAxis: {title: 'sales revenue'},
            hAxis: {title: '카테고리'},
            seriesType: 'bars',
            series: {4: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
</script>
<script type="text/javascript" src="/js/product.js"></script>
<script type="text/javascript" src="/js/user.js"></script>
