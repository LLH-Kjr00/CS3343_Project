package animalchess.game;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePanel extends JPanel {
    private GameUI gameUI;
    private TimePanel timePanel;
    private ButtonPanel buttonPanel;
    private TitlePanel titlePanel;
    private SurrenderButtonBox yieldButtons;

    ConsolePanel() {
        this.setBackground(Color.decode("#F9CB9C"));
        this.setLayout(new FlowLayout());

        timePanel = new TimePanel();
        buttonPanel = new ButtonPanel();
        titlePanel = new TitlePanel();
        yieldButtons = new SurrenderButtonBox(1);

        this.add(titlePanel);

        gameUI.logArea = new JTextArea(25, 40);
        gameUI.logArea.setLineWrap(true);
        gameUI.logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameUI.logArea);

        this.add(yieldButtons);
        this.add(timePanel);
        this.add(scrollPane);
        this.add(buttonPanel);

    }
}
