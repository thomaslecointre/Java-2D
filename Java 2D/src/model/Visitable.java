package model;

import java.awt.Rectangle;

public interface Visitable {

	public abstract void acceptVisitor(Visitor visitor);
	
	public abstract void translate(int translateX, int translateY);
	
	public abstract Rectangle getBounds();

}
