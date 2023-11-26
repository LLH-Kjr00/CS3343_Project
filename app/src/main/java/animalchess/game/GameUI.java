
package animalchess.game;

import javax.swing.*;
import java.awt.*;
import animalchess.board.Board;


public class GameUI extends JFrame implements TileUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6927800654387867583L;


	private Board board;
	

	private JPanel containerPanel;
	private BoardPanel boardPanel;
	private ConsolePanel consolePanel;
	
	public static JTextArea logArea;

	static boolean is_Game_Pause = false; // Flag to track game's state of pausing
	static boolean is_Game_Start = false; // Flag to track game's state of pausing
	
	
	
	// Singleton pattern for GameUI
	private static final GameUI instance = new GameUI(Board.getInstance());

	public static GameUI getInstance() {
		return instance;
	}
	
	// Constructor for GameUI
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

	// Sending important messages to logArea
	public void gameMajorMsg(String content) {
		String result = new String();
		for (int i = 0; i < 62; i++) {
			result += "=";
		}
		logArea.append(result);
		logArea.append(content + " !\n");
		logArea.append("\n");

	}
	
	// Building the UI
	// The UI consists of 2 parts: consolePanel for UI's functions 
	// and boardPanel for user's interaction with the game logic
	// containerPanel is just a container for storing consolePanel and boardPanel
	
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

	// Announcing who wins the game in logArea and set the game to be ended 
	public void announce_Win() {
		if (Board.is_P1_Win) {
			logArea.append("Player 1 wins!\n");
		} else if (Board.is_P2_Win) {
			logArea.append("Player 2 wins!\n");
		} 
		is_Game_Start = false;
	}
	
	// Refreshing UI when the game has started/ restarted
	public void restart_game () {
		boardPanel.reset_boardPanel();
		board.init_board();
		board.init_animalsCount();
    	gameMajorMsg("Game Start");
		is_Game_Start = true;

	}
	
	// Announcing whose turn is it now in logArea and notify Board to shift turn
	public void call_shift_turn_inUI() {
		gameMajorMsg("Turn Ended");
		board.change_turn();
		
	}
	
	

}
