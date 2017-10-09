package main;

import java.awt.EventQueue;

import controller.Controller;
import model.Model;
import view.Game;
import view.View;

public abstract class Main {

	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model, null);
		View view = new View(model, controller);
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
