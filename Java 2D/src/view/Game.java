package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends JFrame {

	private View view;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public Game(View view) {
		this.view = view;
	}

	public void buildGUI() {
		view.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		this.add(view, BorderLayout.CENTER);

		this.setTitle("Java 2D");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void loop() {
		view.loop();
	}

}
