<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
    <%
    for(int i=0;i<200;i++){
    	out.println(i); 	%>
    	<br/>
    	<%
    }
    %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="content-type" content="text/html; charset=UTF-8">
<title>返回顶部</title>
<link rel="stylesheet" href="http://at.alicdn.com/t/font_403793_08hscus8kccba9k9.css">
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width,
                                 initial-scale=1.0,
                                 maximum-scale=1.0,
                                 user-scalable=no">
</head>
<body>
<%@include file="../../includes/goTop.txt" %>
</body>

</html>