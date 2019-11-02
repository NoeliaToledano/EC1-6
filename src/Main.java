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

        try (Reader reader = new FileReader("cuboEjemplo.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);
            cube.setSize(cube.getDOWN().length);// mega importante

            System.out.println(cube.objetivo());
            Problema miProblema = new Problema(cube);
            miProblema.busqueda("Anchura",9999999);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}