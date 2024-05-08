import java.util.Scanner;

public class ControladorMain implements Controller
{
    ControladorAdministrador administrador = new ControladorAdministrador();
    Scanner leer = new Scanner(System.in);

    @Override
    public void execute() {
        Menu menu = new Menu();
        Controller adinic = () -> administrador.inicioSesion(1);
        Controller admins = () -> administrador.inicioSesion(2);
        Controller superAdm = () -> administrador.inicioSesion(3);
        menu.addMenuItem(1, new MenuItem("Cliente", adinic));
        menu.addMenuItem(2, new MenuItem("Administrador", admins));
        menu.addMenuItem(3, new MenuItem("Super administratos", superAdm));
        menu.display();
    }
    /*
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
    public boolean logOut(){
        return true;
    }
    */
}

