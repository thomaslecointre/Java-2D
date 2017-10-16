package model;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Obstacle implements Visitable {

	public final int width;
	public final int height;
	public final Point location;
	public final Color color;
	public double alpha=1f;
	private double shearing = 0;
	
	public Obstacle(int width, int height, Point location) {
		this.width = width;
		this.height = height;
		this.location = location;
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		color = new Color(r, g, b);
	}

	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visitObstacle(this);
	}

	public void transparent(double tmpalpha) {
		alpha=tmpalpha;
	}

	public void shear(double shearing) {
		this.shearing = shearing;
	}

	public double getShearing() {
		return shearing;
	}

}
