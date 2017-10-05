package model;

import java.awt.Point;
import java.util.ArrayList;

import view.View;

public class Model {
	
	private ArrayList<Visitable> objects;
	private View view;
	
	public Model() {
		this(null);
	}
	
	public Model(View view) {
		objects = new ArrayList<Visitable>();
		this.view = view;
		buildModel();
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public void buildModel() {
		objects.add(new Player(20, 50, new Point(100, 100)));
	}
	
	public ArrayList<Visitable> getObjects() {
		return objects;
	}

}
