package view;

import java.awt.Graphics;

import controller.Controller;
import model.Model;

public class TransparentView extends View {

	private float alphaRate = 0.001f;
	private float totalalpha = 0f;
	private int tmpalpha = 0;
	private boolean incremented = true;

	public TransparentView(Model model, Controller controller) {
		super(model, controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (incremented) {
			tmpalpha += alphaRate * 1000;

			model.update(tmpalpha);
			if (tmpalpha >= 1000) {
				tmpalpha = 1000;
				incremented = false;
			}
		} else {
			tmpalpha -= alphaRate * 1000;

			model.update(tmpalpha);
			if (tmpalpha <= 0) {
				tmpalpha = 0;
				incremented = true;
			}
		}

		visitor.visitObjects(g);
	}

}
