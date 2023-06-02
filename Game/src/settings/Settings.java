package settings;

import GUI.SettingsFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private static final Properties properties = new Properties();
    private static final SettingsFrame settingsFrame = new SettingsFrame();

    private static int player1UpKey;
    private static int player1DownKey;

    private static int player2UpKey;
    private static int player2DownKey;

    private static Color player1Color;
    private static Color player2Color;

    private static final String ballDefaultSize = "100";
    private static Dimension ballSize;
    private static Color ballColor;

    public static void loadSettings() {

        try (InputStream fis = Settings.class.getResourceAsStream("/settings.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player1UpKey = Integer.parseInt(properties.getProperty("player1_up", String.valueOf(KeyEvent.VK_W)));
        player1DownKey = Integer.parseInt(properties.getProperty("player1_down", String.valueOf(KeyEvent.VK_S)));

        player2UpKey = Integer.parseInt(properties.getProperty("player2_up", String.valueOf(KeyEvent.VK_UP)));
        player2DownKey = Integer.parseInt(properties.getProperty("player2_down", String.valueOf(KeyEvent.VK_DOWN)));

        player1Color = new Color(Integer.parseInt(properties.getProperty("player1_rgb", String.valueOf(Color.RED.getRGB()))));
        player2Color = new Color(Integer.parseInt(properties.getProperty("player2_rgb", String.valueOf(Color.BLUE.getRGB()))));

        int propertyBallSize = Integer.parseInt(properties.getProperty("ball_size", ballDefaultSize));
        ballSize = new Dimension(propertyBallSize, propertyBallSize);
        ballColor = new Color(Integer.parseInt(properties.getProperty("ball_rgb", String.valueOf(Color.WHITE.getRGB()))));

    }

    public static int getPlayer1UpKey() {
        return player1UpKey;
    }

    public static int getPlayer1DownKey() {
        return player1DownKey;
    }

    public static int getPlayer2UpKey() {
        return player2UpKey;
    }

    public static int getPlayer2DownKey() {
        return player2DownKey;
    }

    public static Color getPlayer1Color() {
        return player1Color;
    }

    public static Color getPlayer2Color() {
        return player2Color;
    }

    public static Dimension getBallSize() {
        return ballSize;
    }

    public static Color getBallColor() {
        return ballColor;
    }

    public static void openSettingsFrame() {
        settingsFrame.setVisible(true);
        settingsFrame.setLocationRelativeTo(null);
    }
    public static void closeSettingsFrame() {
        settingsFrame.setVisible(false);
    }
}
