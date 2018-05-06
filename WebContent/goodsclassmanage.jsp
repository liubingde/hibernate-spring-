<%@ page language="java" import="java.util.*,org.springframework.web.context.*,org.springframework.web.context.support.*,dao.DButil" pageEncoding="UTF-8"%>      
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
            <h1 align="center">商品类型添加界面</h1>
    	<form action="ManageServlet" name="form1">
    		<table width="100%" border="0" cellspacing="1" bgcolor="black">
    			 <tr bgcolor="white">
				    <td align="center">商品名称:</td>
				    <td><input size="20" name="gcname" id="gcname"/></td>
			 	 </tr>
			</table>
			 <tr bgcolor="white"> 
				  	<td><input type="submit" name="action" value="增添" onClick="mycheck()"> </td>
				  	<td><input type="reset"></td>
			</tr> 
    	</form>
    
  </body>  
</html>