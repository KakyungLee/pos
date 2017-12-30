package pos_coffee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

public class MemberController {
	private DBDAO db;
	private MemberPanel mp;

	int row;
	int col;
	Object value;

	boolean flag = true;

	public MemberController() {
		AppManager.createInstance().setMemberController(this);
		db = AppManager.createInstance().getDao();
		mp = AppManager.createInstance().getMemberPanel();

		mp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				Member p = null;
				if (obj == mp.insertUpdateBtn) {

					if (flag == true) {// 삽입
						p.setMemname(mp.memNameTxt.getText());
						p.setMemphone(mp.memPhoneTxt.getText());
						db.newMember(p);
					} else {// 수정
						p.setMemno(Integer.parseInt(mp.idxTxt.getText()));
						p.setMemname(mp.memNameTxt.getText());
						p.setMemphone(mp.memPhoneTxt.getText());
						db.updateMember(p);
					}
				}
				if (obj == mp.deleteBtn) {
					db.delMember(Integer.parseInt(mp.idxTxt.getText()));
				}
				System.out.println("TEST");
				refresh();
			}
		});

		mp.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag = false;
				Member p = null;
				JTable obj = (JTable) e.getSource();
				row = obj.getSelectedRow();
				col = obj.getSelectedColumn();
				value = obj.getValueAt(row, col);

				if (col == 0) {
					p = db.getMember(Integer.parseInt(mp.idxTxt.getText()));
				} else if (col == 1) {
					p = db.getNameMember(value + "");
				} else if (col == 2) {
					p = db.getPhoneMember(value + "");
				}
				mp.idxTxt.setText(p.getMemno() + "");
				mp.memNameTxt.setText(p.getMemname());
				mp.memPhoneTxt.setText(p.getMemphone());
			}// mouseClicked

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
		mp.datas = db.getAllMember();

		int i = 0;
		for (Member p : mp.datas) {
			mp.rows[i][0] = p.getMemno();
			mp.rows[i][1] = p.getMemname();
			mp.rows[i][2] = p.getMemphone();
			i++;
		}
		mp.model.fireTableDataChanged();
	}
}
