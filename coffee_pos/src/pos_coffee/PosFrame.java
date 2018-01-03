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
	
	String[] contentsName = { "main", "product", "member", "sales" };

	public PosFrame() {
		AppManager.createInstance().setPosFrame(this);
		new DBDAO();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1024, 800);

		setTitle("cafe_pos_system");
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

		contents.add(contentsName[0], mainP);
		contents.add(contentsName[1], productP);
		contents.add(contentsName[2], memberP);
		contents.add(contentsName[3], salesP);
		cardLayout.show(contents, contentsName[0]);

		this.add(contents);

		ButtonController bc = new ButtonController();
		////////////////////////
		top.setBackground(Color.BLUE);
		contents.setBackground(Color.white);
		///////////////////////////
		this.add(top, "top");
		this.add(contents, "contents");

		setVisible(true);

		
	}// PosFrame

	public static void main(String[] args) {
		new PosFrame();

	}// main

}// PosFrame
