public class Transaccion {
    private String id;
    private String tipo;
    private Cliente cliente;
     private Libro libro;
    private Fecha fecha;

    public String getId() {return id;}

    public String getTipo() {return tipo;}

    public Cliente getCliente() {return cliente;}

    public Libro getLibro() { return libro;}

    public Fecha getFecha() {return fecha;}

    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
}

