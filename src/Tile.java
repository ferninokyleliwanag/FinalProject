import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile implements IPaint {

    private Image image;
    private int x, y;

    public Tile(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintImage(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image, x, y, null);
    }

}
