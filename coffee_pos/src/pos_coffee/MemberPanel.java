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
		
		// 상품 목록
		memberList = new JList();
		memberList.setSize(983,400);
		memberList.setLocation(12, 10);
		
		// 상품 입력 폼 
		contents = new JPanel();
		contents.setLayout(new GridLayout(1,6,5,10));
		contents.setSize(983,50);
		contents.setLocation(12,420);
		contents.setBackground(Color.white);
		
		memberCode = new JLabel("회원번호");
		idxCombo = new JComboBox();
		memberName = new JLabel("이름");
		memNameTxt = new JTextField();
		memberPhone = new JLabel("휴대폰 번호");
		memPhoneTxt = new JTextField();

		contents.add(memberCode);
		contents.add(idxCombo);
		contents.add(memberName);
		contents.add(memNameTxt);
		contents.add(memberPhone);
		contents.add(memPhoneTxt);
		
		// 버튼 
		bottom  = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.setSize(980,50);
		bottom.setLocation(12,490);
		selectBtn = new JButton("조회");
		insertUpdateBtn = new JButton("추가/수정");
		deleteBtn = new JButton("탈퇴");
		
		bottom.add(selectBtn);
		bottom.add(insertUpdateBtn);
		bottom.add(deleteBtn);
		
		
		///////////add
		this.add(memberList);
		this.add(contents);
		this.add(bottom);
	}

}
