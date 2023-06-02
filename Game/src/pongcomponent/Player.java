package pongcomponent;

public class Player extends BaseComponent {

    private boolean movingUp;
    private boolean movingDown;

    public Player(int defaultX, int defaultY) {
        super(20, 100, defaultX, defaultY);
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

    @Override
    public void tick() {

    }
}
