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
		}
		
		public String drawWarehouse(Cell[][] warehouse){
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
