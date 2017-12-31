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
	
	public MemberPanel() {
		AppManager.createInstance().setMemberPanel(this);
		db = AppManager.createInstance().getDao();
		//mc = AppManager.createInstance().getMemberController();
		this.setLayout(null);
		this.setSize(1024,720);
		
		// ��ǰ ���		
		colNames = new Object[4];
		colNames[0] = "회원번호";
		colNames[1] = "이름";
		colNames[2] = "휴대폰번호";
		colNames[3] = "스탬프";
		
		datas = new ArrayList<Member>();
		datas = db.getAllMember();
		rows = new Object[datas.size()][4];
		int i = 0;
		for (Member p : datas) {
			rows[i][0] = p.getMemno(); 
			rows[i][1] = p.getMemname();
			rows[i][2] = p.getMemphone(); /// ���Ⱑ �����ϴ�  �Ʊ� Ŭ���Ҷ� ��� ���� ��
			rows[i][3] = p.getMemstamp();
			i++;
		}
		
		model = new DefaultTableModel(rows,colNames);
		memberList = new JTable(model);
		JScrollPane memberListScroll = new JScrollPane(memberList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memberListScroll.setSize(983,400);
		memberListScroll.setLocation(12, 10);
		
		// ��ǰ �Է� �� 
		contents = new JPanel();
		contents.setLayout(new GridLayout(1,6,5,10));
		contents.setSize(983,50);
		contents.setLocation(12,420);
		contents.setBackground(Color.white);
		
		memberCode = new JLabel("ȸ����ȣ");
		idxTxt = new JTextField();
		memberName = new JLabel("�̸�");
		memNameTxt = new JTextField();
		memberPhone = new JLabel("�޴��� ��ȣ");
		memPhoneTxt = new JTextField();

		contents.add(memberCode);
		contents.add(idxTxt);
		contents.add(memberName);
		contents.add(memNameTxt);
		contents.add(memberPhone);
		contents.add(memPhoneTxt);
		
		// ��ư 
		bottom  = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.setSize(980,50);
		bottom.setLocation(12,490);
		insertBtn = new JButton("삽입");
		updateBtn = new JButton("수정");
		deleteBtn = new JButton("삭제");
		
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

}
