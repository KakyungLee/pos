package pos_coffee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TabPanel extends JPanel{
	//상단 탭 패널
	
	protected JButton mainBtn; //매인 패널 이동 
	protected JButton productBtn;//상품관리 패널로 이동
	protected JButton memberBtn; //회원관리 패널로 이동
	protected JButton salesBtn; //매출관리 패널로 이동
	protected JLabel dataClockLbl;//날짜와 시간 표시
	
	TabPanel(){
		
		//AppManager.createInstance().setTabPanel(this);
		this.setLayout(null);
		this.setSize(1024,80);
		this.setBackground(Color.WHITE);
		
		//
		int w = -2; int h = 0;
		mainBtn = new JButton(changeSize(new ImageIcon("./image/mainButton.png")));	//메인패널로 이동
		mainBtn.setBackground(null);
		mainBtn.setBorderPainted(false);
		mainBtn.setFocusPainted(false);
		mainBtn.setSize(200, 80);
		mainBtn.setLocation(w, h);
		w = w+193;

		productBtn = new JButton(changeSize(new ImageIcon("./image/productButton.png")));
		productBtn.setBackground(null);
		productBtn.setBorderPainted(false);
		productBtn.setFocusPainted(false);
		productBtn.setSize(200, 80);
		productBtn.setLocation(w, h);
		w = w+193;
		
		memberBtn = new JButton(changeSize(new ImageIcon("./image/memberButton.png")));
		memberBtn.setBackground(null);
		memberBtn.setBorderPainted(false);
		memberBtn.setFocusPainted(false);
		memberBtn.setSize(200, 80);
		memberBtn.setLocation(w, h);
		w = w+193;
		
		salesBtn = new JButton(changeSize(new ImageIcon("./image/salesButton.png")));
		salesBtn.setBackground(null);
		salesBtn.setBorderPainted(false);
		salesBtn.setFocusPainted(false);
		salesBtn.setSize(200, 80);
		salesBtn.setLocation(w, h);
		w = w+202;
		
		dataClockLbl = new JLabel(changeSize(new ImageIcon("./image/clockBackground.png")));
		dataClockLbl.setBackground(null);
		dataClockLbl.setSize(224, 80);
		dataClockLbl.setLocation(w, h);

		add(mainBtn);
		add(productBtn);
		add(memberBtn);
		add(salesBtn);
		add(dataClockLbl);
	}
	
	ImageIcon changeSize(ImageIcon temp) {
		Image tempImg = temp.getImage();// imageicon을 image로 변환
		if(temp.getIconWidth()==610){
			tempImg = tempImg.getScaledInstance(228, 74, java.awt.Image.SCALE_SMOOTH);
		}else {
			tempImg = tempImg.getScaledInstance(195, 80, java.awt.Image.SCALE_SMOOTH);
		}
		
		temp.setImage(tempImg);		
		return temp;
	}
	
	void addButtonActionListener(ActionListener listener) {
		mainBtn.addActionListener(listener);
		productBtn.addActionListener(listener);
		memberBtn.addActionListener(listener);
		salesBtn.addActionListener(listener);
	}
	
}

