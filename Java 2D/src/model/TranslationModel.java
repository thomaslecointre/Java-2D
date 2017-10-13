package model;

import java.awt.Point;
import java.util.Random;

import exceptions.NoFloorException;
import exceptions.NoPlayerException;
import view.Game;
import view.View;

public class TranslationModel extends Model {
	
	private final int minObjectWidth = 50, maxObjectWidth = 200, minObjectHeight = 50, maxObjectHeight = 300;
	private final int objectCount = 20;
	
	@Override
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(100, (int) (Game.HEIGHT - 100 -(20 + 50 + Math.sqrt(50 * 50 * 3 / 4))))));
		objects.add(new Floor(Game.WIDTH * 4, Game.HEIGHT - 100));
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
		// int yTranslation = args[1];
		try {
			Player player = findPlayer();
			player.translate(xTranslation, 0);
			if(player.isJumping()) {
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Floor floor = findFloor();
							long oldTimeMillis = System.currentTimeMillis();
							while(floor.bounds.intersects(player.getBounds())) {
								long currentTimeMillis = System.currentTimeMillis();
								int deltaTimeMultiple = (int) ((currentTimeMillis - oldTimeMillis) / View.DELTA_TIME_REQUIREMENT);
								if(deltaTimeMultiple > 0) {
									player.setLocation(player.getLocation().x, cinematicEquation(deltaTimeMultiple * View.DELTA_TIME_REQUIREMENT));
									oldTimeMillis = currentTimeMillis;
								}
								try {
									Thread.sleep(5);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							player.setJumping(false);
						} catch (NoFloorException e) {
							e.printStackTrace();
						}
					}
					
					private int cinematicEquation(long elapsedTime) {
						final int G = 10;
						final int INITIAL_SPEED = 500;
						return (int) ((1/2) * G * elapsedTime * elapsedTime - INITIAL_SPEED * elapsedTime + player.locationToFloor());
					}
				});
				thread.start();
			}
		} catch (NoPlayerException e) {
			e.printStackTrace();
		}
	}
}
