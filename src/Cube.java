import java.util.Arrays;

public class Cube implements Cloneable {
    private int[][] BACK;
    private int[][] DOWN;
    private int[][] FRONT;
    private int[][] LEFT;
    private int[][] RIGHT;
    private int[][] UP;
    private int n;


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
    public void moveL(int n){
        int[][] auxB = Arrays.stream(BACK).map(int[]::clone).toArray(int[][]::new);
        for(int i=0;i<BACK.length;i++){
            BACK[i][n]=DOWN[i][n];
            DOWN[i][n]=FRONT[i][n];
            FRONT[i][n]=UP[UP.length-i-1][UP.length-1-n];
            UP[UP.length-i-1][UP.length-1-n]=auxB[i][n];

        }
    }
    public void moveD(int n){
        int[][] auxB = Arrays.stream(BACK).map(int[]::clone).toArray(int[][]::new);
        for(int i=0;i<BACK.length;i++){
           BACK[BACK.length-1-n][i]=LEFT[LEFT.length-1-i][LEFT.length-1-n];
           LEFT[i][LEFT.length-1-n]=FRONT[n][i];
           FRONT[n][i]=RIGHT[i][n];
           RIGHT[RIGHT.length-1-i][RIGHT.length-1-n]=auxB[BACK.length-n-1][BACK.length-1-i];

        }
    }
    public void moveB(int n){

    }
}