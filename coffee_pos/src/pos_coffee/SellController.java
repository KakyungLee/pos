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

	int sum = 0;

	public SellController() {
		/*
		 * Shim Soo 
		 *  AppManager에 SellController set
		 *  필요한 db와 SalesPanel(sp)를 get
		 */
		AppManager.createInstance().setSellController(this);
		db = AppManager.createInstance().getDao();
		sp = AppManager.createInstance().getSalesPanel();

		sp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == sp.dateSelectBtn) {
					dateSelect();
				}
				if (obj == sp.salesSelectBtn) {
					Allrefresh();
				}
				if (obj == sp.refundBtn) {
					refund();
					Allrefresh();
				}
			}
		});

		sp.addMouseListener(new MouseListener() {

			@Override
			// 매출을 누르면 바로 보여주기
			public void mouseClicked(MouseEvent e) {
				/*
				 * Shim Soo 
				 * 매출 JTable를 누르면 바로 setText
				 */
				JTable obj = (JTable) e.getSource();

				row = obj.getSelectedRow();
				col = obj.getSelectedColumn();

				sp.selDate.setText(obj.getValueAt(row, 0) + "");
				sp.selMember.setText(obj.getValueAt(row, 1) + "");
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
		/*
		 * Shim Soo 
		 * 환불 버튼을 누르면 실행되는 메소드
		 * 매출에 저장된 회원 휴대폰 번호를 기준으로 회원의 정보를 가져옴
		 * -> 거래를 통해 얻은 스탬프(쿠폰을 사용한 스탬프) 되돌리기
		 * -> 다시 멤버 수정
		 * -> 하루 매출조정
		 * -> 해당 매출 삭제
		 */
		Member m = db.getMemberPhone(sp.selMember.getText());

		m.setMemstamp(m.getMemstamp() - Integer.parseInt(sp.selStamp.getText()));
		db.updateMember(m);

		sum -= Integer.parseInt(sp.selPrice.getText());

		db.delSale(Integer.parseInt(sp.selDate.getText()));
	}

	void dateSelect() {
		/*
		 * Shim Soo 
		 * 콤보 박스로 얻은 날짜를 기즌으로 JTable 다시 setting
		 */
		String date = (String) sp.dateCombo.getSelectedItem();
		System.out.println(date);
		if (date.equals("날짜")) {
			Allrefresh();
		} else {
			sp.datas.clear();
			sp.datas = db.getAllSaleWhereDate(date);

			Object[][] row = new Object[sp.datas.size()][4];
			sum = 0;

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
	/*
	 * Shim Soo 
	 * db에서 매 순간 정보를 가져와 새로고침
	 */
	void Allrefresh() {
		sp.dateCombo.setModel(new DefaultComboBoxModel(db.getSalItems()));
		sp.datas.clear();
		sp.datas = db.getAllSale();

		Object[][] row = new Object[sp.datas.size()][4];
		sum = 0;

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
