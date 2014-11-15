/**
 * 
 */
package XMLDemo.XMLWriteTest;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * @author zfh1005
 *
 */
public class RectangleComponent extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2175239154048439097L;
	public RectangleComponent() {
		rectangle2ds = new ArrayList<Rectangle2D>();
		colors = new ArrayList<Color>();
		generRandom = new Random();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			builder = factory.newDocumentBuilder();
		}
		catch(ParserConfigurationException exception){
			exception.printStackTrace();
		}
	}
	
	/*
	 * Create a new random drawing.
	 * */
	public void newDrawing(){
		int n = 10 + generRandom.nextInt(20);
		rectangle2ds.clear();
		colors.clear();
		for(int i = 1; i <= n; i ++){
			int x = generRandom.nextInt(getWidth());
			int y = generRandom.nextInt(getHeight());
			int width = generRandom.nextInt(getWidth() - x);
			int height = generRandom.nextInt(getHeight() - y);
			rectangle2ds.add(new Rectangle(x, y, width, height));
			int r = generRandom.nextInt(256);
			int g = generRandom.nextInt(256);
			int b = generRandom.nextInt(256);
			colors.add(new java.awt.Color(r, g, b));
		}
		repaint();		
	}
	
	/*
	 * paint component
	 * */
	@Override
	public void  paintComponent(Graphics graphics) {
		if(rectangle2ds.size() == 0){
			newDrawing();
		}
		Graphics2D graphics2d = (Graphics2D) graphics;
		//draw all rectangles
		for(int i = 0; i < rectangle2ds.size(); i++){
			graphics2d.setPaint(colors.get(i));
			graphics2d.fill(rectangle2ds.get(i));
		}
	}
	
	/*
	 * Create an SVG document of the current drawing.
	 * @return the DOM tree of the SVG document
	 * */
	public Document buildDocument(){
		Document document = builder.newDocument();
		Element svgElement = document.createElement("rect");
		document.appendChild(svgElement);
		svgElement.setAttribute("width", "" + getWidth());
		svgElement.setAttribute("height", "" + getHeight());
		for(int i = 0; i < rectangle2ds.size(); i ++){
			Color color = colors.get(i);
			Rectangle2D rectangle2d = rectangle2ds.get(i);
			Element rectElement = document.createElement("rect");
			rectElement.setAttribute("x", "" + rectangle2d.getX());
			rectElement.setAttribute("y", "" + rectangle2d.getY());
			rectElement.setAttribute("width", "" + rectangle2d.getWidth());
			rectElement.setAttribute("height", "" + rectangle2d.getHeight());
			rectElement.setAttribute("fill", colorToString(color));
			svgElement.appendChild(rectElement);
		}
		return document;
	}
	
	/*
	 *writers an SVG document of the current drawing.
	 *@param write the document destination 
	 * */
	public void writeDocument(XMLStreamWriter writer)throws XMLStreamException {
		writer.writeStartDocument();
		writer.writeDTD("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20000802//EN\"" 
				+ "\"http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd\">");
		writer.writeStartElement("svg");
		writer.writeAttribute("width", "" + getWidth());
		writer.writeAttribute("height", "" + getHeight());
		for(int i = 0; i < rectangle2ds.size(); i ++){
			Color color = colors.get(i);
			Rectangle2D rectangle2d = rectangle2ds.get(i);
			writer.writeEmptyElement("rect");
			writer.writeAttribute("x", "" + rectangle2d.getX());
			writer.writeAttribute("y", "" + rectangle2d.getY());
			writer.writeAttribute("width", "" + rectangle2d.getWidth());
			writer.writeAttribute("height", "" + rectangle2d.getHeight());
			writer.writeAttribute("fill", colorToString(color));
		}
		//close svg element
		writer.writeEndElement();
	}
	
	
	
	/**
	 * @param color
	 * @return
	 */
	private static String colorToString(Color color) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(Integer.toHexString(color.getRGB() & 0xFFFFFFF));
		while(buffer.length() < 6){
			buffer.insert(0, "0");
		}
		buffer.insert(0, "#");
		return buffer.toString();
	}



	private ArrayList<Rectangle2D> rectangle2ds;
	private ArrayList<Color> colors;
	private Random generRandom;
	private DocumentBuilder builder;
}
