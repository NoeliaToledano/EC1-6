import java.io.*;

import Presentation.*;
import Recursos.Cube;
import com.google.gson.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GUIForm frame=new GUIForm();

        Gson gson = new Gson();

        try (Reader reader = new FileReader("cubo10x10.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);
            cube.setSize(cube.getDOWN().length);// mega importante

            System.out.println(cube.toLineString());
            System.out.println(cube.toMD5());

            cube.paint(frame);

            while(true){
                System.out.println("Por favor, inserte los movimientos :");
                Scanner scan = new Scanner(System.in);
                String movimiento = scan.next();
                cube.movimientos(movimiento);

                //cube.movimientosInvertidos(movimiento);
                cube.paint(frame);
                System.out.println(cube);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}