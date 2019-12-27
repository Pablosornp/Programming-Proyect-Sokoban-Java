package es.upm.pproject.sokoban.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.controller.SokobanElements;

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	

	public ImagePanel(SokobanElements element) {
		try {
			String userWindows = System.getenv("USERNAME");
			String path = "C:\\Users\\"+userWindows+"\\eclipse-workspace\\sokoban\\images\\";
			switch(element) {
			case WALL:
				path = path + "blackWall.png";
				break;
			case PLAYER:
				path = path + "player.png";
				break;
			case BOX:
				path = path + "box.png";
				break;
			case BOX_IN_GOAL:
				path = path + "boxInGoal.png";
				break;
			case GOAL:
				path = path + "goal.png";
				break;
			case GAP:
				path = path + "concreteGap.png";
				break;
			default:
				throw new IllegalArgumentException();
			}		
			image = ImageIO.read(new File(path));
		} catch (IOException ex) {
			System.out.println("Illegal Image Argument");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 32, 32, this);      
	}

}
