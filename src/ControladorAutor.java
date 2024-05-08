import java.util.Scanner;

public class ControladorAutor {
    Scanner leer = new Scanner(System.in);

    public void autores(Administrador admin) {
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
            System.out.println("> 1.- Borrar Autor");
        }
        if(escribir) {
            System.out.println("> 2.- Crear Autor");
            System.out.println("> 3.- Editar Autor ");
        }
        if(ver) {
            System.out.println(">4.- Mostrar Autores");
            System.out.println(">5.- Ver libros del algun autor");
        }
        System.out.println(">6.- Volver al menú principal");
        int aut = leer.nextInt();
        switch (aut) {
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
            case 5 -> {
                this.verlibros();
            }
        }
    }

    public void borrar() {
        if (RepositorAutor.Autores.isEmpty()) {
            System.out.println("No hay autores");
        } else {
            System.out.println("Nombre del Autor:");
            String nombre = leer.next();
            System.out.println("Apellido del Autor");
            String apellido = leer.next();
            boolean a = false;
            for (int i = 0; i < RepositorAutor.Autores.size(); i++) {
                Autor autor = RepositorAutor.Autores.get(i);
                if (autor.getPerfil().getNombre().equals(nombre) && autor.getPerfil().getApellido().equals(apellido)) {
                    RepositorAutor.Autores.remove(i);
                    a = true;
                    System.out.println("Autor eliminado");
                    break;
                }
            }
            if (!a) {
                System.out.println("Autor inexistente");
            }
        }
    }

    public void crear() {
        System.out.println("DATOS DEL AUTOR");
        System.out.println(">1.-Nombre del autor");
        String nombre = leer.next();
        System.out.println(">2.-Apellido del  autor");
        String apellido = leer.next();
        System.out.println(">3.-Fecha de Nacimiento con 2 digitos");
        System.out.println("Día: ");
        int dia = leer.nextInt();
        System.out.println("Mes: ");
        int mes = leer.nextInt();
        System.out.println("Año: ");
        int anualidad = leer.nextInt();
        Fecha nacimiento = new Fecha(dia, mes, anualidad);
        Perfil autores = new Perfil(nombre, apellido, nacimiento);
        Autor autoress = new Autor();
        autoress.setPerfil(autores);
        RepositorAutor.Autores.add(autoress);
    }

    public void editar() {
        if (RepositorAutor.Autores.isEmpty()) {
            System.out.println("No hay autores");
        } else {
            String libros = " ";
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-10s : %-10s :\n", "NOMBRE", "APELLIDO", "NACIEMIENTO", "LIBROS");
            System.out.println("................................................................................................................................................");
            for (int i = 0; i < RepositorAutor.Autores.size(); i++) {
                if (RepositorAutor.Autores.get(i).getLibrosEscritos().isEmpty()) {
                    libros = "Sin libros";
                } else {
                    for (int j = 0; j < RepositorAutor.Autores.get(i).getLibrosEscritos().size(); j++) {
                        libros += RepositorAutor.Autores.get(i).getLibrosEscritos().get(j).getTitulo() + " : ";
                    }
                }
                Fecha fecha = RepositorAutor.Autores.get(i).getPerfil().getNacimiento();
                System.out.printf(": %-30s : %-20s : %-4s/%-4s/%-4s\n", RepositorAutor.Autores.get(i).getPerfil().getNombre(), RepositorAutor.Autores.get(i).getPerfil().getApellido(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad(), libros);
            }
            System.out.println("................................................................................................................................................");
        }

        System.out.println("AUTOR A EDITAR");
        System.out.println("Nombre del Autor:");
        String nombre = leer.next();
        System.out.println("Apellido del Autor");
        String apellido = leer.next();
        boolean a = false;
        int pos = 0;
        for (int i = 0; i < RepositorAutor.Autores.size(); i++) {
            Autor autor = RepositorAutor.Autores.get(i);
            if (autor.getPerfil().getNombre().equals(nombre) && autor.getPerfil().getApellido().equals(apellido)) {
                if (!a) {
                    System.out.println("Autor inexistente");
                }
                System.out.println("¿Qué dato desea cambiar? (Nombre, Apellido, Nacimiento) ");
                String dato = leer.next();
                switch (dato) {
                    case "Nombre" -> {
                        System.out.println("Ingrese el nombre correcto:");
                        String nomb = leer.next();
                        autor.getPerfil().setNombre(nomb);
                    }
                    case "Apellido" -> {
                        System.out.println("Ingrese el apellido correcto:");
                        String ape = leer.next();
                        autor.getPerfil().setApellido(ape);
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
                        autor.getPerfil().setNacimiento(nacimiento);


                    }
                }
            }
        }

    }

    public void mostrar() {
        if (RepositorAutor.Autores.isEmpty()) {
            System.out.println("No hay autores");
        } else {
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-10s : %-10s :\n", "NOMBRE", "APELLIDO", "NACIEMIENTO");
            System.out.println("................................................................................................................................................");
            for (int i = 0; i < RepositorAutor.Autores.size(); i++) {
                Fecha fecha = RepositorAutor.Autores.get(i).getPerfil().getNacimiento();
                System.out.printf(": %-30s : %-20s : %-4s/%-4s/%-4s\n", RepositorAutor.Autores.get(i).getPerfil().getNombre(), RepositorAutor.Autores.get(i).getPerfil().getApellido(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad());
            }
            System.out.println("................................................................................................................................................");
        }
    }

    public void verlibros() {
        System.out.println("De que autor desea ver los libros?");
        if (RepositorAutor.Autores.isEmpty()) {
            System.out.println("No hay autores");
        } else {
            System.out.println("................................................................................................................................................");
            System.out.printf(": %-20s : %-10s : %-10s :\n", "NOMBRE", "APELLIDO", "NACIEMIENTO");
            System.out.println("................................................................................................................................................");
            for (int i = 0; i < RepositorAutor.Autores.size(); i++) {
                Fecha fecha = RepositorAutor.Autores.get(i).getPerfil().getNacimiento();
                System.out.printf(": %-30s : %-20s : %-4s/%-4s/%-4s\n", RepositorAutor.Autores.get(i).getPerfil().getNombre(), RepositorAutor.Autores.get(i).getPerfil().getApellido(), fecha.getDia(), fecha.getMes(), fecha.getAnualidad());
            }
            System.out.println("................................................................................................................................................");
        }
    }
}
