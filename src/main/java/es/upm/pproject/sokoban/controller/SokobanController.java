package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.view.GameView;

public class SokobanController {

		private GameView view; 
		private SokobanModel model;

		public SokobanController(GameView view, SokobanModel model) {
			this.view = view;
			this.model = model;
		}
		
		public void setView(GameView view) {
			this.view = view;
		}
		
		public void onMoveUp() {
			Game currentGame = model.performMovement(SokobanMovements.UP);
			String levelScore = currentGame.getLevelScore().toString();
			view.setLevelScoreValue(levelScore);
			Cell[][] board = currentGame.getWarehouse();
			view.drawWarehouse(this.warehouseToString(board));
		}
		
		public void onMoveDown() {
			Game currentGame = model.performMovement(SokobanMovements.DOWN);
			String levelScore = currentGame.getLevelScore().toString();
			view.setLevelScoreValue(levelScore);
			Cell[][] board = currentGame.getWarehouse();
			view.drawWarehouse(this.warehouseToString(board));
			view.enableKeyboard();
		}
		
		public void onMoveRight() {
			Game currentGame = model.performMovement(SokobanMovements.RIGHT);
			String levelScore = currentGame.getLevelScore().toString();
			view.setLevelScoreValue(levelScore);
			Cell[][] board = currentGame.getWarehouse();
			view.drawWarehouse(this.warehouseToString(board));
			view.enableKeyboard();
		}
		
		public void onMoveLeft() {
			Game currentGame = model.performMovement(SokobanMovements.LEFT);
			String levelScore = currentGame.getLevelScore().toString();
			view.setLevelScoreValue(levelScore);
			Cell[][] board = currentGame.getWarehouse();
			view.drawWarehouse(this.warehouseToString(board));
			view.enableKeyboard();
		}
		
		public void onRestart() {
			Game restartedLevel = model.restartLevel();
			String levelScore = restartedLevel.getLevelScore().toString();
			String gameScore = restartedLevel.getGameScore().toString();
			view.setLevelScoreValue(levelScore);
			view.setGameScoreValue(gameScore);
			Cell[][] board = restartedLevel.getWarehouse();
			view.drawWarehouse(this.warehouseToString(board));
			view.enableKeyboard();
		}
		
		public String warehouseToString(Cell[][] warehouse){
			StringBuilder sb = new StringBuilder("\n");
			Cell cell;
			
			for(int i=0;i<warehouse.length;i++) {
				for(int j=0;j<warehouse[0].length;j++) {
					cell = warehouse[i][j];
					if(cell.isGap()) {
						if(cell.containsNothing()) 
							sb.append(" ");
						if(cell.containsBox())
							sb.append("#");
						if(cell.containsPlayer())
							sb.append("W");						
					}
					if(cell.isGoal()) {
						if(cell.containsNothing()) 
							sb.append("*");
						if(cell.containsBox())
							sb.append("#");
						if(cell.containsPlayer())
							sb.append("W");						
					}
					if(cell.isWall())
						sb.append("+");	
				}
				sb.append("\n");
			}
			return sb.toString();
		}
}
