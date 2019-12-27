package es.upm.pproject.sokoban.model;

import java.util.Stack;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanMovement;

public class SokobanModel implements GameModel {
	private Game current;
	private Stack<SokobanMovement> lastMovements;

	public Game getCurrent() {
		return current;
	}
	public SokobanModel() {
		this.current = new Game("Initial Level", createBoard());
		this.lastMovements = new Stack<>();
	}

	@Override
	public Game startNewGame(){
		// TODO Auto-generated method stub
		return restartLevel();
	}

	@Override
	public Game performMovement(SokobanAction action) {
		SokobanMovement movement = this.current.move(action);
		if(movement.isPlayerMoved())
			this.lastMovements.push(movement);
		return this.current;
	}
	@Override
	public Game undoMovement() {
		if(!lastMovements.empty()) {
			SokobanMovement movement = this.lastMovements.pop();
			this.current.undoMove(movement);
		}
		return this.current;
	}
	@Override
	public Game restartLevel() {
		int restartedGameScore = current.getGameScore() - current.getLevelScore();	
		this.current.restartLevel(createBoard(), restartedGameScore);
		this.lastMovements = new Stack<>();
		return this.current;
	}

	@Override
	public boolean saveGame() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Game loadGame(String pathFile) {
		// TODO Auto-generated method stub
		return null;
	}


	private Cell[][] createBoard() {
		Cell [][] board = new Cell [8][8];
		board [0][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [0][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [0][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [0][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [0][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [0][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [0][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [0][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		board [1][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [1][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [1][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		board [2][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [2][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [2][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][5] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][6] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [3][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [3][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [4][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [4][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [4][2] = new Cell(SokobanElements.GAP, SokobanElements.PLAYER);
		board [4][3] = new Cell(SokobanElements.GOAL, SokobanElements.NONE);
		board [4][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [4][5] = new Cell(SokobanElements.GAP, SokobanElements.BOX);
		board [4][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [4][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [5][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [5][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][3] = new Cell(SokobanElements.GAP, SokobanElements.BOX);
		board [5][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [5][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [6][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [6][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [6][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [6][3] = new Cell(SokobanElements.GOAL, SokobanElements.NONE);
		board [6][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [6][5] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [6][6] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [6][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [7][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [7][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [7][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		return board;
	}

}
