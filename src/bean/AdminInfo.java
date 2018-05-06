package bean;

public class AdminInfo {
	private int aid;
	private String aname;
	private String apwd;
	private int alevel;
	public AdminInfo() {
		
	}
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getApwd() {
		return apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

	public int getAlevel() {
		return alevel;
	}

	public void setAlevel(int alevel) {
		this.alevel = alevel;
	}
	public String toString(){
		return this.aname+"/t"+this.apwd;
	}
}
