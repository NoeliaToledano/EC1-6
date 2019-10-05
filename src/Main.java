import java.io.*;

import com.google.gson.*;


public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader("cubo1.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);
            System.out.println(cube);
            System.out.println("--------------------------------");
            cube.moveD(0);
            System.out.println(cube);
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}