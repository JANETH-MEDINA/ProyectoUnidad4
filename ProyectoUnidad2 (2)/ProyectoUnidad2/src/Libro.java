import java.util.ArrayList;

public class Libro {
    private String isbn;
    private String titulo;
    private Autor autor;
    private Fecha FechaDePublicacion;
    private boolean Disponibilidad;

    public Libro(String isbn, String titulo, Autor autor, Fecha FechaDePublicacion, boolean Disponibilidad) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.FechaDePublicacion = FechaDePublicacion;
        this.Disponibilidad = Disponibilidad;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        Disponibilidad = disponibilidad;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setFechaDePublicacion(Fecha fechaDePublicacion) {
        FechaDePublicacion = fechaDePublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public Fecha getFechaDePublicacion() {
        return FechaDePublicacion;
    }

    public boolean isDisponibilidad() {
        return Disponibilidad;
    }
}
