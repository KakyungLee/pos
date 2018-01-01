package pos_coffee;

import java.awt.event.*;

public class ButtonController {
	private TabPanel tp;
	private PosFrame pf;

	protected MainController mc = null;
	protected ProductController pc= null;
	protected MemberController Mmc= null;;
	protected SellController sc= null;;

	public ButtonController() {
		System.out.println("test");
		AppManager.createInstance().setButtonController(this);
		tp = AppManager.createInstance().getTabPanel();
		pf = AppManager.createInstance().getPosFrame();

		//mc = AppManager.createInstance().getMainController();
		mc = new MainController();
		pc = new ProductController();
		Mmc = new MemberController();
		sc = new SellController();

		tp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();

				if (obj == tp.mainBtn) {
					System.out.println("Main");
					pf.cardLayout.show(pf.contents, pf.contentsName[0]);
					mc.refresh();
				}
				if (obj == tp.productBtn) {
					System.out.println("product");
					pf.cardLayout.show(pf.contents, pf.contentsName[1]);
					pc.refresh();
				}
				if (obj == tp.memberBtn) {
					System.out.println("member");
					pf.cardLayout.show(pf.contents, pf.contentsName[2]);
					Mmc.refresh();
				}
				if (obj == tp.salesBtn) {
					System.out.println("sales");
					pf.cardLayout.show(pf.contents, pf.contentsName[3]);
					sc.Allrefresh();
				}
			}
		});
	}// ButtonController
}
