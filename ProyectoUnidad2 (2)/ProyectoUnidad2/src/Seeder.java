public class Seeder {
    public static void DatosAgregados() {
        Administrador administrador1 = new Administrador();
        Fecha nacimiento1 = new Fecha(18, 5, 2004);
        Perfil perfil1 = new Perfil("Nadia", "Tovar", nacimiento1);
        administrador1.setSuperAdmin(true);
        administrador1.setContrasena("sa1234");
        administrador1.setNombredeUsuario("nadiwis");
        administrador1.setBorrar();
        administrador1.setEscribir();
        administrador1.setVer();
        RepositorUsuario.usuarios.add(administrador1);


        Cliente cliente1 = new Cliente();
        Fecha nacimiento2 = new Fecha(01, 8, 2003);
        Perfil perfil2 = new Perfil("Brandon", "Rodriguez", nacimiento2);
        cliente1.setContrasena("br1458");
        cliente1.setNombredeUsuario("brang");
        RepositorUsuario.usuarios.add(cliente1);


        Autor autor1 = new Autor();
        Fecha nacimiento3= new Fecha(23, 11, 1992);
        Fecha publicacion1 = new Fecha(15, 04, 2019);
        Perfil perfil3 = new Perfil("Eduardo", "Nieves", nacimiento3);
        Libro libro1 = new Libro("1234", "Mascotas", autor1, publicacion1, true);
        RepositorLibro.Libros.add(libro1);
        RepositorAutor.Autores.add(autor1);
    }
}
