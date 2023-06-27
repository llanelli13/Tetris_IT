import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Image;


public class GameArea extends JPanel implements KeyListener{
    public static int STATE_GAME_PLAY = 0;
    public static int STATE_GAME_PAUSE = 1;
    public static int STATE_GAME_OVER = 2;
    private Image transparentImage;
    private int state = STATE_GAME_PLAY;
    private static final int FPS = 60;
    private static final int delay = 1000 / FPS;

    public static final int GameArea_width = 10;
    public static final int GameArea_height = 20;
    public static final int block_size = 30;
    private final Timer score;
    private final Color[][] gamearea = new Color[GameArea_height][GameArea_width];
    private final Block[] blocks = new Block[7];

    private Block block;
    private final Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"),
                                Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a249a4"), Color.decode("#3f48cc")};

    private Block currentBlock;

    private final Random random;
    private String timerString = "";
    public int points = 0, lines = 0;
    private Block nextBlock;
    public enum GameMode {
        SOLO,
        VERSUS,
        COUNTDOWN
    }

    private final GameMode gameMode;
    private GameArea secondPlayerArea;
    private final int countdownTime;


    private boolean gameOver = false;


    TetrisMusic tetrisMusic = new TetrisMusic();
    private static int speed;



    public GameArea(GameMode gameMode, int countdownTime, int speed){
        this.speed = speed;

        transparentImage = Toolkit.getDefaultToolkit().getImage("fond_tetris.png");

        if ( gameMode != GameMode.VERSUS){
            TetrisMusic.playGameplayMusic();
        }

        this.countdownTime = countdownTime;
        setPreferredSize(new Dimension(800, 600));
        this.gameMode = gameMode;
        random = new Random();
        blocks[0] = new Block(new int[][]{
                {1,1,1,1}}, this, colors[0], speed);

        blocks[1] = new Block(new int[][]{
                {1,1,1},
                {0,1,0}},this, colors[1], speed);

        blocks[2] = new Block(new int[][]{
                {1,1,1},
                {1,0,0}},this, colors[2], speed);

        blocks[3] = new Block(new int[][]{
                {1,1,1},
                {0,0,1}},this, colors[3], speed);

        blocks[4] = new Block(new int[][]{
                {0,1,1},
                {1,1,0}},this, colors[4], speed);

        blocks[5] = new Block(new int[][]{
                {1,1,0},
                {0,1,1}},this, colors[5], speed);

        blocks[6] = new Block(new int[][]{
                {1,1},
                {1,1}},this, colors[6], speed);

        currentBlock = blocks[random.nextInt(blocks.length)];
        generateNextBlock();

        score = new Timer(delay, new ActionListener() {
            final int n = 0;

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
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof JFrame frame) {
                frame.dispose();
            }
            new Homepage();
        });

        if (gameMode == GameMode.COUNTDOWN) {
            startCountdown();
        }
    }

    private void update() {
        if (state == STATE_GAME_PLAY) {
            currentBlock.update();
        } else if (state == STATE_GAME_OVER) {
            // Arrêter la mise à jour du jeu
            score.stop();
        }
    }


    private void startCountdown() {
        Timer countdownTimer = new Timer(1000, new ActionListener() {
            int remainingTime = countdownTime;

            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                if (remainingTime < 0) {
                    state = STATE_GAME_OVER;
                    score.stop();
                    timerString = "Time: 0s";
                    repaint();
                        if (!gameOver) { // Vérifier si le jeu est déjà terminé pour éviter l'ouverture en boucle
                          gameOver = true;
                          TetrisMusic.stopCurrentMusic();
                          TetrisMusic.playGameOverMusic();
                          new GameOverWindow(points, gameMode.name());
                    }
                } else {
                    timerString = "Time: " + remainingTime + "s";
                    update();
                    repaint();
                }
            }
        });
        countdownTimer.start();
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.drawImage(transparentImage, 0, 0, getWidth(), getHeight(), this);


        g.setColor(Color.white);
        g.drawString("Score: " + points + " pts", 320, 100);

        g.setColor(Color.white);
        g.drawString("Nombre de ligne: " + lines , 320, 150);

        g.setColor(Color.white);
        g.drawString("Next Block:", 320, 200);
        if (nextBlock != null) {
            nextBlock.renderNext(g, 320, 220);
        }

        if (gameMode == GameMode.COUNTDOWN) {
            g.setColor(Color.white);
            g.drawString(timerString, 320, 400);
        }
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
        g2d.dispose();
    }

    public Color[][] getGamearea(){
        return gamearea;
    }

    public void setCurrentBlock() {
        currentBlock = nextBlock;
        currentBlock.resetGameArea();
        generateNextBlock();
        GameOver();
    }


    private void generateNextBlock() {
        nextBlock = blocks[random.nextInt(blocks.length)];
    }


    public void setSecondPlayerArea(GameArea secondPlayerArea) {
        this.secondPlayerArea = secondPlayerArea;
    }

    private void GameOver() {
        int[][] coords = currentBlock.getCoords();
        boolean firstPlayerLost = false;
        boolean secondPlayerLost = false;

        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[0].length; col++) {
                if (coords[row][col] != 0) {
                    if (gamearea[row + currentBlock.getY()][col + currentBlock.getX()] != null) {
                        System.out.println("First player game over!");
                        firstPlayerLost = true;
                        break;
                    }
                }
            }
        }

        if (secondPlayerArea != null) {
            coords = secondPlayerArea.currentBlock.getCoords();
            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[0].length; col++) {
                    if (coords[row][col] != 0) {
                        if (secondPlayerArea.gamearea[row + secondPlayerArea.currentBlock.getY()][col + secondPlayerArea.currentBlock.getX()] != null) {
                            System.out.println("Second player game over!");
                            secondPlayerLost = true;
                            break;
                        }
                    }
                }
            }
        }
        if (firstPlayerLost || secondPlayerLost) {
            state = STATE_GAME_OVER;
            TetrisMusic.stopCurrentMusic();
            TetrisMusic.playGameOverMusic();
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // Obtain the current frame
            currentFrame.dispose(); // Close the current frame
            if (secondPlayerArea != null) {
                secondPlayerArea.endGame();
            }
            new GameOverWindow(points, gameMode.name());
        }
    }


    public void endGame() {
        state = STATE_GAME_OVER;
    }

    public void setState(int state) {
        this.state = state;
    }


    public void setSecondPlayerBlock() {
        secondPlayerArea.setCurrentBlock();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {
            currentBlock.FastSpeed();
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            currentBlock.Left();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            currentBlock.Right();
        } else if (e.getKeyCode() == KeyEvent.VK_Z) {
            currentBlock.rotateBlock();
        }

        if (gameMode == GameMode.VERSUS) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                secondPlayerArea.currentBlock.Left();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                secondPlayerArea.currentBlock.Right();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                secondPlayerArea.currentBlock.rotateBlock();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
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

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (state == STATE_GAME_PLAY){
                state = STATE_GAME_PAUSE;
            }else if (state ==STATE_GAME_PAUSE){
                state = STATE_GAME_PLAY;
            }
        }

        if (gameMode == GameMode.VERSUS) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (state == STATE_GAME_PLAY) {
                    state = STATE_GAME_PAUSE;
                    secondPlayerArea.setState(STATE_GAME_PAUSE); // Mettre en pause la deuxième zone de jeu
                } else if (state == STATE_GAME_PAUSE) {
                    state = STATE_GAME_PLAY;
                    secondPlayerArea.setState(STATE_GAME_PLAY); // Reprendre la deuxième zone de jeu
                }
            }
        }
}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S){
            currentBlock.NormalSpeed();
        }
        if (gameMode == GameMode.VERSUS) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                secondPlayerArea.currentBlock.NormalSpeed();
            }
        }
    }
}
