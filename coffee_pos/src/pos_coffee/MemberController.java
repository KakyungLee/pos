package pos_coffee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTable;

public class MemberController {
	private DBDAO db;
	private MemberPanel mp;

	int row;
	int col;
	Object value;
	int stamp = 0;

	boolean flag = true;

	public MemberController() {
		/*
		 * Shim Soo 
		 * AppManager에서 MemberController set
		 * db와 MemberPanel(mp)필요하므로 get
		 */
		AppManager.createInstance().setMemberController(this);
		db = AppManager.createInstance().getDao();
		mp = AppManager.createInstance().getMemberPanel();

		mp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				Member m = null;
				m = new Member();
				
				/*
				 * Shim Soo 
				 * TextField의 값으로 Member가 추가
				 */
				if (obj == mp.insertBtn) {
					m.setMemname(mp.memNameTxt.getText());
					m.setMemphone(mp.memPhoneTxt.getText());
					db.newMember(m);

				}
				/*
				 * Shim Soo 
				 * textField의 값으로 Member 수정 
				 */
				if (obj == mp.updateBtn) {
					m.setMemno(Integer.parseInt(mp.idxTxt.getText()));
					m.setMemname(mp.memNameTxt.getText());
					m.setMemphone(mp.memPhoneTxt.getText());
					m.setMemstamp(stamp);
					db.updateMember(m);
				}
				/*
				 * Shim Soo 
				 * 회원 JTable을 해당 회원을 클릭 후 해당 회원 삭제
				 */
				if (obj == mp.deleteBtn) {
					db.delMember(Integer.parseInt(mp.idxTxt.getText()));
				}
				
				/*
				 * Shim Soo 
				 * 다른 작업을 위해 초기화와 새로고침
				 */
				refresh();
				mp.idxTxt.setText("");
				mp.memNameTxt.setText("");
				mp.memPhoneTxt.setText("");
			}
		});

		mp.addMouseListener(new MouseListener() {
			@Override
			/*
			 * Shim Soo 
			 * 회원 JTable을 누르면 해당 값이 맞게 setText
			 */
			public void mouseClicked(MouseEvent e) {
				JTable obj = (JTable) e.getSource();
				row = obj.getSelectedRow();
				col = obj.getSelectedColumn();

				mp.idxTxt.setText(obj.getValueAt(row, 0) + "");
				mp.memNameTxt.setText(obj.getValueAt(row, 1) + "");
				mp.memPhoneTxt.setText(obj.getValueAt(row, 2) + "");
				stamp = Integer.parseInt(obj.getValueAt(row, 3) + "");

			}// mouseClicked

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO �ڵ� ������ �޼ҵ� ����

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO �ڵ� ������ �޼ҵ� ����

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO �ڵ� ������ �޼ҵ� ����

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO �ڵ� ������ �޼ҵ� ����

			}

		});

	}// ProductController

	/*
	 * Shim Soo 
	 * db에서 매 순간 정보를 가져와 새로고침
	 */
	void refresh() {
		mp.model.setRowCount(0);

		mp.datas.clear();
		mp.datas = db.getAllMember();
		
		Object[][] row = new Object[mp.datas.size()][4];
		
		int i = 0;
		for (Member p : mp.datas) {
			row[i][0] = p.getMemno();
			row[i][1] = p.getMemname();
			row[i][2] = p.getMemphone(); /// ���Ⱑ �����ϴ�
			row[i][3] = p.getMemstamp();
			i++;
		}
		
		mp.model.setDataVector(row, mp.colNames);
		mp.memberList.setModel(mp.model);
	}
}
