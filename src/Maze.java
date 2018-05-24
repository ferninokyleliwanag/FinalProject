import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maze extends JPanel implements ActionListener {

    Timer timer;

    public Maze() {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
    }

    public void setup() {
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
