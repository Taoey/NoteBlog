<%@page import="
dao.NoteDao,
dao.TagDao,
java.util.*,
javabean.Note,
javabean.Tag

"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  tagList
<%
	List<Tag> tagList=TagDao.getAllTag();
	if (tagList != null&& !tagList.isEmpty()) {
		for (int i = 0; i < tagList.size(); i++) {
			String guid = tagList.get(i).getGuid();	
			String name = tagList.get(i).getName();
			String tag =String.format("<li><a href=\"#\">%s</a></li>",name);						
		}
	}
	
%>
</body>
</html>