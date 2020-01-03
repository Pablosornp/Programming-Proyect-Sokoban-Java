package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.model.Game;

@DisplayName("Test message")
public class TestGame {	
	
	@Test
	@DisplayName("Test for testing movements")
	void wholeLevelTest() {
		
		Game game = new Game("Test");
		game.getWarehouse().setHowManyBoxes(1);

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