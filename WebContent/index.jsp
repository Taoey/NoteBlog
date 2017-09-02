<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>hwt</title>

<%@ include file="static/includes/header.txt" %>
<%@include file="static/includes/googleUA.txt"%>

<style>
body {
	padding-top: 50px;
}

ul, ol {
	list-style-type: none;
}

ul, ol, li {
	padding: 0;
	margin: 0;
}
/*巨幕*/
.jumbotron {
	background-color: #17d684;
	color: #fff;
}

/*首页大模块*/
.content-item {
	margin-bottom: 4em;
}

.content-item-title {
	color: #222;
}

.content-item-title .glyphicon {
	float: left;
	margin-right: 8px;
}

.content-more {
	font-size: 0.6em;
	margin-left: 1em;
}
/*首页小模块*/
.home-block { /*border: 1px #ccc solid;*/
	border-radius: 6px;
	padding: 16px 1em 5px 1.5em;
	margin-bottom: 1em;
}

.home-block-title {
	border-bottom: 1px #ccc solid;
	padding-bottom: 10px;
	font-weight: bold;
}

.home-block .list a {
	color: #333;
}

.home-block .list li {
	margin-bottom: 1px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
/*输入框选中边框颜色*/
.form-control:focus {
	border-color: #17d684;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), #17d684;
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), #17d684;
}
/*页脚 start*/
#footer {
	background-color: #17d684;
	margin-top: 35px;
	color: #fff;
}

.copyright {
	padding: 10px;
}

.gly-i {
	font-size: large;
	padding: 0 3px 0 3px;
}

.alist a {
	color: #b7ffbe;
}

.alist a:hover {
	text-decoration: none;
	color: #fff;
}

.alist a:focus {
	text-decoration: none;
}
/*页脚 end*/
</style>
</head>
<body>

	<!--引入博客头-->
	<%@include file="static/includes/navbar.txt"%>


	<div class="jumbotron">
		<div class="container">
			<div class="header-banner">
				<div class="container">
					<div class="text-center">
						<h1>HWT Blog</h1>
						<p>精益求精，发表高质量的技术博客</p>
						<p>生命不息,折腾不止</p>
					</div>

				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="content-item">
			<h3 class="content-item-title">
				<span class="glyphicon glyphicon-pencil"></span> <span>博客</span> <span
					class="content-more"> 共187篇博客 <a
					href="/static/pages/blogs.jsp">more&gt;</a>
				</span>
			</h3>

			<div class="row">
				<div class="col-sm-4">
					<div class="home-block">
						<h4 class='home-block-title'>博客分类</h4>
						<ul class="list">

							<li><a href="/blog/?tag=1" target=_blank>我的网站搭建</a> <span>(56)</span>
							</li>

							<li><a href="/blog/?tag=6" target=_blank>Python</a> <span>(31)</span>
							</li>

							<li><a href="/blog/?tag=8" target=_blank>Django</a> <span>(24)</span>
							</li>

							<li><a href="/blog/?tag=2" target=_blank>Excel</a> <span>(18)</span>
							</li>

							<li><a href="/blog/?tag=10" target=_blank>VSTO</a> <span>(15)</span>
							</li>

							<li><a href="/blog/?tag=3" target=_blank>数据库</a> <span>(11)</span>
							</li>

							<li><a href="/blog/?tag=12" target=_blank>机器学习</a> <span>(11)</span>
							</li>

							<li><a href="/blog/?tag=4" target=_blank>vb/vba</a> <span>(8)</span>
							</li>

							<li><a href="/blog/?tag=7" target=_blank>树莓派&amp;硬件</a> <span>(5)</span>
							</li>

							<li><a href="/blog/?tag=9" target=_blank>Linux</a> <span>(5)</span>
							</li>

							<li><a href="/blog/?tag=11" target=_blank>Web前端</a> <span>(5)</span>
							</li>

							<li><a href="/blog/?tag=5" target=_blank>C#</a> <span>(3)</span>
							</li>

						</ul>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="home-block">
						<h4 class='home-block-title'>最新发表</h4>
						<ul class="list">

							<li><a href="/blog/188" target=_blank>初步使用tastypie的体悟（三）</a></li>

							<li><a href="/blog/187" target=_blank>充分利用Excel的布尔值</a></li>

							<li><a href="/blog/185" target=_blank>初步使用tastypie的体悟（二）</a></li>

							<li><a href="/blog/184" target=_blank>初步使用tastypie的体悟（一）</a></li>

							<li><a href="/blog/183" target=_blank>Excel数组公式入门</a></li>

							<li><a href="/blog/182" target=_blank>Django测试驱动开发</a></li>

							<li><a href="/blog/181" target=_blank>scp远程传输文件命令</a></li>

							<li><a href="/blog/180" target=_blank>我的网站搭建(第56天)
									用户登录注册信息加密</a></li>

							<li><a href="/blog/179" target=_blank>Django模板设置全局变量(默认变量)</a></li>

							<li><a href="/blog/178" target=_blank>Django拆分app为多个小app</a></li>

							<li><a href="/blog/177" target=_blank>ui-router使用ocLazyLoad加载js</a></li>

							<li><a href="/blog/176" target=_blank>解决python安装pycurl的问题</a></li>

							<li><a href="/blog/175" target=_blank>Excel常用的字符串公式</a></li>

							<li><a href="/blog/174" target=_blank>我的网站搭建(第55天)
									站内消息通知</a></li>

						</ul>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="home-block">
						<h4 class='home-block-title'>随机推荐</h4>
						<ul class="list">

							<li><a href="/blog/91" target=_blank>UEditor使用prettify.js处理代码高亮</a></li>

							<li><a href="/blog/119" target=_blank>我的网站搭建(第42天)
									添加公告和打赏</a></li>

							<li><a href="/blog/164" target=_blank>Django
									Celery定时任务和时间设置</a></li>

							<li><a href="/blog/97" target=_blank>VSTO详细打包过程</a></li>

							<li><a href="/blog/130" target=_blank>VSTO窗体在Word内弹窗置顶</a></li>

							<li><a href="/blog/1" target=_blank>我的网站搭建(第1天) Hello
									Django</a></li>

							<li><a href="/blog/66" target=_blank>Excel优雅的Max函数和Min函数</a></li>

							<li><a href="/blog/81" target=_blank>Django处理同名url参数</a></li>

							<li><a href="/blog/118" target=_blank>VSTO中Excel添加事件</a></li>

							<li><a href="/blog/20" target=_blank>Access建表规范总结(3)：主子表拆分</a></li>

							<li><a href="/blog/10" target=_blank>Access
									vba导出数据到Excel方法总结</a></li>

							<li><a href="/blog/137" target=_blank>机器学习06：朴素贝叶斯理论知识</a></li>

							<li><a href="/blog/175" target=_blank>Excel常用的字符串公式</a></li>

							<li><a href="/blog/29" target=_blank>Python字符串处理方法总结</a></li>

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="static/includes/footer.txt" %>
</body>
</html>