import java.io.File;
import java.util.HashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class TetrisMusic {

    private static Clip currentClip;
    private static HashMap<String, Clip> playerMusicMap = new HashMap<>();

    public static void playMenuMusic() {
        playMusic("tetris_menu.wav", true);
    }

    public static void playLineCompleteMusic() {
        playMusic("linetetris.wav", false);
    }

    public static void playGameplayMusic() {
        playMusic("ingamemusic.wav", true);
    }

    public static void playGameOverMusic() {
        playMusic("gameovermusic.wav", false);
    }


    private static void playMusic(String filename, boolean loop) {
        try {
            File musicFile = new File(filename);
            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    currentClip = clip;
                }
                clip.start();
            } else {
                System.out.println("Can't find file: " + filename);
            }
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }

    public static void stopCurrentMusic() {
        if (currentClip != null) {
            currentClip.stop();
            currentClip.close();
            currentClip = null;
        }
    }

    public static void stopMusicForPlayer(String playerName) {
        Clip musicClip = playerMusicMap.get(playerName);
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }

}
