package pos_coffee;

import java.awt.event.*;
import javax.swing.*;

public class ProductController {
	private DBDAO db;
	private ProductPanel pp;

	int row;
	int col;
	Object value;

	boolean flag = true;

	public ProductController() {
		AppManager.createInstance().setProductController(this);
		db = AppManager.createInstance().getDao();
		pp = AppManager.createInstance().getProductPanel();

		pp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				refresh();
				if (obj == pp.insertUpdateBtn) {
					if (flag == true) {// »ðÀÔ
						Product p = null;
						p.setProname(pp.proNameTxt.getText());
						p.setProprice(Integer.parseInt(pp.proPriceTxt.getText()));

						db.newProduct(p);
					} else {// ¼öÁ¤
						Product p = null;
						p.setProcode(Integer.parseInt((String) pp.proCodeTxt.getText()));
						p.setProname(pp.proNameTxt.getText());
						p.setProprice(Integer.parseInt(pp.proPriceTxt.getText()));

						db.updateProduct(p);
					}
				}
				if (obj == pp.deleteBtn) {
					db.delMember(Integer.parseInt(pp.proCodeTxt.getText()));
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
				flag = false;
				JTable obj = (JTable) e.getSource();
				row = obj.getSelectedRow();
				col = obj.getSelectedColumn();
				value = obj.getValueAt(row, col);
				Product p = null;
				if (col == 0) {
					p = db.getProduct(Integer.parseInt(pp.proCodeTxt.getText()));
				} else if (col == 1) {
					p = db.getNameProduct(pp.proNameTxt.getText());
				} else if (col == 2) {
					p = db.getPriceProduct(pp.proPriceTxt.getText());
				}

				pp.proCodeTxt.setText(p.getProcode() + "");
				pp.proNameTxt.setText(p.getProname());
				pp.proPriceTxt.setText(p.getProprice() + "");
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

	void refresh() {

		pp.datas = db.getAllProduct();

		int i = 0;
		for (Product p : pp.datas) {
			pp.rows[i][0] = p.getProcode();
			pp.rows[i][1] = p.getProname();
			pp.rows[i][2] = p.getProprice();
			i++;
		}
		pp.model.fireTableDataChanged();

	}

}// ProductController
