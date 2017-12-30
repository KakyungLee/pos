package pos_coffee;

import java.awt.*;
import javax.swing.*;

public class PosFrame extends JFrame {
	JPanel top;
	JPanel contents;
	CardLayout cardLayout;

	protected TabPanel tabP;
	protected MainPanel mainP;
	protected SalesPanel salesP;
	protected ProductPanel productP;
	protected MemberPanel memberP;

	public PosFrame() {
		AppManager.createInstance().setPosFrame(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1024, 800);
		setTitle("Å×½ºÆ®");
		setLayout(null);

		top = new JPanel();
		top.setBounds(0, 0, 1024, 80);
		top.setLayout(null);

		tabP = new TabPanel();
		tabP.setBounds(0, 0, 1024, 80);
		top.add(tabP);

		mainP = new MainPanel();
		salesP = new SalesPanel();
		productP = new ProductPanel();
		memberP = new MemberPanel();


		contents = new JPanel();
		contents.setSize(1024, 720);
		contents.setLocation(0, 80);
		cardLayout = new CardLayout();
		
		contents.setLayout(cardLayout);
		contents.add("main",mainP);
		contents.add("product",productP);
		contents.add("member",memberP);
		contents.add("sales",salesP);
		cardLayout.show(contents,"main");
		this.add(contents);

		////////////////////////
		top.setBackground(Color.BLUE);
		contents.setBackground(Color.white);
		///////////////////////////
		this.add(top,"top");
		this.add(contents,"contents");

		setVisible(true);
	}// PosFrame

	public static void main(String[] args) {
		new PosFrame();
		new ButtonController();
	}// main

}// PosFrame
