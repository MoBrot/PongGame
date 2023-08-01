package pongcomponent;

import java.awt.*;
import java.util.ArrayList;

public class BaseComponent {

    public static ArrayList<BaseComponent> components = new ArrayList<>();

    private int x;
    private int y;

    private int width;
    private int height;

    private Color color;

    private final int frameHeight;

    private final Rectangle hitBox = new Rectangle();


    public BaseComponent(int width, int height, int defaultX, int defaultY, Color color, int frameHeight) {
        this.x = defaultX;
        this.y = defaultY;
        this.width = width;
        this.height = height;
        this.color = color;

        this.frameHeight = frameHeight;

        hitBox.setBounds(this.x, this.y, this.width, this.height);

        components.add(this);
    }

    public void setY(int y) {
        this.y = y;

        this.hitBox.setLocation(this.x, this.y);
    }

    public void setX(int x) {
        this.x = x;

        this.hitBox.setLocation(this.x, this.y);
    }

    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public boolean isInBounds(int position) {

        if(position == this.frameHeight - this.getHeight())
            return false;

        else if (position == 0)
            return false;

        return true;
    }

    public void tick(){};

    public void draw(Graphics2D graphics2D) {};
}
