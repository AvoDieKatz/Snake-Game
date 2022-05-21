import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Rectangle> body;
    private int w = Game.w;
    private int h = Game.h;
    private int d = Game.d;

    private String move;

    public Snake() {
        body = new ArrayList<>();

        Rectangle temp = new Rectangle(Game.d, Game.d);
        temp.setLocation(Game.w / 2 * Game.d, Game.h / 2 * Game.d);
        body.add(temp);

        temp = new Rectangle(d,d);
        temp.setLocation((w / 2 - 1) * d, (h / 2) * d);
        body.add(temp);

        temp = new Rectangle(d,d);
        temp.setLocation((w / 2 - 2) * d, (h / 2) * d);
        body.add(temp);

        move = "NOTHING";
    }

    private Rectangle updateSnake() {
        Rectangle first = body.get(0);
        Rectangle temp = new Rectangle(Game.d, Game.d);
        if (move == "UP") {
            temp.setLocation(first.x, first.y - Game.d);
        } else if (move == "DOWN") {
            temp.setLocation(first.x, first.y + Game.d);
        } else if (move == "LEFT") {
            temp.setLocation(first.x - Game.d, first.y);
        } else if (move == "RIGHT") {
            temp.setLocation(first.x + Game.d, first.y);
        }
        return temp;
    }

    public void move() {
        if (move != "NOTHING") {
            Rectangle temp = updateSnake();
            body.add(0, temp);
            body.remove(body.size()-1);
        }
    }

    public void grow() {
        Rectangle temp = updateSnake();
        body.add(0, temp);
    }

    public ArrayList<Rectangle> getBody() {
        return body;
    }

    public void setBody() {
        this.body = body;
    }

    public int getX() {
        return body.get(0).x;
    }

    public int getY() {
        return body.get(0).y;
    }

    public String getMove() {
        return move;
    }

    public void up() {
        move = "UP";
    }

    public void down() {
        move = "DOWN";
    }

    public void left() {
        move = "LEFT";
    }

    public void right() {
        move = "RIGHT";
    }
}
