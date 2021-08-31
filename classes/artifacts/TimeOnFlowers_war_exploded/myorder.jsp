<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>我的订单-花点时间</title>
<link href="${res }/themes/css/myorder.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<div class="right_top">
		<a href="IndexServlet">首页</a>
        <a href="CartServlet">查看购物车</a>
        <a href="OrderServlet">查看订单</a>
        hello, ${username }<a href="LogoutServlet">退出</a>
	</div>
	<c:if test="${orderInfoList[0].getOrderItems() == null }">
		<div class="row">   
			<h1>您还没有购买过任何东西！！！<a href="IndexServlet">去购物</a></h1> 
		</div>
	</c:if>
	<c:if test="${orderInfoList[0].getOrderItems() != null }">
	<div class="total_order">
		<h1>我的订单</h1>
		<c:forEach items="${orderInfoList }" var="order">
		<div class="each_order">
			<div class="order_head">
			<h3>订单${order.getOrderid() }信息</h3>
			<table class="order_table" border="1">
				<thead>
					<tr>
						<td class="table_center">订单号</td>
						<td class="table_center">总计</td>
						<td class="table_center">收货地址</td>
						<td class="table_center">收货人</td>
						<td class="table_center">收获联系电话</td>
						<td class="table_center">订单生成时间</td>
						<td class="table_center">订单状态</td>
						<td class="table_center">收货时间</td>
						<td class="table_center">
						<a href="#" onclick="openwindow(${order.getOrderid() })">修改订单</a>&nbsp;
						<a href="#" onclick="deleteOrder(${order.getOrderid() }, '${order.getOrder_status() }')">删除订单</a>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="table_center">${order.getOrderid() }</td>
						<td class="table_center">${order.getPrice() }</td>
						<td class="table_center">${order.getAddress() }</td>
						<td class="table_center">${order.getTosb_name() }</td>
						<td class="table_center">${order.getTosb_phone() }</td>
						<td class="table_center">${order.getWhento() }</td>
						<td class="table_center">${order.getOrder_status() }</td>
						<td class="table_center">${order.getReceive_time() }</td>
					</tr>
				</tbody>
			</table>
			</div>
			<div class="order_details">
				<h3>订单${order.getOrderid() }详情</h3>
				<table class="order_table" border="1">
					<thead>
						<tr>
							<td class="table_center">花束名称</td>
							<td class="table_center">数量</td>
							<td class="table_center">单价</td>
							<td class="table_center">总计</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${order.getOrderItems() }" var="orderItem">
							<tr>
								<td class="table_center">${orderItem.getFlowers().getFlowerName() }</td>
								<td class="table_center">${orderItem.getQuantity() }束</td>
								<td class="table_center">${orderItem.getFlowers().getVip_price() }元</td>
								<td class="table_center">${orderItem.getPrice() }元</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<br /><br />	
		<div id="${order.getOrderid() }" class="order_inf">
			<h5 class="ptag">修改收货人信息</h5>
			<hr/>
			<form method="post" action="OrderServlet">
			<div class="pageFormContent">
			<input type="hidden" name="order_id" value="${order.orderid }"/>
			<p>
				<label>收货人姓名：</label>
				<input type="text" size="30" value="${order.tosb_name }" name="receive_name"/>
			</p>
			<p>
				<label>收货人联系电话：</label>
				<input type="text" size="30" value="${order.tosb_phone }" name="receive_phone" >
			</p>
			<p>
				<label>收货地址：</label>
				<input type="text" size="30" value="${order.address }" name="receive_address" >
			</p>
			</div>
			<div class="formBar">
			<ul style="list-style: none">
				<li style="display: inline"><button type="submit" class="submit_btn">保存</button></li>
				<li style="display: inline"><button type="button" onclick="closewindow(${order.getOrderid()})" class="cancel_btn">取消</button></li>
			</ul>
			</div>
			</form>
		</div><div id="${order.getOrderid() }back" class="all_light"></div>
		
		</c:forEach>
	</div>
	</c:if>
	
	<script type="text/javascript">
		function deleteOrder(order_id, order_status){
			if(order_status == "未处理"){
				if(confirm("确认要删除订单？")){
					window.location.href="DeleteOrderServlet?order_id="+order_id; 
				}
			}else{
				alert("订单已经打包，不能删除哦！")
			}
		}
		function openwindow(order_id){
			document.getElementById(order_id+'back').style.display = 'block';
			document.getElementById(order_id).style.display = 'block';
		}
		function closewindow(order_id){
			document.getElementById(order_id+'back').style.display = 'none';
			document.getElementById(order_id).style.display = 'none';
		}
	</script>
</body>
</html>
