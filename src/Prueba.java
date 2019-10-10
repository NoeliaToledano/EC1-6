import com.google.gson.Gson;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.awt.*;
import java.util.Scanner;
//todo colocar las clases
//todo eliminar esta clase y crear clases de dibujo..
//todo Facilitar la impresión
//
public class Prueba extends JFrame {
    public Prueba(){
        super("Prueba gráfica");

        setSize(1080,800);

    }

    public static void main(String[] args) {
        Prueba application = new Prueba();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyCanvas canvas=new MyCanvas();

        Gson gson = new Gson();

        try (Reader reader = new FileReader("cubo2.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);

            canvas.setCube(cube);
            application.getContentPane().add(canvas);
            application.setVisible(true);


            Scanner scan = new Scanner(System.in);
            String movimientos = scan.next();
            cube.movimientos(movimientos);

            application.getContentPane().add(canvas);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}