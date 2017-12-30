package pos_coffee;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class MainPanel extends JPanel{
	//메인 화면
		////// 각종 컴포넌트 정의
		
		
		private int startX = 10;
		private int startY = 10;
		
		private int panel_hight = 740;
		private int half_panel_width = 640;

		
		
		//>>> 좌측 패널(상품리스트, 선택버튼, 샷추가버튼, 제거버튼) 정의
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
			
		
		//>>> 우측패널 정의 (선택된 상품리스트, 총액, 회원, 결제 등) 정의
		private int r_margin = 0;
		private int r_gap = 10;
		private int r_height  = 380;
		
		protected JPanel mainRightPanel;
			protected JList selectedProductList;
				private int selProList_width = half_panel_width - (startX*2) - l_margin;
				private int	selProList_hight = 320 - r_margin;
			private JLabel lbl1 = new JLabel("총액");	
			protected JLabel pricelLbl;	//결제할 총액
			private JLabel lbl2 = new JLabel("원");
		
		
			

			protected JPanel selectedProPanel;
					//선택된 상품. 결제리스트
				protected JPanel pricePanel;

			protected JPanel memberPayPanel;
				protected JPanel memberPanel;
					protected JTextField memberTxt = new JTextField("회원번호입력");	//조회할 회원 휴대폰번호 입력
					protected JButton searchBtn = new JButton("조회");		//핸드폰 번호로 회원 조회 -> 스탬프 적립, 사용시 필수
					protected JLabel memberinfoLbl = new JLabel("::회원정보::");	//회원 조회시 회원정보 나타냄
					protected JButton useStampBtn;	//스탬프 사용
				protected JPanel payPanel;
					protected JButton clearBtn = new JButton("취소");	//결제리스트 및 회원 화면 클리어
					protected JButton paymentBtn = new JButton("결제");	//결제 진행 및 매출 발생
						
		

		MainPanel(){
			
			
			/*테스트용 변수*/
			Vector<String>testVector;
			testVector = new Vector<String>();
			testVector.add("JList테스트용백터");
			testVector.add("JList테스트용백터2");
			/*테스트용 변수*/
			
			this.setLayout(null);
			this.setBackground(Color.yellow);
			
			
		
			// >>>>>>좌측패널 그리기
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
				
				//::선택버튼
				selectBtn = new JButton("상품선택");
				mainLeftPanel.add(selectBtn);
				selectBtn.setBounds(startX, startY + proList_height + l_gap, l_btnWidth, l_btnHight);
				
				//::샷추가버튼
				shotSelectBtn = new JButton("샷추가 / 선택");
				mainLeftPanel.add(shotSelectBtn);
				shotSelectBtn.setBounds(startX + (l_btnWidth + l_gap), startY + proList_height + l_gap, l_btnWidth, l_btnHight);
						
				//::선택취소버튼
				removeBtn = new JButton("선택 취소");
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
			
			
			
			/*좌측 패널 (상품리스트, 선택버튼, 샷추가버튼, 제거버튼) */
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
				//mainLeftPanel.add(productList, BorderLayout.CENTER);	//상품리스트
				//mainLeftPanel.add(productList);
				//mainLeftPanel.add(new JScrollPane(productList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
				
				
				//leftBtnPanel = new JPanel();
				//leftBtnPanel.setLayout(new GridLayout(1,3));
				//mainLeftPanel.add(leftBtnPanel, BorderLayout.PAGE_END);	//버튼모음
				//mainLeftPanel.add(leftBtnPanel);
				
					//selectBtn = new JButton("선택");
					//leftBtnPanel.add(selectBtn);
					
					//shotSelectBtn = new JButton("샷추가/선택");
					//leftBtnPanel.add(shotSelectBtn);
					
					//removeBtn = new JButton("제거");
					//leftBtnPanel.add(removeBtn);
					
					
					
			/*
					
			/*우측 패널 (선택된 상품 리스트, 금액, 맴버, 결제	
			mainRightPanel = new JPanel();
			//mainRightPanel.setLayout(new GridLayout(2,2)); //상하분할
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
						
						pricePanel.add(lbl1);	//총액
						
						pricelLbl = new JLabel("999999");	//실제금액
						selectedProPanel.add(pricelLbl);	
						
						pricePanel.add(lbl2);	//원
					
						
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
			
			
			/*테스트용*/
			//productList.setListData(testVector);
			selectedProductList.setListData(testVector);
			/*테스트용*/
			
			
		}
	
}
