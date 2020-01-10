package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.model.Cell;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.Warehouse;
import es.upm.pproject.sokoban.model.Movement;

@DisplayName("Tests for model methods")
public class TestModel {

	private Cell [][] basicBoard;
	private Game basicGame;

	@BeforeEach
	@DisplayName("Create Basic Board and Game")
	public  void createBasicBoardAndGame() {
		this.basicBoard = new Cell [5][8];
		basicBoard [0][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [0][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [0][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [0][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [0][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [0][5] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [0][6] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [0][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		basicBoard [1][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [1][1] = new Cell(SokobanElements.GAP, SokobanElements.PLAYER);
		basicBoard [1][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [1][3] = new Cell(SokobanElements.GAP, SokobanElements.BOX);
		basicBoard [1][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [1][5] = new Cell(SokobanElements.GOAL, SokobanElements.NONE);
		basicBoard [1][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [1][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		basicBoard [2][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [2][1] = new Cell(SokobanElements.GAP, SokobanElements.BOX);
		basicBoard [2][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [2][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [2][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [2][5] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [2][6] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [2][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		basicBoard [3][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [3][1] = new Cell(SokobanElements.GOAL, SokobanElements.NONE);
		basicBoard [3][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [3][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [3][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [3][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [3][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [3][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		basicBoard [4][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [4][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [4][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		basicBoard [4][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [4][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [4][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [4][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		basicBoard [4][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		
		this.basicGame = new Game(88, "Basic Level", new Warehouse(basicBoard));
	}

	@Test
	@DisplayName("Test for Basic Level completion")
	void wholeBasicLevelTest() {
		basicGame.move(SokobanAction.DOWN);
		basicGame.move(SokobanAction.UP);
		basicGame.move(SokobanAction.RIGHT);
		basicGame.move(SokobanAction.RIGHT);
		basicGame.move(SokobanAction.RIGHT);
		assertTrue(basicGame.isLevelCompleted());
	}
	
	@Test
	@DisplayName("Test for getting the board")
	void getBoardTest() {
		assertEquals(basicBoard,basicGame.getWarehouse().getBoard());
	}
	
	@Test
	@DisplayName("Test for getting the last Action")
	void getLastActionTest() {
		basicGame.move(SokobanAction.RIGHT);
		basicGame.move(SokobanAction.RIGHT);
		basicGame.move(SokobanAction.UP);
		basicGame.move(SokobanAction.LEFT);
		basicGame.move(SokobanAction.LEFT);
		basicGame.move(SokobanAction.DOWN);
		assertEquals(SokobanAction.DOWN,basicGame.getWarehouse().getLastAction());
	}
	
	@Test
	@DisplayName("Test for getting the player's step")
	void getStepTest() {
		basicGame.move(SokobanAction.UP);
		basicGame.move(SokobanAction.UP);
		basicGame.move(SokobanAction.UP);
		assertTrue(basicGame.getWarehouse().getStep()==1);
	}
	
	@Test
	@DisplayName("Test for moving box out of goal")
	void movingBoxOutOfGoalTest() {
		basicGame.move(SokobanAction.RIGHT);
		basicGame.move(SokobanAction.RIGHT);
		basicGame.move(SokobanAction.RIGHT);
		assertTrue(basicGame.getWarehouse().getBoxesAtGoal()==1);
		basicGame.move(SokobanAction.RIGHT);
		assertTrue(basicGame.getWarehouse().getBoxesAtGoal()==0);
	}
	
	@Test
	@DisplayName("Test for Movement constructor lowercase")
	void MovementConstuctorLowercaseTest() {
		Movement up = new Movement('u');
		Movement down = new Movement('d');
		Movement left = new Movement('l');
		Movement right = new Movement('r');
		Movement none = new Movement('j');
		assertTrue(up.getAction()==SokobanAction.UP && down.getAction()==SokobanAction.DOWN && left.getAction()==SokobanAction.LEFT && right.getAction()==SokobanAction.RIGHT);
		assertTrue(none.getAction()==null);
	}
	@Test
	@DisplayName("Test for Movement constructor uppercase")
	void MovementConstuctorUppercaseTest() {
	Movement upBox = new Movement('U');
	Movement downBox = new Movement('D');
	Movement leftBox = new Movement('L');
	Movement rightBox = new Movement('R');
	assertTrue(upBox.getAction()==SokobanAction.UP && downBox.getAction()==SokobanAction.DOWN && leftBox.getAction()==SokobanAction.LEFT && rightBox.getAction()==SokobanAction.RIGHT);
	assertTrue(upBox.isBoxMoved()==downBox.isBoxMoved()==leftBox.isBoxMoved()==rightBox.isBoxMoved()==true);
	}
	
}
