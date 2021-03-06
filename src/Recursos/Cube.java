package Recursos;
import Presentation.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.lang.Math;


import java.util.Arrays;


public class Cube implements Cloneable {
    private int[][] BACK;
    private int[][] DOWN;
    private int[][] FRONT;
    private int[][] LEFT;
    private int[][] RIGHT;
    private int[][] UP;
    private int size;

    Cube(int[][] back, int[][] down, int[][] front, int[][] left, int[][] right, int[][] up) {
        this.BACK = back;
        this.DOWN = down;
        this.FRONT = front;
        this.LEFT = left;
        this.RIGHT = right;
        this.UP = up;
        this.size = BACK.length;

    }

    public int[][] getBACK() {
        return BACK;
    }

    public int[][] getDOWN() {
        return DOWN;
    }

    public int[][] getFRONT() {
        return FRONT;
    }

    public int[][] getLEFT() {
        return LEFT;
    }

    public int[][] getRIGHT() {
        return RIGHT;
    }

    public int[][] getUP() {
        return UP;
    }

    public int getSize() {
        return size;
    }

    public void setBACK(int[][] BACK) {
        this.BACK = BACK;
    }

    public void setDOWN(int[][] DOWN) {
        this.DOWN = DOWN;
    }

    public void setFRONT(int[][] FRONT) {
        this.FRONT = FRONT;
    }

    public void setLEFT(int[][] LEFT) {
        this.LEFT = LEFT;
    }

    public void setRIGHT(int[][] RIGHT) {
        this.RIGHT = RIGHT;
    }

    public void setUP(int[][] UP) {
        this.UP = UP;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String toString() {
        return " BACK= " + Arrays.deepToString(BACK) +
                "\n DOWN= " + Arrays.deepToString(DOWN) +
                "\n FRONT=" + Arrays.deepToString(FRONT) +
                "\n LEFT= " + Arrays.deepToString(LEFT) +
                "\n RIGHT=" + Arrays.deepToString(RIGHT) +
                "\n UP=   " + Arrays.deepToString(UP);
    }

    public void paint(GUIForm frame) {
        MyCanvas canvas = new MyCanvas();
        canvas.setCube(this);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
    }

    public Cube clone() {
        int[][] auxB = Arrays.stream(BACK).map(int[]::clone).toArray(int[][]::new);
        int[][] auxD = Arrays.stream(DOWN).map(int[]::clone).toArray(int[][]::new);
        int[][] auxF = Arrays.stream(FRONT).map(int[]::clone).toArray(int[][]::new);
        int[][] auxL = Arrays.stream(LEFT).map(int[]::clone).toArray(int[][]::new);
        int[][] auxR = Arrays.stream(RIGHT).map(int[]::clone).toArray(int[][]::new);
        int[][] auxU = Arrays.stream(UP).map(int[]::clone).toArray(int[][]::new);
        Cube auxCube = new Cube(auxB, auxD, auxF, auxL, auxR, auxU);
        return auxCube;
    }

    public void movimientosInvertidos(String ordenes) {
        char c;
        int n;
        String[] mov = ordenes.split("(?<=\\d)(?=\\D)");
        try {
            for (int i = mov.length - 1; i >= 0; i--) {
                String[] part = mov[i].split("(?<=\\D)(?=\\d)");
                c = part[0].charAt(0);
                n = Integer.parseInt(part[1]);

                if (Character.isUpperCase(c)) {
                    c = Character.toLowerCase(c);
                } else {
                    c = Character.toUpperCase(c);
                }
                aplicarMovimiento(c, n);
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Set de instrucciones mal declarado.");
        } catch (NumberFormatException e) {
            System.out.println("Set de instrucciones mal declarado.");
        }
    }

    public void movimientos(String ordenes) {
        char c;
        int n;
        String[] mov = ordenes.split("(?<=\\d)(?=\\D)");

        try {
            for (int i = 0; i < mov.length; i++) {
                String[] part = mov[i].split("(?<=\\D)(?=\\d)");
                c = part[0].charAt(0);
                n = Integer.parseInt(part[1]);
                aplicarMovimiento(c, n);
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Set de instrucciones mal declarado.");
        } catch (NumberFormatException e) {
            System.out.println("Set de instrucciones mal declarado.");
        }
    }

    private void aplicarMovimiento(char c, int n) {
        if ((c == 'B' || c == 'b' || c == 'L' || c == 'l' || c == 'd' || c == 'D') && (n <= size && n >= 0)) {

            switch (c) {

                case 'L':
                    moveL(n);
                    break;
                case 'l':
                    movel(n);
                    break;
                case 'D':
                    moveD(n);
                    break;
                case 'd':
                    moved(n);
                    break;
                case 'B':
                    moveB(n);
                    break;
                case 'b':
                    moveb(n);
                    break;

            }

        } else {

            System.out.println("El movimiento: " + c + n + " no existe.");
        }
    }

    public void moveL(int n) {

        int[][] auxB = Arrays.stream(BACK).map(int[]::clone).toArray(int[][]::new);
        for (int i = 0; i < BACK.length; i++) {
            BACK[i][n] = DOWN[i][n];
            DOWN[i][n] = FRONT[i][n];
            FRONT[i][n] = UP[UP.length - i - 1][UP.length - 1 - n];
            UP[UP.length - i - 1][UP.length - 1 - n] = auxB[i][n];

        }
        if (n == 0) {
            LEFT = rotateClockWise(LEFT);
        } else if (n==(size-1)) {
            RIGHT = rotateClockWise(RIGHT);
        }


    }

    public void movel(int n) {

        int[][] auxD = Arrays.stream(DOWN).map(int[]::clone).toArray(int[][]::new);

        for (int i = 0; i < BACK.length; i++) {

            DOWN[i][n] = BACK[i][n];
            BACK[i][n] = UP[UP.length - i - 1][UP.length - 1 - n];
            UP[UP.length - i - 1][UP.length - 1 - n] = FRONT[i][n];
            FRONT[i][n] = auxD[i][n];

        }
        if (n == 0) {
            LEFT = rotateAntiClockWise(LEFT);
        } else if (n==(size-1)) {
            RIGHT = rotateAntiClockWise(RIGHT);

        }


    }

    public void moveD(int n) {

        int[][] auxB = Arrays.stream(BACK).map(int[]::clone).toArray(int[][]::new);
        int[][] auxL = Arrays.stream(LEFT).map(int[]::clone).toArray(int[][]::new);

        for (int i = 0; i < BACK.length; i++) {
            BACK[BACK.length - 1 - n][i] = auxL[LEFT.length - 1 - i][LEFT.length - 1 - n];
            LEFT[i][LEFT.length - 1 - n] = FRONT[n][i];
            FRONT[n][i] = RIGHT[RIGHT.length - 1 - i][n];
            RIGHT[RIGHT.length - 1 - i][n] = auxB[BACK.length - n - 1][BACK.length - 1 - i];

        }
        if (n == 0) {
            DOWN = rotateClockWise(DOWN);

        } else if (n==(size-1)) {
            UP = rotateClockWise(UP);

        }

    }

    public void moved(int n) {

        int[][] auxL = Arrays.stream(LEFT).map(int[]::clone).toArray(int[][]::new);
        for (int i = 0; i < BACK.length; i++) {
            LEFT[LEFT.length - 1 - i][LEFT.length - 1 - n] = BACK[BACK.length - 1 - n][i];
            BACK[BACK.length - n - 1][i] = RIGHT[i][n];
            RIGHT[i][n] = FRONT[n][RIGHT.length - 1 - i];
            FRONT[n][RIGHT.length - 1 - i] = auxL[LEFT.length - 1 - i][LEFT.length - 1 - n];


        }
        if (n == 0) {
            DOWN = rotateAntiClockWise(DOWN);
        } else if (n==(size-1)) {
            UP = rotateAntiClockWise(UP);

        }

    }

    public void moveB(int n) {
        int[][] auxL = Arrays.stream(LEFT).map(int[]::clone).toArray(int[][]::new);
        for (int i = 0; i < BACK.length; i++) {
            LEFT[n][i] = UP[n][i];
            UP[n][i] = RIGHT[n][i];
            RIGHT[n][i] = DOWN[n][i];
            DOWN[n][i] = auxL[n][i];

        }
        if (n == 0) {
            BACK = rotateClockWise(BACK);
        } else if (n==(size-1)) {
            FRONT = rotateClockWise(FRONT);

        }

    }

    public void moveb(int n) {

        int[][] auxU = Arrays.stream(UP).map(int[]::clone).toArray(int[][]::new);
        for (int i = 0; i < BACK.length; i++) {
            UP[n][i] = LEFT[n][i];
            LEFT[n][i] = DOWN[n][i];
            DOWN[n][i] = RIGHT[n][i];
            RIGHT[n][i] = auxU[n][i];

        }
        if (n == 0) {
            BACK = rotateAntiClockWise(BACK);
        } else if (n==(size-1)) {
            FRONT = rotateAntiClockWise(FRONT);
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

    public String toMD5() {
        String cubeString = this.toLineString();

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hashInBytes = md.digest(cubeString.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String toLineString() {
        return this.toString().replaceAll("[^0-6]", "");
    }
    public double calcularHeuristica(){

        return  Math.abs(calcularEntropia(BACK)+calcularEntropia(DOWN)+
                calcularEntropia(FRONT)+calcularEntropia(LEFT)+
                calcularEntropia(RIGHT)+calcularEntropia(UP));
    }
    public double calcularEntropia(int[][] cara){
        double entropia=0;
        double NN=(cara.length*cara[0].length);
        int contador[] =new int[6];

        for(int x=0;x<cara.length;x++){
            for(int y=0;y<cara[0].length;y++){
                contador[cara[x][y]]++;

            }
        }
        for(int c=0;c<6;c++){
            if (contador[c]>0){
                entropia = entropia + (double)contador[c]/NN * logN((double)contador[c]/NN,6.0);
            }

        }
        return -entropia;
    }
    private double logN(double A,double B){
        return  (Math.log(A) / Math.log(B));
    }

    public boolean objetivo() {
        boolean esObjetivo = true;
        if (!comprobar(BACK)) {
            esObjetivo = false;
        } else if (!comprobar(DOWN)) {
            esObjetivo = false;
        } else if (!comprobar(FRONT)) {
            esObjetivo = false;
        } else if (!comprobar(LEFT)) {
            esObjetivo = false;
        } else if (!comprobar(RIGHT)) {
            esObjetivo = false;
        } else if (!comprobar(UP)) {
            esObjetivo = false;
        }

        return esObjetivo;

    }

    private boolean comprobar(int[][] cara) {
        int muestraB = cara[0][0];
        boolean iguales = true;
        for (int i = 0; i < size && iguales; i++) {
            for (int j = 0; j < size && iguales; j++) {
                if (muestraB != cara[i][j])
                    iguales = false;
            }
        }
        return iguales;

    }


}