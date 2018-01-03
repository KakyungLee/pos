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
		/*
		 * Shim Soo 
		 * AppManager에 ProductController set
		 * 필요한 db와 ProductPanel(pp)를  get
		 */
		AppManager.createInstance().setProductController(this);
		db = AppManager.createInstance().getDao();
		pp = AppManager.createInstance().getProductPanel();

		pp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				
				/*
				 * Shim Soo 
				 * textField의 값을 가져와 상품을 삽입
				 */
				if (obj == pp.insertBtn) {
					Product p = null;
					p = new Product();
					p.setProname(pp.proNameTxt.getText());
					p.setProprice(Integer.parseInt(pp.proPriceTxt.getText()));
					db.newProduct(p);
				}
				/*
				 * Shim Soo 
				 *  textField의 값을 가져와 상품 수정
				 */
				if (obj == pp.updateBtn) {
					Product p = null;
					p = new Product();
					p.setProcode(Integer.parseInt(pp.proCodeTxt.getText()));
					p.setProname(pp.proNameTxt.getText());
					p.setProprice(Integer.parseInt(pp.proPriceTxt.getText()));
					db.updateProduct(p);
				}
				/*
				 * Shim Soo 
				 * 해당 상품 삭제
				 */
				if (obj == pp.deleteBtn) {

					db.delProduct(Integer.parseInt(pp.proCodeTxt.getText()));
				}

				/*
				 * Shim Soo 
				 * 바로바로 새로고침
				 * 다음 실행을 위해 초기화
				 */
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
				/*
				 * Shim Soo 
				 * JTable을 누르면 바로 textField로 값 setText
				 */
				row = obj.getSelectedRow();
				col = obj.getSelectedColumn();
				pp.proCodeTxt.setText(obj.getValueAt(row, 0) + "");
				pp.proNameTxt.setText(obj.getValueAt(row, 1) + "");
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
	/*
	 * Shim Soo db에서 매 순간 정보를 가져와 새로고침
	 */

	void refresh() {
		pp.model.setRowCount(0);

		pp.datas.clear();
		pp.datas = db.getAllProduct();

		Object[][] row = new Object[pp.datas.size()][3];

		int i = 0;
		for (Product p : pp.datas) {
			row[i][0] = p.getProcode();
			row[i][1] = p.getProname();
			row[i][2] = p.getProprice();
			i++;
		}
		pp.model.setDataVector(row, pp.colNames);

		pp.productList.setModel(pp.model);
	}

}// ProductController
