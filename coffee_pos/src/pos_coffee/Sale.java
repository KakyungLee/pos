package pos_coffee;

public class Sale {
	String saldate;
	int salno;
	String memphone;
	int totalprice;
	int stamp;
	
	public Sale() {
		
	}
	public Sale(String saldate, int salno, String memphone, int totalprice, int stamp) {
		this.saldate = saldate;
		this.salno = salno;
		this.memphone = memphone;
		this.totalprice = totalprice;
		this.stamp = stamp;
	}
	public String getSaldate() {
		return saldate;
	}
	public void setSaldate(String saldate) {
		this.saldate = saldate;
	}
	public int getSalno() {
		return salno;
	}
	public void setSalno(int salno) {
		this.salno = salno;
	}
	public String getMemphone() {
		return memphone;
	}
	public void setMemphone(String memphone) {
		this.memphone = memphone;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public int getStamp() {
		return stamp;
	}
	public void setStamp(int stamp) {
		this.stamp = stamp;
	}
	public String toString() {
		return saldate + " "+ salno +" "+memphone+" "+" "+totalprice+" "+stamp; 
	}
}
