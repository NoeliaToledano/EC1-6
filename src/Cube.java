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
        switch (n){
            case 0:
                LEFT=rotateClockWise(LEFT);
                break;
            case 2:
                RIGHT=rotateClockWise(RIGHT);
                break;

        }
    }
    public void movel(int n){
        int[][] auxD = Arrays.stream(DOWN).map(int[]::clone).toArray(int[][]::new);
        for(int i=0;i<BACK.length;i++){

            DOWN[i][n]=BACK[i][n];
            BACK[i][n]=UP[UP.length-i-1][UP.length-1-n];
            UP[UP.length-i-1][UP.length-1-n]= FRONT[i][n];
            FRONT[i][n]=auxD[i][n];

        }
        switch (n){
            case 0:
                LEFT=rotateAntiClockWise(LEFT);
                break;
            case 2:
                RIGHT=rotateAntiClockWise(RIGHT);
                break;

        }
    }

    public void moveD(int n){
        int[][] auxB = Arrays.stream(BACK).map(int[]::clone).toArray(int[][]::new);
        int[][] auxL = Arrays.stream(LEFT).map(int[]::clone).toArray(int[][]::new);

        for(int i=0;i<BACK.length;i++){
            BACK[BACK.length-1-n][i]=auxL[LEFT.length-1-i][LEFT.length-1-n];
            LEFT[i][LEFT.length-1-n]=FRONT[n][i];
            FRONT[n][i]=RIGHT[i][n];
            RIGHT[i][n]=auxB[BACK.length-n-1][i];

        }
        switch (n){
            case 0:
                DOWN=rotateClockWise(DOWN);
                break;
            case 2:
                UP=rotateClockWise(UP);
                break;

        }
    }
    public void moved(int n){
        int[][] auxL = Arrays.stream(LEFT).map(int[]::clone).toArray(int[][]::new);
        for(int i=0;i<BACK.length;i++){
            LEFT[LEFT.length-1-i][LEFT.length-1-n]=BACK[BACK.length-1-n][i];
            BACK[BACK.length-n-1][i]=RIGHT[i][n];
            RIGHT[i][n]=FRONT[n][i];
            FRONT[n][i]=auxL[i][LEFT.length-1-n];


        }
        switch (n){
            case 0:
                DOWN=rotateAntiClockWise(DOWN);
                break;
            case 2:
                UP=rotateAntiClockWise(UP);
                break;

        }
    }
    public void moveB(int n){
        int[][] auxL = Arrays.stream(LEFT).map(int[]::clone).toArray(int[][]::new);
        for(int i=0;i<BACK.length;i++){
            LEFT[n][i]=UP[n][i];
            UP[n][i]=RIGHT[n][i];
            RIGHT[n][i]=DOWN[n][i];
            DOWN[n][i]=auxL[n][i];

        }
        switch (n){
            case 0:
                BACK=rotateClockWise(BACK);
                break;
            case 2:
                FRONT=rotateClockWise(FRONT);
                break;

        }
    }
    public void moveb(int n){
        int[][] auxU = Arrays.stream(UP).map(int[]::clone).toArray(int[][]::new);
        for(int i=0;i<BACK.length;i++){
            UP[n][i]=LEFT[n][i];
            LEFT[n][i]=DOWN[n][i];
            DOWN[n][i]=RIGHT[n][i];
            RIGHT[n][i]=auxU[n][i];

        }
        switch (n){
            case 0:
                BACK=rotateAntiClockWise(BACK);
                break;
            case 2:
                FRONT=rotateAntiClockWise(FRONT);
                break;

        }
    }
    private int[][] rotateClockWise(int[][] array) {
        int size = array.length;
        int[][] aux = new int[size][size];

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                aux[i][j] = array[size - j - 1][i];

        return aux;
    }
    private int[][] rotateAntiClockWise(int[][] array) {
        int size = array.length;
        int[][] aux = new int[size][size];

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                aux[i][j] = array[j][size - i - 1];

        return aux;
    }
}