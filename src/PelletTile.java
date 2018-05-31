import java.awt.*;

public class PelletTile extends Tile {

    public Image pellet;
    public Image blank;
    public boolean eaten = false;

    public PelletTile(int x, int y) {
        super(null, x, y);
        pellet = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pellets/pellet.png"));
        blank = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pellets/blank.png"));
    }

    public void eat() {
        if(!eaten) {
            GAMESTATES.setSCORE(GAMESTATES.getSCORE() + 10);
            eaten = true;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 4, y + 4, 8, 8);
    }

    @Override
    public void paintImage(Graphics g) {
        if (!eaten) {
            g.drawImage(pellet, x, y, null);
        } else {
            g.drawImage(blank, x, y, null);
        }
    }

}
