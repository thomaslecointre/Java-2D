package view;

import java.awt.Graphics;

import controller.Controller;
import model.Model;

public class TranslationView extends View {
	
	private final int xTranslationRate = -5;
	private int totalXTranslation = 0;
	
	public TranslationView(Model model, Controller controller) {
		super(model, controller);
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
	
}
