package model;

import java.awt.Point;
import java.util.Random;

import view.Game;

public class TransparentModel extends Model {

	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(100, 100)));
		objects.add(new Obstacle(100, 200, new Point(50, 50)));

	}

	@Override
	public void update(int... args) {
		int tmpalpha = args[0];
		for (Visitable object : objects) {
			if (object instanceof Obstacle) {

				Obstacle obstacle = (Obstacle) object;
				obstacle.transparent((float) (tmpalpha) / 1000);
			}
		}
	}

	@Override
	public void update(double... args) {
		double tmpalpha = args[0];
		for (Visitable object : objects) {
			if (object instanceof Obstacle) {

				Obstacle obstacle = (Obstacle) object;
				obstacle.transparent(tmpalpha);
			}
		}
	}

}
