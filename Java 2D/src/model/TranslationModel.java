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
	public void update(int xTranslation, int yTranslation) {
		for(Visitable object : objects) {
			if(object instanceof Player) {
				Player player = (Player) object;
				player.translate(xTranslation, yTranslation);
			}
		}
	}

}
