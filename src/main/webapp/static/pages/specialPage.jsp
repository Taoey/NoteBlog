<%@page import="utils.Myutils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=Myutils.getProperty2("aliLink")%>">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
     <%@include file="../includes/googleUA.txt"%>                                
    <style>
        body{
            padding-top: 50px;
        }
        .special-div{
            height: 340px;
        }
        .special-border{
            height: 320px;;
            padding:5px 10px 0 10px;
            border-style: solid;
            border-color: #a4a1aa;
            border-width: 2px;
            border-radius:5px;
        }
        .special-border div a:hover{
            text-decoration:none;
        }
        .special-info-border{
            text-overflow:ellipsis;
            border-top:dashed;
            border-color: #a4a1aa;
            border-width: 2px;
        }
        .largerShow{
            transition: transform 0.25s ease;;
        }
        .largerShow:hover {
            transform: scale(1.1);
            -ms-transform: scale(1.1);
            -webkit-transform: scale(1.1);
            -moz-transform: scale(1.1);
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
    </style>
</head>
<body>
<%@include file="../../static/includes/goTop.txt"%>
<%@ include file="../includes/navbar.txt"%>
<div class="jumbotron" style="background-color: <%=Myutils.getProperty2("siteColor")%>;color: #FFF">
    <div class="container">
        <div class="header-banner">
            <div class="container">
                <div class="text-center">
                    <h1>相信未来</h1>
                    <p>精益求精，发表高质量的技术博客</p>
                    <p>黄为涛的博客</p>
                </div>

            </div>
        </div>
    </div>
</div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-sm-6   special-div">
                <div class="special-border largerShow">
                    <div class="text-center">
                        <a href="./tagList.jsp?tagGuid=8bab9ff0-91d0-433c-99f1-353859fcc04c">
                            <img  src="../images/specialPage/SQL.jpg" height="100" width="100"/>
                            <h3>SQL</h3>
                        </a>
                    </div>
                    <p><b>状态:<span style="color: #ffaa34">连载中</span></b></p>
                    <div>
                        <div class="special-info-border text_con">
                            <p>构化查询语言(Structured Query Language)，是一种特殊目的的编程语言，是一种数据库查询和程序设计语言，用于存取数据以及查询、更新和管理关系数据库系统</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-sm-6   special-div">
                <div class="special-border largerShow">
                    <div class="text-center">
                        <a href="./tagList.jsp?tagGuid=bdcf2d42-546a-4c47-a0e5-6eefefe159ea">
                            <img  src="../images/specialPage/Mybatis.jpg" height="100" width="100"/>
                            <h3>Mybatis</h3>
                        </a>
                    </div>
                    <p><b>状态:<span style="color: #ffaa34"> 连载中</span></b></p>
                    <div>
                        <div class="special-info-border text_con">
                            <p>MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集</p>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-lg-3 col-sm-6   special-div">
                <div class="special-border largerShow">
                    <div class="text-center">
                        <a href="./tagList.jsp?tagGuid=0dc2f57e-ce51-4259-9670-3b8f4ea85f9e">
                            <img  src="../images/specialPage/Bootstrap.png" height="100" width="100"/>
                            <h3>BootStrap</h3>
                        </a>
                    </div>
                    <p><b>状态:<span style="color: #ffaa34"> 连载中</span></b></p>
                    <div>
                        <div class="special-info-border text_con">
                            <p>Bootstrap 是全球最受欢迎的前端组件库，用于开发响应式布局、移动设备优先的 WEB 项目。</p>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-lg-3 col-sm-6   special-div">
                <div class="special-border largerShow">
                    <div class="text-center">
                        <a href="./tagList.jsp?tagGuid=c5f41a9f-6e87-400a-b5a1-550fd7f87bac">
                            <img  src="../images/specialPage/Github.jpg" height="100" width="100"/>
                            <h3>Github</h3>
                        </a>
                    </div>
                    <p><b>状态:<span style="color: #ffaa34"> 连载中</span></b></p>
                    <div>
                        <div class="special-info-border text_con">
                            <p>作为开源代码库以及版本控制系统，Github拥有超过900万开发者用户。随着越来越多的应用程序转移到了云上，Github已经成为了管理软件开发以及发现已有代码的首选方法</p>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-lg-3 col-sm-6   special-div">
                <div class="special-border largerShow">
                    <div class="text-center">
                        <a href="./tagList.jsp?tagGuid=ed108358-e2c8-43d3-a766-04df365e446f">
                            <img  src="../images/specialPage/Spring.jpg" height="100" width="100"/>
                            <h3>Spring</h3>
                        </a>
                    </div>
                    <p><b>状态:<span style="color: #ff2620">待学</span></b></p>
                    <div>
                        <div class="special-info-border text_con">
                            <p>Spring 是一个开源框架，是为了解决企业应用程序开发复杂性而创建的。框架的主要优势之一就是其分层架构，分层架构允许您选择使用哪一个组件，同时为 J2EE 应用程序开发提供集成的框架</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="../includes/footer.txt"%>
</body>
</html>