package model;

import java.awt.Point;
import java.awt.Rectangle;

public class Text implements Visitable {

	private Point location;
	private String text;
	private int transparency=0;

	
	public Text(Point location, String text) {
		this.location=location;
		this.text=text;
		
	}
	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visitText(this);

	}

	@Override
	public void translate(int translateX, int translateY) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public Point getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	public String getString() {
		// TODO Auto-generated method stub
		return text;
	}
	public void transparent(int i) {
		this.transparency=i;
		
	}
	public double getTransparency() {
		return transparency;
	}

}
