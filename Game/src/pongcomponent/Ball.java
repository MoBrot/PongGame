package pongcomponent;

import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Random;

public class Ball extends BaseComponent {

    private final int defaultMotion = 5;

    private final int frameWidth;

    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private final int frameHeight;

    private final int defaultX;
    private final int defaultY;

    public Ball(int width, int height, int defaultX, int defaultY, Color color, int frameHeight, int frameWidth) {

        super(width, height, defaultX, defaultY, color, frameHeight);

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        this.defaultX = defaultX;
        this.defaultY = defaultY;
    }

    @Override
    public void tick() {

        int nextYPosition = this.getY();

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
        // Colid left
        if(this.getX() == 0) {

            Main.getPlayer2().addPoint();

            randomMotion();

            // Colid right
        } else if(this.getX() == frameWidth) {

            Main.getPlayer1().addPoint();

            randomMotion();

            // Calculate PlayerBounce
        }else if(this.getX() == Main.getPlayer1().getX() + Main.getPlayer1().getWidth() || this.getX() + this.getWidth() == Main.getPlayer2().getX()) {

            if (colidsWithPlayerY(Main.getPlayer1())) {
                movingLeft = false;
            } else if (colidsWithPlayerY(Main.getPlayer2())) {
                movingLeft = true;
            }
        }

        int motion = defaultMotion;
        if(movingLeft)
            motion = defaultMotion * (-1);

        this.setX(motion + this.getX());
    }

    private boolean colidsWithPlayerY(Player player) {
        int y = this.getY() + (this.getHeight() / 2);
        return player.getY() <= y && y <= player.getY() + player.getHeight();
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

    private final Random random = new Random();
    public void randomMotion() {

        this.setX(this.defaultX);
        this.setY(this.defaultY);

        // Set random ball movement
        if(random.nextBoolean())
            this.setMovingUp(true);
        else
            this.setMovingDown(true);

        if(random.nextBoolean())
            this.setMovingLeft(true);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(this.getColor());
        graphics2D.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        int y = this.getY() + (this.getHeight() / 2);
        graphics2D.setColor(Color.green);
        graphics2D.drawLine(0, 0, this.getX(), y);
    }
}
