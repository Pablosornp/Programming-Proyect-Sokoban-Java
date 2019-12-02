package es.upm.pproject.sokoban.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView window = new GameView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		JPanel scorePanel = new JPanel();
		frame.getContentPane().add(scorePanel, BorderLayout.NORTH);
		scorePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel game_score = new JPanel();
		scorePanel.add(game_score);
		
		JLabel lblGameScore = new JLabel("Game score");
		lblGameScore.setHorizontalAlignment(SwingConstants.LEFT);
		game_score.add(lblGameScore);
		
		textField = new JTextField();
		game_score.add(textField);
		textField.setColumns(4);
		
		JPanel level_score = new JPanel();
		scorePanel.add(level_score);
		
		JLabel lblNewLabel = new JLabel("Level score");
		level_score.add(lblNewLabel);
		
		textField_1 = new JTextField();
		level_score.add(textField_1);
		textField_1.setColumns(4);
		
		JPanel gamePanel = new JPanel();
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		gamePanel.add(panel);
		
		JPanel menuPanel = new JPanel();
		frame.getContentPane().add(menuPanel, BorderLayout.EAST);
		
		JButton btnNewGame = new JButton("Start new game");
		
		JButton btnRestartLevel = new JButton("Restart level");
		menuPanel.setLayout(new GridLayout(0, 1, 0, 0));
		menuPanel.add(btnNewGame);
		menuPanel.add(btnRestartLevel);
		
		JButton btnUndo = new JButton("Undo movement");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuPanel.add(btnUndo);
		
		JButton btnSave = new JButton("Save Game");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuPanel.add(btnSave);
		
		JButton btnSaveGame = new JButton("Load Game");
		menuPanel.add(btnSaveGame);
		
		JButton btnExit = new JButton("Exit");
		menuPanel.add(btnExit);
	}

}
