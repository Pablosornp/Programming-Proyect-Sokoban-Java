package es.upm.pproject.sokoban.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import es.upm.pproject.sokoban.controller.SokobanElements;

public class LevelLoader {

	private String levelsFolderPath;

	public LevelLoader() {
		Path path = Paths.get("./levels/");
		this.levelsFolderPath = path.toAbsolutePath().toString()+"\\";
	}

	public Game convertMap (int levelNumber) throws FileNotFoundException, IOException {
		String levelPath = levelsFolderPath + "level_" + levelNumber + ".txt"; 
		LineNumberReader lnr = new LineNumberReader(new FileReader(levelPath));
		String levelName = null; 
		String line;
		Cell[][] warehouse = new Cell [0][0];
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
				warehouse = new Cell[m][n];
			}
			else {
				for(int j=0;j<line.length();j++){
					warehouse[row][j] = charToElem(line.charAt(j));
					// SPACEEEES AND SCORE
				}
				row++;
			}
		}
		Game game = new Game(levelName, warehouse);
		return game;
	}

	private Cell charToElem(char character) {
		Cell element = null;
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
		}
		return element;
	}
	
	public static void main(String[] args) {
		LevelLoader ld = new LevelLoader();
		try {
			Game game = ld.convertMap(2);
			game.showWarehouse();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
