package pos_coffee;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PosFrame extends JFrame{
	TabPanel tabP;
	MainPanel mainP;
	SalesPanel salesP;

	
	public PosFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1024,450);
		setTitle("Å×½ºÆ®");
		setLayout(new FlowLayout());
		
		tabP = new TabPanel();
		mainP = new MainPanel();
		salesP = new SalesPanel();
		add(tabP);
		add(mainP);
		add(salesP);
		
		
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
		
	}
	
	/*
	public static void main(String[] args){
		new PosFrame();
	
	}
	*/
}
