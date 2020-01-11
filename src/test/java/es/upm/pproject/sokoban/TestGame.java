package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.model.Cell;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.Warehouse;
import es.upm.pproject.sokoban.model.Movement;

@DisplayName("Tests for Game methods in a default board")
public class TestGame {	

	private Cell [][] defaultBoard;
	private Game game;
	Logger logger 
	= Logger.getLogger( 
			TestGame.class.getName()); 

	@BeforeEach
	@DisplayName("Create Default Board and Game")
	public  void createDefaultBoardAndGame() {
		this.defaultBoard = new Cell [8][8];
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

		this.game = new Game(99, "Test Level", new Warehouse(defaultBoard));
	}

	@Test
	@DisplayName("Test for testing movements")
	void wholeLevelTest() {
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
		assertFalse(game.isLevelCompleted());
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
		assertTrue(game.isLevelCompleted());
	}

	@Test
	@DisplayName("Test to get LevelNumber")
	void levelNumberTest() {
		assertSame(99,game.getLevelNumber());		
	}

	@Test
	@DisplayName("Test to get LevelName")
	void getLevelNameTest() {
		assertEquals("Test Level",game.getLevelName());
	}

	@Test
	@DisplayName("Test to get GameScore")
	void getGameScoreTest() {
		assertSame(0,game.getGameScore());
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT); // walk into wall
		game.move(SokobanAction.DOWN);
		assertSame(6,game.getGameScore());
	}

	@Test
	@DisplayName("Test to set GameScore")
	void setGameScoreTest() {
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		game.setGameScore(0);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		assertSame(2,game.getGameScore());		
	}

	@Test
	@DisplayName("Test to get LevelScore")
	void getLevelScoreTest() {
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
		assertSame(15,game.getLevelScore());
	}

	@Test
	@DisplayName("Test to set LevelScore")
	void setLevelScoreTest() {
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.setLevelScore(1);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		assertSame(5,game.getLevelScore());	
	}

	@Test
	@DisplayName("Test to decrement Score")
	void decrementScoreTest() {
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.decrementScore();
		assertSame(8, game.getGameScore());
		assertSame(8, game.getLevelScore());
	}

	@Test
	@DisplayName("Test to undo Move. Without involving the stack")
	void undoMoveTest() {
		Movement firstMove = game.move(SokobanAction.UP);
		Movement secondMove = game.move(SokobanAction.RIGHT);
		Movement thirdMove = game.move(SokobanAction.RIGHT);
		Movement forthMove = game.move(SokobanAction.RIGHT);
		Movement fifthMove = game.move(SokobanAction.RIGHT);
		Movement sixthMove = game.move(SokobanAction.RIGHT);
		Movement seventhMove = game.move(SokobanAction.DOWN);
		Movement eighthMove = game.move(SokobanAction.LEFT);
		Movement ninthMove = game.move(SokobanAction.DOWN);
		Movement tenthMove = game.move(SokobanAction.LEFT);
		Movement eleventhMove = game.move(SokobanAction.UP);
		assertTrue(game.getLevelScore()==9);
		game.undoMove(eleventhMove);
		game.undoMove(tenthMove);
		game.undoMove(ninthMove);
		game.undoMove(eighthMove);
		game.undoMove(seventhMove);	
		game.undoMove(sixthMove);	
		game.undoMove(fifthMove);	
		game.undoMove(forthMove);	
		game.undoMove(thirdMove);	
		game.undoMove(secondMove);	
		game.undoMove(firstMove);
		assertTrue(game.getLevelScore()==0);
	}

}