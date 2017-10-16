package model;

import java.awt.Color;
import java.awt.Rectangle;

import view.Game;

public class Floor implements Visitable {

	public final int floorLeftMostX;
	public final int floorY1;
	public final int floorRightMostX;
	public final int floorY2;
	public final Color groundColor;
	private int translateY;
	private int translateX;
	
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

	@Override
	public void translate(int translateX, int translateY) {
		this.translateX = translateX;
		this.translateY = translateY;
	}

	public double getTranslateX() {
		return translateX;
	}

	public double getTranslateY() {
		return translateY;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(floorLeftMostX + translateX, floorY1 + translateY, floorRightMostX - floorLeftMostX, 1);
	}

}
