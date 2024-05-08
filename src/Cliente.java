import java.util.ArrayList;

public class Cliente extends  Usuario {

    private Perfil perfil;
    ArrayList<Libro> PrestamoLibro = new ArrayList<>();

    public void setPerfil(Perfil clientes) {
        perfil = clientes;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public ArrayList<Libro> getPrestamoLibro() {
        return PrestamoLibro;
    }

    public void setPrestamoLibro(Libro libro) {
        PrestamoLibro.add(libro);
    }

}