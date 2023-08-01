package GUI;

import main.Main;
import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    // Start game button
    private final JButton startGame = new JButton("Startgame");
    // Settings button
    private final JButton settings = new JButton("Settings");
    // Highscore Player1

    // HighScore Player2

    private final Dimension defaultButtonSize = new Dimension(200, 100);

    public MainPanel(GamePanel panel) {

        this.setSize(panel.getSize());
        this.setBackground(Color.DARK_GRAY);

        this.setLayout(null);

        setButton(startGame, (e -> Main.play(panel)), 400, 500, "Start Game!");
        setButton(settings, (e -> Settings.openSettingsFrame()),  400, 600, "Settings");

    }

    public void setButton(JButton button, ActionListener listener, int x, int y, String text) {

        button.setLocation(x, y);
        button.setSize(this.defaultButtonSize);

        button.addActionListener(listener);

        button.setBorder(null);

        button.setText(text);

        this.add(button);

    }

}
