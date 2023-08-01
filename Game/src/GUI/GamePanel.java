package GUI;

import main.Main;
import pongcomponent.BaseComponent;
import pongcomponent.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final JLabel player1Points = new JLabel();
    public static final JLabel player2Points = new JLabel();

    private final JButton mainMenu = new JButton();

    public GamePanel(Dimension size) {

        this.setSize(size);
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        final int middle = (int) (size.getWidth() / 2);
        final int labelWidth = 150;

        final Font defaultFont = new Font("verdana", Font.PLAIN, 18);

        addPointLabel(middle - labelWidth * 2, player1Points, labelWidth, Main.getPlayer1(), defaultFont);
        addPointLabel(middle + labelWidth, player2Points, labelWidth, Main.getPlayer2(), defaultFont);

        final int buttonWidth = 175;
        mainMenu.setBounds(middle - (buttonWidth / 2), 0, buttonWidth, 75);

        mainMenu.setText("MainMenu");
        mainMenu.setBackground(Color.GRAY);
        mainMenu.setBorder(null);
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setFont(defaultFont);

        mainMenu.addActionListener((e -> Main.stop(this)));

        this.add(mainMenu);
    }

    private void addPointLabel(int x, JLabel label, int labelWidth, Player player, Font font) {

        updateLabel(player, label);

        label.setForeground(Color.WHITE);
        label.setFont(font);

        label.setBounds(x, 0, labelWidth, 100);

        this.add(label);

    }

    public static void updateLabel(Player player, JLabel label) {
        label.setText(
                "Player" + player.getId() + ": \n" + player.getPoints() + "points"
        );
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;

        for (BaseComponent component : BaseComponent.components)
            component.draw(graphics2D);

        g.dispose();
    }

    public Thread gameThread;
    public void startGame(int fps) {

        double nextDifference = (double) 1000000000 / fps;
        final double[] nextWaitingTime = {System.nanoTime() + nextDifference};

        // Main game thread
        gameThread = new Thread(() -> {

            while (gameThread != null) {

                // Tick components
                for (BaseComponent baseComponent : BaseComponent.components)
                    baseComponent.tick();

                // Paint the components again
                this.repaint();

                // Hold the FPS
                try {
                    double remaining = (nextWaitingTime[0] - System.nanoTime()) / 1000000;
                    if(remaining < 0)
                        remaining = 0;

                    Thread.sleep((long) remaining);

                    nextWaitingTime[0] += nextDifference;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        gameThread.start();
    }
}
