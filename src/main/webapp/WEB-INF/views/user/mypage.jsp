<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
	a {
		text-decoration: none;
	}
</style>
<div style="background-color:white;border-radius:4px;padding:20px;min-height:500px; width:960px; margin: auto;">
	<br>
	<h3><span style="font-size:20px">${principal.user.username } 님의</span> My Page</h3>
	<div style="width:100%;margin-top:20px;margin-bottom:20px;">
		<table style="width:100%;height:50px;text-align:center;font-weight:bold;border:1px solid #e0e0e0;">
			<tbody>
			<tr>
				<td id="mypage_menu_1" style="width: 33.33%; border-right: 1px solid rgb(224, 224, 224); background-color: #e0e0e0;">
					<a href="/shop/mypage/mypage_orderlist.php">
						<span style="color:black;">주문내역/배송조회</span>
					</a>
				</td>
				<td id="mypage_menu_2" style="width: 33.33%; border-right: 1px solid rgb(224, 224, 224); background-color: rgb(255, 255, 255);">
					<a href="/shop/member/myinfo.php">
						<span style="color:black;">회원정보관리</span>
					</a>
				</td>
				<td id="mypage_menu_3" style="width:33.33%;border-right:1px solid #e0e0e0;">
					<a href="/shop/member/hack.php">
						<span style="color:black;">회원탈퇴</span>
					</a>

				</td>


			</tr>
			<tr>
				<td colspan="7" style="height:2px;background-color:#707070;"></td>
			</tr>
		</tbody>
		</table>	
	</div>
<table width="100%" >
<tbody >
	<tr style="background-color:#e0e0e0;height:40px;font-weight:bold;color:#505050;">
		<th>번호</th>
		<th>구분</th>
		<th>주문일시</th>
		<th>주문번호</th>
		<th>결제방법</th>
		<th>주문금액</th>
		<th>취소금액</th>
		<th>주문상태</th>
		<th>수령확인</th>
		<th>상세보기</th>
	</tr>
	<tr >
		<td>1</td>
		<td>test</td>
		<td>2022-12-12</td>
		<td>10245</td>
		<td>test</td>
		<td>test</td>
		<td>10000</td>
		<td>2000</td>
		<td>미수령</td>
		<td>확인</td>
	</tr>



</tbody>
</table>
<hr>


<table style="width:100%;margin-bottom:10px;">
  <tbody>
    <tr>
      <td align="center">
      </td>
    </tr>
  </tbody>
</table>

<table width="100%">
<colgroup><col width="100"><col width="100"><col width="100" align="center"><col width="50" align="center"><col width="70" align="center"><col width="70" align="center">
</colgroup><tbody><tr style="background-color:#e0e0e0;height:40px;font-weight:bold;color:#505050;">
	<th>쿠폰</th>
	<th>적용상품</th>
	<th>사용일 및 기간</th>
	<th>기능</th>
	<th>할인<br>적립</th>
	<th>사용여부</th>
</tr>
<tr><td height="1" bgcolor="#D6D6D6" colspan="10"></td></tr>
<tr height="25">
	<td><div style="text-overflow:ellipsis;overflow:hidden;width:250px;padding-left:10px;line-height:18px;" nowrap="">발뮤다 회원가입 5% 쿠폰</div>
		<div style="text-overflow:ellipsis;overflow:hidden;width:250px;padding-left:10px;line-height:18px;font-size:12px;color:#909090;" nowrap="">회원가입 5% 할인쿠폰</div>
	</td>
	<td><div style="text-overflow:ellipsis;overflow:hidden;width:250px;padding-left:10px;line-height:18px;" nowrap=""> - </div></td>
	<td style="font-size:12px;">2022-12-07~ <br>2023-04-01</td>
	<td>할인</td>
	<td>5%</td>
	<td><font color="#007FC8">미사용</font></td>
</tr>
<tr><td colspan="7" height="1" bgcolor="#ECECEC"></td></tr>
</tbody></table>
<div style="height:12px"></div>
<div align="right" style="padding-top:5;">

</div>
<div style="height:30px"></div>
<div style="text-align:left;padding-left:10px;color: #007fc8; font-size: 11px; margin: 3px 0 0 3px; line-height:150%;">
* 아래와 같이 정상 결제되지 않은 경우 사용된 쿠폰을 미사용 상태로 변경하여 재사용하실 수 있습니다.<br>
1)  결제가 진행되는 과정에서 직접 취소한 경우<br>
2)  인터넷 환경 등의 시스템 문제로 인하여 결제가 완료되지 못한 경우<br>
3)  신용카드 상태 등의 문제로 결제가 실패한 경우 <br>
<a href="#" style="font-weight:bold;color: #007fc8;">[주문내역/배송조회 바로가기 &gt;]</a>
</div><!-- End indiv -->

<div style="width:0;height:0;font-size:0"></div>
</div>
<%@ include file="../layout/footer.jsp"%>

