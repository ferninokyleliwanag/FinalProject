import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JFrame {

    Maze maze;

    public Game() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pacman");
        setResizable(false);
        setVisible(true);
        this.maze = new Maze();
        add(this.maze);
        pack();
        setLocationRelativeTo(null);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (GAMESTATES.isMENU()) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        GAMESTATES.startGame();
                        maze.setup();
                    }
                } else if (GAMESTATES.isPLAY()) {
                    if (e.getKeyCode() == KeyEvent.VK_P) {
                        GAMESTATES.pauseGame();
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        maze.player.changeDirection("UP", maze.tiles);
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        maze.player.changeDirection("DOWN", maze.tiles);
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        maze.player.changeDirection("LEFT", maze.tiles);
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        maze.player.changeDirection("RIGHT", maze.tiles);
                    }
                } else if (GAMESTATES.isPAUSE()) {
                    if (e.getKeyCode() == KeyEvent.VK_P) {
                        GAMESTATES.resumeGame();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

    }

    public static void main(String[] args) {
        Game game = new Game();
        GAMESTATES.gameLaunch();
        game.maze.setup();
    }

}
