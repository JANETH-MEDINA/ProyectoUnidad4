import java.util.Scanner;

public class ControladorLibro {
    Scanner leer = new Scanner(System.in);

    public void libro(Administrador admin) {
        boolean borrar = false;
        boolean escribir = false;
        boolean ver = false;
        for (Permisos per: admin.perimisos){
            if(String.valueOf(per).equalsIgnoreCase("borrar")){
                borrar = true;
            }
            if(String.valueOf(per).equalsIgnoreCase("escribir")){
                escribir = true;
            }
            if(String.valueOf(per).equalsIgnoreCase("ver")){
                ver = true;
            }
        }
        System.out.println("{MENU}");
        if(borrar) {
            System.out.println("> 1.- Borrar Libro");
        } if(escribir) {
            System.out.println("> 2.- Crear Libro");
            System.out.println("> 3.- Editar Libro");
        } if(ver) {
            System.out.println(">4.- Mostarar libros");
            System.out.println(">5.- Libros en prestamo");
            System.out.println(">6.- Libros disponibles");
        }
        System.out.println(">5.- Volver al menú principal");
        int lib = leer.nextInt();
        leer.nextLine();
        switch (lib) {
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

    public void borrar() {
        if (RepositorLibro.Libros.isEmpty()) {
            System.out.println("No hay libros");
        } else {
            System.out.println("ISBN del Libro:");
            String isbn = leer.next();
            boolean a = false;
            for (int i = 0; i < RepositorLibro.Libros.size(); i++) {
                Libro libro = RepositorLibro.Libros.get(i);
                if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                    RepositorLibro.Libros.remove(i);
                    a = true;
                    System.out.println("Libro eliminado");
                }
            }
            if (!a) {
                System.out.println("Libro inexistente");
            }
        }
    }

    public void crear() {
        if (RepositorAutor.Autores.isEmpty()) {
            System.out.println("No se puede crear un libro por que no hay autores");
        } else {
            System.out.println("DATOS DEL LIBRO");
            System.out.println(">1.-ISBN");
            String isbn = leer.next();
            System.out.println(">2.-Titulo");
            String titulo = leer.next();
            System.out.println(">3.-Autor");
            System.out.println("Nombre del Autor:");
            String nombre = leer.next();
            System.out.println("Apellido del Autor:");
            String apellido = leer.next();
            Autor autor = new Autor();
            for (int i = 0; i < RepositorAutor.Autores.size(); i++) {
                if (RepositorAutor.Autores.get(i).getPerfil().getNombre().equalsIgnoreCase(nombre) && RepositorAutor.Autores.get(i).getPerfil().getApellido().equalsIgnoreCase(apellido)) {
                    autor = RepositorAutor.Autores.get(i);
                }
            }

            System.out.println(">4.-Fecha de publicación");
            System.out.println("Día: ");
            int dia = leer.nextInt();
            System.out.println("Mes: ");
            int mes = leer.nextInt();
            System.out.println("Año: ");
            int anualidad = leer.nextInt();
            Fecha FechaDePublicacion = new Fecha(dia, mes, anualidad);
            Libro libros = new Libro(isbn, titulo, autor, FechaDePublicacion, true);
            RepositorLibro.Libros.add(libros);
            System.out.println(">5.-Disponibilidad");
        }
    }

    public void editar() {
        if (RepositorLibro.Libros.isEmpty()) {
            System.out.println("No hay libros");
        } else {
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-20s: %-20s: %-20s : %-10s :\n", "ISBN", "TITULO", "AUTOR", "FECHA DE PUBLICACION", "DISPONIBILIDAD");
            System.out.println("................................................................................................................................................");
            for (int i = 0; i < RepositorLibro.Libros.size(); i++) {
                Fecha fecha = RepositorLibro.Libros.get(i).getFechaDePublicacion();
                System.out.printf(": %-20s : %-20s :%-20s: %-6s/%-7s/%-7s : %-10s\n", RepositorLibro.Libros.get(i).getIsbn(), RepositorLibro.Libros.get(i).getTitulo(), RepositorLibro.Libros.get(i).getAutor().getPerfil().getNombre(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad(), RepositorLibro.Libros.get(i).isDisponibilidad());
            }
            System.out.println("................................................................................................................................................");
        }

        System.out.println("LIBRO A EDITAR");
        System.out.println("ISBN del Libro:");
        String isbn = leer.nextLine();
        boolean a = false;
        int pos = 0;
        for (int i = 0; i < RepositorLibro.Libros.size(); i++) {
            Libro libro = RepositorLibro.Libros.get(i);
            if (libro.getIsbn().equals(isbn)) {
                if (!a) {
                    System.out.println("Libro inexistente");
                }
                System.out.println("¿Qué dato desea cambiar? (ISBN, Titulo, Autor, Fecha de publicación) ");
                String dato = leer.next();
                switch (dato) {
                    case "ISBN" -> {
                        System.out.println("Ingrese el ISBN correcto:");
                        String ISBN = leer.next();
                        libro.setIsbn(ISBN);
                    }
                    case "Titulo" -> {
                        System.out.println("Ingrese el titulo correcto:");
                        String tit = leer.next();
                        libro.setTitulo(tit);
                    }
                    case "Autor" -> {
                        System.out.println("Ingrese el autor correcto:");
                        System.out.println("Ingrese el nombre del autor:");
                        String nombre = leer.next();
                        System.out.println("Ingrese el apellido del autor:");
                        String apellido = leer.next();

                        for (Autor autor : RepositorAutor.Autores) {
                            if (autor.getPerfil().getNombre().equalsIgnoreCase(libro.getAutor().getPerfil().getNombre()) && autor.getPerfil().getApellido().equalsIgnoreCase(libro.getAutor().getPerfil().getApellido())) {
                                for (int j = 0; j < autor.LibrosEscritos.size(); j++) {
                                    if (autor.LibrosEscritos.get(j).getIsbn().equalsIgnoreCase(libro.getIsbn())) {
                                        autor.LibrosEscritos.remove(j);
                                    }
                                }
                            }
                            if (autor.getPerfil().getNombre().equalsIgnoreCase(nombre) && autor.getPerfil().getApellido().equalsIgnoreCase(apellido)) {
                                libro.setAutor(autor);
                            }
                        }

                    }
                    case "Publicación" -> {
                        System.out.println("Ingrese la fecha correcta:");
                        System.out.println("Día:");
                        int dia = leer.nextInt();
                        System.out.println("Mes:");
                        int mes = leer.nextInt();
                        System.out.println("Año:");
                        int anualidad = leer.nextInt();
                        Fecha publicaccion = new Fecha(dia, mes, anualidad);
                        libro.setFechaDePublicacion(publicaccion);
                    }
                }
            }
        }

    }

    public void mostrar() {
        if (RepositorLibro.Libros.isEmpty()) {
            System.out.println("No hay libros");
        } else {
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-20s: %-20s: %-20s : %-10s :\n", "ISBN", "TITULO", "AUTOR", "FECHA DE PUBLICACION", "DISPONIBILIDAD");
            System.out.println("................................................................................................................................................");
            for (int i = 0; i < RepositorLibro.Libros.size(); i++) {
                Fecha fecha = RepositorLibro.Libros.get(i).getFechaDePublicacion();
                System.out.printf(": %-20s : %-20s :%-20s: %-6s/%-7s/%-7s : %-10s\n", RepositorLibro.Libros.get(i).getIsbn(), RepositorLibro.Libros.get(i).getTitulo(), RepositorLibro.Libros.get(i).getAutor().getPerfil().getNombre(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad(), RepositorLibro.Libros.get(i).isDisponibilidad());
            }
            System.out.println("................................................................................................................................................");
        }
    }
}