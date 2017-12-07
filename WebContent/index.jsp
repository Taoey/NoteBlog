<%@page import="
dao.NoteDao,
dao.TagDao,
dao.Note2TagDao,
java.util.*,
javabean.Note,
javabean.Tag

"%>
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
							<%
								List<Tag> tagList=TagDao.getTopTags();
								if (tagList != null&& !tagList.isEmpty()) {
									for (int i = 0; i < tagList.size(); i++) {
										String guid = tagList.get(i).getGuid();	
										String name = tagList.get(i).getName();						
										out.println(String.format("<form id=\"%s\" action=\"%s\">","f"+i,"/static/pages/tagList.jsp"));
										String input=String.format("<input name=\"tagGuid\" type=\"hidden\" value=\"%s\"/>",guid);
										String tagShow =String.format("<li><a  href=\"javascript:document:%s.submit();\">&nbsp%s<span>(%s)</span></a></li>","f"+i,name,Note2TagDao.getNoteCount(guid));					
										out.println(input);
										out.println(tagShow);
										out.println("</form>");							
									}
								}
								
							%>
						</ul>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="home-block">
						<h4 class='home-block-title'>随机推荐</h4>
						<ul class="list">
							<%
								List<Note> nList=NoteDao.getRandNotes();
								if(nList!=null && !nList.isEmpty()){
									for(int i=0;i<nList.size();i++){
										String noteGuid = nList.get(i).getGuid();
										String noteTitle = nList.get(i).getTitle();
										out.println("<li><a href=\"/../blogs/" + noteGuid + "\" target=\"_blank\">" + noteTitle + "</a></li>");//title
									}
								}
							%>							
						</ul>
					</div>
				</div>

			</div>
		</div>
	</div>

	<%@include file="static/includes/footer.txt" %>
</body>
</html>