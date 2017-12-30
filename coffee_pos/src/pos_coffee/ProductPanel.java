package pos_coffee;

import java.awt.*;

import javax.swing.*;

public class ProductPanel extends JPanel {
	///////////////header
	JList productList;
	///////////// center
	JPanel contents;
	
	JLabel productCode;
	JLabel productName;
	JLabel ProductPrice;
	
	JComboBox idxCombo;
	JTextField proNameTxt;
	JTextField proPriceTxt;
	//////////// bottom
	JPanel bottom;
	
	JButton selectBtn;
	JButton insertUpdateBtn;
	JButton deleteBtn;

	public ProductPanel(){
		this.setLayout(null);
		this.setSize(1024,720);
		
		// ��ǰ ���
		productList = new JList();
		productList.setSize(983,400);
		productList.setLocation(12, 10);
		
		// ��ǰ �Է� �� 
		contents = new JPanel();
		contents.setLayout(new GridLayout(1,6,5,10));
		contents.setSize(983,50);
		contents.setLocation(12,420);
		contents.setBackground(Color.white);
		
		productCode = new JLabel("��ǰ�ڵ�");
		idxCombo = new JComboBox();
		productName = new JLabel("��ǰ��");
		proNameTxt = new JTextField();
		ProductPrice = new JLabel("����");
		proPriceTxt = new JTextField();

		contents.add(productCode);
		contents.add(idxCombo);
		contents.add(productName);
		contents.add(proNameTxt);
		contents.add(ProductPrice);
		contents.add(proPriceTxt);
		
		// ��ư 
		bottom  = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.setSize(980,50);
		bottom.setLocation(12,490);
		selectBtn = new JButton("����");
		insertUpdateBtn = new JButton("�߰�/����");
		deleteBtn = new JButton("����");
		
		bottom.add(selectBtn);
		bottom.add(insertUpdateBtn);
		bottom.add(deleteBtn);
		
		
		///////////add
		this.add(productList);
		this.add(contents);
		this.add(bottom);

		
	}
}
