package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.Controller;
import model.Model;

public class TexturingView extends View {

	private static final long serialVersionUID = 1L;

	BufferedImage stone1, stone2, stone3;

	public TexturingView(Model model, Controller controller) {

		super(model, controller);

		try {
			stone1 = ImageIO.read(new File("stone1.jpg"));
			stone2 = ImageIO.read(new File("stone2.jpg"));
			stone3 = ImageIO.read(new File("stone3.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		TexturePaint texture1 = new TexturePaint(stone1, new Rectangle(0, 0, 90, 60));
		TexturePaint texture2 = new TexturePaint(stone2, new Rectangle(0, 0, 90, 60));
		TexturePaint texture3 = new TexturePaint(stone3, new Rectangle(0, 0, 90, 60));

		g2d.setPaint(texture1);
		g2d.fillRect(100, 15, 190, 160);

		g2d.setPaint(texture2);
		g2d.fillRect(300, 15, 190, 160);

		g2d.setPaint(texture3);
		g2d.fillRect(500, 15, 190, 160);

	}

}
