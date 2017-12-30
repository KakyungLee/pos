package pos_coffee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonController implements ActionListener {
	private TabPanel tp;
	private PosFrame pf;

	public ButtonController() {
		AppManager.createInstance().setButtonController(this);
		tp = AppManager.createInstance().getTabPanel();
		pf = AppManager.createInstance().getPosFrame();

	}// ButtonController
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == tp.mainBtn) {
			pf.cardLayout.show(pf.mainP, "main");
		}
		if (obj == tp.productBtn) {
			pf.cardLayout.show(pf.productP, "product");
		}
		if (obj == tp.memberBtn) {
			pf.cardLayout.show(pf.memberP, "member");
		}
		if (obj == tp.salesBtn) {
			pf.cardLayout.show(pf.salesP, "sales");
		}
		System.out.println("OK");

	}
}
