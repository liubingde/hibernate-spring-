package bean;

public class UserBean {
	private int span = 8;
	private int totalPage = 1;
	private int nowPage = 1;
	private String hql = "";
	private String pageHql = "";
	public int getSpan() {
		return span;
	}
	public void setSpan(int span) {
		this.span = span;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public String getPageHql(){
		return pageHql;
	}
	public void setPageHql(String pageHql){
		this.pageHql = pageHql;
	}
}
