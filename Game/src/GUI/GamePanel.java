package GUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel(Dimension size) {
        this.setSize(size);
    }

    public void startGame(int fps) {

        new Thread(() -> {

            double nextDifference = (double) 1000000000 / fps;
            double nextWaitingTime = System.nanoTime() + nextDifference;

            while (true) {

                // Tick components


                // Paint the components again
                this.repaint();

                // Hold the FPS
                double remaining = (nextWaitingTime - System.nanoTime()) / 1000000;
                if(remaining < 0)
                    remaining = 0;

                try {
                    Thread.sleep((long) remaining);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
