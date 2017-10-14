package main;

import java.awt.EventQueue;

import controller.Controller;
import controller.TranslationController;
import controller.TransparentController;
import controller.ScalingController;
import model.Model;
import model.TranslationModel;
import model.TransparentModel;
import model.ScalingModel;
import view.Game;
import view.TranslationView;
import view.TransparentView;
import view.ScalingView;
import view.View;

public abstract class ScalingMain {

	public static void main(String[] args) {
		/*
		Model model = new TranslationModel();
		Controller controller = new TranslationController(model, null);
		View view = new TranslationView(model, controller);
		controller.setView(view);
		model.setView(view);
		*/
		
		Model model = new ScalingModel();
		Controller controller = new ScalingController(model, null);
		View view = new ScalingView(model, controller);
		controller.setView(view);
		model.setView(view);
		
		/*
		Model model = new TransparentModel();
		Controller controller = new TransparentController(model, null);
		View view = new TransparentView(model, controller);
		controller.setView(view);
		model.setView(view);
		*/
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
