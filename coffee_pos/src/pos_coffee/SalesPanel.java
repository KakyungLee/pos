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
	
	

	SalesPanel(){
		this.setBackground(Color.MAGENTA);
		this.setSize(1024, 720);
		this.setLayout(null);
		
		int w = 5;
		int h = 5;
		// ��¥ panel
		top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.setSize(998,50);
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
		salesList = new JList();
		salesList.setSize(998,400);
		salesList.setBackground(Color.white);
		salesList.setLocation(w, h);
		h = h+405;
		
		//�Ѿ� panel
		center = new JPanel();
		center.setLayout(new FlowLayout(FlowLayout.RIGHT));
		center.setSize(998,50);
		center.setBackground(Color.white);
		center.setLocation(w, h);
		h = h+55;
		
		salesAccountLbl = new JLabel("0");
		center.add(salesAccountLbl);
		
		won = new JLabel("��");
		center.add(won);
		
		// ����â panel
		bottom = new JPanel();
		bottom.setLayout(new BorderLayout(50,50));
		bottom.setSize(998,155);
		bottom.setBackground(Color.white);
		bottom.setLocation(w, h);
		h = h+55;
		
		salesSelectBtn = new JButton("����");
		bottom.add(salesSelectBtn,BorderLayout.WEST);
		
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
		this.add(salesList);
		this.add(center);
		this.add(bottom);

	}
	
}