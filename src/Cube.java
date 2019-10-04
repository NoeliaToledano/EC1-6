import java.util.Arrays;

public class Cube implements Cloneable {
    private int[][] BACK;
    private int[][] DOWN;
    private int[][] FRONT;
    private int[][] LEFT;
    private int[][] RIGHT;
    private int[][] UP;
    

    /*
        Cube(int[][] back, int[][] down, int[][] front, int[][] left, int[][] right, int[][] up){
            this.back=back;
            this.down=down;
            this.front=front;
            this.left=left;
            this.right=right;
            this.up=up;
    
        }
    
     */
    public String toString() {
        return " BACK= " + Arrays.deepToString(BACK) +
                "\n DOWN= " + Arrays.deepToString(DOWN) +
                "\n FRONT=" + Arrays.deepToString(FRONT) +
                "\n LEFT= " + Arrays.deepToString(LEFT) +
                "\n RIGHT=" + Arrays.deepToString(RIGHT) +
                "\n UP=   " + Arrays.deepToString(UP);
    }
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setBACK(int[][] BACK) {
        this.BACK = BACK;
    }
}