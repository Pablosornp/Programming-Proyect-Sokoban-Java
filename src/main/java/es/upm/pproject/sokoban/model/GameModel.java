package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanMovements;

public interface GameModel {

	public Game performMovement(SokobanMovements movement);
	public Game undoMovement();
	public Game restartLevel();
	
}