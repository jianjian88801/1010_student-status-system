<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
            body
            {  
                 background:url('img/liu3718.jpg');  
                 background-position:center; 
                 background-repeat:no-repeat;  
                 background-color:  #CCCCCC;
            }


        </style>
		
		<script type='text/javascript' src='<%=path %>/dwr/interface/loginService.js'></script>
        <script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
        <script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
        
		<script language="javascript">
		function check1()
		{                                                                                         
		     if(document.ThisForm.userName.value=="")
			 {
			 	alert("请输入用户名");
				document.ThisForm.userName.focus();
				return false;
			 }
			 if(document.ThisForm.userPw.value=="")
			 {
			 	alert("请输入密码");
				document.ThisForm.userPw.focus();
				return false;
			 }
			 document.getElementById("indicator").style.display="block";
			 loginService.login(document.ThisForm.userName.value,document.ThisForm.userPw.value,0,callback);
		}
		
		function callback(data)
		{
		    document.getElementById("indicator").style.display="none";
		    if(data=="no")
		    {
		        alert("用户名或密码错误");
		    }
		    if(data=="yes")
		    {
		        alert("通过验证,系统登录成功");
		        window.location.href="<%=path %>/loginSuccess.jsp";
		    }
		    
		}
		
		
		function reg()
	    {
			  var strUrl = "<%=path %>/userReg.jsp";
	          var ret = window.showModalDialog(strUrl,"","dialogWidth:800px; dialogHeight:500px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
		}
	    </script>

	</head>

	<body>
		<div id="Layer1" style="position:absolute; left:550px; top:247px; width:360px; height:131px; z-index:1">
			<form action="" name="ThisForm" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="100%" colspan="2" align="left">
							<font style="font-size: 40px;color: white;font-weight: bolder;display: block;text-align: left">
							    &nbsp;&nbsp;&nbsp;学籍管理系统
				            </font>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="2" height="20">
							
						</td>
					</tr>
					<tr>
						<td width="15%" height="22">
							<span style="font-size: 12px;font-weight: 700">账号:</span>
						</td>
						<td width="85%" height="22">
							<input name="userName" type="text" style="height:20px;width:130px; border:solid 1px #bbbbbb">
						</td>
					</tr>
					<tr>
						<td width="15%" height="22">
							<span style="font-size: 12px;font-weight: 700">密码:</span>
						</td>
						<td width="85%" height="22">
							<input name="userPw" type="password" style="height:20px;width:130px; border:solid 1px #bbbbbb">
						</td>
					</tr>
					<tr>
						<td width="15%" height="22">
							
						</td>
						<td width="85%" height="22">
							<br/>
							<input type="button" value="登录" style="width: 60px;" onClick="check1()">
							<input type="reset" value="重置" style="width: 60px;">
                            <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
