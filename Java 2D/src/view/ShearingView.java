package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import controller.Controller;
import exceptions.NoFloorException;
import exceptions.NoPlayerException;
import model.Floor;
import model.Model;
import model.Obstacle;
import model.Player;
import model.Visitable;

public class ShearingView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final float xShearingRate = 0.001f;
	private float totalXShearing = 0f;
	private double totalRotation = Math.PI/180;

	public ShearingView(Model model, Controller controller) {
		super(model, controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		long currentTimeMillis = System.currentTimeMillis();
		if(oldTimeMillis != 0) {
			int shearingMultiple = (int) ((currentTimeMillis - oldTimeMillis) / DELTA_TIME_REQUIREMENT);
			if(shearingMultiple > 0) {
				float xShearing = shearingMultiple * xShearingRate;
				totalXShearing += xShearing;
				model.update((int) xShearing);
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
				
		Player player = null;
		try {
			player = model.findPlayer();
		} catch (NoPlayerException e) {
			System.out.println("shearing : player not found");
			e.printStackTrace();
		}
		visitor.visitOnlyPlayer(g);
		
		Floor floor = null;
		try {
			floor = model.findFloor();
		} catch (NoFloorException e) {
			System.out.println("shearing : floor not found");
			e.printStackTrace();
		}
		visitor.visitFloor(floor);
		
		((Graphics2D) g).shear(totalXShearing*2, -totalXShearing/10);
		visitor.visitAllObstacles(g);
	}
}