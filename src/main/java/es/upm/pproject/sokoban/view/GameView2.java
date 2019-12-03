package es.upm.pproject.sokoban.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Color;

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

public class GameView2 extends JFrame{

	private SokobanController controller;
	
	private JTextField textFieldGameScore;
	private JTextField textFieldLevelScore;
	


	/**
	 * Create the application.
	 */
	public GameView2(SokobanController controller) {
		this.controller=controller;
		initialize();
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public GameView2() {
		initialize();
		this.pack();
		this.setVisible(true);
	}

	private void initialize() {
		

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setForeground(Color.DARK_GRAY);
		menuBar_1.setBackground(Color.GRAY);
		this.setJMenuBar(menuBar_1);		

		JMenu mnNewMenu = new JMenu("Options");
		mnNewMenu.setBackground(Color.GRAY);
		menuBar_1.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New Game");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Load");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem_3);
		
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
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		gamePanel.add(panel);
		
		JPanel menuPanel = new JPanel();
		this.getContentPane().add(menuPanel, BorderLayout.EAST);
		
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView2 window = new GameView2();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
