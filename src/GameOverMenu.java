import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverMenu extends JPanel implements ActionListener, MouseListener {
    private JFrame frame;
    private JButton restartButton;
    private JButton exitButton;
    private JLabel scoreLabel;
    private int boardWidth;
    private int boardHeight;
    private int finalScore;

    public GameOverMenu(JFrame frame, int boardWidth, int boardHeight, int score) {
        this.frame = frame;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.finalScore = score;
        
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        
        // Game Over Title
        JLabel gameOverTitle = new JLabel("GAME OVER");
        gameOverTitle.setFont(new Font("Arial", Font.BOLD, 34));
        gameOverTitle.setForeground(Color.RED);
        gameOverTitle.setBounds(boardWidth/2 - 160, 100, 320, 80);
        
        // Score
        scoreLabel = new JLabel("Score: " + finalScore);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(boardWidth/2 - 120, 200, 240, 50);
        
    // Restart Button
restartButton = new JButton("Restart");
restartButton.setFont(new Font("Arial", Font.BOLD, 20));
restartButton.setBounds(boardWidth/2 - 130, boardHeight/2, 160, 50); // moved 50px left
restartButton.addActionListener(this);

// Exit Button
exitButton = new JButton("Exit");
exitButton.setFont(new Font("Arial", Font.BOLD, 20));
exitButton.setBounds(boardWidth/2 - 130, boardHeight/2 + 70, 160, 50); // moved 50px left
exitButton.addActionListener(this);

        
        // Add components
        add(gameOverTitle);
        add(scoreLabel);
        add(restartButton);
        add(exitButton);
        
        // Add mouse listener for hover effects
        restartButton.addMouseListener(this);
        exitButton.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            // Remove game over menu and start a new game
            frame.remove(this);
            SnakeApp newGame = new SnakeApp(boardWidth, boardHeight);
            frame.add(newGame, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            newGame.requestFocus(); // Give focus to new game
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, boardWidth, boardHeight);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == restartButton) {
            restartButton.setBackground(Color.GREEN);
        } else if (e.getSource() == exitButton) {
            exitButton.setBackground(Color.RED);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == restartButton) {
            restartButton.setBackground(UIManager.getColor("Button.background"));
        } else if (e.getSource() == exitButton) {
            exitButton.setBackground(UIManager.getColor("Button.background"));
        }
    }
}
