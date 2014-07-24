/**
 * 
 */
package functionDemo;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * @author zfh1005
 */
public class InnerClassTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TalkingClock tClock = new TalkingClock(1000, true);
		tClock.start();
		JOptionPane.showMessageDialog(null, "Quit?");
		System.exit(0);
	}

	public static class TalkingClock{
		public TalkingClock(int interval, boolean beep){
			this.interval = interval;
			this.beep = beep;
		}
		
		public void start(){
			ActionListener listener = new TimerPrint();
			Timer t = new Timer(interval, listener);
			t.start();
		}

		private int interval;
		private boolean beep;
		
		private class TimerPrint implements ActionListener{
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date now = new Date();
				System.out.println("The time is: " + now);
				if(beep){
					Toolkit.getDefaultToolkit().beep();
				}
			}
		}
	}
}

