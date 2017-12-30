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

	JButton insertUpdateBtn;
	JButton deleteBtn;
	
	public MemberPanel() {
		AppManager.createInstance().setMemberPanel(this);
		db = AppManager.createInstance().getDao();
		mc = AppManager.createInstance().getMemberController();
		this.setLayout(null);
		this.setSize(1024,720);
		
		// ��ǰ ���		
		colNames = new Object[3];
		colNames[0] = "ȸ����ȣ";
		colNames[1] = "�̸�";
		colNames[2] = "�޴��� ��ȣ";
		
		datas = new ArrayList<Member>();
		
		model = new DefaultTableModel(rows,colNames);
		memberList = new JTable(model);
		memberList.setSize(983,400);
		memberList.setLocation(12, 10);
		
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
		insertUpdateBtn = new JButton("�߰�/����");
		deleteBtn = new JButton("Ż��");
		
		bottom.add(insertUpdateBtn);
		bottom.add(deleteBtn);
		
		
		///////////add
		this.add(memberList);
		this.add(contents);
		this.add(bottom);
		
		mc = new MemberController();
		mc.refresh();		
	}
	void addButtonActionListener(ActionListener listener) {
		insertUpdateBtn.addActionListener(listener);
		deleteBtn.addActionListener(listener);
	}
	public void addMouseListener(MouseListener listener){
		memberList.addMouseListener(listener);
	}

}
