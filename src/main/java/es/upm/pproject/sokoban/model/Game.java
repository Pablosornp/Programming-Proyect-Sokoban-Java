package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanMovement;

public class Game {
	private int levelNumber;
	private String levelName;
	private Integer gameScore;
	private Integer levelScore;
	private Warehouse warehouse;

	public Game(int levelNumber, String levelName, Warehouse warehouse) {
		this.levelNumber = levelNumber;
		this.levelName = levelName;
		this.levelScore = 0;
		this.gameScore = 0;
		this.warehouse = warehouse;	
	}

	public void restartLevel(Cell[][] board, int gameScore) {
		this.levelScore = 0;
		this.gameScore = gameScore;
		this.warehouse.restartWarehouse(board);
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getLevelName() {
		return levelName;
	}

	public Integer getGameScore() {
		return gameScore;
	}

	public void setGameScore(Integer gameScore) {
		this.gameScore = gameScore;
	}

	public Integer getLevelScore() {
		return levelScore;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}


	public void incrementScore() {
		this.levelScore++;
		this.gameScore++;
	}

	public void decrementScore() {
		this.levelScore--;
		this.gameScore--;
	}

	public SokobanMovement move(SokobanAction action) {
		SokobanMovement move = this.warehouse.move(action);
		if(move.isPlayerMoved())
			incrementScore();
		return move;
	}

	public void undoMove(SokobanMovement movement) {
		this.warehouse.undoMove(movement);
		decrementScore();
	}
	
	public boolean isLevelCompleted() {
		return warehouse.getBoxesAtGoal()==warehouse.getHowManyBoxes();
	}
}