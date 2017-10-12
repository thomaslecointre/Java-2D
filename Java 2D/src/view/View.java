package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import controller.Controller;
import model.Model;
import model.Visitor;

public class View extends Canvas {

	private Model model;
	private Controller controller;
	private Visitor visitor;
	private boolean animationIsActive = true;
	private long oldTimeMillis = 0;
	
	private final int xTranslationRate = -2;
	private int totalXTranslation = 0;
	
	private final int deltaTime = 50;
	
	public void toggleAnimationActive() {
		animationIsActive = !animationIsActive;
	}

	public View(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		visitor = new Visitor(model);
		this.addMouseListener(controller);
		this.addKeyListener(controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		long currentTimeMillis = System.currentTimeMillis();
		if(oldTimeMillis != 0) {
			int translationMultiple = (int) ((currentTimeMillis - oldTimeMillis) / deltaTime);
			if(translationMultiple > 0) {
				int xTranslation = translationMultiple * xTranslationRate;
				totalXTranslation += xTranslation;
				model.update(-xTranslation, 0);
				oldTimeMillis = currentTimeMillis;
			}
		} else {
			oldTimeMillis = currentTimeMillis;
		}
		g.translate(totalXTranslation, 0);
		visitor.visitObjects(g);
	}
	
	public void loop() {

		
		this.createBufferStrategy(2);
		BufferStrategy strategy = this.getBufferStrategy();
		
		// Main loop
		while (animationIsActive) {
			// Prepare for rendering the next frame
			// ...

			// Render single frame
			do {
				// The following loop ensures that the contents of the drawing
				// buffer
				// are consistent in case the underlying surface was recreated
				do {
					// Get a new graphics context every time through the loop
					// to make sure the strategy is validated
					Graphics graphics = strategy.getDrawGraphics();
					// Render to graphics
					// ...
					
					this.paint(graphics);
					// Dispose the graphics
					graphics.dispose();

					// Repeat the rendering if the drawing buffer contents
					// were restored
				} while (strategy.contentsRestored());

				// Display the buffer
				strategy.show();
				
				// Repeat the rendering if the drawing buffer was lost
			} while (strategy.contentsLost());
		}
		
		
	}
	
}
