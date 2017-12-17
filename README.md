# 印象博客框架


### 1.运行环境

- MySQL版本:5.6.17
- Tomcat版本:8.0.33
- JDK版本:1.8.0_65

### 2. Token 获取
因自己的API权限印象笔记官方暂时没有开通,故只能利用其它应用的Token问题解决地址:
[获取Token][1]

### 3.项目使用
- 把项目下的.SQL文件导入到MySQL中,建立数据库
- 把获取的Token复制到ROOT\WEB-INF\classes目录下的config.properties文件中
- 配置自己想要共享的笔记本名字
- 把ROOT.war移动到Tomcat中的Webapps中,并删除项目中原有的ROOT文件夹
- 启动Tomcat,过一段时间看到解压的ROOT文件夹即可
- 访问127.0.0.1:你的端口号  ,例如127.0.0.1:8080,即可
- 访问127.0.0.1:端口号/admin 可启动同步服务

  [1]: https://github.com/suziwen/blogxiaoshujiang/blob/master/2017-9-17%20%E5%85%B3%E4%BA%8E%20Evernote%28%E5%8D%B0%E8%B1%A1%E7%AC%94%E8%AE%B0%29%20%E5%81%9C%E6%AD%A2%E4%BD%BF%E7%94%A8%20Developer%20Token,%E5%B0%8F%E4%B9%A6%E5%8C%A0%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%B8%8D%E8%83%BD%E7%BB%91%E5%AE%9A%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88.md
