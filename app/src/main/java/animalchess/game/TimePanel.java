package animalchess.game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import animalchess.board.Board;

public class TimePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6600268741435304573L;
	private Timer P1_Timer;
	private Timer P2_Timer;
	private int P1_Timer_val = 600 * 3; // count down for User 1
	private int P2_Timer_val = 600 * 3; // count down for User 2
	private JLabel P1_Timer_label;
	private JLabel P2_Timer_label;
	
	private ConsolePanel consolePanel;

	TimePanel(ConsolePanel consolePanel) {
		this.consolePanel = consolePanel;
		
		int P1_minutes = (P1_Timer_val % 3600) / 60;
		int P1_seconds = P1_Timer_val % 60;
		int P2_minutes = (P2_Timer_val % 3600) / 60;
		int P2_seconds = P2_Timer_val % 60;
		this.add(new JLabel("Player 1 Timer:")); // Label for user 1 timer

		P1_Timer_label = new JLabel(String.format("%02d:%02d", P1_minutes, P1_seconds));
		P1_Timer_label.setHorizontalAlignment(JLabel.CENTER);
		P1_Timer_label.setFont(new Font("Arial", Font.BOLD, 16));
		start_P1_Timer();
		this.add(P1_Timer_label);

		this.add(new JLabel("Player 2 Timer:")); // Label for user 2 timer
		P2_Timer_label = new JLabel(String.format("%02d:%02d", P2_minutes, P2_seconds));
		P2_Timer_label.setHorizontalAlignment(JLabel.CENTER);
		P2_Timer_label.setFont(new Font("Arial", Font.BOLD, 16));
		start_P2_Timer();
		this.add(P2_Timer_label);

	}
	private void start_P1_Timer() {
		P1_Timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				P1_Timer_val--;
				int minutes = (P1_Timer_val % 3600) / 60;
				int seconds = P1_Timer_val % 60;

				P1_Timer_label.setText(String.format("%02d:%02d", minutes, seconds));

				if (P1_Timer_val <= 0) {
					P1_Timer.stop();
					Board.is_P2_Win = true;
					GameUI.logArea.append("Player 1's time is up...\n");
					consolePanel.End_game();

				}

			}
		});
	}

	private void start_P2_Timer() {
		P2_Timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				P2_Timer_val--;
				int minutes = (P2_Timer_val % 3600) / 60;
				int seconds = P2_Timer_val % 60;

				P2_Timer_label.setText(String.format("%02d:%02d", minutes, seconds));

				if (P2_Timer_val <= 0) {
					P2_Timer.stop();
					Board.is_P1_Win = true;
					GameUI.logArea.append("Player 2's time is up...\n");
					consolePanel.End_game();
				}

			}
		});
	}
	public void start_P1_timer () {
		P1_Timer.start();
	}
	public void start_P2_timer () {
		P2_Timer.start();
	}
	public void restart_P1_timer () {
		P1_Timer.restart();
	}
	public void restart_P2_timer () {
		P2_Timer.restart();
	}
	public void stop_P1_timer () {
		P1_Timer.stop();
	}
	public void stop_P2_timer() {
		P2_Timer.stop();
	}
	public boolean is_P1_timer_Running () {
		return P1_Timer.isRunning();
	}
	public boolean is_P2_timer_Running () {
		return P2_Timer.isRunning();
	}
	
	public void reset_timer_value() {
		P1_Timer_val = 600 * 3;
		P2_Timer_val = 600 * 3;
	}
	public void change_Countdown_timer() {
		if (Board.is_P1_Turn == true) {
			P1_Timer.stop();
			P2_Timer.start();
			P1_Timer_val = 600 * 3;
		} else {
			P2_Timer.stop();
			P1_Timer.start();
			P2_Timer_val = 600 * 3;
		}
	}
}
