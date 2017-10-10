package model;

import java.util.ArrayList;

import view.View;

public abstract class Model {
	
	protected ArrayList<Visitable> objects;
	protected View view;
	protected long oldTimeMillis = 0;
	protected long deltaTime = 20; // Equivalent to 50 fps
	
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
	
	public abstract void buildModel();
	
	public abstract void update();
	
	public ArrayList<Visitable> getObjects() {
		return objects;
	}

}
