<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>土特产出售网</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link href="css/animate.min.css" rel="stylesheet"> 
    <link href="css/lightbox.css" rel="stylesheet"> 
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">  
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<header id="header">      
        <div class="container">
            <div class="row">
                <div class="col-sm-12 overflow">
                   <div class="social-icons pull-right">
                        <ul class="nav nav-pills">
                            <li><a href=""><i class="fa fa-facebook"></i></a></li>
                            <li><a href=""><i class="fa fa-twitter"></i></a></li>
                            <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                            <li><a href=""><i class="fa fa-dribbble"></i></a></li>
                            <li><a href=""><i class="fa fa-linkedin"></i></a></li>
                        </ul>
                    </div> 
                </div>
             </div>
        </div>
        <div class="navbar navbar-inverse" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <a class="navbar-brand" href="index.html">
                    	<h1><img src="images/logo.png" alt="logo"></h1>
                    </a>
                    
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="index.jsp">首页</a></li>
                        <li class="dropdown"><a href="#">介绍 <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                                <li><a href="aboutus.html">关于我们</a></li>
                               <!--  <li><a href="service.html">Services</a></li> 
                                <li><a href="pricing.html">Pricing</a></li>-->
                                <li><a href="contact.html">联系我们</a></li>
<!--                                 <li><a href="contact2.html">Contact us 2</a></li>
                                <li><a href="404.html">404 error</a></li> -->
                                <li><a href="coming-soon.html">即将推出</a></li>
                            </ul>
                        </li>                    
                      <!--   <li class="dropdown"><a href="blog.html">Blog <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                                <li><a href="blog.html">Blog Default</a></li>
                                <li><a href="blogtwo.html">Timeline Blog</a></li>
                                <li><a href="blogone.html">2 Columns + Right Sidebar</a></li>
                                <li><a href="blogthree.html">1 Column + Left Sidebar</a></li>
                                <li><a href="blogfour.html">Blog Masonary</a></li>
                                <li><a href="blogdetails.html">Blog Details</a></li>
                            </ul>
                        </li> -->
                        <li class="dropdown"><a href="portfolio.html">土特产 <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                                <li><a href="portfolio.html">水果</a></li>
                                <li><a href="portfoliofour.html">草药</a></li>
                                <li><a href="portfolioone.html">海产</a></li>
<!--                                 <li><a href="portfoliotwo.html">3 Columns + Left Sidebar</a></li>
                                <li><a href="portfoliothree.html">2 Columns</a></li>
                                <li><a href="portfolio-details.html">Portfolio Details</a></li> -->
                            </ul>
                        </li>                         
                       <!--  <li><a href="shortcodes.html ">Shortcodes</a></li>   -->                  
                    </ul>
                </div>
                <div class="search">
                    <form role="form">
                        <i class="fa fa-search"></i>
                        <div class="field-toggle">
                            <input type="text" class="search-form" autocomplete="off" placeholder="Search">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </header>
    <!--/#header-->
    <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>
							产品
						</th>
						<th>
							单价（元/公斤）
						</th>
						<th>
							介绍
						</th>
						<th>
							剩余数量
						</th>
						<th>
							输入购买量
						</th>
						<th>
							点击购买
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}"  var="list" varStatus="vs">  
					<form method="post" action="servlet/Buy" >
					<tr>
					
						<td>
							${list.name}
						</td>
						<td>
							${list.price}
						</td>
						<td>
							${list.info}
						</td>
						<td>
							${list.num}
						</td>
						<td>
							<input name="count" value="1" type="text" />
						</td>
						<td>
						<input name="id" value="${list.id}" type="hidden" />
						<button class="btn" contenteditable="true" type="submit">提交</button>
						</td>
						
					</tr>
					</form>
					</c:forEach>
					
				</tbody>
			</table>
		</div>
	</div>
</div>
    
    <!--/#home-slider-->

   

    <footer ">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="copyright-text text-center">
                        <a>&copy; 土特产网 2018. All Rights Reserved.</a>
                        <p> <a target="_blank" href="#">Designed byThemeum</a></p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!--/#footer-->

    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/lightbox.min.js"></script>
    <script type="text/javascript" src="js/wow.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>   
</body>
</html>

