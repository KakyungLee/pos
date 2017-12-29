package pos_coffee;

public class AppManager {

	
	public static void main(String[] args) {
		//AppManager app = new AppManager();
		DBDAO dao = new DBDAO();
		dao.getProduct(103);
		dao.getMember(1);
		dao.getSale(4);
		System.out.println("Test");

	}// main
}
