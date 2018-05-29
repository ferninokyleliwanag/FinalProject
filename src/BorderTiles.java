import java.awt.*;

public class BorderTiles {

    public static class MiddleBorder extends Tile {
        public MiddleBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border/middle.png"), x, y);
        }
    }

    public static class CenterBorder extends Tile {
        public CenterBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border/center.png"), x, y);
        }
    }

    public static class TopLeftCornerBorder extends Tile {
        public TopLeftCornerBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border/top-left-corner.png"), x, y);
        }
    }

    public static class BotLeftCornerBorder extends Tile {
        public BotLeftCornerBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border/bot-left-corner.png"), x, y);
        }
    }

    public static class TopRightCornerBorder extends Tile {
        public TopRightCornerBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border/top-right-corner.png"), x, y);
        }
    }

    public static class BotRightCornerBorder extends Tile {
        public BotRightCornerBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border/bot-right-corner.png"), x, y);
        }
    }

}
