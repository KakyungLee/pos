package pos_coffee;
import java.awt.*;
import javax.swing.*;

public class SalesPanel extends JPanel{
	// ��¥ panel
	JPanel top;
	JLabel dateLabel;
	JComboBox dateCombo;
	JButton dateSelectBtn;
	
	// ��ǰ���
	JList salesList;
	
	// �Ѿ� panel
	JPanel center;
	JLabel salesAccountLbl;
	JLabel won;
	
	// ����â panel
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
		// ��¥ panel
		top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.setSize(1020,50);
		top.setBackground(Color.white);
		top.setLocation(w, h);
		h = h+55;
		
		dateLabel = new JLabel("��¥");
		top.add(dateLabel);
		
		dateCombo = new JComboBox();
		top.add(dateCombo);
		
		dateSelectBtn = new JButton("��¥ ����");
		top.add(dateSelectBtn);
		
		//��ǰ ���
		
		
		// add
		this.add(top);

	}
	
}
