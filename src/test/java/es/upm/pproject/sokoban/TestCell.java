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
	@DisplayName("Test #1 for Cell constructor with SokobanElements")
	void cellConstructorWithElemTest1() {
		Cell gapBox = new Cell(SokobanElements.GAP,SokobanElements.BOX);
		Cell goalNone = new Cell(SokobanElements.GOAL,SokobanElements.NONE);
		assertTrue(gapBox.containsBox() && gapBox.isGap() && goalNone.containsNothing() && goalNone.isGoal());
	}

	@Test
	@DisplayName("Test #2 for Cell constructor with SokobanElements")
	void cellConstructorWithElemTest2() {
		Cell wallNone = new Cell(SokobanElements.WALL,SokobanElements.NONE);
		Cell gapPlayer = new Cell(SokobanElements.GAP,SokobanElements.PLAYER);
		assertTrue(wallNone.isWall() && wallNone.containsNothing() && gapPlayer.containsPlayer() && gapPlayer.isGap());
	}

	@Test
	@DisplayName("Test #3 for Cell constructor with SokobanElements")
	void cellConstructorWithElemTest3() {
		Cell gapNone = new Cell(SokobanElements.GAP,SokobanElements.NONE);
		Cell goalPlayer = new Cell(SokobanElements.GOAL,SokobanElements.PLAYER);
		Cell goalBox = new Cell(SokobanElements.GOAL,SokobanElements.BOX);
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
	@DisplayName("Test Cell to Char #1 - ContainsNothing cases")
	void cellToCharTest1() {
		Cell goalNone = new Cell('*');
		Cell wallNone = new Cell('+');
		Cell gapNone = new Cell(' ');
		assertTrue(goalNone.toChar().equals('*') && wallNone.toChar().equals('+') && gapNone.toChar().equals(' '));
		assertTrue(goalNone.containsNothing()==wallNone.containsNothing()==gapNone.containsNothing());
	}

	@Test
	@DisplayName("Test Cell to Char #2 - Player Case 1")
	void cellToCharTest2() {
		Cell gapPlayer = new Cell('W');
		assertTrue(gapPlayer.toChar().equals('W'));
		assertTrue(gapPlayer.isGap() && gapPlayer.containsPlayer());
	}
	
	@Test
	@DisplayName("Test Cell to Char #3 - Player case 2")
	void cellToCharTest3() {
		Cell goalPlayer = new Cell('@');
		assertTrue(goalPlayer.toChar().equals('@'));
		assertTrue(goalPlayer.isGoal() && goalPlayer.containsPlayer());
	}
	
	@Test
	@DisplayName("Test Cell to Char #4 - Box case 1")
	void cellToCharTest4() {
		Cell gapBox = new Cell(SokobanElements.GAP,SokobanElements.BOX);
		Character charAtTest = gapBox.toChar();
		assertTrue(charAtTest.equals('#'));
		assertTrue(gapBox.isGap() && gapBox.containsBox());
	}
	
	@Test
	@DisplayName("Test Cell to Char #5 - Box case 2")
	void cellToCharTest5() {
		Cell goalBox = new Cell(SokobanElements.GOAL,SokobanElements.BOX);
		Character charAtTest = goalBox.toChar();
		assertTrue(charAtTest.equals('$'));
		assertTrue(goalBox.isGoal() && goalBox.containsBox());
	}
}
