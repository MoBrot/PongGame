import GUI.GamePanel;
import listener.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();



    public static void main(String[] args) {

        JFrame frame = new JFrame();
        KeyHandler handler = new KeyHandler();

        device.setFullScreenWindow(frame);

        GamePanel gamePanel = new GamePanel(frame.getSize());

        frame.add(gamePanel);
        frame.addKeyListener(handler);

    }
}
