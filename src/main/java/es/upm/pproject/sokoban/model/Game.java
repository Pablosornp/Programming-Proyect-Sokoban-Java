package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.controller.SokobanAction;
import es.upm.pproject.sokoban.controller.SokobanMovement;

public class Game {
	private int levelNumber;
	private String levelName;
	private Integer gameScore;
	private Integer levelScore;
	private Cell[][] warehouse;
	private Position playerPos;
	private int howManyBoxes;
	private int boxesAtGoal;

	public Game(String levelName, Cell[][] warehouse) {
		this.levelName = levelName;
		this.levelScore = 0;
		this.gameScore = 0;
		this.warehouse = warehouse;
		updatePlayerPositionAndNumberOfBoxes();	
		this.boxesAtGoal = 0;
		this.howManyBoxes = 0;
	}

	public void restartLevel(Cell[][] warehouse, int gameScore) {
		this.levelScore = 0;
		this.gameScore = gameScore;
		this.warehouse = warehouse;
		updatePlayerPositionAndNumberOfBoxes();	
		this.boxesAtGoal = 0;
	}
	
	
	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	public String getLevelName() {
		return levelName;
	}

	public Integer getGameScore() {
		return gameScore;
	}

	public Integer getLevelScore() {
		return levelScore;
	}

	public int getBoxesAtGoal() {
		return boxesAtGoal;
	}

	public int getHowManyBoxes() {
		return howManyBoxes;
	}

	public Cell[][] getWarehouse() {
		return warehouse;
	}

	public void setHowManyBoxes(int howManyBoxes) {
		this.howManyBoxes = howManyBoxes;
	}

	public void updatePlayerPositionAndNumberOfBoxes(){
		this.howManyBoxes=0;
		int i,j;
		for(i=0;i<warehouse.length;i++) {
			for(j=0;j<warehouse[0].length;j++) {
				if(warehouse[i][j].containsPlayer()) 
					this.playerPos = new Position(i,j);
				else if(warehouse[i][j].containsBox())
					this.howManyBoxes ++;
			}
		}
	}

	public void incrementScore() {
		this.levelScore++;
		this.gameScore++;
	}

	public void decrementScore() {
		this.levelScore--;
		this.gameScore--;
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
			incrementScore();
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
				incrementScore();
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
			decrementScore();
		}
	}

	public void showWarehouse(){
		Cell cell;
		for(int i=0;i<warehouse.length;i++) {
			for(int j=0;j<warehouse[0].length;j++) {
				cell = warehouse[i][j];
				if(cell.isGap()) {
					if(cell.containsNothing()) 
						System.out.print(" ");
					if(cell.containsBox())
						System.out.print("#");
					if(cell.containsPlayer())
						System.out.print("W");						
				}
				if(cell.isGoal()) {
					if(cell.containsNothing()) 
						System.out.print("*");
					if(cell.containsBox())
						System.out.print("#");
					if(cell.containsPlayer())
						System.out.print("W");						
				}
				if(cell.isWall())
					System.out.print("+");	
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Cell [][] board = new Cell [8][8];
		board [0][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [0][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [0][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [0][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [0][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [0][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [0][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [0][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		board [1][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [1][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [1][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [1][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		board [2][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [2][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [2][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][5] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][6] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [2][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [3][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [3][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][4] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [3][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [4][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [4][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [4][2] = new Cell(SokobanElements.GAP, SokobanElements.PLAYER);
		board [4][3] = new Cell(SokobanElements.GOAL, SokobanElements.NONE);
		board [4][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [4][5] = new Cell(SokobanElements.GAP, SokobanElements.BOX);
		board [4][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [4][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [5][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [5][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [5][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [5][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [6][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [6][1] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [6][2] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [6][3] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [6][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [6][5] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [6][6] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [6][7] = new Cell(SokobanElements.WALL, SokobanElements.NONE);

		board [7][0] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][1] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][2] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][3] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][4] = new Cell(SokobanElements.WALL, SokobanElements.NONE);
		board [7][5] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [7][6] = new Cell(SokobanElements.GAP, SokobanElements.NONE);
		board [7][7] = new Cell(SokobanElements.GAP, SokobanElements.NONE);

		Game game = new Game("Initial Level",board);
		game.setHowManyBoxes(1);

		game.showWarehouse();
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.LEFT);
		game.move(SokobanAction.UP);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.DOWN);
		game.move(SokobanAction.RIGHT);
		game.move(SokobanAction.UP);
		game.showWarehouse();
		System.out.println(game.boxesAtGoal);

	}
}