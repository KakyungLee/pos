package pos_coffee;

import java.sql.*;
import java.util.*;

public class DBDAO {
	String jdbcDiver = "com.mysql.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://192.168.0.10/coffee_teamproject";
	String dbID = "team_user";
	String dbPassword = "coffee";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	Vector<String> proItems; // 상품 콤보박스
	Vector<String> memItems; // 사용자 콤보박스
	Vector<String> salItems; // 판매 콤보작스

	String sql;
	
	public DBDAO() {
		AppManager.createInstance().setDao(this);
		connectDB();
	}
	
	void connectDB() {

		try {
			// JDBC 드라이버 로드
			Class.forName(jdbcDiver);
			// 데이터베이스 연결
			conn = DriverManager.getConnection(jdbcUrl, dbID, dbPassword);
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void closeDB() {
		try {
			// 순차적 연결 해제
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// 모든 데이터를 가져오는 함수
	public ArrayList<Product> getAllProduct() {
		// 상품리스트 가져오기
		sql = "select * from product";

		// 데이터 전달 arraylist
		ArrayList<Product> datas = new ArrayList<Product>();

		proItems = new Vector<String>();
		proItems.add("전체");

		try {
			// statement 생성
			pstmt = conn.prepareStatement(sql);

			// sql 문 전송 및 받아오기
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Product p = new Product();
				p.setProcode(rs.getInt("procode"));
				p.setProname(rs.getString("proname"));
				p.setProprice(rs.getInt("proprice"));
				datas.add(p);
				System.out.println(p.toString());
				proItems.add(p.getProcode() + "");
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return datas;
	}

	public ArrayList<Member> getAllMember() {
		// 회원 리스트 가져오기
		sql = "select * from member";

		// 데이터 전달 arraylist
		ArrayList<Member> datas = new ArrayList<Member>();

		memItems = new Vector<String>();
		memItems.add("전체");

		try {
			// statement 생성
			pstmt = conn.prepareStatement(sql);

			// sql 문 전송 및 받아오기
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Member m = new Member();
				m.setMemno(rs.getInt("memno"));
				m.setMemphone(rs.getString("memphone"));
				m.setMemname(rs.getString("memname"));
				m.setMemstamp(rs.getInt("memstamp"));
				datas.add(m);
				System.out.println(m.toString());
				memItems.add(m.getMemno() + "");
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return datas;
	}

	public ArrayList<Sale> getAllSale() {
		// 매출 리스트
		sql = "select * from sale";

		// 데이터 전달 arraylist
		ArrayList<Sale> datas = new ArrayList<Sale>();

		salItems = new Vector<String>();
		salItems.add("전체");

		try {
			// statement 생성
			pstmt = conn.prepareStatement(sql);

			// sql 문 전송 및 받아오기
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Sale s = new Sale();
				s.setSalno(rs.getInt("salno"));
				s.setSaldate(rs.getString("saldate"));
				s.setMemphone(rs.getString("memphone"));
				s.setTotalprice(rs.getInt("totalprice"));
				s.setStamp(rs.getInt("stamp"));
				datas.add(s);
				System.out.println(s.toString());
				salItems.add(s.getSalno() + "");
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return datas;
	}

	// 하나의 데이터를 가져오는 함수
	public Product getProduct(int procode) {
		sql = "select * from product where procode = ?";
		Product p = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, procode);
			rs = pstmt.executeQuery();

			if (rs.next()){
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

			if (rs.next()){
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

			if (rs.next()){
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

	// 데이터를 삽입하는 함수
	boolean newProduct(Product product) {
		sql = "insert into product(proname,proprice) values(?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProname());  // 1번 ?에 값을 넣는다.
			pstmt.setInt(2, product.getProprice()); // 2번 ?에 값을 넣는다.
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	boolean newMember(Member member) {
		sql = "insert into member(memphone,memname) values(?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemphone());  // 1번 ?에 값을 넣는다.
			pstmt.setString(2, member.getMemname()); // 2번 ?에 값을 넣는다.
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	boolean newSale(Sale sale) {
		sql = "insert into sale(memphone,totalprice,stamp) values(?,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sale.getMemphone());  // 1번 ?에 값을 넣는다.
			pstmt.setInt(2, sale.getTotalprice()); // 2번 ?에 값을 넣는다.
			pstmt.setInt(3, sale.getStamp()); // 3번 ?에 값을 넣는다. 
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	// 데이터를 수정하는 함수
	boolean updateProduct(Product product) {
		sql = "update product set prname =?, price =? where prcode =?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProname());  // 1번 ?에 값을 넣는다.
			pstmt.setInt(2, product.getProprice()); // 2번 ?에 값을 넣는다.
			pstmt.setInt(3, product.getProcode()); // 3번 ?에 값을 넣는다. 
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	boolean updateMember(Member member) {
		sql = "update member set memphone =?, memname =?, memstamep =? where memphone =?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemphone());  // 1번 ?에 값을 넣는다.
			pstmt.setString(2, member.getMemname()); // 2번 ?에 값을 넣는다.
			pstmt.setInt(3, member.getMemstamp());
			pstmt.setString(4, member.getMemphone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	// 데이터를 삭제하는 함수
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
		
		if(result > 0) {
			return true;
		}else {
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
		
		if(result > 0) {
			return true;
		}else {
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
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	//comboBox에 넣는 데이터
	Vector<String> getProItems() {
		return proItems;
	}
	
	Vector<String> getMemItems() {
		return memItems;
	}
	
	Vector<String> getSalItems() {
		return salItems;
	}



}
