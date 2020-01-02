package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.controller.SokobanMovement;

public class Warehouse {
	private Cell[][] warehouse;
	private Position playerPos;
	private int howManyBoxes;
	private int boxesAtGoal;
	
	public Warehouse(Cell[][] warehouse) {
		this.warehouse = warehouse;
		this.boxesAtGoal = 0;
		updatePlayerPositionAndNumberOfBoxes();	
	}
	
	private void updatePlayerPositionAndNumberOfBoxes(){
		this.howManyBoxes=0;
		int i;
		int j;
		for(i=0;i<warehouse.length;i++) {
			for(j=0;j<warehouse[0].length;j++) {
				if(warehouse[i][j].containsPlayer()) 
					this.playerPos = new Position(i,j);
				else if(warehouse[i][j].containsBox())
					this.howManyBoxes ++;
			}
		}
	}
	
	public SokobanMovement move(SokobanAction action) {
		boolean boxMoved=false;
		boolean playerMoved=false;
		int posX = playerPos.getPosX();
		int posY = playerPos.getPosY();
		int nextPosX=posX;
		int nextPosY=posY;
		int nextNextPosX=posX;
		int nextNextPosY=posY;
		switch (action) {
		case UP:
			nextPosX=nextPosX-1;
			nextNextPosX=nextNextPosX-2;
			break;
		case DOWN:
			nextPosX=nextPosX+1;
			nextNextPosX=nextNextPosX+2;
			break;
		case LEFT:
			nextPosY=nextPosY-1;
			nextNextPosY=nextNextPosY-2;
			break;
		case RIGHT:
			nextPosY=nextPosY+1;
			nextNextPosY=nextNextPosY+2;
			break;
		default:
			throw new IllegalArgumentException();
		}
		Cell currentCell = warehouse[posX][posY];
		Cell nextCell = warehouse[nextPosX][nextPosY];
		//next position is empty
		if(nextCell.containsNothing() && !nextCell.isWall()) {
			nextCell.setContent(SokobanElements.PLAYER);
			currentCell.setContent(SokobanElements.NONE);
			this.playerPos.setPosX(nextPosX);
			this.playerPos.setPosY(nextPosY);
			playerMoved=true;
		}
		//next position is box
		else if(nextCell.containsBox()) {
			Cell nextNextCell = warehouse[nextNextPosX][nextNextPosY];			
			if(nextNextCell.containsNothing() && !nextNextCell.isWall()) {
				if(nextCell.isGoal()) {
					boxesAtGoal--;
				}
				nextNextCell.setContent(SokobanElements.BOX);
				nextCell.setContent(SokobanElements.NONE);
				boxMoved = true;
				if(nextNextCell.isGoal()) {
					boxesAtGoal++;
				}
				nextCell.setContent(SokobanElements.PLAYER);
				currentCell.setContent(SokobanElements.NONE);
				this.playerPos.setPosX(nextPosX);
				this.playerPos.setPosY(nextPosY);
				playerMoved=true;				
			}
		}
		return new SokobanMovement(action,playerMoved,boxMoved);
	}
	
	public void undoMove(SokobanMovement movement) {
		SokobanAction action = movement.getAction();
		boolean playerMoved = movement.isPlayerMoved();
		boolean boxMoved = movement.isBoxMoved();

		int posX = playerPos.getPosX();
		int posY = playerPos.getPosY();
		int nextPosX=posX;
		int nextPosY=posY;
		int prevPosX=posX;
		int prevPosY=posY;

		switch (action) {
		case UP:
			nextPosX=nextPosX-1;
			prevPosX=prevPosX+1;
			break;
		case DOWN:
			nextPosX=nextPosX+1;
			prevPosX=prevPosX-1;
			break;
		case LEFT:
			nextPosY=nextPosY-1;
			prevPosY=prevPosY+1;
			break;
		case RIGHT:
			nextPosY=nextPosY+1;
			prevPosY=prevPosY-1;
			break;
		default:
			throw new IllegalArgumentException();
		}
		Cell currentCell = warehouse[posX][posY];
		Cell nextCell = warehouse[nextPosX][nextPosY];
		Cell prevCell = warehouse[prevPosX][prevPosY];

		if(playerMoved) {
			this.playerPos.setPosX(prevPosX);
			this.playerPos.setPosY(prevPosY);
			prevCell.setContent(SokobanElements.PLAYER);
			currentCell.setContent(SokobanElements.NONE);

			//next position is box
			if(nextCell.containsBox() && boxMoved) {
				currentCell.setContent(SokobanElements.BOX);
				nextCell.setContent(SokobanElements.NONE);
				if(nextCell.isGoal())
					boxesAtGoal--;
				if(currentCell.isGoal())
					boxesAtGoal++;
			}
		}
	}

}
