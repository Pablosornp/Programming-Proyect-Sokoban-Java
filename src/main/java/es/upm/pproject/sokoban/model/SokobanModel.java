package es.upm.pproject.sokoban.model;

import java.util.ArrayDeque;
import java.util.Deque;
import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanMovement;

public class SokobanModel implements GameModel {
	private int gameScore;
	private int currentLevelNumber;
	private Game current;
	private Deque<SokobanMovement> lastMovements;

	private LevelLoader ld;
	private SavesManager gls;


	public SokobanModel() {
		this.currentLevelNumber = 0;
		this.lastMovements = new ArrayDeque<>();
		this.ld = new LevelLoader(); 
		this.gls = new SavesManager();
	}

	public Game getCurrent() {
		return current;
	}

	@Override
	public void startNewGame(){
		this.gameScore = 0;
		this.currentLevelNumber = 0;
	}

	@Override
	public Game performMovement(SokobanAction action) {
		SokobanMovement movement = this.current.move(action);
		if(movement.isPlayerMoved()) {
			this.lastMovements.push(movement);
			this.gameScore++;
		}
		return this.current;
	}
	@Override
	public Game undoMovement() {
		if(!lastMovements.isEmpty()) {
			SokobanMovement movement = this.lastMovements.pop();
			this.current.undoMove(movement);
			this.gameScore--;
		}
		return this.current;
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
		this.current = game;
		if (game != null)
			this.current.setGameScore(this.gameScore);
		return game;
	}
	

	@Override
	public Game restartLevel() {
		Game restartedGame = null;
		int restartedGameScore = current.getGameScore() - current.getLevelScore();
		this.gameScore = restartedGameScore;
		restartedGame = ld.convertMap(this.currentLevelNumber);
		restartedGame.setGameScore(restartedGameScore);
		this.lastMovements = new ArrayDeque<>();
		this.current = restartedGame;
		return this.current;
	}

	@Override
	public boolean saveGame(String name) {
		return gls.saveGame(this.current, this.lastMovements, name);
	}
	
	@Override
	public Game loadGame() {
		// TODO Auto-generated method stub
		return null;
	}




}
