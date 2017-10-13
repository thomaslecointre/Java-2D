package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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

		Player.Head head = player.getHead();
		g.drawOval(head.getLocation().x, head.getLocation().y, head.getRadius() * 2, head.getRadius() * 2);
		
		
		Player.Body.Limb leftArm = player.getBody().getLeftArm();
		Player.Body.Limb rightArm = player.getBody().getRightArm();
		Player.Body.Limb leftLeg = player.getBody().getLeftLeg();
		Player.Body.Limb rightLeg = player.getBody().getRightLeg();
		Player.Body.Limb chest = player.getBody().getChest();
		
		g.drawLine(leftArm.x1, leftArm.y1, leftArm.x2, leftArm.y2);
		g.drawLine(rightArm.x1, rightArm.y1, rightArm.x2, rightArm.y2);
		g.drawLine(leftLeg.x1, leftLeg.y1, leftLeg.x2, leftLeg.y2);
		g.drawLine(rightLeg.x1, rightLeg.y1, rightLeg.x2, rightLeg.y2);
		g.drawLine(chest.x1, chest.y1, chest.x2, chest.y2);
	
	}

	public void visitFloor(Floor floor) {
		g.setColor(floor.groundColor);
		g.fillRect(floor.floorLeftMostX, floor.floorY1, floor.floorRightMostX - floor.floorLeftMostX, Game.HEIGHT - floor.floorY1);
		g.setColor(Color.BLACK);
		g.drawLine(floor.floorLeftMostX, floor.floorY1, floor.floorRightMostX, floor.floorY2);
	}

	public void visitObstacle(Obstacle obstacle) {
		g.setColor(obstacle.color);
		g.fillRect(obstacle.location.x, obstacle.location.y, obstacle.width, obstacle.height);
		g.setColor(Color.BLACK);
		g.drawRect(obstacle.location.x, obstacle.location.y, obstacle.width, obstacle.height);
	}
}
