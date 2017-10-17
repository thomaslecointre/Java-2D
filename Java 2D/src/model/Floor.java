package model;

import java.awt.Color;
import java.awt.Rectangle;

import view.Game;

public class Floor implements Visitable {

	public final int FLOOR_LEFT_MOST_X;
	public final int FLOOR_LEFT_MOST_Y;
	public final int FLOOR_RIGHT_MOST_X;
	public final int FLOOR_RIGHT_MOST_Y;
	public final Color GROUND_COLOR;
	private int translateY;
	private int translateX;
	
	public Floor(int width, int height) {
		FLOOR_LEFT_MOST_X = 0;
		FLOOR_LEFT_MOST_Y = height;
		FLOOR_RIGHT_MOST_X = width;
		FLOOR_RIGHT_MOST_Y = height;
		GROUND_COLOR = new Color(0, 100, 0);
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
		return new Rectangle(FLOOR_LEFT_MOST_X + translateX, FLOOR_LEFT_MOST_Y + translateY, FLOOR_RIGHT_MOST_X - FLOOR_LEFT_MOST_X, 1);
	}

}
