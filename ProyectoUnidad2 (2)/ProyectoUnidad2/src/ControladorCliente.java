import javax.print.DocFlavor;
import java.util.Scanner;

public class ControladorCliente {
    Scanner leer = new Scanner(System.in);

    public void clientes(Administrador admin) {
        boolean borrar = false;
        boolean escribir = false;
        boolean ver = false;
        for (Permisos per : admin.perimisos) {
            if (String.valueOf(per).equalsIgnoreCase("borrar")) {
                borrar = true;
            }
            if (String.valueOf(per).equalsIgnoreCase("escribir")) {
                escribir = true;
            }
            if (String.valueOf(per).equalsIgnoreCase("ver")) {
                ver = true;
            }
        }
        System.out.println("{MENU}");
        if (borrar) {
            System.out.println("> 1.- Borrar Cliente");
        }
        if (escribir) {
            System.out.println("> 2.- Crear Cliente");
            System.out.println("> 3.- Editar Cliente ");
        }
        if (ver) {
            System.out.println(">4.- Mostrar clientes");
            System.out.println(">5.- Adeudos de cliente");
        }
        System.out.println(">6.- Volver al menú principal");
        int cli = leer.nextInt();
        switch (cli) {
            case 1 -> {
                this.borrar();
            }
            case 2 -> {
                this.crear();
            }
            case 3 -> {
                this.editar();
            }
            case 4 -> {
                this.mostar();
            }
            case 5 -> {
                this.adeudos();
            }
        }
    }

    public void borrar() {
        if (this.contClientes() == 0) {
            System.out.println("No hay clientes");
        } else {
            System.out.println("Nombre del Cliente:");
            String nombre = leer.next();
            System.out.println("Apellido del Cliente");
            String apellido = leer.next();
            boolean c = false;
            for (Usuario usuario : RepositorUsuario.usuarios) {
                if (usuario instanceof Cliente) {
                    if (usuario.getPerfil().getNombre().equals(nombre) && usuario.getPerfil().getApellido().equals(apellido)) {
                        RepositorUsuario.usuarios.remove(usuario);
                        c = true;
                        System.out.println("Cliente eliminado");
                        break;
                    }
                }
            }
            if (!c) {
                System.out.println("Autor inexistente");
            }
        }
    }

    public void crear() {
        System.out.println("DATOS DEL CLIENTE");
        System.out.println(">1.-Nombre del cliente");
        String nombre = leer.next();
        System.out.println(">2.-Apellido del cliente");
        String apellido = leer.next();
        System.out.println(">3.-Fecha de Nacimiento con 2 digitos");
        System.out.println("Día: ");
        int dia = leer.nextInt();
        System.out.println("Mes: ");
        int mes = leer.nextInt();
        System.out.println("Año: ");
        int anualidad = leer.nextInt();
        System.out.println("Usuario: ");
        String usuario = leer.next();
        System.out.println("Contraseña");
        String contra = leer.next();
        Fecha nacimiento = new Fecha(dia, mes, anualidad);
        Perfil clientes = new Perfil(nombre, apellido, nacimiento);
        Cliente clientess = new Cliente();
        clientess.setPerfil(clientes);
        clientess.setContrasena(contra);
        clientess.setNombredeUsuario(usuario);
        RepositorUsuario.usuarios.add(clientess);
    }

    public void editar() {
        if (this.contClientes() == 0) {
            System.out.println("No hay clientes");
        } else {
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-10s : %-10s :\n", "NOMBRE", "APELLIDO", "NACIEMIENTO");
            System.out.println("................................................................................................................................................");
            for (Usuario user : RepositorUsuario.usuarios) {
                if (user instanceof Cliente) {
                    Fecha fecha = user.getPerfil().getNacimiento();
                    System.out.printf(": %-30s : %-20s : %-4s/%-4s/%-4s\n", user.getPerfil().getNombre(), user.getPerfil().getApellido(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad());
                }
            }
            System.out.println("................................................................................................................................................");
        }

        System.out.println("CLIENTE A EDITAR");
        System.out.println("Nombre del Cliente:");
        String nombre = leer.next();
        System.out.println("Apellido del Cliente");
        String apellido = leer.next();
        boolean a = true;
        int pos = 0;
        for (Usuario usuario : RepositorUsuario.usuarios) {
            if (usuario instanceof Cliente) {
                if (usuario.getPerfil().getNombre().equals(nombre) && usuario.getPerfil().getApellido().equals(apellido)) {
                    if (!a) {
                        System.out.println("Cliente inexistente");
                    }
                    System.out.println("¿Qué dato desea cambiar? (Nombre, Apellido, Nacimiento) ");
                    String dato = leer.next();
                    switch (dato) {
                        case "Nombre" -> {
                            System.out.println("Ingrese el nombre correcto:");
                            String nomb = leer.next();
                            usuario.getPerfil().setNombre(nomb);
                        }
                        case "Apellido" -> {
                            System.out.println("Ingrese el apellido correcto:");
                            String ape = leer.next();
                            usuario.getPerfil().setApellido(ape);
                        }
                        case "Nacimiento" -> {
                            System.out.println("Ingrese la fecha correcta:");
                            System.out.println("Día:");
                            int dia = leer.nextInt();
                            System.out.println("Mes:");
                            int mes = leer.nextInt();
                            System.out.println("Año:");
                            int anualidad = leer.nextInt();
                            Fecha nacimiento = new Fecha(dia, mes, anualidad);
                            usuario.getPerfil().setNacimiento(nacimiento);
                        }
                    }
                }
            }
        }

    }

    public void mostar() {
        if (this.contClientes() == 0) {
            System.out.println("No hay clientes");
        } else {
            String libros = "";
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-10s : %-10s : %-30s :\n", "NOMBRE", "APELLIDO", "NACIEMIENTO", "LIBROS");
            System.out.println("................................................................................................................................................");
            for (Usuario usuario : RepositorUsuario.usuarios) {
                if (usuario instanceof Cliente) {
                    if (((Cliente) usuario).getPrestamoLibro().isEmpty()) {
                        libros = "Sin libros";
                    } else {
                        for (Libro libroa: ((Cliente) usuario).PrestamoLibro) {
                            libros += libroa.getTitulo() + " : ";
                        }
                    }
                    Fecha fecha = usuario.getPerfil().getNacimiento();
                    System.out.printf(": %-20s : %-10s : %-10s : %-30s :\n", usuario.getPerfil().getNombre(), usuario.getPerfil().getApellido(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad(), libros);
                    System.out.println("................................................................................................................................................");
                }
            }
        }
    }

    public void adeudos() {

    }


    public void menu() {
        System.out.println("¿QUE DESEAS VER?");
        System.out.println(">1.- Leer libros");
        System.out.println(">2.- Transacciones");
        System.out.println(">3.- Libros existentes ");
    }

    public int contClientes() {
        int c = 0;
        for (Usuario usuario : RepositorUsuario.usuarios) {
            if (usuario instanceof Cliente) {
                c++;
            }
        }
        return c;
    }
}
