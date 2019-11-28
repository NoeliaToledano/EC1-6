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
        int estrategia = 0, profMax = 0;
        GUIForm frame=new GUIForm();
        Gson gson = new Gson();
        Scanner keyboard = new Scanner(System.in);

        System.out.println(" 1. Anchura \n 2. Profundidad \n 3. Costo Uniforme \n 4. A* \n 5. Voraz");
        try{
        do {
            System.out.println("Introduzca número estrategia:");
            estrategia = keyboard.nextInt();
        }while(estrategia<1 || estrategia>5);
            do {
                System.out.println("Introduzca profundidad max:");
                profMax = keyboard.nextInt();
            }while(profMax % 1 != 0);

        } catch (Exception e) {
            System.out.println("Carácter introducido no válido.");
        }

        try (Reader reader = new FileReader("cuboEjemplo.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);
            cube.setSize(cube.getDOWN().length);// mega importante

            //System.out.println(cube.objetivo());
            Problema miProblema = new Problema(cube, estrategia);
            miProblema.busqueda(estrategia,profMax);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}