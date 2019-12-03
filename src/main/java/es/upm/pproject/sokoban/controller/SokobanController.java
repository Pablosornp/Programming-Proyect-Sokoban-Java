package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.model.SokobanModel;
import es.upm.pproject.sokoban.view.GameView;

public class SokobanController {


		private GameView view; 
		private SokobanModel model;

		public SokobanController(GameView view, SokobanModel model) {
			this.view = view;
			this.model = model;
		}
		
		public void setView(GameView view) {
			this.view = view;
		}
}
