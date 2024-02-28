import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeatingHeart extends JPanel implements ActionListener {
    private int heartX = 50; // Initial position of the heart
    private int heartY = 50;
    private int direction = 1; // Direction of movement along the line
    private int heartbeatWidth = 40; // Initial width of the heart
    private int heartbeatHeight = 40; // Initial height of the heart

    private Timer timer;

    public BeatingHeart() {
        timer = new Timer(50, this); // Timer to update animation every 50 milliseconds
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the line
        g2d.drawLine(50, 100, 550, 100);

        // Draw the heart
        g2d.setColor(Color.RED);

        // Draw the left part of the heart (elliptical shape)
        g2d.fillArc(heartX - heartbeatWidth / 2, heartY - heartbeatHeight / 2, heartbeatWidth / 2, heartbeatHeight, 0,
                180);

        // Draw the right part of the heart (elliptical shape)
        g2d.fillArc(heartX, heartY - heartbeatHeight / 2, heartbeatWidth / 2, heartbeatHeight, 0, 180);

        // Draw the triangle for the bottom of the heart
        int[] xPoints = { heartX - heartbeatWidth / 2, heartX + heartbeatWidth / 2, heartX };
        int[] yPoints = { heartY, heartY, heartY + heartbeatHeight };
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the heart's position along the line
        heartX += direction;

        // Adjust direction when reaching the end of the line
        if (heartX >= 550 || heartX <= 50) {
            direction *= -1;
        }

        // Update the size of the heart for the beating effect
        if (heartbeatWidth < 60 && heartbeatHeight < 60) {
            heartbeatWidth++;
            heartbeatHeight++;
        } else {
            heartbeatWidth = 40;
            heartbeatHeight = 40;
        }

        repaint(); // Request repaint to update the animation
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Beating Heart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);
        frame.add(new BeatingHeart());
        frame.setVisible(true);
    }
}
