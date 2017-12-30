package pos_coffee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TabPanel extends JPanel {
	// ��� �� �г�
	protected JButton mainBtn;// = new JButton("����"); // �����гη� �̵�
	protected JButton productBtn;// = new JButton("��ǰ����"); // ��ǰ���� �гη� �̵�
	protected JButton memberBtn;// = new JButton("ȸ������"); // ȸ������ �гη� �̵�
	protected JButton salesBtn;// = new JButton("�������"); // ������� �гη� �̵�
	protected JLabel dataClockLbl;// = new JLabel("�ð�"); // ��¥�� �ð� ǥ��

	void TabPanel() {
		AppManager.createInstance().setTabPanel(this);
		//setSize(200, 100);
		setBackground(Color.yellow);
		this.setLayout(null);

		mainBtn = new JButton("����");
		mainBtn.setBounds(0, 0, 50, 100);
		this.add(mainBtn);

		productBtn = new JButton("��ǰ����");
		this.add(productBtn);

		memberBtn = new JButton("ȸ������");
		this.add(memberBtn);

		salesBtn = new JButton("�������");
		this.add(salesBtn);

		dataClockLbl = new JLabel("�ð�");
		this.add(dataClockLbl);
	}

	void addButtonActionListener(ActionListener listener) {
		mainBtn.addActionListener(listener);
		productBtn.addActionListener(listener);
		memberBtn.addActionListener(listener);
		salesBtn.addActionListener(listener);
	}
}
