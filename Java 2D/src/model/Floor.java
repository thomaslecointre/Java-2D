package model;

import java.awt.Color;

public class Floor implements Visitable {

	public final int floorLeftMostX;
	public final int floorY1;
	public final int floorRightMostX;
	public final int floorY2;
	public final Color groundColor;
	
	public Floor(int width, int height) {
		floorLeftMostX = 0;
		floorY1 = height;
		floorRightMostX = width;
		floorY2 = height;
		groundColor = new Color(0, 100, 0);
	}
	
	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visitFloor(this);
	}

}
