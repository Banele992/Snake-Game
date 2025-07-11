import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeApp extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;
    // Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    // Points
    Tile food;
    Random random;

    // game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    SnakeApp(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(8, 8);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(12, 12);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

 @Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int w = getWidth();
    int h = getHeight();

    // Draw red border within actual dimensions
    g.setColor(Color.RED);
    g.drawRect(0, 0, w - 1, h - 1);  // outer border
    g.drawRect(1, 1, w - 3, h - 3);  // inner border

    draw(g);
}


    public void draw(Graphics g) {
        // Points
        g.setColor(Color.red);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        // Snake Head
        g.setColor(Color.green);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        // Snake Body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        // Score
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize * 2, tileSize);

        // Game Over message
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.RED);
            String gameOverText = "Game Over! Score: " + snakeBody.size();
            int textWidth = g.getFontMetrics().stringWidth(gameOverText);
            g.drawString(gameOverText, boardWidth/2 - textWidth/2, boardHeight/2);
        }
    }

    public void placeFood() {
        food.x = random.nextInt((boardWidth / tileSize)-1);
        food.y = random.nextInt((boardHeight / tileSize) -1);
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move(){
        //eat food
        if (collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        //Snake Body
        for (int i = snakeBody.size()-1; i >= 0; i--){
            Tile snakePart = snakeBody.get(i);
            if(i==0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        //Snake Head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //Game over conditions
        for(int i = 0; i < snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);

            //Game ends when collision with head
            if (collision(snakeHead,snakePart)){
                gameOver = true;
            }
        }
        if (snakeHead.x * tileSize < 0 || snakeHead.x * tileSize >= getWidth() ||
    snakeHead.y * tileSize < 0 || snakeHead.y * tileSize >= getHeight()) {
    gameOver = true;
}

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            gameLoop.stop();
            // Create and show game over menu
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.remove(this);
                GameOverMenu gameOverMenu = new GameOverMenu(frame, boardWidth, boardHeight, snakeBody.size());
                frame.add(gameOverMenu, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        }

        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }

        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
