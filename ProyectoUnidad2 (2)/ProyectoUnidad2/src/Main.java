import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControladorMain menuprincipal = new ControladorMain();
        int mod = 32;
        Seeder.DatosAgregados();

        do {
            menuprincipal.execute();
        } while (mod != 55);

    }
}