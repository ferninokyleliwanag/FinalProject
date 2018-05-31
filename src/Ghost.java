import java.awt.*;
import java.util.ArrayList;

public class Ghost {

    Image image;
    Image scared;
    public int x, y;
    public int homeX, homeY;

    public Ghost(String ghost, int x, int y) {
        this.x = x;
        this.homeX = x;
        this.y = y;
        this.homeY = y;
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ghost/" + ghost + ".png"));
        scared = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ghost/scared.png"));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 32);
    }

    private boolean isBorder(Tile tile) {
        if (tile instanceof FruitTile || tile instanceof PelletTile || tile instanceof PowerPelletTile) {
            return false;
        }
        return true;
    }

    public void eaten() {
        this.x = homeX;
        this.y = homeY;
    }

    private boolean willHitBorder(Rectangle newPosition, ArrayList<Tile> tiles) {
        for (Tile tile : tiles) {
            if (isBorder(tile) && tile.getBounds().intersects(newPosition)) {
                return true;
            }
        }
        return false;
    }

    public void move(Player player, ArrayList<Tile> tiles) {
        if (!player.powerUpActive()) {
            Rectangle newPosition = getBounds();
            if (player.x > x) {
                newPosition = new Rectangle(x + 1, y, 32, 32);
            } else if (player.x < x) {
                newPosition = new Rectangle(x - 1, y, 32, 32);
            }

            if (willHitBorder(newPosition, tiles) || newPosition == getBounds() || player.x == newPosition.x) {
                if (player.y >= y) {
                    newPosition = new Rectangle(x, y + 1, 32, 32);
                } else if (player.y < y) {
                    newPosition = new Rectangle(x, y - 1, 32, 32);
                }
                if (!willHitBorder(newPosition, tiles)) {
                    this.x = newPosition.x;
                    this.y = newPosition.y;
                }
            } else {
                this.x = newPosition.x;
                this.y = newPosition.y;
            }
        }
    }

    public void paintImage(Graphics g, Player p) {
        Image draw = image;
        if (p.powerUpActive()) {
            draw = scared;
        }
        g.drawImage(draw, x, y, null);
    }

}
