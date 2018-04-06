<%@page import="com.evernote.edam.notestore.NoteList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="dao.MultiDao"%>
<%@page import="
dao.NoteDao,
dao.TagDao,
dao.Note2TagDao,
java.util.*,
utils.Myutils,
javabean.Note,
javabean.Tag
"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>	
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<title>hwt</title>
<link rel="stylesheet" href="<%=Myutils.getProperty2("aliLink")%>">
<link type="text/css" rel="stylesheet" href="../tctip/css/myRewards.css">


<%@include file="../includes/header.txt"%>
<%@include file="../includes/googleUA.txt"%>


<style type="text/css">
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
	background-color: <%=Myutils.getProperty2("siteColor")%>;
	color: #fff;
}
/*输入框选中边框颜色*/
.form-control:focus {
	border-color: <%=Myutils.getProperty2("siteColor")%>;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), <%=Myutils.getProperty2("siteColor")%>;
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), <%=Myutils.getProperty2("siteColor")%>;
}
/*页脚 start*/
#footer {
	background-color: <%=Myutils.getProperty2("siteColor")%>;
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
	color: <%=Myutils.getProperty2("secondColor")%>;
}

.alist a:hover {
	text-decoration: none;
	color: #fff;
}

.alist a:focus {
	text-decoration: none;
}
/*页脚 end*/

/*博客列表 start*/
.blog-border {
	border: 1px #ccc solid;
	border-radius: 6px;
	margin-bottom: 10px;
}

.blog-item {
	clear: both;
	border-bottom: 1px solid #ddd;
	padding: 1.5em 1em 0.8em 1.5em;
	overflow: hidden;
}

.blog-title {
	margin-bottom: 0.5em;
	font-size: 115%;
	font-weight: bold;
}

.blog_descipt {
	color: #777;
	font-size: small;
}

.blog-tags {
	font-size: 70%;
	color: #75878a;
	margin-top: 0.5em;
	display: inline-block;
	border-top: 1px #ddd solid;
}

.blog-tags li {
	float: left;
	padding-top: 0.5em;
	margin-right: 1em;
}
/*博客列表 end*/
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
	<!--回到顶端-->
	<%@include file="../../static/includes/goTop.txt"%>
	<%@ include file="../includes/navbar.txt"%>

	<div class="jumbotron">
		<div class="container">
			<div class="header-banner">
				<div class="container">
					<div class="text-center">
						<h1>我的博客</h1>
						<p>精益求精，发表高质量的技术博客</p>
						<p>生命不息,折腾不止</p>
					</div>

				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="col-md-9 col-xs-12">
			<div class="blog-border side-margin">
				<%
					List<Note> notesList = NoteDao.getAllNotes();
					if (notesList != null) {
						for (int i = 0; i < notesList.size(); i++) {
							String title = notesList.get(i).getTitle();	
							String guid = notesList.get(i).getGuid();					
							//String note = String.format("<a href=\"%s\">%s</a></br>", url, title);
							Timestamp timestamp = notesList.get(i).getTime();
							String time = String.format("%s年%s月%s日",timestamp.getYear()+1900+"",timestamp.getMonth()+1+"",timestamp.getDate()+"");
							
							out.println("<div class=\"blog-item\">");
							out.println("<div class=\"blog-title\">");
							out.println("<a href=\"blogs/" + guid + "\" target=\"_blank\">" + title + "</a>");//title
							out.println("</div>");
							out.println("<div class=\"blog-tags\">");
							out.println("<ul>");
							out.println("<li><span class=\"glyphicon glyphicon-time\"></span>");
							out.println("<span>"+time+"</span></li>"); //time
							
							//获取对应笔记的标签名和标签guid
							List<Tag> tList=MultiDao.getAllTagByNote(guid);
							if(tList!=null && !tList.isEmpty()){
								for(int j=0;j<tList.size();j++){
									String tagGuid = tList.get(j).getGuid();
									String name = tList.get(j).getName();
									
									out.println("<li>");
									out.println(String.format("<form id=\"%s\" action=\"%s\">","f"+i+"f"+j,"static/pages/tagList.jsp"));
									String input=String.format("<input name=\"tagGuid\" type=\"hidden\" value=\"%s\"/>",tagGuid);
									String tagShow =String.format("<span class=\"glyphicon glyphicon-tag\"style=\"color:#7c827f\"></span><a  href=\"javascript:document:%s.submit();\">&nbsp%s</a>","f"+i+"f"+j,name);					
									out.println(input);
									out.println(tagShow);
									out.println("</form>");
									out.println("</li>");				
																	
								}
							}

							out.println("</ul>");
							out.println("</div></div>");

						}
					}
				%>
			</div>
		</div>
		
		<div class="col-md-3 col-xs-12  ">
			<div class="side-list side-margin  blog-border">
				<h4 style="color:#7c827f">
					<span class="glyphicon glyphicon-book" ></span> 博客标签
				</h4>
				<ul>
				<%
					List<Tag> tagList=TagDao.getAllTag();
					if (tagList != null&& !tagList.isEmpty()) {
						for (int i = 0; i < tagList.size(); i++) {
							String guid = tagList.get(i).getGuid();	
							String name = tagList.get(i).getName();								
							out.println(String.format("<form id=\"%s\" action=\"%s\">","f"+i,"static/pages/tagList.jsp"));
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
	</div>


	<%@include file="../includes/footer.txt"%>
<!-- 打赏插件 -->
	<%@include file="../includes/tctip.txt" %>
	<script src="../tctip/js/tctip.min.js"></script>
</body>


</html>