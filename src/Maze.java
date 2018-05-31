import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Maze extends JPanel implements ActionListener {

    public Player player;
    public ArrayList<Tile> tiles;
    public ArrayList<Ghost> ghosts;
    private Timer timer;

    public Maze() {
        setPreferredSize(new Dimension(508, 896));
        setBackground(Color.BLACK);
        timer = new Timer(1000 / 60, this);
        timer.start();
    }

    public void makeBasicBlock(int x, int y, int width, int height, int hpadding) {
        tiles.add(new BorderTiles.TopLeftCornerBorder((x*32) + hpadding, y*32));
        tiles.add(new BorderTiles.TopRightCornerBorder(((x+width)*32) + hpadding, y*32));
        tiles.add(new BorderTiles.BotLeftCornerBorder((x*32)+hpadding, (y+height)*32));
        tiles.add(new BorderTiles.BotRightCornerBorder(((x+width)*32) + hpadding, (y+height)*32));
        for(int i = 1; i < width; i++) {
            tiles.add(new BorderTiles.MiddleTopBorder(((x+i)*32) + hpadding, y*32));
            tiles.add(new BorderTiles.MiddleBotBorder(((x+i)*32) + hpadding, (y+height)*32));
        }
        for(int i = 1; i < height; i++) {
            tiles.add(new BorderTiles.CenterLeftBorder((x*32)+hpadding, (y+i)*32));
            tiles.add(new BorderTiles.CenterRightBorder(((x+width)*32)+hpadding, (y+i)*32));
        }
    }

    public void putPellet(int x, int y, int padding) {
        tiles.add(new PelletTile((x * 32) + padding, y * 32));
    }

    public void putPower(int x, int y, int padding) {
        tiles.add(new PowerPelletTile((x * 32) + padding, y *32));
    }

    public void putFruit(int x, int y, int padding) {
        tiles.add(new FruitTile((x * 32) + padding, y * 32));
    }

    public void putPlayer(int x, int y, int padding) {
        player.x = (x * 32) + padding;
        player.y = (y * 32) + padding;
    }

    public void putGhost(String color, int x, int y, int padding) {
        ghosts.add(new Ghost(color, (x*32)+padding, y*32));
    }

    public void setup() {

        tiles = new ArrayList<>();
        ghosts = new ArrayList<>();
        // Build Field
        int columns = 15, rows = 28, padding = 14;
        makeBasicBlock(0, 0, columns - 1, rows - 1, padding);


        // Blocks
        makeBasicBlock(2, 3, 4, 3, padding);
        makeBasicBlock(8, 3, 4, 3, padding);
        makeBasicBlock(2, 20, 2, 4, padding);
        makeBasicBlock(10, 20, 2, 4, padding);
        makeBasicBlock(6, 22, 2, 2, padding);
        makeBasicBlock(2, 16, 10, 1, padding);
        makeBasicBlock(2, 9, 10, 2, padding);

        // Spawn player
        player = new Player(0, 0);
        putPlayer(columns/2, rows/2, padding);
        putFruit(7, 21, padding);

        // Add ghosts
        putGhost("red", (int)(Math.random()*(columns-2))+2, rows - 2, padding);
        putGhost("orange", 1, (int)(Math.random()*(rows-3))+3, padding);
        putGhost("blue", player.x/32, 1, padding);

        // Add pellets
        putPower(1, 1, padding);
        putPower(columns - 2, 1, padding);
        putPower(1, rows - 2, padding);
        putPower(columns - 2, rows - 2, padding);
        // Border
        for(int i = 2; i < columns - 2; i++) {
            putPellet(i, 1, padding);
            putPellet(i, rows - 2, padding);
            putPellet(i, 18, padding);
            putPellet(i, 7, padding);
        }
        for(int i = 2; i < rows - 2; i++) {
            putPellet(1, i, padding);
            putPellet(columns - 2, i, padding);
        }
        for(int i = 2; i < 7; i++) {
            putPellet(7, i, padding);
        }
        for(int i = 19; i < 26; i++) {
            putPellet(5, i, padding);
            putPellet(9, i, padding);
        }
        putPellet(2, 2, padding);
        putPellet(columns - 3, 2, padding);
        putPellet(2, rows - 3, padding);
        putPellet(columns - 3, rows - 3, padding);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        if (GAMESTATES.isMENU()) {
            g.setFont(new Font("Courier", Font.BOLD, 48));
            printSimpleString("Pacman", getWidth(), 0, getHeight() / 2, g);
            g.setFont(new Font("Courier", Font.BOLD, 24));
            printSimpleString("Press Enter to start", getWidth(), 0, getHeight() / 2 + 50, g);
        } else if (GAMESTATES.isPLAY()) {
            g.setFont(new Font("Courier", Font.BOLD, 36));
            if(player.powerUpActive()) {
                printSimpleString("Score: " + GAMESTATES.getSCORE() + "!", getWidth(), 0, getHeight() / 2 - 100, g);
            } else {
                printSimpleString("Score: " + GAMESTATES.getSCORE(), getWidth(), 0, getHeight() / 2 - 100, g);
            }
            player.move(tiles, ghosts);
            for (Tile tile : tiles) {
                tile.paintImage(g);
            }
            for (Ghost ghost : ghosts) {
                ghost.move(player, tiles);
                ghost.paintImage(g, player);
            }
            if(countPellets() <= 0) {
                GAMESTATES.endGame();
            }
            player.paintImage(g);
        } else if (GAMESTATES.isPAUSE()) {
            g.setFont(new Font("Courier", Font.BOLD, 48));
            printSimpleString("Paused", getWidth(), 0, getHeight() / 2, g);
            g.setFont(new Font("Courier", Font.BOLD, 24));
            printSimpleString("Press P to resume", getWidth(), 0, getHeight() / 2 + 50, g);
        } else if (GAMESTATES.isEND()) {
            g.setFont(new Font("Courier", Font.BOLD, 48));
            printSimpleString("Game Over", getWidth(), 0, getHeight() / 2, g);
            printSimpleString("You Scored: " + GAMESTATES.getSCORE(), getWidth(), 0, getHeight() / 2 + 50, g);
        }
    }

    public int countPellets() {
        int count = 0;
        for (Tile tile : tiles) {
            if(tile instanceof PelletTile) {
                if(!((PelletTile) tile).eaten) {
                    count++;
                }
            } else if(tile instanceof PowerPelletTile) {
                if(!((PowerPelletTile) tile).eaten) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d) {
        int stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width / 2 - stringLen / 2;
        g2d.drawString(s, start + XPos, YPos);
    }

}
