package model;

import java.awt.Point;

import view.Game;

public class TranslationModel extends Model {

	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(100, 100)));
		objects.add(new Floor(Game.WIDTH, Game.HEIGHT));
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
				for(Visitable object : objects) {
					if(!(object instanceof Player)) {
						
					}
				}
			}
		}
	}

}
