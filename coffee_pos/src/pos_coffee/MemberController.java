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
	int stamp =0;

	boolean flag = true;

	public MemberController() {
		AppManager.createInstance().setMemberController(this);
		db = AppManager.createInstance().getDao();
		mp = AppManager.createInstance().getMemberPanel();

		mp.addButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				Member m = null;
				m = new Member();
				if (obj == mp.insertBtn) {
					m.setMemname(mp.memNameTxt.getText());
					m.setMemphone(mp.memPhoneTxt.getText());
					db.newMember(m);

				}
				if (obj == mp.updateBtn) {
					m.setMemno(Integer.parseInt(mp.idxTxt.getText()));
					m.setMemname(mp.memNameTxt.getText());
					m.setMemphone(mp.memPhoneTxt.getText());
					m.setMemstamp(stamp);
					db.updateMember(m);
				}
				if (obj == mp.deleteBtn) {
					db.delMember(Integer.parseInt(mp.idxTxt.getText()));
				}
				System.out.println("TEST");
				refresh();
				mp.idxTxt.setText("");
				mp.memNameTxt.setText("");
				mp.memPhoneTxt.setText("");
			}
		});

		mp.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable obj = (JTable) e.getSource();
				row = obj.getSelectedRow();
				col = obj.getSelectedColumn();

				mp.idxTxt.setText(obj.getValueAt(row, 0) + "");
				mp.memNameTxt.setText(obj.getValueAt(row, 1) + "");
				mp.memPhoneTxt.setText(obj.getValueAt(row, 2) + "");
				stamp = Integer.parseInt(obj.getValueAt(row, 3)+"");

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

	void refresh() {
		mp.datas = new ArrayList<Member>();
		mp.datas = db.getAllMember();
		mp.rows = new Object[mp.datas.size()][4];

		int i = 0;
		for (Member p : mp.datas) {
			mp.rows[i][0] = p.getMemno();
			mp.rows[i][1] = p.getMemname();
			mp.rows[i][2] = p.getMemphone(); /// ���Ⱑ �����ϴ�
			mp.rows[i][3] = p.getMemstamp();
			i++;
		}
		mp.model.fireTableDataChanged();
	}
}
