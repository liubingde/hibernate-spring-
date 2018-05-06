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
	public int getTotalPage(String hql,int span){				//�����õ���ҳ��
		Session sess = sf.openSession();						//�����Ự
		Query q = sess.createQuery(hql);						//ִ�в�ѯ
		java.util.List<Long>list = q.list();								//�õ�����б�
		int count = (list.get(0)).intValue();					//�õ��ܼ�¼����
		int page = (count%span==0)?(count/span):(count/span+1);	//�õ���ҳ��
		sess.close();
		return page;											//����ҳ������
	}
	public java.util.List<GoodsInfo> getPageContent(String hql,int page,int span) {
		ArrayList temp = new ArrayList();							//����list,�������ҳ������
		Session sess = sf.openSession();						//�����Ự
		Query q = sess.createQuery(hql);						//ִ�в�ѯ
		java.util.List<GoodsInfo> list = q.list();	
		int i=0;
		while((page-1)*span+i<list.size()&&i<span){							
			temp.add(list.get((page-1)*span+i));				//�������ӵ�temp��
			i++;												//��־λ�Լ�
		}
		sess.close();
		return temp;
	}
	public Object getObject(String tablename,int id){
		Session sess = sf.openSession();						//�����Ự
		Object obj = null;										//��������
		if(tablename.equals("GoodsInfo")){						//���õ���Ʒ����ʱ
			obj = sess.get(GoodsInfo.class,id);					//�õ�����			
		}
		else if(tablename.equals("GoodsClassInfo")){			//���õ�������ʱ
			obj = sess.get(GoodsClassInfo.class,id);			//�õ�����
		}

		sess.close();											//�رջỰ
		return obj;												//���ض���
	}
	public java.util.List<String> getGoodsClass(){
		Session sess = sf.openSession();						//�õ�session����
		String hql = "select gcname from GoodsClassInfo";		//�õ����е�������hql
		Query q = sess.createQuery(hql);						//ִ�в�ѯ
		java.util.List<String> name = q.list();							//�õ��б�
		sess.close();
		return name;											//���������
	}
	public String getId(String tablename,String columnname){
		Session sess = sf.openSession();						//�����Ự
		String hql = "select Max("+columnname+") from "+tablename;
		Query q = sess.createQuery(hql);						//���в�ѯ
		java.util.List<String> result = q.list();							//�õ�����б�
		if(result.get(0)==null){								//������û�м�¼��
			return "10001";
		}
		int id = Integer.parseInt(result.get(0));				//��idת��Ϊint��
		id++;													//��id�Լ�
		sess.close();
		return Integer.valueOf(id).toString();					//����id
	}
}
