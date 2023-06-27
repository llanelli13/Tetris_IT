import javax.swing.*;
import java.awt.*;

public class CountDownMode extends JFrame {

    private final GameArea gameArea;
    private final int time;

    TetrisMusic tetrisMusic = new TetrisMusic();

    public CountDownMode(int time){
        this.time = time;
        setTitle("CountDown");
        setSize(450, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        gameArea = new GameArea(GameArea.GameMode.COUNTDOWN, time, 600);
        add(gameArea, BorderLayout.CENTER);
        addKeyListener(gameArea);

        setVisible(true);
    }
}
