package model;

import java.util.ArrayList;

import exceptions.NoPlayerException;
import view.View;

public abstract class Model {
	
	protected ArrayList<Visitable> objects;
	protected View view;
	protected long oldTimeMillis = 0;
	
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
	
	public abstract void update(int... args);
	
	public ArrayList<Visitable> getObjects() {
		return objects;
	}

	public Player findPlayer() throws NoPlayerException {
		for(Visitable object: objects) {
			if(object instanceof Player) {
				return (Player) object;
			}
		}
		throw new NoPlayerException();
	}

}
