package animalchess.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

public class PauseButtonBox extends Box {
	private JButton P1_Pause_button;
	private JButton P2_Pause_button;
	private boolean P1_Pause = false;
	private boolean P2_Pause = false;
	private TimePanel timePanel;
	
	public PauseButtonBox(int axis, TimePanel timePanel) {
		super(axis);
		this.timePanel = timePanel;
		
		P1_Pause_button = make_P1_Pause();
		P2_Pause_button = make_P2_Pause();
		
		this.add(P1_Pause_button);
		this.add(P2_Pause_button);
	}
	private JButton make_P1_Pause() {
		JButton P1_Pause_button = new JButton("P1 Pause");
		P1_Pause_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				P1_Pause = true;
				if (GameUI.is_Game_Pause == false) {
					if (P2_Pause == false) {
						GameUI.logArea.append("Player 1 wants to pause the game!\n");
						GameUI.logArea.append("Need Player 2's agreement to pause the game!\n");
					} else if (P1_Pause == true && P2_Pause == true) {
						if (GameUI.is_P1_Turn == true) {
							timePanel.stop_P1_timer();
						} else {
							timePanel.stop_P2_timer();
						}
						GameUI.is_Game_Pause = true;
						GameUI.logArea.append("Game Paused!\n");
					}
				} else {
					GameUI.logArea.append("Action ignored: The game has paused already!\n");
				}
			}
		});
		return P1_Pause_button;
	}
	
	private JButton make_P2_Pause() {
		JButton P2_Pause_button = new JButton("P2 Pause");
		P2_Pause_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				P2_Pause = true;
				if (GameUI.is_Game_Pause == false) {
					if (P1_Pause == false) {
						GameUI.logArea.append("Player 2 wants to pause the game!\n");
						GameUI.logArea.append("Need Player 1's agreement to pause the game!\n");
					} else if (P1_Pause == true && P2_Pause == true) {
						if (GameUI.is_P1_Turn == true) {
							timePanel.stop_P1_timer();
						} else {
							timePanel.stop_P2_timer();
						}
						GameUI.is_Game_Pause = true;
						GameUI.logArea.append("Game Paused!\n");
					}
				} else {
					GameUI.logArea.append("Action ignored: The game has paused already!\n");
				}
			}
		});
		return P2_Pause_button;
	}
	
	public void reset_Pause_state() {
		P1_Pause = false;
		P2_Pause = false;
	}
	

}
