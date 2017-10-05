package model;

import java.awt.Point;

public class Player implements Visitable {
	
	private Point location;
	private Head head;
	private Body body;
	
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
		this.location = location;
		head = new Head(radius, location);
		body = new Body(radius, limbLength, location);
	}
	
	public void translate(int dx, int dy) {
		setLocation(this.location.x + dx, this.location.y + dy);
	}
	
	private void setLocation(int x, int y) {
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
		private int radius;     // Useful for determining limb locations
		private int limbLength; // 
		private Point location; // 
		
		public Body(int radius, int limbLength, Point location) {
			this.leftArm = new Limb(location.x, location.y + radius, location.x + limbLength / 2, location.y + radius + limbLength);
			this.rightArm = new Limb(location.x, location.y + radius, location.x - limbLength / 2, location.y + radius + limbLength);
			this.leftLeg = new Limb(location.x, location.y + radius + limbLength, location.x + limbLength / 2, location.y + radius + 2 * limbLength);
			this.rightLeg = new Limb(location.x, location.y + radius + limbLength, location.x - limbLength / 2, location.y + radius + 2 * limbLength);
			this.chest = new Limb(location.x, location.y + radius, location.x, location.y + radius + limbLength);
			this.radius = radius;
			this.limbLength = limbLength;
			this.location = location;
		}
		
		public void setLocation(Point newLocation) {
			location = newLocation;
			leftArm.setLocation(location.x, location.y + radius, location.x + limbLength / 2, location.y + radius + limbLength);
			rightArm.setLocation(location.x, location.y + radius, location.x - limbLength / 2, location.y + radius + limbLength);
			leftLeg.setLocation(location.x, location.y + radius + limbLength, location.x + limbLength / 2, location.y + radius + 2 * limbLength);
			rightLeg.setLocation(location.x, location.y + radius + limbLength, location.x - limbLength / 2, location.y + radius + 2 * limbLength);
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
			public int x1;
			public int y1;
			public int x2; 
			public int y2;
			
			public Limb(int x1, int y1, int x2, int y2) {
				this.x1 = x1;
				this.y1 = y1;
				this.x2 = x2;
				this.y2 = y2;
			}
			
			public void setLocation(int x1, int y1, int x2, int y2) {
				this.x1 = x1;
				this.y1 = y1;
				this.x2 = x2;
				this.y2 = y2;
			}
		}
		
	}

	public void acceptVisitor(Visitor visitor) {
		visitor.visitPlayer(this);
	}
	
	
}
