package model;

import java.awt.Point;
import java.awt.Rectangle;

public class Player implements Visitable {

	private Point location;
	private Head head;
	private Body body;
	
	private final int RADIUS;
	private final int LIMB_LENGTH;
	
	
	private double rotation = 0;
	private int size = 1;
	private boolean jumping = false;
	private boolean preparingToJump = false;

	public Rectangle getBounds() {
		return new Rectangle(Math.min(head.getLocation().x, body.rightArm.outerX), head.getLocation().y,
				Math.max(RADIUS * 2, LIMB_LENGTH), (int) (RADIUS * 2 + LIMB_LENGTH + Math.sqrt(LIMB_LENGTH * LIMB_LENGTH * 3 / 4)));
	}
	
	public int locationToFloor() {
		return getBounds().height - RADIUS;
	}
	
	public Head getHead() {
		return head;
	}

	public Body getBody() {
		return body;
	}

	public Point getLocation() {
		return location;
	}

	public Player(int radius, int limbLength, Point location) {
		this.RADIUS = radius;
		this.LIMB_LENGTH = limbLength;
		this.location = location;
		head = new Head(radius, location);
		body = new Body(radius, limbLength, location);
	}

	public void translate(int translateX, int translateY) {
		setLocation(this.location.x + translateX, this.location.y + translateY);
	}

	public void setLocation(int x, int y) {
		location.setLocation(x, y);
		head.setLocation(location);
		body.setLocation(location);
	}

	public class Head {
		private int radius;
		private Point center;

		public Head(int radius, Point center) {
			this(radius, center.x - radius, center.y - radius);
		}

		public Head(int radius, int x, int y) {
			this.radius = radius;
			center = new Point(x, y);
		}

		public void setLocation(Point newCenter) {
			center.x = newCenter.x - radius;
			center.y = newCenter.y - radius;
		}

		public Point getLocation() {
			return center;
		}

		public int getRadius() {
			return radius;
		}

		public void setRadius(int radius) {
			this.radius = radius;
		}

	}

	public class Body {
		private Limb leftArm;
		private Limb rightArm;
		private Limb leftLeg;
		private Limb rightLeg;
		private Limb chest;
		private int radius; // Useful for determining limb locations
		private int limbLength; //
		private Point location; //

		public Body(int radius, int limbLength, Point location) {
			this.leftArm = new Limb(location.x, location.y + radius, location.x + limbLength / 2,
					location.y + radius + limbLength);
			this.rightArm = new Limb(location.x, location.y + radius, location.x - limbLength / 2,
					location.y + radius + limbLength);
			this.leftLeg = new Limb(location.x, location.y + radius + limbLength, location.x + limbLength / 2,
					location.y + radius + 2 * limbLength);
			this.rightLeg = new Limb(location.x, location.y + radius + limbLength, location.x - limbLength / 2,
					location.y + radius + 2 * limbLength);
			this.chest = new Limb(location.x, location.y + radius, location.x, location.y + radius + limbLength);
			this.radius = radius;
			this.limbLength = limbLength;
			this.location = location;
		}

		public void setLocation(Point newLocation) {
			location = newLocation;
			leftArm.setLocation(location.x, location.y + radius, location.x + limbLength / 2,
					location.y + radius + limbLength);
			rightArm.setLocation(location.x, location.y + radius, location.x - limbLength / 2,
					location.y + radius + limbLength);
			leftLeg.setLocation(location.x, location.y + radius + limbLength, location.x + limbLength / 2,
					location.y + radius + 2 * limbLength);
			rightLeg.setLocation(location.x, location.y + radius + limbLength, location.x - limbLength / 2,
					location.y + radius + 2 * limbLength);
			chest.setLocation(location.x, location.y + radius, location.x, location.y + radius + limbLength);
		}

		public Limb getLeftArm() {
			return leftArm;
		}

		public Limb getRightArm() {
			return rightArm;
		}

		public Limb getLeftLeg() {
			return leftLeg;
		}

		public Limb getRightLeg() {
			return rightLeg;
		}

		public Limb getChest() {
			return chest;
		}

		public class Limb {
			public int centerX;
			public int centerY;
			public int outerX;
			public int outerY;

			public Limb(int x1, int y1, int x2, int y2) {
				this.centerX = x1;
				this.centerY = y1;
				this.outerX = x2;
				this.outerY = y2;
			}

			public void setLocation(int x1, int y1, int x2, int y2) {
				this.centerX = x1;
				this.centerY = y1;
				this.outerX = x2;
				this.outerY = y2;
			}
		}

	}

	public void acceptVisitor(Visitor visitor) {
		visitor.visitPlayer(this);
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public void jump() {
		this.jumping = true;
	}

	public void rotate(double rotation) {
		this.rotation = rotation;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	public void scale(int size) {
		this.size = size;
	}
	public int getSize() {
		return this.size;
	}

	public void prepareToJump() {
		this.preparingToJump = true;
	}

	public boolean wantsToJump() {
		return this.preparingToJump;
	}

	public void noLongerPreparingToJump() {
		this.preparingToJump = false;
	}

}
