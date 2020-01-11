package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.SokobanAction;

public class Movement {

	private SokobanAction action;
	private boolean playerMoved;
	private boolean boxMoved;

	public Movement(SokobanAction action, boolean playerMoved, boolean boxMoved) {
		this.action=action;
		this.playerMoved=playerMoved;
		this.boxMoved=boxMoved;
	}
	
	public Movement (Character character) {
		this.playerMoved=true;
		if(Character.isUpperCase(character)) 
			this.boxMoved = true;
		else 
			boxMoved = false;
		
		Character lcCharacter = Character.toLowerCase(character);
		switch(lcCharacter){
		case 'u':
			this.action = SokobanAction.UP;
			break;
		case 'd':
			this.action = SokobanAction.DOWN;
			break;
		case 'l':
			this.action = SokobanAction.LEFT;
			break;
		case 'r':
			this.action = SokobanAction.RIGHT;
			break;
		default: 
		}
	}

	public SokobanAction getAction() {
		return action;
	}

	public boolean isPlayerMoved() {
		return playerMoved;
	}

	public boolean isBoxMoved() {
		return boxMoved;
	}

	public Character toChar() {
		Character c;
		switch(this.getAction()) {
		case UP:
			c='u';
			break;
		case DOWN:
			c='d';
			break;
		case LEFT:
			c='l';
			break;
		case RIGHT:
			c='r';
			break;
		default: c=null;
		}
		if(this.boxMoved)
			c = Character.toUpperCase(c);
		return c;
	}

}
