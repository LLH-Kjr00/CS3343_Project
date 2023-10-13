package animalchess.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import animalchess.board.Board;

public class Game extends JFrame implements GameUtil {

    private final String[] verticalAxis = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
    private final String[] horizontalAxis = { "1", "2", "3", "4", "5", "6", "7" };
    int rows = verticalAxis.length;
    int cols = horizontalAxis.length;

    private JPanel containerPanel;
    private JPanel boardPanel;
    private JPanel consolePanel;
    private JPanel rulesPanel;
    private JPanel pausePanel;

    // private Player redPlayer
    // private Player bluePlayer
    // private ArrayList<Animal> animalList = new ArrayList();
    private Board board;

    private String systemMsg = new String();

    private int tileSize = 100;
    private int borderWidth = 3;
    private int redTimer = 600 * 3; // 1 hour countdown for User 1
    private int blueTimer = 600 * 3; // 1 hour countdown for User 2

    // Board JungleChessBoard
    public Game() {

        JFrame frame = new JFrame();
        frame.setSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        // frame.setExtendedState(MAXIMIZED_BOTH);

        // jTextArea = new JTextArea(systemMsg, 25, 40);
        // jTextArea.setLineWrap(true);

        // jLabel = new JLabel();
        // jLabel.setBounds(20, 20, 100, 40);

        // JScrollPane scrollPane = new JScrollPane(jTextArea);
        // logPanel.add(scrollPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setupPanels(frame);
        frame.setVisible(true);
    }

    private void appendMsg(String msg) {
        systemMsg += "\n" + msg;
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

        pausePanel = new JPanel();
        pausePanel.setVisible(false);
        frame.add(pausePanel);

        // getContentPane().add(containerPanel);
        frame.add(containerPanel);
    }

    private JPanel setup_consolePanel() {
        consolePanel = new JPanel();
        consolePanel.setBackground(Color.decode("#F9CB9C"));
        consolePanel.setLayout(new FlowLayout());

        JPanel titlePanel = setup_titlePanel();
        consolePanel.add(titlePanel);

        Box pauseButtons = create_buttonBox();

        // addAnimals();

        gameStartMsg();

        JTextArea jTextArea = new JTextArea(systemMsg, 25, 40);
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(jTextArea);

        JPanel timePanel = setup_timePanel();

        consolePanel.add(timePanel);
        consolePanel.add(scrollPane);
        consolePanel.add(pauseButtons);
        return consolePanel;

    }

    private JPanel setup_timePanel() {
        JPanel timePanel = new JPanel(new GridLayout(1, 2));
        JLabel redTimer_label;
        JLabel blueTimer_label;
        int red_minutes = (redTimer % 3600) / 60;
        int red_seconds = redTimer % 60;
        int blue_minutes = (blueTimer % 3600) / 60;
        int blue_seconds = blueTimer % 60;
        timePanel.add(new JLabel("User 1 Timer:")); // Label for user 1 timer

        redTimer_label = new JLabel(String.format("%02d:%02d", red_minutes, red_seconds));
        redTimer_label.setHorizontalAlignment(JLabel.CENTER);
        redTimer_label.setFont(new Font("Arial", Font.BOLD, 16));
        timePanel.add(redTimer_label);

        timePanel.add(new JLabel("User 2 Timer:")); // Label for user 2 timer
        blueTimer_label = new JLabel(String.format("%02d:%02d", blue_minutes, blue_seconds));
        blueTimer_label.setHorizontalAlignment(JLabel.CENTER);
        blueTimer_label.setFont(new Font("Arial", Font.BOLD, 16));
        timePanel.add(blueTimer_label);
        return timePanel;
    }

    private JPanel setup_titlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(4, 0));
        JLabel title = new JLabel("Shou Dou Qi (The Jungle Chess)");
        title.setFont(new Font("Verdana", 1, 20));
        titlePanel.add(title);
        titlePanel.add(new JLabel(" "));
        JButton gameStart = new JButton("Game Start!");
        titlePanel.add(gameStart);
        titlePanel.setBackground(Color.decode("#F9CB9C"));
        titlePanel.add(new JLabel(" "));
        return titlePanel;
    }

    private Box create_buttonBox() {
        Box pauseButtons = Box.createHorizontalBox();
        JButton pausePlayer1 = new JButton("P1 Pause");
        JButton pausePlayer2 = new JButton("P2 Pause");
        pauseButtons.add(pausePlayer1);
        pauseButtons.add(pausePlayer2);
        return pauseButtons;
    }

    private void gameStartMsg() {
        // TODO Auto-generated method stub
        String result = new String();
        for (int i = 0; i < 62; i++) {
            result += "=";
        }
        appendMsg(result);
        appendMsg("Game Start");
        appendMsg(result);
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
        // for(int i=0;i<player1.length;i++){
        // if (this.player1[i].isAlive())
        // drawChess(g, this.player1[i]);
        // }
        // for(int i=0;i<player2.length;i++){
        // if (this.player2[i].isAlive())
        // drawChess(g,this.player2[i]);
        // }
        // drawIndex(g);
        // jTextArea.setText(systemMsg);
    }

    public void addAnimals() {
        // animalList.add();
    }

    public int getTileSize() {
        return tileSize;
    }

    public static void main(String[] args) {
        Game ui = new Game();

    }

}
