package pos_coffee;

public class Member {
	private int memno;
	private String memphone = "00000000000";
	private String memname;
	private int memstamp = 0;
	
	public Member() {
		
	}
	public Member(int memno, String memphone, String memname, int memstamp) {
		this.memno = memno;
		this.memphone = memphone;
		this.memname = memname;
		this.memstamp = memstamp;
	}
	public int getMemno() {
		return memno;
	}
	public void setMemno(int memno) {
		this.memno = memno;
	}
	public String getMemphone() {
		return memphone;
	}
	public void setMemphone(String memphone) {
		this.memphone = memphone;
	}
	public String getMemname() {
		return memname;
	}
	public void setMemname(String memname) {
		this.memname = memname;
	}
	public int getMemstamp() {
		return memstamp;
	}
	public void setMemstamp(int memstamp) {
		this.memstamp = memstamp;
	}
	
	public String toString() {
		return memno + " "+ memphone + " "+ memname + " "+ memstamp;
	}
}

