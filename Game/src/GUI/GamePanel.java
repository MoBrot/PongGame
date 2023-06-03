package GUI;

import main.Main;
import pongcomponent.BaseComponent;
import pongcomponent.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final JLabel player1Points = new JLabel();
    public static final JLabel player2Points = new JLabel();

    private final JButton mainMenu = new JButton();

    public GamePanel(Dimension size) {
        this.setSize(size);
        this.setBackground(Color.BLACK);

        int middle = (int) (size.getWidth() / 2);
        int labelWidth = 150;

        addPointLabel(middle - labelWidth, player1Points, labelWidth, Main.getPlayer1());
        addPointLabel(middle + labelWidth, player2Points, labelWidth, Main.getPlayer2());

        mainMenu.addActionListener((e -> {



            Main.stop(this);

        }));
    }

    private void addPointLabel(int x, JLabel label, int labelWidth, Player player) {

        updateLabel(player, label);

        label.setForeground(Color.WHITE);
        label.setFont(new Font("verdana", Font.PLAIN, 18));

        label.setBounds(x, 0, labelWidth, 100);

        label.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        this.add(label);

    }

    public static void updateLabel(Player player, JLabel label) {

        label.setText(
                "Player" + player.getId() + ": " + player.getPoints() + "points"
        );
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;

        for (BaseComponent component : BaseComponent.components)
            component.draw(graphics2D);

        Main.getBall().draw(graphics2D);

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
