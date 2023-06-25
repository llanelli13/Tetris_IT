import javax.swing.*;
import java.awt.*;

public class GameWindow {

    public static final int width = 445, height = 700;
    private final GameArea gameArea;
    private final JFrame window;

    public GameWindow() {
        window = new JFrame("Tetris");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        // Utilisation de JPanel pour la mise en page de la fenêtre
        JPanel contentPane = new JPanel(new BorderLayout());
        window.setContentPane(contentPane);

        JLabel background = new JLabel(new ImageIcon("fond_tetris.png"));
        contentPane.add(background, BorderLayout.CENTER);

        gameArea = new GameArea(GameArea.GameMode.SOLO, 99999999);
        contentPane.add(gameArea, BorderLayout.SOUTH);
        window.addKeyListener(gameArea);

        window.setVisible(true);
    }
}
