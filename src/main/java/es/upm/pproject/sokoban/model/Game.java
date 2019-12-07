package es.upm.pproject.sokoban.model;

public class Game {
	private Integer gameScore;
	private Integer levelScore;
	private Cell[][] warehouse;
	private Position playerPos;
	private int howManyBoxes;
	private int boxesAtGoal;

	public Game(Cell[][] warehouse) {
		this.levelScore = 0;
		this.warehouse = warehouse;
		Position pos = getPlayerPosition();
	}

	public Position getPlayerPosition(){
		int i,j;

		for(i=0;i<warehouse.length;i++) {
			for(j=0;j<warehouse[0].length;j++) {
				if(warehouse[i][j].containsPlayer()) {
					return new Position(i,j);
				}
			}
		}
		return null;
	}

	public void moveUp(SokobanMovements movementUP) {
		int posX = playerPos.getPosX();
		int posY = playerPos.getPosY();
		Cell upperCell = warehouse[posX-1][posY];
		//upper position is empty
		if(upperCell.containsNothing()) {
			warehouse[posX][posY].setContent("");
			upperCell.setContent("player");
			this.playerPos.setPosX(posX-1);
			return;
		}
		//upper position is wall
		if(upperCell.isWall()) {
			return;			
		}
		//upper position is box
		if(upperCell.containsBox()) {
			Cell upperUpperCell = warehouse[posX-2][posY];			
			if(upperUpperCell.containsNothing()) {
				if(upperCell.isGoal()) {
					boxesAtGoal--;
				}
				upperUpperCell.setContent("box");
				if(upperUpperCell.isGoal()) {
					boxesAtGoal++;
				}
				upperCell.setContent("player");
				this.playerPos.setPosX(posX-1);
			}
		}
	}
}