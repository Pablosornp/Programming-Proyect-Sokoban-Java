package es.upm.pproject.sokoban.controller;

public class SokobanMovement {

	private SokobanAction action;
	private boolean playerMoved;
	private boolean boxMoved;

	public SokobanMovement(SokobanAction action, boolean playerMoved, boolean boxMoved) {
		this.action=action;
		this.playerMoved=playerMoved;
		this.boxMoved=boxMoved;
	}

	public SokobanAction getAction() {
		return action;
	}
	public void setMovement(SokobanAction action) {
		this.action = action;
	}

	public boolean isPlayerMoved() {
		return playerMoved;
	}

	public void setPlayerMoved(boolean playerMoved) {
		this.playerMoved = playerMoved;
	}

	public boolean isBoxMoved() {
		return boxMoved;
	}

	public void setBoxMoved(boolean boxMoved) {
		this.boxMoved = boxMoved;
	}

}
