import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class Initials extends JPanel implements Runnable {
    private int frame = 0;
    private boolean animasiSelesai = false;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Mula-mula gambar huruf M
        Font font = new Font("Arial", Font.PLAIN, 50);
        g2d.setFont(font);
        if (!animasiSelesai) {
            switch (frame) {
                case 0:
                case 1:
                    g2d.drawString("M", 50, 100);
                    break;
                case 2:
                case 3:
                    // Transformasi ke huruf D
                    AffineTransform transform = new AffineTransform();
                    transform.translate(50, 100);
                    g2d.transform(transform);
                    g2d.drawString("D", 0, 0);
                    break;
                default:
                    // Transformasi selesai
                    g2d.drawString("D", 50, 100);
                    animasiSelesai = true;
                    break;
            }
        } else {
            // Transformasi selesai, gambar huruf D
            g2d.drawString("D", 50, 100);
        }
    }

    @Override
    public void run() {
        try {
            while (!animasiSelesai) {
                Thread.sleep(1000); // Tunggu 1 detik
                frame++;
                if (frame > 4)
                    frame = 0;
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animasi Transformasi Huruf M ke D");
        Initials panel = new Initials();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(panel).start(); // Mulai animasi di thread terpisah
    }
}
