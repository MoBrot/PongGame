package pongcomponent;

import java.awt.*;
import java.util.ArrayList;

public class BaseComponent {

    public static ArrayList<BaseComponent> components = new ArrayList<>();

    private int x;
    private int y;

    private final int width;
    private final int height;

    public BaseComponent(int width, int height, int defaultX, int defaultY) {
        this.x = defaultX;
        this.y = defaultY;
        this.width = width;
        this.height = height;

        components.add(this);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
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

    public void tick(){};

    public void draw(Graphics2D graphics2D) {};
}
