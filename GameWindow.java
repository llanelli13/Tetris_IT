import javax.swing.*;
import java.awt.*;

public class GameWindow {

    public static final int width = 445, height = 700;
    private GameArea gameArea;
    private JFrame window;

    public GameWindow(){
        window = new JFrame("Tetris");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        // Utilisation de BorderLayout pour la fenêtre
        window.setLayout(new BorderLayout());

        gameArea = new GameArea();
        window.add(gameArea, BorderLayout.CENTER);
        window.addKeyListener(gameArea);



        window.setVisible(true);
    }
}
