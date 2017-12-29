package pos_coffee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonController {
	private final TabPanel t;

	public ButtonController(TabPanel tab) {
	
		this.t = tab;
	}

	void appMain() {
	/*	t.addButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == t.mainBtn) {
					PanelChange.viewPanel(0);

				} else if (obj == t.productBtn) {
					PanelChange.viewPanel(1);

				} else if (obj == t.memberBtn) {
					PanelChange.viewPanel(2);

				} else if (obj == t.salesBtn) {
					PanelChange.viewPanel(3);
				}
			}// actionPerformed
		});*/
	}

	public static void main(String[] args) {
		ButtonController app = new ButtonController(new TabPanel());
		app.appMain();

	}// main
}
