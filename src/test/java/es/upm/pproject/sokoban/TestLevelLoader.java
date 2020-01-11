package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.LevelLoader;
import es.upm.pproject.sokoban.model.Position;

@DisplayName("Test for LevelLoader's methods")
public class TestLevelLoader {
	private static LevelLoader loader = new LevelLoader();
	private  Game loadedGame;


	@BeforeAll
	static void load() {
		Path path = Paths.get("./src/test/resources/levels/");
		loader.setLevelsPath(path.toAbsolutePath().toString()+"/level_");
		System.out.println(loader.getLevelsPath());
	}


	@Nested
	class NestedClass {

		@BeforeEach 
		void initInnerClass() {
			loadedGame = loader.convertMap(1);
		}

		@Test
		@DisplayName("Testing levelName game loaded from level_1")
		void assertCorrectLevelName() {        
			assertTrue(loadedGame.getLevelName().equals("Initial level"));
			assertTrue(loadedGame.getGameScore()==0);
			assertTrue(loadedGame.getWarehouse().getHowManyBoxes()==1);
			
		}

		@Test
		@DisplayName("Testing levelName game loaded from level_1")
		void assertCorrectGameScore() { 
			assertTrue(loadedGame.getGameScore()==0);
			assertTrue(loadedGame.getWarehouse().getHowManyBoxes()==1);
		}
		
		@Test
		@DisplayName("Testing numberOfBoxes and BoxesAtGoal from level_1")
		void assertCorrectNumberOfBoxes() { 
			assertTrue(loadedGame.getWarehouse().getHowManyBoxes()==1);
			assertTrue(loadedGame.getWarehouse().getBoxesAtGoal()==0);
		}
		
		@Test
		@DisplayName("Testing levelName game loaded from level_1")
		void assertCorrectPlayerPosition() { 
			System.out.println(loadedGame.getWarehouse().getPlayerPos().getPosX());
			System.out.println(loadedGame.getWarehouse().getPlayerPos().getPosY());
			boolean isPosOk = loadedGame.getWarehouse().getPlayerPos().isEqualTo(new Position(4,2));
			assertTrue(isPosOk);
			isPosOk = loadedGame.getWarehouse().getPlayerPos().isEqualTo(new Position(1,3));
			assertFalse(isPosOk);
		}
	}
}
