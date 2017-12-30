package pos_coffee;

import java.awt.*;

import javax.swing.*;

public class ProductPanel extends JPanel {
	
	JList productList;
	
	JLabel productCode;
	JLabel productName;
	JLabel ProductPrice;
	
	JComboBox idxCombo;
	JTextField proNameTxt;
	JTextField proPriceTxt;
	
	JButton selectBtn;
	JButton insertUpdateBtn;
	JButton deleteBtn;
	
	
	public ProductPanel(){
		this.setLayout(null);
		this.setSize(1024,720);
		
		productList = new JList();
		productList.setSize(983,400);
		productList.setLocation(12, 10);
		
		
		
		this.add(productList);
		
		
		
	}
}
