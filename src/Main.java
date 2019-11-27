import java.io.*;

import Logica.Frontera;
import Logica.Problema;
import Presentation.*;
import Recursos.*;


import com.google.gson.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GUIForm frame=new GUIForm();
        Gson gson = new Gson();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduzca número estrategia:");
        System.out.println(" 1. Anchura \n 2. Profundidad \n 3. Coste Uniforme \n 4. A* \n 5. Voraz");
        int estrategia = keyboard.nextInt();
        System.out.println("Introduzca profundidad max:");
        int profMax = keyboard.nextInt();

        try (Reader reader = new FileReader("cuboEjemplo.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);
            cube.setSize(cube.getDOWN().length);// mega importante

            //System.out.println(cube.objetivo());
            Problema miProblema = new Problema(cube);
            miProblema.busqueda(estrategia,profMax);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}