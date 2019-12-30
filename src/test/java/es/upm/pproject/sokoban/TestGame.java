package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.SokobanModel;

@DisplayName("Test message")
public class TestGame {	
	
	@Test
	@DisplayName("Test for testing movements")
	void wholeLevelTest() {
		SokobanModel model = new SokobanModel();
		
		Game game = model.getCurrent();
		game.setHowManyBoxes(1);

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
		assertTrue(game.getBoxesAtGoal()==1);
	}  	
}