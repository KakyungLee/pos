package pos_coffee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

public class SellController {
	private DBDAO db;
	private SalesPanel sp;
	
	public SellController(){
		AppManager.createInstance().setSellController(this);
		db = AppManager.createInstance().getDao();
		sp = AppManager.createInstance().getSalesPanel();
		
		sp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				
				if(obj == sp.dateCombo) {
					
				}
			}
		});
	}// SellController()
	
	void refresh() {
		sp.dateCombo.setModel(new DefaultComboBoxModel(db.getSalItems()));
	}
}
