package model;

import java.awt.Point;
import java.util.Random;

import view.Game;

public class TranslationModel extends Model {
	
	private final int minObjectWidth = 50, maxObjectWidth = 200, minObjectHeight = 50, maxObjectHeight = 300;
	private final int objectCount = 20;
	
	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(100, 100)));
		objects.add(new Floor(Game.WIDTH * 10, Game.HEIGHT - 100));
		for(int i = 0; i < objectCount; i++) {
			Random rand = new Random();
			int randomWidth = rand.nextInt(maxObjectWidth - minObjectWidth) + minObjectWidth;
			int randomHeight = rand.nextInt(maxObjectHeight - minObjectHeight) + minObjectHeight;
			int randomX = rand.nextInt(Game.WIDTH * 4);
			objects.add(new Obstacle(randomWidth, randomHeight, new Point(randomX, Game.HEIGHT - 100 - randomHeight)));
		}
		
	}

	@Override
	public void update(int... args) {
		int xTranslation = args[0];
		int yTranslation = args[1];
		for(Visitable object : objects) {
			if(object instanceof Player) {
				Player player = (Player) object;
				player.translate(xTranslation, yTranslation);
			}
		}
	}

}
