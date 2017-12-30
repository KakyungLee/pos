package pos_coffee;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ProductPanel extends JPanel {
	private ProductController pc;
	private DBDAO db;
	/////////////// header
	JTable productList;
	Object[] colNames;
	Object[][] rows;
	ArrayList<Product> datas;
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

	public ProductPanel() {
		AppManager.createInstance().setProductPanel(this);
		db = AppManager.createInstance().getDao();
		pc = AppManager.createInstance().getProductController();
		this.setLayout(null);
		this.setSize(1024, 720);

		// �긽�뭹 紐⑸줉
		colNames = new Object[3];
		colNames[0] = "proCode";
		colNames[1] = "Name";
		colNames[2] = "Price";

		datas = new ArrayList<Product>();
		datas = db.getAllProduct();
		rows = new Object[datas.size()][3];

		int i = 0;
		for (Product p : datas) {
			rows[i][0] = p.getProcode();
			rows[i][1] = p.getProname();
			rows[i][2] = p.getProprice();
			i++;
		}

		productList = new JTable(rows, colNames);
		productList.setSize(983, 400);
		productList.setLocation(12, 10);

		// �긽�뭹 �엯�젰 �뤌
		contents = new JPanel();
		contents.setLayout(new GridLayout(1, 6, 5, 10));
		contents.setSize(983, 50);
		contents.setLocation(12, 420);
		contents.setBackground(Color.white);

		productCode = new JLabel("�긽�뭹肄붾뱶");
		idxCombo = new JComboBox();
		productName = new JLabel("�젣�뭹紐�");
		proNameTxt = new JTextField();
		ProductPrice = new JLabel("媛�寃�");
		proPriceTxt = new JTextField();

		contents.add(productCode);
		contents.add(idxCombo);
		contents.add(productName);
		contents.add(proNameTxt);
		contents.add(ProductPrice);
		contents.add(proPriceTxt);

		// 踰꾪듉
		bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.setSize(980, 50);
		bottom.setLocation(12, 490);
		selectBtn = new JButton("�꽑�깮");
		insertUpdateBtn = new JButton("異붽�/�닔�젙");
		deleteBtn = new JButton("�궘�젣");

		bottom.add(selectBtn);
		bottom.add(insertUpdateBtn);
		bottom.add(deleteBtn);

		/////////// add
		this.add(productList);
		this.add(contents);
		this.add(bottom);
		
		pc = new ProductController();
		
	}

	void addButtonActionListener(ActionListener listener) {
		selectBtn.addActionListener(listener);
		insertUpdateBtn.addActionListener(listener);
		deleteBtn.addActionListener(listener);
	}
}
