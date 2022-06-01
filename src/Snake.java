import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;

public class Snake {

    private ArrayList<Rectangle> body;
    private int w = Game.w;
    private int h = Game.h;
    private int d = Game.d;

    private String move;

    private Queue<String> pendingMove = new CircularFifoQueue<>(2);

    public Snake() {
        body = new ArrayList<>();

        Rectangle temp = new Rectangle(Game.d, Game.d);
        temp.setLocation(Game.w / 2 * Game.d, Game.h / 2 * Game.d);
        body.add(temp);

        temp = new Rectangle(d, d);
        temp.setLocation((w / 2 - 1) * d, (h / 2) * d);
        body.add(temp);

        temp = new Rectangle(d, d);
        temp.setLocation((w / 2 - 2) * d, (h / 2) * d);
        body.add(temp);

        move = "RIGHT";
    }

    private Rectangle updateSnake() {
        Rectangle first = body.get(0);
        Rectangle temp = new Rectangle(Game.d, Game.d);
        System.out.println("Current movement: " + move);
        System.out.println("Current queue: " + pendingMove);
        String movement = pendingMove.poll();
        if (movement == null) {
            movement = this.move;
        }
        if (movement == "UP") {
            temp.setLocation(first.x, first.y - Game.d);
            setMove("UP");
        } else if (movement == "DOWN") {
            temp.setLocation(first.x, first.y + Game.d);
            setMove("DOWN");
        } else if (movement == "LEFT") {
            temp.setLocation(first.x - Game.d, first.y);
            setMove("LEFT");
        } else if (movement == "RIGHT") {
            temp.setLocation(first.x + Game.d, first.y);
            setMove("RIGHT");
        }
        return temp;
    }

    public void pendMove(String movement) {
        pendingMove.add(movement);
    }

    public void move() {
        System.out.println("Move method ran");
        if (move != "NOTHING") {
            Rectangle temp = updateSnake();
            body.add(0, temp);
            body.remove(body.size() - 1);
        }
    }

    public void grow() {
        System.out.println("Grow method ran");
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

    public void setMove(String movement) {
        this.move = movement;
    }

}
