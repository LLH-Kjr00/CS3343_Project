
package animalchess.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import animalchess.board.Board;

public class GameUI extends JFrame implements TileUtil {

    private final String[] verticalAxis = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
    private final String[] horizontalAxis = { "1", "2", "3", "4", "5", "6", "7" };
    int rows = verticalAxis.length;
    int cols = horizontalAxis.length;

    private JPanel containerPanel;
    private JPanel boardPanel;
    private JPanel consolePanel;
    private JPanel rulesPanel;
    private JPanel buttonPanel;
    private JPanel timePanel;
    private JPanel titlePanel;
    
    
    private JButton gameStart;
    private JTextArea logArea;

    
    // private ArrayList<Animal> animalList = new ArrayList();
    private Board board;

    private String systemMsg = new String();

    private JLabel P1_Timer_label;
    private JLabel P2_Timer_label;
    private Timer P1_Timer;
    private Timer P2_Timer;
    private int P1_Timer_val = 600 * 3; // count down for User 1
    private int P2_Timer_val = 600 * 3; // count down for User 2
    private boolean is_P1_Turn = false; // Flag to track player 1's turn
    
    private boolean P1_Pause = false;
    private boolean P2_Pause = false;
    private JButton P1_Pause_button;
    private JButton P2_Pause_button;
    private JButton Resume_button;
    private Box pauseButtons;
   

    private int tileSize = 100;
    private int borderWidth = 3;

    // Board JungleChessBoard
    public GameUI() {

        JFrame frame = new JFrame();
        frame.setSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setupPanels(frame);
        frame.setVisible(true);
    }
    
    private void gameStartMsg() {
        String result = new String();
        for (int i = 0; i < 62; i++) {
            result += "=";
        }
        logArea.append(result);
        logArea.append("Game Start\n");
        logArea.append(result);
    }
    
    public void drawAnimal(Graphics g) {
        String imgSrc = "red_cat.png";
        InputStream is = this.getClass().getResourceAsStream(imgSrc);
        int x = 100;
        int y = 100;
        try {
            Image image = ImageIO.read(is);
            g.drawImage(image, x, y, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    public void addAnimals() {
        // animalList.add();
    }

    public int getTileSize() {
        return tileSize;
    }


    private void setupPanels(JFrame frame) {
    	
        containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(1, 2));

        boardPanel = setup_boardPanel();
        containerPanel.add(boardPanel);

        consolePanel = setup_consolePanel();
        containerPanel.add(consolePanel);

        rulesPanel = new JPanel();
        rulesPanel.setVisible(false);
        frame.add(rulesPanel);

        

        frame.add(containerPanel);
    }

    private JPanel setup_consolePanel() {
    	
        consolePanel = new JPanel();
        consolePanel.setBackground(Color.decode("#F9CB9C"));
        consolePanel.setLayout(new FlowLayout());

        JPanel titlePanel = setup_titlePanel();
        consolePanel.add(titlePanel);

       
        logArea = new JTextArea(systemMsg, 25, 40);
        logArea.setLineWrap(true);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        timePanel = setup_timePanel();
        buttonPanel = setup_buttonPanel();
        
        consolePanel.add(timePanel);
        consolePanel.add(scrollPane);
        consolePanel.add(buttonPanel);
        
        return consolePanel;

    }

    private JPanel setup_timePanel() {
        JPanel timePanel = new JPanel(new GridLayout(1, 2));
       
        int P1_minutes = (P1_Timer_val % 3600) / 60;
        int P1_seconds = P1_Timer_val % 60;
        int P2_minutes = (P2_Timer_val % 3600) / 60;
        int P2_seconds = P2_Timer_val % 60;
        timePanel.add(new JLabel("Player 1 Timer:")); // Label for user 1 timer

        P1_Timer_label = new JLabel(String.format("%02d:%02d", P1_minutes, P1_seconds));
        P1_Timer_label.setHorizontalAlignment(JLabel.CENTER);
        P1_Timer_label.setFont(new Font("Arial", Font.BOLD, 16));
        start_P1_Timer();
        timePanel.add(P1_Timer_label);

        timePanel.add(new JLabel("Player 2 Timer:")); // Label for user 2 timer
        P2_Timer_label = new JLabel(String.format("%02d:%02d", P2_minutes, P2_seconds));
        P2_Timer_label.setHorizontalAlignment(JLabel.CENTER);
        P2_Timer_label.setFont(new Font("Arial", Font.BOLD, 16));
        start_P2_Timer();
        timePanel.add(P2_Timer_label);
        
        return timePanel;
    }
    private JPanel setup_buttonPanel() {
    	buttonPanel = new JPanel(new GridLayout(2,1));
    	create_Pausebutton_Box();
    	//pauseButtons.setVisible(false);
		Resume_button = new JButton ("Resume");
		Resume_button.addActionListener(new ActionListener() {
        	@Override 
        	public void actionPerformed(ActionEvent e) {
        		if (is_P1_Turn == true) {
            		P1_Timer.restart();
            	}
            	else {
            		P2_Timer.restart();
            	}
        		P1_Pause = false;
        		P2_Pause = false;
        		logArea.append("Game Resumed!\n");
        	}
        });
        //resumeButtons.setVisible(false);
        buttonPanel.add(pauseButtons);
        buttonPanel.add(Resume_button);
    	return buttonPanel;
    	
    }
    private JPanel setup_titlePanel() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(4, 0));
        JLabel title = new JLabel("Shou Dou Qi (The Jungle Chess)");
        title.setFont(new Font("Verdana", 1, 20));
        titlePanel.add(title);
        titlePanel.add(new JLabel(" "));
        gameStart = new JButton("Game Start!");
        gameStart.addActionListener(new ActionListener() {
        	@Override 
        	public void actionPerformed(ActionEvent e) {
                gameStartMsg();
                gameStart.setVisible(false);
                
                if (is_P1_Turn) {
                	P1_Timer.start(); // Start player 1's timer
                } else {
                	P2_Timer.start(); // Start player 2's timer
                }
        	}
        });
        titlePanel.add(gameStart);
        titlePanel.setBackground(Color.decode("#F9CB9C"));
        
        return titlePanel;
    }

    private void create_Pausebutton_Box() {
       pauseButtons = Box.createHorizontalBox();
       P1_Pause_button = new JButton("P1 Pause");
        P1_Pause_button.addActionListener(new ActionListener() {
        	@Override 
        	public void actionPerformed(ActionEvent e) {
        		P1_Pause = true;
                
                if (P2_Pause == false) {
                	logArea.append("Player 1 wants to pause the game!\nNeed Player 2's agreement to pause the game!\n");
                } 
                else if (P1_Pause == true && P2_Pause == true) {
                	if (is_P1_Turn == true) {
                		P1_Timer.stop();
                	}
                	else {
                		P2_Timer.stop();
                	}
                    logArea.append("Game Paused!\n");
                }
        	}
        });
      
        P2_Pause_button = new JButton("P2 Pause");
        P2_Pause_button.addActionListener(new ActionListener() {
        	@Override 
        	public void actionPerformed(ActionEvent e) {
        		P2_Pause = true;
                
                if (P1_Pause == false) {
                	logArea.append("Player 2 wants to pause the game!\nNeed Player 1's agreement to pause the game!\n");
                } 
                else if (P1_Pause == true && P2_Pause == true) {
                	if (is_P1_Turn == true) {
                		P1_Timer.stop();
                	}
                	else {
                		P2_Timer.stop();
                	}
                    logArea.append("Game Paused!\n");
                }
        	}
        });
        pauseButtons.add(P1_Pause_button);
        pauseButtons.add(P2_Pause_button);
        
        
    }

	

    private JPanel setup_boardPanel() {
        boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        boardPanel.setBackground(Color.black);
        boardPanel.setLayout(new GridLayout(rows, cols, borderWidth, borderWidth));

        for (int j = verticalAxis.length - 1; j != -1; j--) {
            for (int i = 0; i != horizontalAxis.length; i++) {
                JLabel tile = setup_Tile(j, i);
                boardPanel.putClientProperty(verticalAxis[j] + horizontalAxis[i], tile);
                boardPanel.add(tile);
            }
        }
        return boardPanel;

    }

    private JLabel setup_Tile(int row, int col) {
        JLabel tile = new JLabel();
        tile.setPreferredSize(new Dimension(tileSize, tileSize));
        tile.setOpaque(true);
        tile.setBackground(Color.white);
        tile.setText(verticalAxis[row] + horizontalAxis[col]);
        tile.setVerticalAlignment(JLabel.TOP);

        if (isRiver(row, col) == true) {
            tile.setBackground(Color.cyan);
        } else if (isTrap(row, col) == true) {
            tile.setBackground(Color.orange);
        } else if (isDen(row, col) == true) {
            tile.setBackground(Color.green);
        }

        return tile;
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
                    logArea.append("Player 1's time is up!\n");
                    P1_Timer_val = 600* 3;
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
                    logArea.append("Player 2's time is up!\n");
                    P2_Timer_val = 600* 3;
                }
              
                
            }
        });
    }
    
    public static void main(String[] args) {
        GameUI ui = new GameUI();

    }
}
