import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private Timer t = new Timer(100, this);
    public String state;
    private Snake snake;
    private Food food;
    private Game game;

    public Board(Game g) {
        t.start();
        state = "START";
        game = g;
        snake = game.getSnake();
        food = game.getFood();

        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gp = (Graphics2D) g;

        gp.setColor(Color.lightGray);
        gp.fillRect(0, 0, Game.w * Game.d + 5, Game.h * Game.d +5);

        if (state == "START") {
            gp.setColor(Color.white);
            gp.drawString("Press Any Key", Game.w/2 * Game.d - 40, Game.h/2 * Game.d - 20);
        } else if (state == "RUNNING") {
            gp.setColor(Color.red);
            gp.fillRect(food.getX() * Game.d, food.getY() * Game.d, Game.d, Game.d);
            gp.setColor(Color.green);
            for (Rectangle r : snake.getBody()) {
                gp.fill(r);
            }
        } else {
            gp.setColor(Color.white);
            gp.drawString("Score: " + (snake.getBody().size() - 3), Game.w/2 * Game.d - 40, Game.h/2 * Game.d - 20);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}
