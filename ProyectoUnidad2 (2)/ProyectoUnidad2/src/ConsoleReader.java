import java.util.Scanner;

public class ConsoleReader {
    Scanner leer = new Scanner(System.in);

    public int leerint(String prompt, IntegerValidator validator) {
        boolean val = false;
        while (!val) {
            System.out.println(prompt + ":");
            int va = leer.nextInt();
            leer.nextLine();
            boolean isValid = validator.val(va);
            if (isValid) return va;
            else  System.out.println("Error-Opción invalida");
        }
        return 0;
    }
    public String leerString(String prompt, StringValidator validator) {
        boolean val = false;
        String vali = " ";
        while (!val) {
            System.out.println(prompt + ":");
            vali = leer.nextLine();
            boolean isValid = validator.validar(vali);
            if (isValid) return vali;
            else System.out.println("Error-Opción invalida");
        }
            return " ";
        }

}


