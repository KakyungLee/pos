package pos_coffee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import javax.swing.*;

public class TabPanel extends JPanel {
	// 상단 탭 패널

	protected JButton mainBtn = new JButton("메인"); // 메인패널로 이동
	protected JButton productBtn = new JButton("상품관리"); // 상품관리 패널로 이동
	protected JButton memberBtn = new JButton("회원관리"); // 회원관리 패널로 이동
	protected JButton salesBtn = new JButton("매출관리"); // 매출관리 패널로 이동
	protected JLabel dataClockLbl = new JLabel("시계"); // 날짜와 시간 표시

	void TabPanel() {

		AppManager.createInstance().setTabPanel(this);
		setSize(200,100);
		setBackground(Color.lightGray);
		add(mainBtn);
		add(productBtn);
		add(memberBtn);
		add(salesBtn);
		add(dataClockLbl);
	}
	void addButtonActionListener(ActionListener listener){
		mainBtn.addActionListener(listener);
		productBtn.addActionListener(listener);
		memberBtn.addActionListener(listener);
		salesBtn.addActionListener(listener);		
	}
}

