package es.upm.pproject.sokoban.model;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadManager {

	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.LoadManager");

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
					StringBuilder bld = new StringBuilder(line2[0]);
					for(int i = 1; i<line2.length;i++) {
						bld.append(" " + line2[i]);
					}
					levelName = bld.toString();
				}
				else if(lnr.getLineNumber() == 4){
					String[] mapDimension = line.split("\\s+");
					m = new Integer(mapDimension[0]);
					n = new Integer(mapDimension[1]);
					board = new Cell[m][n];
				}
				else if(lnr.getLineNumber() != 3){
					for(int j=0;j<line.length();j++){
						board[row][j] = new Cell(line.charAt(j));
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

	public Deque<Movement> loadLastMovements(String fileName) {
		ArrayDeque<Movement> lastMovements = new ArrayDeque<>();
		boolean isLastMovementsLoaded = false;
		try(LineNumberReader lnr = new LineNumberReader(new FileReader(fileName))){
			String line;
			lastMovements = new ArrayDeque<>();
			while (!isLastMovementsLoaded &&((line = lnr.readLine()) != null) ) {
				if(lnr.getLineNumber() == 3) {
					for(int i=0;i<line.length();i++) {
						lastMovements.push(new Movement(line.charAt(i)));
					}
					isLastMovementsLoaded = true;
				}
			}
			return lastMovements;
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error while loading game.", e);
			return lastMovements;
		}
	}

}
