package pos_coffee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SellController {
	private DBDAO db;
	private SalesPanel sp;

	int row;
	int col;

	int sum =0;
	
	public SellController() {
		AppManager.createInstance().setSellController(this);
		db = AppManager.createInstance().getDao();
		sp = AppManager.createInstance().getSalesPanel();

		sp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == sp.dateSelectBtn) {

				}
				if (obj == sp.salesSelectBtn) {
					
				}
				if (obj == sp.refundBtn) {
					refund();
				}
			}
		});

		sp.addMouseListener(new MouseListener() {

			@Override
			// 매출을 누르면 바로 보여주기
			public void mouseClicked(MouseEvent e) {
				JTable obj = (JTable) e.getSource();

				row = obj.getSelectedRow();
				col = obj.getSelectedColumn();

				sp.selDate.setText(obj.getValueAt(row, 0) + "");
				sp.selMember.setText(obj.getValueAt(row, 1)+ "");
				sp.selPrice.setText(obj.getValueAt(row, 2) + "");
				sp.selStamp.setText(obj.getValueAt(row, 3) + "");
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
	}// SellController()

	void refund() {
		Member m = db.getMemberPhone(sp.selMember.getText());
		
		m.setMemstamp(m.getMemstamp() - Integer.parseInt(sp.selStamp.getText()));
		db.updateMember(m);
		
		sum -= Integer.parseInt(sp.selPrice.getText());
	}
	void Allrefresh() {
		//sp.dateCombo.setModel(new DefaultComboBoxModel(db.getSalItems()));
		sp.datas.clear();
		sp.datas = db.getAllSale();
		
		Object[][] row = new Object[sp.datas.size()][4];
		sum=0;
		
		int i = 0;
		for (Sale s : sp.datas) {
			row[i][0] = s.getSalno();
			row[i][1] = s.getMemphone();
			row[i][2] = s.getTotalprice();
			sum += s.getTotalprice();
			row[i][3] = s.getStamp();
			i++;
		}

		sp.model.setDataVector(row, sp.colNames);
		sp.salesList.setModel(sp.model);
	
		sp.salesAccountLbl.setText(sum + "");
	}
}
