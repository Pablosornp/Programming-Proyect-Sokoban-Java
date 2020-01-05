package es.upm.pproject.sokoban.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveManager {

	private String savesFolderPath;

	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.SavesManager");
	
	public SaveManager() {
		Path path = Paths.get("./saves/");
		this.savesFolderPath = path.toAbsolutePath().toString();
	}

	public boolean saveGame(Game game, Deque<Movement> movements, String saveName) {
		try{
			File directory = new File(savesFolderPath);
			if (!directory.exists()){
				directory.mkdir();
			}
			String saveFile="\\"+saveName+".sav";
			File file = new File(savesFolderPath+saveFile);
			try(Writer bw = new BufferedWriter(new FileWriter(file))){
				String newLine = System.getProperty("line.separator");

				//Line 1: GameScore LevelScore
				bw.write(game.getGameScore()+ " " + game.getLevelScore() + newLine);
				
				//Line 2: LevelNumber LevelName
				bw.write(game.getLevelNumber()+ " " +  game.getLevelName() + newLine);

				//Line 3: movements
				Movement move;
				StringBuilder sb = new StringBuilder("");
				Object[] movementsArray = movements.toArray();
				for(int i=movementsArray.length-1; i>=0; i--) {
					move=(Movement) movementsArray[i];
					sb.append(move.toChar());
				}
				bw.write(sb.toString() + newLine);
				sb.setLength(0);//Empty the StringBuilder

				//Line 4: M N
				Cell[][] warehouse = game.getWarehouse().getBoard();
				int m = warehouse.length;
				int n = warehouse[0].length;
				bw.write(m + " " + n + newLine);

				//Line 5 to 5+M: ASCII MAP
				for(int i=0; i<m; i++) {
					//Empty the StringBuilder
					sb.setLength(0);
					for(int j=0; j<n; j++) {
						sb.append(warehouse[i][j].toChar());
					}
					bw.write(sb.toString() + newLine);
				}
			}
			LOGGER.log(Level.INFO, "Game saved succesfullly.");
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "An error ocurred while saving the game.");
			return false;
		}
		return true;
	}

}
