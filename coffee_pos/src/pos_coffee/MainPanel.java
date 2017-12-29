package pos_coffee;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class MainPanel extends JPanel{
	//��� �� �г�
	
	protected JButton selectBtn = new JButton("����");		//������ ��ǰ�� ����
	protected JButton shotSelectBtn = new JButton("���߰�/����");	//������ ��ǰ�� ���߰� ���ý� ��ǰ�̸��� (���߰�), ���� 500�� �߰�
	protected JButton removeBtn = new JButton("����");		//���õ� ��ǰ ������Ͽ��� ����
	protected JButton searchBtn = new JButton("��ȸ");		//�ڵ��� ��ȣ�� ȸ�� ��ȸ -> ������ ����, ���� �ʼ�
	protected JButton useStampBtn = new JButton("���������");	//������ ���
	protected JButton clearBtn = new JButton("���");	//��������Ʈ �� ȸ�� ȭ�� Ŭ����
	protected JButton paymentBtn = new JButton("����");	//���� ���� �� ���� �߻�
	protected JList productList;	//��ǰ���
	protected JList selectedProductList = new JList();	//���õ� ��ǰ. ��������Ʈ
	protected JTextField memberTxt = new JTextField("ȸ����ȣ�Է�");	//��ȸ�� ȸ�� �޴�����ȣ �Է�
	private JLabel lbl1 = new JLabel("�Ѿ�");	
	protected JLabel pricelLbl = new JLabel("0");	//������ �Ѿ�
	private JLabel lbl2 = new JLabel("��");
	protected JLabel memberinfoLbl = new JLabel("::ȸ������::");	//ȸ�� ��ȸ�� ȸ������ ��Ÿ��
	
	
	MainPanel(){
		
		/*�׽�Ʈ�� ����*/
		Vector<String>testVector;
		testVector = new Vector<String>();
		testVector.add("JList�׽�Ʈ�����");
		testVector.add("JList�׽�Ʈ�����2");
		/**/
		
		setBackground(Color.white);
		add(selectBtn);
		add(shotSelectBtn);
		add(removeBtn);
		add(searchBtn);
		add(useStampBtn);
		add(clearBtn);
		add(paymentBtn);
		productList = new JList();
		add(new JScrollPane(productList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		add(productList);
		add(selectedProductList);
		add(new JScrollPane(selectedProductList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		add(memberTxt);
		add(lbl1);
		add(pricelLbl);
		add(lbl2);
		add(memberinfoLbl);
		
		
		/*�׽�Ʈ��*/
		productList.setListData(testVector);
		selectedProductList.setListData(testVector);
	}
	
}
