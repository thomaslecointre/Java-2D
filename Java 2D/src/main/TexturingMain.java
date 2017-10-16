package main;


import java.awt.EventQueue;


import controller.Controller;
import controller.TexturingController;
import model.Model;
import model.TexturingModel;
import view.Game;
import view.TexturingView;
import view.View;

public class TexturingMain {
	
	public static void main(String[] args) {
		Model model = new TexturingModel();
		Controller controller = new TexturingController(model, null);
		View view = new TexturingView(model, controller);
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