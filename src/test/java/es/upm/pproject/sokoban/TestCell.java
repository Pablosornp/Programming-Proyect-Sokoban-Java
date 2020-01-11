package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.model.Cell;

@DisplayName("Tests for Cell class methods")
public class TestCell {

	@Test
	@DisplayName("Test #1 for Cell constructor")
	void cellConstructorTest1() {
		Cell gapBox = new Cell('#');
		Cell goalNone = new Cell('*');
		assertTrue(gapBox.containsBox() && gapBox.isGap() && goalNone.containsNothing() && goalNone.isGoal());
	}

	@Test
	@DisplayName("Test #2 for Cell constructor")
	void cellConstructorTest2() {
		Cell wallNone = new Cell('+');
		Cell gapPlayer = new Cell('W');
		assertTrue(wallNone.isWall() && wallNone.containsNothing() && gapPlayer.containsPlayer() && gapPlayer.isGap());
	}

	@Test
	@DisplayName("Test #3 for Cell constructor")
	void cellConstructorTest3() {
		Cell gapNone = new Cell(' ');
		Cell goalPlayer = new Cell('@');
		Cell goalBox = new Cell('$');
		assertTrue(gapNone.isGap() && gapNone.containsNothing() && goalPlayer.isGoal() && goalPlayer.containsPlayer());
		assertTrue(goalBox.isGoal() && goalBox.containsBox());
	}

	@Test
	@DisplayName("Test Cell getType")
	void cellGetTypeTest() {
		Cell wallNone = new Cell('+');
		assertEquals(SokobanElements.WALL,wallNone.getType());
	}

	@Test
	@DisplayName("Test Cell getContent")
	void cellGetContentTest() {
		Cell wallNone = new Cell('+');
		assertEquals(SokobanElements.NONE,wallNone.getContent());
	}

	@Test
	@DisplayName("Test Cell setType")
	void cellSetTypeTest() {
		Cell goalNone = new Cell('*');
		assertNotEquals(SokobanElements.GAP,goalNone.getType());
		goalNone.setType(SokobanElements.GAP);
		assertEquals(SokobanElements.GAP,goalNone.getType());
	}

	@Test
	@DisplayName("Test Cell to Char #1")
	void cellToCharTest1() {
		Cell gapBox = new Cell('#');
		Cell goalNone = new Cell('*');
		Cell wallNone = new Cell('+');
		assertTrue(gapBox.toChar().equals('#') && goalNone.toChar().equals('*') && wallNone.toChar().equals('+'));
	}

	@Test
	@DisplayName("Test Cell to Char #2")
	void cellToCharTest2() {
		Cell gapPlayer = new Cell('W');
		Cell gapNone = new Cell(' ');
		Cell goalPlayer = new Cell('@');
		Cell goalBox = new Cell('$');
		assertTrue(gapPlayer.toChar().equals('W') && gapNone.toChar().equals(' ') && goalPlayer.toChar().equals('@') && goalBox.toChar().equals('$'));
	}
}
