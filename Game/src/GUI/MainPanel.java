package GUI;

import main.Main;
import settings.Settings;

import javax.swing.*;

public class MainPanel extends JPanel {

    // Start game button
    private final JButton startGame = new JButton("Startgame");
    // Settings button
    private final JButton settings = new JButton("Settings");
    // Highscore Player1

    // HighScore Player2

    public MainPanel(GamePanel panel) {

        startGame.addActionListener((e -> Main.play(panel)));
        settings.addActionListener((e -> Settings.openSettingsFrame()));

    }

}
