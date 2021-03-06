package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanElements;

public class Warehouse {
	private Cell[][] board;

	private Position playerPos;
	private int howManyBoxes;
	private int boxesAtGoal;
	private SokobanAction lastAction;
	private int step;

	public Warehouse(Cell[][] warehouse) {
		this.board = warehouse;
		this.boxesAtGoal = 0;
		updateWarehouseParameters();	
		this.lastAction = SokobanAction.DOWN;
	}

	public Cell[][] getBoard() {
		return board;
	}

	public Position getPlayerPos() {
		return playerPos;
	}

	public int getHowManyBoxes() {
		return howManyBoxes;
	}

	public int getBoxesAtGoal() {
		return boxesAtGoal;
	}

	public SokobanAction getLastAction() {
		return lastAction;
	}

	public int getStep() {
		return this.step;
	}

	public void nextStep(){
		this.step=(this.step + 1)%2;
	}

	private void updateWarehouseParameters(){
		this.howManyBoxes=0;
		this.boxesAtGoal=0;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				if(board[i][j].containsPlayer()) {
					this.playerPos = new Position(i,j);
				}
				else if(board[i][j].containsBox()) {
					if(board[i][j].isGoal())
						this.boxesAtGoal++;
					this.howManyBoxes ++;
				}
			}
		}
	}

	public Movement move(SokobanAction action) {
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
		Cell currentCell = board[posX][posY];
		Cell nextCell = board[nextPosX][nextPosY];
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
			Cell nextNextCell = board[nextNextPosX][nextNextPosY];			
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
		this.lastAction = action;
		this.nextStep();
		return new Movement(action,playerMoved,boxMoved);
	}

	public void undoMove(Movement movement) {
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
		Cell currentCell = board[posX][posY];
		Cell nextCell = board[nextPosX][nextPosY];
		Cell prevCell = board[prevPosX][prevPosY];

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
		this.lastAction = movement.getAction();
		this.nextStep();
	}

}
