package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import model.Model;
import view.View;

public class RotationController extends Controller implements MouseListener, KeyListener {

	public RotationController(Model model, View view) {
		super(model, view);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//TODO
	}
}