package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanAction;

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

	public int getLevelNumber() {
		return levelNumber;
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

	public void setLevelScore(Integer levelScore) {
		this.levelScore = levelScore;
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

	public Movement move(SokobanAction action) {
		Movement move = this.warehouse.move(action);
		if(move.isPlayerMoved())
			incrementScore();
		return move;
	}

	public void undoMove(Movement movement) {
		this.warehouse.undoMove(movement);
		if(movement.isPlayerMoved())
			decrementScore();
	}

	public boolean isLevelCompleted() {
		return warehouse.getBoxesAtGoal()==warehouse.getHowManyBoxes();
	}
}