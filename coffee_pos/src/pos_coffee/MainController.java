package pos_coffee;

import java.awt.event.*;

public class MainController {
	private MainPanel mp;
	private DBDAO db;

	public MainController() {
		AppManager.createInstance().setMainController(this);
		mp = AppManager.createInstance().getMainPanel();
		db = AppManager.createInstance().getDao();

		appMain();

	}// MainController

	void appMain(){
		mp.addButtonAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				
				if(obj == mp.selectBtn){
					System.out.println("test");
				}
				if(obj == mp.shotSelectBtn){
					
				}
				if(obj == mp.removeBtn){
					
				}				
			}// actionPerformed
		});// addButtonAction
	}
	void refreshData() {

	}

}// MainController
