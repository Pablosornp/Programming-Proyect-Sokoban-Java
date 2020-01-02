package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanAction;

public interface GameModel {

	public void startNewGame();
	public Game performMovement(SokobanAction movement);
	public Game undoMovement();
	public Game restartLevel();
	public boolean hasNextLevel();
	public Game loadNextLevel();
	public boolean saveGame(String name);
	public Game loadGame();
	
}