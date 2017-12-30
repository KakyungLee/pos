package pos_coffee;

import java.awt.*;

import javax.swing.*;


public class MemberPanel extends JPanel{
///////////////header
	JList memberList;
	///////////// center
	JPanel contents;
	
	JLabel memberCode;
	JLabel memberName;
	JLabel memberPhone;
	
	JComboBox idxCombo;
	JTextField memNameTxt;
	JTextField memPhoneTxt;
	//////////// bottom
	JPanel bottom;
	
	JButton selectBtn;
	JButton insertUpdateBtn;
	JButton deleteBtn;
	
	public MemberPanel() {
		this.setLayout(null);
		this.setSize(1024,720);
		
		// ��ǰ ���
		memberList = new JList();
		memberList.setSize(983,400);
		memberList.setLocation(12, 10);
		
		// ��ǰ �Է� �� 
		contents = new JPanel();
		contents.setLayout(new GridLayout(1,6,5,10));
		contents.setSize(983,50);
		contents.setLocation(12,420);
		contents.setBackground(Color.white);
		
		memberCode = new JLabel("ȸ����ȣ");
		idxCombo = new JComboBox();
		memberName = new JLabel("�̸�");
		memNameTxt = new JTextField();
		memberPhone = new JLabel("�޴��� ��ȣ");
		memPhoneTxt = new JTextField();

		contents.add(memberCode);
		contents.add(idxCombo);
		contents.add(memberName);
		contents.add(memNameTxt);
		contents.add(memberPhone);
		contents.add(memPhoneTxt);
		
		// ��ư 
		bottom  = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.setSize(980,50);
		bottom.setLocation(12,490);
		selectBtn = new JButton("��ȸ");
		insertUpdateBtn = new JButton("�߰�/����");
		deleteBtn = new JButton("Ż��");
		
		bottom.add(selectBtn);
		bottom.add(insertUpdateBtn);
		bottom.add(deleteBtn);
		
		
		///////////add
		this.add(memberList);
		this.add(contents);
		this.add(bottom);
	}

}
