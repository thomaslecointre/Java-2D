package model;

import java.awt.Point;
import java.util.Random;

import view.Game;

public class ShearingModel extends Model {

	private final int MIN_OBJECT_WIDTH = 50, MAX_OBJECT_WIDTH = 200, MIN_OBJECT_HEIGHT = 50, MAX_OBJECT_HEIGHT = 300;
	private final int OBJECT_COUNT = 5;
	
	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(100, (int) (Game.HEIGHT - 100 - (20 + 50 + Math.sqrt(50 * 50 * 3 / 4))))));
		objects.add(new Floor(Game.WIDTH * 4, Game.HEIGHT - 100));
		for (int i = 0; i < OBJECT_COUNT; i++) {
			Random rand = new Random();
			int randomWidth = rand.nextInt(MAX_OBJECT_WIDTH - MIN_OBJECT_WIDTH) + MIN_OBJECT_WIDTH;
			int randomHeight = rand.nextInt(MAX_OBJECT_HEIGHT - MIN_OBJECT_HEIGHT) + MIN_OBJECT_HEIGHT;
			int randomX = rand.nextInt(Game.WIDTH-150) + 150;
			objects.add(new Obstacle(randomWidth, randomHeight, new Point(randomX, Game.HEIGHT - 100 - randomHeight)));
		}
	}

	@Override
	public void update(int... args) {
		
	}

	@Override
	public void update(double... args) {
		double shearing = args[0];
		for(Visitable object : objects) {
			if(object instanceof Obstacle) {
				Obstacle obstacle = (Obstacle) object;
				obstacle.shear(shearing);
			}
		}
	}
}