package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.model.Movement;

@DisplayName("Test for Movement related methods")
public class TestMovement {

	@Test
	@DisplayName("Test for Movement constructor lowercase")
	void movementConstuctorLowercaseTest() {
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
	void movementConstuctorUppercaseTest() {
		Movement upBox = new Movement('U');
		Movement downBox = new Movement('D');
		Movement leftBox = new Movement('L');
		Movement rightBox = new Movement('R');
		assertTrue(upBox.getAction()==SokobanAction.UP && downBox.getAction()==SokobanAction.DOWN && leftBox.getAction()==SokobanAction.LEFT && rightBox.getAction()==SokobanAction.RIGHT);
		assertTrue(upBox.isBoxMoved()==downBox.isBoxMoved()==leftBox.isBoxMoved()==rightBox.isBoxMoved()==true);
	}

	@Test
	@DisplayName("Test for Movement toChar lowercase")
	void movementToCharLowercaseTest() {
		Movement up = new Movement('u');
		Movement down = new Movement('d');
		Movement left = new Movement('l');
		Movement right = new Movement('r');
		assertTrue(up.toChar().equals('u') && down.toChar().equals('d') && left.toChar().equals('l') && right.toChar().equals('r'));
	}

	@Test
	@DisplayName("Test for Movement toChar uppercase")
	void movementToCharUppercaseTest() {
		Movement upBox = new Movement('U');
		Movement downBox = new Movement('D');
		Movement leftBox = new Movement('L');
		Movement rightBox = new Movement('R');
		assertTrue(upBox.toChar().equals('U') && downBox.toChar().equals('D') && leftBox.toChar().equals('L') && rightBox.toChar().equals('R'));
	}


}
