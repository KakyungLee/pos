package pos_coffee;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class MainPanel extends JPanel {
	// ���� ȭ��
	////// ���� ������Ʈ ����
	private int startX = 10;
	private int startY = 10;

	private int panel_hight = 740;			//�����г��� ���̰�
	private int half_panel_width = 500;		//�����г��� ���� 2��� ��

	// >>> ���� �г�(��ǰ����Ʈ, ���ù�ư, ���߰���ư, ���Ź�ư) ����
	private int l_margin = 0;
	private int l_gap = 10;

	protected JPanel mainLeftPanel;
	protected JList productList;
	private int proList_width = half_panel_width - (startX * 2) - l_margin;		
	private int proList_height = 540 - l_margin;

	protected JButton selectBtn;
	private int l_btnWidth = (proList_width - (l_gap + l_margin) * 2) / 3;
	private int l_btnHight = 100 - l_margin;
	protected JButton shotSelectBtn;
	protected JButton removeBtn;

	// >>> �����г� ���� (���õ� ��ǰ����Ʈ, �Ѿ�, ȸ��, ���� ��) ����
	private int r_margin = 0;
	private int r_gap = 10;
	private int r_height = 370;		//�� �г��� ����

	protected JPanel mainRightPanel;
	protected JList selectedProductList;
	private int selProList_width = half_panel_width - (startX * 2) - l_margin;
	private int selProList_hight = 290 - r_margin;
	private JLabel lbl1 = new JLabel("�Ѿ�");
	protected JTextField pricelLbl; // ������ �Ѿ�
	private JLabel lbl2 = new JLabel("��");


	// >>> ���� ȸ�� �г� ���� (ȸ����ȣ �Է�, ȸ������ ��, ������ ��� ��ư)
	private int m_margin = 0;
	private int m_gap = 10;
	private int m_height = (int)((panel_hight - r_height) * 0.48);
	private int m_LeftWidth = (int) (half_panel_width * 0.7) - m_gap;
	private int m_RightWidth = (int) (half_panel_width * 0.3)- m_gap*2;
		
	protected JPanel memberPanel;
	protected JTextField memberTxt; // ��ȸ�� ȸ�� �޴�����ȣ
	protected JButton searchBtn; // �ڵ��� ��ȣ�� ȸ�� ��ȸ -> ������ ����,
	protected JTextArea memberinfoLbl; // ȸ�� ��ȸ�� ȸ������ ��Ÿ��
	protected JButton useStampBtn; // ������ ���
	
	
	// >>> ���� ������ư, �ʱ�ȭ ��ư �г� ����
	private int p_height = (int)((panel_hight - r_height) * 0.52);
	private int p_btnWidth = (half_panel_width - (m_gap *3)) /2 ;
	private int p_btnHeight = 100;
	
	protected JPanel payPanel;
	protected JButton clearBtn; // ��������Ʈ �� ȸ�� ȭ�� Ŭ����
	protected JButton paymentBtn; // ���� ���� �� ���� �߻�
	

	private MainController mc;	
	
	MainPanel() {

		
		/*��Ʈ ����*/
		Font listFont = new Font("���� ���", Font.PLAIN, 24);
		Font priceFont = new Font("���� ���", Font.PLAIN, 28);
		Font mInfoFont = new Font("���� ���", Font.PLAIN, 20);
		
		/* �׽�Ʈ�� ���� */
		Vector<String> testVector;
		testVector = new Vector<String>();
		testVector.add("JList�׽�Ʈ�����");
		testVector.add("JList�׽�Ʈ�����2");
		/* �׽�Ʈ�� ���� */

		AppManager.createInstance().setMainPanel(this);
		this.setLayout(null);
		//this.setBackground(Color.yellow);

		/////////////������Ʈ �׸��� ����
		// >>>> �����г� �׸���
		mainLeftPanel = new JPanel();
		//mainLeftPanel.setBackground(Color.CYAN);
		mainLeftPanel.setBounds(0, 0, half_panel_width, panel_hight);
		mainLeftPanel.setLayout(null);
		this.add(mainLeftPanel);
		productList = new JList();		//��ǰ ����Ʈ
		//productList.setBackground(Color.RED);	
		productList.setFont(listFont);	//��Ʈ ����
		JScrollPane proListScroll = new JScrollPane(productList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainLeftPanel.add(proListScroll);
		proListScroll.setVisible(true);
		proListScroll.setBounds(startX, startY, proList_width, proList_height);

		// ::���ù�ư
		selectBtn = new JButton(changeSize (new ImageIcon("./image/main_selectBtn.png"),l_btnWidth , l_btnHight));
		mainLeftPanel.add(selectBtn);
		selectBtn.setBounds(startX, startY + proList_height + l_gap, l_btnWidth, l_btnHight);

		// ::���߰���ư
		shotSelectBtn = new JButton(changeSize (new ImageIcon("./image/main_shotSelectBtn.png"),l_btnWidth , l_btnHight));
		mainLeftPanel.add(shotSelectBtn);
		shotSelectBtn.setBounds(startX + (l_btnWidth + l_gap), startY + proList_height + l_gap, l_btnWidth, l_btnHight);

		// ::������ҹ�ư
		removeBtn = new JButton(changeSize (new ImageIcon("./image/main_removeBtn.png"),l_btnWidth , l_btnHight));
		mainLeftPanel.add(removeBtn);
		removeBtn.setBounds(startX + (l_btnWidth + l_gap) * 2, startY + proList_height + l_gap, l_btnWidth, l_btnHight);

		
		// >>>> ���� �г�
		// >> ���õ� ��ǰ ����Ʈ & �Ѿ� �г�
		mainRightPanel = new JPanel();
		//mainRightPanel.setBackground(Color.GRAY);
		mainRightPanel.setBounds(half_panel_width, 0, half_panel_width, r_height);
		mainRightPanel.setLayout(null);
		this.add(mainRightPanel);
		selectedProductList = new JList();	//���õ� ��ǰ ����Ʈ
		//selectedProductList.setBackground(Color.RED);
		selectedProductList.setFont(listFont);
		JScrollPane proSelListScroll = new JScrollPane(selectedProductList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainRightPanel.add(proSelListScroll);
		proSelListScroll.setVisible(true);
		proSelListScroll.setBounds(startX, startY, selProList_width, selProList_hight);

		mainRightPanel.add(lbl1);	//"�Ѿ�"
		lbl1.setBounds(startX, selProList_hight + r_gap*2, 150, 50);
		lbl1.setFont(priceFont);

		// :: ������ ���� �ݾ� ��
		pricelLbl = new JTextField("9999");	// ���� ǥ�õǴ� �ݾ�
		pricelLbl.setEditable(false);
		mainRightPanel.add(pricelLbl);
		pricelLbl.setBounds(250, selProList_hight + r_gap*2, 200, 50);
		pricelLbl.setFont(priceFont);

		mainRightPanel.add(lbl2);	// "��"
		lbl2.setBounds(450, selProList_hight + r_gap*2, 150, 50);
		lbl2.setFont(priceFont);

		
		// >> ȸ�� ��ȸ & ������ ��� �г�
		memberPanel = new JPanel();
		//memberPanel.setBackground(Color.orange);
		memberPanel.setBounds(half_panel_width, r_height, half_panel_width, m_height);
		memberPanel.setLayout(null);
		this.add(memberPanel);
		
		memberTxt = new JTextField("ȸ����ȣ�Է�");
		memberTxt.setBounds(startX, startY, m_LeftWidth, 60);
		memberTxt.setFont(priceFont);
		memberPanel.add(memberTxt);
		
		searchBtn = new JButton(changeSize (new ImageIcon("./image/main_searchBtn.png"),m_RightWidth , 60));
		searchBtn.setBounds(m_LeftWidth + m_gap*2, startY, m_RightWidth, 60);
		memberPanel.add(searchBtn);
		
		// :: ȸ������ ǥ�� �ؽ�Ʈ
		memberinfoLbl = new JTextArea();
		memberinfoLbl.setBounds(startX , 60 + m_gap*2 , m_LeftWidth, 90);
		memberinfoLbl.setEditable(false);
		memberinfoLbl.setFont(mInfoFont);
		memberPanel.add(memberinfoLbl);
		
		useStampBtn = new JButton(changeSize (new ImageIcon("./image/main_useStampBtn.png"),m_RightWidth , 90));
		useStampBtn.setBounds(m_LeftWidth + m_gap*2, 60 + m_gap*2, m_RightWidth, 90);
		memberPanel.add(useStampBtn);
		
		
		// >> ���� ��ư �г�
		payPanel = new JPanel();
		//payPanel.setBackground(Color.PINK);
		payPanel.setBounds(half_panel_width, r_height + m_height, half_panel_width, p_height);
		payPanel.setLayout(null);
		this.add(payPanel);
		
		clearBtn = new JButton(changeSize (new ImageIcon("./image/main_clearBtn.png"),p_btnWidth , p_btnHeight));
		clearBtn.setBounds(startX, startY , p_btnWidth, p_btnHeight);
		payPanel.add(clearBtn);
		
		paymentBtn = new JButton(changeSize (new ImageIcon("./image/main_paymentBtn.png"),p_btnWidth , p_btnHeight));
		paymentBtn.setBounds(p_btnWidth + m_gap*2, startY , p_btnWidth, p_btnHeight);
		payPanel.add(paymentBtn);

		/////////////������Ʈ �׸��� ��
		
		
		/*���� �׽�Ʈ�� ����*/
		selectedProductList.setListData(testVector);
		productList.setListData(testVector);
		memberinfoLbl.append("�̸� : ����\n");
		memberinfoLbl.append("ȸ����ȣ : 01062838520\n");
		memberinfoLbl.append("���� ������ ���� : 10");
		/*�׽�Ʈ�� ����*/
		
		
		mc = AppManager.createInstance().getMainController();
	}
	
	ImageIcon changeSize(ImageIcon temp, int width, int height) {
		Image tempImg = temp.getImage();
		tempImg = tempImg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		temp.setImage(tempImg);
		return temp;
	}

	void addButtonAction(ActionListener listener){
		selectBtn.addActionListener(listener);
		shotSelectBtn.addActionListener(listener);
		removeBtn.addActionListener(listener);
	}

}
