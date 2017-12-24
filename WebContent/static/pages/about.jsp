<%@page import="utils.Myutils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=Myutils.getProperty2("aliLink")%>">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">

    <style>
        body{
            padding-top: 50px;
        }        
        .text-info{
            font-size: medium;
        }
        .big {line-height:200%}
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
    </style>
</head>
<body>
	<%@include file="../../static/includes/goTop.txt"%>
	<%@ include file="../includes/navbar.txt"%>
    <div class="container" >
        <div class="row">
            <div class="col-lg-8 text-info">
                <h1>关于本项目和本人</h1><hr>
                <h3 id="aboutProject">1.关于本项目</h3>
                <div>
                    <p class="big">&nbsp;&nbsp;&nbsp;&nbsp;本项目是一个基于印象笔记的开源博客框架.想法来源于一个最初的需求
                        :拥有一个博客,虽然网络上已经有CSDN,博客园,简书等等各种博客平台.但是作为一名正在成长的程序员来说,
                        自己建立一个独立博客的精神还是值得学习的,恰好我是印象笔记的一名忠实用户,在跳了好多坑之后,终于研究好了大体的
                        印象笔记的API,经过不懈的努力这个框架终于成功发布了.现在这个项目框架比较简单,在深入学习网络知识框架之后,会对次框架进行进一步修改.如有任何建议请通过我的github项目Issue中提问，或者邮箱联系我哦
                        (详情请移步<b><a href="https://github.com/swhwt" style="color:<%=Myutils.getProperty2("siteColor")%>;">我的github</a></b>)
                    </p>
                 </div>
                <h3 id="aboutMe">2.关于本人</h3>
                <div class="big">
                    <ul>
                        <li>身份:大三单身老腊肉</li>
                        <li>爱好:编程,动漫,音乐,读书,跑步</li>
                        <li>有问题,联系我:
                            <ul>
                                <li>邮箱:swhwtqwer@163.com</li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <h3 id="donate">3.捐助列表</h3>
                <div>
                    <ul>
                        <li></li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
    <%@ include file="../includes/footer.txt"%>
</body>
</html>