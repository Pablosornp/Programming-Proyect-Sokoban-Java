package es.upm.pproject.sokoban.model;

public class Cell{

	private String type;
	private String content;

	public Cell(String type, String content) {
		this.type= type;
		this.content= content;
	}

	public boolean isGoal() {
		return this.type.equals("goal");
	}
	
	public boolean isWall() {
		return this.type.equals("wall");
	}
	
	public boolean isGap() {
		return this.type.equals("gap");
	}
	
	public boolean containsPlayer() {
		return this.content.equals("player");
	}
	
	public boolean containsBox() {
		return this.content.equals("box");
	}

	public boolean containsNothing() {
		return this.content.equals("");
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