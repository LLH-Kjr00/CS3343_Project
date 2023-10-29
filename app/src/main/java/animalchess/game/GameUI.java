
package animalchess.game;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.board.Tiles.Tile;
import animalchess.exceptions.InvalidMovementException;


public class GameUI extends JFrame implements TileUtil {

	private Board board;
	// private Animal[] P1;
	// private Animal[] P2;

	private final String[] verticalAxis = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
	private final String[] horizontalAxis = { "1", "2", "3", "4", "5", "6", "7" };
	private JLabel[][] tileUI = new JLabel[7][9];
	private int rows = verticalAxis.length;
	private int cols = horizontalAxis.length;

	private JPanel containerPanel;
	private JPanel boardPanel;
	private JPanel consolePanel;
	private JPanel rulesPanel;
	private JPanel buttonPanel;
	private JPanel timePanel;
	private JPanel titlePanel;

	private JButton gameStart;
	private JTextArea logArea;

	private JLabel P1_Timer_label;
	private JLabel P2_Timer_label;
	private Timer P1_Timer;
	private Timer P2_Timer;
	private int P1_Timer_val = 600 * 3; // count down for User 1
	private int P2_Timer_val = 600 * 3; // count down for User 2

	private boolean legit_choice = false;
	private int choosenX = -1;
	private int choosenY = -1;
	
	private boolean is_P1_Turn = true; // Flag to track player 1's turn
	private boolean is_Game_Pause = false; // Flag to track game's state of pausing
	private boolean is_Game_Start = false; // Flag to track game's state of pausing
	private boolean is_P1_Win = false; // Flag to track player 1's win
	private boolean is_P2_Win = false; // Flag to track player 2's win
	private boolean P1_Pause = false;
	private boolean P2_Pause = false;
	private JButton P1_Pause_button;
	private JButton P2_Pause_button;
	private JButton Resume_button;
	private Box pauseButtons;

	private JButton P1_Surrender_button;
	private JButton P2_Surrender_button;
	private Box yieldButtons;

	private int tileSize = 100;
	private int borderWidth = 3;

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

	private void gameMajorMsg(String content) {
		String result = new String();
		for (int i = 0; i < 62; i++) {
			result += "=";
		}
		logArea.append(result);
		logArea.append(content +" !\n");
		
	}

	private void setupPanels(JFrame frame) {

		containerPanel = new JPanel();
		containerPanel.setLayout(new GridLayout(1, 2));

		setup_boardPanel();
		containerPanel.add(boardPanel);

		setup_consolePanel();
		containerPanel.add(consolePanel);

		rulesPanel = new JPanel();
		rulesPanel.setVisible(false);
		frame.add(rulesPanel);

		frame.add(containerPanel);
	}

	private void setup_consolePanel() {

		consolePanel = new JPanel();
		consolePanel.setBackground(Color.decode("#F9CB9C"));
		consolePanel.setLayout(new FlowLayout());

		setup_titlePanel();
		consolePanel.add(titlePanel);

		logArea = new JTextArea(25, 40);
		logArea.setLineWrap(true);
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea);

		setup_timePanel();
		setup_buttonPanel();
		create_Surrenderbutton_Box();
		consolePanel.add(yieldButtons);
		consolePanel.add(timePanel);
		consolePanel.add(scrollPane);
		consolePanel.add(buttonPanel);

	}

	private void setup_timePanel() {
		timePanel = new JPanel(new GridLayout(1, 2));

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

	}

	private void setup_buttonPanel() {
		buttonPanel = new JPanel(new GridLayout(2, 1));
		create_Pausebutton_Box();
		// pauseButtons.setVisible(false);
		Resume_button = new JButton("Resume");
		Resume_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (is_P1_Turn == true && P1_Timer.isRunning() == false) {
					P1_Timer.restart();
					P1_Pause = false;
					P2_Pause = false;
					is_Game_Pause = false;
					logArea.append("Game Resumed!\n");
				} else if (is_P1_Turn == false && P2_Timer.isRunning() == false) {
					P2_Timer.restart();
					P1_Pause = false;
					P2_Pause = false;
					is_Game_Pause = false;
					logArea.append("Game Resumed!\n");
				} else {
					logArea.append("Action ignored: The game has not paused!\n");
				}
			}
		});
		Resume_button.setVisible(false);
		buttonPanel.setBackground(Color.decode("#F9CB9C"));
		buttonPanel.add(pauseButtons);
		buttonPanel.add(Resume_button);

	}

	private void setup_titlePanel() {
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
				logArea.setText("");
				
				is_Game_Start = true;
				gameStart.setVisible(false);
				Resume_button.setVisible(true);
				yieldButtons.setVisible(true);
				buttonPanel.setVisible(true);
				P1_Pause_button.setVisible(true);
				P2_Pause_button.setVisible(true);
				gameMajorMsg("Game Start");
				setup_boardPanel();
				if (is_P1_Turn) {
					P1_Timer.start(); // Start player 1's timer
				} else {
					P2_Timer.start(); // Start player 2's timer
				}
				is_P1_Win = false;
				is_P2_Win = false;
				P1_Timer_val = 600 * 3;
				P2_Timer_val = 600 * 3;
			}
		});
		titlePanel.add(gameStart);
		titlePanel.setBackground(Color.decode("#F9CB9C"));

	}

	private void create_Pausebutton_Box() {
		pauseButtons = Box.createHorizontalBox();
		P1_Pause_button = new JButton("P1 Pause");
		P1_Pause_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				P1_Pause = true;
				if (is_Game_Pause == false) {
					if (P2_Pause == false) {
						logArea.append("Player 1 wants to pause the game!\n");
						logArea.append("Need Player 2's agreement to pause the game!\n");
					} else if (P1_Pause == true && P2_Pause == true) {
						if (is_P1_Turn == true) {
							P1_Timer.stop();
						} else {
							P2_Timer.stop();
						}
						is_Game_Pause = true;
						logArea.append("Game Paused!\n");
					}
				} else {
					logArea.append("Action ignored: The game has paused already!\n");
				}
			}
		});

		P2_Pause_button = new JButton("P2 Pause");
		P2_Pause_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				P2_Pause = true;
				if (is_Game_Pause == false) {
					if (P1_Pause == false) {
						logArea.append("Player 2 wants to pause the game!\n");
						logArea.append("Need Player 1's agreement to pause the game!\n");
					} else if (P1_Pause == true && P2_Pause == true) {
						if (is_P1_Turn == true) {
							P1_Timer.stop();
						} else {
							P2_Timer.stop();
						}
						is_Game_Pause = true;
						logArea.append("Game Paused!\n");
					}
				} else {
					logArea.append("Action ignored: The game has paused already!\n");
				}
			}
		});
		P1_Pause_button.setVisible(false);
		P2_Pause_button.setVisible(false);
		pauseButtons.add(P1_Pause_button);
		pauseButtons.add(P2_Pause_button);
	}

	private void create_Surrenderbutton_Box() {
		yieldButtons = Box.createHorizontalBox();
		P1_Surrender_button = new JButton("P1 Surrender");
		P1_Surrender_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (is_P1_Win == false && is_P1_Win == false) {
					P1_Timer.stop();
					P2_Timer.stop();
					is_P2_Win = true;
					logArea.append("Player 1 yields...\n");
					announce_Win();
				}
			}

		});
		P2_Surrender_button = new JButton("P2 Surrender");
		P2_Surrender_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (is_P1_Win == false && is_P1_Win == false) {
					P1_Timer.stop();
					P2_Timer.stop();
					is_P1_Win = true;
					logArea.append("Player 2 yields...\n");
					announce_Win();
				}
			}

		});
		yieldButtons.add(P1_Surrender_button);
		yieldButtons.add(P2_Surrender_button);
		yieldButtons.setVisible(false);

	}

	private void setup_boardPanel() {
		boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
		boardPanel.setBackground(Color.black);
		boardPanel.setLayout(new GridLayout(rows, cols, borderWidth, borderWidth));

		for (int j = verticalAxis.length - 1; j != -1; j--) {
			for (int i = 0; i != horizontalAxis.length; i++) {
				JLabel tile = setup_Tile(j, i);
				initialP1_Animal(j, i, tile);
				initialP2_Animal(j, i, tile);
				tileUI[i][j]= tile;
				boardPanel.add(tile);
			}
		}

	}

	private JLabel setup_Tile(int row, int col) {
		JLabel tile = new JLabel();
		tile.setVerticalTextPosition(JLabel.CENTER);
		tile.setPreferredSize(new Dimension(tileSize, tileSize));
		tile.setOpaque(true);
		tile.setBackground(Color.white);
		tile.setText(verticalAxis[row] + horizontalAxis[col]);
		tile.setVerticalAlignment(JLabel.TOP);

		check_Tile_SpecialProp(row, col, tile);

		tile.setText(verticalAxis[row] + horizontalAxis[col]);
		tile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("Clicked tile: Row " + verticalAxis[row] + ", Column " + horizontalAxis[col]);
				if (is_Game_Start == true) {
					if (legit_choice == false) {
						call_TileSelect(col, row);
					}
					else {
						call_AnimalMove(col, row);
					}
				}
				else {
					System.out.println("You cannot click on the board yet!");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				tile.setBackground(Color.yellow);
				tile.setForeground(Color.magenta);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				check_Tile_SpecialProp(row, col, tile);
			}
		});
		return tile;
	}

	private void check_Tile_SpecialProp(int row, int col, JLabel tile) {
		if (isRiver(row, col) == true) {
			tile.setBackground(Color.cyan);
			tile.setForeground(Color.black);
		} else if (isTrap(row, col) == true) {
			tile.setBackground(Color.orange);
			tile.setForeground(Color.black);
		} else if (isP1_Den(row, col) == true) {
			tile.setBackground(Color.red);
			tile.setForeground(Color.white);
		} else if (isP2_Den(row, col) == true) {
			tile.setBackground(Color.blue);
			tile.setForeground(Color.white);
		} else {
			tile.setBackground(Color.white);
			tile.setForeground(Color.black);
		}
	}

	private void call_TileSelect(int x, int y) {
		/*
		 * WrappedLocation W_location = new WrappedLocation(y,x);
		 * WrappedAnimal W_animal = new WrappedAnimal();
		 * WrappedTile W_tile = new WrappedTile(W_location, null);
		 */
		// Animal target = board.getTarget(x, y);
		Tile choosenTile = board.getTile(x, y);
		Animal target = choosenTile.getAnimal();
		if (target == null) {
			System.out.println("This is a tile at (" + verticalAxis[y] + "," + horizontalAxis[x] + ")");
			System.out.println("It is a "+choosenTile.toString()+" with no animal.");
		} else {
			System.out.println("This is a tile at (" + verticalAxis[y] + "," + horizontalAxis[x] + ")");
			System.out.println("It is a "+choosenTile.toString()+" with a/an "+ target.toString()+ " that "+target.get_Owner()+" can choose.");
			if (target.get_isRed() == is_P1_Turn) {
				System.out.println("You can choose the animal in this tile to move");
				choosenX = x;
				choosenY = y;
				legit_choice = true;
			}
			else {
				System.out.println("You cannot choose the animal in this tile to move");
			}
		}
	}
	
	private void call_AnimalMove(int x ,int y) {
		Tile DestTile = board.getTile(x, y);
		Tile StartTile = board.getTile(choosenX, choosenY);
		Animal choosenAnimal = StartTile.getAnimal();
		
		try {
			if (choosenAnimal.checkIsValidMove(x, y) == true) {		
				System.out.println("Can move to that tile");
				choosenAnimal.Move(x,y);
				//MoveAnimal_onUI(x,y);
				MoveAnimal_onUI(x,y);
				gameMajorMsg("Turn Ended");
				if (is_P1_Turn == true) {
					logArea.append("Player 2's turn now!\n");
					is_P1_Turn = false;
					P1_Timer.stop();
					P2_Timer.start();
					P1_Timer_val = 600 * 3;
				} else {
					logArea.append("Player 1's turn now!\n");
					is_P1_Turn = true;
					P2_Timer.stop();
					P1_Timer.start();
					P2_Timer_val = 600 * 3;
				}
			}
		} catch (InvalidMovementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cannot move to that tile");
		}
		
		
		choosenX = -1;
		choosenY = -1;
		legit_choice = false;
	}
	private void MoveAnimal_onUI(int x, int y) {
		//tileUI[y][x].setIcon(null);
		ImageIcon ori_icon= (ImageIcon) tileUI[choosenX][choosenY].getIcon();
		System.out.println("BEFORE");

		System.out.println(tileUI[x][y]);
		System.out.println(tileUI[choosenX][choosenY]);
		
		tileUI[x][y].setIcon(ori_icon);
		
		
		
		tileUI[choosenX][choosenY].setIcon(null);
		System.out.println("AFTER");
		System.out.println(tileUI[x][y]);
		System.out.println(tileUI[choosenX][choosenY]);
		
		//tileUI[x][y].setIcon(choosenIcon);
		//tileUI[choosenY][choosenX].setIcon(null);
	}

	private void announce_Win() {
		if (is_P1_Win) {
			logArea.append("Player 1 wins!\n");
		} else if (is_P2_Win) {
			logArea.append("Player 2 wins!\n");
		} else {
			logArea.append("No ones wins!\n");
		}

		yieldButtons.setVisible(false);
		buttonPanel.setVisible(false);
		gameStart.setVisible(true);
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
					is_P2_Win = true;
					logArea.append("Player 1's time is up...\n");
					announce_Win();

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
					is_P1_Win = true;
					logArea.append("Player 2's time is up...\n");
					announce_Win();
				}

			}
		});
	}

	public static void main(String[] args) {
		// GameUI ui = new GameUI();

	}
}
