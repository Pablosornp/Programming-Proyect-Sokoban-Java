package es.upm.pproject.sokoban.view;

import java.io.File;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MessageManager {

	private JFrame view;
	private static final String SOKOBAN = "Sokoban";
	
	
	public MessageManager(JFrame view) {
		this.view = view;
	}
	
	public void showControlsAndRules() {
		JOptionPane.showMessageDialog(view, "<html>GOAL:<br/>The goal of the puzzle is to push all of the boxes onto the goals.<br/><br/>RULES:<br/>· Only one box can be pushed at a time.<br/>· A box cannot be pulled.<br/>· The player cannot walk through boxes or walls.<br/>· The puzzle is solved when all boxes are on the goals.<br/><br/>CONTROLS:<br/>· Arrow keys for movement<br/>· [U] for undo move<br/>· [R] for restart level</html>", SOKOBAN , 1, new ImageIcon("images/icon_help.png"));
	}

	public void showLevelCompletedMessage(int levelNumber, int gameScore) {
		JOptionPane.showMessageDialog(view,"LEVEL "+levelNumber+" COMPLETED!\n\nGame Score: "+gameScore, SOKOBAN, 1, new ImageIcon("images/icon_level.png"));
	}

	public void showYouWonMessage(int gameScore) {
		JOptionPane.showMessageDialog(view,"WINNER!\nYou completed the game!\nGAME SCORE: "+ gameScore, SOKOBAN, 1, new ImageIcon("images/icon_winner.png"));
	}
	
	public void showSuccessfulSaveMessage() {
		JOptionPane.showMessageDialog(view, "Game saved successfully.", SOKOBAN, 1, new ImageIcon("images/icon_ok.png"));
	}
	
	public Object showSaveNameInputMessage() {
		return JOptionPane.showInputDialog(view, "Introduce a name for your saved game.",SOKOBAN, 1, new ImageIcon("images/icon_save.png"), null, null);
	}

	public void showInvalidLevelMessage() {
		JOptionPane.showMessageDialog(view, "The map is not valid. Loading next level.", SOKOBAN, 0, new ImageIcon("images/icon_fail.png"));
	}

	public void showFailedSaveMessage() {
		JOptionPane.showMessageDialog(view, "There was a problem trying to save the game.", SOKOBAN, 0, new ImageIcon("images/icon_fail.png"));
	}
	
	public void showFailedLoadMessage() {
		JOptionPane.showMessageDialog(view, "There was a problem trying to load the game.", SOKOBAN, 0, new ImageIcon("images/icon_fail.png"));
	}

	public int showExitMessage() {
		return JOptionPane.showConfirmDialog(view, "Are you sure you want to leave?\n"
				+ "You may lose your progress.", SOKOBAN, JOptionPane.YES_NO_OPTION, 0, new ImageIcon("images/icon_q2.png"));
	}
	public String showSaveSelectionWindow() {
		String savePath = null;
		String savesFolderPath = Paths.get("./saves/").toAbsolutePath().toString();
		File directory = new File(savesFolderPath);
		if (!directory.exists()){
			savesFolderPath="";
		}
		JFileChooser fc = new JFileChooser(savesFolderPath);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Save files (.sav)", "sav");
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(filter);
		int returnVal = fc.showOpenDialog(view);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			savePath =  fc.getSelectedFile().toString();
		}
		else if(returnVal == JFileChooser.CANCEL_OPTION) {
			savePath = "cancel";
		}
		return savePath;
	}
}
