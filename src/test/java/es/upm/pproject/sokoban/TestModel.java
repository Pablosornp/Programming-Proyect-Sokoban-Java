package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
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
import es.upm.pproject.sokoban.model.SokobanModel;
import es.upm.pproject.sokoban.model.SaveManager;
import es.upm.pproject.sokoban.model.LoadManager;

@DisplayName("Tests for model methods")
public class TestModel {

	private Cell [][] basicBoard;
	private Game basicGame;

	Logger logger 
	= Logger.getLogger( 
			TestModel.class.getName()); 

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
	@DisplayName("Test to warehouse undoMove #2. Without involving the stack yet")
	void undoMoveTest2() {
		Movement firstMove = basicGame.move(SokobanAction.RIGHT);
		Movement secondMove = basicGame.move(SokobanAction.RIGHT);
		Movement thirdMove = basicGame.move(SokobanAction.RIGHT);
		Movement forthMove = basicGame.move(SokobanAction.RIGHT);
		Movement fifthMove = basicGame.move(SokobanAction.RIGHT); //player-box-wall
		assertTrue(basicGame.getLevelScore()==4);
		basicGame.undoMove(fifthMove);	
		basicGame.undoMove(forthMove);	
		basicGame.undoMove(thirdMove);	
		basicGame.undoMove(secondMove);	
		basicGame.undoMove(firstMove);
		assertTrue(basicGame.getLevelScore()==0);
	}



	@Test
	@DisplayName("Test for SokobanModel constructor")
	void sokobanModelTest() {
		SokobanModel model = new SokobanModel();
		model.loadNextLevel();
		//		logger.info(model.getCurrent().toString());
		assertNotEquals(model.getCurrent(),model.loadNextLevel());
	}

	@Test
	@DisplayName("Test for SokobanModel performMovement")
	void sokobanModelPerformMovementTest() {
		SokobanModel model = new SokobanModel();
		model.startNewGame();
		model.loadNextLevel();
		//		logger.info("GameScore before any move: "+ model.getGameScore()+" \n");
		model.performMovement(SokobanAction.RIGHT);
		//		logger.info("GameScore after 1 move: "+ model.getGameScore()+" \n");
		model.performMovement(SokobanAction.RIGHT);	// player walks into wall
		//		logger.info("GameScore after 2 moves: "+ model.getGameScore()+" (wall)\n");
		model.performMovement(SokobanAction.RIGHT);	// player walks into wall x2
		//		logger.info("GameScore after 3 moves: "+ model.getGameScore()+" (wall)\n");
		model.performMovement(SokobanAction.RIGHT);	// player walks into wall x3
		//		logger.info("GameScore after 4 moves: "+ model.getGameScore()+" (wall)\n");
		model.performMovement(SokobanAction.UP);
		//		logger.info("GameScore after 5 moves: "+ model.getGameScore()+" \n");
		assertEquals(2,model.getGameScore());
	}

	@Test
	@DisplayName("Test for SokobanModel undoMovement")
	void sokobanModelUndoMovementTest() {
		SokobanModel model = new SokobanModel();
		model.startNewGame();
		model.loadNextLevel();
		//		logger.info("GameScore before any move: "+ model.getGameScore()+" \n");
		model.performMovement(SokobanAction.RIGHT);
		//		logger.info("GameScore after 1 move: "+ model.getGameScore()+" \n");
		model.performMovement(SokobanAction.RIGHT);	// player walks into wall
		//		logger.info("GameScore after 2 moves: "+ model.getGameScore()+" \n");
		model.performMovement(SokobanAction.UP);
		//		logger.info("GameScore after 3 moves: "+ model.getGameScore()+" \n");
		model.undoMovement();
		//		logger.info("GameScore after undoMovement: "+ model.getGameScore()+" \n");
		model.undoMovement();
		//		logger.info("GameScore after undoMovement: "+ model.getGameScore()+" \n");
		model.undoMovement();
		//		logger.info("GameScore after undoMovement: "+ model.getGameScore()+" \n");
		assertEquals(0,model.getGameScore());
	}

	@Test
	@DisplayName("Test for SokobanModel restartLevel")
	void sokobanModelRestartLevelTest() {
		SokobanModel model = new SokobanModel();
		model.startNewGame();
		model.loadNextLevel();
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.UP);
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.DOWN);
		assertFalse(model.getCurrent()==null);
		model.restartLevel();
		//		logger.info("GameScore after restart: "+model.getGameScore());
		assertTrue(model.getGameScore()==0);
	}

	@Test
	@DisplayName("Test for SokobanModel hasNextLevel")
	void sokobanModelHasNextLevelTest() {
		SokobanModel model = new SokobanModel();
		model.startNewGame();
		assertTrue(model.hasNextLevel());
	}

	@Test
	@DisplayName("Test for SaveManager saveGame")
	void saveManagerSaveGameTest() {
		SokobanModel model = new SokobanModel();
		model.startNewGame();
		model.loadNextLevel();
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.UP);
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.DOWN);
		assertTrue(model.saveGame("TestSavedGame"));
	}

	@Test
	@DisplayName("Test for LoadManager loadGame")
	void loadManagerLoadGameTest() {
		SokobanModel model = new SokobanModel();

		model.startNewGame();
		model.loadNextLevel();
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.UP);
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.DOWN);
//		int gameScoreBeforeSave = model.getGameScore();
//		logger.info("GameScoreBeforeSave: "+gameScoreBeforeSave);
		assertTrue(model.saveGame("TestSavedGame"));
		model.performMovement(SokobanAction.RIGHT);
		model.performMovement(SokobanAction.UP);
		model.performMovement(SokobanAction.LEFT);
//		logger.info("GameScoreAfter3moves: "+model.getGameScore());		
//		String savesFolderPath = Paths.get("./src/test/resources/saves/").toAbsolutePath().toString();
		Path path = Paths.get("./saves/TestSavedGame.sav");
		String pathToFile = path.toString();
//		logger.info("pathToFile given to loadGame: " + pathToFile);
//		model.loadGame("./saves/ThisFileNotExists.sav");
		model.loadGame(pathToFile);
//		logger.info("GameScoreAfterLoad: " + model.getGameScore());
		model.undoMovement();
//		logger.info("GameScoreAfterUndo1move: " + model.getGameScore());
		assertEquals(4,model.getGameScore());
	}

}