package model;

import java.awt.Point;

import view.Game;

public class EndModel extends Model {

	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(Game.WIDTH/8, Game.HEIGHT/8)));
		objects.add(new Player(20, 50, new Point(Game.WIDTH*7/8, Game.HEIGHT/8)));
		objects.add(new Player(20, 50, new Point(Game.WIDTH*7/8, Game.HEIGHT*3/4)));
		objects.add(new Player(20, 50, new Point(Game.WIDTH/8, Game.HEIGHT*3/4)));
		objects.add(new Obstacle(Game.WIDTH/4, Game.HEIGHT/4, new Point(Game.WIDTH*3/8, Game.HEIGHT*3/8)));
	}

	@Override
	public void update(int... args) {
		int tmpRotation = args[0];
		int tmpShearing = args[1];
		int tmpTranslationX = args[2];
		int tmpTranslationY = args[3];
		int tmpTransparent = args[4];
		int tmpScaling = args[5];
		
		((Player) objects.get(0)).rotate(tmpRotation);
		((Player) objects.get(1)).shear(tmpShearing);
		((Player) objects.get(2)).translate(tmpTranslationX, tmpTranslationY);
		((Player) objects.get(3)).transparent(tmpTransparent);
		((Obstacle) objects.get(4)).scale(tmpScaling);
	}
}