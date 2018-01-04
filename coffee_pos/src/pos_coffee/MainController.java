package pos_coffee;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainController {
	private MainPanel mp;
	private DBDAO db;
	
	int row;
	int stamp = 0;
	int sum = 0;
	int shot =0;

	Product p = new Product();
	Member m = new Member();
	Sale s = new Sale();
	
	/*
	 * Shim Soo 
	 * stampUse: 스탬프 사용 유무	 
	 */
	boolean stampUse = false;

	public MainController() {
		/*
		 * Shim Soo
		 * AppManager에서 MainController를 set을 해주고
		 * 필요한 MainPanel(mp), DBDAO(db)를 get를 하게 하여 사용  
		 */
		AppManager.createInstance().setMainController(this);
		mp = AppManager.createInstance().getMainPanel();
		db = AppManager.createInstance().getDao();

		mp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				/*
				 * Shim Soo 
				 * 선택 버튼을 누르면 선택 된 상품Jtable에 있는 상품 정보를 주문 Jtable에 있는 곳으로 add해주게 된다.
				 * 그러면서 금액을 맞게 총액(sum)을 더한다. 
				 */
				//MOA,가경 ② 샷추가에서 변경한 코드 스타일에 맞게 상품선택 코드도 수정
				if (obj == mp.selectBtn) {// 선택
					
					DefaultTableModel m = (DefaultTableModel)mp.selectedProductList.getModel();
					int row = mp.productList.getSelectedRow();
					System.out.println(row);
					sum += Integer.parseInt(mp.productList.getValueAt(row, 2)+"");
					
					m.addRow(new Object[] {mp.productList.getValueAt(row, 0),mp.productList.getValueAt(row, 1),mp.productList.getValueAt(row, 2)});
					mp.selectedProductList.updateUI();
					mp.pricelLbl.setText(sum + "");	
				}
				/*
				 * Shim Soo
				 * 주문 JTable에서 해당 상품을 클릭을 하고 버튼을 누르면 상품 이름 옆에 (샷추가)가 생기고 
				 * 샷 추가(500)이 해당 금액이 올라간다. 또 총액에 맞게 돈이 추가 된다.
				 */
				//MOA ① 샷추가시 발생하는 버그 막기 위해 코드 변경.
				// -> 기존 배열과 반복문을 통한 데이터 수정 코드에서, JTable에서 제공하는 메소드를 사용하여
				// 실제 데이터가 있는 JTable 모델의 데이터를 수정하는 방식으로 코드 변경함.
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
				/*
				 * Shim Soo 
				 * 주문 JTable에 해당 상품을 클릭하고 버튼을 누르면 주문 Jtable에 해당 상품이 삭제되고 맞게 총액도 변경된다.
				 */
				//MOA,가경 ③ 수정한 코드 스타일에 맞게 주문삭제 코드도 수정
				if (obj == mp.removeBtn) {// 주문 삭제
					DefaultTableModel m = (DefaultTableModel)mp.selectedProductList.getModel();
					int row = mp.selectedProductList.getSelectedRow();
					System.out.println(row);
					sum -= Integer.parseInt(mp.selectedProductList.getValueAt(row, 2)+"");
					
					m.removeRow(row);
					mp.selectedProductList.updateUI();
					mp.pricelLbl.setText(sum + "");					
				}
				/*
				 * Shim Soo
				 * 버튼을 누르면 주문 JTable에 있는 모든 상품이 삭제된다. 총액도 물론 0으로 변한다. 
				 */
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
				/*
				 * Shim Soo 
				 * 회원의 휴대폰 번호를 입력 후 버튼을 누르면 db에서 회원 정보를 가져와 회원 이름, 번호, 스탬프 개수를 보여준다.
				 * 이때 스탬프 개수가 10개(10개 이상이 되야 할인(아메리카노 가격: 2800) 받을 수 있다)이상이 되면 스탬프사용할 수 있는 버튼을 활성화 해주었다.	 
				 */
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
				/*
				 * Shim Soo 
				 * 아메리카노 가격만큼 총액 금야겡서 사라지고 스탬프 사용은 아무리 많아도 한 번만 사용할 수 있도록 스탬프 사용버튼을 비활성화 한다.
				 * 그리고 스탬프를 사용한 것을 알기 위해 stampUse를 true로 변경한다.	 
				 */
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
				/*
				 * Shim Soo 
				 * 편리성을 위해 txt를 누르면 아무 것도 나오지 않게 설정하였다.	 
				 */
				if (d == mp.memberTxt) {
					mp.memberTxt.setText("");
				}
				/*
				 * Shim Soo 
				 * JTable을 누르면 해당 누른 정보에 맞게 정보를 Product(p)에 저장한다..	 
				 */
				else {
					JTable obj = (JTable) e.getSource();
					row = obj.getSelectedRow();

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

	/*
	 * Shim Soo 
	 * 주문 JTable에서 전체 삭제할 떄 사용하는 메소드 
	 */
	void refreshSelectJList() {
		mp.model2.setRowCount(0);

		mp.model2.setDataVector(mp.rows2, mp.colNames);
		mp.selectedProductList.setModel(mp.model2);	
		mp.selectedProductList.getColumnModel().getColumn(0).setMaxWidth(70); 
		mp.selectedProductList.getColumnModel().getColumn(1).setMinWidth(250); 
	}
	/*
	 * Shim Soo 
	 * 결제 버튼을 누르면 실행되는 버튼으로, 스탬프개수는 주문한 음료 수대로 적립이 되고 스탬프를 사용한 것이 있으면 10개를 깍고 적립하지 않는다.
	 * 매출 Table에 삽입이 되고 회원 Table에도 수정이 된다.
	 * 주문 JTable에 초기화를 하고 회원 정보 또한 초기화를 한다. 
	 */
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
	/*
	 * Shim Soo 
	 * 상품 JTable을 바로바로 db에서 가져와서 setting을 다시한다.	 
	 */
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
