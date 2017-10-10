package model;

import java.awt.Point;

import view.Game;

public class TranslationModel extends Model {

	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(100, 100)));
		objects.add(new Floor(Game.WIDTH, Game.HEIGHT));
		objects.add(new Obstacle(100, 200, new Point(Game.WIDTH / 2, Game.HEIGHT / 2)));
	}

	@Override
	public void update() {
		if(oldTimeMillis != 0) {
			while(System.currentTimeMillis() - oldTimeMillis < deltaTime) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
