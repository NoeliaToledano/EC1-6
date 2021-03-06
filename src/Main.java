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
        int estrategia = 0, profMax = 0, poda = 2;
        boolean sePoda = true;
        GUIForm frame = new GUIForm();
        Gson gson = new Gson();
        Scanner keyboard = new Scanner(System.in);
        String rutaJson = "";  //si lo haceis manual poner aqui el nombre del json
        rutaJson="";
        Cube cube=null;
        try {
            System.out.println("Introduzca la ruta del archivo JSON:");
            rutaJson=keyboard.next();
            Reader reader = new FileReader(rutaJson);
            cube = gson.fromJson(reader, Cube.class);

            System.out.println(" 1. Anchura \n 2. Profundidad \n 3. Costo Uniforme \n 4. A* \n 5. Voraz");
            do {
                System.out.println("Introduzca número estrategia:");
                estrategia = keyboard.nextInt();
            } while (estrategia < 1 || estrategia > 5);
            do {
                System.out.println("Introduzca profundidad max:");
                profMax = keyboard.nextInt();
            } while (profMax % 1 != 0);
            do {
                System.out.println("¿Poda? \n Si-1 \n No-0");
                poda = keyboard.nextInt();
                if (poda == 0) sePoda = false;
                else sePoda = true;
            } while (poda != 0 && poda != 1);

            cube.setSize(cube.getDOWN().length);// mega importante
            //System.out.println(cube.objetivo());
            Problema miProblema = new Problema(cube, estrategia, sePoda);
            miProblema.busqueda(estrategia, profMax);

        }catch (IOException e) {
            System.out.println("Error en ruta del JSON");
        } catch (Exception e) {
            System.out.println("Carácter introducido no válido.");
        }

    }

}