package es.upm.pproject.sokoban.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanElements;

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.view.ImagePanel");

	

	public ImagePanel(String folderPath, SokobanElements element, SokobanAction action, int step) {
		try {
			String imagePath;
			switch(element) {
			case WALL:
				imagePath = folderPath + "\\wall.gif";
				break;
			case PLAYER:		
				imagePath = folderPath + actionToImagenName(action) + (step) + ".gif";
				break;
			case BOX:
				imagePath = folderPath + "\\box.gif";
				break;
			case BOX_IN_GOAL:
				imagePath = folderPath + "\\boxInGoal.gif";
				break;
			case GOAL:
				imagePath = folderPath + "\\goal.gif";
				break;
			case GAP:
				imagePath = folderPath + "\\gap.gif";
				break;
			default:
				throw new IllegalArgumentException();
			}		
			image = ImageIO.read(new File(imagePath));
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE,"Image for panel not found.");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 32, 32, this);      
	}
	
	private String actionToImagenName(SokobanAction action) {
		String imageName=null;
		switch(action) {	
		case UP:
			imageName = "\\player_U_";
			break;
		case DOWN:		
			imageName = "\\player_D_";
			break;
		case LEFT:
			imageName = "\\player_L_";
			break;
		case RIGHT:
			imageName = "\\player_R_";
			break;
		default:
			imageName=null;
		}	
		return imageName;
	}

}
