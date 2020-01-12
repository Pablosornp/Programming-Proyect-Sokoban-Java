package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		assertTrue(up.getAction()==SokobanAction.UP);
		assertTrue(down.getAction()==SokobanAction.DOWN);
	}
	
	@Test
	@DisplayName("Test for Movement constructor lowercase #2")
	void movementConstuctorLowercaseTest2() {
		Movement left = new Movement('l');
		Movement right = new Movement('r');
		assertTrue(left.getAction()==SokobanAction.LEFT);
		assertTrue(right.getAction()==SokobanAction.RIGHT);
	}
	
	@Test
	@DisplayName("Test for Movement constructor lowercase #3")
	void movementConstuctorLowercaseTest3() {
		Movement none = new Movement('j');
		assertTrue(none.getAction()==null);
	}

	@Test
	@DisplayName("Test for Movement constructor uppercase Up")
	void movementConstuctorUppercaseUpTest() {
		Movement upBox = new Movement('U');
		assertTrue(upBox.getAction()==SokobanAction.UP);
		assertTrue(upBox.isBoxMoved());
	}
	
	@Test
	@DisplayName("Test for Movement constructor uppercase Down")
	void movementConstuctorUppercaseDownTest() {
		Movement downBox = new Movement('D');
		assertEquals(SokobanAction.DOWN,downBox.getAction());
		assertTrue(downBox.isBoxMoved());
	}
	
	@Test
	@DisplayName("Test for Movement constructor uppercase Left")
	void movementConstuctorUppercaseLeftTest() {
		Movement leftBox = new Movement('L');
		assertEquals(SokobanAction.LEFT,leftBox.getAction());
		assertTrue(leftBox.isBoxMoved());
	}
	
	@Test
	@DisplayName("Test for Movement constructor uppercase Right")
	void movementConstuctorUppercaseRightTest() {
		Movement rightBox = new Movement('R');
		assertEquals(SokobanAction.RIGHT,rightBox.getAction());
		assertTrue(rightBox.isBoxMoved());
	}
	
	@Test
	@DisplayName("Test for Movement constructor uppercase Null")
	void movementConstuctorUppercaseNullTest() {
		Movement noneBox = new Movement('J');
		assertTrue(noneBox.getAction()==null);
	}

	
	@Test
	@DisplayName("Test for Movement toChar lowercase #1")
	void movementToCharLowercaseTest1() {
		Movement up = new Movement('u');
		Movement down = new Movement('d');
		assertTrue(up.toChar().equals('u'));
		assertTrue(down.toChar().equals('d'));
	}
	
	@Test
	@DisplayName("Test for Movement toChar lowercase #2")
	void movementToCharLowercaseTest2() {
		Movement left = new Movement('l');
		Movement right = new Movement('r');
		assertTrue(left.toChar().equals('l'));
		assertTrue(right.toChar().equals('r'));
	}

	@Test
	@DisplayName("Test for Movement toChar uppercase")
	void movementToCharUppercaseTest() {
		Movement upBox = new Movement('U');
		Movement downBox = new Movement('D');
		assertTrue(upBox.toChar().equals('U'));
		assertTrue(downBox.toChar().equals('D'));
	}
	
	@Test
	@DisplayName("Test for Movement toChar uppercase #2")
	void movementToCharUppercaseTest2() {
		Movement leftBox = new Movement('L');
		Movement rightBox = new Movement('R');
		assertTrue(leftBox.toChar().equals('L'));
		assertTrue(rightBox.toChar().equals('R'));
	}


}
