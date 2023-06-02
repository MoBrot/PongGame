package pongcomponent;

import main.Main;

import java.awt.*;

public class Ball extends BaseComponent {

    private int defaultMotion = 5;
    private int playerBounceMotion = 8;

    private final int frameWidth;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private final int frameHeight;

    public Ball(int width, int height, int defaultX, int defaultY, Color color, int frameHeight, int frameWidth) {

        super(width, height, defaultX, defaultY, color, frameHeight);

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    @Override
    public void tick() {

        int nextYPosition = this.getY();
        int nextXPosition;

        int xMotion = defaultMotion;

        // Calculate Y
        if(this.getY() == 0) {
            movingDown = true;
            movingUp = false;
        } else if((frameHeight - this.getHeight() == (this.getY()))) {
            movingDown = false;
            movingUp = true;
        }

        // Calculate new Y position
        if(movingUp)
            nextYPosition = this.getY() - this.defaultMotion;
        else if (movingDown)
            nextYPosition = this.getY() + this.defaultMotion;

        this.setY(nextYPosition);

        // Calculate X
        // Calculate PlayerBounce
        if(this.getY() >= Main.getPlayer1().getY() - Main.getPlayer1().getHeight() && this.getY() <= Main.getPlayer1().getY())


        if(movingLeft)
            xMotion = xMotion * (-1);

        this.setX(xMotion + this.getX());

        /*
        // Calculate Win
        if(touchesXBorder(nextXPosition)) {
            // Add point to player who made the point



        }

         */
    }

    private boolean touchesXBorder(int position) {
        return false;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }
    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(this.getColor());
        graphics2D.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
