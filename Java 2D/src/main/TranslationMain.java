package main;

import java.awt.EventQueue;

import controller.Controller;
import controller.TranslationController;
import controller.TransparentController;
import model.Model;
import model.TranslationModel;
import model.TransparentModel;
import view.Game;
import view.TranslationView;
import view.TransparentView;
import view.View;

public abstract class TranslationMain {

	public static void main(String[] args) {

		Model model = new TranslationModel();
		Controller controller = new TranslationController(model, null);
		View view = new TranslationView(model, controller);
		controller.setView(view);
		model.setView(view);

		
		Game game = new Game(view);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				game.buildGUI();
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						game.loop();
					}
				});
				thread.start();
			}
		});
	}
}
