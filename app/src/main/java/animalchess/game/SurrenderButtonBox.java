package animalchess.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

import animalchess.board.Board;

public class SurrenderButtonBox extends Box {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3345169898415376032L;
	private JButton P1_Surrender_button;
	private JButton P2_Surrender_button;
	private TimePanel timePanel;
	private ConsolePanel consolePanel;
	
	// Constructor
	public SurrenderButtonBox(int axis, TimePanel timePanel, ConsolePanel consolePanel) {
		super(axis);
		this.timePanel = timePanel;
		this.consolePanel = consolePanel;

		P1_Surrender_button = make_P1_Surrender();
		P2_Surrender_button = make_P2_Surrender();
		
		this.setVisible(false);
		this.add(P1_Surrender_button);
		this.add(P2_Surrender_button);

	}
	
	// Constructing the surrender button for Player 2
	// This function includes setting the actions to be done after pressing the button 
	// Will change Board's boolean to show Player 2 wins 
	// And notify consolePanel to end the game 
	private JButton make_P1_Surrender () {
		JButton P1_Surrender = new JButton("P1 Surrender");
		P1_Surrender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Board.is_P1_Win == false && Board.is_P1_Win == false) {
					timePanel.stop_P1_timer();
					timePanel.stop_P2_timer();
					Board.is_P2_Win = true;
					GameUI.logArea.append("Player 1 yields...\n");
					
					consolePanel.End_game();
				}
			}
		});
		return P1_Surrender;
	}
	
	// Constructing the surrender button for Player 1
	// This function includes setting the actions to be done after pressing the button 
	// Will change Board's boolean to show Player 2 wins 
	// And notify consolePanel to end the game 
	private JButton make_P2_Surrender () {
		JButton P2_Surrender = new JButton("P2 Surrender");
		P2_Surrender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Board.is_P1_Win == false && Board.is_P1_Win == false) {
					timePanel.stop_P1_timer();
					timePanel.stop_P2_timer();
					Board.is_P1_Win = true;
					GameUI.logArea.append("Player 2 yields...\n");
					consolePanel.End_game();
				}
			}

		});
		return P2_Surrender;
	}
	

}
