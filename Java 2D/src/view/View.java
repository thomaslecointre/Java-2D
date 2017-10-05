package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import controller.Controller;
import model.Model;
import model.Visitor;

public class View extends JPanel {

	private Model model;
	private Controller controller;
	private Visitor visitor;

	public View(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		visitor = new Visitor(model);
	}

	@Override
	protected void paintComponent(Graphics g) {
		visitor.visitObjects(g);
	}
	
}
