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
   
  <script type="text/javascript">
	function mycheck(){
		var name=document.form1.gcname.value;
		if(name==""){
			alert("名称不能为空");
			return false;

			}
			
		}
  </script>
  </head>  
    
  <body>  
      <form action="ManageServlet" name="form1">
      	<table>
      		<tr>
      			<td align="center">种类名称</td>
				<td><input name="gcname" id="gcname"/></td>
      		</tr>
      		<tr>
      			<td><input type="submit" name="action"value="agc" onClick="mycheck()">增添</td>
      			<td><input type="reset"></td>
      		</tr>
      	</table>
      	
      </form>
    
  </body>  
</html>