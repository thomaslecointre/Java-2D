package model;

import java.awt.Point;
import java.util.Random;

import exceptions.NoFloorException;
import exceptions.NoPlayerException;
import view.Game;
import view.View;

public class TranslationModel extends Model {

	private final int minObjectWidth = 50, maxObjectWidth = 200, minObjectHeight = 50, maxObjectHeight = 300;
	private final int objectCount = 15;
	private boolean gameover=false;

	@Override
	public void buildModel() {
		objects.add(
				new Player(20, 50, new Point(100, (int) (Game.HEIGHT - 100 - (20 + 50 + Math.sqrt(50 * 50 * 3 / 4))))));
		objects.add(new Floor(Game.WIDTH * 10, Game.HEIGHT - 100));
		for (int i = 0; i < objectCount; i++) {
			Random rand = new Random();
			int randomWidth = rand.nextInt(maxObjectWidth - minObjectWidth) + minObjectWidth;
			int randomHeight = rand.nextInt(maxObjectHeight - minObjectHeight) + minObjectHeight;
			int randomX = rand.nextInt(Game.WIDTH * 9)+Game.WIDTH;
			objects.add(new Obstacle(randomWidth, randomHeight, new Point(randomX, Game.HEIGHT - 100 - randomHeight)));
		}
		objects.add(new Text(new Point(Game.WIDTH/2,Game.HEIGHT/2),"GAME OVER"));

	}

	@Override
	public void update(int... args) {
		int xTranslation = args[0];
		int yTranslation = args[1];
		
		
		try {
			
			
			Player player = findPlayer();
			
			if (player.wantsToJump()) {
				if(!player.isJumping()) {
					player.setJumping(true);
					/******************************************/
					Thread thread = new Thread(new Runnable() {
						final int YO = player.getLocation().y;
						@Override
						public void run() {
							try {
								Floor floor = findFloor();
								long oldTimeMillis = System.currentTimeMillis();
								
								while (!floor.getBounds().intersects(player.getBounds())) {
									long currentTimeMillis = System.currentTimeMillis();
									long deltaTimeMultiple = (currentTimeMillis - oldTimeMillis)
											/ View.DELTA_TIME_REQUIREMENT;
									if (deltaTimeMultiple > 0) {
										player.setLocation(player.getLocation().x,
												(int) cinematicEquation(deltaTimeMultiple * View.DELTA_TIME_REQUIREMENT));
									}
								}
								
								if(floor.getBounds().intersects(player.getBounds())) {
									player.noLongerPreparingToJump();
									player.setJumping(false);
									player.setLocation(player.getLocation().x,
											player.getLocation().y - 10);
								}
								
							} catch (NoFloorException e) {
								e.printStackTrace();
							}
						}

						private double cinematicEquation(long elapsedTime) {
							final double appropriateTime = (double) elapsedTime / 500;
							final int G = 1000;
							final int INITIAL_SPEED = -1000;
							
							double result = (0.5 * G * appropriateTime * appropriateTime)
									+ (INITIAL_SPEED * appropriateTime) + YO;
							return result;
						}
					});
					/******************************************/
					thread.start();
				}
			}
			for(Visitable object : objects) {
				if(!(object instanceof Player)) {
					object.translate(xTranslation, 0);
				}
				if(object instanceof Obstacle) {
					
					if(((Obstacle)object).getBounds().intersects(player.getBounds())) {
						
						gameover=true;
					}
				}
				if(object instanceof Text) {
					if(gameover) {
						((Text)object).transparent(1);
					}
				}
			}
			
			
		} catch (NoPlayerException e) {
			e.printStackTrace();
		}
	}
}
