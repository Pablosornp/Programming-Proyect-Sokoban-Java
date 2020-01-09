package es.upm.pproject.sokoban.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.upm.pproject.sokoban.controller.SokobanController;
import es.upm.pproject.sokoban.controller.SokobanElements;
import es.upm.pproject.sokoban.controller.SokobanAction;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameView extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	private SokobanController controller;

	private JPanel gamePanel;
	private JPanel menuPanel;
	private JPanel infoPanel;
	private JPanel lowerButtonPanel;
	
	private JMenuBar jMenuBar;
	private JTextField textFieldGameScore;
	private JTextField textFieldLevelScore;
	private JLabel textFieldLevelName;
	private boolean keyboardEnabled;

	private MessageManager mm;

	private static final int SIZE = 32;
	private static final int NUMBER_OF_THEMES = 5;
	private String imagesPath;

	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.view.GameView");


	/**
	 * Create the application.
	 */
	public GameView(SokobanController controller) {
		this.setTitle("Sokoban");

		Path currentDir = Paths.get("./src/main/resources/images/");
		this.imagesPath = currentDir.toAbsolutePath().toString();
		this.controller=controller;
		this.mm = new MessageManager(this);

		initializeComponents();
		setResizable(false);
		setFrameIcon();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Initialize the frame components.
	 */
	private void initializeComponents() {
		initializeMenuPanel();
		initializeInfoPanel();	
		initializeMenuBar();
		initializeLowerButtonPanel();
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

	}


	private void initializeMenuBar() {
		this.jMenuBar = new JMenuBar();
		this.setJMenuBar(jMenuBar);		


		JMenu mnNewMenu = new JMenu("Menu");
		jMenuBar.add(mnNewMenu);

		JMenuItem newGameMenuItem = new JMenuItem("Start New Game", new ImageIcon(imagesPath+"/newgame.gif"));
		newGameMenuItem.addActionListener(event -> controller.onStart());
		mnNewMenu.add(newGameMenuItem);

		JMenuItem loadMenuItem = new JMenuItem("Load Game", new ImageIcon(imagesPath+"/load.gif"));
		loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		loadMenuItem.addActionListener(event -> controller.onLoad());
		mnNewMenu.add(loadMenuItem);

		JMenuItem saveMenuItem = new JMenuItem("Save Game", new ImageIcon(imagesPath+"/save.gif"));
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenuItem.addActionListener(event -> controller.onSave());
		mnNewMenu.add(saveMenuItem);
		
		JMenuItem controlsMenuItem = new JMenuItem("Controls & Rules", new ImageIcon(imagesPath+"/help.gif"));
		controlsMenuItem.addActionListener(event -> controller.onControls());
		mnNewMenu.add(controlsMenuItem);
		
		JMenuItem aboutMenuItem = new JMenuItem("About", new ImageIcon(imagesPath+"/about.gif"));
		aboutMenuItem.addActionListener(event -> controller.onAbout());
		mnNewMenu.add(aboutMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(event -> controller.onExit());
		mnNewMenu.add(exitMenuItem);
	}

	private void initializeLowerButtonPanel() {
		this.lowerButtonPanel = new JPanel();
		getContentPane().add(lowerButtonPanel, BorderLayout.SOUTH);

		JButton btnLowerUndo = new JButton("Undo move", new ImageIcon(this.imagesPath+"/undo.gif"));
		btnLowerUndo.addActionListener(event -> controller.onUndoMove());
		lowerButtonPanel.setLayout(new GridLayout(0, 2, 0, 0));
		lowerButtonPanel.add(btnLowerUndo);


		JButton btnLowerRestart = new JButton("Restart level", new ImageIcon(this.imagesPath+"/restart.gif"));
		btnLowerRestart.addActionListener(event -> controller.onRestart());
		lowerButtonPanel.add(btnLowerRestart);
	}

	private void setFrameIcon() {
		try {
			BufferedImage image = ImageIO.read(new File(imagesPath+"/icon.png"));
			this.setIconImage(image);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING,"Icon not found");
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
	
	public String getImagesPath() {
		return imagesPath;
	}

	public void setPanelsVisible(boolean visible) {
		this.infoPanel.setVisible(visible);
		this.jMenuBar.setVisible(visible);
		this.menuPanel.setVisible(false);
		this.lowerButtonPanel.setVisible(visible);
	}

	public void drawWelcomeScreen(){
		this.setPanelsVisible(false);
		if(gamePanel!=null)
			this.remove(gamePanel);

		initializeGamePanel();
		JLabel welcomeLabel = new JLabel("WELCOME TO SOKOBAN");
		Dimension dim = new Dimension(SIZE*8, SIZE*4);
		this.gamePanel.setPreferredSize(dim);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnPressStart = new JButton("Start New Game");
		btnPressStart.addActionListener(event -> controller.onStart());
		btnPressStart.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.textFieldGameScore.setText("");
		this.textFieldLevelName.setText("");
		this.textFieldLevelScore.setText("");
		gamePanel.add(welcomeLabel);
		gamePanel.add(btnPressStart);
		this.pack();
	}

	public void drawWarehousePanel(SokobanElements[][] elements, 
			SokobanAction lastAction, int levelNumber, int step) {
		this.setPanelsVisible(true);
		if(gamePanel!=null)
			this.remove(gamePanel);	

		this.gamePanel = new JPanel();

		int m = elements.length;
		int n = elements[0].length;
		int border=0;
		if(SIZE*n < 275)
			 border = (275 - SIZE*n)/2;
		Dimension dim = new Dimension(SIZE*n+border*2, SIZE*m+border*2);
		this.gamePanel.setBorder(new EmptyBorder(border, border, border, border));
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
