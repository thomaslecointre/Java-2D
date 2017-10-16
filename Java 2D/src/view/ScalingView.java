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
	//private int totalXSize = 0;
	
	public ScalingView(Model model, Controller controller) {
		super(model, controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Player player = null;
		try {
			player = model.findPlayer();
			player.setLocation(Game.WIDTH/2, Game.HEIGHT/2);
			sizex = player.getSize();
			sizey = player.getSize();
		} catch (NoPlayerException e) {
			System.out.println("scaling : player not found");
			e.printStackTrace();
		}
		((Graphics2D) g).scale(sizex, sizey);
		if(sizex != 1)
			((Graphics2D) g).translate(- (player.getLocation().getX()-player.getLocation().getX()/sizex), -(player.getLocation().getY()-player.getLocation().getY()/sizex));
		visitor.visitObjects(g);
	}
}
