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

	String sql;
	
	private ProductPanel pp;

	public DBDAO() {
		AppManager.createInstance().setDao(this);
		pp = AppManager.createInstance().getProductPanel();
		connectDB();
	}

	void connectDB() {

		try {
			// JDBC 占쏙옙占쏙옙譴占� 占싸듸옙
			Class.forName(jdbcDiver);
			// 占쏙옙占쏙옙占싶븝옙占싱쏙옙 占쏙옙占쏙옙
			conn = DriverManager.getConnection(jdbcUrl, dbID, dbPassword);
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void closeDB() {
		try {
			// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 占쏙옙占� 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌉쇽옙
	public ArrayList<Product> getAllProduct() {
		// 占쏙옙품占쏙옙占쏙옙트 占쏙옙占쏙옙占쏙옙占쏙옙
		sql = "select * from product";

		// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 arraylist
		ArrayList<Product> datas = new ArrayList<Product>();

		try {
			// statement 占쏙옙占쏙옙
			pstmt = conn.prepareStatement(sql);

			// sql 占쏙옙 占쏙옙占쏙옙 占쏙옙 占쌨아울옙占쏙옙
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
	
	//Shim에서 만든겁니다.
	public Vector getAllProductS() {
		// 占쏙옙품占쏙옙占쏙옙트 占쏙옙占쏙옙占쏙옙占쏙옙
		sql = "select * from product";
		
		pp.datas.clear();


		try {
		pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector in = new Vector<String>();
				
				int Code = rs.getInt("procode");
				String Name = rs.getString("proname");
				String Price = rs.getString("proprice");

				String CString = Code + "";
				in.add(CString);
				in.add(Name);
				in.add(Price);
				
				pp.datas.add(in);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return pp.datas;
	}

	public ArrayList<Member> getAllMember() {
		// 회占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙占쏙옙占쏙옙
		sql = "select * from member";

		// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 arraylist
		ArrayList<Member> datas = new ArrayList<Member>();

		try {
			// statement 占쏙옙占쏙옙
			pstmt = conn.prepareStatement(sql);

			// sql 占쏙옙 占쏙옙占쏙옙 占쏙옙 占쌨아울옙占쏙옙
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
		// 占쏙옙占쏙옙 占쏙옙占쏙옙트
		sql = "select * from sale";

		// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 arraylist
		ArrayList<Sale> datas = new ArrayList<Sale>();


		try {
			// statement 占쏙옙占쏙옙
			pstmt = conn.prepareStatement(sql);

			// sql 占쏙옙 占쏙옙占쏙옙 占쏙옙 占쌨아울옙占쏙옙
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

	// 占싹놂옙占쏙옙 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌉쇽옙
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
		System.out.println(p.toString());
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
		System.out.println(m.toString());
		return m;
	}

	public Sale getSale(int salno) {
		sql = "select * from sale where salno = ? ";
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

	// 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占싹댐옙 占쌉쇽옙
	boolean newProduct(Product product) {
		sql = "insert into product(proname,proprice) values(?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProname()); // 1占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
			pstmt.setInt(2, product.getProprice()); // 2占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
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
			pstmt.setString(1, member.getMemphone()); // 1占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
			pstmt.setString(2, member.getMemname()); // 2占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
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
			pstmt.setString(1, sale.getMemphone()); // 1占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
			pstmt.setInt(2, sale.getTotalprice()); // 2占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
			pstmt.setInt(3, sale.getStamp()); // 3占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
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

	// 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占싹댐옙 占쌉쇽옙
	boolean updateProduct(Product product) {
		sql = "update product set proname =?, proprice =? where procode =?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProname()); // 1占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
			pstmt.setInt(2, product.getProprice()); // 2占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
			pstmt.setInt(3, product.getProcode()); // 3占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
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
			pstmt.setString(1, member.getMemphone()); // 1占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
			pstmt.setString(2, member.getMemname()); // 2占쏙옙 ?占쏙옙 占쏙옙占쏙옙 占쌍는댐옙.
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

	// 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占싹댐옙 占쌉쇽옙
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



}
