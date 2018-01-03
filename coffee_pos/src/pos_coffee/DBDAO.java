package pos_coffee;

import java.sql.*;
import java.util.*;

public class DBDAO {
	String jdbcDiver = "com.mysql.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://127.0.0.1/coffee_teamproject";
	String dbID = "team_user";
	String dbPassword = "coffee";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	Vector<String> salItems;

	String sql;

	private ProductPanel pp;

	public DBDAO() {
		AppManager.createInstance().setDao(this);
		pp = AppManager.createInstance().getProductPanel();
		connectDB();
	}

	void connectDB() {

		try {

			// JDBC �뱶�씪�씠踰� 濡쒕뱶
			Class.forName(jdbcDiver);
			// �뜲�씠�꽣踰좎씠�뒪 �뿰寃�
			conn = DriverManager.getConnection(jdbcUrl, dbID, dbPassword);
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void closeDB() {
		try {

			// �닚李⑥쟻 �뿰寃� �빐�젣
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 紐⑤뱺 �뜲�씠�꽣瑜� 媛��졇�삤�뒗 �븿�닔
	public ArrayList<Product> getAllProduct() {
		// �긽�뭹由ъ뒪�듃 媛��졇�삤湲�
		sql = "select * from product";

		// �뜲�씠�꽣 �쟾�떖 arrayList
		ArrayList<Product> datas = new ArrayList<Product>();

		try {
			// statement �깮�꽦
			pstmt = conn.prepareStatement(sql);

			// sql 臾� �쟾�넚 諛� 諛쏆븘�삤湲�
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Product p = new Product();
				p.setProcode(rs.getInt("procode"));
				p.setProname(rs.getString("proname"));
				p.setProprice(rs.getInt("proprice"));
				datas.add(p);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return datas;
	}

	public ArrayList<Member> getAllMember() {
		// �쉶�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
		sql = "select * from member";

		// �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 arraylist
		ArrayList<Member> datas = new ArrayList<Member>();

		try {
			// statement �뜝�룞�삕�뜝�룞�삕
			pstmt = conn.prepareStatement(sql);

			// sql �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�뙣�븘�슱�삕�뜝�룞�삕
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Member m = new Member();
				m.setMemno(rs.getInt("memno"));
				m.setMemphone(rs.getString("memphone"));
				m.setMemname(rs.getString("memname"));
				m.setMemstamp(rs.getInt("memstamp"));
				datas.add(m);
				System.out.println(m.toString());
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return datas;
	}

	public ArrayList<Sale> getAllSale() {
		sql = "select date_format(saldate,'%Y-%m-%d') as saldate ,salno, memphone,totalprice,stamp from sale";
		// �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 arraylist
		ArrayList<Sale> datas = new ArrayList<Sale>();

		salItems = new Vector<String>();
		salItems.add("�궇吏�");

		try {
			// statement �뜝�룞�삕�뜝�룞�삕
			pstmt = conn.prepareStatement(sql);

			// sql �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�뙣�븘�슱�삕�뜝�룞�삕
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Sale s = new Sale();
				s.setSalno(rs.getInt("salno"));
				s.setSaldate(rs.getString("saldate"));
				s.setMemphone(rs.getString("memphone"));
				s.setTotalprice(rs.getInt("totalprice"));
				s.setStamp(rs.getInt("stamp"));
				datas.add(s);
				if (salItems.lastElement().equals(s.getSaldate())) {

				} else {
					salItems.add(s.getSaldate());
				}

			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return datas;
	}

	// �뜝�떦�냲�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떢紐뚯삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�뙃�눦�삕
	public Product getProduct(int procode) {
		sql = "select * from product where procode = ?";
		Product p = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, procode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				p = new Product();
				p.setProcode(rs.getInt("procode"));
				p.setProname(rs.getString("proname"));
				p.setProprice(rs.getInt("proprice"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return p;
	}

	public Member getMember(int memno) {
		sql = "select * from member where memno = ?";
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				m = new Member();
				m.setMemno(rs.getInt("memno"));
				m.setMemphone(rs.getString("memphone"));
				m.setMemname(rs.getString("memname"));
				m.setMemstamp(rs.getInt("memstamp"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return m;
	}

	/*
	 * Shim Soo
	 * MainPanel에서 회원의 휴대번호를 입력해서 회원 정보를 얻어야 하는 것이 필요 
	 */
	public Member getMemberPhone(String phone) {
		sql = "select * from member where memphone = ?";
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				m = new Member();
				m.setMemno(rs.getInt("memno"));
				m.setMemphone(rs.getString("memphone"));
				m.setMemname(rs.getString("memname"));
				m.setMemstamp(rs.getInt("memstamp"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return m;
	}

	public Sale getSale(int salno) {
		sql = "select date_format(saldate,'%Y-%m-%d'),salno, memphone,totalprice,stamp from sale where salno ?";

		Sale s = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, salno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				s = new Sale();
				s.setSalno(rs.getInt("salno"));
				s.setSaldate(rs.getString("saldate"));
				s.setMemphone(rs.getString("memphone"));
				s.setTotalprice(rs.getInt("totalprice"));
				s.setStamp(rs.getInt("stamp"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		System.out.println(s.toString());
		return s;
	}

	public ArrayList<Sale> getAllSaleWhereDate(String date) {
		sql = "select date_format(saldate,'%Y-%m-%d') as saldate ,salno, memphone,totalprice,stamp from sale where date(saldate) = ?";

		// �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 arraylist
		ArrayList<Sale> datas = new ArrayList<Sale>();

		try {
			// statement �뜝�룞�삕�뜝�룞�삕
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			// sql �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�뙣�븘�슱�삕�뜝�룞�삕
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Sale s = new Sale();
				s.setSalno(rs.getInt("salno"));
				s.setSaldate(rs.getString("saldate"));
				s.setMemphone(rs.getString("memphone"));
				s.setTotalprice(rs.getInt("totalprice"));
				s.setStamp(rs.getInt("stamp"));
				datas.add(s);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return datas;
	}

	// �뜝�룞�삕�뜝�룞�삕�뜝�떢紐뚯삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕 �뜝�뙃�눦�삕
	boolean newProduct(Product product) {
		sql = "insert into product(proname,proprice) values(?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProname()); // 1�뜝�룞�삕 ?�뜝�룞�삕
														// �뜝�룞�삕�뜝�룞�삕
														// �뜝�뙇�뒗�뙋�삕.
			pstmt.setInt(2, product.getProprice()); // 2�뜝�룞�삕 ?�뜝�룞�삕
													// �뜝�룞�삕�뜝�룞�삕 �뜝�뙇�뒗�뙋�삕.
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	boolean newMember(Member member) {
		sql = "insert into member(memphone,memname) values(?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemphone()); // 1�뜝�룞�삕 ?�뜝�룞�삕
														// �뜝�룞�삕�뜝�룞�삕
														// �뜝�뙇�뒗�뙋�삕.
			pstmt.setString(2, member.getMemname()); // 2�뜝�룞�삕 ?�뜝�룞�삕
														// �뜝�룞�삕�뜝�룞�삕
														// �뜝�뙇�뒗�뙋�삕.
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	boolean newSale(Sale sale) {
		sql = "insert into sale(memphone,totalprice,stamp) values(?,?,?)";
		int result = 0;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sale.getMemphone()); // 1�뜝�룞�삕 ?�뜝�룞�삕
													// �뜝�룞�삕�뜝�룞�삕 �뜝�뙇�뒗�뙋�삕.
			pstmt.setInt(2, sale.getTotalprice()); // 2�뜝�룞�삕 ?�뜝�룞�삕
													// �뜝�룞�삕�뜝�룞�삕 �뜝�뙇�뒗�뙋�삕.
			pstmt.setInt(3, sale.getStamp()); // 3�뜝�룞�삕 ?�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
												// �뜝�뙇�뒗�뙋�삕.
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	// �뜝�룞�삕�뜝�룞�삕�뜝�떢紐뚯삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕 �뜝�뙃�눦�삕
	boolean updateProduct(Product product) {
		sql = "update product set proname =?, proprice =? where procode =?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProname()); // 1�뜝�룞�삕 ?�뜝�룞�삕
														// �뜝�룞�삕�뜝�룞�삕
														// �뜝�뙇�뒗�뙋�삕.
			pstmt.setInt(2, product.getProprice()); // 2�뜝�룞�삕 ?�뜝�룞�삕
													// �뜝�룞�삕�뜝�룞�삕 �뜝�뙇�뒗�뙋�삕.
			pstmt.setInt(3, product.getProcode()); // 3�뜝�룞�삕 ?�뜝�룞�삕
													// �뜝�룞�삕�뜝�룞�삕 �뜝�뙇�뒗�뙋�삕.
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	boolean updateMember(Member member) {
		sql = "update member set memphone =?, memname =?, memstamp =? where memno =?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemphone()); // 1�뜝�룞�삕 ?�뜝�룞�삕
														// �뜝�룞�삕�뜝�룞�삕
														// �뜝�뙇�뒗�뙋�삕.
			pstmt.setString(2, member.getMemname()); // 2�뜝�룞�삕 ?�뜝�룞�삕
														// �뜝�룞�삕�뜝�룞�삕
														// �뜝�뙇�뒗�뙋�삕.
			pstmt.setInt(3, member.getMemstamp());
			pstmt.setInt(4, member.getMemno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	// �뜝�룞�삕�뜝�룞�삕�뜝�떢紐뚯삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕 �뜝�뙃�눦�삕
	boolean delProduct(int procode) {
		sql = "delete from product where procode = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, procode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	boolean delMember(int memno) {
		sql = "delete from member where memno = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	boolean delSale(int salno) {
		sql = "delete from sale where salno = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, salno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	Vector<String> getSalItems() {
		return salItems;
	}

}
