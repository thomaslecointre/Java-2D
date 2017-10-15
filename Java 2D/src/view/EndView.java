package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import model.Model;
import model.Obstacle;
import model.Player;

public class EndView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalIteration = 0;
	private final int rotationRate = 2;
	private final float shearingRate = 0.001f;
	private final int xTranslationRate = 15;
	private final int yTranslationRate = 15;
	private final float transparenceRate = 0.001f;
	private final float scalingRate = 1.1f;

	public EndView(Model model, Controller controller) {
		super(model, controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		long currentTimeMillis = System.currentTimeMillis();
		int rotation = 0, translationX = 0, translationY = 0;
		float shearing = 0, transparence = 0, scaling = 0;
		if(oldTimeMillis != 0) {
			int timeMultiple = (int) ((currentTimeMillis - oldTimeMillis) / DELTA_TIME_REQUIREMENT);
			if(timeMultiple > 0) {
				rotation = timeMultiple * rotationRate;
				shearing = timeMultiple * shearingRate * 1000;
				translationX = timeMultiple * xTranslationRate;
				translationY = timeMultiple * yTranslationRate;
				transparence = timeMultiple * transparenceRate * 1000;
				scaling = timeMultiple * scalingRate * 1000;
				totalIteration++;
				model.update(rotation, (int) shearing, translationX, translationY, (int) transparence, (int) scaling);
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
		
		Player p0 = (Player) model.getObjects().get(0);
		float centerXPlayer0 = p0.getBounds().width/2 + p0.getLocation().x - p0.getHead().getRadius();
		float centerYPlayer0 = p0.getBounds().height/2 + p0.getLocation().y - p0.getHead().getRadius();
		((Graphics2D) g).rotate(totalIteration * rotation * Math.PI / 180, centerXPlayer0, centerYPlayer0);
		visitor.visitOnlyObject(g, p0);
		
		Player p1 = (Player) model.getObjects().get(1);
		((Graphics2D) g).shear(totalIteration * shearing * 2, -totalIteration * shearing / 10);
		visitor.visitOnlyObject(g, p1);
		
		Player p2 = (Player) model.getObjects().get(2);
		g.translate((int) (20*Math.cos(totalIteration * translationX * Math.PI / 180)), (int) (20*Math.sin(totalIteration * translationY * Math.PI / 180)));
		visitor.visitOnlyObject(g, p2);
		
		Player p3 = (Player) model.getObjects().get(3);
		p3.transparent((int) (totalIteration * transparence));
		visitor.visitOnlyObject(g, p3);
		
		Obstacle obstacle = (Obstacle) model.getObjects().get(4);
		((Graphics2D) g).scale(totalIteration * scaling * 1.77, totalIteration * scaling);
		visitor.visitOnlyObject(g, obstacle);
		
		g.drawString("Goodbye, World", Game.WIDTH*7/16, Game.HEIGHT*7/16);
	}
}