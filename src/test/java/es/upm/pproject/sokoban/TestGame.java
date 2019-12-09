package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.Cell;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.SokobanMovements;

@DisplayName("Test message")
public class TestGame {	
	
	@Test
	@DisplayName("Test for testing tests")
	void assertThrowsInvalidaName() {
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

		Game game = new Game(board);
		game.setHowManyBoxes(1);

		game.showWarehouse();
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.UP);
		game.showWarehouse();
		assertTrue(game.getBoxesAtGoal()==1);
	}  	
}