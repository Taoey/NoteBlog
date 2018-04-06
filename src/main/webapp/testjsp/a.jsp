<%@page import="
dao.NoteDao,
dao.TagDao,
dao.Note2TagDao,
java.util.*,
javabean.Note,
javabean.Tag,
java.io.File
"%>
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
		<title>Insert title here</title>
	</head>
	<body>
		<%System.out.println(System.getProperty("catalina.home")); %>
		<% System.out.println(this.getClass().getResource("")); %>
		<%
		//class根路径
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
        //项目执行位置所在(一定正确)
        System.out.println(f.getAbsolutePath().replace("WEB-INF"+f.separator+"classes", ""));
        %>
        <a href="<%=basePath%>">首页</a>
        <%        
        String noteGuid ="a0";String noteTitle= "ti";
        out.println(String.format("<li><a href=\"%sblogs/%s\" target=\"_blank\">%s</a></li>",basePath,noteGuid,noteTitle));
        %>
	</body>
</html>