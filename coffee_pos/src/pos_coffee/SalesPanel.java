package pos_coffee;
import java.awt.*;
import javax.swing.*;

public class SalesPanel extends JPanel{
	private SellController sc;
	private DBDAO db;
	// ��¥ panel
	JPanel top;
	JLabel dateLabel;
	JComboBox dateCombo;
	JButton dateSelectBtn;
	
	// ��ǰ���
	JTable salesList;
	
	// �Ѿ� panel
	JPanel center;
	JLabel salesAccountLbl;
	JLabel won;
	
	// ����â panel
	JPanel bottom;
	JPanel selectedSalesPan;
	JButton refundBtn;
	
	// ���� ���� �����ִ� â
	JLabel date;
	JLabel selDate;
	JLabel member;
	JLabel selMember;
	JLabel price;
	JLabel selPrice;
	JLabel stamp;
	JLabel selStamp;
	////////////////////
	Color salesColor;
	int w, h;

	SalesPanel(){
		AppManager.createInstance().setSalesPanel(this);
		db = AppManager.createInstance().getDao();
		this.setLayout(null);
		this.setSize(1024, 720);
		salesColor = new Color(91,155, 213);
		this.setBackground(salesColor);

		// 날짜 페널 설정
		top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.setSize(983,80);
		top.setBackground(Color.white);
		top.setLocation(12, 10);

		dateLabel = new JLabel("날 짜");
		top.add(dateLabel);
		
		dateCombo = new JComboBox();
		top.add(dateCombo);
		
		dateSelectBtn = new JButton("기 록 조 회");
		top.add(dateSelectBtn);
		
		//기록 리스트
		salesList = new JTable();
		salesList.setRowHeight(30);
		salesList.setFont(new Font("",Font.PLAIN,12));
		salesList.getTableHeader().setFont(new Font("",Font.BOLD,15));
		salesList.getTableHeader().setBackground(new Color(225, 235, 247));
		JScrollPane salesListScroll = new JScrollPane(salesList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		salesListScroll.setSize(983,400);
		salesListScroll.setLocation(12, 100);

		
		//총액 페널
		center = new JPanel();
		center.setLayout(new FlowLayout(FlowLayout.RIGHT));
		center.setSize(983,30);
		center.setBackground(salesList.getTableHeader().getBackground());
		center.setLocation(12, 500);
		
		salesAccountLbl = new JLabel("0");
		
		center.add(salesAccountLbl);
		
		won = new JLabel("   원   ");
		center.add(won);
		
		// 버튼 페널 
		bottom = new JPanel();
		bottom.setLayout(new BorderLayout(50,50));
		bottom.setSize(983,155);
		bottom.setBackground(Color.white);
		bottom.setLocation(12, 540);


		selectedSalesPan = new JPanel();
		selectedSalesPan.setLayout(new GridLayout(4,2));
		selectedSalesPan.setBackground(Color.white);
		bottom.add(selectedSalesPan, BorderLayout.CENTER);
		
		refundBtn = new JButton("ȯ��");
		bottom.add(refundBtn,BorderLayout.EAST);
		
		// ���� ���� �����ִ� â
		date = new JLabel("�� ��");
		selectedSalesPan.add(date);
		
		selDate = new JLabel("");
		selectedSalesPan.add(selDate);
		
		member = new JLabel("ȸ �� �� ȣ");
		selectedSalesPan.add(member);
		
		selMember = new JLabel("");
		selectedSalesPan.add(selMember);
		
		price = new JLabel("�� �� �� ��");
		selectedSalesPan.add(price);
		
		selPrice = new JLabel("");
		selectedSalesPan.add(selPrice);
		
		stamp = new JLabel("�� �� ��");
		selectedSalesPan.add(stamp);
		
		selStamp = new JLabel("");
		selectedSalesPan.add(selStamp);
		
		
		// add
		this.add(top);
		this.add(salesListScroll);
		this.add(center);
		this.add(bottom);

	}
	
}