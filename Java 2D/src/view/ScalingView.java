package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import model.Model;


public class ScalingView extends View{

	private float alphaRate =0.001f;
	private float totalalpha =0f;
	private int tmpalpha=0;
	private boolean incremented=true;
	
	public ScalingView(Model model, Controller controller) {
		super(model, controller);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
				if(incremented) {
				tmpalpha += alphaRate*1000;
				
				model.update(tmpalpha);
				System.out.println(tmpalpha);
				if(tmpalpha>=1000) {
					tmpalpha=1000;
					incremented=false;
				}
				}else {
					tmpalpha -= alphaRate*1000;
					
					model.update(tmpalpha);
					if(tmpalpha<=0) {
						tmpalpha=0;
						incremented=true;
					}
				}
			
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		visitor.visitObjects(g);
	}
	

}
