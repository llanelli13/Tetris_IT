import javax.swing.*;

public class GameWindow {

    public static final int width = 445, height = 629;
    private GameArea gameArea;
    private JFrame window;

    public GameWindow(){
        window = new JFrame("Tetris");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        gameArea = new GameArea();
        window.add(gameArea);
        window.addKeyListener(gameArea);
        window.addKeyListener(gameArea);
        window.setVisible(true);
    }

    public static void main(String[] args){
        new GameWindow();
    }
}
