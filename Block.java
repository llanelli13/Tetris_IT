import java.awt.*;

public class Block {

    public static final int GameArea_width = 10;
    public static final int GameArea_height = 20;
    public static final int block_size = 30;

    private int x = 4, y = 0;
    private int normal = 600;
    private int fast = 50;
    private int delayMovementTime = normal;
    private long beginTime;

    private int deltaX = 0;
    private boolean collision = false;
    private GameArea gameArea;
    private Color color;

    private int[][] coords;


    public Block(int [][] coords, GameArea gameArea, Color color){
        this.coords = coords;
        this.gameArea = gameArea;
        this.color = color;
    }


    public void update(){
        if (collision){
            // Update les collisions de la gameArea
            for (int row = 0; row < coords.length; row++){
                for (int col = 0; col < coords[0].length; col++){
                    if (coords[row][col] != 0){
                        gameArea.getGamearea()[y + row][x + col] = color;
                    }
                }
            }
            gameArea.points += 20;
            CheckLine();
            gameArea.setCurrentBlock();
            return;
        }

        boolean moveX = true;
        if (!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0)){
            for( int row = 0; row < coords.length; row++){
                for (int col = 0 ; col < coords[row].length ; col++){
                    if (coords[row][col] != 0) {
                        if (gameArea.getGamearea()[y + row][x + deltaX + col] != null){
                            moveX = false;
                        }
                    }
                }
            }
            if (moveX){
                x+=deltaX;
            }

        }
        deltaX = 0;


        if (System.currentTimeMillis() - beginTime > delayMovementTime){
            if (!(y + 1 + coords.length > GameArea_height)) {
                for (int row = 0 ; row < coords.length ; row++){
                    for (int col = 0 ; col < coords[row].length ; col++){
                        if (coords[row][col] != 0 ){
                            if (gameArea.getGamearea()[y + 1 + row][x + deltaX + col] != null){
                                collision = true;
                            }
                        }
                    }
                }
                if (!collision){
                    y++;
                }
            }else{
                collision = true;
            }
            beginTime = System.currentTimeMillis();
        }
    }

    private void CheckLine(){
        int BottomLine = gameArea.getGamearea().length - 1;
        for (int topLine = gameArea.getGamearea().length - 1; topLine > 0 ; topLine--){
            int count = 0;
            for (int col = 0; col < gameArea.getGamearea()[0].length; col++){
                if (gameArea.getGamearea()[topLine][col] != null){
                    count++;
                }
                gameArea.getGamearea()[BottomLine][col] = gameArea.getGamearea()[topLine][col];
            }
            if (count < gameArea.getGamearea()[0].length){
                BottomLine--;
            }
        }
    }

    public void rotateBlock(){
        int [][] rotatedBlock = transposeMatrice(coords);
        reverseRows(rotatedBlock);
        // Check pour que les blocks ne sortent pas de la grille
        if ((x + rotatedBlock[0].length > gameArea.GameArea_width) || (y + rotatedBlock.length > 20)){
            return;
        }

        // Check pour que les blocks ne puissent pas rotatent dans d'autres blocks
        for (int row = 0 ; row < rotatedBlock.length ; row++){
            for (int col = 0 ; col < rotatedBlock[0].length ; col++){
                if (rotatedBlock[row][col] != 0){
                    if (gameArea.getGamearea()[y + row][x + col] != null){
                        return;
                    }
                }
            }
        }
        coords = rotatedBlock;
    }

    private int[][] transposeMatrice(int [][] matrice){
        int [][] temp = new int[matrice[0].length][matrice.length];
        for (int row = 0; row < matrice.length ; row++){
            for (int col = 0 ; col < matrice[0].length; col++){
                temp[col][row] = matrice[row][col];
            }
        }
        return temp;
    }

    private void reverseRows(int [][] matrice){
        int middle = matrice.length/2;
        for (int row = 0 ; row < middle; row++){
            int[] temp = matrice[row];
            matrice[row] = matrice[matrice.length - row - 1];
            matrice[matrice.length - row - 1] = temp;
        }

    }

    public void render(Graphics g){
        for (int row=0; row < coords.length; row++){
            for (int col = 0; col < coords[0].length; col++){
                if(coords[row][col] != 0){
                    g.setColor(color);
                    g.fillRect(col * block_size + x * block_size, row * block_size + y * block_size, block_size, block_size);
                }
            }
        }
    }

    public void resetGameArea(){
        this.x = 4;
        this.y = 0;
        this.collision = false;
    }

    public int[][] getCoords() {
        return coords;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void FastSpeed(){
        delayMovementTime = fast;
    }

    public void NormalSpeed(){
        delayMovementTime = normal;
    }

    public void Left(){
        deltaX = -1 ;
    }

    public void Right(){
        deltaX = 1 ;
    }
}
