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
	Vector colNames = null;
	Vector datas = null;
	///////////// center
	JPanel contents;

	JLabel productCode;
	JLabel productName;
	JLabel ProductPrice;

	JTextField proCodeTxt;
	JTextField proNameTxt;
	JTextField proPriceTxt;
	//////////// bottom
	JPanel bottom;
	JButton insertBtn;
	JButton updateBtn;
	JButton deleteBtn;
	
	/////////////
	Color productColor;
	int w; int h;

	@SuppressWarnings("unchecked")
	public ProductPanel() {		
		AppManager.createInstance().setProductPanel(this);
		db = AppManager.createInstance().getDao();
		pc = AppManager.createInstance().getProductController();
		this.setLayout(null);
		this.setSize(1024, 720);
		productColor = new Color(255,192,0);
		this.setBackground(productColor);

		// 테이블 컬럼 명
		colNames = new Vector<>();
		colNames.add("상 품 코 드");
		colNames.add("상 품 명");
		colNames.add("가 격");

		// 처음 화면에 뿌려주는 곳
		datas = new Vector<>();
		
		model = new DefaultTableModel();
		
		Vector rows = db.getAllProductS();

		model.setDataVector(rows, colNames);
		
		productList = new JTable(model);
		productList.setRowHeight(30);
		productList.setFont(new Font("",Font.PLAIN,12));
		productList.getTableHeader().setFont(new Font("",Font.BOLD,15));
		productList.getTableHeader().setBackground(new Color(255,242,204));
		JScrollPane productListScroll = new JScrollPane(productList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		productListScroll.setSize(983, 508);
		productListScroll.setLocation(12, 10);

		// 占쎄맒占쎈�� 占쎌뿯占쎌젾 占쎈쨲
		contents = new JPanel();
		contents.setLayout(new GridLayout(3, 2, 5, 5));
		contents.setSize(660, 140);
		contents.setLocation(12, 528);
		contents.setBackground(productList.getTableHeader().getBackground());
		contents.setBorder(BorderFactory.createEmptyBorder(5 , 5 , 5 , 5));
		
		productCode = new JLabel("상 품 코 드");
		productCode.setFont(productList.getTableHeader().getFont());
		productCode.setHorizontalAlignment(JLabel.CENTER);
		proCodeTxt = new JTextField();
		productName = new JLabel("상 품 명");
		productName.setFont(productList.getTableHeader().getFont());
		productName.setHorizontalAlignment(JLabel.CENTER);
		proNameTxt = new JTextField();
		ProductPrice = new JLabel("가 격");
		ProductPrice.setFont(productList.getTableHeader().getFont());
		ProductPrice.setHorizontalAlignment(JLabel.CENTER);
		proPriceTxt = new JTextField();

		contents.add(productCode);
		contents.add(proCodeTxt);
		contents.add(productName);
		contents.add(proNameTxt);
		contents.add(ProductPrice);
		contents.add(proPriceTxt);

		// 甕곌쑵�뱣
		bottom = new JPanel();
		bottom.setLayout(null);
		bottom.setSize(320, 80);
		bottom.setLocation(680, 528);
		bottom.setBackground(productColor);
	
		h =0; w = 0;
		insertBtn = new JButton(changeSize(new ImageIcon("./image/proAdd.png")));
		insertBtn.setBackground(null);
		insertBtn.setBorderPainted(false);
		insertBtn.setFocusPainted(false);
		insertBtn.setFocusable(false);
		insertBtn.setSize(100, 80);
		insertBtn.setLocation(w,h);
		
		w = w+100;
		updateBtn = new JButton(changeSize(new ImageIcon("./image/proEdit.png")));
		updateBtn.setBackground(null);
		updateBtn.setBorderPainted(false);
		updateBtn.setFocusPainted(false);
		updateBtn.setSize(100, 80);
		updateBtn.setLocation(w,h);
		
		w = w+110;
		deleteBtn = new JButton(changeSize(new ImageIcon("./image/proDelete.png")));
		deleteBtn.setBackground(null);
		deleteBtn.setBorderPainted(false);
		deleteBtn.setFocusPainted(false);
		deleteBtn.setSize(100, 80);
		deleteBtn.setLocation(w,h);

		bottom.add(insertBtn);
		bottom.add(updateBtn);
		bottom.add(deleteBtn);

		/////////// add
		this.add(productListScroll);
		this.add(contents);
		this.add(bottom);
		
		pc = new ProductController();
		//pc.refresh();
	}

	void addButtonActionListener(ActionListener listener) {
		insertBtn.addActionListener(listener);
		updateBtn.addActionListener(listener);
		deleteBtn.addActionListener(listener);
	}
	public void addMouseListener(MouseListener listener){
		productList.addMouseListener(listener);
	}

	ImageIcon changeSize(ImageIcon temp) {
		Image tempImg = temp.getImage();
		tempImg = tempImg.getScaledInstance(100, 80, java.awt.Image.SCALE_SMOOTH);
		temp.setImage(tempImg);
		return temp;
	}
}
