package es.upm.pproject.sokoban.model;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.controller.SokobanMovement;

public class LoadManager {

	private String savesFolderPath;
	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.LoadManager");


	public LoadManager() {
		Path path = Paths.get("./saves/");
		this.savesFolderPath = path.toAbsolutePath().toString();
	}

	public Game loadGame(String fileName) {

		try(LineNumberReader lnr = new LineNumberReader(new FileReader(fileName))){
			String levelName = ""; 
			String line;
			Cell[][] board = new Cell [0][0];
			Integer m;
			Integer n;
			Integer gameScore = 0;
			Integer levelScore = 0;
			Integer levelNumber = 0;
			int row = 0;
			while ((line = lnr.readLine()) != null) {
				if(lnr.getLineNumber() == 1) {
					String[] line1 = line.split("\\s+");
					gameScore = new Integer(line1[0]);
					levelScore = new Integer(line1[1]);
				}
				else if(lnr.getLineNumber() == 2){
					String[] line2 = line.split("\\s+");
					levelNumber = new Integer(line2[0]);
					for(int i = 1; i<line2.length;i++) {
						levelName = levelName + " " + line2[i];
					}
				}
				else if(lnr.getLineNumber() == 3){
					continue;
				}
				else if(lnr.getLineNumber() == 4){
					String[] mapDimension = line.split("\\s+");
					m = new Integer(mapDimension[0]);
					n = new Integer(mapDimension[1]);
					board = new Cell[m][n];
				}
				else {
					for(int j=0;j<line.length();j++){
						board[row][j] = charToElem(line.charAt(j));
					}
					row++;
				}
			}
			Warehouse warehouse = new Warehouse(board);
			Game game = new Game(levelNumber, levelName, warehouse);
			game.setGameScore(gameScore);
			game.setLevelScore(levelScore);
			return game;
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error while loading game.", e);
			return null;
		}
	}

	public Deque<SokobanMovement> loadLastMovements(String fileName) {

		try(LineNumberReader lnr = new LineNumberReader(new FileReader(fileName))){
			String line;
			ArrayDeque<SokobanMovement> lastMovements = new ArrayDeque<>();
			while ((line = lnr.readLine()) != null) {
				if(lnr.getLineNumber() == 3) {
					for(int i=0;i<line.length();i++) {
						lastMovements.push(charToMovement(line.charAt(i)));
					}
				}
			}
			return lastMovements;
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error while loading game.", e);
			return null;
		}
	}

	private Cell charToElem(char character) {
		Cell element;
		switch(character){
		case '#':
			element = new Cell(SokobanElements.GAP, SokobanElements.BOX);
			break;
		case '*':
			element = new Cell(SokobanElements.GOAL, SokobanElements.NONE);
			break;
		case '+':
			element = new Cell(SokobanElements.WALL, SokobanElements.NONE);
			break;
		case 'W':
			element = new Cell(SokobanElements.GAP, SokobanElements.PLAYER);
			break;
		case ' ':
			element = new Cell(SokobanElements.GAP, SokobanElements.NONE);
			break;
		case '@':
			element = new Cell(SokobanElements.GOAL, SokobanElements.PLAYER);
			break;
		case '$':
			element = new Cell(SokobanElements.GOAL, SokobanElements.BOX);
			break;

		default: 
			element = null;
		}
		return element;
	}

	private SokobanMovement charToMovement(Character character) {
		SokobanMovement movement = null;
		boolean boxMoved;
		if(Character.isUpperCase(character)) 
			boxMoved = true;
		else 
			boxMoved = false;
		character = Character.toLowerCase(character);
		switch(character){
		case 'l':
			movement = new SokobanMovement(SokobanAction.LEFT, true, boxMoved);
			break;
		case 'r':
			movement = new SokobanMovement(SokobanAction.RIGHT, true, boxMoved);
			break;
		case 'd':
			movement = new SokobanMovement(SokobanAction.DOWN, true, boxMoved);
			break;
		case 'u':
			movement = new SokobanMovement(SokobanAction.UP, true, boxMoved);
			break;
		default: 
			movement = null;
		}
		return movement;
	}
}
