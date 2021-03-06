package pos_coffee;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ProductPanel extends JPanel {
	private DBDAO db;
	/////////////// header
	protected JTable productList;
	protected DefaultTableModel model;
	protected Object[] colNames = null;
	protected Object[][] rows;
	protected ArrayList<Product> datas;

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

	public ProductPanel() {		
		/*
		 * Shim Soo 
		 * AppManager에 ProductPanel을 set해주고 db가 필요하므로 get	 
		 */
		AppManager.createInstance().setProductPanel(this);
		db = AppManager.createInstance().getDao();

		this.setLayout(null);
		this.setSize(1024, 720);
		productColor = new Color(255,192,0);
		this.setBackground(productColor);
		
		Font contentFont = new Font("맑은 고딕", Font.PLAIN, 18);

		/*
		 * Shim Soo 
		 * 상품 JTable setting	 
		 */
		// 테이블 컬럼 명
		colNames = new Object[3];
		colNames[0] = "상 품 코 드";
		colNames[1] = "상 품 명";
		colNames[2] = "가 격";

		// 처음 화면에 뿌려주는 곳
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

		model = new DefaultTableModel(rows,colNames);
		productList = new JTable(model);
		productList.setRowHeight(30);
		productList.setFont(new Font("",Font.PLAIN,16));
		productList.getTableHeader().setFont(new Font("맑은 고딕",Font.BOLD,16));
		productList.getTableHeader().setBackground(new Color(255,242,204));
		JScrollPane productListScroll = new JScrollPane(productList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		productListScroll.setSize(978, 495);
		productListScroll.setLocation(12, 10);

		// 占쎄맒占쎈�� 占쎌뿯占쎌젾 占쎈쨲
		contents = new JPanel();
		contents.setLayout(new GridLayout(3, 2, 5, 5));
		contents.setSize(660, 140);
		contents.setLocation(12, 515);
		contents.setBackground(productList.getTableHeader().getBackground());
		contents.setBorder(BorderFactory.createEmptyBorder(5 , 5 , 5 , 5));
		

		productCode = new JLabel("상 품 코 드");
		productCode.setFont(contentFont);
		productCode.setHorizontalAlignment(JLabel.CENTER);
		proCodeTxt = new JTextField();
		proCodeTxt.setFont(contentFont);
		productName = new JLabel("상 품 명");
		productName.setFont(contentFont);
		productName.setHorizontalAlignment(JLabel.CENTER);
		proNameTxt = new JTextField();
		proNameTxt.setFont(contentFont);
		ProductPrice = new JLabel("가 격");
		ProductPrice.setFont(contentFont);
		ProductPrice.setHorizontalAlignment(JLabel.CENTER);
		proPriceTxt = new JTextField();
		proPriceTxt.setFont(contentFont);

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
		insertBtn = new JButton(changeSize(new ImageIcon("./image/add.png")));
		insertBtn.setBackground(null);
		insertBtn.setBorderPainted(false);
		insertBtn.setFocusPainted(false);
		insertBtn.setFocusable(false);
		insertBtn.setSize(100, 80);
		insertBtn.setLocation(w,h);
		
		w = w+110;
		updateBtn = new JButton(changeSize(new ImageIcon("./image/edit.png")));
		updateBtn.setBackground(null);
		updateBtn.setBorderPainted(false);
		updateBtn.setFocusPainted(false);
		updateBtn.setSize(100, 80);
		updateBtn.setLocation(w,h);
		
		w = w+110;
		deleteBtn = new JButton(changeSize(new ImageIcon("./image/delete.png")));
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
	}
	ImageIcon changeSize(ImageIcon temp) {
		Image tempImg = temp.getImage();
		tempImg = tempImg.getScaledInstance(100, 80, java.awt.Image.SCALE_SMOOTH);
		temp.setImage(tempImg);
		return temp;
	}
	
	/*
	 * Shim Soo 
	 * Controller와 연결하기 위한 메소드
	 * addButtonActionListener: 버튼에 대한 actionListener
	 * addMouseListener: JTable에 대한 actionListener 
	 */
	void addButtonActionListener(ActionListener listener) {
		insertBtn.addActionListener(listener);
		updateBtn.addActionListener(listener);
		deleteBtn.addActionListener(listener);
	}
	public void addMouseListener(MouseListener listener){
		productList.addMouseListener(listener);
	}	
}
