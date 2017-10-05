package view;

import javax.swing.JFrame;

public class Game extends JFrame {

	private View view;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	public Game(View view) {
		this.view = view;
		setContentPane(view);
		setSize(WIDTH, HEIGHT);
		setTitle("Java 2D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}

	public void loop() {
		view.invalidate();
	}

}
