/**
 * 
 */
package SwingDemo;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class EventSourceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventSourceFrame es = new EventSourceFrame();
		es.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		es.setVisible(true);
	}

}

/*
 * a frame with a button panel
 * */
class EventSourceFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019069859833188217L;
	public EventSourceFrame(){
		setTitle("MulticastTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		//add panel to frame
		final EventSourcePanel panel = new EventSourcePanel();
		add(panel);
		panel.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent event){
				setTitle("EventSourceTest - " + event.getNewValue());
			}
		});
		
		
	}

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}


class EventSourcePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 151922912624744941L;

	@Override
	public void paintComponent(Graphics g){
		int oldPaintCount = paintCount;
		paintCount ++;
		firePropertyChangeListener(new PropertyChangeEvent(this, 
				"paintCount", oldPaintCount, paintCount));
		super.paintComponent(g);
		
	}
	
	/*
	 * add a change listener
	 * */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener){
		listenerList.add(PropertyChangeListener.class, listener);
	}
	
	/*
	 * remove a change listener
	 * */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener){
		listenerList.remove(PropertyChangeListener.class, listener);
	}

	public void firePropertyChangeListener(PropertyChangeEvent event){
		EventListener[] listeners = listenerList.getListeners(PropertyChangeListener.class);
		for(EventListener el: listeners){
			((PropertyChangeListener)el).propertyChange(event);
		}
			
	}
	
	public int getPaintCount(){
		return paintCount;
	}
	
	private int paintCount;
	
}
