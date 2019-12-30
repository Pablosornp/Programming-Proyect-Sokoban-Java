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
	
	public void showWelcomeScreen(){
		// TODO Auto-generated method stub
		onRestart();
	}

	public void onStart() {
		// TODO 
		onRestart();
	}

	public void onRestart() {
		Game restartedLevel = model.restartLevel();
		updateLevelInfo(restartedLevel);
		view.enableKeyboard();
	}

	public void onMove(SokobanAction movement) {
		Game currentGame = model.performMovement(movement);
		updateLevelInfo(currentGame);
		if(haveIWon(currentGame))
			onRestart();
		view.enableKeyboard();
	}

	public void onUndoMove() {
		Game currentGame = model.undoMovement();
		updateLevelInfo(currentGame);
		if(haveIWon(currentGame))
			onRestart();
		view.enableKeyboard();

	}

	public void onSave() {
		// TODO 
		view.enableKeyboard();
	}

	public void onLoad() {		
		// TODO 
		view.enableKeyboard();
	}

	public void onExit() {
		int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?\n"
				+ "You may lose your progress.", "Sokoban", JOptionPane.YES_NO_CANCEL_OPTION);
		// 0=yes, 1=no, 2=cancel
		if(input==0)
			System.exit(0);
	}

	private SokobanElements[][] warehouseCellToWarehouseElements(Cell[][] warehouse){
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
					else if(cell.isGoal() && cell.containsBox()) 
						warehouseElements[i][j]=SokobanElements.BOX_IN_GOAL;
					else 
						warehouseElements[i][j]=cell.getContent();
				}
			}
		}
		return warehouseElements;
	}

	private void updateLevelInfo(Game game) {
		String levelScore = game.getLevelScore().toString();
		String gameScore = game.getGameScore().toString();
		view.setLevelScoreValue(levelScore);
		view.setGameScoreValue(gameScore);
		Cell[][] board = game.getWarehouse();
		String levelName = game.getLevelName();
		view.setLevelName(levelName);
		view.drawWarehousePanel(this.warehouseCellToWarehouseElements(board));
	}

	private String warehouseToString(Cell[][] warehouse){
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
