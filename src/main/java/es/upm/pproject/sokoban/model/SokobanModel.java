package es.upm.pproject.sokoban.model;

import java.util.ArrayDeque;
import java.util.Deque;
import es.upm.pproject.sokoban.controller.SokobanAction;

public class SokobanModel implements GameModel {
	private int gameScore;
	private int currentLevelNumber;
	private Game currentGame;
	private Deque<Movement> lastMovements;

	private LevelLoader ld;
	private SaveManager sm;
	private LoadManager lm;

	public SokobanModel() {
		this.currentLevelNumber = 0;
		this.lastMovements = new ArrayDeque<>();
		this.ld = new LevelLoader(); 
		this.sm = new SaveManager();
		this.lm = new LoadManager();
	}

	public Game getCurrent() {
		return currentGame;
	}

	@Override
	public void startNewGame(){
		this.gameScore = 0;
		this.currentLevelNumber = 0;
	}

	@Override
	public Game performMovement(SokobanAction action) {
		Movement movement = this.currentGame.move(action);
		if(movement.isPlayerMoved()) {
			this.lastMovements.push(movement);
			this.gameScore++;
		}
		return this.currentGame;
	}
	@Override
	public Game undoMovement() {
		if(!lastMovements.isEmpty()) {
			Movement movement = this.lastMovements.pop();
			this.currentGame.undoMove(movement);
			this.gameScore--;
		}
		return this.currentGame;
	}

	public int getGameScore() {
		return this.gameScore;
	}
	@Override
	public boolean hasNextLevel() {
		int nextLevelNumber = this.currentLevelNumber+1;
		return ld.levelExists(nextLevelNumber);
	}

	@Override
	public Game loadNextLevel(){
		Game game=null;
		this.currentLevelNumber++;
		if(ld.validMap(this.currentLevelNumber))
			game = ld.convertMap(this.currentLevelNumber);
		this.lastMovements = new ArrayDeque<>();		
		this.currentGame = game;
		if (game != null)
			this.currentGame.setGameScore(this.gameScore);
		return game;
	}


	@Override
	public Game restartLevel() {
		Game restartedGame = null;
		int restartedGameScore = currentGame.getGameScore() - currentGame.getLevelScore();
		this.gameScore = restartedGameScore;
		restartedGame = ld.convertMap(this.currentLevelNumber);
		restartedGame.setGameScore(restartedGameScore);
		this.lastMovements = new ArrayDeque<>();
		this.currentGame = restartedGame;
		return this.currentGame;
	}
	

	@Override
	public boolean saveGame(String name) {
		return sm.saveGame(this.currentGame, this.lastMovements, name);
	}

	@Override
	public Game loadGame(String path) {
		Game loadedGame = lm.loadGame(path);
		if(loadedGame!=null) {
			this.currentGame = loadedGame;
			this.gameScore = loadedGame.getGameScore();
			this.currentLevelNumber = loadedGame.getLevelNumber();
			this.lastMovements = lm.loadLastMovements(path);
		}
		return loadedGame;
	}
}
