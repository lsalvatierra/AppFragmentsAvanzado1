package pe.edu.idat.appfragmentsavanzado1.Modelo;

public class Flores {
    private String Nombre;
    private String UrlImagen;

    public Flores(String nombre, String urlImagen) {
        Nombre = nombre;
        UrlImagen = urlImagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUrlImagen() {
        return UrlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        UrlImagen = urlImagen;
    }
}
