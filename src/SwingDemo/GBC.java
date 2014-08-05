/**
 * 
 */
package SwingDemo;
import java.awt.*;
/**
 * @author zfh1005
 */
public class GBC extends GridBagConstraints{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2110355826605536932L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public GBC(int gridx, int gridy){
		this.gridx = gridx;
		this.gridy = gridy;		
	}
	
	public GBC(int gridx, int gridy, int gridwidth, int gridheigth){
		this.gridx = gridx;
		this.gridy = gridy;	
		this.gridwidth = gridwidth;
		this.gridheight = gridheigth;
	}
	
	public GBC setAnchor(int anchor){
		this.anchor = anchor;
		return this;
	}
	
	public GBC setFill(int fill){
		this.fill = fill;
		return this;
	}
	
	public GBC setWeight(double weigthx, double weigthy){
		this.weightx = weigthx;
		this.weighty = weigthy;
		return this;
	}
	
	public GBC setInsets(int distance){
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}
	
	public GBC setInsets(int top, int left, int bottom, int right){
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	public GBC setIpad(int ipadx, int ipady){
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}

}
