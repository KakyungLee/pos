package pos_coffee;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SalesPanel extends JPanel {
	private SellController sc;
	private DBDAO db;	
	///////////////////
	private final int startX = 10;
	private final int startY = 10;

	private final int panel_hight = 740; // 메인패널의 높이값
	private final int panel_width = 1000;

	private JPanel topPanel;
	private final int topPanel_height = 55;
	protected JComboBox<String> dateCombo;
	protected JButton dateSelectBtn;

	private JPanel salesListPanel;
	private final int salesListPanel_height = 390;
	protected JTable salesList = new JTable(); // 거래목록

	private JPanel salesPanel;
	private final int salesPanel_height = 60;
	protected JTextField salesAccountLbl; // 금액 표기
	private JLabel lbl2 = new JLabel("원"); // 원

	private JPanel bottomPanel;
	private final int bottomPanel_height = 200;
	protected JButton salesSelectBtn;
	protected JButton refundBtn;
	protected JPanel selectedSalesPan;
	protected JLabel date;
	protected JLabel selDate;
	protected JLabel member;
	protected JLabel selMember;
	protected JLabel price;
	protected JLabel selPrice;
	protected JLabel stamp;
	protected JLabel selStamp;

	SalesPanel(){
		AppManager.createInstance().setSalesPanel(this);
		db = AppManager.createInstance().getDao();
		
		
		Font listFont = new Font("맑은 고딕", Font.PLAIN, 24);
		Font contentFont = new Font("맑은 고딕", Font.PLAIN, 20);

		this.setLayout(null);
		this.setSize(1024, 720);
		

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

		// 거래목록
		salesListPanel = new JPanel();
		// salesListPanel.setBackground(Color.YELLOW);
		salesListPanel.setBounds(0, topPanel_height, panel_width, salesListPanel_height);
		salesListPanel.setLayout(null);
		this.add(salesListPanel);

		salesList = new JTable();
		// salesList.setBackground(Color.RED);
		JScrollPane proListScroll = new JScrollPane(salesList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		salesListPanel.add(proListScroll);
		proListScroll.setVisible(true);
		proListScroll.setBounds(startX, startY, panel_width - startX * 2, salesListPanel_height - 20);

		// 거래 총액
		salesPanel = new JPanel();
		salesPanel.setBackground(Color.LIGHT_GRAY);
		salesPanel.setBounds(0, topPanel_height + salesListPanel_height, panel_width, salesPanel_height);
		salesPanel.setLayout(null);
		this.add(salesPanel);

		salesAccountLbl = new JTextField("999999");
		salesAccountLbl.setBounds(750, startY, 190, 40);
		salesAccountLbl.setFont(listFont);
		salesPanel.add(salesAccountLbl);

		lbl2.setBounds(950, startY, 50, 40);
		lbl2.setFont(listFont);
		salesPanel.add(lbl2);

		// 하단 거래내역, 취소 버튼 등
		bottomPanel = new JPanel();
		// bottomPanel.setBackground(Color.GRAY);
		bottomPanel.setBounds(0, topPanel_height + salesListPanel_height + salesPanel_height, panel_width,
				bottomPanel_height);
		bottomPanel.setLayout(null);
		this.add(bottomPanel);

		salesSelectBtn = new JButton(changeSize(new ImageIcon("./image/sales_salesSelectBtn.png"), 150, 140));
		salesSelectBtn.setBounds(startX, startY, 150, 140);
		bottomPanel.add(salesSelectBtn);

		refundBtn = new JButton(changeSize(new ImageIcon("./image/sales_refundBtn.png"), 150, 140));
		refundBtn.setBounds(840, startY, 150, 140);
		bottomPanel.add(refundBtn);

		// 거래내용 표시 패널
		selectedSalesPan = new JPanel();
		selectedSalesPan.setLayout(new GridLayout(4, 2));
		selectedSalesPan.setBackground(Color.white);
		selectedSalesPan.setBounds(170, startY, 660, 140);
		bottomPanel.add(selectedSalesPan, BorderLayout.CENTER);

		date = new JLabel("거래 날짜");
		selectedSalesPan.add(date);
		date.setFont(contentFont);

		selDate = new JLabel("2017-12-31");
		selectedSalesPan.add(selDate);
		selDate.setFont(contentFont);

		member = new JLabel("회원이름");
		selectedSalesPan.add(member);
		member.setFont(contentFont);

		selMember = new JLabel("김모아");
		selectedSalesPan.add(selMember);
		selMember.setFont(contentFont);

		price = new JLabel("금액");
		selectedSalesPan.add(price);
		price.setFont(contentFont);

		selPrice = new JLabel("9999");
		selectedSalesPan.add(selPrice);
		selPrice.setFont(contentFont);

		stamp = new JLabel("스탬프");
		selectedSalesPan.add(stamp);
		stamp.setFont(contentFont);

		selStamp = new JLabel("10");
		selectedSalesPan.add(selStamp);
		selStamp.setFont(contentFont);
	/*	
		if(sc == null)
			new SellController();
		sc = AppManager.createInstance().getSellController();
		sc.refresh();
		this.repaint();*/
	}

	ImageIcon changeSize(ImageIcon temp, int width, int height) {
		Image tempImg = temp.getImage();
		tempImg = tempImg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		temp.setImage(tempImg);
		return temp;
	}
	
	void addButtonActionListener(ActionListener listener) {
		dateCombo.addActionListener(listener);
	}

}