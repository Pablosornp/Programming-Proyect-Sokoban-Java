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
		this.gameScore = 0;
		this.warehouse = warehouse;
		this.playerPos = getPlayerPosition();	
		this.boxesAtGoal = 0;
	}

	public Game(Cell[][] warehouse, int gameScore) {
		this.levelScore = 0;
		this.gameScore = gameScore;
		this.warehouse = warehouse;
		this.playerPos = getPlayerPosition();	
		this.boxesAtGoal = 0;
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

	public void incrementLevelScore() {
		this.levelScore++;
	}

	public void move(SokobanMovements movement) {
		int posX = playerPos.getPosX();
		int posY = playerPos.getPosY();
		int nextPosX=posX;
		int nextPosY=posY;
		int nextNextPosX=posX;
		int nextNextPosY=posY;
		switch (movement) {
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
			currentCell.setContent("");
			nextCell.setContent("player");
			this.playerPos.setPosX(nextPosX);
			this.playerPos.setPosY(nextPosY);
			incrementLevelScore();
			return;
		}
		//next position is box
		if(nextCell.containsBox()) {
			Cell nextNextCell = warehouse[nextNextPosX][nextNextPosY];			
			if(nextNextCell.containsNothing()) {
				if(nextCell.isGoal()) {
					boxesAtGoal--;
				}
				nextNextCell.setContent("box");
				if(nextNextCell.isGoal()) {
					boxesAtGoal++;
				}
				nextCell.setContent("player");
				currentCell.setContent("");
				this.playerPos.setPosX(nextPosX);
				this.playerPos.setPosY(nextPosY);
				incrementLevelScore();
			}
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
		board [0][0] = new Cell("wall", "");
		board [0][1] = new Cell("wall", "");
		board [0][2] = new Cell("wall", "");
		board [0][3] = new Cell("wall", "");
		board [0][4] = new Cell("gap", "");
		board [0][5] = new Cell("gap", "");
		board [0][6] = new Cell("gap", "");
		board [0][7] = new Cell("gap", "");

		board [1][0] = new Cell("wall", "");
		board [1][1] = new Cell("gap", "");
		board [1][2] = new Cell("gap", "");
		board [1][3] = new Cell("wall", "");
		board [1][4] = new Cell("gap", "");
		board [1][5] = new Cell("gap", "");
		board [1][6] = new Cell("gap", "");
		board [1][7] = new Cell("gap", "");

		board [2][0] = new Cell("wall", "");
		board [2][1] = new Cell("gap", "");
		board [2][2] = new Cell("gap", "");
		board [2][3] = new Cell("wall", "");
		board [2][4] = new Cell("wall", "");
		board [2][5] = new Cell("wall", "");
		board [2][6] = new Cell("wall", "");
		board [2][7] = new Cell("wall", "");

		board [3][0] = new Cell("wall", "");
		board [3][1] = new Cell("gap", "");
		board [3][2] = new Cell("gap", "");
		board [3][3] = new Cell("gap", "");
		board [3][4] = new Cell("gap", "");
		board [3][5] = new Cell("gap", "");
		board [3][6] = new Cell("gap", "");
		board [3][7] = new Cell("wall", "");

		board [4][0] = new Cell("wall", "");
		board [4][1] = new Cell("wall", "");
		board [4][2] = new Cell("gap", "player");
		board [4][3] = new Cell("goal", "");
		board [4][4] = new Cell("wall", "");
		board [4][5] = new Cell("gap", "box");
		board [4][6] = new Cell("gap", "");
		board [4][7] = new Cell("wall", "");

		board [5][0] = new Cell("wall", "");
		board [5][1] = new Cell("gap", "");
		board [5][2] = new Cell("gap", "");
		board [5][3] = new Cell("gap", "");
		board [5][4] = new Cell("wall", "");
		board [5][5] = new Cell("gap", "");
		board [5][6] = new Cell("gap", "");
		board [5][7] = new Cell("wall", "");

		board [6][0] = new Cell("wall", "");
		board [6][1] = new Cell("gap", "");
		board [6][2] = new Cell("gap", "");
		board [6][3] = new Cell("gap", "");
		board [6][4] = new Cell("wall", "");
		board [6][5] = new Cell("wall", "");
		board [6][6] = new Cell("wall", "");
		board [6][7] = new Cell("wall", "");

		board [7][0] = new Cell("wall", "");
		board [7][1] = new Cell("wall", "");
		board [7][2] = new Cell("wall", "");
		board [7][3] = new Cell("wall", "");
		board [7][4] = new Cell("wall", "");
		board [7][5] = new Cell("gap", "");
		board [7][6] = new Cell("gap", "");
		board [7][7] = new Cell("gap", "");

		Game game = new Game(board);
		game.setHowManyBoxes(1);

		game.showWarehouse();
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.LEFT);
		game.move(SokobanMovements.UP);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.DOWN);
		game.move(SokobanMovements.RIGHT);
		game.move(SokobanMovements.UP);
		game.showWarehouse();
		System.out.println(game.boxesAtGoal);

	}
}