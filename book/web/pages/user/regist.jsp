<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含 base标签，css样式，jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">

		$(function (){
			//给用户名输入框绑定失去焦点事件
			$("#username").blur(function(){
				//获取用户名
				var username=this.value;
				$.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistsUsername&username="+username,function (data){
					if(data.existsUsername){
						$("span.errorMsg").text("用户名已存在！");
					}else{
						$("span.errorMsg").text("用户名可用");
					}
				});
			});


			//给验证码图片绑定单击事件
			$("#code_img").click(function (){
				//在事件响应的function函数中有一个this对象，代表当前事件的dom对象，即验证码img对象
				//src表示img标签的 图片路径。它可读、可写
				//最后加一个时间戳，以免浏览器从缓存读取图片，导致图片不更换
				this.src="${basePath}kaptcha.jpg;?d=" + new Date();
			});

			$("#sub_btn").click(function (){

				// 验证用户名：必须由字母，数字下划线组成，并且长度为5 到12 位
				//1.获取用户名输入框里的内容
				var usernameText=$("#username").val();
				//2.创建正则表达式对象
				var usernamePatt=/^\w{5,12}$/;
				//3.使用test方法验证
				if(!usernamePatt.test(usernameText)){
					//4.提示结果
					$("span.errorMsg").text("用户名不合法");
					return false;
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为5 到12 位
				//1.获取密码输入框里的内容
				var passwordText=$("#password").val();
				//2.创建正则表达式对象
				var passwordPatt=/^\w{5,12}$/;
				//3.使用test方法验证
				if(!passwordPatt.test(passwordText)){
					//4.提示结果
					$("span.errorMsg").text("密码不符合要求");
					return false;
				}

				// 验证确认密码：和密码相同
				var repwdText=$("#repwd").val();
				if(passwordText!=repwdText){
					$("span.errorMsg").text("密码不一致");
					return false;
				}

				// 邮箱验证：xxxxx@xxx.com
				var emailText=$("#email").val();
				var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if(!emailPatt.test(emailText)){
					$("span.errorMsg").text("邮箱格式不正确");
					return false;
				}

				// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。code
				var codeText=$("#code").val();
				//去掉验证码前后空格
				codeText=$.trim(codeText);
				if(codeText==null||codeText==""){
					$("span.errorMsg").text("验证码不能为空");
					return false;
				}

				$("span.errorMsg").text("");
			});

		});
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off"
										   tabindex="1" name="username" id="username"
										   value="${requestScope.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.email}" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 110px;" name="code" id="code"/>
									<%--添加谷歌验证码图--%>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right;width: 120px; height: 40px;margin-right: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含 页脚信息--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>