import javax.swing.*;
import java.awt.BorderLayout;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 600;
        int boardHeight = 600;

        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        
        // Create and show menu first
        Menu menu = new Menu(frame, boardWidth, boardHeight);
        frame.add(menu, BorderLayout.CENTER);
        
        // Set size after adding components
        frame.pack();
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
