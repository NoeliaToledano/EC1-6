import com.google.gson.Gson;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.awt.*;

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
            System.out.println(cube);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}