import java.io.*;

import Logica.Frontera;
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

            System.out.println(cube.toMD5());



            Frontera frontera= Frontera.getFrontera();
            Estado estado1=new Estado(cube);
            NodoArbol nodo1 = new NodoArbol(1,estado1,1,"L1",1,6,null);
            NodoArbol nodo2 = new NodoArbol(2,estado1,1,"L1",1,2,null);
            NodoArbol nodo3 = new NodoArbol(3,estado1,1,"L1",1,8,null);
            frontera.insertar(nodo1);
            frontera.insertar(nodo2);
            frontera.insertar(nodo3);
            int i =0;
            while(true){

                frontera.insertar(new NodoArbol(i,estado1,1,"l3",2,2,null));
                System.out.println(i);
                i++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}