package pos_coffee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonController {
	private final TabPanel tp;

	public ButtonController() {
		AppManager.createInstance().setButtonController(this);
		tp = AppManager.createInstance().getTabPanel();
		
		
	}

	void appMain() {

	}

}
