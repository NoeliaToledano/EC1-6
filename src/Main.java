import java.io.*;
import com.google.gson.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader("cubo2.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);
            cube.setSize(cube.getBACK().length - 1); //IMPORTANT set Size of first cube
            System.out.println(cube);
            System.out.println("--------------------------------");
            System.out.println("Por favor, inserte los movimientos :");
            Scanner scan = new Scanner(System.in);
            String movimientos = scan.next();
            cube.movimientos(movimientos);

            System.out.println(cube);
            System.out.println(cube.toLineString());
            System.out.println(cube.toMD5());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}