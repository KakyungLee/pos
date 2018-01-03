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
		/*
		 * Shim Soo
		 * AppManager에서 ButtonController을 set하고 
		 * TabPanel(tp) PosFrame(pf)를 get을 하여 사용 
		 */
		AppManager.createInstance().setButtonController(this);
		tp = AppManager.createInstance().getTabPanel();
		pf = AppManager.createInstance().getPosFrame();

		mc = new MainController();
		pc = new ProductController();
		Mmc = new MemberController();
		sc = new SellController();

		tp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				/*
				 * Shim Soo
				 * Panel 변경은 CardLayout을 사용하였고
				 * 해당 버튼을 누르면 맞는 Panel을 나오게 설정
				 * 또, 바로바로 refresh를 해줘서 사용자가 불편을 격지 않게 해줌 
				 */
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
