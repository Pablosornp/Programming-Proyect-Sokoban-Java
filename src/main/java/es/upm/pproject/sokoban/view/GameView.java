package es.upm.pproject.sokoban.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.SystemColor;
import java.awt.FlowLayout;

public class GameView extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	private SokobanController controller;

	private JPanel gamePanel;
	private JPanel menuPanel;
	private JPanel infoPanel;
	private JPanel undoRestartPanel;
	private JMenuBar menuBar;
	private JTextField textFieldGameScore;
	private JTextField textFieldLevelScore;
	private JLabel textFieldLevelName;
	private boolean keyboardEnabled;

	private MessageManager mm;

	private static final int SIZE = 32;
	private static final int NUMBER_OF_THEMES = 5;
	private String imagesPath;

	/**
	 * Create the application.
	 */
	public GameView(SokobanController controller) {
		this.setTitle("Sokoban");

		Path currentDir = Paths.get("./images/");
		this.imagesPath = currentDir.toAbsolutePath().toString();
		this.controller=controller;
		this.mm = new MessageManager(this);

		initializeComponents();
		this.setVisible(true);
		setResizable(false);
		setFrameIcon();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Initialize the frame components.
	 */
	private void initializeComponents() {
		initializeMenuPanel();
		initializeInfoPanel();	
		initializeButtonPanel();
		initializeMenuBar();
		initializeUndoRestart();
		drawWelcomeScreen();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}


	private void initializeGamePanel() {
		this.gamePanel = new JPanel();
		gamePanel.setBackground(SystemColor.window);
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
		FlowLayout flowLayout = (FlowLayout) levelNamePanel.getLayout();
		flowLayout.setHgap(1);
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
		btnExit.addActionListener(event -> controller.onExit());
		menuPanel.add(btnExit);
		
		menuPanel.setVisible(false);
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

	private void initializeMenuBar() {
		this.menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);		

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem newGameMenuItem = new JMenuItem("Start New Game", new ImageIcon("images/newgame.gif"));
		newGameMenuItem.addActionListener(event -> controller.onStart());
		mnNewMenu.add(newGameMenuItem);

		JMenuItem loadMenuItem = new JMenuItem("Load Game", new ImageIcon("images/load.gif"));
		loadMenuItem.addActionListener(event -> controller.onLoad());
		mnNewMenu.add(loadMenuItem);

		JMenuItem saveMenuItem = new JMenuItem("Save Game", new ImageIcon("images/save.gif"));
		saveMenuItem.addActionListener(event -> controller.onSave());
		mnNewMenu.add(saveMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(event -> controller.onExit());
		mnNewMenu.add(exitMenuItem);
	}
	
	private void initializeUndoRestart() {
		this.undoRestartPanel = new JPanel();
		getContentPane().add(undoRestartPanel, BorderLayout.SOUTH);
		
		JButton btnLowerUndo = new JButton("Undo movement", new ImageIcon("images/undo.gif"));
		btnLowerUndo.addActionListener(event -> controller.onUndoMove());
		undoRestartPanel.setLayout(new GridLayout(0, 2, 0, 0));
		undoRestartPanel.add(btnLowerUndo);
		
		JButton btnLowerRestart = new JButton("Restart level", new ImageIcon("images/restart2.gif"));
		btnLowerRestart.addActionListener(event -> controller.onRestart());
		undoRestartPanel.add(btnLowerRestart);
		this.setKeyboardEnabled(false);
		panelsVisible(false);
	}

	private void setFrameIcon() {
		try {
			BufferedImage image = ImageIO.read(new File(imagesPath+"\\icon.png"));
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

	public MessageManager getMm() {
		return mm;
	}

	public void panelsVisible(boolean visible) {
		this.infoPanel.setVisible(visible);
		this.menuBar.setVisible(visible);
		this.menuPanel.setVisible(visible);
		this.undoRestartPanel.setVisible(visible);
		this.pack();
	}

	public void drawWelcomeScreen(){
		if(gamePanel!=null)
			this.remove(gamePanel);

		initializeGamePanel();
		JLabel welcomeLabel = new JLabel("WELCOME TO SOKOBAN");
		Dimension dim = new Dimension(SIZE*8, SIZE*4);
		this.gamePanel.setPreferredSize(new Dimension(256, 128));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JButton btnPressStart = new JButton("START NEW GAME");
		btnPressStart.addActionListener(event -> controller.onStart());
		btnPressStart.setHorizontalAlignment(SwingConstants.CENTER);
		this.textFieldGameScore.setText("");
		this.textFieldLevelName.setText("");
		this.textFieldLevelScore.setText("");
		gamePanel.add(welcomeLabel);
		gamePanel.add(btnPressStart);

	}

	public void drawWarehousePanel(SokobanElements[][] elements, 
			SokobanAction lastAction, int levelNumber, int step) {
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
				panel = new ImagePanel(intToThemePath(levelNumber),elements[i][j], lastAction, step);
				this.gamePanel.add(panel);
			}
		}
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
		this.pack();
	}

	private String intToThemePath(int levelNumber) {
		String path ;
		switch(levelNumber % GameView.NUMBER_OF_THEMES) {
		case 0 :
			path = this.imagesPath+"\\volcano";
			break;
		case 2 :
			path = this.imagesPath+"\\desert";
			break;
		case 3 :
			path = this.imagesPath+"\\garden";
			break;
		case 4 :
			path = this.imagesPath+"\\ocean";
			break;
		default :
			path = this.imagesPath+"\\factory";
			break;
		}
		return path;
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
