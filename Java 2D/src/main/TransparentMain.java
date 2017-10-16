package main;

import java.awt.EventQueue;

import controller.Controller;
import controller.TransparentController;
import model.Model;
import model.TransparentModel;
import view.Game;
import view.TransparentView;
import view.View;

public class TransparentMain {
	public static void main(String[] args) {

		Model model = new TransparentModel();
		Controller controller = new TransparentController(model, null);
		View view = new TransparentView(model, controller);
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
