<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>      
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>insert title</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
   
  
  </head>  
    
  <body>  
      <form action="ManageServlet" name="form1">
      	<table>
      		<tr> 
      			<td>名称:</td>
      			<td><input type="text" name="aname"></td>
      		</tr>
      		<tr>
      			<td>密码:</td>
      			<td><input type="text" name="apwd"></td>
      		</tr>
      		<tr>
      			<td>等级:</td>
      			<td><input type="text" name="alevel"></td> 
      		</tr>
      		<tr>
      			<td><input type="submit" name="action" value="admn"></td>
      			<td><input type="reset" ></td>
      		</tr>
      	</table>
      </form>
    	
  </body>  
</html>