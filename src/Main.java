import java.io.*;

import Presentation.*;
import Recursos.Cube;
import Recursos.Estado;
import Recursos.Sucesor;

import com.google.gson.*;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GUIForm frame=new GUIForm();
        Gson gson = new Gson();

        try (Reader reader = new FileReader("cubo2.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);
            cube.setSize(cube.getDOWN().length);// mega importante

            System.out.println(cube.toMD5());


            Estado prueba=new Estado(cube);

            ArrayList<Sucesor> sucesores =prueba.sucesores();

            for (int i = 0; i < sucesores.size(); i++) {
                System.out.println(sucesores.get(i));

                sucesores.get(i).getCubo().paint(frame);

            }


/*
            while(true){
                System.out.println("Por favor, inserte los movimientos :");
                Scanner scan = new Scanner(System.in);
                String movimiento = scan.next();
                cube.movimientos(movimiento);

                cube.paint(frame);
                System.out.println(cube);
                System.out.println(cube.toMD5());
                System.out.println(cube.objetivo());

            }
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}