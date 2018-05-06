package dao;



import antlr.collections.List;
import bean.GoodsClassInfo;
import bean.GoodsInfo;

import java.util.ArrayList;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
public class DButil {
	private SessionFactory sf;
	public SessionFactory getSf() {
		return this.sf;
	}
	public void setSf(SessionFactory sf){
		this.sf = sf;
	}
	public java.util.List<?> getInfo(String hql) {

		Session session=sf.openSession();
		Query query=session.createQuery(hql);
		java.util.List<?> list=query.list();
		session.close();
		return list;
	}
	public int getTotalPage(String hql,int span){				//用来得到总页数
		Session sess = sf.openSession();						//创建会话
		Query q = sess.createQuery(hql);						//执行查询
		java.util.List<Long>list = q.list();								//得到结果列表
		int count = (list.get(0)).intValue();					//得到总记录条数
		int page = (count%span==0)?(count/span):(count/span+1);	//得到总页数
		sess.close();
		return page;											//将总页数返回
	}
	public java.util.List<GoodsInfo> getPageContent(String hql,int page,int span) {
		ArrayList temp = new ArrayList();							//创建list,用来存放页面内容
		Session sess = sf.openSession();						//创建会话
		Query q = sess.createQuery(hql);						//执行查询
		java.util.List<GoodsInfo> list = q.list();	
		int i=0;
		while((page-1)*span+i<list.size()&&i<span){							
			temp.add(list.get((page-1)*span+i));				//将结果添加到temp中
			i++;												//标志位自加
		}
		sess.close();
		return temp;
	}
	public Object getObject(String tablename,int id){
		Session sess = sf.openSession();						//创建会话
		Object obj = null;										//声明引用
		if(tablename.equals("GoodsInfo")){						//当得到商品对象时
			obj = sess.get(GoodsInfo.class,id);					//得到对象			
		}
		else if(tablename.equals("GoodsClassInfo")){			//当得到类别对象时
			obj = sess.get(GoodsClassInfo.class,id);			//得到对象
		}

		sess.close();											//关闭会话
		return obj;												//返回对象
	}
	public java.util.List<String> getGoodsClass(){
		Session sess = sf.openSession();						//得到session对象
		String hql = "select gcname from GoodsClassInfo";		//得到所有的类名的hql
		Query q = sess.createQuery(hql);						//执行查询
		java.util.List<String> name = q.list();							//得到列表
		sess.close();
		return name;											//将结果返回
	}
	public String getId(String tablename,String columnname){
		Session sess = sf.openSession();						//创建会话
		String hql = "select Max("+columnname+") from "+tablename;
		Query q = sess.createQuery(hql);						//进行查询
		java.util.List<String> result = q.list();							//得到结果列表
		if(result.get(0)==null){								//当表中没有记录进
			return "10001";
		}
		int id = Integer.parseInt(result.get(0));				//将id转化为int型
		id++;													//将id自加
		sess.close();
		return Integer.valueOf(id).toString();					//返回id
	}
}
