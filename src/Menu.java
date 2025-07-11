import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JPanel implements ActionListener, MouseListener {
    private JFrame frame;
    private JButton startButton;
    private JButton exitButton;
    private JLabel title;
    private JLabel instructions;
    private int boardWidth;
    private int boardHeight;

    public Menu(JFrame frame, int boardWidth, int boardHeight) {
        this.frame = frame;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        
        // Title
        title = new JLabel("SNAKE GAME");
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.GREEN);
        title.setBounds(boardWidth/2 - 160, 100, 320, 80);
        
        // Instructions
        instructions = new JLabel("Use arrow keys to control the snake");
        instructions.setFont(new Font("Arial", Font.PLAIN, 24));
        instructions.setForeground(Color.WHITE);
        instructions.setBounds(boardWidth/2 - 200, 200, 400, 40);
        
     // Start Button
startButton = new JButton("Start Game");
startButton.setFont(new Font("Arial", Font.BOLD, 20));
startButton.setBounds(boardWidth/2 - 130, boardHeight/2, 160, 50); // moved 50px left
startButton.addActionListener(this);

// Exit Button
exitButton = new JButton("Exit");
exitButton.setFont(new Font("Arial", Font.BOLD, 20));
exitButton.setBounds(boardWidth/2 - 130, boardHeight/2 + 70, 160, 50); // moved 50px left
exitButton.addActionListener(this);

        
        // Add components
        add(title);
        add(instructions);
        add(startButton);
        add(exitButton);
        
        // Add mouse listener for hover effects
        startButton.addMouseListener(this);
        exitButton.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Remove menu and show game
            frame.remove(this);
            SnakeApp snakeGame = new SnakeApp(boardWidth, boardHeight);
            frame.add(snakeGame, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            snakeGame.requestFocus(); // Give focus to game panel
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
        if (e.getSource() == startButton) {
            startButton.setBackground(Color.GREEN);
        } else if (e.getSource() == exitButton) {
            exitButton.setBackground(Color.RED);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == startButton) {
            startButton.setBackground(UIManager.getColor("Button.background"));
        } else if (e.getSource() == exitButton) {
            exitButton.setBackground(UIManager.getColor("Button.background"));
        }
    }
}
