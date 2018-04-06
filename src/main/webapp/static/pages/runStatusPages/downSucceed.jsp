<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>关闭成功</title>
		<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<meta name="viewport" content="width=device-width,
		                                 initial-scale=1.0,
		                                 maximum-scale=1.0,
		                                 user-scalable=no">
		
		<style type="text/css">
		body {margin: 0px; padding:0px; font-family:"微软雅黑", Arial, "Trebuchet MS", Verdana, Georgia,Baskerville,Palatino,Times; font-size:16px;}
		div{margin-left:auto; margin-right:auto;}
		a {text-decoration: none; color: #17d684;}
		a:hover {color: #17d684;}
		h1,h2,h3,h4 {
		/*	display:block;*/
			margin:0;
			font-weight:normal;
			font-family: "微软雅黑", Arial, "Trebuchet MS", Helvetica, Verdana ;
		}
		h1{font-size:44px; color: #17d684; padding:20px 0px 10px 0px;}
		h2{color: #17d684; font-size:16px; padding:10px 0px 40px 0px;}
		
		#page{ padding:20px 20px 40px 20px; margin-top:80px;}
		.button{width:180px; height:28px; margin-left:0px; margin-top:10px; background: #17d684; border-bottom:4px solid #18d676; text-align:center;}
		.button a{width:180px; height:28px; display:block; font-size:14px; color:#fff; }
		.button a:hover{ background: #04a57e;}
		</style>
		
		<style id="style-1-cropbar-clipper">/* Copyright 2014 Evernote Corporation. All rights reserved. */
		.en-markup-crop-options {
		    top: 18px !important;
		    left: 50% !important;
		    margin-left: -100px !important;
		    width: 200px !important;
		    border: 2px rgba(255,255,255,.38) solid !important;
		    border-radius: 4px !important;
		}
		
		.en-markup-crop-options div div:first-of-type {
		    margin-left: 0px !important;
		}
		</style>
	</head>
	<body>
		<div class="container">
		        <div class="col-md-12 col-sm-12 ">
		            <div  id="page" style="border-style:dashed;border-color:#e4e4e4;line-height:30px;">
						<div class="row">
							<div class="col-md-4 col-md-offset-5">
								<h1>关闭成功</h1>
								<div class="button">
									<a href=""  target="_blank">进入首页</a>
								</div>
							</div>
						</div>
		   			</div>
				</div>
		</div>	
	</body>
</html>