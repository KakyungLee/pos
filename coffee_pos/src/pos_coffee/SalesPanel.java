package pos_coffee;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class SalesPanel extends JPanel {
	private DBDAO db;
	///////////////////
	/*MOA
	 * 세일즈 패널 정의 -> 크게 상,중,하단 패널로 구분
	 * 세일즈패널은 날짜를 표시해주는 패널과
	 * 리스트를 나타내는 패널, 버튼과 거래내용을 표시해주는 패널로 구분
	 */
	
	
	//MOA 각종 컴포넌트 정의
	private final int startX = 10;
	private final int startY = 10;

	private final int panel_hight = 740; // 메인패널의 높이값
	private final int panel_width = 1000;

	private JPanel topPanel;						//상단 패널
	private final int topPanel_height = 55;
	protected JComboBox<String> dateCombo;			//날짜 선택 콤보박스
	protected JButton dateSelectBtn;				//날짜 선택 버튼

	private JPanel salesListPanel;					//매출 목록 패널
	private final int salesListPanel_height = 390;
	protected JTable salesList; 					//매출목록 테이블

	private JPanel salesPanel;						//매출 금액 표기 패널
	private final int salesPanel_height = 60;
	protected JLabel salesAccountLbl; 				//매출 총액
	private JLabel lbl2 = new JLabel("원"); // 원

	private JPanel bottomPanel;						//하단 패널
	private final int bottomPanel_height = 200;
	protected JButton salesSelectBtn;				
	protected JButton refundBtn;					//거래취소(환불)버튼
	protected JPanel selectedSalesPan;
	protected JLabel date;
	protected JLabel selDate;
	protected JLabel member;
	protected JLabel selMember;
	protected JLabel price;
	protected JLabel selPrice;
	protected JLabel stamp;
	protected JLabel selStamp;

	protected DefaultTableModel model;
	protected Object[] colNames = null;
	protected Object[][] rows;
	protected ArrayList<Sale> datas;

	SalesPanel() {
		/*
		 * Shim Soo 
		 * AppManager에 SalesPanel을 set/ db가 필요하므로 get	 
		 */
		AppManager.createInstance().setSalesPanel(this);
		db = AppManager.createInstance().getDao();

		//MOA 폰트 정의
		Font listFont = new Font("맑은 고딕", Font.PLAIN, 24);
		Font salsesListHeadFont = new Font("맑은 고딕", Font.BOLD, 16); 
		Font salsesListFont = new Font("맑은 고딕", Font.PLAIN,16);
		Font contentFont = new Font("맑은 고딕", Font.PLAIN, 20);

		this.setLayout(null);
		this.setSize(1024, 720);

		////MOA 날짜 선택 패널
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, panel_width, topPanel_height);
		topPanel.setLayout(null);
		this.add(topPanel);

		dateCombo = new JComboBox<String>();
		dateCombo.setBounds(startX, startY, 230, 45);
		dateCombo.setFont(listFont);
		topPanel.add(dateCombo);

		dateSelectBtn = new JButton("날짜선택");
		dateSelectBtn.setFont(listFont);
		dateSelectBtn.setBounds(startX + 240, startY, 150, 45);
		topPanel.add(dateSelectBtn);

		//MOA 매출 테이블 패널
		salesListPanel = new JPanel();
		salesListPanel.setBounds(0, topPanel_height, panel_width, salesListPanel_height);
		salesListPanel.setLayout(null);
		this.add(salesListPanel);
		
		/*
		 * Shim Soo 
		 * 판매 JTable setting	 
		 */
		colNames = new Object[4];
		colNames[0] = "번     호";
		colNames[1] = "전화번호";
		colNames[2] = "총     액";
		colNames[3] = "스 탬 프";

		datas = new ArrayList<Sale>();
		datas = db.getAllSale();
		rows = new Object[datas.size()][4];

		int sum =0;
		
		int i = 0;
		for (Sale s : datas) {
			rows[i][0] = s.getSalno();
			rows[i][1] = s.getMemphone();
			rows[i][2] = s.getTotalprice();
			sum += s.getTotalprice();
			rows[i][3] = s.getStamp();
			i++;
		}

		
		model = new DefaultTableModel(rows, colNames);
		salesList = new JTable(model);
		salesList.setRowHeight(30);
		salesList.setFont(salsesListFont); // 폰트 설정
		salesList.getTableHeader().setFont(salsesListHeadFont);
		JScrollPane proListScroll = new JScrollPane(salesList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		salesListPanel.add(proListScroll);
		proListScroll.setVisible(true);
		proListScroll.setBounds(startX, startY, panel_width - startX * 2, salesListPanel_height - 20);

		//MOA 거래 총액 패널
		salesPanel = new JPanel();
		salesPanel.setBackground(Color.LIGHT_GRAY);
		salesPanel.setBounds(0, topPanel_height + salesListPanel_height, panel_width, salesPanel_height);
		salesPanel.setLayout(null);
		this.add(salesPanel);

		salesAccountLbl = new JLabel(sum +"");
		salesAccountLbl.setBounds(750, startY, 190, 40);
		salesAccountLbl.setFont(listFont);
		salesPanel.add(salesAccountLbl);

		lbl2.setBounds(950, startY, 50, 40);
		lbl2.setFont(listFont);
		salesPanel.add(lbl2);

		//MOA 하단 거래내역, 취소 버튼 등
		bottomPanel = new JPanel();
		//MOA bottomPanel.setBackground(Color.GRAY);
		bottomPanel.setBounds(0, topPanel_height + salesListPanel_height + salesPanel_height, panel_width,
				bottomPanel_height);
		bottomPanel.setLayout(null);
		this.add(bottomPanel);

		refundBtn = new JButton(changeSize(new ImageIcon("./image/sales_refundBtn.png"), 150, 140));
		refundBtn.setBounds(840, startY, 150, 140);
		bottomPanel.add(refundBtn);

		//MOA 거래내용 표시 패널
		selectedSalesPan = new JPanel();
		selectedSalesPan.setLayout(new GridLayout(4, 2));
		selectedSalesPan.setBackground(Color.white);
		selectedSalesPan.setBounds(startX, startY, 820, 140);
		bottomPanel.add(selectedSalesPan, BorderLayout.CENTER);

		date = new JLabel("매출  번호");
		selectedSalesPan.add(date);
		date.setFont(contentFont);

		selDate = new JLabel("");
		selectedSalesPan.add(selDate);
		selDate.setFont(contentFont);

		member = new JLabel("회원  번호");
		selectedSalesPan.add(member);
		member.setFont(contentFont);

		selMember = new JLabel("");
		selectedSalesPan.add(selMember);
		selMember.setFont(contentFont);

		price = new JLabel("금       액");
		selectedSalesPan.add(price);
		price.setFont(contentFont);

		selPrice = new JLabel("");
		selectedSalesPan.add(selPrice);
		selPrice.setFont(contentFont);

		stamp = new JLabel("스  탬  프");
		selectedSalesPan.add(stamp);
		stamp.setFont(contentFont);

		selStamp = new JLabel("");
		selectedSalesPan.add(selStamp);
		selStamp.setFont(contentFont);
	}

	//이미지 리사이즈 위한 함수
	ImageIcon changeSize(ImageIcon temp, int width, int height) {
		Image tempImg = temp.getImage();
		tempImg = tempImg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		temp.setImage(tempImg);
		return temp;
	}
	
	/*
	 * Shim Soo 
	 * Controller와 연결하기 위한 메소드
	 * addButtonActionListener: 버튼에 대한 actionListener
	 * addMouseListener: JTable에 대한 actionListener 
	 */
	void addButtonActionListener(ActionListener listener) {
		dateSelectBtn.addActionListener(listener);
		//salesSelectBtn.addActionListener(listener);
		refundBtn.addActionListener(listener);
	}

	public void addMouseListener(MouseListener listener) {
		salesList.addMouseListener(listener);
	}

}