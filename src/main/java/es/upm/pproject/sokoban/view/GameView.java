package es.upm.pproject.sokoban.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

import es.upm.pproject.sokoban.controller.SokobanController;

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
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Button;

public class GameView extends JFrame{

	private SokobanController controller;
	
	private JTextField textFieldGameScore;
	private JTextField textFieldLevelScore;
	private JTextArea gameTextArea;


	/**
	 * Create the application.
	 */
	public GameView(SokobanController controller) {
		this.controller=controller;
		initialize();
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel scorePanel = new JPanel();
		this.getContentPane().add(scorePanel, BorderLayout.NORTH);
		scorePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel game_score = new JPanel();
		scorePanel.add(game_score);
		
		JLabel lblGameScore = new JLabel("Game score");
		lblGameScore.setHorizontalAlignment(SwingConstants.LEFT);
		game_score.add(lblGameScore);
		
		textFieldGameScore = new JTextField();
		textFieldGameScore.setEditable(false);
		game_score.add(textFieldGameScore);
		textFieldGameScore.setColumns(4);
		
		JPanel level_score = new JPanel();
		scorePanel.add(level_score);
		
		JLabel lblNewLabel = new JLabel("Level score");
		level_score.add(lblNewLabel);
		
		textFieldLevelScore = new JTextField();
		textFieldLevelScore.setEditable(false);
		level_score.add(textFieldLevelScore);
		textFieldLevelScore.setColumns(4);
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBorder(null);
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(null);
		
		gameTextArea = new JTextArea();
		gameTextArea.setBounds(0, 0, 107, 138);
		gameTextArea.setEditable(false);
		gamePanel.add(gameTextArea);
		
		JPanel menuPanel = new JPanel();
		this.getContentPane().add(menuPanel, BorderLayout.EAST);
		
		JButton btnNewGame = new JButton("Start new game");
		
		JButton btnRestartLevel = new JButton("Restart level");
		btnRestartLevel.addActionListener(event -> controller.onRestart());
		menuPanel.add(btnRestartLevel);
		
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
		
		JPanel buttonsPanel = new JPanel();
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton buttonUp = new JButton("UP");
		buttonUp.addActionListener(event -> controller.onMoveUp() );
		buttonsPanel.add(buttonUp);
		
		JButton buttonDown = new JButton("DOWN");
		buttonDown.addActionListener(event -> controller.onMoveDown() );
		buttonsPanel.add(buttonDown);
		
		JButton buttonRight = new JButton("RIGHT");
		buttonRight.addActionListener(event -> controller.onMoveRight() );
		buttonsPanel.add(buttonRight);
		
		JButton buttonLeft = new JButton("LEFT");
		buttonLeft.addActionListener(event -> controller.onMoveLeft() );
		buttonsPanel.add(buttonLeft);
		
	}
	
	public void setLevelScoreValue(String levelScore) {
		textFieldLevelScore.setText(levelScore);
	}
	
	public void setGameScoreValue(String gameScore) {
		textFieldGameScore.setText(gameScore);
	}
	
	public void drawWarehouse(String board) {
		gameTextArea.setText(board);
	}

	
}
