import javax.swing.*;
import java.awt.*;


public class GameWindow {

    public static final int width = 445, height = 700;
    private final GameArea gameArea;
    private final JFrame window;

    TetrisMusic tetrisMusic = new TetrisMusic();
    public GameWindow(int speed){
        window = new JFrame("Tetris");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);


        // Utilisation de BorderLayout pour la fenêtre
        window.setLayout(new BorderLayout());

        gameArea = new GameArea(GameArea.GameMode.SOLO, 99999999, speed);
        window.add(gameArea, BorderLayout.CENTER);
        window.addKeyListener(gameArea);

        window.setVisible(true);
    }
}
