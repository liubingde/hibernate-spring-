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
  		<br>
  		<br>
      <h1 align="center">用户信息管理系统</h1>
      <form action="ManageServlet" target="_top">
	      <table >
	    		<tr>
	    			<td width="80">
					    	 <a href="goodsmanage.jsp" target="mainFrame"><font size="2">主界面</font></a>
					  </td> 
					  <td width="80">
	    					 <a href="adminmanage.jsp"target="mainFrame"></a>
	    			</td>
	    			<td width="80">
	    					<input name="action" value="logout" type="submit" >
	    			</td>
	    		</tr>
	    	</table>
	      
      </form>
    	
  </body>  
</html>