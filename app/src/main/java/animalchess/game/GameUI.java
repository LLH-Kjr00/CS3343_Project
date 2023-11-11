
package animalchess.game;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.board.Tiles.Tile;
import animalchess.exceptions.InvalidMovementException;

import animalchess.game.UIListener;

public class GameUI extends JFrame implements TileUtil {

	private Board board;
	

	private JPanel containerPanel;
	private BoardPanel boardPanel;
	private ConsolePanel consolePanel;
	
	static JTextArea logArea;

	static boolean is_Game_Pause = false; // Flag to track game's state of pausing
	static boolean is_Game_Start = false; // Flag to track game's state of pausing
	
	
	

	private static final GameUI instance = new GameUI(new Board());

	public static GameUI getInstance() {
		return instance;
	}

	// Board JungleChessBoard
	private GameUI(Board board) {
		this.board = board;
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(1000, 1000));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBackground(Color.decode("#F9CB9C"));
		setupPanels(frame);
		frame.setVisible(true);
	}

	public void gameMajorMsg(String content) {
		String result = new String();
		for (int i = 0; i < 62; i++) {
			result += "=";
		}
		logArea.append(result);
		logArea.append(content + " !\n");
		logArea.append("\n");

	}

	private void setupPanels(JFrame frame) {

		containerPanel = new JPanel();
		containerPanel.setLayout(new GridLayout(1, 2));

		
		consolePanel = new ConsolePanel(this);
		boardPanel = new BoardPanel (consolePanel, board);
		containerPanel.add(boardPanel);
		containerPanel.add(consolePanel);
		
		//rulesPanel = new JPanel();
		//rulesPanel.setVisible(false);
		//frame.add(rulesPanel);

		frame.add(containerPanel);
	}

	
	public void announce_Win() {
		if (Board.is_P1_Win) {
			logArea.append("Player 1 wins!\n");
		} else if (Board.is_P2_Win) {
			logArea.append("Player 2 wins!\n");
		} else {
			logArea.append("No ones wins!\n");
		}
	}

	
	
	public void restart_game () {
		boardPanel.reset_boardPanel();
		board.init();
	}

}
