package bean;

public class GoodsClassInfo {
	private int gcid;
	private String gcname;
	
	public GoodsClassInfo(){}
	public GoodsClassInfo(int gcid,String gcname){
		this.gcid = gcid;
		this.gcname = gcname;
	}
	public int getGcid() {
		return gcid;
	}
	public void setGcid(int gcid) {
		this.gcid = gcid;
	}
	public String getGcname() {
		return gcname;
	}
	public void setGcname(String gcname) {
		this.gcname = gcname;
	}
}
