import java.awt.*;

public class BorderTiles {

    public static class MiddleTopBorder extends Tile {
        public MiddleTopBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border\\middle-top.png"), x, y);
        }
    }

    public static class MiddleBotBorder extends Tile {
        public MiddleBotBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border\\middle-bot.png"), x, y);
        }
    }

    public static class CenterLeftBorder extends Tile {
        public CenterLeftBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border\\center-left.png"), x, y);
        }
    }

    public static class CenterRightBorder extends Tile {
        public CenterRightBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border\\center-right.png"), x, y);
        }
    }

    public static class TopLeftCornerBorder extends Tile {
        public TopLeftCornerBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border\\top-left.png"), x, y);
        }
    }

    public static class BotLeftCornerBorder extends Tile {
        public BotLeftCornerBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border\\bot-left.png"), x, y);
        }
    }

    public static class TopRightCornerBorder extends Tile {
        public TopRightCornerBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border\\top-right.png"), x, y);
        }
    }

    public static class BotRightCornerBorder extends Tile {
        public BotRightCornerBorder(int x, int y) {
            super(Toolkit.getDefaultToolkit().getImage("border\\bot-right.png"), x, y);
        }
    }

}
