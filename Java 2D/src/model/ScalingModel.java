package model;

import java.awt.Point;
import java.util.Random;

import view.Game;

public class ScalingModel extends Model{

	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(Game.WIDTH/2, Game.HEIGHT/2)));
	}

	@Override
	public void update(int... args) {
		int size = args[0];
		for(Visitable object : objects) {
			if(object instanceof Player) {
				Player player = (Player) object;
				player.scale(size);
			}
		}
	}

}
