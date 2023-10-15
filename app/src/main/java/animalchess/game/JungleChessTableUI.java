package animalchess.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JungleChessTableUI extends JFrame {
    private static final int ROWS = 9;
    private static final int COLUMNS = 7;

    private JPanel boardPanel;
    private JTextArea logArea;
    private JButton startButton;
    private JButton swapButton1;
    private JButton swapButton2;
    private JLabel timeLabel1;
    private JLabel timeLabel2;
    private Timer timer1;
    private Timer timer2;
    private int timeInSeconds1 = 600; // 1 hour countdown for User 1
    private int timeInSeconds2 = 600; // 1 hour countdown for User 2
    private boolean isPlayer1Turn = true; // Flag to track player 1's turn
    private boolean isPlayer2Turn = false; // Flag to track player 2's turn

    public JungleChessTableUI() {
        setTitle("Jungle Chess Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());

        boardPanel = new JPanel(new GridLayout(ROWS, COLUMNS));
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());

        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(logArea);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel timePanel = new JPanel(new GridLayout(2, 2));

        timePanel.add(new JLabel("User 1 Timer:")); // Label for user 1 timer
        int hours = timeInSeconds2 / 3600;
        int minutes = (timeInSeconds2 % 3600) / 60;
        int seconds = timeInSeconds2 % 60;
        timeLabel1 = new JLabel(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        timeLabel1.setHorizontalAlignment(JLabel.CENTER);
        timeLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        timePanel.add(timeLabel1);

        timePanel.add(new JLabel("User 2 Timer:")); // Label for user 2 timer
        timeLabel2 = new JLabel(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        timeLabel2.setHorizontalAlignment(JLabel.CENTER);
        timeLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        timePanel.add(timeLabel2);

        rightPanel.add(timePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1)); // Change to 3 rows

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setVisible(false); // Hide the start button
                swapButton1.setVisible(true); // Show swap button 1
                swapButton2.setVisible(true); // Show swap button 2
                
                if (isPlayer1Turn) {
                    timer1.start(); // Start player 1's timer
                } else {
                    timer2.start(); // Start player 2's timer
                }
            }
        });
        buttonPanel.add(startButton); // Add the start button initially

        swapButton1 = new JButton("Swap to User 2");
        swapButton1.setVisible(false); // Hide swap button 1 initially
        swapButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.append("Swap to User 2\n");
                timer1.stop(); // Stop player 1's timer
                timer2.start(); // Start player 2's timer
                isPlayer1Turn = false; // Set player 1's turn flag to false
                isPlayer2Turn = true; // Set player 2's turn flag to true
            }
        });
        buttonPanel.add(swapButton1);

        swapButton2 = new JButton("Swap to User 1");
        swapButton2.setVisible(false); // Hide swap button 2 initially
        swapButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.append("Swap to User 1\n");
                timer2.stop(); // Stop player 2's timer
                timer1.start(); // Start player 1's timer
                isPlayer2Turn = false; // Set player 2's turn flag to false
                isPlayer1Turn = true; // Set player 1's turn flag to true
            }
        });
        buttonPanel.add(swapButton2);

        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(rightPanel, BorderLayout.EAST);

        getContentPane().add(mainPanel);

        createCheckerboard();

        startTimer1();
        startTimer2();

        pack();
        setLocationRelativeTo(null);
    }

    private void createCheckerboard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                JPanel squarePanel = new JPanel();
                squarePanel.setPreferredSize(new Dimension(40, 40));

                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 16));
                label.setForeground(Color.BLACK);

                if ((row == 3 && col == 1) || (row == 4 && col == 1) || (row == 5 && col == 1) ||
                        (row == 3 && col == 2) || (row == 4 && col == 2) || (row == 5 && col == 2) ||
                        (row == 5 && col == 4) || (row == 3 && col == 4) || (row == 4 && col == 4) ||
                        (row == 4 && col == 5) || (row == 3 && col == 5) || (row == 5 && col == 5)) {
                    squarePanel.setBackground(Color.BLUE);
                } else if ((row == 0 && col == 2) || (row == 1 && col == 3) || (row == 0 && col == 4) ||
                        (row == 8 && col == 2) || (row == 7 && col == 3) || (row == 8 && col == 4)) {
                    squarePanel.setBackground(Color.RED);
                } else {
                    squarePanel.setBackground(Color.WHITE);
                }

                squarePanel.add(label);
               // Add squarePanel to the boardPanel
                boardPanel.add(squarePanel);
            }
        }
    }

    private void startTimer1() {
        timer1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeInSeconds1--;

                int hours = timeInSeconds1 / 3600;
                int minutes = (timeInSeconds1 % 3600) / 60;
                int seconds = timeInSeconds1 % 60;

                timeLabel1.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                if (timeInSeconds1 <= 0) {
                    timer1.stop();
                    logArea.append("User 1's time is up!\n");
                }
            }
        });
    }

    private void startTimer2() {
        timer2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeInSeconds2--;

                int hours = timeInSeconds2 / 3600;
                int minutes = (timeInSeconds2 % 3600) / 60;
                int seconds = timeInSeconds2 % 60;

                timeLabel2.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                if (timeInSeconds2 <= 0) {
                    timer2.stop();
                    logArea.append("User 2's time is up!\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JungleChessTableUI().setVisible(true);
            }
        });
    }
}

