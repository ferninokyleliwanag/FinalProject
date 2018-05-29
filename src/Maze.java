import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Maze extends JPanel implements ActionListener {

    ArrayList<Tile> tiles;
    Timer timer;

    public Maze() {
        setPreferredSize(new Dimension(540, 960));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void setup() {

        tiles = new ArrayList<>();

        // Top and bottom border
        int columns = 15, rows = 30, padding = 30;
        for(int i = 0; i < columns; i++) {
            if(i == 0) {
                tiles.add(new BorderTiles.TopLeftCornerBorder((i * 32)+padding, 0));
                tiles.add(new BorderTiles.BotLeftCornerBorder((i * 32)+padding, getHeight()-32));
                for(int j = 1; j < rows - 1; j++) {
                    tiles.add(new BorderTiles.CenterLeftBorder((i * 32)+padding, j * 32));
                    tiles.add(new PelletTile(32 + padding, j * 32));
                }
            } else if(i == columns - 1) {
                tiles.add(new BorderTiles.TopRightCornerBorder((i * 32)+padding, 0));
                tiles.add(new BorderTiles.BotRightCornerBorder((i * 32)+padding, getHeight()-32));
                for(int j = 1; j < rows - 1; j++) {
                    tiles.add(new BorderTiles.CenterRightBorder((i * 32)+padding, j * 32));
                    tiles.add(new PelletTile(i*32, j * 32));
                }
            } else {
                tiles.add(new BorderTiles.MiddleTopBorder((i * 32)+padding, 0));
                tiles.add(new BorderTiles.MiddleBotBorder((i * 32)+padding, getHeight()-32));
            }
        }

        // Add fruit tile
        int x = (columns*16)+padding, y = (rows*16);
        tiles.add(new FruitTile(x, y));

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
            for(Tile tile : tiles) {
                tile.paintImage(g);
            }
        } else if(GAMESTATES.isPAUSE()) {
            g.setFont(new Font("Courier", Font.BOLD, 48));
            printSimpleString("Paused", getWidth(), 0, getHeight()/2, g);
            g.setFont(new Font("Courier", Font.BOLD, 24));
            printSimpleString("Press P to resume", getWidth(), 0, getHeight()/2 + 50, g);
        } else if(GAMESTATES.isEND()) {
            g.setFont(new Font("Courier", Font.BOLD, 48));
            printSimpleString("Game Over", getWidth(), 0, getHeight()/2, g);
            printSimpleString("You Scored: " + GAMESTATES.getSCORE(), getWidth(), 0, getHeight()/2 + 50, g);
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
