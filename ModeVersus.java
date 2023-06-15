import javax.swing.*;
import java.awt.*;

public class ModeVersus extends JFrame{


    private GameArea firstPlayerArea, secondPlayerArea;
    private JPanel firstPlayerPanel, secondPlayerPanel;

    public enum GameMode {
        SOLO,
        VERSUS
    }

    private GameMode gameMode;

    public ModeVersus() {
        setTitle("Versus");
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(1, 2));

        firstPlayerArea = new GameArea(GameArea.GameMode.VERSUS);
        secondPlayerArea = firstPlayerArea.getSecondPlayerArea();


        add(firstPlayerArea);
        add(secondPlayerArea);

        setVisible(true);
    }
}
