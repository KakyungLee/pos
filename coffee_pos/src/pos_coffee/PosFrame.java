package pos_coffee;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PosFrame extends JFrame{
	
	JPanel top;
	JPanel contents;
	CardLayout cardLayout;
	
	TabPanel tabP;
	MainPanel mainP;
	SalesPanel salesP;
	ProductPanel productP;
	MemberPanel memberP;
	String[] contentsName = {"main","product","member","sales"};

	public PosFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1024,800);
		setTitle("테스트");
		setLayout(null);

		tabP = new TabPanel();
		mainP = new MainPanel();
		salesP = new SalesPanel();
		productP = new ProductPanel();
		memberP = new MemberPanel();
		
		top = new JPanel();
		top.setSize(1024,80);
		top.setLocation(0, 0);
		top.add(tabP);

		contents = new JPanel();		
		contents.setSize(1024, 720);
		contents.setLocation(0,80);
		contents.setLayout(new CardLayout());
		contents.add(contentsName[0],mainP);		
		contents.add(contentsName[1],productP);
		contents.add(contentsName[2],memberP);
		contents.add(contentsName[3],salesP);
		((CardLayout)contents.getLayout()).show(contents, contentsName[0]);


		////////////////////////
		top.setBackground(Color.BLUE);
		contents.setBackground(Color.white);
		///////////////////////////
		this.add(top,"top");
		this.add(contents,"contents");
		
		setVisible(true);
	}
	
	public void addButtonActionListener(ActionListener listener) {
		tabP.mainBtn.addActionListener(listener);
		tabP.productBtn.addActionListener(listener);
		tabP.memberBtn.addActionListener(listener);
		tabP.salesBtn.addActionListener(listener);
		
		mainP.removeBtn.addActionListener(listener);
		mainP.searchBtn.addActionListener(listener);
		mainP.selectBtn.addActionListener(listener);
		mainP.useStampBtn.addActionListener(listener);
		mainP.paymentBtn.addActionListener(listener);
		mainP.clearBtn.addActionListener(listener);
		
		salesP.dateSelectBtn.addActionListener(listener);
		salesP.salesSelectBtn.addActionListener(listener);
		salesP.refundBtn.addActionListener(listener);
		// 왜 선택 창이 안ㄴ올까?
		// //ㄴ하 ㅐㄴ꺼랑 똑 같이 ㅐㅎㅅ는데 ㅜㅜ
		//언니꺼 다시 올려봐
		
	}
	
	
	public static void main(String[] args){

		new PosFrame();


	}//main

}//PosFrame



