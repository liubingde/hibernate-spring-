<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>      
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>登陆</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  

   	<script type="text/javascript" charset="UTF-8" >
		function mycheck(){
		
			var uname=document.form1.username.value;//注意form1
			var pawd=document.form1.password.value;

			if(uname==""){
				alert("用户名为空，请重新输入！！！");
				return false;
				}
			if(pawd==""){
				alert("密码为空，请重新输入");
				return false;
				}
			return ture;

			}
   	</script>
  
  </head>  
    
  <body>  
     <form action="ManageServlet" name="form1">
     	<center>
     		<table>
     			<tr>
     				<td>用户名:</td>
     				<td> <input type="text" name="username" value=""></td>
     			</tr>
     			<tr>
     				<td>密码:</td>
     				<td> <input type="text" name="password" value=""></td>
     			</tr>
     			<tr> <td><input type="submit" name="action" value="登陆" onClick="mycheck()"> </td></tr>
     		</table>
     			
     	</center>
     </form>
    
  </body>  
</html>