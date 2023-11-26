package animalchess.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import animalchess.board.Board;

public class ButtonPanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 525240945275831717L;
	private JButton Resume_button;
	private PauseButtonBox pauseButtons;
	
	ButtonPanel (TimePanel timePanel) {
		
		pauseButtons = new PauseButtonBox(2, timePanel);
		// pauseButtons.setVisible(false);
		Resume_button = new JButton("Resume");
		Resume_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Board.is_P1_Turn == true && timePanel.is_P1_timer_Running() == false) {
					timePanel.restart_P1_timer();
					GameUI.logArea.append("Game Resumed!\n");
					
				} else if (Board.is_P1_Turn == false && timePanel.is_P2_timer_Running() == false) {
					timePanel.restart_P2_timer();
					GameUI.logArea.append("Game Resumed!\n");
					
				} else {
					GameUI.logArea.append("Action ignored: The game has not paused!\n");
				}
				pauseButtons.reset();
				GameUI.is_Game_Pause = false;
				
			}
		});
		//Resume_button.setVisible(false);
		this.setVisible(false);
		this.setBackground(Color.decode("#F9CB9C"));
		this.add(pauseButtons);
		this.add(Resume_button);

	}
	public void reset_PauseState() {
		pauseButtons.reset();
	}
}
