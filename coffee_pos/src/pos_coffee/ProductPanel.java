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

		this.setLayout(null);
		this.setSize(1024, 720);

		// ��ǰ ���
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

		// ��ǰ �Է� ��
		contents = new JPanel();
		contents.setLayout(new GridLayout(1, 6, 5, 10));
		contents.setSize(983, 50);
		contents.setLocation(12, 420);
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
		bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.setSize(980, 50);
		bottom.setLocation(12, 490);
		selectBtn = new JButton("����");
		insertUpdateBtn = new JButton("�߰�/����");
		deleteBtn = new JButton("����");

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
