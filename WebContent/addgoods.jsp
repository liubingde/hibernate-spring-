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
     <script type="text/javascript" charset="UTF-8" >
		function mycheck(){
		
			var gname=document.form1.gname.value;//注意form1
			var gcname=document.form1.gcname.value;
			var gunit=document.form1.gunit.value;
			var gpin=document.form1.gpin.value;
			var gpout=document.form1.gpout.value;
			var gamount=document.form1.gamount.value;
			if(gname==""){
				alert("商品名称名为空，请重新输入！！！");
				return false;
				}
			if(gcname==""){
				alert("商品类别为空，请重新输入");
				return false;
				}
			if(gunit==""){
				alert("商品单位为空，请重新输入");
				return false;
				}
			if(gpin==""){
				alert("商品金价为空，请重新输入");
				return false;
				}
			if(gpout==""){
				alert("商品售价为空，请重新输入");
				return false;
				}
			if(gamount==""){
				alert("商品数量为空，请重新输入");
				return false;
				}
			return ture;

			}
		function reset(){
			document.form1.gname.value="";
			document.form1.gcname.value="";
			document.form1.gunit.value="";
			document.form1.gpin.value="";
			document.form1.gpout.value="";
			document.form1.gamount.value="";
			
			}
   	</script>
  
  </head>  
    
  <body>  
      <h1 align="center">商品添加界面</h1>
    	<form action="ManageServlet" name="form1">
    		<table width="100%" border="0" cellspacing="1" bgcolor="black">
    			 <tr bgcolor="white">
				    <td align="center">商品名称:</td>
				    <td><input size="20" name="gname" id="gname"/></td>
			 	 </tr>
				  <tr bgcolor="white">
				  	<td align="center">商品类别:</td>
				  	<td>
				      	  <select name="gcname" id="gcname">
					      	  <% 
								//获取WebApplicationContext
								WebApplicationContext wac=
								   WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
								DButil db = (DButil)wac.getBean("dbutil");
								List<String> gcname = db.getGoodsClass();
								for(String name:gcname){
									name = new String(name.getBytes("ISO-8859-1"),"gbk");
					      	    %>
					      	    	<option value="<%= name %>"><%= name %></option>
					      	    <% 
					      	    	}
					      	     %>
		      	  		</select>
		  			</td>
		 		 </tr>
				  <tr bgcolor="white">
				  	<td align="center">计量单位:</td>
				  	<td><input name="gunit" id="gunit"/></td>
				  </tr>
				  <tr bgcolor="white">
				  	<td align="center">进&nbsp;&nbsp;&nbsp;&nbsp;价:</td>
				  	<td><input name="gpin" id="gpin"/></td>
				  </tr>
				  <tr bgcolor="white">
				  	<td align="center">售&nbsp;&nbsp;&nbsp;&nbsp;价:</td>
				  	<td><input name="gpout" id="gpout"/></td>
				  </tr>
				  <tr bgcolor="white">
				  	<td align="center">商品数量:</td>
				  	<td><input name="gamount" id="gamount"/></td>
				  </tr>	
				 
    		</table>
    		 <tr bgcolor="white"> 
				  	<td><input type="submit" name="action" value="增添" onClick="mycheck()"> </td>
				  	<td><input type="reset"></td>
			</tr>
    	</form>
  </body>  
</html>