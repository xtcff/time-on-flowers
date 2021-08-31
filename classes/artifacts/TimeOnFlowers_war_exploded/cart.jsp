<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>我的购物车-花点时间</title>
<link href="${res }/themes/css/cart.css" type="text/css" rel="stylesheet" />
<link href="${res }/themes/css/jbox.css" type="text/css" rel="stylesheet" />
<script src="${js }/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${js }/jquery.cookie.js" type="text/javascript"></script>
<script src="${js }/jquery.validate.js" type="text/javascript"></script>
<script src="${js }/jquery.bgiframe.js" type="text/javascript"></script>
</head>

<body>
	<div class="right_top">
		<a href="IndexServlet">首页</a>
        <a href="CartServlet">查看购物车</a>
        <a href="OrderServlet">查看订单</a>
        hello, ${username }<a href="LogoutServlet">退出</a>
	</div>
	<c:if test="${cart == null }">
		<h1>您的购物车还是空空的，赶紧行动吧！！！<a href="IndexServlet">去购物</a></h1>
	</c:if>
	<c:if test="${cart != null }">
		<div class="cart_all">
			<div class="cart_top">
				<h1>我的购物车</h1>
				<!-- 显示购物车中的商品 -->
				<div class="cart_info">
					<table class="table_info" border="1">
						<thead>
							<tr>
								<td class="table_head">花名</td>
								<td class="table_head">数量</td>
								<td class="table_head">单价</td>
								<td class="table_head">总计</td>
							</tr>
						</thead>
						<tbody>
							<!-- 遍历购物车中的商品并显示 -->
							<c:forEach items="${cart.getMapValues() }" var="cartItem">
								<tr>
									<td class="table_body">${cartItem.getFlowers().getFlowerName() }</td>
									<td class="table_body">${cartItem.getQuantity() }束</td>
									<td class="table_body">${cartItem.getFlowers().getVip_price() }元</td>
									<td class="table_body">${cartItem.getVip_price() }元</td>
									<td class="table_body"><a href="DeleteCartItemServlet?item_id=${cartItem.getItem_id() }">删除</a></td>
								</tr>
							</c:forEach>
							<!-- //遍历购物车中的商品并显示 -->
						</tbody>
					</table>
				</div>
				<!-- //显示购物车中的商品 -->
				<!-- 显示总计金额  -->
				<div class="cart_total">
					<span>总计:${cart.getPrice() }元</span>
				</div>
				<!-- //显示总计金额  -->				
			</div>
			
			
			<div class="cart_bottom">
				<div class="pageContent">
					<div class="pull-left">
						<a href="IndexServlet">继续购物</a>
						<a href="#" onclick="clearCart()">清空购物车</a>
					</div>
					<div class="tobe_order">
						<a href="#" onclick="openwindow()">提交订单</a>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div id="order_inf">
		<h5 class="ptag">填写收货人信息</h5>
		<hr/>
		<form method="post" action="OrderServlet">
		<div class="pageFormContent">
		<input type="hidden" value="" name="order_id" value=""/>
		<p>
			<label>收货人姓名：</label>
			<input type="text" size="30" value="" name="receive_name"/>
		</p>
		<p>
			<label>收货人联系电话：</label>
			<input type="text" size="30" value="" name="receive_phone" >
		</p>
		<p>
			<label>收货地址：</label>
			<input type="text" size="30" value="" name="receive_address" >
		</p>
		</div>
		<div class="formBar">
		<ul style="list-style: none">
			<li style="display: inline"><button type="submit" class="submit_btn">保存</button></li>
			<li style="display: inline"><button type="button" onclick="closewindow()" class="cancel_btn">取消</button></li>
		</ul>
		</div>
		</form>
	</div><div id="all_light"></div>
	<script type="text/javascript">
		function clearCart(){
			if(confirm("确认要清空购物车？")){
				window.location.href="ClearCartServlet"; 
			}
		}
		function openwindow(){
			document.getElementById('all_light').style.display = 'block';
			document.getElementById('order_inf').style.display = 'block';
		}
		function closewindow(){
			document.getElementById('all_light').style.display = 'none';
			document.getElementById('order_inf').style.display = 'none';
		}
	</script>
	
</body>
</html>
