import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ControladorMain menuprincipal = new ControladorMain();
        int mod;
        ControladorAdministrador con = new ControladorAdministrador();
        Seeder.DatosAgregados();

        do {
            mod = menuprincipal.menu();
        } while (mod != 55);

    }
}