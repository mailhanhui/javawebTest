<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含 base标签，css样式，jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>

</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">购物车</span>

		<%--静态包含，登录成功之后的菜单--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function (){
				//给删除绑定单击事件
				$("a.deleteItem").click(function (){
					return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text()+ "】吗？")
				});
				//给清空购物车绑定单击事件
				$("#clearCart").click(function (){
					return confirm("你确定要【清空购物车】吗？")
				});
				//给输入框绑定onchange事件(内容发生变化时触发)
				$(".updateCount").change(function (){
					var name=$(this).parent().parent().find("td:first").text();
					var count =this.value;
					if(confirm("你确定要将【" + name + "】的数量修改为:【"+ count +"】吗？")){
						//发起请求，给服务器保存修改
						var url="http://localhost:8080/book/cartServlet?action=updateCount"
						location.href= url + "&count="+count+"&id="+$(this).attr("bookId");
					}else{
						//恢复默认值
						this.value=this.defaultValue;
					}
				});
			});
		</script>
	</div>
	
	<div id="main">
			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<%--如果购物车为空--%>
				<c:if test="${empty sessionScope.cart.items}">
					<tr>
						<td colspan="5" align="center" ><a href="index.jsp">您的购物车中没有商品！点击跳回首页</a></td>
					</tr>
				</c:if>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width:50px " bookId="${entry.value.id}" type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<%--购物车非空情况下才输出下面的内容--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
					<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
					<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
				</div>
			</c:if>
	</div>
	<%--静态包含 页脚信息--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>