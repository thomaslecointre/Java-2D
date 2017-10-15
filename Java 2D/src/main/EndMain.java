package main;

import java.awt.EventQueue;

import controller.Controller;
import controller.EndController;
import model.EndModel;
import model.Model;
import view.EndView;
import view.Game;
import view.View;

public class EndMain {

	public static void main(String[] args) {
		
		Model model = new EndModel();
		Controller controller = new EndController(model, null);
		View view = new EndView(model, controller);
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
