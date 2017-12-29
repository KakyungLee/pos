package pos_coffee;
import java.awt.*;
import javax.swing.*;

public class SalesPanel extends JPanel{
	//상단 탭 패널
	
	protected JButton dateSelectBtn = new JButton("날짜선택");		//결제할 상품을 선택
	protected JButton salesSelectBtn = new JButton("");	//결제할 상품에 샷추가 선택시 제품이름에 (샷추가), 가격 500원 추가
	protected JButton refundBtn = new JButton("거래취소");		//선택된 상품 결제목록에서 제거
	protected JList salesList = new JList();	//상품목록
	protected JLabel salesAccountLbl = new JLabel("0");	
	private JLabel lbl2 = new JLabel("원");
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
