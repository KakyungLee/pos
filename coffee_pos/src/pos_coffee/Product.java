package pos_coffee;

public class Product {
	private int procode = 0;
	private String proname = null;
	private int proprice = 0;
	
	public Product() {
		
	}
	public Product(int procode, String proname, int proprice) {
		this.procode = procode;
		this.proname = proname;
		this.proprice = proprice;
	}
	
	public int getProcode() {
		return procode;
	}
	public void setProcode(int procode) {
		this.procode = procode;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public int getProprice() {
		return proprice;
	}
	public void setProprice(int proprice) {
		this.proprice = proprice;
	}
	public String toString() {
		return procode + " "+ proname + " "+ proprice;
	}
}
