package pos_coffee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import javax.swing.*;

public class TabPanel extends JPanel {
	// ��� �� �г�

	protected JButton mainBtn = new JButton("����"); // �����гη� �̵�
	protected JButton productBtn = new JButton("��ǰ����"); // ��ǰ���� �гη� �̵�
	protected JButton memberBtn = new JButton("ȸ������"); // ȸ������ �гη� �̵�
	protected JButton salesBtn = new JButton("�������"); // ������� �гη� �̵�
	protected JLabel dataClockLbl = new JLabel("�ð�"); // ��¥�� �ð� ǥ��

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

