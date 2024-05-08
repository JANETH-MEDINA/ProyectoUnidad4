import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ControladorAdministrador {
    ControladorAutor autor = new ControladorAutor();
    ControladorCliente clientes = new ControladorCliente();
    ControladorLibro libro = new ControladorLibro();
    ControladorTransacciones transacciones = new ControladorTransacciones();
    Scanner leer = new Scanner(System.in);
    private Administrador admin = new Administrador();

    public int Administradores() {
        System.out.println("{MENU}");
        System.out.println("> 1.- Modificar Autores");
        System.out.println("> 2.- Modificar Libros");
        System.out.println("> 3.- Modificar Cliente");
        System.out.println(">4.- Transacciones");
        System.out.println(">5.- Salir");
        int mod = leer.nextInt();
        switch (mod) {
            case 1 -> {
                autor.autores(this.admin);
            }
            case 2 -> {
                libro.libro(this.admin);
            }
            case 3 -> {
                clientes.clientes(this.admin);
            }
            case 4 -> {
                transacciones.transacciones();
            }
        }
        return mod;
    }

    public int SuperAdmi() {
        System.out.println("{MENU}");
        System.out.println("> 1.- Modificar Autores");
        System.out.println("> 2.- Modificar Libros");
        System.out.println("> 3.- Modificar Cliente");
        System.out.println(">4.- Modificar Administradores");
        System.out.println(">5.- Transacciones");
        System.out.println(">6.- Salir");
        int mod = leer.nextInt();
        switch (mod) {
            case 1 -> {
                autor.autores(this.admin);
            }
            case 2 -> {
                libro.libro(this.admin);
            }
            case 3 -> {
                clientes.clientes(this.admin);
            }
            case 4 -> {
                this.menuAdmins();
            }
            case 5 -> {
                transacciones.transacciones();
            }
        }
        return mod;
    }

    private void menuAdmins() {
        System.out.println("> 1.- Borrar administrador");
        System.out.println("> 2.- Crear administrador");
        System.out.println("> 3.- Editar administrador ");
        System.out.println(">4.- Mostrar administrador");
        System.out.println(">5.- Volver al menú principal");
        int adm = leer.nextInt();
        switch (adm) {
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
                this.mostrar();
            }
        }
    }

    public void mostrar() {
        if (RepositorAdministrador.administrador.isEmpty()) {
            System.out.println("No hay administradores");
        } else {
            String permisos = "";
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-10s : %-10s : %-30s :\n", "NOMBRE", "APELLIDO", "NACIEMIENTO", "PERMISOS");
            System.out.println("................................................................................................................................................");
            for (int i = 0; i < RepositorAdministrador.administrador.size(); i++) {
                if (RepositorAdministrador.administrador.get(i).perimisos.isEmpty()) {
                    permisos = "Sin permisos";
                } else {
                    for (int j = 0; j < RepositorAdministrador.administrador.get(i).perimisos.size(); j++) {
                        permisos += String.valueOf(RepositorAdministrador.administrador.get(i).perimisos.get(j));
                    }
                }
                Fecha fecha = RepositorAdministrador.administrador.get(i).getPerfil().getNacimiento();
                System.out.printf(": %-20s : %-10s : %-10s : %-30s :\n", RepositorAdministrador.administrador.get(i).getPerfil().getNombre(), RepositorAdministrador.administrador.get(i).getPerfil().getApellido(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad(), permisos);
                System.out.println("................................................................................................................................................");
            }
        }
    }

    public void editar() {
        if (RepositorAdministrador.administrador.isEmpty()) {
            System.out.println("No hay clientes");
        } else {
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-10s : %-10s :\n", "NOMBRE", "APELLIDO", "NACIEMIENTO");
            System.out.println("................................................................................................................................................");
            for (int i = 0; i < RepositorAdministrador.administrador.size(); i++) {
                Fecha fecha = RepositorAdministrador.administrador.get(i).getPerfil().getNacimiento();
                System.out.printf(": %-30s : %-20s : %-4s/%-4s/%-4s\n", RepositorAdministrador.administrador.get(i).getPerfil().getNombre(), RepositorAdministrador.administrador.get(i).getPerfil().getApellido(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad());
            }
            System.out.println("................................................................................................................................................");
        }

        System.out.println("Administrador A EDITAR");
        System.out.println("Nombre del administrador:");
        String nombre = leer.next();
        System.out.println("Apellido del administrador");
        String apellido = leer.next();
        boolean a = true;
        int pos = 0;
        for (int i = 0; i < RepositorAdministrador.administrador.size(); i++) {
            Administrador administrador = RepositorAdministrador.administrador.get(i);
            if (administrador.getPerfil().getNombre().equals(nombre) && administrador.getPerfil().getApellido().equals(apellido) && !administrador.isSuperAdmin()) {
                if (!a) {
                    System.out.println("Cliente inexistente o super administrador");
                }
                System.out.println("¿Qué dato desea cambiar? (Nombre, Apellido, Nacimiento, Permisos) ");
                String dato = leer.next();
                switch (dato) {
                    case "Nombre" -> {
                        System.out.println("Ingrese el nombre correcto:");
                        String nomb = leer.next();
                        administrador.getPerfil().setNombre(nomb);
                    }
                    case "Apellido" -> {
                        System.out.println("Ingrese el apellido correcto:");
                        String ape = leer.next();
                        administrador.getPerfil().setApellido(ape);
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
                        administrador.getPerfil().setNacimiento(nacimiento);
                    }
                    case "Permisos" -> {
                        for (int j = 0; j < administrador.perimisos.size(); j++) {
                            administrador.perimisos.remove(i);
                        }
                        this.setPermisos(administrador);
                    }
                }
            }
        }

    }

    public void borrar() {
        if (RepositorAdministrador.administrador.isEmpty()) {
            System.out.println("No hay administradores");
        } else {
            System.out.println("Nombre del administrador:");
            String nombre = leer.next();
            System.out.println("Apellido del administrador");
            String apellido = leer.next();
            boolean c = false;
            for (int i = 0; i < RepositorAdministrador.administrador.size(); i++) {
                Administrador administrador = RepositorAdministrador.administrador.get(i);
                if (administrador.getPerfil().getNombre().equals(nombre) && administrador.getPerfil().getApellido().equals(apellido) && !administrador.isSuperAdmin()) {
                    RepositorAdministrador.administrador.remove(i);
                    c = true;
                    System.out.println("Administrador eliminado");
                    break;
                }
            }
            if (!c) {
                System.out.println("Autor inexistente o super admin");
            }
        }
    }

    public void crear() {
        System.out.println("DATOS DEL Administrador");
        System.out.println(">1.-Nombre del administrador");
        String nombre = leer.next();
        System.out.println(">2.-Apellido del administrador");
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
        Perfil admins = new Perfil(nombre, apellido, nacimiento);
        Administrador administrador = new Administrador();
        administrador.setContrasena(contra);
        administrador.setNombredeUsuario(usuario);
        this.setPermisos(administrador);
        administrador.setPerfil(admins);
        RepositorAdministrador.administrador.add(administrador);
    }

    private void setPermisos(Administrador admin) {
        int res;
        System.out.println("El administrador puede leer datos");
        System.out.println("1) Si");
        System.out.println("2) No");
        res = leer.nextInt();
        if (res == 1) {
            admin.setVer();
        }
        System.out.println("El administrador puede escribir datos");
        System.out.println("1) Si");
        System.out.println("2) No");
        res = leer.nextInt();
        if (res == 1) {
            admin.setEscribir();
        }
        System.out.println("El administrador puede eliminar datos");
        System.out.println("1) Si");
        System.out.println("2) No");
        res = leer.nextInt();
        if (res == 1) {
            admin.setBorrar();
        }
    }

    public boolean inicioSesion(int eleccion) {
        boolean sesion = false;
        System.out.println("Nombre de usuario: ");
        String usuario = leer.next();
        System.out.println("Contraseña: ");
        String contra = leer.next();
        switch (eleccion) {
            case 1 -> {
                for (Cliente cliente : RepositorCliente.Clientes) {
                    if (cliente.getNombredeUsuario().equals(usuario) && Cliente.checkPassword(contra, cliente)) {
                        sesion = true;
                        this.clientes=clientes;
                    }
                }
            }
            case 2 -> {
                for (Administrador admin : RepositorAdministrador.administrador) {
                    if (admin.getNombredeUsuario().equals(usuario) && Administrador.checkPassword(contra, admin)) {
                        sesion = true;
                        this.admin = admin;
                    }
                }
            }
            case 3 -> {
                for (Administrador admin : RepositorAdministrador.administrador) {
                    if (admin.getNombredeUsuario().equals(usuario) && Administrador.checkPassword(contra, admin) && admin.isSuperAdmin()) {
                        sesion = true;
                        this.admin = admin;
                    }
                }
            }
        }
        return sesion;
    }

    public static String hashString(String input) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Calculate the SHA-256 hash value
            byte[] hash = md.digest(input.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found.");
            return null;
        }
    }
}