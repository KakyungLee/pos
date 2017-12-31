package pos_coffee;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

public class ProductController {
	private DBDAO db;
	private ProductPanel pp;

	int row;
	int col;
	Object value;

	public ProductController() {
		AppManager.createInstance().setProductController(this);
		db = AppManager.createInstance().getDao();
		pp = AppManager.createInstance().getProductPanel();

		pp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();				
				
				if (obj == pp.insertBtn) {
					Product p = null;
					p = new Product();
					p.setProname(pp.proNameTxt.getText());
					p.setProprice(Integer.parseInt(pp.proPriceTxt.getText()));
					db.newProduct(p);			
				}
				if(obj == pp.updateBtn) {
					Product p = null;
					p = new Product();
					p.setProcode(Integer.parseInt(pp.proCodeTxt.getText()));
					p.setProname(pp.proNameTxt.getText());
					p.setProprice(Integer.parseInt(pp.proPriceTxt.getText()));
					db.updateProduct(p);
				}
				if (obj == pp.deleteBtn) {
					db.delProduct(Integer.parseInt(pp.proCodeTxt.getText()));
				}
				System.out.println("TEST");
				refresh();
				pp.proCodeTxt.setText("");
				pp.proNameTxt.setText("");
				pp.proPriceTxt.setText("");
			}
		});

		pp.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JTable obj = (JTable) e.getSource();
				
				row = obj.getSelectedRow();
				col = obj.getSelectedColumn();
				pp.proCodeTxt.setText(obj.getValueAt(row, 0)+"");
				pp.proNameTxt.setText(obj.getValueAt(row, 1)+"");
				pp.proPriceTxt.setText(obj.getValueAt(row, 2) + "");
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});

	}// ProductController

	void refresh() { // do not working refresh;
		Vector result = db.getAllProductS();
		
		pp.model.setDataVector(result, pp.colNames);;
	}

}// ProductController
