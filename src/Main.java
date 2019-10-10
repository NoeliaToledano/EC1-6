import java.io.*;

import Presentation.*;
import Recursos.Cube;
import com.google.gson.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GUIForm frame=new GUIForm();

        Gson gson = new Gson();

        try (Reader reader = new FileReader("cubo2.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);


            cube.paint(frame);

            System.out.println("Por favor, inserte los movimientos :");
            Scanner scan = new Scanner(System.in);
            String movimientos = scan.next();
            cube.movimientos(movimientos);

            cube.paint(frame);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}