import Logica.Problema;
import Presentation.GUIForm;
import Recursos.Cube;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GUIForm frame=new GUIForm();
        Gson gson = new Gson();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduzca número estrategia:");
        System.out.println(" 1. Anchura \n 2. Profundidad \n 3. Costo Uniforme \n 4. A* \n 5. Voraz");
        int estrategia = keyboard.nextInt();
        System.out.println("Introduzca profundidad max:");
        int profMax = keyboard.nextInt();

        try (Reader reader = new FileReader("cubo10x10.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);
            cube.setSize(cube.getDOWN().length);// mega importante
            cube.movimientos("B1d0L7l5");
            //System.out.println(cube.objetivo());
            Problema miProblema = new Problema(cube, estrategia);
            miProblema.busqueda(estrategia,profMax);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}