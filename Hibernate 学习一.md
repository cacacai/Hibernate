# Hibernate和servlet集成

## 环境
- myeclipse 2017
- jdk1.8
## 数据库
```
 create table `travel`.`travel`(
        `id` BIGINT not null,
       `spot` VARCHAR(300),
       `line` VARCHAR(300),
       `price` FLOAT(10),
       `num` INT,
        primary key (`id`)
    );

    create unique index `PRIMARY` on `travel`.`travel`(`id`);
```
## 过程
### 新建Javaee项目
直接new一个web project项目起个名字，然后next默认class保存目录，这里不用改。再next选择项目的首页和创建web.xml文件![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/16530995.jpg)
，然后再next就是lib选择，默认就好。
### 创建包来分类文件
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/66920596.jpg)

- iolayer放置servlet文件
- models放置hibernate生成的POJO DAO等文件
- sessionft放置HibernateSessionFactory文件

其他的为自动生成文件，也可以根据自己的习惯来分类文件。
### 修改编码防止乱码
- 修改项目编码，直接在项目名称右键选择properties，在resource中选择utf-8，默认为gbk
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/56376576.jpg)
- jsp文件修改方式为修改pageEncoding
```
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
```
- servlet 设置输出为utf-8方法为，设置响应编码为utf-8
```
response.setContentType("text/html;charset=utf-8");
response.setCharacterEncoding("utf-8");
```

## 添加servlet
直接在iolayer包名new选择servlet，建议不要选择在右键栏中显示的servlet，那个servlet配置不全面，应该在右键栏选择other然后输入servlet查找选择。
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/5688583.jpg)
然后填写一下servlet信息就行了直接finish。
> 可能出现的问题，在web.xml中刚刚添加进去servlet标签报下面的错误。
> cvc-complex-type.2.4.a: Invalid content was found starting with element 'description'. 
> 解决办法，修改一下标签的顺序，原因大概为读取标签的顺序不能随便来。
> ![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/85961053.jpg)

### 测试servlet
启动项目，在浏览器直接访问servlet的在web.xml设置的map路径，就可以获取到get方法里面的内容。
```
response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
//统一设置编码
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", 使用get方法访问travel servlet<br>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
```
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/21323858.jpg)

> 如果修改了servlet内容再点击run按钮在浏览器看不到servlet的变化需要把tomcat服务关了然后再重新运行。这个应该是没有重新编译修改后的servlet。

## 添加hibernate支持

### 连接数据库
在顶部选择Window里面的show view的DB Browser，如果没有则在show view选择other再选择。如果以前没有操作过的则需要在空白出右键选择new，根据自己的数据库类型来建立数据库连接类型。数据库驱动文件需要自己下载。这里提供mysql官方的下载地址https://dev.mysql.com/downloads/connector/j/5.1.html。
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/66768328.jpg)
测试连接成功后Finish。
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/17212219.jpg)
### 添加hibernate支持
在项目名称右键选择config fate，旧版本的myeclipse为选择myeclipse。然后选择安装hibernate。

- 根据自己需求选择版本，我是默认
- 然后next选择session 保存位置，就是刚才建立的sessionft包中。
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/88009713.jpg)
- 继续next选择数据库连接，直接在下拉框中选择我们刚才新建的数据库连接。
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/2683730.jpg)

然后就直接finish。
### 反转数据库表到项目中
在DBbrowser中选择需要操作的数据库表，右键选择Hibernate Reverse Engineering。

- 首先选择项目源文件路径/demo/src，选择我们建立的项目，如果没有需要重做上一步。 然后就是生成文件保存路径，默认没有，好像就算改了也还是在default中生成。然后就是POJO文件和DAO需不需要自动生成。
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/8767065.jpg)

- 然后就算hibernate配置文件的生成选择，直接点击setup，至于生成主键生成方法选择哪种可以参考http://www.imooc.com/article/8937
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/13922535.jpg)
- 配置表对应的类名，还有主键生成类型，然后finish就完成了。
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/53103744.jpg)

### 调整配置
因为生成的文件默认在default包中，可以根据需要调整到其他包中
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/92403834.jpg)
因为我把Travel class文件移动到了models包中，所以在对应的配置文件Travel.hbm.xml中也要修改他对应的包目录，
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/59217964.jpg)

## 测试

在servlet  travel中的doGet()的方法中添加代码

```
		response.setContentType("text/html;charset=utf-8");//设置编码
		response.setCharacterEncoding("utf-8");//设置编码
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", 使用get方法访问travel servlet<br>");
		//测试
		TravelDAO td=new TravelDAO();
		List<Travel> list=td.findAll();
		for (Travel travel : list) {
			out.print(travel.getLine()+"<br>");
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
```
结果
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/21307273.jpg)

到这里servlet和hibernate就基本可以一起工作了。
<!--stackedit_data:
eyJoaXN0b3J5IjpbNzAyMzg0MDc1XX0=
-->