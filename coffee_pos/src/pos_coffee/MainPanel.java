package pos_coffee;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class MainPanel extends JPanel{
	//���� ȭ��
		////// ���� ������Ʈ ����
		
		
		private int startX = 10;
		private int startY = 10;
		
		private int panel_hight = 740;
		private int half_panel_width = 640;

		
		
		//>>> ���� �г�(��ǰ����Ʈ, ���ù�ư, ���߰���ư, ���Ź�ư) ����
		private int l_margin = 0;
		private int l_gap = 10;
		
		protected JPanel mainLeftPanel;
			protected JList productList;
				private int proList_width = half_panel_width - (startX*2) - l_margin;
				private int proList_height = 550 - l_margin;
				
			protected JButton selectBtn;
				private int l_btnWidth = (proList_width - (l_gap + l_margin) * 2 ) / 3;
				private int l_btnHight = 100 - l_margin;
			protected JButton shotSelectBtn;
			protected JButton removeBtn;	
			
		
		//>>> �����г� ���� (���õ� ��ǰ����Ʈ, �Ѿ�, ȸ��, ���� ��) ����
		private int r_margin = 0;
		private int r_gap = 10;
		private int r_height  = 380;
		
		protected JPanel mainRightPanel;
			protected JList selectedProductList;
				private int selProList_width = half_panel_width - (startX*2) - l_margin;
				private int	selProList_hight = 320 - r_margin;
			private JLabel lbl1 = new JLabel("�Ѿ�");	
			protected JLabel pricelLbl;	//������ �Ѿ�
			private JLabel lbl2 = new JLabel("��");
		
		
			

			protected JPanel selectedProPanel;
					//���õ� ��ǰ. ��������Ʈ
				protected JPanel pricePanel;

			protected JPanel memberPayPanel;
				protected JPanel memberPanel;
					protected JTextField memberTxt = new JTextField("ȸ����ȣ�Է�");	//��ȸ�� ȸ�� �޴�����ȣ �Է�
					protected JButton searchBtn = new JButton("��ȸ");		//�ڵ��� ��ȣ�� ȸ�� ��ȸ -> ������ ����, ���� �ʼ�
					protected JLabel memberinfoLbl = new JLabel("::ȸ������::");	//ȸ�� ��ȸ�� ȸ������ ��Ÿ��
					protected JButton useStampBtn;	//������ ���
				protected JPanel payPanel;
					protected JButton clearBtn = new JButton("���");	//��������Ʈ �� ȸ�� ȭ�� Ŭ����
					protected JButton paymentBtn = new JButton("����");	//���� ���� �� ���� �߻�
						
		

		MainPanel(){
			
			
			/*�׽�Ʈ�� ����*/
			Vector<String>testVector;
			testVector = new Vector<String>();
			testVector.add("JList�׽�Ʈ�����");
			testVector.add("JList�׽�Ʈ�����2");
			/*�׽�Ʈ�� ����*/
			
			this.setLayout(null);
			this.setBackground(Color.yellow);
			
			
		
			// >>>>>>�����г� �׸���
			mainLeftPanel = new JPanel();
			mainLeftPanel.setBackground(Color.CYAN);
			mainLeftPanel.setBounds(0,0,half_panel_width,panel_hight);
			mainLeftPanel.setLayout(null);
			this.add(mainLeftPanel);
				productList = new JList(testVector);
				productList.setBackground(Color.RED);
				JScrollPane proListScroll =new JScrollPane(productList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				mainLeftPanel.add(proListScroll);
				proListScroll.setVisible(true);
				proListScroll.setBounds(startX, startY, proList_width, proList_height);
				
				//::���ù�ư
				selectBtn = new JButton("��ǰ����");
				mainLeftPanel.add(selectBtn);
				selectBtn.setBounds(startX, startY + proList_height + l_gap, l_btnWidth, l_btnHight);
				
				//::���߰���ư
				shotSelectBtn = new JButton("���߰� / ����");
				mainLeftPanel.add(shotSelectBtn);
				shotSelectBtn.setBounds(startX + (l_btnWidth + l_gap), startY + proList_height + l_gap, l_btnWidth, l_btnHight);
						
				//::������ҹ�ư
				removeBtn = new JButton("���� ���");
				mainLeftPanel.add(removeBtn);
				removeBtn.setBounds(startX + (l_btnWidth + l_gap)*2, startY + proList_height + l_gap, l_btnWidth, l_btnHight);
			
			
			mainRightPanel = new JPanel();
			mainRightPanel.setBackground(Color.GRAY);
			mainRightPanel.setBounds(half_panel_width,0,half_panel_width,380);
			mainRightPanel.setLayout(null);
			this.add(mainRightPanel);
				selectedProductList = new JList();
				selectedProductList.setBackground(Color.RED);
				JScrollPane proSelListScroll = new JScrollPane(selectedProductList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				mainRightPanel.add(proSelListScroll);
				proSelListScroll.setVisible(true);
				proSelListScroll.setBounds(startX, startY, selProList_width, selProList_hight);
				
				mainRightPanel.add(lbl1);
				lbl1.setBounds(startX, selProList_hight + r_gap, 150, 50);
			
				pricelLbl = new JLabel("9999");
				mainRightPanel.add(pricelLbl);
				pricelLbl.setBounds(400, selProList_hight + r_gap, 200, 50);
				
				mainRightPanel.add(lbl2);
				lbl2.setBounds(600, selProList_hight + r_gap, 150, 50);
				
				
			//this.setLayout(new GridLayout(1,2));
			//this.setLayout(null);
			
			
			
			/*���� �г� (��ǰ����Ʈ, ���ù�ư, ���߰���ư, ���Ź�ư) */
			//mainLeftPanel = new JPanel();
			//mainLeftPanel.setLayout(new BorderLayout());
			//mainLeftPanel.setPreferredSize(new Dimension(600, 400));
			//this.add(mainLeftPanel);
			
			//productList = new JList();
			//productList.setLocation(0,0);
			//productList.setSize(999,999);
			//this.add(productList);
			//this.add(new JScrollPane(productList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
			//productList.setPreferredSize(new Dimension(800,800));
			
				//productList = new JList();
				//mainLeftPanel.add(productList, BorderLayout.CENTER);	//��ǰ����Ʈ
				//mainLeftPanel.add(productList);
				//mainLeftPanel.add(new JScrollPane(productList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
				
				
				//leftBtnPanel = new JPanel();
				//leftBtnPanel.setLayout(new GridLayout(1,3));
				//mainLeftPanel.add(leftBtnPanel, BorderLayout.PAGE_END);	//��ư����
				//mainLeftPanel.add(leftBtnPanel);
				
					//selectBtn = new JButton("����");
					//leftBtnPanel.add(selectBtn);
					
					//shotSelectBtn = new JButton("���߰�/����");
					//leftBtnPanel.add(shotSelectBtn);
					
					//removeBtn = new JButton("����");
					//leftBtnPanel.add(removeBtn);
					
					
					
			/*
					
			/*���� �г� (���õ� ��ǰ ����Ʈ, �ݾ�, �ɹ�, ����	
			mainRightPanel = new JPanel();
			//mainRightPanel.setLayout(new GridLayout(2,2)); //���Ϻ���
			this.add(mainRightPanel);
			
				selectedProPanel= new JPanel();
				selectedProPanel.setLayout(new BorderLayout());
				mainRightPanel.add(selectedProPanel);
				
					selectedProductList = new JList();
					//selectedProPanel.add(new JScrollPane(selectedProductList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
					selectedProPanel.add(selectedProductList, BorderLayout.EAST);

					pricePanel = new JPanel();
					//pricePanel.setLayout();
					selectedProPanel.add(pricePanel, BorderLayout.PAGE_END);
						
						pricePanel.add(lbl1);	//�Ѿ�
						
						pricelLbl = new JLabel("999999");	//�����ݾ�
						selectedProPanel.add(pricelLbl);	
						
						pricePanel.add(lbl2);	//��
					
						
			/*
					
			
			setBackground(Color.white);
			//add(selectBtn);
			//add(shotSelectBtn);
			//add(removeBtn);
			//add(searchBtn);
			//add(useStampBtn);
			//add(clearBtn);
			//add(paymentBtn);
			//productList = new JList();
			//add(productList);
			//add(new JScrollPane(selectedProductList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
			//add(memberTxt);
			//add(memberinfoLbl);
			
			
			/*�׽�Ʈ��*/
			//productList.setListData(testVector);
			selectedProductList.setListData(testVector);
			/*�׽�Ʈ��*/
			
			
		}
	
}
