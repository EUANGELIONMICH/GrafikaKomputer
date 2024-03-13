import java.awt.*;
import javax.swing.*;

public class FractalPatternDrawer extends JPanel {
    
    private static final int CELL_SIZE = 20; // Size of each cell in the grid
    private static final int PATTERN_LENGTH = 5; // Length of the repeating pattern

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int width = getWidth();
        int height = getHeight();
        
        // Calculate the position for drawing the pattern
        int x = width / 2 - CELL_SIZE * PATTERN_LENGTH / 2;
        int y = height / 2 - CELL_SIZE * PATTERN_LENGTH / 2;
        
        // Draw X-axis
        g.drawLine(0, height / 2, width, height / 2);
        // Draw Y-axis
        g.drawLine(width / 2, 0, width / 2, height);
        
        // Draw pattern
        for (int i = 0; i < PATTERN_LENGTH; i++) {
            switch (i) {
                case 0:
                    // Draw a filled circle
                    g.fillOval(x + CELL_SIZE / 4, y + CELL_SIZE / 4, CELL_SIZE / 2, CELL_SIZE / 2);
                    break;
                case 1:
                case 2:
                    // Draw an empty circle
                    g.drawOval(x + CELL_SIZE / 4, y + CELL_SIZE / 4, CELL_SIZE / 2, CELL_SIZE / 2);
                    break;
                case 3:
                case 4:
                    // Draw a double circle
                    g.drawOval(x + CELL_SIZE / 4, y + CELL_SIZE / 4, CELL_SIZE / 2, CELL_SIZE / 2);
                    g.drawOval(x + CELL_SIZE / 3, y + CELL_SIZE / 3, CELL_SIZE / 3, CELL_SIZE / 3);
                    break;
            }
            x += CELL_SIZE;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fractal Pattern");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.add(new FractalPatternDrawer());
            frame.setVisible(true);
        });
    }
}
