package es.upm.pproject.sokoban.model;

public class Cell{

	private String type;
	private String content;

	public Cell(String type, String content) {
		this.type= type;
		this.content= content;
	}

	public boolean isEmpty() {
		return this.content.equals("");
	}
	
	public boolean isGoal() {
		return this.content.equals("goal");
	}
	
	public boolean isWall() {
		return this.content.equals("wall");
	}
	
	public boolean isPlayer() {
		return this.content.equals("player");
	}
	
	public boolean isBox() {
		return this.content.equals("box");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}