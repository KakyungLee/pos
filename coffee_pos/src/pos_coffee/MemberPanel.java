package pos_coffee;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MemberPanel extends JPanel{
	private MemberController mc;
	private DBDAO db;
///////////////header
	JTable memberList;
	DefaultTableModel model;
	Object[] colNames;
	Object[][] rows;
	ArrayList<Member> datas;
	///////////// center
	JPanel contents;
	
	JLabel memberCode;
	JLabel memberName;
	JLabel memberPhone;
	
	JTextField idxTxt;
	JTextField memNameTxt;
	JTextField memPhoneTxt;
	//////////// bottom
	JPanel bottom;
	JButton insertBtn;
	JButton updateBtn;
	JButton deleteBtn;
	//////////
	Color memberColor;
	int w, h;
	
	public MemberPanel() {
		AppManager.createInstance().setMemberPanel(this);
		db = AppManager.createInstance().getDao();
		this.setLayout(null);
		this.setSize(1024,720);
		memberColor = new Color(112, 173, 71);
		this.setBackground(memberColor);
		
		// 테이블 컬럼 명
		colNames = new Object[4];
		colNames[0] = "회 원 번 호";
		colNames[1] = "이름";
		colNames[2] = "휴대폰번호";
		colNames[3] = "스탬프";
		
		// 처음 화면에 뿌러주는 곳
		datas = new ArrayList<Member>();
		datas = db.getAllMember();
		rows = new Object[datas.size()][4];
		
		int i = 0;
		for (Member p : datas) {
			rows[i][0] = p.getMemno(); 
			rows[i][1] = p.getMemname();
			rows[i][2] = p.getMemphone(); 
			rows[i][3] = p.getMemstamp();
			i++;
		}
		
		model = new DefaultTableModel(rows,colNames);
		memberList = new JTable(model);
		memberList.setRowHeight(30);
		memberList.setFont(new Font("",Font.PLAIN,12));
		memberList.getTableHeader().setFont(new Font("",Font.BOLD,15));
		memberList.getTableHeader().setBackground(new Color(226, 240, 217));
		JScrollPane memberListScroll = new JScrollPane(memberList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memberListScroll.setSize(983,508);
		memberListScroll.setLocation(12, 10);
		
		// ��ǰ �Է� �� 
		contents = new JPanel();
		contents.setLayout(new GridLayout(3,2,5,5));
		contents.setSize(660,140);
		contents.setLocation(12,528);
		contents.setBackground(memberList.getTableHeader().getBackground());
		contents.setBorder(BorderFactory.createEmptyBorder(5 , 5 , 5 , 5));
		
		memberCode = new JLabel("회 원 번 호");
		memberCode.setFont(memberList.getTableHeader().getFont());
		memberCode.setHorizontalAlignment(JLabel.CENTER);
		idxTxt = new JTextField();
		memberName = new JLabel("이 름");
		memberName.setFont(memberList.getTableHeader().getFont());
		memberName.setHorizontalAlignment(JLabel.CENTER);
		memNameTxt = new JTextField();
		memberPhone = new JLabel("휴 대 폰 번 호");
		memberPhone.setFont(memberList.getTableHeader().getFont());
		memberPhone.setHorizontalAlignment(JLabel.CENTER);
		memPhoneTxt = new JTextField();

		contents.add(memberCode);
		contents.add(idxTxt);
		contents.add(memberName);
		contents.add(memNameTxt);
		contents.add(memberPhone);
		contents.add(memPhoneTxt);
		
		// ��ư 
		bottom = new JPanel();
		bottom.setLayout(null);
		bottom.setSize(320, 80);
		bottom.setLocation(680, 528);
		bottom.setBackground(memberColor);
	
		h =0; w = 0;
		insertBtn = new JButton(changeSize(new ImageIcon("./image/memAdd.png")));
		insertBtn.setBackground(null);
		insertBtn.setBorderPainted(false);
		insertBtn.setFocusPainted(false);
		insertBtn.setFocusable(false);
		insertBtn.setSize(100, 80);
		insertBtn.setLocation(w,h);
			
		w = w+100;
		updateBtn = new JButton(changeSize(new ImageIcon("./image/memEdit.png")));
		updateBtn.setBackground(null);
		updateBtn.setBorderPainted(false);
		updateBtn.setFocusPainted(false);
		updateBtn.setSize(100, 80);
		updateBtn.setLocation(w,h);
		
		w = w+110;
		deleteBtn = new JButton(changeSize(new ImageIcon("./image/memDelete.png")));
		deleteBtn.setBackground(null);
		deleteBtn.setBorderPainted(false);
		deleteBtn.setFocusPainted(false);
		deleteBtn.setSize(100, 80);
		deleteBtn.setLocation(w,h);

		bottom.add(insertBtn);
		bottom.add(updateBtn);
		bottom.add(deleteBtn);
		
		
		///////////add
		this.add(memberListScroll);
		this.add(contents);
		this.add(bottom);
		
		mc = new MemberController();
		mc.refresh();

	}
	void addButtonActionListener(ActionListener listener) {
		insertBtn.addActionListener(listener);
		updateBtn.addActionListener(listener);
		deleteBtn.addActionListener(listener);
	}
	public void addMouseListener(MouseListener listener){
		memberList.addMouseListener(listener);
	}
	ImageIcon changeSize(ImageIcon temp) {
		Image tempImg = temp.getImage();
		tempImg = tempImg.getScaledInstance(100, 80, java.awt.Image.SCALE_SMOOTH);
		temp.setImage(tempImg);
		return temp;
	}

}
