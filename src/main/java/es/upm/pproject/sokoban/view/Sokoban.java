package es.upm.pproject.sokoban.view;

import java.awt.EventQueue;
import es.upm.pproject.sokoban.controller.SokobanController;
import es.upm.pproject.sokoban.model.SokobanModel;

public class Sokoban {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SokobanModel model = new SokobanModel();
			SokobanController controller = new SokobanController(null,model);
			GameView view = new GameView(controller);
			controller.setView(view);
		});
	}
}