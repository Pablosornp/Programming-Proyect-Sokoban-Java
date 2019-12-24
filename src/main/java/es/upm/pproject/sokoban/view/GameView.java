package es.upm.pproject.sokoban.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import es.upm.pproject.sokoban.controller.SokobanController;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.controller.SokobanMovements;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.FlowLayout;

public class GameView extends JFrame implements KeyListener {

	private SokobanController controller;

	JPanel gamePanel;
	private JTextField textFieldGameScore;
	private JTextField textFieldLevelScore;


	/**
	 * Create the application.
	 */
	public GameView(SokobanController controller) {
		setResizable(false);
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

		this.gamePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) gamePanel.getLayout();
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);

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
		btnNewGame.addActionListener(event -> controller.onRestart());

		JButton btnRestartLevel = new JButton("Restart level");
		btnRestartLevel.addActionListener(event -> controller.onRestart());
		menuPanel.add(btnRestartLevel);

		menuPanel.setLayout(new GridLayout(0, 1, 0, 0));
		menuPanel.add(btnNewGame);
		menuPanel.add(btnRestartLevel);

		JButton btnUndo = new JButton("Undo movement");
		btnUndo.addActionListener(event -> controller.onUndoMove());
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
		btnExit.addActionListener(event -> controller.onExit() );
		menuPanel.add(btnExit);

		JPanel buttonsPanel = new JPanel();
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

		JButton buttonUp = new JButton("UP");
		buttonUp.addActionListener(event -> controller.onMove(SokobanMovements.UP) );

		buttonsPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JButton blank1 = new JButton("");
		blank1.setEnabled(false);
		buttonsPanel.add(blank1);
		buttonsPanel.add(buttonUp);

		JButton buttonRight = new JButton("RIGHT");
		buttonRight.addActionListener(event -> controller.onMove(SokobanMovements.RIGHT) );

		JButton blank2 = new JButton("");
		blank2.setEnabled(false);
		buttonsPanel.add(blank2);

		JButton buttonLeft = new JButton("LEFT");
		buttonLeft.addActionListener(event -> controller.onMove(SokobanMovements.LEFT) );
		buttonsPanel.add(buttonLeft);

		JButton buttonDown = new JButton("DOWN");
		buttonDown.addActionListener(event -> controller.onMove(SokobanMovements.DOWN) );
		buttonsPanel.add(buttonDown);
		buttonsPanel.add(buttonRight);

		buttonsPanel.setVisible(false);

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

	public void drawWarehousePanel(SokobanElements[][] elements) {
		int m = elements.length;
		int n = elements[0].length;

		this.remove(gamePanel);
		this.gamePanel = new JPanel();
		Dimension dim = new Dimension(64*m, 64*m);
		this.gamePanel.setPreferredSize(dim);
		this.gamePanel.setLayout(new GridLayout(m,n));
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);

		ImagePanel panel;
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				panel = new ImagePanel(elements[i][j]);
				panel.setSize(64, 64);
				this.gamePanel.add(panel);
			}
		}
		this.pack();
	}


	@Override
	public void keyPressed(KeyEvent arg0) {

		int key = arg0.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			this.controller.onMove(SokobanMovements.UP);
		}
		if (key == KeyEvent.VK_DOWN) {
			this.controller.onMove(SokobanMovements.DOWN);
		}
		if (key == KeyEvent.VK_RIGHT) {
			this.controller.onMove(SokobanMovements.RIGHT);
		}
		if (key == KeyEvent.VK_LEFT) {
			this.controller.onMove(SokobanMovements.LEFT);
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
