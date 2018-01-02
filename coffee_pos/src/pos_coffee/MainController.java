package pos_coffee;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainController {
	private MainPanel mp;
	private DBDAO db;

	int row;
	int col;
	Object value;
	int stamp = 0;
	int sum = 0;
	int shot =0;

	Product p = new Product();
	Member m = new Member();
	Sale s = new Sale();

	boolean stampUse = false;

	public MainController() {
		AppManager.createInstance().setMainController(this);
		mp = AppManager.createInstance().getMainPanel();
		db = AppManager.createInstance().getDao();

		mp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();

				if (obj == mp.selectBtn) {// 선택
					
					DefaultTableModel m = (DefaultTableModel)mp.selectedProductList.getModel();
					int row = mp.productList.getSelectedRow();
					System.out.println(row);
					sum += Integer.parseInt(mp.productList.getValueAt(row, 2)+"");
					
					m.addRow(new Object[] {mp.productList.getValueAt(row, 0),mp.productList.getValueAt(row, 1),mp.productList.getValueAt(row, 2)});
					mp.selectedProductList.updateUI();
					mp.pricelLbl.setText(sum + "");	
					
				}
				if (obj == mp.shotSelectBtn) {// 샷 추기
					DefaultTableModel m = (DefaultTableModel)mp.selectedProductList.getModel();
					int row = mp.selectedProductList.getSelectedRow();
					System.out.println(row);
					sum += 500;
					
					mp.selectedProductList.setValueAt(mp.selectedProductList.getValueAt(row, 1)+"(샷추가)",row,1);
					mp.selectedProductList.setValueAt(Integer.parseInt(mp.selectedProductList.getValueAt(row, 2).toString())+500,row,2);
					mp.selectedProductList.updateUI();
					mp.pricelLbl.setText(sum + "");	
								
				}
				if (obj == mp.removeBtn) {// 주문 삭제
					DefaultTableModel m = (DefaultTableModel)mp.selectedProductList.getModel();
					int row = mp.selectedProductList.getSelectedRow();
					System.out.println(row);
					sum -= Integer.parseInt(mp.selectedProductList.getValueAt(row, 2)+"");
					
					m.removeRow(row);
					mp.selectedProductList.updateUI();
					mp.pricelLbl.setText(sum + "");					
					
				}
				if (obj == mp.clearBtn) {// 주문 전체 삭제
					sum = 0;
					mp.rows2 = new Object[0][3];
					mp.selectCount = 0;

					stampUse = false;

					mp.memberTxt.setText("회원폰번호입력");
					mp.model2.setRowCount(0);
					mp.memberinfoLbl.setText("");

					refreshSelectJList();
					mp.pricelLbl.setText(sum + "");
				}
				if (obj == mp.paymentBtn) {// 결제
					payment();
				}
				if (obj == mp.searchBtn) {// 회원 조회
					mp.memberinfoLbl.setText("");
					String phone = mp.memberTxt.getText();

					m = db.getMemberPhone(phone);
					if (m != null) {
						mp.memberinfoLbl.append("이름 >> " + m.getMemname() + "\n");
						mp.memberinfoLbl.append("번호 >> " + m.getMemphone() + "\n");
						mp.memberinfoLbl.append("개수 >> " + m.getMemstamp() + "\n");
						if (m.getMemstamp() > 9) {
							mp.useStampBtn.setEnabled(true);
						}
					} else {
						mp.memberinfoLbl.append("회원이 없습니다.");
					}
				}
				if (obj == mp.useStampBtn) {// 스탬프 사용여부
					int stamp = m.getMemstamp();
					if (stamp > 9) {
						// 시용하면 아메리카노 가격만큼 빼기
						sum -= 2800;
						mp.pricelLbl.setText(sum + "");
						mp.useStampBtn.setEnabled(false);
						stampUse = true;
					}
				}

			}
		});
		mp.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object d = e.getSource();
				if (d == mp.memberTxt) {
					mp.memberTxt.setText("");
				} else {
					JTable obj = (JTable) e.getSource();
					row = obj.getSelectedRow();
					col = obj.getSelectedColumn();

					int code = Integer.parseInt(obj.getValueAt(row, 0)+"");
					String Name = (String) obj.getValueAt(row, 1);
					int Price = Integer.parseInt(obj.getValueAt(row, 2)+"");

					p.setProcode(code);
					p.setProname(Name);
					p.setProprice(Price);
				}
			}// mouseClicked

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
	}// MainController

	void refreshSelectJList() {
		mp.model2.setRowCount(0);

		mp.model2.setDataVector(mp.rows2, mp.colNames);
		mp.selectedProductList.setModel(mp.model2);	
		mp.selectedProductList.getColumnModel().getColumn(0).setMaxWidth(70); 
		mp.selectedProductList.getColumnModel().getColumn(1).setMinWidth(250); 
	}

	void payment() {
		s.setMemphone(m.getMemphone());
		s.setTotalprice(sum);
		if (stampUse == true) {
			s.setStamp(-10);
			m.setMemstamp(m.getMemstamp() - 10);
		} else {
			s.setStamp(mp.selectedProductList.getRowCount());
			m.setMemstamp(m.getMemstamp() + s.getStamp());
		}
		db.updateMember(m);

		stampUse = false;
		db.newSale(s);

		sum = 0;
		mp.rows2 = new Object[0][3];
		mp.selectCount = 0;

		stampUse = false;

		mp.memberTxt.setText("회원폰번호입력");
		mp.model2.setRowCount(0);
		mp.memberinfoLbl.setText("");
		m.setMemname("");
		m.setMemno(1);
		m.setMemphone("0000000000");
		m.setMemstamp(0);

		refreshSelectJList();
		mp.pricelLbl.setText(sum + "");
	}

	void refresh() {
		mp.model.setRowCount(0);

		mp.datas.clear();
		mp.datas = db.getAllProduct();

		Object[][] rows = new Object[mp.datas.size()][3];

		int i = 0;
		for (Product p : mp.datas) {
			rows[i][0] = p.getProcode();
			rows[i][1] = p.getProname();
			rows[i][2] = p.getProprice();
			i++;
		}
		mp.model.setDataVector(rows, mp.colNames);

		mp.productList.setModel(mp.model);
	}

}// MainController
