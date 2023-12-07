package animalchess.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import animalchess.board.Board;

public class TitlePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2021740742418127345L;
	private JButton gameStart;
	
    
    
	// constructor
	// create the title of the game and a button for starting the  game 
	// default starting player is hard-coded to be player 1 
	// but can be changed whenever the needs for freely choosing who will start the game is enabled is present
	TitlePanel(ConsolePanel consolePanel, TimePanel timePanel) {
		
		
		
		this.setLayout(new GridLayout(4, 0));
		JLabel title = new JLabel("Shou Dou Qi (The Jungle Chess)");
		title.setFont(new Font("Verdana", 1, 20));
		this.add(title);
		this.add(new JLabel(" "));
		gameStart = new JButton("Game Start!");
		gameStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameUI.logArea.setText("");
				Board.is_P1_Turn = true; 
				gameStart.setVisible(false);
				//buttonPanel.setVisible(true);
				if (Board.is_P1_Turn) {
					timePanel.start_P1_timer(); // Start player 1's timer
				} else {
					timePanel.start_P2_timer(); // Start player 2's timer
				}
				Board.is_P1_Win = false;
				Board.is_P2_Win = false;
				consolePanel.Start_game();
				timePanel.reset_timer_value();
			}
		});
		this.add(gameStart);
		this.setBackground(Color.decode("#F9CB9C"));
	}
	public void enable_Gamestart() {
		gameStart.setVisible(true);
	}
	public void disable_Gamestart() {
		gameStart.setVisible(false);
	}
}
