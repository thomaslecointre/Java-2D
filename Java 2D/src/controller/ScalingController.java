package controller;

import java.awt.event.KeyEvent;

import exceptions.NoPlayerException;
import model.Model;
import view.View;

public class ScalingController extends Controller{

	private int size = 2;
	public ScalingController(Model model, View view) {
		super(model, view);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			try {
				model.findPlayer().scale(size);
				size += 2;
			} catch (NoPlayerException ex) {
				ex.printStackTrace();
			}
		}
	}
}
