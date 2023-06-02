package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    private final Properties properties = new Properties();

    public Settings() {

        try (FileInputStream fis = new FileInputStream("pfad/zur/properties-datei.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO set defaults

    }

    public static int getPlayer1UpKey() {

    }
}
