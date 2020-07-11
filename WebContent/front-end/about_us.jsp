<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<html>
<head>

<title>MISS M about us</title>
<%@ include file="/front-end/front-end-head.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/about_us.css" type="text/css" />

<style>
.header-bottom{
	margin:0 auto;
}
.container{
	margin:5 auto;
	padding: auto 0;
}

</style>
</head>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>
	
	<div class="container">
		<div class="wrapper">
			<div class="main">
				<div class="title"><h2>關於</h2></div>

				<div class="row">
					<div class="col-sm-1"><img src="<%=request.getContextPath()%>/images/front-end/messageboard/logo.png" style="width: 80px"></div>
					<div class="col-sm-7">
						<h4>Miss M</h4>
						<p>一間傳遞幸福的店</p>
						<img src="<%=request.getContextPath()%>/images/front-end/messageboard/facebook_icon.png" alt="facebookicon" style="width: 30px">
						<img src="<%=request.getContextPath()%>/images/front-end/messageboard/instagram_icon.png" alt="instagramicon" style="width: 30px">
						<img src="<%=request.getContextPath()%>/images/front-end/messageboard/line_icon.png" alt="lineicon" style="width: 30px">
					</div>
					<div class="col-sm-4" id="contact_info">
						<div>營業時間：00：00~24：00 全年無休</div>
						<div>連絡電話：03-4567890</div>
						<div>地址：桃園市中壢區中央路300號</div>
						<div>聯繫方式：Miss_M@miss.com.tw</div>

					</div>
				</div>

				<div class="row" id="contentblock"> 
					<div class="col-sm-5" id="contenttext">
						<p>【製造快樂‧分享快樂】</p>
						<p>• 「製造快樂，分享快樂」是PINEDE在服務顧客及提供甜點時秉持著的目標。</p>
						<p>• 快樂，是我們的企業宗旨，製造快樂與提供快樂是我們的企業理念。</p>
						<p></p>

					</div>
					<div class="col-sm-7 context_pic" ><img class="img-fluid" src="<%=request.getContextPath()%>/images/front-end/messageboard/candy1.jpg" alt="candy1" ></div>
				</div>
				
				<div class="row" id="contentblock" > 
					<div class="col-sm-7 context_pic"><img class="img-fluid" src="<%=request.getContextPath()%>/images/front-end/messageboard/candy2.jpg" alt="candy2" ></div>
					<div class="col-sm-5" id="contenttext">
						<p>創作過程中夥伴們秉持著不斷學習與挑戰的精神，我們想透過用心製作的美味甜點，讓品嘗過的人透過味蕾，擁有彩色般的快樂心情，希望能夠帶給你簡單的味蕾感受，品嘗食材最真實的味道，透過用心研發與堅持品質傳遞幸福與快樂。</p>
					</div>
					
				</div>
					
				<div class="row" id="contentblock"> 
					<div class="col-sm-5" id="contenttext">
						<p>嚴選食材與堅持品質</p>
						<p>Miss M是由一群對食材挑選與製作十分堅持的夥伴組成，每當我們團隊在設計一樣產品時，都會先思考自己是否願意食用，或是給自己在乎的家人朋友食用，這是我們對於產品的堅持，以及同理顧客的感受，把每一位顧客看作我們自己的家人一樣照顧。</p>
					</div>
					<div class="col-sm-7 context_pic"><img class="img-fluid" src="<%=request.getContextPath()%>/images/front-end/messageboard/candy3.jpg" alt="candy3" ></div>
				</div>
				

			</div>
		</div>
	</div>



<script src="../js/jquery_3.5.1.min.js"></script>
<script src="../js/popper.min.js"></script>	
<script src="../js/bootstrap.min.js"></script>

<%@ include file="/front-end/front-end-footer.jsp"%>

</body>
</html>