package pos_coffee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonController {
	private TabPanel tp;
	private PosFrame pf;

	public ButtonController() {
		AppManager.createInstance().setButtonController(this);
		tp = AppManager.createInstance().getTabPanel();
		pf = AppManager.createInstance().getPosFrame();

		tp.addButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = arg0.getSource();

				if (obj.equals(tp.mainBtn)) {
					pf.cardLayout.show(pf.mainP, "main");
				}
				else if (obj.equals(tp.productBtn)) {
					pf.cardLayout.show(pf.productP, "product");
				}
				else if (obj == tp.memberBtn) {
					pf.cardLayout.show(pf.memberP, "member");
				}
				else if (obj == tp.salesBtn) {
					pf.cardLayout.show(pf.salesP, "sales");
				}
				System.out.println("OK");
			}// actionPerformed
		});// addButtonActionListener
	}// ButtonController

	void appMain() {

	}
}
