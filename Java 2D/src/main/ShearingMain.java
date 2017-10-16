package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import controller.Controller;
import controller.ShearingController;
import model.Model;
import model.ShearingModel;
import view.Game;
import view.ShearingView;
import view.View;

public class ShearingMain {

	public static void mainWithPlayer() {				
		Model model = new ShearingModel();
		Controller controller = new ShearingController(model, null);
		View view = new ShearingView(model, controller);
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

	public static void mainWithBanana() {
		//from : http://www.java2s.com/Code/Java/2D-Graphics-GUI/ShearingaDrawnImage.htm
		JFrame frameInit = new JFrame();
		frameInit.add(new MyComponentInit());
		frameInit.setSize(1000, 1000);
		frameInit.setVisible(true);

		JFrame frameSheared = new JFrame();
		frameSheared.add(new MyComponentSheared());
		frameSheared.setSize(1000, 1000);
		frameSheared.setVisible(true);
	}

	public static void main(String[] args) {
		// mainWithBanana();
		mainWithPlayer();
	}

}

class MyComponentInit extends JComponent {
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(new ImageIcon("a.png").getImage(), null, this);
	}
}

class MyComponentSheared extends JComponent {
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform tx = new AffineTransform();

		double shiftx = .1;
		double shifty = .3;
		tx.shear(shiftx, shifty);

		g2d.setTransform(tx);
		g2d.drawImage(new ImageIcon("a.png").getImage(), tx, this);
	}
}