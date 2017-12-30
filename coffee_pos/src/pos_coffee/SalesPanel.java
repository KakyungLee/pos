package pos_coffee;
import java.awt.*;
import javax.swing.*;

public class SalesPanel extends JPanel{
	// 날짜 panel
	JPanel top;
	JLabel dateLabel;
	JComboBox dateCombo;
	JButton dateSelectBtn;
	
	// 상품목록
	JList salesList;
	
	// 총액 panel
	JPanel center;
	JLabel salesAccountLbl;
	JLabel won;
	
	// 선택창 panel
	JPanel bottom;
	JButton salesSelectBtn;
	JPanel selectedSalesPan;
	JButton refundBtn;
	
	// 선택 정보 보여주는 창
	JLabel date;
	JLabel selDate;
	JLabel member;
	JLabel selMember;
	JLabel price;
	JLabel selPrice;
	JLabel stamp;
	JLabel selStamp;
	
	

	SalesPanel(){
		this.setBackground(Color.MAGENTA);
		this.setSize(1024, 720);
		this.setLayout(null);
		
		int w = 5;
		int h = 5;
		// 날짜 panel
		top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.setSize(998,50);
		top.setBackground(Color.white);
		top.setLocation(w, h);
		h = h+55;
		
		dateLabel = new JLabel("날짜");
		top.add(dateLabel);
		
		dateCombo = new JComboBox();
		top.add(dateCombo);
		
		dateSelectBtn = new JButton("날짜 선택");
		top.add(dateSelectBtn);
		
		//상품 목록
		salesList = new JList();
		salesList.setSize(998,400);
		salesList.setBackground(Color.white);
		salesList.setLocation(w, h);
		h = h+405;
		
		//총액 panel
		center = new JPanel();
		center.setLayout(new FlowLayout(FlowLayout.RIGHT));
		center.setSize(998,50);
		center.setBackground(Color.white);
		center.setLocation(w, h);
		h = h+55;
		
		salesAccountLbl = new JLabel("0");
		center.add(salesAccountLbl);
		
		won = new JLabel("원");
		center.add(won);
		
		// 선택창 panel
		bottom = new JPanel();
		bottom.setLayout(new BorderLayout(50,50));
		bottom.setSize(998,155);
		bottom.setBackground(Color.white);
		bottom.setLocation(w, h);
		h = h+55;
		
		salesSelectBtn = new JButton("선택");
		bottom.add(salesSelectBtn,BorderLayout.WEST);
		
		selectedSalesPan = new JPanel();
		selectedSalesPan.setLayout(new GridLayout(4,2));
		selectedSalesPan.setBackground(Color.white);
		bottom.add(selectedSalesPan, BorderLayout.CENTER);
		
		refundBtn = new JButton("환불");
		bottom.add(refundBtn,BorderLayout.EAST);
		
		// 선택 정보 보여주는 창
		date = new JLabel("일 자");
		selectedSalesPan.add(date);
		
		selDate = new JLabel("");
		selectedSalesPan.add(selDate);
		
		member = new JLabel("회 원 번 호");
		selectedSalesPan.add(member);
		
		selMember = new JLabel("");
		selectedSalesPan.add(selMember);
		
		price = new JLabel("구 매 총 액");
		selectedSalesPan.add(price);
		
		selPrice = new JLabel("");
		selectedSalesPan.add(selPrice);
		
		stamp = new JLabel("스 탬 프");
		selectedSalesPan.add(stamp);
		
		selStamp = new JLabel("");
		selectedSalesPan.add(selStamp);
		
		
		// add
		this.add(top);
		this.add(salesList);
		this.add(center);
		this.add(bottom);

	}
	
}