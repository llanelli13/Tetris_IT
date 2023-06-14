import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameArea extends JPanel implements KeyListener{

    private static int FPS = 60;
    private static int delay = 1000 / FPS;
    public static final int GameArea_width = 10;
    public static final int GameArea_height = 20;
    public static final int block_size = 30;
    private Timer score;
    private Color[][] gamearea = new Color[GameArea_width][GameArea_height];
    private int[][] shape1 = {
            {1, 1, 1},
            {0, 1, 0}
    };

    private Block block;


    public GameArea(){
        block = new Block(shape1);
        score = new Timer(delay, new ActionListener() {
            int n = 0;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                update();
                repaint();
            }
        });
        score.start();

    }

    private void update(){
        block.update();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        block.render(g);

        g.setColor(Color.white);
        for (int i=0; i<GameArea_height; i++){
            g.drawLine(0, block_size * i, block_size*GameArea_width, block_size*i);
        }
        for (int j=0; j<GameArea_width + 1; j++){
            g.drawLine(j*block_size, 0, j * block_size, block_size*GameArea_height);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            block.FastSpeed();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            block.Left();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            block.Right();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            block.NormalSpeed();
        }
    }
}
