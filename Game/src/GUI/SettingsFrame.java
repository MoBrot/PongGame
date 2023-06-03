package GUI;

import main.Main;
import pongcomponent.Ball;
import pongcomponent.Player;
import settings.Settings;

import javax.swing.*;

public class SettingsFrame extends JFrame {

    private final JButton apply = new JButton();

    //TODO - 3color pickers with color screen

    public SettingsFrame() {

        apply.addActionListener(e -> {

            Settings.loadSettings();

            Ball ball = Main.getBall();
            Player player1 = Main.getPlayer1();
            Player player2 = Main.getPlayer2();

            ball.setColor(Settings.getBallColor());
            ball.setHeight((int) Settings.getBallSize().getHeight());
            ball.setWidth((int) Settings.getBallSize().getWidth());

            player1.setColor(Settings.getPlayer1Color());
            player2.setColor(Settings.getPlayer2Color());

            Settings.closeSettingsFrame();
        });
    }
}
