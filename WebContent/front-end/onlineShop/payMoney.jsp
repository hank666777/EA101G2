<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="com.mycoupon.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String amount = (String) session.getAttribute("amount");
	MyCpService myCpService = new MyCpService();
	String memno= (String) session.getAttribute("memno");
	List<MyCouponVO> myCoupon = myCpService.getMyCoupon2(memno);		
	pageContext.setAttribute("myCoupon", myCoupon);
%>
<jsp:useBean id="cps" scope="page" class="com.coupon.model.CpService" />
<html lang="zh-Hant-TW">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>MISS M付款頁面</title>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/onlineShop/css/style.css">
<script src="https://use.typekit.net/nxw4uqt.js"></script>
<script>try{Typekit.load({ async: true });}catch(e){}</script>

<script type="text/javascript">

$(function(){
	$(".select").on("change",function(){
		
		$.ajax({
			url:"<%=request.getContextPath()%>/product/OnlineShopServlet.do",
			type:"GET",
			data:{myCoupon:$(".select").val(),
				  action: "paypay" 
				 },
			success:function(data){
			$(".money").val(data);	
			}
			
		})
		
	})
	
})



</script>
</head>
<body>


	<div class="container animated slideInRight">
 <a href="<%=request.getContextPath()%>/front-end/onlineShop/OCart.jsp"><h1>MISS M~</h1></a>
<form action="<%=request.getContextPath()%>/ONOServlet/ONOServlet.do" method="post">
	<label for="">Card type</label>
	<select name="" id="" required tab-index="1">
		<option value="Visa">Visa</option>
		<option value="Mastercard">Mastercard</option>
		<option value="Amex">American Express</option>
		<option value="Paypal">Discover</option>
	</select>
	<label for="">Cardholder name</label>
	<input type="text" placeholder="John Smith" required tab-index="2" value="${memVO.mName}"/>
	<label for="">Card #</label>
	<input id="cc"type="text" placeholder="1234 5678 9010" required  value="1234 5678 9010 2354"/>
	<label for="">Exp. date</label>
	<input id="exp-date" type="text" placeholder="MM/YY" value="08/24">
	<label for="">CVC</label>
	<input id="cvc" type="text" placeholder="123" required  value="035"/>
		
	<select class="select" name="myCoupon">	
	 <option value="">請選擇優惠券</option>	
		<c:forEach var="myCoupon" items="${myCoupon}">
								 
			<c:forEach var="CouponVO" items="${cps.all}">		
                    <c:if test="${myCoupon.couponno==CouponVO.couponno}">
	          			 <option value="${myCoupon.couponsno}">${CouponVO.couponName}</option>
	       
                    </c:if>                
            </c:forEach>        			
		</c:forEach>
	</select>
	
	<input type="hidden" name="onoTotal"  value="<fmt:formatNumber type="number" value="<%=amount %>"></fmt:formatNumber>">
	<input type="hidden" name="action" value="Detail">
	<input id="paySubmitBtn" class="money" type="submit" value="需付新台幣$<fmt:formatNumber type="number" value="<%=amount %>"></fmt:formatNumber>">
	
</form>
</div>
<script>
//按鈕鎖定，避免重複送出
$('#"paySubmitBtn"').click(function(){
	var btn = this;
	setTimeout(function(){
		btn.disabled = true;
	},50);
});
</script>

 <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.3.4/jquery.inputmask.bundle.min.js'></script>
<script  src="<%=request.getContextPath()%>/front-end/onlineShop/js/script.js"></script>
</body>
</html>