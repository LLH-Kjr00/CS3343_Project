package animalchess.game;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import animalchess.board.Board;

public class ConsolePanel extends JPanel {
    
    private TimePanel timePanel;
    private ButtonPanel buttonPanel;
    private TitlePanel titlePanel;
    private SurrenderButtonBox surrenderButtons;

    private GameUI gameUI;
    
    public ConsolePanel(GameUI gameUI) {
    	this.gameUI = gameUI;
        this.setBackground(Color.decode("#F9CB9C"));
        this.setLayout(new FlowLayout());

        timePanel = new TimePanel(this);
        buttonPanel = new ButtonPanel(timePanel);
        titlePanel = new TitlePanel(this, timePanel);
        surrenderButtons = new SurrenderButtonBox(2, timePanel, this);


        GameUI.logArea = new JTextArea(25, 40);
        GameUI.logArea.setLineWrap(true);
        GameUI.logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(GameUI.logArea);
        
        this.add(titlePanel);
        this.add(surrenderButtons);
        this.add(timePanel);
        this.add(scrollPane);
        this.add(buttonPanel);

    }
   
	public void End_game () {
    	surrenderButtons.setVisible(false);
    	buttonPanel.setVisible(false);
    	titlePanel.enable_Gamestart();
    	timePanel.stop_P1_timer();
    	timePanel.stop_P2_timer();
    	gameUI.announce_Win();
    }
    public void Start_game () {
    	surrenderButtons.setVisible(true);
    	buttonPanel.setVisible(true);
    	titlePanel.disable_Gamestart();
    	gameUI.restart_game();
    }
    public void Change_turn () {
    	timePanel.change_Countdown_timer();
    	gameUI.shift_turn();
    }
}
