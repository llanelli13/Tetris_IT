import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;


public class Audio {

    private String soundFolder = "tetrissound" + File.separator;
    private String clearLinePath = soundFolder + "linecomplete3"; 
    private String GameOverPath = soundFolder + "loser2";

    private Clip clearLineSound, GameOverSound;

    public Audio() throws LineUnavailableException {
        try {
            clearLineSound = AudioSystem.getClip();
            GameOverSound = AudioSystem.getClip();

            AudioInputStream clearLineStream = AudioSystem.getAudioInputStream(new File(clearLinePath));
            clearLineSound.open(clearLineStream);

            AudioInputStream gameOverStream = AudioSystem.getAudioInputStream(new File(GameOverPath));
            GameOverSound.open(gameOverStream);
        } catch (Exception ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public void playClearLine() {
        clearLineSound.setFramePosition(0);
        clearLineSound.start();
    }

    public void playGameOver() {
        GameOverSound.setFramePosition(0);
        GameOverSound.start();
    }
}
