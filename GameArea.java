import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameArea extends JPanel implements KeyListener{
    public static int STATE_GAME_PLAY = 0;
    public static int STATE_GAME_PAUSE = 1;
    public static int STATE_GAME_OVER = 2;

    private int state = STATE_GAME_PLAY;

    private static int FPS = 60;
    private static int delay = 1000 / FPS;
    public static final int GameArea_width = 10;
    public static final int GameArea_height = 20;
    public static final int block_size = 30;
    private Timer score;
    private Color[][] gamearea = new Color[GameArea_height][GameArea_width];
    private Block[] blocks = new Block[7];

    private Block block;
    private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"),
                                Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a249a4"), Color.decode("#3f48cc")};

    private Block currentBlock;

    private Random random;
    private String timerString = "";
    public int points = 0;
    private Block nextBlock;
    public enum GameMode {
        SOLO,
        VERSUS
    }

    private GameMode gameMode;
    private GameArea secondPlayerArea;




    public GameArea(GameMode gameMode){

        this.gameMode = gameMode;
        random = new Random();
        blocks[0] = new Block(new int[][]{
                {1,1,1,1}}, this, colors[0]);

        blocks[1] = new Block(new int[][]{
                {1,1,1},
                {0,1,0}},this, colors[1]);

        blocks[2] = new Block(new int[][]{
                {1,1,1},
                {1,0,0}},this, colors[2]);

        blocks[3] = new Block(new int[][]{
                {1,1,1},
                {0,0,1}},this, colors[3]);

        blocks[4] = new Block(new int[][]{
                {0,1,1},
                {1,1,0}},this, colors[4]);

        blocks[5] = new Block(new int[][]{
                {1,1,0},
                {0,1,1}},this, colors[5]);

        blocks[6] = new Block(new int[][]{
                {1,1},
                {1,1}},this, colors[6]);

        currentBlock = blocks[random.nextInt(blocks.length)];

        score = new Timer(delay, new ActionListener() {
            int n = 0;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                update();
                repaint();
            }
        });

        score.start();
        this.setLayout(new BorderLayout());
        JButton home = new JButton("Home");
        //this.add(home, BorderLayout.EAST);

        setFocusable(true);
        addKeyListener(this);

        home.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this); // Obtient la référence de la fenêtre parente
            if (window instanceof JFrame) {
                JFrame frame = (JFrame) window;
                frame.dispose(); // Ferme la fenêtre principale
            }
            new Homepage();
        });


    }

    private void update(){
        if (state == STATE_GAME_PLAY){
            currentBlock.update();
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());


        g.setColor(Color.white);
        String timerString = "Score: " + points + "s";
        g.drawString(timerString, 340, 100);

        currentBlock.render(g);

        for (int row = 0 ; row < gamearea.length ; row++){
            for (int col = 0 ; col < gamearea[row].length ; col++){
                if (gamearea[row][col] != null){
                    g.setColor(gamearea[row][col]);
                    g.fillRect(col*block_size, row*block_size, block_size, block_size);
                }
            }
        }

        g.setColor(Color.white);
        for (int i=0; i<GameArea_height +1; i++){
            g.drawLine(0, block_size * i, block_size*GameArea_width, block_size*i);
        }
        for (int j=0; j<GameArea_width + 1; j++){
            g.drawLine(j*block_size, 0, j * block_size, block_size*GameArea_height);
        }

        if (state == STATE_GAME_OVER){
            g.setColor(Color.white);
            g.drawString("GAME OVER", 50, 200);
        }
        if (state == STATE_GAME_PAUSE){
            g.drawString("GAME PAUSED !", 50, 200);
        }

    }

    public Color[][] getGamearea(){
        return gamearea;
    }

    public GameArea getSecondPlayerArea() {
        return secondPlayerArea;
    }

    public void setCurrentBlock(){
        currentBlock = blocks[random.nextInt(blocks.length)];
        currentBlock.resetGameArea();
        GameOver();
    }

    private void GameOver(){
        int[][] coords = currentBlock.getCoords();
        for (int row=0; row<coords.length; row++){
            for (int col=0; col<coords[0].length; col++){
                if(coords[row][col] != 0){
                    if(gamearea[row + currentBlock.getY()][col + currentBlock.getX()] != null){
                        System.out.println("Game over !");
                        state = STATE_GAME_OVER;
                        new GameOverWindow(points);
                    }
                }
            }
        }
    }

    public void setSecondPlayerBlock() {
        secondPlayerArea.setCurrentBlock();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentBlock.FastSpeed();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentBlock.Left();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentBlock.Right();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentBlock.rotateBlock();
        }

        if (gameMode == GameMode.VERSUS) {
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                secondPlayerArea.currentBlock.Left();
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                secondPlayerArea.currentBlock.Right();
            } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                secondPlayerArea.currentBlock.rotateBlock();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                secondPlayerArea.currentBlock.FastSpeed();
            }
        }



        // Replay
        if (state == STATE_GAME_OVER){
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                for (int row = 0 ; row < gamearea.length ; row++){
                    for (int col = 0 ; col < gamearea[row].length ; col++){
                        gamearea[row][col] = null;
                    }
                }
                setCurrentBlock();
                state = STATE_GAME_PLAY;
            }
        }

        // Pause
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (state == STATE_GAME_PLAY){
                state = STATE_GAME_PAUSE;
            }else if (state ==STATE_GAME_PAUSE){
                state = STATE_GAME_PLAY;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            currentBlock.NormalSpeed();
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            secondPlayerArea.currentBlock.NormalSpeed();
        }

    }
}
