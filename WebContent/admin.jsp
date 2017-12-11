<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="static/css/login.css" rel="stylesheet">
</head>
<body>
   <div class="container">
      <form class="form-signin" action="${pageContext.request.contextPath }/LoginServlet" method="post">
        <h2 class="form-signin-heading">登录</h2>
        <label for="inputEmail" class="sr-only">用户名</label>
        <input type="text" id="inputEmail" name="name" class="form-control" placeholder="用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="pwd" class="form-control" placeholder="密码" required>
		<%
        String isError=(String)session.getAttribute("error");
        if("True".equals(isError)){
        	out.println("<div style=\"color:red; margin:5px 0 5px 0\">");
        	out.println("<lable>用户名或密码错误!!!</lable>");
        	out.println("</div>");
        }
        %>
        <button class="btn btn-lg btn-yx btn-block" style="margin-top:10px" type="submit">Sign in</button>
      </form>

    </div> 
</body>
</html>