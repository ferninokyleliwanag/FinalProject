import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile implements IPaint {

    public Image image;
    public int x;
    public int y;

    public Tile(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32 ,32);
    }

    @Override
    public void paintImage(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image, x, y, null);
    }



}
