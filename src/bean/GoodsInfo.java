package bean;

public class GoodsInfo {
	private int gid;
	private String gname;
	private int gcid;
	private String gunit;
	private String gpin;
	private String gpout;
	private String gamount;
	public GoodsInfo(){}
	public GoodsInfo(String gname,int gcid,
		String gunit,String gpin,String gpout,String gamount){
		this.gname = gname;
		this.gcid = gcid;
		this.gunit = gunit;
		this.gpin = gpin;
		this.gpout = gpout;
		this.gamount = gamount;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getGcid() {
		return gcid;
	}
	public void setGcid(int gcid) {
		this.gcid = gcid;
	}
	public String getGunit() {
		return gunit;
	}
	public void setGunit(String gunit) {
		this.gunit = gunit;
	}
	public String getGpin() {
		return gpin;
	}
	public void setGpin(String gpin) {
		this.gpin = gpin;
	}
	public String getGout() {
		return gpout;
	}
	public void setGout(String gpout) {
		this.gpout = gpout;
	}
	public String getGamount() {
		return gamount;
	}
	public void setGamount(String gamount) {
		this.gamount = gamount;
	}
	public String toString(){
		return this.gname;
	}	
}
