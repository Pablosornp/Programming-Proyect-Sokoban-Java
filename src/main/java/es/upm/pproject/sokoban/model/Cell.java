package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanElements;

public class Cell{

	private SokobanElements type;
	private SokobanElements content;

	public Cell(SokobanElements type, SokobanElements content) {
		this.type= type;
		this.content= content;
	}
	
	public Cell(char character) {
		switch(character){
		case '#':
			this.type=SokobanElements.GAP;
			this.content=SokobanElements.BOX;	
			break;
		case '*':
			this.type=SokobanElements.GOAL;
			this.content=SokobanElements.NONE;
			break;
		case '+':
			this.type=SokobanElements.WALL;
			this.content=SokobanElements.NONE;
			break;
		case 'W':
			this.type=SokobanElements.GAP;
			this.content=SokobanElements.PLAYER;			
			break;
		case ' ':
			this.type=SokobanElements.GAP;
			this.content=SokobanElements.NONE;
			break;
		case '@':
			this.type=SokobanElements.GOAL;
			this.content=SokobanElements.PLAYER;
			break;
		case '$':
			this.type=SokobanElements.GOAL;
			this.content=SokobanElements.BOX;
			break;
		default: 
		}
	}
	
	public boolean isGoal() {
		return this.type.equals(SokobanElements.GOAL);
	}
	
	public boolean isWall() {
		return this.type.equals(SokobanElements.WALL);
	}
	
	public boolean isGap() {
		return this.type.equals(SokobanElements.GAP);
	}
	
	public boolean containsPlayer() {
		return this.content.equals(SokobanElements.PLAYER);
	}
	
	public boolean containsBox() {
		return this.content.equals(SokobanElements.BOX);
	}

	public boolean containsNothing() {
		return this.content.equals(SokobanElements.NONE);
	}
	
	public SokobanElements getType() {
		return type;
	}

	public void setType(SokobanElements type) {
		this.type = type;
	}

	public SokobanElements getContent() {
		return content;
	}

	public void setContent(SokobanElements content) {
		this.content = content;
	}
	
	public Character toChar() {
		Character c = null;
		if(this.isGoal()){
			if(this.containsNothing())
				c='*';
			else if(this.containsPlayer())
				c='@';
			else if(this.containsBox())
				c='$';	
		}else if(this.isGap()){
			if(this.containsNothing())
				c=' ';
			else if(this.containsPlayer())
				c='W';
			else if(this.containsBox())
				c='#';	
		}else {
			c='+';	
		}
		return c;
	}

	
}