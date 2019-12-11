package es.upm.pproject.sokoban.model;

public class SokobanModel implements GameModel {
	private Game current;

	public Game getCurrent() {
		return current;
	}
	public SokobanModel() {
		this.current = new Game(createBoard());		
	}
	@Override
	public Game performMovement(SokobanMovements movement) {
		this.current.move(movement);
		return this.current;
	}

	public Game restartLevel() {
		int restartedGameScore = current.getGameScore() - current.getLevelScore();	
		if(restartedGameScore < 0)
			restartedGameScore = 0;
		this.current = new Game(createBoard(),restartedGameScore);
		return this.current;
	}

	private Cell[][] createBoard() {
		Cell [][] board = new Cell [8][8];
		board [0][0] = new Cell("wall", "");
		board [0][1] = new Cell("wall", "");
		board [0][2] = new Cell("wall", "");
		board [0][3] = new Cell("wall", "");
		board [0][4] = new Cell("gap", "");
		board [0][5] = new Cell("gap", "");
		board [0][6] = new Cell("gap", "");
		board [0][7] = new Cell("gap", "");

		board [1][0] = new Cell("wall", "");
		board [1][1] = new Cell("gap", "");
		board [1][2] = new Cell("gap", "");
		board [1][3] = new Cell("wall", "");
		board [1][4] = new Cell("gap", "");
		board [1][5] = new Cell("gap", "");
		board [1][6] = new Cell("gap", "");
		board [1][7] = new Cell("gap", "");

		board [2][0] = new Cell("wall", "");
		board [2][1] = new Cell("gap", "");
		board [2][2] = new Cell("gap", "");
		board [2][3] = new Cell("wall", "");
		board [2][4] = new Cell("wall", "");
		board [2][5] = new Cell("wall", "");
		board [2][6] = new Cell("wall", "");
		board [2][7] = new Cell("wall", "");

		board [3][0] = new Cell("wall", "");
		board [3][1] = new Cell("gap", "");
		board [3][2] = new Cell("gap", "");
		board [3][3] = new Cell("gap", "");
		board [3][4] = new Cell("gap", "");
		board [3][5] = new Cell("gap", "");
		board [3][6] = new Cell("gap", "");
		board [3][7] = new Cell("wall", "");

		board [4][0] = new Cell("wall", "");
		board [4][1] = new Cell("wall", "");
		board [4][2] = new Cell("gap", "player");
		board [4][3] = new Cell("goal", "");
		board [4][4] = new Cell("wall", "");
		board [4][5] = new Cell("gap", "box");
		board [4][6] = new Cell("gap", "");
		board [4][7] = new Cell("wall", "");

		board [5][0] = new Cell("wall", "");
		board [5][1] = new Cell("gap", "");
		board [5][2] = new Cell("gap", "");
		board [5][3] = new Cell("gap", "");
		board [5][4] = new Cell("wall", "");
		board [5][5] = new Cell("gap", "");
		board [5][6] = new Cell("gap", "");
		board [5][7] = new Cell("wall", "");

		board [6][0] = new Cell("wall", "");
		board [6][1] = new Cell("gap", "");
		board [6][2] = new Cell("gap", "");
		board [6][3] = new Cell("gap", "");
		board [6][4] = new Cell("wall", "");
		board [6][5] = new Cell("wall", "");
		board [6][6] = new Cell("wall", "");
		board [6][7] = new Cell("wall", "");

		board [7][0] = new Cell("wall", "");
		board [7][1] = new Cell("wall", "");
		board [7][2] = new Cell("wall", "");
		board [7][3] = new Cell("wall", "");
		board [7][4] = new Cell("wall", "");
		board [7][5] = new Cell("gap", "");
		board [7][6] = new Cell("gap", "");
		board [7][7] = new Cell("gap", "");

		return board;
	}
}
