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
        timer.start();
    }

    public void setup() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        if(GAMESTATES.isMENU()) {
            g.setFont(new Font("Courier", Font.BOLD, 48));
            printSimpleString("Pacman", getWidth(), 0, getHeight()/2, g);
            g.setFont(new Font("Courier", Font.BOLD, 24));
            printSimpleString("Press Enter to start", getWidth(), 0, getHeight()/2 + 50, g);
        } else if(GAMESTATES.isPLAY()) {
            g.setFont(new Font("Courier", Font.PLAIN, 24));
            printSimpleString("Score: " + GAMESTATES.getSCORE(), 0, getWidth()/16, 25, g);
        } else if(GAMESTATES.isPAUSE()) {
            g.setFont(new Font("Courier", Font.BOLD, 48));
            printSimpleString("Paused", getWidth(), 0, getHeight()/2, g);
            g.setFont(new Font("Courier", Font.BOLD, 24));
            printSimpleString("Press P to resume", getWidth(), 0, getHeight()/2 + 50, g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s, start + XPos, YPos);
    }

}
