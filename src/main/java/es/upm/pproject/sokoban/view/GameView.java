package es.upm.pproject.sokoban.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import es.upm.pproject.sokoban.controller.SokobanController;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.controller.SokobanAction;

import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Color;

public class GameView extends JFrame implements KeyListener {

	private SokobanController controller;

	private JPanel gamePanel;
	private JPanel menuPanel;
	private JPanel infoPanel;
	private JTextField textFieldGameScore;
	private JTextField textFieldLevelScore;
	private JLabel textFieldLevelName;
	private boolean keyboardEnabled;

	private static final int SIZE = 32;
	private String imagesPath;

	/**
	 * Create the application.
	 */
	public GameView(SokobanController controller) {
		this.setTitle("Sokoban");
		Path currentDir = Paths.get("./images/");
		this.imagesPath = currentDir.toAbsolutePath().toString()+"\\";
		setFrameIcon();	
		this.controller=controller;
		setResizable(false);
		initialize();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setFrameIcon() {
		try {
			BufferedImage image = ImageIO.read(new File(imagesPath+"player.png"));
			this.setIconImage(image);
		} catch (IOException e) {
			System.out.println("Icon not found");
		}
	}

	public void focusOnKeyboard() {
		this.toFront();
		this.requestFocus();
	}

	public void setKeyboardEnabled(boolean keyboardEnabled) {
		this.keyboardEnabled = keyboardEnabled;
	}

	public void setLevelScoreValue(String levelScore) {
		textFieldLevelScore.setText(levelScore);
	}

	public void setGameScoreValue(String gameScore) {
		textFieldGameScore.setText(gameScore);
	}

	public void setLevelName(String levelName) {
		textFieldLevelName.setText(levelName);
	}

	public void drawWelcomeScreen(){
		if(gamePanel!=null)
			this.remove(gamePanel);
		
		initializeGamePanel();
		JLabel welcomeLabel = new JLabel("WELCOME TO SOKOBAN");
		Dimension dim = new Dimension(SIZE*8, SIZE*4);
		this.gamePanel.setPreferredSize(dim);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JButton btnPressStart = new JButton("START NEW GAME");
		btnPressStart.addActionListener(event -> controller.onStart());
		btnPressStart.setHorizontalAlignment(SwingConstants.CENTER);
		this.textFieldGameScore.setText("");
		this.textFieldLevelName.setText("");
		this.textFieldLevelScore.setText("");
		gamePanel.add(welcomeLabel);
		gamePanel.add(btnPressStart);
		panelsVisible(false);
		
		this.setKeyboardEnabled(false);
		this.pack();
	}

	public void drawWarehousePanel(SokobanElements[][] elements) {
		if(gamePanel!=null)
			this.remove(gamePanel);	
		
		this.gamePanel = new JPanel();
		int m = elements.length;
		int n = elements[0].length;
		Dimension dim = new Dimension(SIZE*n, SIZE*m);
		this.gamePanel.setPreferredSize(dim);
		this.gamePanel.setLayout(new GridLayout(m,n));
		
		ImagePanel panel;
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				panel = new ImagePanel(elements[i][j], this.imagesPath);
				this.gamePanel.add(panel);
			}
		}
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
		this.pack();
	}

	public void panelsVisible(boolean visible) {
		this.menuPanel.setVisible(visible);
		this.infoPanel.setVisible(visible);
		this.pack();
	}

	/**
	 * Initialize the frame components.
	 */
	private void initialize() {

		initializeMenuPanel();
		initializeInfoPanel();
		drawWelcomeScreen();	
		initializeButtonPanel();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.pack();
	}


	private void initializeGamePanel() {
		this.gamePanel = new JPanel();
		gamePanel.setBackground(Color.LIGHT_GRAY);
		gamePanel.setLayout(new GridLayout(0, 1, 0, 0));
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
	}

	private void initializeInfoPanel() {

		//InfoPanel
		this.infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		this.getContentPane().add(infoPanel, BorderLayout.NORTH);

		////levelName panel
		JPanel levelNamePanel = new JPanel();
		textFieldLevelName = new JLabel();
		levelNamePanel.add(textFieldLevelName);

		infoPanel.add(levelNamePanel);

		////ScorePanel
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel gameScore = new JPanel();
		JLabel lblGameScore = new JLabel("Game score");
		lblGameScore.setHorizontalAlignment(SwingConstants.LEFT);
		gameScore.add(lblGameScore);
		textFieldGameScore = new JTextField();
		textFieldGameScore.setEditable(false);
		textFieldGameScore.setColumns(4);
		gameScore.add(textFieldGameScore);
		scorePanel.add(gameScore);

		JPanel levelScore = new JPanel();
		JLabel lblLevelScore = new JLabel("Level score");
		levelScore.add(lblLevelScore);
		textFieldLevelScore = new JTextField();
		textFieldLevelScore.setEditable(false);
		textFieldLevelScore.setColumns(4);
		levelScore.add(textFieldLevelScore);
		scorePanel.add(levelScore);

		infoPanel.add(scorePanel);


	}

	private void initializeMenuPanel() {
		this.menuPanel = new JPanel();
		this.getContentPane().add(menuPanel, BorderLayout.EAST);

		JButton btnNewGame = new JButton("Start new game");
		btnNewGame.addActionListener(event -> controller.onStart());

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
		btnSave.addActionListener(event -> controller.onSave());
		menuPanel.add(btnSave);

		JButton btnLoad = new JButton("Load Game");
		btnLoad.addActionListener(event -> controller.onLoad());
		menuPanel.add(btnLoad);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(event -> controller.onExit() );
		menuPanel.add(btnExit);
	}

	private void initializeButtonPanel() {
		JPanel buttonsPanel = new JPanel();
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

		JButton buttonUp = new JButton("UP");
		buttonUp.addActionListener(event -> controller.onMove(SokobanAction.UP) );

		buttonsPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JButton blank1 = new JButton("");
		blank1.setEnabled(false);
		buttonsPanel.add(blank1);
		buttonsPanel.add(buttonUp);

		JButton buttonRight = new JButton("RIGHT");
		buttonRight.addActionListener(event -> controller.onMove(SokobanAction.RIGHT) );

		JButton blank2 = new JButton("");
		blank2.setEnabled(false);
		buttonsPanel.add(blank2);

		JButton buttonLeft = new JButton("LEFT");
		buttonLeft.addActionListener(event -> controller.onMove(SokobanAction.LEFT) );
		buttonsPanel.add(buttonLeft);

		JButton buttonDown = new JButton("DOWN");
		buttonDown.addActionListener(event -> controller.onMove(SokobanAction.DOWN) );
		buttonsPanel.add(buttonDown);
		buttonsPanel.add(buttonRight);

		buttonsPanel.setVisible(false);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (keyboardEnabled) {
			int key = arg0.getKeyCode();

			if (key == KeyEvent.VK_UP) {
				this.controller.onMove(SokobanAction.UP);
			}
			if (key == KeyEvent.VK_DOWN) {
				this.controller.onMove(SokobanAction.DOWN);
			}
			if (key == KeyEvent.VK_RIGHT) {
				this.controller.onMove(SokobanAction.RIGHT);
			}
			if (key == KeyEvent.VK_LEFT) {
				this.controller.onMove(SokobanAction.LEFT);
			}
			if (key == KeyEvent.VK_R) {
				this.controller.onRestart();
			}
			if (key == KeyEvent.VK_U) {
				this.controller.onUndoMove();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		//Method not needed but necessary to override.
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		//Method not needed but necessary to override.
	}

}
