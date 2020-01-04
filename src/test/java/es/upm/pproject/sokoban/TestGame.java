package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.model.Cell;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.Warehouse;

@DisplayName("Test message")
public class TestGame {	
	
	private Cell [][] defaultBoard;

	@BeforeEach
	public  void createDefaultBoard() {
		defaultBoard = new Cell [8][8];
		defaultBoard [0][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [0][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [0][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [0][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [0][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [0][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [0][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [0][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		defaultBoard [1][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [1][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [1][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [1][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [1][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [1][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [1][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [1][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		defaultBoard [2][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [2][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [2][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [2][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [2][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [2][5] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [2][6] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [2][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		defaultBoard [3][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [3][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [3][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [3][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [3][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [3][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [3][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [3][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		defaultBoard [4][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [4][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [4][2] = new Cell(SokobanElements.GAP, SokobanElements.PLAYER);
		defaultBoard [4][3] = new Cell(SokobanElements.GOAL, SokobanElements.NONE);
		defaultBoard [4][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [4][5] = new Cell(SokobanElements.GAP, SokobanElements.BOX);
		defaultBoard [4][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [4][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		defaultBoard [5][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [5][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [5][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [5][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [5][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [5][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [5][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [5][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		defaultBoard [6][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [6][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [6][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [6][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [6][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [6][5] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [6][6] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [6][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		defaultBoard [7][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [7][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [7][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [7][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [7][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		defaultBoard [7][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [7][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		defaultBoard [7][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
	}

	@Test
	@DisplayName("Test for testing movements")
	void wholeLevelTest() {

		Game game = new Game(1, "Test", new Warehouse(defaultBoard));

		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.UP);
		assertTrue(game.getWarehouse().getBoxesAtGoal()==1);
	}  	
}