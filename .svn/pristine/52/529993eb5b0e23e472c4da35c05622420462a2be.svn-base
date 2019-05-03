<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/xml" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>index</title>
	<!-- base需要放到head中 -->
	<base href="<%=basePath%>"></base>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--<style>
        div {
            border: 1px solid red;
        }
    </style>-->
</head>
<body>
    <!--logo部分-->
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-6">
                <img src="img/logo.png">
            </div>
            <div class="col-lg-5 col-md-5 col-sm-6 hidden-xs">
                <img src="img/header.jpg">
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12" style="padding-top: 20px">
                <a href="#">登录</a>
                <a href="#">注册</a>
                <a href="#">购物车</a>
            </div>
        </div>
    </div>
    <!--菜单栏或导航栏部分-->
    <div class="container">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">首页</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">秒杀<span class="sr-only">(current)</span></a></li>
                        <li><a href="#">优惠券</a></li>
                        <li><a href="#">闪购</a></li>
                        <li><a href="#">拍卖</a></li>
                        <li><a href="#">服装城</a></li>
                        <li><a href="#">全球购</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">全部分类<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                    <li><a href="#">秒杀</a></li>
                                <li><a href="#">闪购</a></li>
                                <li><a href="#">拍卖</a></li>
                                <li><a href="#">服装城</a></li>
                                <li><a href="#">全球购</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">没有更多啦</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-right">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="请输入关键词">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
    <!--百叶窗部分-->
    <div class="container">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="img/4.jpg" alt="First slide">
                </div>
                <div class="item">
                    <img src="img/5.jpg" alt="Second slide">
                </div>
                <div class="item">
                    <img src="img/6.jpg" alt="Third slide">
                </div>
            </div>
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <!--热门商品-->
    <div class="container">
        <!--上面文字部分-->
        <div class="row">
            <span style="font-size: 25px;margin-left: 15px">热门商品</span>
            <img src="img/title2.jpg">
        </div>
        <!--下边图片部分-->
        <div class="row">
            <!--分为两列，左边占2，右边占10-->
            <div class="col-md-2 col-sm-2 hidden-sm hidden-xs" style="height: 400px">
                <img src="img/big01.jpg" height="100%"/>
            </div>
            <div class="col-md-10 col-sm-10" style="padding-left: 10px">
                <!--上边的行-->
                <div class="row">
                    <div class="col-md-6 hidden-sm hidden-xs" style="height: 200px;width: 485px">
                        <img src="img/middle01.jpg" height="100%" width="100%"/>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center" style="margin-top: 12px">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--广告图片-->
    <div class="container">
        <div class="row">
            <div class="col-md-12 hidden-sm hidden-xs">
                <img src="img/ad.jpg" width="100%">
            </div>
        </div>
    </div>
    <!--最新商品-->
    <div class="container">
        <!--上面文字部分-->
        <div class="row">
            <span style="font-size: 25px;margin-left: 15px">最新商品</span>
            <img src="img/title2.jpg">
        </div>
        <!--下边图片部分-->
        <div class="row">
            <!--分为两列，左边占2，右边占10-->
            <div class="col-md-2 col-sm-2 hidden-sm hidden-xs" style="height: 400px">
                <img src="img/big01.jpg" height="100%"/>
            </div>
            <div class="col-md-10 col-sm-10" style="padding-left: 10px">
                <!--上边的行-->
                <div class="row">
                    <div class="col-md-6 hidden-sm hidden-xs" style="height: 200px;width: 485px">
                        <img src="img/middle01.jpg" height="100%" width="100%"/>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                </div>
                <!--下边的行-->
                <div class="row">
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6" align="center">
                        <a href="#"><img src="img/small01.jpg" width="130px" height="130px"></a>
                        <a href="#"><p style="color: gray">华为P10</p></a>
                        <p style="color: red">￥3499</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--广告图片-->
    <div class="container">
        <div class="row">
            <div class="col-md-12 hidden-sm hidden-xs">
                <img src="img/footer.jpg" width="100%" />
            </div>
        </div>
    </div>
    <!--版权信息和友情链接-->
    <div class="container">
        <div class="row">
            <div style="text-align: center;margin-top: 5px;">
                <ul class="list-inline">
                    <li><a href="info.html">关于我们</a></li>
                    <li><a>联系我们</a></li>
                    <li><a>招贤纳士</a></li>
                    <li><a>法律声明</a></li>
                    <li><a>友情链接</a></li>
                    <li><a>支付方式</a></li>
                    <li><a>配送方式</a></li>
                    <li><a>服务声明</a></li>
                    <li><a>广告声明</a></li>
                </ul>
            </div>
            <div class="row footer-bottom">
                <ul class="list-inline text-center">
                    <li> Copyright &copy; 2005-2017</li><li>51Code商城 版权所有</li>
                </ul>
            </div>
        </div>
    </div>
    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>