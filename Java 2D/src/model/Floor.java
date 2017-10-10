package model;

public class Floor implements Visitable {

	public final int x1;
	public final int y1;
	public final int x2;
	public final int y2;
	
	public Floor(int width, int height) {
		x1 = 0;
		y1 = height - 100;
		x2 = width;
		y2 = height - 100;
	}
	
	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visitFloor(this);
	}

}
