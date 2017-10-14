package model;

import java.awt.Point;

import view.Game;

public class RotationModel extends Model {
	
	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(100, (int) (Game.HEIGHT - 100 - (20 + 50 + Math.sqrt(50 * 50 * 3 / 4))))));
		objects.add(new Floor(Game.WIDTH * 4, Game.HEIGHT - 100));
	}

	@Override
	public void update(int... args) {
		int tmpalpha = args[0];
		for(Visitable object : objects) {
			if(object instanceof Player) {
				Player player = (Player) object;
				player.rotate(tmpalpha);
			}
		}
	}
}