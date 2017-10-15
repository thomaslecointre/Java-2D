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
	private int totalRotation = 0;
	private final float shearingRate = 0.001f;
	private float totalShearing = 0;
	private final int xTranslationRate = 15;
	private int totalXTranslation = 0;
	private final int yTranslationRate = 15;
	private int totalYTranslation = 0;
	private final float transparenceRate = 0.001f;
	private float totalTransparence = 0;
	private final float scalingRate = 1.1f;
	private float totalScaling = 1f;

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
				totalRotation += rotation;
				shearing = timeMultiple * shearingRate * 1000;
				totalShearing += shearing;
				translationX = timeMultiple * xTranslationRate;
				totalXTranslation += translationX;
				translationY = timeMultiple * yTranslationRate;
				totalYTranslation += translationY;
				transparence = timeMultiple * transparenceRate * 1000;
				totalTransparence += transparence;
				scaling = timeMultiple * scalingRate * 1000;
				totalScaling *= scaling;
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
//		((Graphics2D) g).rotate(totalIteration * rotation * Math.PI / 180, centerXPlayer0, centerYPlayer0);
		((Graphics2D) g).rotate(totalRotation * Math.PI / 180, centerXPlayer0, centerYPlayer0);
		visitor.visitOnlyObject(g, p0);
		
		Player p1 = (Player) model.getObjects().get(1);
//		((Graphics2D) g).shear(totalIteration * shearing * 2, -totalIteration * shearing / 10);
		((Graphics2D) g).shear(totalShearing * 2, -totalIteration * shearing / 10);
		visitor.visitOnlyObject(g, p1);
		
		Player p2 = (Player) model.getObjects().get(2);
//		g.translate((int) (20*Math.cos(totalIteration * translationX * Math.PI / 180)), (int) (20*Math.sin(totalIteration * translationY * Math.PI / 180)));
		g.translate((int) (20*Math.cos(totalXTranslation * Math.PI / 180)), (int) (20*Math.sin(totalYTranslation * Math.PI / 180)));
		visitor.visitOnlyObject(g, p2);
		
		Player p3 = (Player) model.getObjects().get(3);
		p3.transparent((int) ((totalTransparence > 1)?1:totalTransparence));
		visitor.visitOnlyObject(g, p3);
		
		Obstacle obstacle = (Obstacle) model.getObjects().get(4);
		((Graphics2D) g).scale(totalScaling * 1.77, totalScaling);
		visitor.visitOnlyObject(g, obstacle);
		
		g.drawString("Goodbye, World", Game.WIDTH*7/16, Game.HEIGHT*7/16);
	}
}