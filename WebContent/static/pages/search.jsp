<%@page import="
dao.NoteDao,
java.util.*,
javabean.Note,
dao.MultiDao,
java.util.*,
javabean.Tag,
dao.TagDao

"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<title>搜索结果</title>

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
	background-color: #17d684;
	color: #fff;
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
	               request.setCharacterEncoding("utf-8");
			       String wd = request.getParameter("wd");			       
				
					List<Note> notesList = NoteDao.getNotes(wd);
					if (notesList != null && notesList.size()!=0) {
						for (int i = 0; i < notesList.size(); i++) {
							String title = notesList.get(i).getTitle();						
							String guid = notesList.get(i).getGuid();
							//String note = String.format("<a href=\"%s\">%s</a></br>", url, title);
							out.println("<div class=\"blog-item\">");
							out.println("<div class=\"blog-title\">");
							out.println("<a href=\"/../blogs/" + guid + "\" target=\"_blank\">" + title + "</a>");//title
							out.println("</div>");
							out.println("<div class=\"blog-tags\">");
							out.println("<ul>");
							out.println("<li><span class=\"glyphicon glyphicon-time\"></span>");
							out.println("<span>#time#</span></li>"); //time
							
							
							//获取对应笔记的标签名和标签guid
							List<Tag> tList=MultiDao.getAllTagByNote(guid);
							if(tList!=null && !tList.isEmpty()){
								for(int j=0;j<tList.size();j++){
									String tagGuid2 = tList.get(j).getGuid();
									String name = tList.get(j).getName();
									
									out.println("<li>");
									out.println(String.format("<form id=\"%s\" action=\"%s\">","f"+i+"f"+j,"/static/pages/tagList.jsp"));
									String input=String.format("<input name=\"tagGuid\" type=\"hidden\" value=\"%s\"/>",tagGuid2);
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

				<!--             <div class="blog-item">
                <div class="blog-title">
                    <a href="" target="_blank">######</a>
                </div>
                <div class="blog_descipt">
               		 博客描述 ###
                </div>
                <div class="blog-tags">
                    <ul>
                        <li>
                            <span class="glyphicon glyphicon-time"></span>
                            <span>#time#</span>
                        </li>
                        <li>
                            <span class="glyphicon glyphicon-tag"></span>
                            <a href="###">###</a>
                        </li>



                    </ul>
                </div>
            </div> -->

			</div>
		</div>
		<div class="col-md-3 col-xs-12  ">
			<div class="side-list side-margin  blog-border">
				<h4>
					<span class="glyphicon glyphicon-book"></span> 博客标签
				</h4>
				<ul>

					<li><a href="/blog/24" target="_blank">利用Eval函数，简单实现回调机制</a></li>

					<li><a href="/blog/23" target="_blank">Access建表规范总结(6)：最小信息</a>
					</li>

					<li><a href="/blog/44" target="_blank">以图搜图(二)：Python实现pHash算法</a>
					</li>

					<li><a href="/blog/81" target="_blank">Django处理同名url参数</a></li>

					<li><a href="/blog/116" target="_blank">Excel公式新手学习推荐</a></li>

					<li><a href="/blog/170" target="_blank">Python实现快速排序算法</a></li>

					<li><a href="/blog/100" target="_blank">我的网站搭建(第37天)
							上传图片加水印</a></li>

					<li><a href="/blog/101" target="_blank">C#的序列化和反序列化</a></li>

					<li><a href="/blog/168" target="_blank">我的网站搭建(第54天)
							Celery异步发送邮件</a></li>

					<li><a href="/blog/134" target="_blank">我的网站搭建(第46天) 在线头像</a>
					</li>

				</ul>
			</div>
			<div class="side-list  side-margin blog-border ">
				<h4>
					<span class="glyphicon glyphicon-book"></span> 博客标签
				</h4>
				<ul>

					<li><a href="/blog/24" target="_blank">利用Eval函数，简单实现回调机制</a></li>

					<li><a href="/blog/23" target="_blank">Access建表规范总结(6)：最小信息</a>
					</li>

					<li><a href="/blog/44" target="_blank">以图搜图(二)：Python实现pHash算法</a>
					</li>

					<li><a href="/blog/81" target="_blank">Django处理同名url参数</a></li>

					<li><a href="/blog/116" target="_blank">Excel公式新手学习推荐</a></li>

					<li><a href="/blog/170" target="_blank">Python实现快速排序算法</a></li>

					<li><a href="/blog/100" target="_blank">我的网站搭建(第37天)
							上传图片加水印</a></li>

					<li><a href="/blog/101" target="_blank">C#的序列化和反序列化</a></li>

					<li><a href="/blog/168" target="_blank">我的网站搭建(第54天)
							Celery异步发送邮件</a></li>

					<li><a href="/blog/134" target="_blank">我的网站搭建(第46天) 在线头像</a>
					</li>

				</ul>
			</div>
		</div>
	</div>


	<%@include file="../includes/footer.txt"%>

</body>


</html>