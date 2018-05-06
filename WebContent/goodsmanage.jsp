<%@ page language="java" import="java.util.*,org.springframework.web.context.*,
    org.springframework.web.context.support.*,bean.GoodsInfo,dao.DButil,bean.UserBean,bean.GoodsClassInfo" pageEncoding="UTF-8"%>      
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  

<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>资料查询</title>  
      <% 

	//List<GoodsInfo> goodslist = (List<GoodsInfo>)request.getAttribute("goodslist");
 	List<GoodsInfo> goodslist =(List<GoodsInfo>)session.getAttribute("goodslist");
 	if(goodslist==null){
 		
 		System.out.println("fail");
 	}
 %> 
  </head>  
    
  <body>  
  <jsp:useBean id="userBean" class="bean.UserBean" scope="session"/>
 
      <table>
      	<form action="ManageServlet" method="post" id="smf">
      		<tr> 
      			<td>
      				<table height="42" >
      					<tr>
      						<td>
      							<input name="key" id="key" value="请输入要搜索的商品名称 " style="border: 0" size="28" >
      						</td>
      						<td width="86" align="right">
      							<input type="submit" name="action" value="查询">
      						</td>
      						<td width="80" align="center">
					 		      <input type="radio" name="type" value="name" checked="true"><font size="2" >按名称</font>
						     </td>
						     <td width="80">
						     	  <input type="radio" name="type" value="class"><font size="2" >按类别</font>
						    </td>
      						<td width="80">
					    	 	 <a href="addgoods.jsp" target="mainFrame"><font size="2">添加商品</font></a>
					    	</td> 
					    	<td width="80">
					    	 	 <a href="addgoodsclass.jsp" target="mainFrame"><font size="2">添加商品类型</font></a>
					    	</td> 
      					</tr>
      				</table>
      			</td>
      		</tr>
      	</form>
      </table>
      
      
    	<hr size="1" width="100%" color="black"/>
    	<% 
		if(goodslist.isEmpty()){
			out.println("<br/><br/><br/><center><h2>没有搜索到你要的商品!!!</h2></center>");
		}
		else{
	 %>
	 
	 
    <table width="100%" border="0" cellspacing="1" bgcolor="black">
	  <tr bgcolor="#D1F1FE" align="center">
	    <th>商品名称</th>
	    <th>类别</th>
	    <th>进价</th>
	  	<th>售价</th>
	  	<th>单位</th>
	  	<th>数量</th>
	  	<th>查看/修改</th>
	  	<th>删除</th>
	  </tr>
	  <%
		//获取WebApplicationContext
		WebApplicationContext wac=
		   WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		DButil db = (DButil)wac.getBean("dbutil");
		int i = 0;
		for(GoodsInfo gi:goodslist){
		String gname = gi.getGname();
		int gcid = gi.getGcid();
		GoodsClassInfo gci = (GoodsClassInfo)db.getObject("GoodsClassInfo",gcid);
		String gamount = gi.getGamount();
		String gunit = gi.getGunit();
		String gpin = gi.getGpin();
		String gpout = gi.getGout();
	
		if(i%2==0){
			i++;
			out.println("<tr bgcolor='white' align='center'>");
		}
		else{
			i++;
			out.println("<tr bgcolor='#EBF5FD' align='center'>");
		}
	 %>
	   <td><%= new String(gname.getBytes("ISO-8859-1"),"gbk") %></td>
	    <td><%=new String(gci.getGcname().getBytes("ISO-8859-1"),"gbk") %>
	    <td>￥<%= gpin %></td>
	    <td>￥<%= gpout %></td>
	    <td><%= new String(gunit.getBytes("ISO-8859-1"),"gbk") %></td>
	    <td><%= gamount %></td>
	    <td width="120"><a href="ManageServlet?action=lookGoods&gid=<%= gi.getGid() %>" target="mainFrame"><img border="0" src="img/mod.gif" height="16" width="16"/>查看/修改</a></td>
	    
	  </tr>
	<%
		}
	 %>
	  </table>
	  
	  <table width="100%">
	<form method="post" id="mf" action="ManageServlet">
	  <tr>
	    <td align="left">
	      <font size="2">共<%= userBean.getTotalPage() %>页&nbsp;&nbsp;当前页:<%= userBean.getNowPage() %></font>
	    </td>
	    <td align="right">
	      <% 
	      	if(userBean.getNowPage()>1){
	       %>
	      <a href="ManageServlet?action=changePage&pagename=/goodsmanage.jsp&page=<%= userBean.getNowPage()-1 %>" target="mainFrame"><img src="img/prev.gif" border="0"/></a>
	      <% 
	      	}
	      	if(userBean.getNowPage()<userBean.getTotalPage()){
	       %>	       
	      <a href="ManageServlet?action=changePage&pagename=/goodsmanage.jsp&page=<%= userBean.getNowPage()+1 %>" target="mainFrame"><img src="img/next.gif" border="0"/></a>
	      <% 
	      	}
	      	else{
	      		out.println("<img src='img/next.gif' style='visibility:hidden'/>");
	      	}
	       %>
	      <font size="2">第<input name="page" id="page" value="<%= userBean.getNowPage() %>" size="2" onfocus="document.all.page.value=''"/>页</font>
	      <input type="hidden" name="action" value="changePage" />
	      <input type="hidden" name="pagename" value="/goodsmanage.jsp"/>	    
	    </td>
	    <td width="10">
	      <img src="img/go.gif" border="0" style="cursor:hand" onclick="JavaScript:checkPage(<%= userBean.getTotalPage() %>)">
	    </td>
	  </tr>	
	</form>
	</table>
	  <%
		}
	 %>
  </body>  
</html>