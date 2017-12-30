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
	JLabel selectedSalesLbl;
	JButton refundBtn;

	SalesPanel(){
		this.setBackground(Color.MAGENTA);
		this.setSize(1024, 720);
		this.setLayout(null);
		
		int w = 5;
		int h = 5;
		// 날짜 panel
		top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.setSize(1020,50);
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
		
		
		// add
		this.add(top);

	}
	
}
