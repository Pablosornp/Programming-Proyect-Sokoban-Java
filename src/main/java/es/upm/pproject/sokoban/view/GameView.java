package es.upm.pproject.sokoban.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import es.upm.pproject.sokoban.controller.SokobanController;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GameView extends JFrame implements KeyListener {

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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JPanel gamePanel = new JPanel();
		gamePanel.setBorder(null);
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(null);

		gameTextArea = new JTextArea();
		gameTextArea.setBounds(0, 0, 107, 138);
		gameTextArea.setEditable(false);
		gamePanel.add(gameTextArea);

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

		JButton buttonUp = new JButton("UP");
		buttonUp.addActionListener(event -> controller.onMoveUp() );

		buttonsPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JButton blank1 = new JButton("");
		blank1.setEnabled(false);
		buttonsPanel.add(blank1);
		buttonsPanel.add(buttonUp);

		JButton buttonRight = new JButton("RIGHT");
		buttonRight.addActionListener(event -> controller.onMoveRight() );

		JButton blank2 = new JButton("");
		blank2.setEnabled(false);
		buttonsPanel.add(blank2);

		JButton buttonLeft = new JButton("LEFT");
		buttonLeft.addActionListener(event -> controller.onMoveLeft() );
		buttonsPanel.add(buttonLeft);

		JButton buttonDown = new JButton("DOWN");
		buttonDown.addActionListener(event -> controller.onMoveDown() );
		buttonsPanel.add(buttonDown);
		buttonsPanel.add(buttonRight);
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}
	
	public void enableKeyboard() {
		this.toFront();
		this.requestFocus();
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

	@Override
	public void keyPressed(KeyEvent arg0) {

		int key = arg0.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			this.controller.onMoveUp();
		}
		if (key == KeyEvent.VK_DOWN) {
			this.controller.onMoveDown();
		}
		if (key == KeyEvent.VK_RIGHT) {
			this.controller.onMoveRight();
		}
		if (key == KeyEvent.VK_LEFT) {
			this.controller.onMoveLeft();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
