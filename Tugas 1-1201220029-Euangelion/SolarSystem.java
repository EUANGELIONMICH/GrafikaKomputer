import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class SolarSystem extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SUN_RADIUS = 20;
    private static final int PLANET_RADIUS = 10;
    private static final int ORBIT_RADIUS = 200;
    private static final int FRAMES_PER_SECOND = 30;
    private static final int TOTAL_ROTATIONS = 365;
    private static final double ANGLE_INCREMENT = 2 * Math.PI / (FRAMES_PER_SECOND * TOTAL_ROTATIONS);

    private double planetAngle = 0;

    public SolarSystem() {
        Timer timer = new Timer(1000 / FRAMES_PER_SECOND, e -> {
            planetAngle += ANGLE_INCREMENT;
            if (planetAngle >= 2 * Math.PI * TOTAL_ROTATIONS) {
                planetAngle = 0; // Reset the angle after completing the rotations
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw sun at origin
        Ellipse2D sun = new Ellipse2D.Double(WIDTH / 2 - SUN_RADIUS, HEIGHT / 2 - SUN_RADIUS, 2 * SUN_RADIUS, 2 * SUN_RADIUS);
        g2d.setColor(Color.YELLOW);
        g2d.fill(sun);

        // Calculate planet position
        double planetX = WIDTH / 2 + ORBIT_RADIUS * Math.cos(planetAngle);
        double planetY = HEIGHT / 2 - ORBIT_RADIUS * Math.sin(planetAngle);

        // Draw planet orbit
        Ellipse2D orbit = new Ellipse2D.Double(WIDTH / 2 - ORBIT_RADIUS, HEIGHT / 2 - ORBIT_RADIUS, 2 * ORBIT_RADIUS, 2 * ORBIT_RADIUS);
        g2d.setColor(Color.GRAY);
        g2d.draw(orbit);

        // Draw planet
        GradientPaint planetColor = new GradientPaint(
                (float) (planetX - PLANET_RADIUS), (float) planetY, Color.BLUE,
                (float) (planetX + PLANET_RADIUS), (float) planetY, Color.WHITE);
        g2d.setPaint(planetColor);
        Ellipse2D planet = new Ellipse2D.Double(planetX - PLANET_RADIUS, planetY - PLANET_RADIUS, 2 * PLANET_RADIUS, 2 * PLANET_RADIUS);
        g2d.fill(planet);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Animation");
        SolarSystem animation = new SolarSystem();
        frame.add(animation);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}