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
	private final double xShearingRate = 0.001
			;
	private double totalXShearing = 0;
	// private double totalRotation = Math.PI/180;

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
				double xShearing = shearingMultiple * xShearingRate;
				totalXShearing += xShearing;
				model.update(totalXShearing);
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

		visitor.visitObjects(g);
	}
}