package Presentation;

import Recursos.*;

import javax.swing.*;
import java.awt.*;

public class MyCanvas extends JComponent {
    Cube cube;

    public void setCube(Cube cube) {
        this.cube = cube;
        repaint();//mark this component to be repainted
    }

    public void paint(Graphics g) {
        super.paint(g);

        paintFace(g, 260, 40, cube.getBACK());
        paintFace(g, 10, 290, cube.getLEFT());
        paintFace(g, 260, 290, cube.getDOWN());
        paintFace(g, 510, 290, cube.getRIGHT());
        paintFace(g, 760, 290, cube.getUP());
        paintFace(g, 260, 540, cube.getFRONT());

    }
    public void paintFace(Graphics g, int initX, int initY, int[][] cara) {
        int n=cara.length;
        int longitud=240/n;

        for (int x=0; x<n; x++ ){
            int pixelx=initX + x*longitud;
            for (int y=0; y<n;y++){
                int pixely=initY + y*longitud;
                switch(cara[y][x]){
                    case 0:
                        g.setColor(Color.RED);
                        break;
                    case 1:
                        g.setColor(Color.BLUE);
                        break;
                    case 2:
                        g.setColor(Color.YELLOW);
                        break;
                    case 3:
                        g.setColor(Color.GREEN);
                        break;
                    case 4:
                        g.setColor(Color.ORANGE);
                        break;
                    case 5:
                        g.setColor(Color.WHITE);
                        break;

                }

                g.fillRect(pixelx, pixely,longitud, longitud);

                g.setColor(Color.BLACK);
                g.drawRect(pixelx, pixely, longitud, longitud);
            }

        }


    }
}