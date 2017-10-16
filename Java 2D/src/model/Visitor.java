package model;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import exceptions.NoPlayerException;
import view.Game;

public class Visitor {
	
	private Model model;
	private Graphics g;
	private Color color;
	
	public Visitor(Model model) {
		this.model = model;
	}
	
	public void visitObjects(Graphics g) {
		this.g = g;
		ArrayList<Visitable> objects = model.getObjects();
		for(Visitable object : objects) {
			object.acceptVisitor(this);
		}
	}

	public void visitPlayer(Player player) {
		AffineTransform tx = new AffineTransform();
		tx.rotate(player.getRotation(), player.getBounds().width/2 + player.getLocation().x - player.getHead().getRadius(), player.getBounds().height/2 + player.getLocation().y - player.getHead().getRadius());
		
			
			tx.scale(player.getSize(), player.getSize());
		if(player.getSize()!=1)
			tx.translate(- (player.getLocation().getX()-player.getLocation().getX()/player.getSize()), -(player.getLocation().getY()-player.getLocation().getY()/player.getSize()));
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setTransform(tx);
		Player.Head head = player.getHead();
		g.drawOval(head.getLocation().x, head.getLocation().y, head.getRadius() * 2, head.getRadius() * 2);
		
		
		Player.Body.Limb leftArm = player.getBody().getLeftArm();
		Player.Body.Limb rightArm = player.getBody().getRightArm();
		Player.Body.Limb leftLeg = player.getBody().getLeftLeg();
		Player.Body.Limb rightLeg = player.getBody().getRightLeg();
		Player.Body.Limb chest = player.getBody().getChest();
		
		g.drawLine(leftArm.centerX, leftArm.centerY, leftArm.outerX, leftArm.outerY);
		g.drawLine(rightArm.centerX, rightArm.centerY, rightArm.outerX, rightArm.outerY);
		g.drawLine(leftLeg.centerX, leftLeg.centerY, leftLeg.outerX, leftLeg.outerY);
		g.drawLine(rightLeg.centerX, rightLeg.centerY, rightLeg.outerX, rightLeg.outerY);
		g.drawLine(chest.centerX, chest.centerY, chest.outerX, chest.outerY);
		
		g2d.setTransform(new AffineTransform());
	}

	public void visitFloor(Floor floor) {
		g.setColor(floor.groundColor);
		g.fillRect(floor.floorLeftMostX, floor.floorY1, floor.floorRightMostX - floor.floorLeftMostX, Game.HEIGHT - floor.floorY1);
		g.setColor(Color.BLACK);
		g.drawLine(floor.floorLeftMostX, floor.floorY1, floor.floorRightMostX, floor.floorY2);
	}

	private AlphaComposite makeTransparentComposite(double alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, (float)alpha));
	}
	

	public void visitObstacle(Obstacle obstacle) {
		AffineTransform tx = new AffineTransform();
		tx.shear(obstacle.getShearing(), obstacle.getShearing());
		Graphics2D g2d= (Graphics2D)g;
		g2d.setTransform(tx);
		//g.setColor(new Color(1,1,0,obstacle.alpha));.
		g2d.setComposite(makeTransparentComposite(obstacle.alpha));
		g2d.setColor(obstacle.color);
		g2d.fillRect(obstacle.location.x, obstacle.location.y, obstacle.width, obstacle.height);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(obstacle.location.x, obstacle.location.y, obstacle.width, obstacle.height);
		g2d.setTransform(new AffineTransform());
	}

	
}