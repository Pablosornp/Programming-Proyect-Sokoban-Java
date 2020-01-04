package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.model.SokobanModel;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.Cell;
import es.upm.pproject.sokoban.view.GameView;
import es.upm.pproject.sokoban.view.MessageManager;

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

	public void onStart() {
		model.startNewGame();
		view.setPanelsVisible(true);
		view.setKeyboardEnabled(true);
		if(!loadLevel()) 
			gameCompleted(model.getCurrent().getGameScore());	
		view.focusOnKeyboard();
	}

	public void onRestart() {
		Game restartedLevel = model.restartLevel();
		updateLevelInfo(restartedLevel);
		view.focusOnKeyboard();
	}

	public void onMove(SokobanAction action) {
		Game currentGame = model.performMovement(action);	
		updateLevelInfo(currentGame);
		if(levelCompleted(currentGame) && !loadLevel()) {
			gameCompleted(currentGame.getGameScore());
		}
		view.focusOnKeyboard();
	}

	public void onUndoMove() {
		Game currentGame = model.undoMovement();
		updateLevelInfo(currentGame);
		view.focusOnKeyboard();
	}

	public void onSave() {
		MessageManager mm = view.getMm();
		String saveName = mm.showSaveNameInputMessage();
		if(saveName!=null) {
			boolean succesfulSave = model.saveGame(saveName);
			if(succesfulSave) {
				mm.showSuccessfulSaveMessage();
			}
			else {
				mm.showFailedSaveMessage();
			}
		}
		view.focusOnKeyboard();
	}

	public void onLoad() {		
		String path = view.getMm().showSaveSelectionWindow();
		Game loadedGame = model.loadGame(path);
		updateLevelInfo(loadedGame);
		view.focusOnKeyboard();
	}

	public void onExit() {
		int input = view.getMm().showExitMessage();
		// 0=yes, 1=no, 2=cancel
		if(input==0)
			System.exit(0);
	}

	private boolean loadLevel() {
		boolean levelLoaded;
		boolean hasNext;
		Game game=null;
		while((hasNext = model.hasNextLevel()) && (game = model.loadNextLevel()) == null) {
			view.getMm().showInvalidLevelMessage();
		}
		//The game is completed  -> Show victory message and load Welcome screen
		if(!hasNext){
			levelLoaded=false;
		}
		//The level is valid -> The level is loaded
		else { 
			updateLevelInfo(game);
			levelLoaded=true;
		}
		return levelLoaded;
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
		Cell[][] board = game.getWarehouse().getBoard();
		String levelName = game.getLevelName();
		int levelNumber = game.getLevelNumber();
		view.setLevelName("Level "+levelNumber+": "+levelName);
		view.drawWarehousePanel(this.warehouseCellToWarehouseElements(board),
				game.getWarehouse().getLastAction(), game.getLevelNumber(), game.getWarehouse().getStep());
	}

	private boolean levelCompleted(Game game) {
		if(game.isLevelCompleted()) {
			view.getMm().showLevelCompletedMessage(game.getLevelNumber(), game.getGameScore());
			return true;
		}
		else return false;
	}

	private void gameCompleted(int gameScore) {
		view.getMm().showYouWonMessage(gameScore);
		view.drawWelcomeScreen();
	}


}