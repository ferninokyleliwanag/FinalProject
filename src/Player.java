import java.awt.*;

public class Player extends Entity implements IPaint {

    private int dx, dy;

    public Player(int x, int y) {
        super(x, y);
	this.dx = 0;
	this.dy = 0;
    }

    public void setVelocity(int dx, int dy) {
	this.dx = dx;
	this.dy = dy;
    }

    public void move(ArrayList<Tile> tiles) {
	Rectangle newPosition = new Rectangle(x + dx, y + dy, 32, 32);
	int newDx = dx, newDy = dy;
	for(Tile tile : tiles) {
	    if(tile.getBounds().intersectsWith(newPosition)) {
		if(!(tile instanceof PelletTile) || !(tile instanceof FruitTile)) {
		    newDx = 0;
		    newDy = 0;
		} else if(tile instanceof FruitTile) {
		    ((FruitTile)tile).eatFruit();
		} else if(tile instanceof PelletTile) {
		    ((PelletTile)tile).eat();
		}
	    }
	}
	x += dx;
	y += dy;
    }

    @Override
    public void paintImage(Graphics g) {
        g.setColor(Color.YELLOW);
	g.fillOval(x, y, 32, 32);
    }

}
