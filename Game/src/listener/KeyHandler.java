package listener;

import main.GameState;
import main.Main;
import settings.Settings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

        if(Main.gameState != GameState.SETTINGS)
            return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setMoving(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setMoving(e.getKeyCode(), false);
    }

    public void setMoving(int key, boolean toggle) {

        if(Main.gameState != GameState.PLAYING)
            return;

        if(key == Settings.getPlayer1UpKey())
            Main.getPlayer1().setMovingUp(toggle);

        if(key == Settings.getPlayer1DownKey())
            Main.getPlayer1().setMovingDown(toggle);

        if(key == Settings.getPlayer2UpKey())
            Main.getPlayer2().setMovingUp(toggle);

        if(key == Settings.getPlayer2DownKey())
            Main.getPlayer2().setMovingDown(toggle);
    }
}
