package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanAction;

public interface GameModel {

	public Game startNewGame();
	public Game performMovement(SokobanAction movement);
	public Game undoMovement();
	public Game restartLevel();
	public Game loadNextLevel(int levelNumber) throws Exception;
	public boolean saveGame();
	public Game loadGame();
	
}