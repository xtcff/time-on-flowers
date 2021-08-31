<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>花点时间</title>
		<link rel="stylesheet" type="text/css" href="${res }/themes/css/index.css"/>
		<script src="${js }/jquery-1.7.2.min.js" type="text/javascript"></script>
		<script src="${js }/jquery.cookie.js" type="text/javascript"></script>
		<script src="${js }/jquery.validate.js" type="text/javascript"></script>
		<script src="${js }/jquery.bgiframe.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="head">
			<div class="title">
				<a href="IndexServlet"><img src="${res }/themes/images/shop.jpg" id="shopimg"></a>
			</div>
			<div class="top_A">
				<form method="post" action="SearchServlet">
                	<div class="search-box-group">
                		<select name="select" class="search">
         					<option value="AllCategory" ${map.select=='AllCategory'?'selected':''}>全部</option>
         					<c:forEach var="category" items="${categoryList }">
         					<option value="${category.getName() }" ${map.select==category.getName()?'selected':''}>${category.getName() }</option>
         					</c:forEach>
      					</select>
      					<input type="text" name="low_price" size="20" class="search" placeholder="最低价格" value="${map.low_price }"/>&nbsp;-
						<input type="text" name="high_price" size="20" class="search" placeholder="最高价格" value="${map.high_price }"/>
                    	<input class="search" name="keyword" type="text" placeholder="输入鲜花进行搜索" value="${map.keyword }">
                    	<button class="search" type="submit">搜索</button>
               	 	</div>
            	</form>
            	<div class="right_top">
            		<a href="IndexServlet">首页</a>
            		<c:if test="${username != null}">
            		<a href="CartServlet">查看购物车</a>
            		<a href="OrderServlet">查看订单</a>
            		hello, ${username }<a href="LogoutServlet">退出</a>
            		</c:if>
            		<c:if test="${username == null}">
            		<a href="login.jsp">登录</a>
            		<a href="register.jsp">注册</a>
            		</c:if>
            	</div>
            	<c:if test="${username == null}">
            	<div class="input_to_login">
            		<form action="${ctx }/LoginServlet" method="post">
						<p>
							<label>用户名：</label>
							<input type="text" name="username" size="20" class="login_input" placeholder="输入用户名"/>
						</p>
						<p>
							<label>密码：&ensp;&ensp;</label>
							<input type="password" name="password" size="20" class="login_input" placeholder="请输入密码"/>
						</p>
						<div class="login_bar">
							<input type="submit" value="登录" />
							还没有账号？点击<a href="register.jsp">注册</a>
						</div>
					</form>	
            	</div>
            	</c:if>
			</div>
		</div>
		<div class="body">
			<div id="category_kuang">
				<p>分类列表</p>
				<ul>
					<li><a href="IndexServlet">全部</a></li>
					<c:forEach var="category" items="${categoryList }">
					<hr>
					<li><a href="FilterServlet?cate=${category.getId() }&order=salesdesc">${category.getName() }</a></li>
					<li><p>${category.getDescription() }</p></li>
					</c:forEach>
				</ul>
			</div>
			<div id="show_kuang">
			<div class="filter">
				<ul>
					<li class="filter_li"><a href="FilterServlet?order=salesdesc">销量降序</a></li>
					<li class="filter_li"><a href="FilterServlet?order=salesasc">销量升序</a></li>
					<li class="filter_li"><a href="FilterServlet?order=priceasc">价格升序</a></li>
					<li class="filter_li"><a href="FilterServlet?order=pricedesc">价格降序</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
					
					<li class="filter_li"><a href="PageFlowersServlet?pageNum=${page.getPageNum() - 1 }">上一页</a></li>
					<li class="filter_li">当前页: 第<form method="post" action="PageFlowersServlet" style="margin:0px;display:inline;">
													<input type="text" name="pageNum" size="1" value="${page.getPageNum() }"/>页
													<input type="submit" value="跳转" />
													</form></li>
					<li class="filter_li">共: ${page.getTotalPage() }页</li>
					<li class="filter_li"><a href="PageFlowersServlet?pageNum=${page.getPageNum() + 1 }">下一页</a></li>
				</ul>
			</div>
			<c:forEach var="flower" items="${page.getList() }" varStatus="status">
				<div class="my_flowers">
					<div class="image_f">
						<img src="${res }/image/${flower.getImage() }" class="img_a"/>
					</div>
					<div class="flower_xinxi">
						<ul>
							<li>${flower.getFlowerName() }</li>
							<li>详情&nbsp;&nbsp;&nbsp;${flower.getDescription() }</li>
							<c:if test="${username == null}">
							<li>价格&nbsp;&nbsp;&nbsp;${flower.getPrice() }￥</li>
							</c:if>
							<li>会员价&nbsp;&nbsp;&nbsp;${flower.getVip_price() }￥
							<c:if test="${username == null}">
							(<a href="login.jsp">登录</a>后即可享受会员价哦！)
							</c:if>
							</li>
							<li>销量&nbsp;&nbsp;&nbsp;${flower.getSales() }件</li>
						</ul>
						<div class="buy">
						<c:if test="${username == null}">
							<a href="login.jsp">加入购物车</a>
						</c:if>
						<c:if test="${username != null}">
							<a href="CartServlet?flowerId=${flower.getId() }">加入购物车</a>
						</c:if>
						</div>
					</div>
				</div>
			</c:forEach>	
			</div>
		</div>
	</body>
</html>