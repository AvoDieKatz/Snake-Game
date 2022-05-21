import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {

    private Snake snake;
    private Board board;
    private Food food;

    private JFrame frame;

    public static final int w = 30;
    public static final int h = 30;
    public static final int d = 20;

    public Game() {
        frame = new JFrame();
        snake = new Snake();
        food = new Food(snake);
        board = new Board(this);

        frame.add(board);
        frame.setTitle("Snake Game");
        frame.setSize(w * d + 2, h * d + 4);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void start() {
        board.state = "RUNNING";
    }

    public void update() {
        if (board.state == "RUNNING") {
            if (check_food_collision()) {
                snake.grow();
                food.random_spawn(snake);
            } else if (check_wall_collision() || check_self_collision()) {
                board.state = "END";
            } else {
                snake.move();
            }
        }
    }

    public boolean check_food_collision() {
        if (snake.getX() == food.getX() * d && snake.getY() == food.getY() * d) {
            return true;
        }
        return false;
    }

    public boolean check_wall_collision() {
        if (snake.getX() < 0 || snake.getX() >= h * d || snake.getY() < 0 || snake.getY() >= h * d) {
            return true;
        }
        return false;
    }

    public boolean check_self_collision() {
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (snake.getX() == snake.getBody().get(i).x && snake.getY() == snake.getBody().get(i).y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (board.state == "RUNNING") {
            if (keyCode == KeyEvent.VK_UP && snake.getMove() != "DOWN") {
                snake.up();
            }
            if (keyCode == KeyEvent.VK_DOWN && snake.getMove() != "UP") {
                snake.down();
            }
            if (keyCode == KeyEvent.VK_RIGHT && snake.getMove() != "LEFT") {
                snake.right();
            }
            if (keyCode == KeyEvent.VK_LEFT && snake.getMove() != "RIGHT") {
                snake.left();
            }
        } else {
            this.start();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No need to implement
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No need to implement
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
