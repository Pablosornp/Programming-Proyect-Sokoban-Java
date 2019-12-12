package es.upm.pproject.sokoban.controller;

import javax.swing.JOptionPane;

import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.view.GameView;

public class SokobanController {

	private GameView view; 
	private SokobanModel model;

	public SokobanController(GameView view, SokobanModel model) {
		this.view = view;
		this.model = model;
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public void onMoveUp() {
		Game currentGame = model.performMovement(SokobanMovements.UP);
		String levelScore = currentGame.getLevelScore().toString();
		String gameScore = currentGame.getGameScore().toString();
		view.setLevelScoreValue(levelScore);
		view.setGameScoreValue(gameScore);
		Cell[][] board = currentGame.getWarehouse();
		view.drawWarehousePanel(this.warehouseCellToWarehouseElements(board));
		view.enableKeyboard();
		if(haveIWon(currentGame))
			onRestart();
	}

	public void onMoveDown() {
		Game currentGame = model.performMovement(SokobanMovements.DOWN);
		String levelScore = currentGame.getLevelScore().toString();
		String gameScore = currentGame.getGameScore().toString();
		view.setGameScoreValue(gameScore);
		view.setLevelScoreValue(levelScore);
		Cell[][] board = currentGame.getWarehouse();
		view.drawWarehousePanel(this.warehouseCellToWarehouseElements(board));
		view.enableKeyboard();
		if(haveIWon(currentGame))
			onRestart();
	}

	public void onMoveRight() {
		Game currentGame = model.performMovement(SokobanMovements.RIGHT);
		String levelScore = currentGame.getLevelScore().toString();
		String gameScore = currentGame.getGameScore().toString();
		view.setLevelScoreValue(levelScore);
		view.setGameScoreValue(gameScore);
		Cell[][] board = currentGame.getWarehouse();
		view.drawWarehousePanel(this.warehouseCellToWarehouseElements(board));
		view.enableKeyboard();
		if(haveIWon(currentGame))
			onRestart();
	}

	public void onMoveLeft() {
		Game currentGame = model.performMovement(SokobanMovements.LEFT);
		String levelScore = currentGame.getLevelScore().toString();
		String gameScore = currentGame.getGameScore().toString();
		view.setLevelScoreValue(levelScore);
		view.setGameScoreValue(gameScore);
		Cell[][] board = currentGame.getWarehouse();
		view.drawWarehousePanel(this.warehouseCellToWarehouseElements(board));
		view.enableKeyboard();
		if(haveIWon(currentGame))
			onRestart();
	}

	public void onRestart() {
		Game restartedLevel = model.restartLevel();
		String levelScore = restartedLevel.getLevelScore().toString();
		String gameScore = restartedLevel.getGameScore().toString();
		view.setLevelScoreValue(levelScore);
		view.setGameScoreValue(gameScore);
		Cell[][] board = restartedLevel.getWarehouse();
		view.drawWarehousePanel(this.warehouseCellToWarehouseElements(board));
		view.enableKeyboard();
	}
	
	public void onExit() {
		System.exit(0);
	}

	public SokobanElements[][] warehouseCellToWarehouseElements(Cell[][] warehouse){
		Cell cell;
		int m = warehouse.length;
		int n = warehouse[0].length;
		SokobanElements [][] warehouseElements = new SokobanElements[m][n];
		for(int i=0;i<warehouse.length;i++) {
			for(int j=0;j<warehouse[0].length;j++) {
				cell = warehouse[i][j];
				if(cell.isWall())
					warehouseElements[i][j]=SokobanElements.WALL;					
				else if(cell.isGap() || cell.isGoal()) {
					if(cell.containsNothing()) 
						warehouseElements[i][j]=cell.getType();
					else 
						warehouseElements[i][j]=cell.getContent();
				}
			}
		}
		return warehouseElements;
	}

	public String warehouseToString(Cell[][] warehouse){
		StringBuilder sb = new StringBuilder("\n");
		Cell cell;

		for(int i=0;i<warehouse.length;i++) {
			for(int j=0;j<warehouse[0].length;j++) {
				cell = warehouse[i][j];
				if(cell.isGap()) {
					if(cell.containsNothing()) 
						sb.append(" ");
					if(cell.containsBox())
						sb.append("#");
					if(cell.containsPlayer())
						sb.append("W");						
				}
				if(cell.isGoal()) {
					if(cell.containsNothing()) 
						sb.append("*");
					if(cell.containsBox())
						sb.append("#");
					if(cell.containsPlayer())
						sb.append("W");						
				}
				if(cell.isWall())
					sb.append("+");	
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private boolean haveIWon(Game game) {
		if(game.getBoxesAtGoal()==game.getHowManyBoxes()) {
			JOptionPane.showMessageDialog(this.view,"YOU WON!\n\nGAME SCORE: "+game.getGameScore());
			return true;
		}
		else return false;
	}
}
