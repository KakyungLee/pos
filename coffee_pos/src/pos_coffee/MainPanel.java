package pos_coffee;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class MainPanel extends JPanel{
	//상단 탭 패널
	
	protected JButton selectBtn = new JButton("선택");		//결제할 상품을 선택
	protected JButton shotSelectBtn = new JButton("샷추가/선택");	//결제할 상품에 샷추가 선택시 제품이름에 (샷추가), 가격 500원 추가
	protected JButton removeBtn = new JButton("제거");		//선택된 상품 결제목록에서 제거
	protected JButton searchBtn = new JButton("조회");		//핸드폰 번호로 회원 조회 -> 스탬프 적립, 사용시 필수
	protected JButton useStampBtn = new JButton("스탬프사용");	//스탬프 사용
	protected JButton clearBtn = new JButton("취소");	//결제리스트 및 회원 화면 클리어
	protected JButton paymentBtn = new JButton("결제");	//결제 진행 및 매출 발생
	protected JList productList;	//상품목록
	protected JList selectedProductList = new JList();	//선택된 상품. 결제리스트
	protected JTextField memberTxt = new JTextField("회원번호입력");	//조회할 회원 휴대폰번호 입력
	private JLabel lbl1 = new JLabel("총액");	
	protected JLabel pricelLbl = new JLabel("0");	//결제할 총액
	private JLabel lbl2 = new JLabel("원");
	protected JLabel memberinfoLbl = new JLabel("::회원정보::");	//회원 조회시 회원정보 나타냄
	
	
	MainPanel(){
		
		/*테스트용 변수*/
		Vector<String>testVector;
		testVector = new Vector<String>();
		testVector.add("JList테스트용백터");
		testVector.add("JList테스트용백터2");
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
		
		
		/*테스트용*/
		productList.setListData(testVector);
		selectedProductList.setListData(testVector);
	}
	
}
