package main;

import GUI.GamePanel;
import GUI.MainPanel;
import listener.KeyHandler;
import pongcomponent.Ball;
import pongcomponent.Player;
import settings.Settings;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private static Player player1; // Player on the left
    private static Player player2; // Player on the right

    public static Player getPlayer1() {
        return player1;
    }
    public static Player getPlayer2() {
        return player2;
    }

    private static Ball ball;

    public static Ball getBall() {
        return ball;
    }

    private static GamePanel gamePanel;
    private static MainPanel mainPanel;

    public static void main(String[] args) {

        Settings.loadSettings();

        JFrame frame = new JFrame();

        // Frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        device.setFullScreenWindow(frame);

        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();

        int playerDistance = 100;

        int playerWidth = 50;
        int playerHeight = 200;

        int playerDefaultY = (frameHeight / 2) - (playerHeight / 2);

        player1 = new Player(playerWidth, playerHeight, playerDistance + playerWidth, playerDefaultY, Settings.getPlayer1Color(), frameHeight, 1);
        player2 = new Player(playerWidth, playerHeight, frameWidth - playerDistance - playerWidth, playerDefaultY, Settings.getPlayer2Color(), frameHeight, 2);

        ball = new Ball(
                (int) Settings.getBallSize().getWidth(),
                (int) Settings.getBallSize().getHeight(),
                (int) ((frameWidth / 2) - (Settings.getBallSize().getWidth() / 2)), (int) ((frameHeight / 2) - (Settings.getBallSize().getHeight() / 2)),
                Settings.getBallColor(),
                frameHeight,
                frameWidth);

        gamePanel = new GamePanel(frame.getSize());
        mainPanel = new MainPanel(gamePanel);

        frame.add(mainPanel);
        frame.add(gamePanel);
        frame.addKeyListener(new KeyHandler());

        play(gamePanel);
    }

    public static GameState gameState;

    public static void play(GamePanel gamePanel) {

        gameState = GameState.PLAYING;

        gamePanel.startGame(60);
        getBall().randomMotion();

        Main.getPlayer1().setToDefault();
        Main.getPlayer2().setToDefault();

        mainPanel.setVisible(false);
        gamePanel.setVisible(true);
    }

    public static void stop(GamePanel gamePanel) {

        gameState = GameState.MAIN_MENU;

        gamePanel.gameThread = null;

        mainPanel.setVisible(true);
        gamePanel.setVisible(false);
    }
}
