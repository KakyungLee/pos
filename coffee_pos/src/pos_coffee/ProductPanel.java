package pos_coffee;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ProductPanel extends JPanel {
	private ProductController pc;
	private DBDAO db;
	/////////////// header
	JTable productList;
	DefaultTableModel model;
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

		// 占쎄맒占쎈�� 筌뤴뫖以�
		colNames = new Object[3];
		colNames[0] = "proCode";
		colNames[1] = "Name";
		colNames[2] = "Price";

		datas = new ArrayList<Product>();
		//datas = db.getAllProduct();
		rows = new Object[datas.size()][3];
		/*int i = 0;
		for (Product p : datas) {
			rows[i][0] = p.getProcode();
			rows[i][1] = p.getProname();
			rows[i][2] = p.getProprice();
			i++;
		}*/

		model = new DefaultTableModel(rows,colNames);
		productList = new JTable(model);
		productList.setSize(983, 400);
		productList.setLocation(12, 10);

		// 占쎄맒占쎈�� 占쎌뿯占쎌젾 占쎈쨲
		contents = new JPanel();
		contents.setLayout(new GridLayout(1, 6, 5, 10));
		contents.setSize(983, 50);
		contents.setLocation(12, 420);
		contents.setBackground(Color.white);

		productCode = new JLabel("占쎄맒占쎈�배굜遺얜굡");
		idxCombo = new JComboBox();
		productName = new JLabel("占쎌젫占쎈�뱄쭗占�");
		proNameTxt = new JTextField();
		ProductPrice = new JLabel("揶쏉옙野껓옙");
		proPriceTxt = new JTextField();

		contents.add(productCode);
		contents.add(idxCombo);
		contents.add(productName);
		contents.add(proNameTxt);
		contents.add(ProductPrice);
		contents.add(proPriceTxt);

		// 甕곌쑵�뱣
		bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.setSize(980, 50);
		bottom.setLocation(12, 490);
		selectBtn = new JButton("占쎄퐨占쎄문");
		insertUpdateBtn = new JButton("�빊遺쏙옙/占쎈땾占쎌젟");
		deleteBtn = new JButton("占쎄텣占쎌젫");

		bottom.add(selectBtn);
		bottom.add(insertUpdateBtn);
		bottom.add(deleteBtn);

		/////////// add
		this.add(productList);
		this.add(contents);
		this.add(bottom);
		
		pc = new ProductController();
		pc.refresh();
	}

	void addButtonActionListener(ActionListener listener) {
		selectBtn.addActionListener(listener);
		insertUpdateBtn.addActionListener(listener);
		deleteBtn.addActionListener(listener);
	}
	public void addMouseListener(MouseListener listener){
		productList.addMouseListener(listener);
	}
}
