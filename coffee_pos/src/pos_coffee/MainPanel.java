package pos_coffee;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class MainPanel extends JPanel {
	private DBDAO db;
	/////////////// header
	/*
	 * Shim Soo 
	 * JTable에 사용해 하는 변수	 
	 */
	DefaultTableModel model;
	Object[] colNames;
	Object[][] rows;
	ArrayList<Product> datas;

	DefaultTableModel model2;
	Object[] colNames2;
	Object[][] rows2;
	ArrayList<Product> datas2;
	int selectCount;

	
	/*	MOA
	 * 	메인패널 정의 ->
	 * 	메인패널은 패널을 반으로 나누어 크게 좌측패널, 우측패널로 나뉘며
	 * 	우측패널에는 회원패널, 결제버튼으로 이어짐.
	 * 	추후에 프레임의 해상도 절대값이 바뀔것을 고려하여
	 * 	모든 패널과 컴포넌트는 상수값 사용을 자제하고, 상대값을 사용하여 제작됨.
	 */
	
	//MOA 메인 화면
	////// 각종 컴포넌트 정의

	//MOA 각 컴포넌트의 시작점 위치
	private int startX = 15;

	private int startY = 10;
	private int startX_right = 0;

	private int panel_hight = 730; // 메인패널의 높이값
	private int half_panel_width = 500; // 메인패널의 넓이 2등분 값

	//MOA >>> 좌측 패널(상품리스트, 선택버튼, 샷추가버튼, 제거버튼) 정의
	private int l_margin = 0;
	private int l_gap = 10; // 컴포넌트간 간격

	protected JPanel mainLeftPanel;		//좌측 패널
	protected JTable productList;		//상품리스트 테이블

	private int proList_width = half_panel_width - (startX * 2) - l_margin;
	private int proList_height = 530 - l_margin;

	protected JButton selectBtn;		//상품선택 버튼
	private int l_btnWidth = (proList_width - (l_gap + l_margin) * 2) / 3;
	private int l_btnHight = 100 - l_margin;
	protected JButton shotSelectBtn;	//샷추가 버튼
	protected JButton removeBtn;		//선택된상품목록 테이블에서 선택한 상품 제거 버튼

	//MOA >>> 우측패널 정의 (선택된 상품리스트, 총액, 회원, 결제 등) 정의
	private int r_margin = 0;
	private int r_gap = 10;

	private int r_height = 365; // 이 패널의 높이

	protected JPanel mainRightPanel;		//우측 패널
	protected JTable selectedProductList;	//선택된상품목록 테이블
	private int selProList_width = half_panel_width - 10 - l_margin;
	private int selProList_hight = 290 - r_margin;
	private JLabel lbl1 = new JLabel("총액");
	protected JTextField pricelLbl; 		// 결제할 총액
	private JLabel lbl2 = new JLabel("원");

	//MOA >>> 우측 회원 패널 정의 (회원번호 입력, 회원정보 라벨, 스탬프 사용 버튼)
	private int m_margin = 0;
	private int m_gap = 10;
	private int m_height = (int) ((panel_hight - r_height) * 0.48);
	private int m_LeftWidth = (int) (half_panel_width * 0.7) - m_gap;
	private int m_RightWidth = (int) (half_panel_width * 0.3) - m_gap * 2;

	protected JPanel memberPanel;	//회원 패널
	protected JTextField memberTxt;	 // 조회할 회원 휴대폰번호
	protected JButton searchBtn;	 // 핸드폰 번호로 회원 조회 -> 스탬프 적립,
	protected JTextArea memberinfoLbl; // 회원 조회시 회원정보 나타냄
	protected JButton useStampBtn; 	// 스탬프 사용

	//MOA >>> 우측 결제버튼, 초기화 버튼 패널 정의

	private int p_height = (int) ((panel_hight - r_height) * 0.52);
	private int p_btnWidth = (half_panel_width - (m_gap * 2)) / 2;

	private int p_btnHeight = 100;

	protected JPanel payPanel;	//결제버튼 패널
	protected JButton clearBtn; // 결제리스트 및 회원 화면 클리어
	protected JButton paymentBtn; // 결제 진행 및 매출 발생

	MainPanel() {
		/*
		 * Shim Soo 
		 * AppManager에 MainPanel을 set하고 db를 사용해야 하므로 get한다.	 
		 */
		AppManager.createInstance().setMainPanel(this);
		db = AppManager.createInstance().getDao();

		/*MOA 폰트 정의 */
		Font listFont = new Font("맑은 고딕", Font.PLAIN, 16);
		Font priceFont = new Font("맑은 고딕", Font.PLAIN, 28);
		Font mInfoFont = new Font("맑은 고딕", Font.PLAIN, 20);

		Font proListHeadFont = new Font("맑은 고딕", Font.BOLD, 16);
		Font proListFont = new Font("맑은 고딕", Font.PLAIN, 26);

		this.setLayout(null);

		/////////////MOA 컴포넌트 그리기 시작
		// >>>> 좌측패널 그리기
		mainLeftPanel = new JPanel();
		// mainLeftPanel.setBackground(Color.CYAN);
		mainLeftPanel.setBounds(0, 0, half_panel_width, panel_hight);
		mainLeftPanel.setLayout(null);
		this.add(mainLeftPanel);

		/*
		 * Shim Soo 
		 * 상품 JTable을 setting	 
		 */
		// 상품 리스트
		colNames = new Object[3];
		colNames[0] = "코드";
		colNames[1] = "상 품 명";
		colNames[2] = "가 격";

		datas = new ArrayList<Product>();
		datas = db.getAllProduct();

		Object[][] rows = new Object[datas.size()][3];
		selectCount = 0;

		int i = 0;
		for (Product p : datas) {
			rows[i][0] = p.getProcode();
			rows[i][1] = p.getProname();
			rows[i][2] = p.getProprice();
			i++;
		}

		model = new DefaultTableModel(rows, colNames);
		productList = new JTable(model); // 상품 리스트
		productList.setRowHeight(30);
		productList.setFont(listFont); // 폰트 설정
		productList.getTableHeader().setFont(proListHeadFont);
		JScrollPane proListScroll = new JScrollPane(productList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		productList.getTableHeader().setFont(proListHeadFont);
		productList.setRowHeight(30);
		productList.getColumnModel().getColumn(0).setMaxWidth(70);
		productList.getColumnModel().getColumn(1).setMinWidth(250);
		mainLeftPanel.add(proListScroll);
		proListScroll.setVisible(true);
		proListScroll.setBounds(startX, startY, proList_width, proList_height);

		//MOA ::선택버튼
		selectBtn = new JButton(changeSize(new ImageIcon("./image/main_selectBtn.png"), l_btnWidth, l_btnHight));
		mainLeftPanel.add(selectBtn);
		selectBtn.setBounds(startX, startY + proList_height + l_gap, l_btnWidth, l_btnHight);

		//MOA ::샷추가버튼
		shotSelectBtn = new JButton(
				changeSize(new ImageIcon("./image/main_shotSelectBtn.png"), l_btnWidth, l_btnHight));
		mainLeftPanel.add(shotSelectBtn);
		shotSelectBtn.setBounds(startX + (l_btnWidth + l_gap), startY + proList_height + l_gap, l_btnWidth, l_btnHight);

		//MOA ::선택취소버튼
		removeBtn = new JButton(changeSize(new ImageIcon("./image/main_removeBtn.png"), l_btnWidth, l_btnHight));
		mainLeftPanel.add(removeBtn);
		removeBtn.setBounds(startX + (l_btnWidth + l_gap) * 2, startY + proList_height + l_gap, l_btnWidth, l_btnHight);

		//MOA >>>> 우측 패널
		// >> 선택된 상품 리스트 & 총액 패널
		mainRightPanel = new JPanel();
		// mainRightPanel.setBackground(Color.GRAY);
		mainRightPanel.setBounds(half_panel_width, 0, half_panel_width, r_height);
		mainRightPanel.setLayout(null);
		this.add(mainRightPanel);

		/*
		 * Shim Soo 
		 * 주문 JTable을 설정 setting	 
		 */		
		rows2 = new Object[0][3];
		model2 = new DefaultTableModel(rows2, colNames);
		
		//MOA 우측 상품리스트 패널 컴포넌트
		selectedProductList = new JTable(model2); // 선택된 상품 리스트
		selectedProductList.setRowHeight(30);
		selectedProductList.setFont(listFont);
		selectedProductList.getTableHeader().setFont(proListHeadFont);
		JScrollPane proSelListScroll = new JScrollPane(selectedProductList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		selectedProductList.setFont(listFont);
		selectedProductList.getTableHeader().setFont(proListHeadFont);
		selectedProductList.setRowHeight(30);
		selectedProductList.getColumnModel().getColumn(0).setMaxWidth(70);
		selectedProductList.getColumnModel().getColumn(1).setMinWidth(250);
		mainRightPanel.add(proSelListScroll);
		proSelListScroll.setVisible(true);
		proSelListScroll.setBounds(startX_right, startY, selProList_width, selProList_hight);

		//MOA :: 결제할 최종 금액 패널 컴포넌트
		mainRightPanel.add(lbl1); // "총액"
		lbl1.setBounds(startX_right, selProList_hight + r_gap * 2, 150, 50);
		lbl1.setFont(priceFont);

		pricelLbl = new JTextField("0"); // 실제 표시되는 금액
		pricelLbl.setEditable(false);
		pricelLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mainRightPanel.add(pricelLbl);
		pricelLbl.setBounds(250, selProList_hight + r_gap * 2, 200, 50);
		pricelLbl.setFont(priceFont);

		mainRightPanel.add(lbl2); // "원"
		lbl2.setBounds(450, selProList_hight + r_gap * 2, 150, 50);
		lbl2.setFont(priceFont);

		//MOA >> 회원 조회 & 스탬프 사용 패널
		memberPanel = new JPanel();
		// memberPanel.setBackground(Color.orange);
		memberPanel.setBounds(half_panel_width, r_height, half_panel_width, m_height);
		memberPanel.setLayout(null);
		this.add(memberPanel);

		//MOA :: 회원번호 입력 텍스트필드
		memberTxt = new JTextField("회원번호입력");
		memberTxt.setBounds(startX_right, startY, m_LeftWidth, 60);
		memberTxt.setFont(priceFont);
		memberPanel.add(memberTxt);

		//MOA :: 회원 검색 버튼
		searchBtn = new JButton(changeSize(new ImageIcon("./image/main_searchBtn.png"), m_RightWidth, 60));
		searchBtn.setBounds(m_LeftWidth + m_gap * 2, startY, m_RightWidth, 60);
		memberPanel.add(searchBtn);

		//MOA :: 회원정보 표시 텍스트
		memberinfoLbl = new JTextArea();
		memberinfoLbl.setBounds(startX_right, 60 + m_gap * 2, m_LeftWidth, 90);
		memberinfoLbl.setEditable(false);
		memberinfoLbl.setFont(mInfoFont);
		memberPanel.add(memberinfoLbl);

		//MOA :: 스탬프 사용 버튼
		useStampBtn = new JButton(changeSize(new ImageIcon("./image/main_useStampBtn.png"), m_RightWidth, 90));
		useStampBtn.setBounds(m_LeftWidth + m_gap * 2, 60 + m_gap * 2, m_RightWidth, 90);
		useStampBtn.setEnabled(false);
		memberPanel.add(useStampBtn);

		//MOA >> 결제 버튼 패널
		payPanel = new JPanel();
		// payPanel.setBackground(Color.PINK);
		payPanel.setBounds(half_panel_width, r_height + m_height, half_panel_width, p_height);
		payPanel.setLayout(null);
		this.add(payPanel);

		//MOA :: 초기화 버튼
		clearBtn = new JButton(changeSize(new ImageIcon("./image/main_clearBtn.png"), p_btnWidth, p_btnHeight));
		clearBtn.setBounds(startX_right, startY, p_btnWidth, p_btnHeight);
		payPanel.add(clearBtn);

		//MOA :: 결제 버튼

		paymentBtn = new JButton(changeSize(new ImageIcon("./image/main_paymentBtn.png"), p_btnWidth, p_btnHeight));
		paymentBtn.setBounds(p_btnWidth + m_gap, startY, p_btnWidth, p_btnHeight);
		payPanel.add(paymentBtn);

		///////////// 컴포넌트 그리기 끝
	}

	//이미지파일의 리사이징을 위한 함수
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
		selectBtn.addActionListener(listener);
		shotSelectBtn.addActionListener(listener);
		removeBtn.addActionListener(listener);
		searchBtn.addActionListener(listener);
		useStampBtn.addActionListener(listener);
		clearBtn.addActionListener(listener);
		paymentBtn.addActionListener(listener);
	}

	public void addMouseListener(MouseListener listener) {
		productList.addMouseListener(listener);
		selectedProductList.addMouseListener(listener);
		memberTxt.addMouseListener(listener);
	}

}
