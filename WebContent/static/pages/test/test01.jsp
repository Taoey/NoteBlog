<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modelTitle</title>
<meta name="viewport"
	content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
</head>
<body>
	<div class="container">
		<h3>#JavaWeb编程常用</h3>
		<div>
			<div>
				<span style="color: rgb(227, 0, 0);">1.关于web.xml</span>
			</div>
			<div>主体部分主要有三部分</div>
			<ul>
				<li>欢迎页定义</li>
				<li>servlet引入</li>
				<li>servlet映射</li>
			</ul>
			<div>
				<br />
			</div>
			<div>&lt;web-app&gt;</div>
			<div>
				<br />
			</div>
			<div style="margin-left: 40px;">&lt;welcome-file-list&gt;</div>
			<div style="margin-left: 40px;">
				&nbsp; &nbsp; &nbsp;&lt;welcome-file&gt;<span
					style="color: rgb(209, 0, 255);">file</span>&lt;/welcome-file&gt;
			</div>
			<div style="margin-left: 40px;">&lt;/welcome-file-list&gt;</div>
			<div style="margin-left: 40px;">
				<br />
			</div>
			<div style="margin-left: 40px;">&lt;servlet&gt;</div>
			<div style="margin-left: 40px;">&nbsp; &nbsp;
				&nbsp;&lt;description&gt;&lt;/description&gt;</div>
			<div style="margin-left: 40px;">&nbsp; &nbsp;
				&nbsp;&lt;display-name&gt;&lt;/dislay-name&gt;</div>
			<div style="margin-left: 40px;">&nbsp; &nbsp;
				&nbsp;&lt;servlet-name&gt;&lt;/servlet-name&gt;</div>
			<div style="margin-left: 40px;">&nbsp; &nbsp;
				&nbsp;&lt;servlet-class&gt;&lt;/servlet-class&gt;</div>
			<div style="margin-left: 40px;">&lt;/servlet&gt;</div>
			<div style="margin-left: 40px;">
				<br />
			</div>
			<div style="margin-left: 40px;">&lt;servlet-mapping&gt;</div>
			<div style="margin-left: 40px;">&nbsp; &nbsp;
				&lt;servlet-name&gt;&lt;/servlet-name&gt;</div>
			<div style="margin-left: 40px;">&nbsp; &nbsp;
				&lt;url-pattern&gt;&lt;/url-pattern&gt;</div>
			<div style="margin-left: 40px;">&lt;/servlet-mapping&gt;</div>
			<div style="margin-left: 40px;">
				<br />
			</div>
			<div style="margin-left: 40px;">
				<br />
			</div>
			<div>&lt;/web-app&gt;</div>
			<div>
				<br />
			</div>
			<div>
				<span style="color: rgb(227, 0, 0);">2.关于jsp</span>
			</div>
			<div>
				<span style="color: rgb(227, 0, 0);"><br /></span>
			</div>
			<div>jsp页面常用的代码</div>
			<div align="left" style="min-height: 12pt;">
				<div>
					<font color="#008080" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">&lt;</span></font><font
						color="#3F7F7F" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">link</span></font> <font
						color="#7F007F" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">rel</span></font><font
						face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">=</span></font><font
						color="#2A00FF" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe"><i>&quot;stylesheet&quot;</i></span></font>
					<font color="#7F007F" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">type</span></font><font
						face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">=</span></font><font
						color="#2A00FF" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe"><i>&quot;text/css&quot;</i></span></font>
					<font color="#7F007F" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">href</span></font><font
						face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">=</span></font><font
						color="#2A00FF" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe"><i>&quot;css/index.css&quot;</i></span></font><font
						color="#008080" face="Courier New"><span
						style="font-size: 12pt; background: #e8f2fe">&gt;</span></font>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
				<div>
					<span style="font-size: 12pt;"><span
						style="font-family: &amp; apos;Courier New&amp;apos;;"><span
							style="background-color: rgb(232, 242, 254);"><span
								style="color: rgb(0, 128, 128);"><br /></span></span></span></span>
				</div>
			</div>
			<div>
				<span style="color: rgb(227, 0, 0);"><br /></span>
			</div>
			<div>
				<br />
			</div>
		</div>
	</div>
</body>
</html>