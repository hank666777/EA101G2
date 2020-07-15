<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>活動首頁</title>
<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,600);
@import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
.snip1344 {
  font-family: 'Open Sans', Arial, sans-serif;
  position: relative;
  overflow: hidden;
  margin: 10px;
  min-width: 230px;
  max-width: 315px;
  width: 100%;
  color: #ffffff;
  text-align: center;
  line-height: 1.4em;
  background-color: #141414;
}
.snip1344 * {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-transition: all 0.25s ease;
  transition: all 0.25s ease;
}
.snip1344 .background {
  width: 100%;
  vertical-align: top;
  opacity: 0.2;
  -webkit-filter: grayscale(100%) blur(10px);
  filter: grayscale(100%) blur(10px);
  -webkit-transition: all 2s ease;
  transition: all 2s ease;
}
.snip1344 figcaption {
  width: 100%;
  padding: 15px 25px;
  position: absolute;
  left: 0;
  top: 50%;
  font-color:#FDFFFF;
}
.snip1344 .profile {
  border-radius: 50%;
  position: absolute;
  bottom: 50%;
  left: 50%;
  max-width: 100px;
  opacity: 1;
  box-shadow: 3px 3px 20px rgba(0, 0, 0, 0.5);
  border: 2px solid rgba(255, 255, 255, 0.5);
  -webkit-transform: translate(-50%, 0%);
  transform: translate(-50%, 0%);
}
.snip1344 h3 {
  margin: 0 0 5px;
  font-weight: 400;
}
.snip1344 h3 span {
  display: block;
  font-size: 0.6em;
  color: #f39c12;
  opacity: 0.75;
}
.snip1344 i {
  padding: 10px 5px;
  display: inline-block;
  font-size: 32px;
  color: #ffffff;
  text-align: center;
  opacity: 0.65;
}
.snip1344 a {
  text-decoration: none;

}
.snip1344 i:hover {
  opacity: 1;
  -webkit-transition: all 0.35s ease;
  transition: all 0.35s ease;
}
.snip1344:hover .background,
.snip1344.hover .background {
  -webkit-transform: scale(1.3);
  transform: scale(1.3);
}

</style>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>

	<div class="container">
		<div class="row text-center">
			<div class="col-6">

				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/activity/activityshop.do">
					<a href="activityshop.jsp"><figure class="snip1344" style="float:left" >
							<img
								src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/profile-sample1.jpg"
								alt="profile-sample1" class="background" />
							<img
								src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/profile-sample1.jpg"
								alt="profile-sample1" class="profile" /></a>
					<figcaption>
						<h3>活動報名</h3>
					</figcaption>
					</figure>
			</div>
			<div class="col-6">
					<a href="activityfeelings.jsp"><figure class="snip1344 hover">
							<img
								src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/profile-sample7.jpg"
								alt="profile-sample7" class="background" />
							<img
								src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/profile-sample7.jpg"
								alt="profile-sample7" class="profile" /></a>
					<figcaption>
						<h3>活動心得填寫</h3>
					</figcaption>
					</figure>
<!-- 					<h4><b>活動心得記錄查詢：</b><a href="feelings.jsp">點擊查詢</a></h4> -->
				</FORM>
			</div>
			
		</div>
	</div>
<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
</html>