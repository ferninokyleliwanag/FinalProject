import java.awt.*;

public class FruitTile extends Tile {

    public long lastFruitTime = 0;
    public int fruit = 0;

    public FruitTile(int x, int y) {
        super(null, x, y);
    }

    public void eatFruit() {
        lastFruitTime = System.currentTimeMillis();
        GAMESTATES.setSCORE(GAMESTATES.getSCORE() + (fruit * 100));
        fruit = 0;
    }

    private Color getColor() {
        switch(fruit) {
            case 1: return Color.RED;
            case 2: return Color.GREEN;
            case 3: return Color.BLUE;
            case 4: return Color.ORANGE;
            case 5: return Color.MAGENTA;
            default: return Color.BLACK;
        }
    }

    @Override
    public void paintImage(Graphics g) {
        if(System.currentTimeMillis() - lastFruitTime >= 7500 && fruit == 0) {
            fruit = (int)(Math.random() * 6);
        }
        g.setFont(new Font("Comic Sans Ms", Font.BOLD, 12));
        if(fruit != 0) {
            printSimpleString("" + fruit * 100, 32, x, y + 16, g);
            g.setColor(getColor());
            g.drawRect(x, y, 32, 32);
        }
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s, start + XPos, YPos);
    }

}
