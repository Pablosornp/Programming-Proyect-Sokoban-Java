package es.upm.pproject.sokoban.view;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageManager {

	private JFrame view;
	private static final String SOKOBAN = "Sokoban";
	
	public MessageManager(JFrame view) {
		this.view = view;
	}

	public void showLevelCompletedMessage(int levelNumber, int gameScore) {
		JOptionPane.showMessageDialog(view,"LEVEL "+levelNumber+" COMPLETED!\n\nGame Score: "+gameScore, SOKOBAN, 1);
	}

	public void showYouWonMessage(int gameScore) {
		JOptionPane.showMessageDialog(view,"YOU WON!\n\nGAME SCORE: "+ gameScore, SOKOBAN, 1);
	}

	public void showInvalidLevelMessage() {
		JOptionPane.showMessageDialog(view, "The map is not valid. Loading next level.", SOKOBAN, 0);
	}

	public String showSaveNameInputMessage() {
		return JOptionPane.showInputDialog(view, "Introduce a name for your saved game.",SOKOBAN, 1);
	}

	public void showSuccessfulSaveMessage() {
		JOptionPane.showMessageDialog(view, "Game saved successfully.", SOKOBAN, 1);
	}

	public void showFailedSaveMessage() {
		JOptionPane.showMessageDialog(view, "There was a problem trying to save the game.", SOKOBAN, 0);
	}

	public int showExitMessage() {
		return JOptionPane.showConfirmDialog(view, "Are you sure you want to leave?\n"
				+ "You may lose your progress.", SOKOBAN, JOptionPane.YES_NO_OPTION);
	}
	public String showSaveSelectionWindow() {
		String savePath = null;
		Path path = Paths.get("./saves/");
		JFileChooser fc = new JFileChooser(path.toAbsolutePath().toString());
		int returnVal = fc.showSaveDialog(view);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			savePath =  fc.getSelectedFile().toString();
		}
		return savePath;
	}
}
