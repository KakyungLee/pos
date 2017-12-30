package pos_coffee;

import java.awt.event.*;

public class ButtonController {
	private TabPanel tp;
	private PosFrame pf;

	public ButtonController() {
		AppManager.createInstance().setButtonController(this);
		tp = AppManager.createInstance().getTabPanel();
		pf = AppManager.createInstance().getPosFrame();

		tp.addButtonActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				
				if (obj == tp.mainBtn) {
					pf.cardLayout.show(pf.contents, pf.contentsName[0]);
				}
				if (obj == tp.productBtn) {
					pf.cardLayout.show(pf.contents, pf.contentsName[1]);
				}
				if (obj == tp.memberBtn) {
					pf.cardLayout.show(pf.contents, pf.contentsName[2]);
				}
				if (obj == tp.salesBtn) {
					pf.cardLayout.show(pf.contents, pf.contentsName[3]);

				}
			}
		});
	}// ButtonController
}
