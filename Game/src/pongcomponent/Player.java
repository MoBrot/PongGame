package pongcomponent;

import GUI.GamePanel;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class Player extends BaseComponent {

    public static int steps = 5;

    private int points = 0;

    private boolean movingUp;
    private boolean movingDown;

    private final int id;

    private final int defaultX;
    private final int defaultY;

    public Player(int width, int height, int defaultX, int defaultY, Color color, int frameHeight, int id) {
        super(width, height, defaultX, defaultY, color, frameHeight);
        this.id = id;
        this.defaultX = defaultX;
        this.defaultY = defaultY;
    }

    public void setToDefault() {
        this.setX(defaultX);
        this.setY(defaultY);
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }
    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void addPoint() {
        this.points++;
        JLabel label;
        if(this.getId() == 1)
            label = GamePanel.player1Points;
        else
            label = GamePanel.player2Points;

        GamePanel.updateLabel(this, label);
    }

    public int getId() {
        return id;
    }

    @Override
    public void tick() {

        int nextPosition;

        if(isMovingUp())
            nextPosition = this.getY() - steps;
        else if (isMovingDown())
            nextPosition = this.getY() + steps;
        else
            return;

        if(this.isInBounds(nextPosition))
            this.setY(nextPosition);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(this.getColor());
        graphics2D.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
