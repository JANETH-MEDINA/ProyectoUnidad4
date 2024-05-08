abstract class Usuario {
    private Perfil perfil;
    private String NombredeUsuario;
    private String contrasena;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getNombredeUsuario() {
        return NombredeUsuario;
    }

    public void setNombredeUsuario(String nombredeUsuario) {
        NombredeUsuario = nombredeUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = ControladorAdministrador.hashString(contrasena);
    }
    public static boolean checkPassword(String contra, Usuario usuario){
        if(ControladorAdministrador.hashString(contra).equals(usuario.getContrasena())){
            return true;
        }
        else return false;
    }
}
