package pos_coffee;

import java.awt.*;
import javax.swing.*;

public class PosFrame extends JFrame {
	private JPanel Center;
	TabPanel tabP;
	MainPanel mainP;

	public PosFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1024, 450);
		setTitle("Å×½ºÆ®");
		setLayout(new BorderLayout());

		tabP = new TabPanel();
		mainP = new MainPanel();
		add(tabP);
		add(mainP);

		setVisible(true);
	}

	public static void main(String[] args) {
		new PosFrame();

	}//main

}//PosFrame
