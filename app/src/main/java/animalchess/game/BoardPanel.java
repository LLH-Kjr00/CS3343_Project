package animalchess.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.board.Tiles.Tile;
import animalchess.exceptions.InvalidMovementException;
 

public class BoardPanel extends JPanel implements TileUtil {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6443534525155053149L;
	private JLabel[][] UI_tiles =  new JLabel[7][9];
	private final String[] verticalAxis = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
	private final String[] horizontalAxis = { "1", "2", "3", "4", "5", "6", "7" };
	
	private int rows = verticalAxis.length;
	private int cols = horizontalAxis.length;
	
	private boolean legit_choice = false;
	private JLabel StartTile_onUI = null;
	private int choosenX = -1;
	private int choosenY = -1;
	
	private int tileSize = 100;
	private int borderWidth = 3;
	
	
	private Board board;

	private ConsolePanel consolePanel;
	
	// constructor
	BoardPanel(ConsolePanel consolePanel, Board board) {
		
		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(rows, cols, borderWidth, borderWidth));

		for (int j = verticalAxis.length - 1; j != -1; j--) {
			for (int i = 0; i != horizontalAxis.length; i++) {
				JLabel tile = setup_Tile(j, i);
				initialP1_Animal(j, i, tile);
				initialP2_Animal(j, i, tile);
				UI_tiles[i][j] = tile;
				this.add(tile);
			}
		}
		
		this.board = board;
		this.consolePanel = consolePanel;
	}
	// Set up the tile's color and text 
	// also set up the event handler for pressing the tile 
	// will call either call_TileSelect() or call_AnimalMove() 
	// depending whether the previous chosen tile contains an animal belongs to the player in his/hers turn
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
				// System.out.println("Clicked tile: Row " + verticalAxis[row] + ", Column " +
				// horizontalAxis[col]);
				if (GameUI.is_Game_Start == true) {
					if (GameUI.is_Game_Pause == false) {
					if (legit_choice == false) {
						call_TileSelect(col, row, tile);
					} else {
						call_AnimalMove(col, row, tile);
					}
				}
					else if (GameUI.is_Game_Pause == true) {
						GameUI.logArea.append("You cannot click on the board if the game is on pause!\n");
					}
			}
				else if (GameUI.is_Game_Start == false) {
					GameUI.logArea.append("You cannot click on the board if the game has not started!\n");
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

	// called when the player click on the tile 
	// check the property of the tile and announce it in the logging area
	private void call_TileSelect(int x, int y, JLabel choosenTileUI) {
		/*
		 * WrappedLocation W_location = new WrappedLocation(y,x);
		 * WrappedAnimal W_animal = new WrappedAnimal();
		 * WrappedTile W_tile = new WrappedTile(W_location, null);
		 */
		// Animal target = board.getTarget(x, y);
		Tile choosenTile = board.getTile(x, y);

		Animal target = choosenTile.getAnimal();
		GameUI.logArea.append("Clicking the tile at (" + verticalAxis[y] + "," + horizontalAxis[x] + ")\n");
		// say the name of the tile with no animal 
		if (target == null) {
			GameUI.logArea.append("It is a " + choosenTile.toString() + " with no animal.\n");
		}
		// say the name of the tile with the name of the animal 
		// while checking whether the selected animal belongs to the player
		else {
			GameUI.logArea.append("It is a " + choosenTile.toString() + " with a/an " + target.toString() + " that "
					+ target.get_Owner() + " can choose.\n");
		}
		GameUI.logArea.append("\n");
		// record the coordinates of the selected tile if the animal in the tile belongs to the player
		// changing legit_choice to true so that clicking the next tile will call call_AnimalMove()
		// otherwise, ignore the action
		if (target.get_isRed() == Board.is_P1_Turn) {
			GameUI.logArea.append("You can choose the animal in this tile to move.\n");
			StartTile_onUI = choosenTileUI;
			choosenX = x;
			choosenY = y;
			legit_choice = true;
		} else {
			GameUI.logArea.append("You cannot choose the animal in this tile to move.\n");
		}
		GameUI.logArea.append("\n");
	}
	// receive the animal in the chosen tile using board.getTile(choosenX, choosenY) then StartTile.getAnimal()
	// tell the animal to move and record the actions
	// catch exception if the move is illegal and tell the player why the move is illegal through the logging area
	// also check whether the move made by the player induced the winning condition 
	private void call_AnimalMove(int x, int y, JLabel DestTile_onUI) {
		Tile StartTile = board.getTile(choosenX, choosenY);
		Animal choosenAnimal = StartTile.getAnimal();
		try {
		
				choosenAnimal.Move(x, y);
				GameUI.logArea.append("Moving to the tile at (" + verticalAxis[y] + "," + horizontalAxis[x] + ")\n");
				// MoveAnimal_onUI(x,y);
				MoveAnimal_onUI(x, y, DestTile_onUI);
				if (board.getWin() == true) {
					consolePanel.End_game();
				}
				
				else {
					consolePanel.Change_turn();
				}
		} catch (InvalidMovementException e) {
			e.printStackTrace();
			GameUI.logArea.append("You cannot move to that tile because " + e.getLocalizedMessage() + "\n");
			
		}
		GameUI.logArea.append("\n");
		choosenX = -1;
		choosenY = -1;
		legit_choice = false;
	}

	// "moving" the animal on the UI by removing the icon in the original tile 
	// and adding the said icon to the tile at the destination 
	private void MoveAnimal_onUI(int x, int y, JLabel DestTile_onUI) {

		DestTile_onUI.setIcon(StartTile_onUI.getIcon());
		StartTile_onUI.setIcon(null);
		StartTile_onUI = null;
	}
	// reset the orientation of the board in the UI when ever the game has started or restarted
	public void reset_boardPanel() {
		for (int j = verticalAxis.length - 1; j != -1; j--) {
			for (int i = 0; i != horizontalAxis.length; i++) {
				UI_tiles[i][j].setIcon(null);
				initialP1_Animal(j, i, UI_tiles[i][j]);
				initialP2_Animal(j, i, UI_tiles[i][j]);
			}
		}
	}
	
	
}
