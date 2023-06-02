package GUI;

import main.Main;
import pongcomponent.BaseComponent;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel(Dimension size) {
        this.setSize(size);
        this.setBackground(Color.BLACK);
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
