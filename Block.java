import java.awt.*;

public class Block {

    public static final int GameArea_width = 10;
    public static final int GameArea_height = 20;
    public static final int block_size = 30;

    private int x = 4, y = 0;
    private final int normal = 600;
    private final int fast = 50;
    private int delayMovementTime = normal;
    private long beginTime;

    private int deltaX = 0;
    private boolean collision = false;
    private final GameArea gameArea;
    private final Color color;

    private int[][] coords;


    public Color getColor() {
        return color;
    }

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

    private void CheckLine() {
        int bottomLine = gameArea.getGamearea().length - 1;
        int completedLines = 0; // Variable pour compter le nombre de lignes complétées

        for (int topLine = gameArea.getGamearea().length - 1; topLine > 0; topLine--) {
            boolean lineCompleted = true;

            for (int col = 0; col < gameArea.getGamearea()[0].length; col++) {
                if (gameArea.getGamearea()[topLine][col] == null) {
                    lineCompleted = false;
                    break;
                }
            }

            if (lineCompleted) {
                completedLines++; // Incrémenter le nombre de lignes complétées
                continue; // Passer au reste de la boucle pour la ligne complétée
            }

            for (int col = 0; col < gameArea.getGamearea()[0].length; col++) {
                gameArea.getGamearea()[bottomLine][col] = gameArea.getGamearea()[topLine][col];
            }
            bottomLine--;
        }

        if (completedLines > 0) {
            int pointsEarned = completedLines * 100; // Calculer les points en fonction du nombre de lignes complétées
            gameArea.lines += completedLines; // Augmenter le nombre de lignes complétées
            gameArea.points += pointsEarned; // Ajouter les points au score total
        }

        // Effacer les lignes restantes au-dessus des blocs déplacés
        for (int line = bottomLine; line >= 0; line--) {
            for (int col = 0; col < gameArea.getGamearea()[0].length; col++) {
                gameArea.getGamearea()[line][col] = null;
            }
        }
    }



    public void rotateBlock(){
        int [][] rotatedBlock = transposeMatrice(coords);
        reverseRows(rotatedBlock);
        // Check pour que les blocks ne sortent pas de la grille
        if ((x + rotatedBlock[0].length > GameArea.GameArea_width) || (y + rotatedBlock.length > 20)){
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

    public void renderNext(Graphics g, int x, int y) {
        int blockSize = block_size / 2; // Taille réduite pour l'aperçu

        int[][] coords = getCoords();
        Color color = getColor();

        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[row].length; col++) {
                if (coords[row][col] != 0) {
                    int xPos = x + col * blockSize;
                    int yPos = y + row * blockSize;
                    g.setColor(color);
                    g.fillRect(xPos, yPos, blockSize, blockSize);
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
