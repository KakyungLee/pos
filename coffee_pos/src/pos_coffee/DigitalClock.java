package pos_coffee;

import java.awt.Font;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DigitalClock extends JLabel implements Runnable {

	private Thread thread;
	ImageIcon icon;
	private Font font;
	
	public DigitalClock(ImageIcon icon) {
		AppManager.createInstance().setDigitalClock(this);
		font = new Font("Arial Black",Font.PLAIN,35);
		this.icon = icon;
		if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
	}
	@Override
	public void run() {
		 while(true){
	            Calendar cal = Calendar.getInstance();
	            String clock = " "+cal.get(Calendar.HOUR)+" : "+cal.get(Calendar.MINUTE)+" : " +cal.get(Calendar.SECOND) ;
	            this.setFont(font);
	            this.setText(clock);
	            try{
	                Thread.sleep(1000);
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	        }

	}

}
