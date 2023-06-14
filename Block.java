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

    private int[][] coords;
    public Block(int [][] coords){
        this.coords = coords;
    }

    public void update(){
        if (collision){
            return;
        }
        if (!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0)){
            x+=deltaX;
        }
        deltaX = 0;
        if (System.currentTimeMillis() - beginTime > delayMovementTime){
            if (!(y + 1 + coords.length > GameArea_height)) {
                y++;
            }else{
                collision = true;
            }
            beginTime = System.currentTimeMillis();
        }
    }

    public void render(Graphics g){
        for (int row=0; row < coords.length; row++){
            for (int col = 0; col < coords[0].length; col++){
                if(coords[row][col] != 0){
                    g.setColor(Color.red);
                    g.fillRect(col * block_size + x * block_size, row * block_size + y * block_size, block_size, block_size);
                }
            }
        }
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
