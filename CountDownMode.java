import javax.swing.*;
import java.awt.*;

public class CountDownMode extends JFrame {

    private GameArea gameArea;

    public CountDownMode(){
        setTitle("CountDown");
        setSize(450, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        gameArea = new GameArea(GameArea.GameMode.COUNTDOWN, 20);
        add(gameArea, BorderLayout.CENTER);
        addKeyListener(gameArea);

        setVisible(true);
    }
}
