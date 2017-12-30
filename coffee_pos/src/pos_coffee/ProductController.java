package pos_coffee;

import java.awt.event.*;
import javax.swing.*;

public class ProductController {
	private DBDAO db;
	private ProductPanel pp;

	public ProductController() {
		AppManager.createInstance().setProductController(this);
		db = AppManager.createInstance().getDao();
		pp = AppManager.createInstance().getProductPanel();

		pp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();

				if (obj == pp.selectBtn) {
					System.out.println("hello");
				}
				if (obj == pp.insertUpdateBtn) {
				}
				if (obj == pp.deleteBtn) {
				}
				System.out.println("TEST");
			}
		});

	}//ProductController
	
	void refresh(){
		//pp.idxCombo(new DefaultComboBoxModel(db.getProItems()));

		pp.datas = db.getAllProduct();
	}
}//ProductController
