import java.awt.*;

public class PelletTile extends Tile {

    public boolean eaten = false;

    public PelletTile(int x, int y) {
        super(null, x, y);
    }

    public void eat() {
	GAMESTATES.setSCORE(GAMESTATES.getScore() + 10);
	eaten = false;
    }

    @Override
    public Rectangle getBounds() {
	return new Rectangle(x + 8, y + 8, 16, 16);
    }

    @Override
    public void paintImage(Graphics g) {
        g.setColor(Color.YELLOW);
        if(!eaten) {
            g.fillOval(x + 8, y + 8, 16, 16);
        }
    }

}
