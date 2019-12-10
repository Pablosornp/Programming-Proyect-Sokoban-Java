package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.Cell;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.SokobanModel;
import es.upm.pproject.sokoban.model.SokobanMovements;

@DisplayName("Test message")
public class TestGame {	
	
	@Test
	@DisplayName("Test for testing tests")
	void assertThrowsInvalidaName() {
		SokobanModel model = new SokobanModel();
		
		Game game = model.getCurrent();
		game.setHowManyBoxes(1);

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
		assertTrue(game.getBoxesAtGoal()==1);
	}  	
}