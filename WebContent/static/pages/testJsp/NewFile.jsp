<%@page
	import="utils.Myutils,dao.MultiDao,dao.NoteDao,dao.TagDao,dao.Note2TagDao,java.util.*,javabean.Note,javabean.Tag"%><%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>1.6 标准C++中的string类的用法总结</title>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport"
	content="width=device-width,                                     initial-scale=1.0,                                     maximum-scale=1.0,                                     user-scalable=no">
<%@include file="../../static/includes/googleUA.txt"%>
<link type="text/css" rel="stylesheet"
	href="../../static/tctip/css/myRewards.css">
<style>
body {
	padding-top: 70px;
}

.jumbotron {
	background-color: #24D666;
	color: #fff;
}

ul, ol {
	list-style-type: none;
}

ul, ol, li {
	padding: 0;
	margin: 0;
} /*标签页*/
.blog-border {
	border: 1px #ccc solid;
	border-radius: 6px;
	margin-bottom: 10px;
}

.side-margin {
	margin: 0 0 10px 0;
}

.side-list h4 {
	padding-top: 0.8em;
	padding-bottom: 0.5em;
	border-bottom: 1px #ccc solid;
	padding-left: 8px;
}

.side-list  li {
	margin: 8px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container"></div><%@include
		file="../../static/includes/navbar.txt"%><div
		class="container">
		<div class="col-md-9 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="blog_header">
						<h3>1.6 标准C++中的string类的用法总结</h3>
						<span class="clearfix"></span>
						<ul>
							<li>分类标签： <%
								//获取对应笔记的标签名和标签guid							List<Tag> tList=MultiDao.getAllTagByNote("283cfa0e-130f-43a1-8444-f0d3ae15aefb");							if(tList!=null && !tList.isEmpty()){								for(int j=0;j<tList.size();j++){									String tagGuid = tList.get(j).getGuid();									String name = tList.get(j).getName();																		String tagShow =String.format("<a  href=\"../../static/pages/tageList.jsp?tagGuid=%s\">%s</a>",tagGuid,name);														out.println(tagShow);																																	}							}
							%>
							</li>
						</ul>
					</div>
				</div>
				<div class="panel-body">
					<div>
						<%@ include file="content.html"%>
					</div>
				</div>
			</div>
			<div>
				<!--PC和WAP自适应版-->
				<div id="SOHUCS" sid="283cfa0e-130f-43a1-8444-f0d3ae15aefb"></div>
				<script type="text/javascript"> (function(){ var appid = 'cytgcTKRt'; var conf = 'prod_aab59059c05e3a401ff2c5d67032bc19'; var width = window.innerWidth || document.documentElement.clientWidth; if (width < 960) { window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>'); } else { var loadJs=function(d,a){var c=document.getElementsByTagName("head")[0]||document.head||document.documentElement;var b=document.createElement("script");b.setAttribute("type","text/javascript");b.setAttribute("charset","UTF-8");b.setAttribute("src",d);if(typeof a==="function"){if(window.attachEvent){b.onreadystatechange=function(){var e=b.readyState;if(e==="loaded"||e==="complete"){b.onreadystatechange=null;a()}}}else{b.onload=a}}c.appendChild(b)};loadJs("https://changyan.sohu.com/upload/changyan.js",function(){window.changyan.api.config({appid:appid,conf:conf})}); } })(); </script>
			</div>
		</div>
		<div class="col-md-3 col-xs-12  ">
			<div class="side-list side-margin  blog-border">
				<h4>
					<span class="glyphicon glyphicon-book"></span> 推荐博文
				</h4>
				<ul>
					<%
						List<Note> nList = NoteDao.getRandNotes();
						if (nList != null && !nList.isEmpty()) {
							for (int i = 0; i < nList.size(); i++) {
								String noteGuid = nList.get(i).getGuid();
								String noteTitle = nList.get(i).getTitle();
								out.println(
										"<li><a href=\"/../blogs/" + noteGuid + "\" target=\"_blank\">" + noteTitle + "</a></li>");
							}
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	<script>    window.tctipConfig = {        headText: "<%=Myutils.getProperty2("headText")%>",        siderText: "<%=Myutils.getProperty2("siderText")%>",        siderTextTop: "-80px",        siderBgcolor: "#323d45",        siderTop:"120px",        buttomText:"了解更多",        buttomLink:"<%=Myutils.getProperty2("buttomLink")%>",        list:{            notice: {icon: "../../static/tctip/img/icon/tip.png", name:"公告栏", className:"myR-on", text: '<%=Myutils.getProperty2("notice")%>
		'
				},
				alipay : {
					icon : "../../static/tctip/img/icon/alipay.png",
					name : "支付宝",
					desc : "支付宝打赏",
					qrimg : "../../static/tctip/img/qr/alipay.png"
				},
				weixin : {
					icon : "../../static/tctip/img/icon/weixin.png",
					name : "微信",
					desc : "微信打赏",
					qrimg : "../../static/tctip/img/qr/weixin.png"
				}
			}
		};
	</script>
	<script src="../../static/tctip/js/tctip.min.js"></script>
</body>
</html>