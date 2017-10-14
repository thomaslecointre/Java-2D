package main;

import java.awt.EventQueue;

import controller.*;
import model.*;
import view.*;

public class RotationMain {

	public static void main(String[] args) {				
		Model model = new RotationModel();
		Controller controller = new RotationController(model, null);
		View view = new RotationView(model, controller);
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