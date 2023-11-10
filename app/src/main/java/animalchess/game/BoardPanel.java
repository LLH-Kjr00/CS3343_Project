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
import animalchess.events.LocationSelectEvent;
import animalchess.events.animal.AnimalMoveEvent;
import animalchess.exceptions.InvalidMovementException;
import animalchess.utils.common.WrappedLocation;
import animalchess.utils.event.EventManager;


public class BoardPanel extends JPanel implements TileUtil {
	private JLabel[][] UI_tiles =  new JLabel[7][9];
	public static final String[] verticalAxis = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
	public static final String[] horizontalAxis = { "1", "2", "3", "4", "5", "6", "7" };
	
	private int rows = verticalAxis.length;
	private int cols = horizontalAxis.length;
	
	private boolean legit_choice = false;
	private JLabel StartTile_onUI = null;
	private int choosenX = -1;
	private int choosenY = -1;
	
	private int tileSize = 100;
	private int borderWidth = 3;
	
	
	private Board board;
	private GameUI gameUI;
	private EventManager eventManager;
	
	BoardPanel(Board board, GameUI gameUI, EventManager eventManager) {
		
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
		this.gameUI = gameUI;
		this.eventManager = eventManager;
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
				// System.out.println("Clicked tile: Row " + verticalAxis[row] + ", Column " +
				// horizontalAxis[col]);
				if (GameUI.is_Game_Start) {
					if (!legit_choice) {
						// call_TileSelect(col, row, tile);
					} else {
						// call_AnimalMove(col, row, tile);
						eventManager.push(AnimalMoveEvent.builder()
										.to(new WrappedLocation(col, row))
								.build());
					}
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

	private void call_TileSelect(int x, int y, JLabel choosenTileUI) {
		/*
		 * WrappedLocation W_location = new WrappedLocation(y,x);
		 * WrappedAnimal W_animal = new WrappedAnimal();
		 * WrappedTile W_tile = new WrappedTile(W_location, null);
		 */
		// Animal target = board.getTarget(x, y);

		LocationSelectEvent event = new LocationSelectEvent(new WrappedLocation(x, y));
		eventManager.push(event);
		if(!event.isCancelled()) {
			StartTile_onUI = choosenTileUI;
			choosenX = x;
			choosenY = y;
			legit_choice = true;
		}
	}

	private void call_AnimalMove(int x, int y, JLabel DestTile_onUI) {
		Tile StartTile = board.getTile(choosenX, choosenY);
		Animal choosenAnimal = StartTile.getAnimal();
		try {
			if (choosenAnimal.checkIsValidMove(x, y) == true) {
				GameUI.logArea.append("Moving to the tile at (" + verticalAxis[y] + "," + horizontalAxis[x] + ")\n");
				;
				choosenAnimal.Move(x, y);
				// MoveAnimal_onUI(x,y);
				MoveAnimal_onUI(x, y, DestTile_onUI);
				gameUI.gameMajorMsg("Turn Ended");
				gameUI.change_turn();
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

	private void MoveAnimal_onUI(int x, int y, JLabel DestTile_onUI) {
		DestTile_onUI.setIcon(StartTile_onUI.getIcon());
		StartTile_onUI.setIcon(null);
		StartTile_onUI = null;
	}
	
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
