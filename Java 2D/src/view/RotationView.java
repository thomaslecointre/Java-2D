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
	private final double rotationRate = 2;
	private double totalRotation = 0;
	
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
				double rotation = rotationMultiple * rotationRate;
				totalRotation += rotation;
				model.update(totalRotation * Math.PI / 180);
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