package pos_coffee;
import java.awt.*;
import javax.swing.*;

public class SalesPanel extends JPanel{
	//��� �� �г�
	
	protected JButton dateSelectBtn = new JButton("��¥����");		//������ ��ǰ�� ����
	protected JButton salesSelectBtn = new JButton("");	//������ ��ǰ�� ���߰� ���ý� ��ǰ�̸��� (���߰�), ���� 500�� �߰�
	protected JButton refundBtn = new JButton("�ŷ����");		//���õ� ��ǰ ������Ͽ��� ����
	protected JList salesList = new JList();	//��ǰ���
	protected JLabel salesAccountLbl = new JLabel("0");	
	private JLabel lbl2 = new JLabel("��");
	protected JLabel selectedSales = new JLabel("");	
	protected JComboBox dateCombo = new JComboBox();
	
	SalesPanel(){
		setBackground(Color.white);
		add(dateSelectBtn);
		add(salesSelectBtn);
		add(refundBtn);
		add(salesList);
		add(salesAccountLbl);
		add(lbl2);
		add(dateCombo);
	}
	
}
