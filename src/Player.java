import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
	    if(tile.getBounds().intersects(newPosition)) {
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


    private static BufferedImage spriteSheet;
    public static final int TILE_SIZE = 32;

    public static BufferedImage loadSprite(String file){

        BufferedImage sprite = null;

        try{
            sprite = ImageIO.read(new File("res/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;

    }

    public static BufferedImage getSprite(int xGrid, int yGrid){

        if(spriteSheet == null){
            spriteSheet = loadSprite("pacman sprite sheet");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid*TILE_SIZE,TILE_SIZE, TILE_SIZE);
    }



}
