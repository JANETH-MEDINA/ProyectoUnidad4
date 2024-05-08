import java.util.ArrayList;

public class Administrador extends Usuario

{
    ArrayList<Permisos>perimisos = new ArrayList<Permisos>();
    private boolean SuperAdmin;

    public boolean isSuperAdmin() {
        return SuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        SuperAdmin = superAdmin;
    }

    public void setEscribir(){
        this.perimisos.add(Permisos.ESCRIBIR);
    }
    public void setVer() {
        this.perimisos.add(Permisos.VER);
    }
    public void setBorrar() {
        this.perimisos.add(Permisos.BORRAR);
    }
    }
