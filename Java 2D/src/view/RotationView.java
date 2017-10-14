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
	private final int xTranslationRate = 2;
	private int totalXTranslation = 0;
	
	public RotationView(Model model, Controller controller) {
		super(model, controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
//		tmpalpha += alphaRate*1000;
//
//		model.update(tmpalpha);
//		System.out.println(tmpalpha);
		
		long currentTimeMillis = System.currentTimeMillis();
		if(oldTimeMillis != 0) {
			int translationMultiple = (int) ((currentTimeMillis - oldTimeMillis) / DELTA_TIME_REQUIREMENT);
			if(translationMultiple > 0) {
				int xTranslation = translationMultiple * xTranslationRate;
				totalXTranslation += xTranslation;
				model.update(xTranslation);
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
		((Graphics2D) g).rotate(totalXTranslation * Math.PI / 180, centerPlayerX, centerPlayerY);
		visitor.visitOnlyPlayer(g);
	}
}