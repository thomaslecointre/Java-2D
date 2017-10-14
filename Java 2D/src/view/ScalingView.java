package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import exceptions.NoPlayerException;
import model.Model;
import model.Player;

public class ScalingView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sizex = 1 ;
	private int sizey = 1;
	private int totalXSize = 0;
	
	public ScalingView(Model model, Controller controller) {
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
				int xSize = translationMultiple * sizex;
				totalXSize += xSize;
				model.update(xSize);
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
			System.out.println("scaling : player not found");
			e.printStackTrace();
		}
		((Graphics2D) g).scale(player.getSize(), player.getSize());
		//((Graphics2D) g).translate(-Game.WIDTH/(2*sizex), -Game.HEIGHT/(2*sizey));
		visitor.visitOnlyPlayer(g);
	}
}
