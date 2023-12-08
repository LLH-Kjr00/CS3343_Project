package animalchess.game;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePanel extends JPanel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1991948255196279333L;
	private TimePanel timePanel;
    private ButtonPanel buttonPanel;
    private TitlePanel titlePanel;
    private SurrenderButtonBox surrenderButtons;

    private GameUI gameUI;
    // constructor
    // create TimePanel, ButtonPanel, TitlePanel, and SurrenderButtonBox
    // adding them into the ConsolePanel
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
   // called when the game ended 
   // hiding surrenderButtons and buttonPanel
   // showing the gameStart button in titlePanel as the game has ended 
   // stopping the timer for both player 
   // call the GameUI to announce who wins the game
	public void End_game () {
    	surrenderButtons.setVisible(false);
    	buttonPanel.setVisible(false);
    	titlePanel.enable_Gamestart();
    	timePanel.stop_P1_timer();
    	timePanel.stop_P2_timer();
    	gameUI.announce_Win();
    }
	
	// called when the game ended 
	   // showing surrenderButtons and buttonPanel
	   // hiding the gameStart button in titlePanel as the game has started 
	   // start the timer for player 1 according to the tradition rules of the game
	   // call the GameUI to start the game and annouce the message in the logging area
	// reset the pausing state for player 1 and player 2 
    public void Start_game () {
    	surrenderButtons.setVisible(true);
    	buttonPanel.setVisible(true);
    	titlePanel.disable_Gamestart();
    	timePanel.reset_timer_value();
    	gameUI.restart_game();
    	buttonPanel.reset_PauseState();
    }
    // call timePanel to change whose timer to count down next 
    // announce the shifting turn message in gameUI 
    public void Change_turn () {
    	timePanel.change_Countdown_timer();
    	gameUI.call_shift_turn_inUI();
    }
}
