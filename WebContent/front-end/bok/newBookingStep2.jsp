<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%	
	String bkperiod = (String)request.getAttribute("bkperiod");
	System.out.println(bkperiod);
	String bkdate = (String)request.getAttribute("bkdate");
	System.out.println(bkdate);
	List<BokdtVO> bokDtListByTime = (ArrayList<BokdtVO>)request.getAttribute("bokDtListByTime");
	for(BokdtVO bokdt : bokDtListByTime){
		System.out.println(bokdt.getTableno());
	}
	
	MemVO memvo = (MemVO) session.getAttribute("memVO");
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>選擇座位和人數</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />

	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="js/app.js"></script>

	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<%@ include file="/front-end/front-end-head.jsp"%>
	<style type="text/css" media="screen">
	
		.bok-container{
			background-image:url(<%=request.getContextPath()%>/front-end/bok/images/back_img2.jpg);
			background-size: cover;
			background-position:bottom ;
			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
		}
		
		#floor_plan{
			position: relative;
			float: left;
			margin-top:1.5%; 
			margin-left: 50px;
			width:1100px;
			height:830px;
			background-color: #F8F8F8;
			background-image:url(<%=request.getContextPath()%>/front-end/bok/seat_img/FloorPlan.jpg);
			background-size: 100% 100%;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			padding: 1px;
		}
		
		#rule-list{
			position: relative;
			float: left;
			margin-top:6.5%; 
			margin-left: 50px;
			width: 300px;
			height: 400px; 
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			padding: 1px;
		}
		#result{
			position: relative;
			float: left;
			margin-top:6.5%; 
			margin-left: 50px;
			width: 300px;
			height: 650px; 
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			padding: 1px;
		}
		li:hover {
			background: #eee;
			cursor: pointer;
		}	
			
		#logo{
			width: 70px;
			height: 45px;
		}
		
		label{
			width: 200px;
			height: 100px;
			text-align: center;
			font-weight:bold;
		}
		label:hover{
			cursor: pointer;
		}
		#bkprice{
			width: 60px;
		}
		.seat2{
			width: 100px;
			height: 100px;
		}
		.seat4{
			width: 100px;
			height: 100px;
		}
		.seat6{
			width: 105px;
			height: 160px;
		}
		.table{
			display: none;
		}
		#T0001{
			position:absolute;
			left:220px;
			top: 520px;
		}
		#T0002{
			position:absolute;
			left:870px;
			top: 120px;
		}
		#T0003{
			position:absolute;
			left:380px;
			top: 150px;
		}
		#T0004{
			position:absolute;
			left:560px;
			top: 210px;
		}
		#T0005{
			position:absolute;
			left:390px;
			top: 290px;
		}
		#T0006{
			position:absolute;
			left:220px;
			top: 650px;
		}
		#T0007{
			position:absolute;
			left:420px;
			top: 650px;
		}
		#T0008{
			position:absolute;
			left:870px;
			top: 265px;
		}
		#T0009{
			position:absolute;
			left:300px;
			top: 20px;
		}
		#T0010{
			position:absolute;
			left:560px;
			top: 360px;
		}
		#T0011{
			position:absolute;
			left:410px;
			top: 460px;
		}
		#T0012{
			position:absolute;
			left:240px;
			top: 290px;
		}
		#T0013{
			position:absolute;
			left:700px;
			top: 210px;
		}
		#T0014{
			position:absolute;
			left:700px;
			top: 420px;
		}
		#T0015{
			position:absolute;
			left:870px;
			top: 420px;
		}
		#newbooking{
			display: none;
			font: 350 20px/1.5 'Noto Sans TC', "Helvetica", "Arial","LiHei Pro","Microsoft JhengHei", Verdana, sans-serif;
		}
		.table ~ p{
			font: bold 14px/1.5 'Noto Sans TC', "Helvetica", "Arial","LiHei Pro","Microsoft JhengHei", Verdana, sans-serif;
		}
		#bkprice{
			position: relative;
			margin-top: 5px; 
			width: 70px;
		}
		#send{
			position: relative;
			float: right;
			margin-right: 15px;
		}
		#card_cvc{
			position: relative;
			margin-top: 5px; 
			width: 50px;
		}
		#card_date{
			position: relative;
			margin-top: 5px; 
			width: 70px;
		}
		
		.nav-link{
			font: bold 18px/1.5 'Noto Sans TC', "Helvetica", "Arial","LiHei Pro","Microsoft JhengHei", Verdana, sans-serif;
		}
		
		#member{
			font: bold 16px/1.5 'Noto Sans TC', "Helvetica", "Arial","LiHei Pro","Microsoft JhengHei", Verdana, sans-serif;
			margin-top: 8px;
		}
	</style>
</head>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/index.jsp">
			<img id="logo" src="${pageContext.request.contextPath}/images/logo.png" title="首頁">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/bok/booking_page_user.jsp">訂位首頁<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/bok/newBookingStep1.jsp">開始訂位</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/bok/listByMemNo.jsp">紀錄查詢</a>
				</li>
			</ul>
		</div>
		<div id="member">
<%-- 			<p>您好! <%=memvo.getmName()%></p> --%>
		</div>
	</nav>

	<div class="container-fluid bok-container">
		<form id="newbooking" METHOD="post" ACTION="<%=request.getContextPath()%>/BokServlet">
			<div id="rule-list">
				<div id="rules">
					<p><img src="<%=request.getContextPath()%>/front-end/bok/images/red-square.png">紅色代表已經被訂</p>
					<p><img src="<%=request.getContextPath()%>/front-end/bok/images/green-square.png">綠色代表可以選擇</p>
					<p><img src="<%=request.getContextPath()%>/front-end/bok/images/black-square.png">灰色代表不可選擇</p>
					<p><img src="<%=request.getContextPath()%>/front-end/bok/images/orange-square.png">橘色代表目前的選擇</p>
					<hr>
					<p><img src="<%=request.getContextPath()%>/front-end/bok/images/blockly-pink.png">注意:考慮本店營業成本，選擇桌位時，每張桌子最多只能留有一個空位。</p>
				</div>
			</div>
			<div id="floor_plan">
				<label id="T0001" for="0"><input class="table" id=0 type="checkbox" name="tableno" value="T0001">
					<img class="seat2" src=""><p>T0001(兩人桌)</p></label>
				<label id="T0002" for="1"><input class="table" id=1 type="checkbox" name="tableno" value="T0002">
					<img class="seat2" src=""><p>T0002(兩人桌)</p></label>
				<label id="T0003" for="2"><input class="table" id=2 type="checkbox" name="tableno" value="T0003">
					<img class="seat2" src=""><p>T0003(兩人桌)</p></label>
				<label id="T0004" for="3"><input class="table" id=3 type="checkbox" name="tableno" value="T0004">
					<img class="seat2" src=""><p>T0004(兩人桌)</p></label>
				<label id="T0005" for="4"><input class="table" id=4 type="checkbox" name="tableno" value="T0005">
					<img class="seat2" src=""><p>T0005(兩人桌)</p></label>
		
				<label id="T0006" for="5"><input class="table" id=5 type="checkbox" name="tableno" value="T0006">
					<img class="seat4" src=""><p>T0006(四人桌)</p></label>
				<label id="T0007" for="6"><input class="table" id=6 type="checkbox" name="tableno" value="T0007">
					<img class="seat4" src=""><p>T0007(四人桌)</p></label>
				<label id="T0008" for="7"><input class="table" id=7 type="checkbox" name="tableno" value="T0008">
					<img class="seat4" src=""><p>T0008(四人桌)</p></label>
				<label id="T0009" for="8"><input class="table" id=8 type="checkbox" name="tableno" value="T0009">
					<img class="seat4" src=""><p>T0009(四人桌)</p></label>
				<label id="T0010" for="9"><input class="table" id=9 type="checkbox" name="tableno" value="T0010">
					<img class="seat4" src=""><p>T0010(四人桌)</p></label>
			
				<label id="T0011" for="10"><input class="table" id=10 type="checkbox" name="tableno" value="T0011">
					<img class="seat6" src=""><p>T0011(六人桌)</p></label>
				<label id="T0012" for="11"><input class="table" id=11 type="checkbox" name="tableno" value="T0012">
					<img class="seat6" src=""><p>T0012(六人桌)</p></label>
				<label id="T0013" for="12"><input class="table" id=12 type="checkbox" name="tableno" value="T0013">
					<img class="seat6" src=""><p>T0013(六人桌)</p></label>
				<label id="T0014" for="13"><input class="table" id=13 type="checkbox" name="tableno" value="T0014">
					<img class="seat6" src=""><p>T0014(六人桌)</p></label>
				<label id="T0015" for="14"><input class="table" id=14 type="checkbox" name="tableno" value="T0015">
					<img class="seat6" src=""><p>T0015(六人桌)</p></label>
			</div>
			<div id="result">
				<br>
				選擇人數:
				<select id="peoples" name="numofpeoples" class="custom-select d-block w-50">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
				<hr>
				<div id="items">
					
				</div>
				<hr>
				<img src="<%=request.getContextPath()%>/front-end/bok/images/visa.png"><br>
				卡號 : <input type="text" id="card_id" value="6666 6666 8787 9487" readonly="readonly"><br>
				CVC : <input type="text" id="card_cvc" value="087" readonly="readonly"><br>
				到期日 : <input type="text" id="card_date" value="08/77" readonly="readonly"><br>
				訂金 : <input type="number" id="bkprice" name="bkprice" value="200" readonly="readonly">元
				<input type="hidden" name="action" value="insert">
				<input type="hidden" name="memno" value="<%=memvo.getMemno()%>">
				<input type="hidden" name="bkdate" value="${bkdate}">
				<input type="hidden" name="bkperiod" value="${bkperiod}">
				<input class="btn btn-success" id="send" type="button" value="送出" onclick="checking()">
			</div>
		</form>
	</div>
	<%@ include file="/front-end/front-end-footer-type2.jsp"%>
	<script type="text/javascript">
		allGreen();
		
		var capacity = [2,2,2,2,2,4,4,4,4,4,6,6,6,6,6];
		var people = $("#peoples").find(":selected").val();
		console.log("需要"+people+"人");
		var total = 0;
		var xxxVal = new Array();
		checkable(people, total);
		booked();

		function checkable(pe,to){
			if((pe-to)<=2 && (pe-to)>0){
				for(var i=5 ; i<=14 ; i++){
					$("input[id='"+i+"']").attr("disabled",true);
					//$("input[id='"+i+"']+p").css("background-color","gray");	
				}
				for(var i=5 ; i<10 ; i++){
					$("input[id='"+i+"']").attr("disabled",true);
					$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat4_gray.png");
					$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat4_orange.png");
				}
				for(var i=10 ; i<15 ; i++){
					$("input[id='"+i+"']").attr("disabled",true);
					$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_gray.png");
					$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_orange.png");
				}
			}else if((pe-to)<=0){
				for(var i=0 ; i<=14 ; i++){
					$("input[id='"+i+"']").attr("disabled",true);	
					//$("input[id='"+i+"']+p").css("background-color","gray");	
				}
				for(var i=0 ; i<5 ; i++){
					$("input[id='"+i+"']").attr("disabled",true);
					$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat2_gray.png");
					$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat2_orange.png");
				}
				for(var i=5 ; i<10 ; i++){
					$("input[id='"+i+"']").attr("disabled",true);
					$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat4_gray.png");
					$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat4_orange.png");
				}
				for(var i=10 ; i<15 ; i++){
					$("input[id='"+i+"']").attr("disabled",true);
					$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_gray.png");
					$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_orange.png");
				}
			}else if((pe-to)>=3 && (pe-to)<=4){
				for(var i=10 ; i<=14 ; i++){
					$("input[id='"+i+"']").attr("disabled",true);	
					//$("input[id='"+i+"']+p").css("background-color","gray");
					$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_gray.png");	
					$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_orange.png");
				}
			}		
			$('input[name="tableno"]:checkbox:checked').attr("disabled",false);
			//$('input[name="tableno"]:checkbox:checked+p').attr('style','background-color:orange;color:black');

		}


		function booked(){
			var t = { T0001:"0",T0002:"1",T0003:"2",T0004:"3",T0005:"4",
					  T0006:"5",T0007:"6",T0008:"7",T0009:"8",T0010:"9",
					  T0011:"10",T0012:"11",T0013:"12",T0014:"13",T0015:"14" };
			<%for( BokdtVO bokdt : bokDtListByTime ){%>
				var n = "<%=bokdt.getTableno()%>"
				console.log(n+"已被預訂");
				$("input[id='"+t[n]+"']").attr("disabled",true);
				$("input[id='"+t[n]+"']+p").attr('style','background-color:red;color:black');
				if(t[n]<5){
					$("input[id='"+t[n]+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat2_red.png");
				}else if (t[n]>4 && t[n]<10) {
					$("input[id='"+t[n]+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat4_red.png");
				}else if (t[n]>9){
					$("input[id='"+t[n]+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_red.png");
				}
				
			<%}%>
		}		

		//$('input[name="tableno"]:checkbox:checked').attr("disabled",false);
				
		$("#peoples").change(function(){
			people = $("#peoples").find(":selected").val();
			console.log("需要"+people+"人");
			$(".table").prop("checked",false); //把選擇清空
			total=0;
			$(".table").attr("disabled",false); 
			allGreen();
			//$('input[name="tableno"]:checkbox:not(:checked)+p').attr('style','background-color:lightgreen;color:black');
			checkable(people,total);
			booked();	
			$('input[name="bkprice"]').val(people*200);	
		});


		$(".table").click(function(){	
			Orange_and_Green();
			
			xxxVal = [];	
			$('input[name="tableno"]:checkbox:checked').each(function(i) {
				xxxVal[i] = this.id;
			});

			total = 0 ;
			for(var i=0 ; i<xxxVal.length ; i++){
				total = total + capacity[xxxVal[i]];
			}

			console.log("已選"+total+"個位置");

			if(people<=total){	
				checkable(people,total);
				booked();
				$('input[name="tableno"]:checkbox:not(:checked)').attr("disabled",true);
			}else{
				$(".table").attr("disabled",false);
				checkable(people,total);
				booked();
				$('input[name="tableno"]:checkbox:checked').attr("disabled",false);
			}
		});
		
		function checking(){
			if(people<=total){
				newbooking.submit();
			}else{
				alert("請選擇座位");
			}
		}
		
		function allGreen(){
			for(var i=0 ; i<5 ; i++)
				$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat2_green.png");
			for(var i=5 ; i<10 ; i++)
				$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat4_green.png");
			for(var i=10 ; i<15 ; i++)
				$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_green.png");
		}
		
		function Orange_and_Green(){
			var text = "" ;
			$("#items").empty();
			for(var i=0 ; i<5 ; i++){
				$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat2_green.png");
				$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat2_orange.png");
				text = "<p>" + $("input[id='"+i+"']:checkbox:checked ~ p").text() + "</p>";
				$("#items").append(text);
			}
			for(var i=5 ; i<10 ; i++){
				$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat4_green.png");
				$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat4_orange.png");
				text = "<p>" + $("input[id='"+i+"']:checkbox:checked ~ p").text() + "</p>";
				$("#items").append(text);
			}
			for(var i=10 ; i<15 ; i++){
				$("input[id='"+i+"']+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_green.png");
				$("input[id='"+i+"']:checkbox:checked+img").attr("src","<%=request.getContextPath()%>/front-end/bok/seat_img/seat6_orange.png");
				text = "<p>" + $("input[id='"+i+"']:checkbox:checked ~ p").text() + "</p>";
				$("#items").append(text);
			}
		}
		$(document).ready(function (){
			$("#newbooking").fadeIn("slow");
		});
	</script>
</body>
</html>