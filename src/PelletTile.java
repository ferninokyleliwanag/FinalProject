import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class PelletTile extends Tile {

    public Image pellet;
    public Image blank;
    public boolean eaten = false;

    public PelletTile(int x, int y) {
        super(null, x, y);
        pellet = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pellets/pellet.png"));
        blank = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pellets/blank.png"));
    }

    public void eat() {
        if(!eaten) {
            GAMESTATES.setSCORE(GAMESTATES.getSCORE() + 10);
            eaten = true;
            try {
                // Open an audio input stream.
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/sound/eat.wav"));
                // Get a sound clip resource.
                Clip clip = AudioSystem.getClip();
                // Open audio clip and load samples from the audio input stream.
                clip.open(audioIn);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 4, y + 4, 8, 8);
    }

    @Override
    public void paintImage(Graphics g) {
        if (!eaten) {
            g.drawImage(pellet, x, y, null);
        } else {
            g.drawImage(blank, x, y, null);
        }
    }

}
