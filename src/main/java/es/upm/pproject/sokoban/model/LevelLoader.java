package es.upm.pproject.sokoban.model;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.upm.pproject.sokoban.controller.SokobanElements;

public class LevelLoader {

	private String levelsPath;

	private int boxCounter;
	private int goalCounter;
	private int playerCounter;
	
	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.LevelLoader");


	public LevelLoader() {
		initializeCounters();
		Path path = Paths.get("./levels/");
		this.levelsPath = path.toAbsolutePath().toString()+"\\level_";
	}

	public void initializeCounters() {
		this.boxCounter=0;
		this.goalCounter=0;
		this.playerCounter=0;
	}


	public int getBoxCounter() {
		return boxCounter;
	}

	public void incrementBoxCounter() {
		this.boxCounter++;
	}

	public int getGoalCounter() {
		return goalCounter;
	}

	public void incrementGoalCounter() {
		this.goalCounter++;
	}

	public int getPlayerCounter() {
		return playerCounter;
	}

	public void incrementPlayerCounter() {
		this.playerCounter++;
	}

	public Game convertMap (int levelNumber)  {
		String levelPath = levelsPath + levelNumber + ".txt"; 
		try(LineNumberReader lnr = new LineNumberReader(new FileReader(levelPath))){
			String levelName = null; 
			String line;
			Cell[][] board = new Cell [0][0];
			Integer m;
			Integer n;
			int row = 0;
			while ((line = lnr.readLine()) != null) {
				if(lnr.getLineNumber() == 1) {
					levelName = line;
				}
				else if(lnr.getLineNumber() == 2){
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
			return new Game(levelNumber, levelName, warehouse);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error while converting map.", e);
			return null;
		}
	}

	public boolean levelExists(int levelNumber) {
		String levelPath = levelsPath + levelNumber + ".txt"; 
		File file = new File(levelPath);
		boolean exists = file.exists();
		boolean isFile = file.isFile();
		return exists && isFile;  
	}

	public boolean validMap(int levelNumber) {
		initializeCounters();
		String levelPath = levelsPath + levelNumber + ".txt"; 
		try	(LineNumberReader lnr = new LineNumberReader(new FileReader(levelPath))){
			String line;
			while ((line = lnr.readLine()) != null) {
				if(lnr.getLineNumber()>2){
					for(int j=0;j<line.length();j++){
						countElem(line.charAt(j));
					}
				}
			}
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error while validating map.", e);
			return false;
		}
		return this.boxCounter>0 && this.boxCounter==this.goalCounter && this.playerCounter==1;
	}

	private void countElem(char character) {
		switch(character){
		case '#':
			this.incrementBoxCounter();
			break;
		case '*':
			this.incrementGoalCounter();
			break;
		case 'W':
			this.incrementPlayerCounter();
			break;
		default: 
			break;
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
		default: 
			element = null;
		}
		return element;
	}
}

