package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import exceptions.NoPlayerException;
import model.Model;
import model.Player;

public class RotationView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int rotationRate = 2;
	private int totalRotation = 0;
	
	public RotationView(Model model, Controller controller) {
		super(model, controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		long currentTimeMillis = System.currentTimeMillis();
		if(oldTimeMillis != 0) {
			int rotationMultiple = (int) ((currentTimeMillis - oldTimeMillis) / DELTA_TIME_REQUIREMENT);
			if(rotationMultiple > 0) {
				int rotation = rotationMultiple * rotationRate;
				totalRotation += rotation;
				model.update(rotation);
				oldTimeMillis = currentTimeMillis;
			}
		} else {
			oldTimeMillis = currentTimeMillis;
		}
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("problem with thread.sleep(1)");
			e.printStackTrace();
		}
		visitor.visitAllButPlayer(g);
		
		Player player = null;
		try {
			player = model.findPlayer();
		} catch (NoPlayerException e) {
			System.out.println("rotation : player not found");
			e.printStackTrace();
		}
		float centerPlayerX = player.getBounds().width/2 + player.getLocation().x - player.getHead().getRadius();
		float centerPlayerY = player.getBounds().height/2 + player.getLocation().y - player.getHead().getRadius();
		((Graphics2D) g).rotate(totalRotation * Math.PI / 180, centerPlayerX, centerPlayerY);
		visitor.visitPlayer(player);
	}
}