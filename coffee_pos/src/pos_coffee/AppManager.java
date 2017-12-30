package pos_coffee;

public class AppManager {

	private static AppManager instance;
	private DBDAO dao;
	private ButtonController buttonController;
	private MainController mainController;
	private MainPanel mainPanel;
	private MemberController memberController;
	private MemberPanel memberPanel;
	private ProductController productController;
	private ProductPanel productPanel;
	private SalesPanel salesPanel;
	private SellController sellController;
	private TabPanel tabPanel;
	private PosFrame posFrame;
	
	public PosFrame getPosFrame() {
		return posFrame;
	}
	public void setPosFrame(PosFrame posFrame) {
		this.posFrame = posFrame;
	}
	public DBDAO getDao() {
		return dao;
	}
	public void setDao(DBDAO dao) {
		this.dao = dao;
	}
	public ButtonController getButtonController() {
		return buttonController;
	}
	public void setButtonController(ButtonController buttonController) {
		this.buttonController = buttonController;
	}
	public MainController getMainController() {
		return mainController;
	}
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	public MainPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	public MemberController getMemberController() {
		return memberController;
	}
	public void setMemberController(MemberController memberController) {
		this.memberController = memberController;
	}
	public MemberPanel getMemberPanel() {
		return memberPanel;
	}
	public void setMemberPanel(MemberPanel memberPanel) {
		this.memberPanel = memberPanel;
	}
	public ProductController getProductController() {
		return productController;
	}
	public void setProductController(ProductController productController) {
		this.productController = productController;
	}
	public ProductPanel getProductPanel() {
		return productPanel;
	}
	public void setProductPanel(ProductPanel productPanel) {
		this.productPanel = productPanel;
	}
	public SalesPanel getSalesPanel() {
		return salesPanel;
	}
	public void setSalesPanel(SalesPanel salesPanel) {
		this.salesPanel = salesPanel;
	}
	public SellController getSellController() {
		return sellController;
	}
	public void setSellController(SellController sellController) {
		this.sellController = sellController;
	}
	public TabPanel getTabPanel() {
		return tabPanel;
	}
	public void setTabPanel(TabPanel tabPanel) {
		this.tabPanel = tabPanel;
	}
	
	private AppManager() {
	/*	dao = new DBDAO();
		//buttonController = new ButtonController();
		mainController = new MainController();
		mainPanel = new MainPanel();
		memberController = new MemberController();
		memberPanel = new MemberPanel();
		productController = new ProductController();
		productPanel = new ProductPanel();
		salesPanel = new SalesPanel();
		sellController = new SellController();
		tabPanel = new TabPanel();
		posFrame = new PosFrame();*/
	}
	
	public static AppManager createInstance() {
		if(instance == null) { 
			instance = new AppManager();
		}
		return instance;
	}
	/*
	public static void main(String args[]) {
		AppManager app = instance.createInstance();
	}
*/
}
