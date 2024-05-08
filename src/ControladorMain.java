import java.util.Scanner;

public class ControladorMain {
    Administrador superadmi = new Administrador();
    ControladorAdministrador administrador = new ControladorAdministrador();
    ControladorCliente clientes = new ControladorCliente();
    Scanner leer = new Scanner(System.in);

    public int menu() {
        System.out.println("¿QUIEN DESEA INICIAR SESION?");
        System.out.println("> 1.- Cliente");
        System.out.println("> 2.- Administrador");
        System.out.println("> 3.- Super administrador");
        int usuario = leer.nextInt();
        switch (usuario) {
            case 1 -> {//cliente
                if (administrador.inicioSesion(1)) {

                } else {
                    System.out.println("Usuario o contraseña incorrectos");
                }
            }
            case 2 -> {//administrador
                if (administrador.inicioSesion(2)) {
                    administrador.Administradores();
                } else {
                    System.out.println("Usuario o contraseña incorrectos");
                }
            }
            case 3 -> {//super admin
                if (administrador.inicioSesion(3)) {
                    administrador.SuperAdmi();
                } else {
                    System.out.println("Usuario o contraseña incorrectos");
                }
            }
        }
        return usuario;
    }
}

