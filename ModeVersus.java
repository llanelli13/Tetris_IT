import javax.swing.*;
import java.awt.*;

public class ModeVersus extends JFrame{


    private final GameArea firstPlayerArea;
    private final GameArea secondPlayerArea;
    private JPanel firstPlayerPanel, secondPlayerPanel;

    TetrisMusic tetrisMusic = new TetrisMusic();

    public enum GameMode {
        SOLO,
        VERSUS
    }

    private GameMode gameMode;
    private int speed;

    public ModeVersus(int speed) {
        this.speed = speed;
        setTitle("Versus");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());


        tetrisMusic.playGameplayMusic();
        firstPlayerArea = new GameArea(GameArea.GameMode.VERSUS, 9999999, speed);
        secondPlayerArea = new GameArea(GameArea.GameMode.VERSUS, 9999999, speed);
        firstPlayerArea.setSecondPlayerArea(secondPlayerArea); // Pass the secondPlayerArea reference

        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.add(firstPlayerArea);
        mainPanel.add(createVSLabel());
        mainPanel.add(secondPlayerArea);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JLabel createVSLabel() {
        JLabel vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Arial", Font.BOLD, 32));
        vsLabel.setForeground(Color.WHITE);
        vsLabel.setBackground(Color.BLACK);
        vsLabel.setOpaque(true);
        vsLabel.setBorder(BorderFactory.createEmptyBorder(15, 45, 15, 45));
        return vsLabel;
    }

}
