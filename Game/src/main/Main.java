package main;

import GUI.GamePanel;
import listener.KeyHandler;
import pongcomponent.Ball;
import pongcomponent.Player;
import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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

    public static void main(String[] args) {

        Settings.loadSettings();

        JFrame frame = new JFrame();
        KeyHandler handler = new KeyHandler();

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

        player1 = new Player(playerWidth, playerHeight, playerDistance + playerWidth, playerDefaultY, Settings.getPlayer1Color(), frameHeight);
        player2 = new Player(playerWidth, playerHeight, frameWidth - playerDistance - playerWidth, playerDefaultY, Settings.getPlayer2Color(), frameHeight);

        ball = new Ball(
                (int) Settings.getBallSize().getWidth(),
                (int) Settings.getBallSize().getHeight(),
                (int) ((frameWidth / 2) - (Settings.getBallSize().getWidth() / 2)), (int) ((frameHeight / 2) - (Settings.getBallSize().getHeight() / 2)),
                Settings.getBallColor(),
                frameHeight,
                frameWidth);

        GamePanel gamePanel = new GamePanel(frame.getSize());

        frame.add(gamePanel);
        frame.addKeyListener(handler);

        play(gamePanel);
    }


    public static final Random random = new Random();
    public static void play(GamePanel gamePanel) {

        gamePanel.startGame(60);

        // Set random ball movement
        boolean up = random.nextBoolean();
        if(up)
            getBall().setMovingUp(true);
        else
            getBall().setMovingDown(true);

        boolean left = random.nextBoolean();
        if(left)
            getBall().setMovingLeft(true);
        else
            getBall().setMovingRight(true);

    }

    public static void stop(GamePanel gamePanel) {
        gamePanel.gameThread = null;
    }
}
