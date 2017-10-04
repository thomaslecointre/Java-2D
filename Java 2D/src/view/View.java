package view;

import javax.swing.JPanel;

import controller.Controller;
import model.Model;

public class View extends JPanel {

	private Model model;
	private Controller controller;

	public View(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}
	
	

}
