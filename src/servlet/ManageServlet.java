package servlet;



import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bean.AdminInfo;
import bean.GoodsClassInfo;
import bean.GoodsInfo;
import dao.DBdelete;
import dao.DBinsert;
import dao.DBupdate;
import dao.DButil;
import bean.UserBean;



public class ManageServlet extends HttpServlet {
	private List<AdminInfo> adminlist;
	private List<GoodsInfo> goodslist;
	SessionFactory sf;
	private UserBean userBean;
	private DButil dButil;
	private String url="";
	private String hql="";
	private String hql2="";
	String info="";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�����ַ���
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//��ȡ��������
		String action=req.getParameter("action");
		//���spring������
		WebApplicationContext webApplicationContext=WebApplicationContextUtils.getWebApplicationContext(
				this.getServletContext());
		//��ȡdButil
		dButil=(DButil) webApplicationContext.getBean("dbutil");
		//��ȡuserbean
		 userBean=(UserBean)webApplicationContext.getBean("userbean");
		//����session����
		Configuration cfg = new Configuration(); 
		 sf=cfg.configure().buildSessionFactory();
		if(action.equals("��½")) {
			//��ȡ�û��������룬��ת���ַ�
			String name=req.getParameter("username");
			String password=req.getParameter("password");
			name=new String(name.getBytes(),"ISO-8859-1");
			password=new String(password.getBytes(), "ISO-8859-1");
			//��ѯ�û���hql���
			hql = "from AdminInfo as p "+				
					"where p.aname='"+name+"' and p.apwd='"+password+"'";
			//����dbutil�ķ������в�ѯ
			adminlist = (List<AdminInfo>)dButil.getInfo(hql);
			//���adminlist��Ϊ�գ�˵�������ݿ����ҵ��˼�¼
			if (!adminlist.isEmpty()) {
				url="/index.jsp";
				//���õ�ǰҳ��Ϊ��һҳ
				userBean.setNowPage(1);
				//��ѯ���еĻ�����Ϣ������
				hql ="from GoodsInfo as p where p.gname like '%"+"%'";
				hql2 = "select count(*) from GoodsInfo where gname like '%"+"%'";
				userBean.setHql(hql);
				userBean.setPageHql(hql2);
				//��ȡһ����Ҫ��ҳ
				int totalpage=dButil.getTotalPage(hql2, userBean.getSpan());
				userBean.setTotalPage(totalpage);
				//��ȡ������Ϣ
				 goodslist=dButil.getPageContent(hql, userBean.getNowPage(), userBean.getSpan());
				 HttpSession session=req.getSession();
					session.setAttribute("goodslist",goodslist);
			} else {
				url="/fail.jsp";
			}
			ServletContext servletContext=getServletContext();
			RequestDispatcher requestDispatcher=servletContext.getRequestDispatcher(url);
			requestDispatcher.forward(req, resp);
			
		}
		else if (action.equals("��ѯ")) {
			//��ȡ��ѯ�ؼ��ֺͲ�ѯ����
			String key=req.getParameter("key");
			String type=req.getParameter("type");
			key = new String(key.getBytes(),"ISO-8859-1");
			type=new String(type.getBytes(),"ISO-8859-1");
			url = "/index.jsp";
			UserBean userBean=(UserBean)webApplicationContext.getBean("userbean");
			userBean.setNowPage(1);
			if(type.equals("name")) {
				hql ="from GoodsInfo as p where p.gname like '%"+key+"%'";
				hql2 = "select count(*) from GoodsInfo where gname like '%"+key+"%'";
				url = "/goodsmanage.jsp";
			}
			else if (type.equals("class")) {
				hql ="from GoodsInfo as p where p.gcid in"
						+ "(select gcid from GoodsClassInfo where gcname like '%"+key+"%')";
				hql2 = "select count(*) from GoodsInfo  as p where p.gcid in"
						+ "(select gcid from GoodsClassInfo where gcname like '%"+key+"%')";
				url = "/goodsmanage.jsp";
			}
			userBean.setHql(hql);
			userBean.setPageHql(hql2);
			int totalpage=dButil.getTotalPage(hql2, userBean.getSpan());
			userBean.setTotalPage(totalpage);
			goodslist=dButil.getPageContent(hql, userBean.getNowPage(), userBean.getSpan());
			HttpSession session=req.getSession();
			session.setAttribute("goodslist",goodslist);
			ServletContext servletContext=getServletContext();
			RequestDispatcher requestDispatcher=servletContext.getRequestDispatcher(url);
			requestDispatcher.forward(req, resp);
		}
		else if(action.equals("����")) {
			
			String gname=req.getParameter("gname");
			String gcname=req.getParameter("gcname");
			String gunit=req.getParameter("gunit");
			String gpin=req.getParameter("gpin");
			String gpout=req.getParameter("gpin");
			String gamount=req.getParameter("gamount");
			//String gid = dButil.getId("GoodsInfo","gid");
			hql = "select gg.gcid from GoodsClassInfo as gg where gg.gcname='"+gcname+"'";
			hql2="from GoodsInfo as gi where gi.gname='"+gname+"'";
			int gcid=(int)( dButil.getInfo(hql).get(0));
			List list=dButil.getInfo(hql2);
			if(list.isEmpty()) {
				GoodsInfo goodsInfo=new GoodsInfo( gname, gcid, gunit, gpin, gpout, gamount);
				//System.out.println(gid);
				Session sess = sf.openSession();	
				Transaction tx = sess.beginTransaction();//�����Ự
				
				try{
					sess.save(goodsInfo);
				    tx.commit();
				    @SuppressWarnings("unchecked")
					List<GoodsInfo> goodslist =(List<GoodsInfo>)req.getSession().getAttribute("goodslist");
				    goodslist.add(goodsInfo);
				    req.getSession().setAttribute("goodslist",goodslist);
				}catch(Exception ex){
					System.err.println(ex);
				    tx.rollback();
				}finally{
				    sess.close();
				}
				info="��ӳɹ�";
				
			}
			else {
				info="���ʧ��";
			}
			url = "/addgoods.jsp";
			req.setAttribute("info", info);
			ServletContext sc = getServletContext();				//�õ������� 
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(req,resp);	
		}
		else if(action.equals("logout")) {
			req.getSession(true).invalidate();
			resp.sendRedirect("adminlogin.jsp");
		}
		else if(action.equals("agc")) {
			String name=req.getParameter("gcname");
			GoodsClassInfo goodsClassInfo=new GoodsClassInfo();
			goodsClassInfo.setGcname(name);
			Session sess = sf.openSession();
			Transaction tx = sess.beginTransaction();//�����Ự
			try{
				sess.save(goodsClassInfo);
			    tx.commit();
			 
			}catch(Exception ex){
			    tx.rollback();
			}finally{
			    sess.close();
			}
			url="/addgoodsclass.jsp";
			ServletContext sc = getServletContext();				//�õ������� 
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(req,resp);
		}
		else if(action.equals("admn")) {
			String name=req.getParameter("aname");
			String pwd=req.getParameter("apwd");
			String level=req.getParameter("alevel");
			AdminInfo adminInfo=new AdminInfo();
			adminInfo.setAname(name);
			adminInfo.setApwd(pwd);
			adminInfo.setAlevel(Integer.parseInt(level));
			Session sess = sf.openSession();
			Transaction tx = sess.beginTransaction();//�����Ự
			try {
				sess.save(adminInfo);
				tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				tx.rollback();
			}
			finally {
				sess.close();
			}
			url="/adminmanage.jsp";
			ServletContext sc = getServletContext();				//�õ������� 
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(req,resp);
		}
		
	}

}
