import javax.management.StringValueExp;
import java.util.Scanner;
import java.util.Random;
public class ControladorTransacciones {
    Scanner leer = new Scanner(System.in);
    Random random = new Random();

    public void transacciones() {
        System.out.println("{TRANSACCIONES}");
        System.out.println("> 1.- Prestamo de un libro");
        System.out.println("> 2.- Devolución de un libro");
        System.out.println("> 3.- Reportes");

        int op = leer.nextInt();
        switch (op) {
            case 1 -> {
                this.Prestamo();
            }
            case 2 -> {
                this.Devolucion();
            }
            case 3 -> {
                this.Reportes();
            }
        }
    }

    public void Prestamo() {
        if (RepositorCliente.Clientes.isEmpty()) {
            System.out.println("{DATOS}");
            System.out.println("No hay clientes");
        } else {
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-10s : %-10s :\n", "NOMBRE", "APELLIDO", "NACIEMIENTO");
            System.out.println("................................................................................................................................................");
            for (int i = 0; i < RepositorCliente.Clientes.size(); i++) {
                Fecha fecha = RepositorCliente.Clientes.get(i).getPerfil().getNacimiento();
                System.out.printf(": %-30s : %-20s : %-4s/%-4s/%-4s\n", RepositorCliente.Clientes.get(i).getPerfil().getNombre(), RepositorCliente.Clientes.get(i).getPerfil().getApellido(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad());
            }
            System.out.println("................................................................................................................................................");
        }
        System.out.println("Nombre del cliente:");
        String nombre = leer.next();
        System.out.println("Apellido del cliente:");
        String apellido = leer.next();
             boolean a = false;
        int pos = 0;
        for (int i = 0; i < RepositorCliente.Clientes.size(); i++) {
            Cliente cliente = RepositorCliente.Clientes.get(i);
            if (cliente.getPerfil().getNombre().equals(nombre) && cliente.getPerfil().getApellido().equals(apellido)) {
                if (!a) {
                    System.out.println("Cliente inexistente");
                } else {
                    if (!RepositorLibro.Libros.isEmpty()) {
                        System.out.println("No hay libros");
                    } else {
                        System.out.println("................................................................................................................................................");
                        System.out.printf(": %-20s : %-20s: %-20s: %-20s : %-10s :\n", "ISBN", "TITULO", "AUTOR", "FECHA DE PUBLICACION", "DISPONIBILIDAD");
                        System.out.println("................................................................................................................................................");
                        for (int k = 0; k < RepositorLibro.Libros.size(); k++) {
                            if (RepositorLibro.Libros.get(k).isDisponibilidad()) {
                                Fecha fecha = RepositorLibro.Libros.get(k).getFechaDePublicacion();
                                System.out.printf(": %-20s : %-20s :%-20s: %-6s/%-7s/%-7s : %-10s\n", RepositorLibro.Libros.get(k).getIsbn(), RepositorLibro.Libros.get(k).getTitulo(), RepositorLibro.Libros.get(k).getAutor().getPerfil().getNombre(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad(), RepositorLibro.Libros.get(k).isDisponibilidad());
                            }
                        }
                        System.out.println("................................................................................................................................................");
                    }
                    System.out.println("ISBN del Libro:");
                    String isbn = leer.next();
                    boolean I= true;
                    int posi = 0;
                    for (int k = 0; k < RepositorLibro.Libros.size(); k++) {
                        Libro libro = RepositorLibro.Libros.get(k);
                        if (libro.getIsbn().equals(isbn)) {
                            I=true;
                            if (!I) {
                                System.out.println("Libro inexistente");
                            } else {
                                cliente.setPrestamoLibro(libro);
                                libro.setDisponibilidad(false);
                                Fecha fechas = new Fecha();
                                fechas.setFechaPrestamos();
                                Transaccion transaccion = new Transaccion();
                                transaccion.setCliente(cliente);
                                transaccion.setFecha(fechas);
                                transaccion.setId(this.id());
                                transaccion.setTipo("Prestamo");
                                transaccion.setLibro(libro);
                                RepositorTransacciones.transacciones.add(transaccion);
                            }
                        }
                    }
                }
            }
        }
    }

    public void Devolucion() {
        System.out.println("Nombre del cliente:");
        String nombre = leer.next();
        System.out.println("Apellido del cliente:");
        String apellido = leer.next();
        System.out.println("Libro a devolver:");
        String titulo = leer.next();
        boolean d = false;
        int pos = 0;
        for (int i = 0; i < RepositorCliente.Clientes.size(); i++) {
            Cliente cliente = RepositorCliente.Clientes.get(i);
            if (cliente.getPerfil().getNombre().equals(nombre) && cliente.getPerfil().getApellido().equals(apellido)) {
                d=true;
                if (!d) {
                    System.out.println("Cliente inexistente");
                } else {
                    if (!cliente.getPrestamoLibro().isEmpty()) {
                        for (Libro libro : RepositorCliente.Clientes.get(i).getPrestamoLibro()) {
                            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                                libro.setDisponibilidad(true);
                                cliente.getPrestamoLibro().remove(libro);
                                Fecha fechas = new Fecha();
                                fechas.setFechaPrestamos();
                                Transaccion transaccion = new Transaccion();
                                transaccion.setCliente(cliente);
                                transaccion.setFecha(fechas);
                                transaccion.setId(this.id());
                                transaccion.setTipo("Devolucion");
                                transaccion.setLibro(libro);
                                RepositorTransacciones.transacciones.add(transaccion);
                            }
                        }
                    }
                }
            }
        }
    }

    public void Reportes() {
        System.out.println("{REPORTES}");
        System.out.println("> 1.- Rango de fechas");
        System.out.println("> 2.- Cliente");
        System.out.println("> 3.- Libro");
        int rep = leer.nextInt();
        leer.nextLine();
        switch (rep) {
            case 1 -> {
            this.rangofechas();
            }
            case 2 -> {
                this.reporteCliente();
            }
            case 3 -> {
                this.reporteLibro();
            }
        }
    }
    public void rangofechas(){
        System.out.println("Fecha de la transacción: ");
        System.out.println("Día: ");
        int dia=leer.nextInt();
        System.out.println("Mes: ");
        int mes=leer.nextInt();
        System.out.println("Año: ");
        int anualidad=leer.nextInt();
    }
    public void reporteCliente(){
        System.out.println("Nombre del cliente: ");
        String nombre = leer.next();
        System.out.println("Apellido del cliente: ");
        String apellido = leer.next();
        if(RepositorTransacciones.transacciones.isEmpty()){
            System.out.println("No hay transacciones registradas");
        } else {
            for (int i = 0; i < RepositorTransacciones.transacciones.size(); i++) {
                if (RepositorTransacciones.transacciones.get(i).getCliente().getPerfil().getNombre().equalsIgnoreCase(nombre) && RepositorTransacciones.transacciones.get(i).getCliente().getPerfil().getApellido().equalsIgnoreCase(apellido)) {
                    System.out.printf(": %-15s : %-15s : %-10s : %-15s : %-6s%n :", "CLIENTE", "LIBRO", "TIPO", "FECHA", "ID");
                    System.out.printf(": %-15s : %-15s : %-10s : %-15s : %-6s :%n", RepositorTransacciones.transacciones.get(i).getCliente().getPerfil().getNombre(), RepositorTransacciones.transacciones.get(i).getLibro().getTitulo(), RepositorTransacciones.transacciones.get(i).getTipo(), RepositorTransacciones.transacciones.get(i).getFecha().getPrestamos(), RepositorTransacciones.transacciones.get(i).getId());
                } else {
                    System.out.println("El cliente no tiene transacciones");
                }
            }
        }
    }
    public void reporteLibro(){
        System.out.println("Nombre del isbn: ");
        String isbn = leer.next();
        if(RepositorTransacciones.transacciones.isEmpty()){
            System.out.println("No hay transacciones registradas");
        } else {
            for (int i = 0; i < RepositorTransacciones.transacciones.size(); i++) {
                if (RepositorTransacciones.transacciones.get(i).getLibro().getIsbn().equalsIgnoreCase(isbn)) {
                    System.out.printf(": %-15s : %-15s : %-10s : %-15s : %-6s%n :", "CLIENTE", "LIBRO", "TIPO", "FECHA", "ID");
                    System.out.printf(": %-15s : %-15s : %-10s : %-15s : %-6s%n :", RepositorTransacciones.transacciones.get(i).getCliente().getPerfil().getNombre(), RepositorTransacciones.transacciones.get(i).getLibro().getTitulo(), RepositorTransacciones.transacciones.get(i).getTipo(), RepositorTransacciones.transacciones.get(i).getFecha().getPrestamos(), RepositorTransacciones.transacciones.get(i).getId());
                } else {
                    System.out.println("El libro no tiene transacciones");
                }
            }
        }
    }
    private String id(){
        String id = "";
        boolean rep = false;
        while(!rep) {
            for (int i = 0; i <  4; i++) {
                int ran = random.nextInt(10);
                String ids = String.valueOf(ran);
                id += ids;
            }
            if(!RepositorTransacciones.transacciones.isEmpty()){
                for(Transaccion transaccion : RepositorTransacciones.transacciones){
                    if(transaccion.getId().equalsIgnoreCase(id)){
                        id = this.id();
                        rep = true;
                    } else {
                        rep = false;
                    }
                }
            } else {
                rep = true;
            }
        }
        return id;
    }
}
