package es.upm.pproject.sokoban.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanMovement;

public class SavesManager {

	private String savesFolderPath;

	public SavesManager() {
		Path path = Paths.get("./saves/");
		this.savesFolderPath = path.toAbsolutePath().toString();
	}

	public boolean saveGame(Game game, Stack<SokobanMovement> movements, String saveName) {
		try{
			File directory = new File(savesFolderPath);
			if (!directory.exists()){
				directory.mkdir();
			}

			Writer bw = null;
			File file = new File(savesFolderPath+"\\"+saveName+".txt");
			bw = new BufferedWriter(new FileWriter(file));
			String newLine = System.getProperty("line.separator");

			//Line 1: GameScore LevelScore
			bw.write(game.getGameScore()+ " " + game.getLevelScore() + newLine);
			//Line 2: LevelNumber LevelName
			bw.write(game.getLevelNumber()+ " " +  game.getLevelName() + newLine);

			//Line 3: movements
			SokobanMovement move;
			StringBuilder sb = new StringBuilder("");
			Object[] movementsArray = movements.toArray();
			for(int i=0; i<movementsArray.length; i++) {
				move=(SokobanMovement) movementsArray[i];
				sb.append(movementToChar(move));
			}
			bw.write(sb.toString() + newLine);
			sb.setLength(0);//Empty the StringBuilder

			//Line 4: M N
			Cell[][] warehouse = game.getWarehouse().getBoard();
			int m = warehouse.length;
			int n = warehouse[0].length;
			bw.write(m + " " + n + newLine);

			//Line 5 to 5+M: ASCII MAP
			for(int i=0; i<m; i++) {
				//Empty the StringBuilder
				sb.setLength(0);
				for(int j=0; j<n; j++) {
					sb.append(elemToChar(warehouse[i][j]));
				}
				bw.write(sb.toString() + newLine);
			}
			bw.close();
			System.out.println("Game saved");

		}catch(Exception e){
			return false;
		}
		return true;
	}

	public Game loadGame() {
		//TODO
		return null;
	}


	private Character movementToChar(SokobanMovement move) {
		Character c;
		SokobanAction action = move.getAction();
		boolean boxMoved = move.isBoxMoved();
		switch(action) {
		case UP:
			c='u';
			break;
		case DOWN:
			c='d';
			break;
		case LEFT:
			c='l';
			break;
		case RIGHT:
			c='r';
			break;
		default: c=null;
		}
		if(boxMoved)
			c = Character.toUpperCase(c);
		return c;
	}

	private Character elemToChar(Cell elem) {
		Character c= null;
		if(elem.isGoal()){
			if(elem.containsNothing())
				c='*';
			else if(elem.containsPlayer())
				c='@';
			else if(elem.containsBox())
				c='$';	
		}else if(elem.isGap()){
			if(elem.containsNothing())
				c=' ';
			else if(elem.containsPlayer())
				c='W';
			else if(elem.containsBox())
				c='#';	
		}else {
			c='+';	
		}
		return c;
	}


}
