import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Player extends Entity implements IPaint {

    public String direction;
    public long powerTime = 0;
    private Image up, down, left, right;
    private static int speed = 3;

    public Player(int x, int y) {
        super(x, y);
        direction = null;
        up = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pacman/up.png"));
        down = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pacman/down.png"));
        left = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pacman/left.png"));
        right = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pacman/right.png"));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    private boolean isBorder(Tile tile) {
        if(tile instanceof FruitTile || tile instanceof PelletTile || tile instanceof PowerPelletTile) {
            return false;
        }
        return true;
    }

    private void getPowerup() {
        this.powerTime = System.currentTimeMillis();
    }

    public void changeDirection(String desiredDirection, ArrayList<Tile> tiles) {
        Rectangle newPosition = new Rectangle();
        if(desiredDirection == "LEFT") {
            newPosition = new Rectangle(x - speed + 4, y + 4, 24, 24);
        } else if(desiredDirection == "RIGHT") {
            newPosition = new Rectangle(x + speed + 4, y + 4, 24, 24);
        } else if(desiredDirection == "UP") {
            newPosition = new Rectangle(x + 4, y - speed + 4, 24, 24);
        } else if(desiredDirection == "DOWN") {
            newPosition = new Rectangle(x + 4, y + speed + 4, 24, 24);
        }
        if(willHitBorder(newPosition, tiles)) {
            desiredDirection = direction;
        }
        this.direction = desiredDirection;
    }

    private boolean willHitBorder(Rectangle newPosition, ArrayList<Tile> tiles) {
        for(Tile tile : tiles) {
            if(isBorder(tile) && tile.getBounds().intersects(newPosition)) {
                return true;
            }
        }
        return false;
    }

    private void checkEatFruit(Rectangle newPosition, ArrayList<Tile> tiles) {
        for(Tile tile : tiles) {
            if(tile.getBounds().intersects(newPosition)) {
                if(tile instanceof PelletTile) {
                    ((PelletTile) tile).eat();
                } else if(tile instanceof FruitTile) {
                    ((FruitTile) tile).eatFruit();
                } else if(tile instanceof PowerPelletTile) {
                    if(!((PowerPelletTile) tile).eaten) {
                        ((PowerPelletTile) tile).eat();
                        getPowerup();
                    }
                }
            }
        }
    }

    public void move(ArrayList<Tile> tiles, ArrayList<Ghost> ghosts) {
        int dx = 0, dy = 0;
        if(direction == "LEFT") {
            dx = -speed;
        } else if(direction == "RIGHT") {
            dx = speed;
        } else if(direction == "UP") {
            dy = -speed;
        } else if(direction == "DOWN") {
            dy = speed;
        }
        Rectangle newPosition = new Rectangle(x + dx + 4, y + dy + 4, 24, 24);
        if(willHitBorder(newPosition, tiles)) {
            dx = 0;
            dy = 0;
        }
        checkEatFruit(newPosition, tiles);
        for(Ghost ghost : ghosts) {
            if(ghost.getBounds().intersects(getBounds())) {
                if(powerUpActive()) {
                    ghost.eaten();
                    GAMESTATES.setSCORE(GAMESTATES.getSCORE() + 50);
                } else {
                    GAMESTATES.endGame();
                }
            }
        }
        x += dx;
        y += dy;
    }

    public boolean powerUpActive() {
        return System.currentTimeMillis() - powerTime <= 5000;
    }

    @Override
    public void paintImage(Graphics g) {
        Image image = null;
        if(direction == "LEFT") {
            image = left;
        } else if(direction == "RIGHT") {
            image = right;
        } else if(direction == "UP") {
            image = up;
        } else if(direction == "DOWN") {
            image = down;
        }
        if(image != null) {
            g.drawImage(image, x, y, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillOval(x + 4, y + 4, 24, 24);
        }
    }

}
