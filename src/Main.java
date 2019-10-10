import java.io.*;
import com.google.gson.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader("cubo2.json")) {
            Cube cube = gson.fromJson(reader, Cube.class);

            System.out.println(cube);

            System.out.println("--------------------------------");
            System.out.println("Por favor, inserte los movimientos :");
            Scanner scan = new Scanner(System.in);
            String movimientos = scan.next();
            cube.movimientos(movimientos);

            System.out.println(cube);

            System.out.println("Por favor, inserte los movimientos :");
             scan = new Scanner(System.in);
             movimientos = scan.next();
            cube.movimientos(movimientos);

            System.out.println(cube);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}