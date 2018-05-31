import java.awt.*;

public class BorderTiles {

    public static class MiddleTopBorder extends Tile {
        public MiddleTopBorder(int x, int y) {
            super(null, x, y);
            this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/border/middle-top.png"));
        }
    }

    public static class MiddleBotBorder extends Tile {
        public MiddleBotBorder(int x, int y) {
            super(null, x, y);
            this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/border/middle-bot.png"));
        }
    }

    public static class CenterLeftBorder extends Tile {
        public CenterLeftBorder(int x, int y) {
            super(null, x, y);
            this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/border/center-left.png"));
        }

        @Override
        public Rectangle getBounds() {
            return new Rectangle(x, y, 24, 32);
        }
    }

    public static class CenterRightBorder extends Tile {
        public CenterRightBorder(int x, int y) {
            super(null, x, y);
            this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/border/center-right.png"));
        }

        @Override
        public Rectangle getBounds() {
            return new Rectangle(x + 2, y, 24, 32);
        }
    }

    public static class TopLeftCornerBorder extends Tile {
        public TopLeftCornerBorder(int x, int y) {
            super(null, x, y);
            this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/border/top-left.png"));
        }

        @Override
        public Rectangle getBounds() {
            return new Rectangle(x, y, 24, 32);
        }
    }

    public static class BotLeftCornerBorder extends Tile {
        public BotLeftCornerBorder(int x, int y) {
            super(null, x, y);
            this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/border/bot-left.png"));
        }

        @Override
        public Rectangle getBounds() {
            return new Rectangle(x, y, 24, 32);
        }
    }

    public static class TopRightCornerBorder extends Tile {
        public TopRightCornerBorder(int x, int y) {
            super(null, x, y);
            this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/border/top-right.png"));
        }

        @Override
        public Rectangle getBounds() {
            return new Rectangle(x + 2, y, 24, 32);
        }
    }

    public static class BotRightCornerBorder extends Tile {
        public BotRightCornerBorder(int x, int y) {
            super(null, x, y);
            this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/border/bot-right.png"));
        }

        @Override
        public Rectangle getBounds() {
            return new Rectangle(x + 2, y, 24, 32);
        }
    }

}
