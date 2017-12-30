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
				if (obj == pp.selectBtn) {
					flag = false;
					Product p = null;
				//	if (col == 0) {
						p = db.getProduct(row + 100);
				/*	} else if (col == 1) {
						p = db.getNameProduct(value + "");
					} else if (col == 2) {
						p = db.getPriceProduct(value + "");
					}*/
					
					pp.proCodeTxt.setText(p.getProcode()+"");
					pp.proNameTxt.setText(p.getProname());
					pp.proPriceTxt.setText(p.getProprice()+"");
				}
				if (obj == pp.insertUpdateBtn) {
					if(flag == true){//삽입
						Product p = null;
						p.setProname(pp.proNameTxt.getText());
						p.setProprice(Integer.parseInt(pp.proPriceTxt.getText()));
						
						db.newProduct(p);
					}else{//수정
						Product p = null;
						p.setProcode(Integer.parseInt((String)pp.proCodeTxt.getText()));
						p.setProname(pp.proNameTxt.getText());
						p.setProprice(Integer.parseInt(pp.proPriceTxt.getText()));
						
						db.updateProduct(p);
					}
				}
				if (obj == pp.deleteBtn) {
				}
				System.out.println("TEST");
				refresh();
			}
		});

		pp.addMouseListener(new MouseListener() {

			
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable obj = (JTable) e.getSource();
				row = obj.getSelectedRow();
				//col = obj.getSelectedColumn();
				
				
				pp.proCodeTxt.setText(row + 100+"");
				value = obj.getValueAt(row, 1);
				pp.proNameTxt.setText(value + "");
				value = obj.getValueAt(row, 2);
				pp.proPriceTxt.setText(value + "");
			
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO 자동 생성된 메소드 스텁

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO 자동 생성된 메소드 스텁

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO 자동 생성된 메소드 스텁

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO 자동 생성된 메소드 스텁

			}

		});

	}// ProductController

	void refresh() {
	/*	pp.idxCombo.setModel(new DefaultComboBoxModel(db.getProItems()));

		pp.datas = db.getAllProduct();

		int i = 0;
		for (Product p : pp.datas) {
			pp.rows[i][0] = p.getProcode();
			pp.rows[i][1] = p.getProname();
			pp.rows[i][2] = p.getProprice();
			i++;
		}
		pp.model.fireTableDataChanged();*/
	}

}// ProductController
