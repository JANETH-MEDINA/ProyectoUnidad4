import java.util.ArrayList;

public class Autor {
    private Perfil perfil;
    ArrayList<Libro> LibrosEscritos = new ArrayList<>();

    public void setPerfil(Perfil autores) {
        perfil = autores;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setLibrosEscritos(ArrayList<Libro> librosEscritos) {
        LibrosEscritos = librosEscritos;
    }

    public ArrayList<Libro> getLibrosEscritos() {
        return LibrosEscritos;

    }
}
